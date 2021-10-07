package test;

import java.util.*;

public class Chapter13Re {

    public static int n, m, l, result;
    public static int[] arr;
    public static Scanner sc = new Scanner(System.in);
    public static int[][] map;
    public static int[][] temp;
    public static int[] d;
    static int[] parent;
    static boolean[][] visited;
    public static int INF = (int) 1e9;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) {
        _13_3();
    }

    static void _13_1() {

        n = sc.nextInt();
        m = sc.nextInt();
        l = sc.nextInt();
        int start = sc.nextInt();
        d = new int[n + 1];

        ArrayList<ArrayList<Node>> list = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 1; i <= n; i++) {
            d[i] = INF;
        }

        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            list.get(a).add(new Node(b, 1));
        }
        d[start] = 0;

        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(start, 0));


        while (!queue.isEmpty()) {

            Node node = queue.poll();
            int now = node.getIndex();
            int distance = node.getDistance();

            for (int i = 0; i < list.get(now).size(); i++) {

                int idx = list.get(now).get(i).getIndex();
                int cost = list.get(now).get(i).getDistance() + distance;

                if (d[idx] > cost) {
                    d[idx] = cost;
                    queue.offer(new Node(idx, cost));
                }
            }
        }

        boolean result = false;

        for (int i = 1; i <= n; i++) {
            if (d[i] == l) {
                System.out.println(i);
                result = true;
            }
        }
        System.out.println(!result ? "-1" : "");
    }

    static void _13_2() {
        n = sc.nextInt();
        m = sc.nextInt();

        map = new int[n][m];
        temp = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                map[i][j] = sc.nextInt();
            }
        }
        result = 0;

        _13_2_dfs(0);
        System.out.println(result);

    }

    static void _13_2_dfs(int count) {

        if (count == 3) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    temp[i][j] = map[i][j];
                }
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (map[i][j] == 2) {
                        _13_2_virus(i, j);
                    }
                }
            }
            result = Math.max(result, _13_2_GetCount());
            return;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 0) {
                    map[i][j] = 1;
                    _13_2_dfs(count + 1);
                    map[i][j] = 0;
                }
            }
        }
    }

    static int _13_2_GetCount() {
        int count = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (temp[i][j] == 0) count++;
            }
        }
        return count;
    }

    static void _13_2_virus(int x, int y) {

        for (int i = 0; i < 4; i++) {

            int nx = x + dx[i];
            int ny = y + dy[i];

            if (0 <= nx && nx < n && 0 <= ny && ny < m && temp[nx][ny] == 0) {
                temp[nx][ny] = 2;
                _13_2_virus(nx, ny);
            }
        }
    }

    /*
    순서대로 bfs를 하는쪽을 계속 고민하다 같은 실수번복함.. ㅠ
    순서를 정렬하고 bfs 를 행하였으므로 순서대로 queue에 쌓여서
    최종적으로 정해놓은 시간 s에만 탈출하고 거기서 바뀐 map 데이터값을 뿌려주면 끝.
     */
    static void _13_3() {
        n = sc.nextInt();
        m = sc.nextInt();
        map = new int[n][n];
        visited = new boolean[n][n];

        ArrayList<VirusInfo> infoList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int type = sc.nextInt();
                map[i][j] = type;
                if (type != 0) infoList.add(new VirusInfo(i, j, 0, type));
            }
        }


        int s = sc.nextInt();
        int x = sc.nextInt();
        int y = sc.nextInt();

        Collections.sort(infoList);

        Queue<VirusInfo> queue = new LinkedList<>();

        for(int i = 0 ; i < infoList.size(); i++){
            queue.offer(infoList.get(i));
        }

        while (!queue.isEmpty()) {

            VirusInfo info = queue.poll();

            if(info.time == s) break;

            for (int j = 0; j < 4; j++) {

                int nx = info.x + dx[j];
                int ny = info.y + dy[j];

                if (0 <= nx && nx < n && 0 <= ny && ny < n && map[nx][ny] == 0) {
                    map[nx][ny] = info.type;
                    queue.offer(new VirusInfo(nx, ny ,info.time+1 ,info.type));
                }
            }
        }

        System.out.println(map[x-1][y-1]);
    }

    static void _13_3_dfs(int x, int y, int count) {

        if (count > 3) return;

        for (int i = 0; i < 4; i++) {

            int nx = x + dx[i];
            int ny = y + dy[i];

            if (0 <= nx && nx < n && 0 <= ny && ny < n) {
                _13_3_dfs(nx, ny, count + 1);
            }
        }
    }

}

class VirusInfo implements Comparable<VirusInfo> {

    int x;
    int y;
    int time;
    int type;

    public VirusInfo(int x, int y, int time, int type) {
        this.x = x;
        this.y = y;
        this.time = time;
        this.type = type;
    }

    @Override
    public int compareTo(VirusInfo o) {
        return type - o.type;
    }
}

