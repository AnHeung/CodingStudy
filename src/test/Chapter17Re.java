package test;

import java.util.*;

public class Chapter17Re {

    public static int n, m, result;
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
        _17_3();
    }

    static void _17_1() {

        n = sc.nextInt();
        m = sc.nextInt();
        d = new int[n + 1];

        map = new int[n + 1][n + 1];

        for (int i = 0; i <= n; i++) {
            Arrays.fill(map[i], INF);
            for (int j = 0; j <= n; j++) {
                if (i == j) map[i][j] = 0;
            }
        }

        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int cost = sc.nextInt();
            if (map[a][b] == INF || map[a][b] != INF && cost < map[a][b]) {
                map[a][b] = cost;
            }
        }

        for (int k = 1; k <= n; k++) {
            for (int x = 1; x <= n; x++) {
                for (int y = 1; y <= n; y++) {
                    map[x][y] = Math.min(map[x][y], map[x][k] + map[k][y]);
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }

    }

    static void _17_2() {
        n = sc.nextInt();
        m = sc.nextInt();
        map = new int[n][n];


        for (int i = 0; i < n; i++) {
            Arrays.fill(map[i], INF);
            for (int j = 0; j < n; j++) {
                if (i == j) map[i][j] = 0;
            }
        }

        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            map[a - 1][b - 1] = 1;
        }

        for (int k = 0; k < n; k++) {
            for (int x = 0; x < n; x++) {
                for (int y = 0; y < n; y++) {
                    map[x][y] = Math.min(map[x][y], map[x][k] + map[k][y]);
                }
            }
        }

        int cnt = 0;
        for (int i = 0; i < n; i++) {
            boolean result = true;
            for (int j = 0; j < n; j++) {
                if (map[i][j] == INF && map[j][i] == INF) {
                    result = false;
                    break;
                }
            }
            if (result) cnt++;
        }

        System.out.println(cnt);
    }

    static void _17_3() {

        int t = sc.nextInt();

        for (int time = 0; time < t; time++) {
            n = sc.nextInt();
            map = new int[n][n];
            temp = new int[n][n];
            visited = new boolean[n][n];
            result = INF;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    map[i][j] = sc.nextInt();
                }
            }

            for (int i = 0; i < n; i++) {
                Arrays.fill(temp[i], INF);
            }

            _17_3_dfs(0, 0 , map[0][0]);
            System.out.println(result);
//            System.out.println(temp[n-1][n-1]);
        }
    }
    static void _17_3_bfs(){

        int x = 0, y = 0, sum = 0;
        Queue<SpacePosition> q = new LinkedList<>();
        q.offer(new SpacePosition(x, y, map[x][y]));
        temp[x][y] = map[x][y];

        while (!q.isEmpty()) {

            SpacePosition sp = q.poll();
            x = sp.x;
            y = sp.y;
            sum = sp.sum;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (0 <= nx && nx < n && 0 <= ny && ny < n) {
                    if (temp[nx][ny] > map[nx][ny] + sum) {
                        temp[nx][ny] = map[nx][ny] + sum;
                        q.offer(new SpacePosition(nx, ny, temp[nx][ny]));
                    }
                }
            }
        }
    }
    static void _17_3_dfs(int x , int y , int sum ){

        if(x == n-1 && y == n-1){
            result = Math.min(result, sum);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (0 <= nx && nx < n && 0 <= ny && ny < n) {
                if(temp[nx][ny] > map[nx][ny] + sum){
                    temp[nx][ny] = map[nx][ny] + sum;
                    _17_3_dfs(nx,ny, temp[nx][ny]);
                }
            }
        }
    }

    static void _17_3_upgradeDijkstra(){
        int x = 0, y = 0, sum = 0;
        PriorityQueue<SpacePosition> q = new PriorityQueue<>();
        q.offer(new SpacePosition(x, y, map[x][y]));
        temp[x][y] = map[x][y];

        while (!q.isEmpty()) {

            SpacePosition sp = q.poll();
            x = sp.x;
            y = sp.y;
            sum = sp.sum;


            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(temp[x][y] < sum)continue;


                if (0 <= nx && nx < n && 0 <= ny && ny < n) {
                    if (temp[nx][ny] > map[nx][ny] + sum) {
                        temp[nx][ny] = map[nx][ny] + sum;
                        q.offer(new SpacePosition(nx, ny, temp[nx][ny]));
                    }
                }
            }
        }
    }
}

class SpacePosition implements  Comparable<SpacePosition>{

    int x;
    int y;
    int sum;

    public SpacePosition(int x, int y, int sum) {
        this.x = x;
        this.y = y;
        this.sum = sum;
    }

    @Override
    public int compareTo(SpacePosition o) {
        return sum - o.sum;
    }
}
