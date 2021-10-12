package test;

import java.util.*;

public class Chapter13Re {

    public static int n, m, l, result, max, min;
    static int plus = 0, sub = 0, mul = 0, div = 0;
    public static int[] arr;
    public static Scanner sc = new Scanner(System.in);
    public static int[][] map;
    public static int[][] union;
    public static String[][] graph;
    public static int[][] temp;
    public static int[] d;
    static int[] parent;
    static boolean[][] visited;
    public static int INF = (int) 1e9;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) {
        _13_7();
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

        for (int i = 0; i < infoList.size(); i++) {
            queue.offer(infoList.get(i));
        }

        while (!queue.isEmpty()) {

            VirusInfo info = queue.poll();

            if (info.time == s) break;

            for (int j = 0; j < 4; j++) {

                int nx = info.x + dx[j];
                int ny = info.y + dy[j];

                if (0 <= nx && nx < n && 0 <= ny && ny < n && map[nx][ny] == 0) {
                    map[nx][ny] = info.type;
                    queue.offer(new VirusInfo(nx, ny, info.time + 1, info.type));
                }
            }
        }

        System.out.println(map[x - 1][y - 1]);
    }

    static void _13_4() {

        String str = sc.next();
        String result = _13_4_sol(str);
        System.out.println(result);
    }

    static boolean _13_4_isCorrectStr(String str) {

        int count = 0;

        for (char c : str.toCharArray()) {
            if (c == '(') count++;
            else if (c == ')') {
                count--;
            }
            if (count < 0) return false;
        }
        return true;
    }

    static int getEqualIdx(String str) {

        int count = 0;

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(') count++;
            else if (str.charAt(i) == ')') {
                count--;
            }
            if (count == 0) return i;
        }
        return -1;
    }

    static String _13_4_reverseStr(String str) {

        StringBuilder result = new StringBuilder();

        for (char c : str.toCharArray()) {
            if (c == ')') result.append('(');
            else if (c == '(') result.append(')');
        }
        return result.toString();
    }

    static String _13_4_sol(String str) {

        if ("".equals(str)) return "";

        String result;

        int firstIdx = getEqualIdx(str);

        String u = str.substring(0, firstIdx + 1);
        String v = str.substring(firstIdx + 1);

        if (_13_4_isCorrectStr(u)) {
            result = u + _13_4_sol(v);
        } else {
            result = "(";
            result += _13_4_sol(v) + ')';
            u = u.substring(1, u.length() - 1);
            result += _13_4_reverseStr(u);
        }

        return result;
    }

    static void _13_5() {
        n = sc.nextInt();
        arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        for (int i = 0; i < 4; i++) {
            int x = sc.nextInt();
            if (i == 0) plus = x;
            if (i == 1) sub = x;
            if (i == 2) mul = x;
            if (i == 3) div = x;
        }

        max = -(int) 1e9;
        min = (int) 1e9;

        _13_5_sol(arr[0], 1, plus + sub + mul + div);

        System.out.println();
    }

    static void _13_5_sol(int result, int idx, int count) {

        if (count < 0 || idx >= n) {
            max = Math.max(max, result);
            min = Math.min(min, result);
            return;
        }

        if (plus > 0) {
            plus--;
            _13_5_sol(result + arr[idx], idx + 1, count - 1);
            plus++;
        }

        if (sub > 0) {
            sub--;
            _13_5_sol(result - arr[idx], idx + 1, count - 1);
            sub++;
        }
        if (mul > 0) {
            mul--;
            _13_5_sol(result * arr[idx], idx + 1, count - 1);
            mul++;
        }
        if (div > 0) {
            div--;
            _13_5_sol(result / arr[idx], idx + 1, count - 1);
            div++;
        }
    }

    static void _13_6() {

        n = sc.nextInt();
        map = new int[n][n];
        ArrayList<Position> studentList = new ArrayList<>();
        graph = new String[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                String type = sc.next();
                if ("S".equals(type)) studentList.add(new Position(i, j));
                graph[i][j] = type;
            }
        }

        System.out.println(_13_6_dfs(0, studentList));
    }

    static String _13_6_dfs(int count, ArrayList<Position> studentList) {

        if (count == 3) {

            for (int i = 0; i < studentList.size(); i++) {
                if (_13_6_Check(studentList.get(i).getX(), studentList.get(i).getY(), graph)) {
                    return "YES";
                }
            }
            return "NO";
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (graph[i][j].equals("X")) {
                    graph[i][j] = "O";
                    if ("YES".equals(_13_6_dfs(count + 1, studentList))) {
                        return "YES";
                    }
                    graph[i][j] = "X";
                }
            }
        }
        return "NO";
    }

    static boolean _13_6_Check(int x, int y, String[][] temp) {

        for (int i = 0; i < 4; i++) {

            int nx = x;
            int ny = y;

            for (int j = 0; j < 4; j++) {

                nx += dx[i];
                ny += dy[i];

                if (0 <= nx && nx < n && 0 <= ny && ny < n) {
                    if (temp[nx][ny].equals("T")) return false;
                    if (temp[nx][ny].equals("O")) break;
                }
            }
        }
        return true;
    }

    /*
        핵심 로직은 풀었으나 문제를 잘못이해해서 헤맷다.
        일단 인구이동이 끝나는 조건에 대해 이해못했고 (더 이상 인구이동이 없다 = 더이상 좌표상 조건이 안되서 국경선이 열리지 않는다)
        두 나라의 연합 조건을 고민할때 bfs 로 접근한건 좋았으나
        만약
        2 20 50
        50 30
        20 40
        이런순일때 50 30 20 이 3개는 연합이라고 생각했으나 20 40은 조건은 맞지만 50에서 가능한 연합이라고 생각안했다 (이것도 문제 잘못이해)
        문제 자체는 그냥 두 나라간 인구차이가 조건에 맞게 나면 국경이 서로 열리고 그 나라를 연합이라 했는데 생각해보면
        50 30 20 이 일단 열리고 그후에 20->40으로 가는데도 열리기 때문에 모두 연결되있다고 고려할수있다.
     */
    static void _13_7() {

        n = sc.nextInt(); //땅크기
        m = sc.nextInt(); //인구차이 m 명이상
        l = sc.nextInt(); // 인구차이 l 명 이하
        union = new int[n][n];
        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        visited = new boolean[n][n];

        while(true){

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    visited[i][j] = false;
                }
            }

            int idx = 0;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (!visited[i][j]) {
                        _13_7_bfs(i, j);
                        idx++;
                    }

                }
            }

            if(idx == n*n) break;
            result++;
        }
        System.out.println(result);

    }

    static void _13_7_bfs(int x, int y) {

        int unionCount = 1;
        ArrayList<Position> list = new ArrayList<>();
        list.add(new Position(x, y));
        int sum = map[x][y];

        visited[x][y] = true;

        Queue<Position> queue = new LinkedList<>();
        queue.offer(new Position(x, y));

        while (!queue.isEmpty()) {

            Position position = queue.poll();
            x = position.getX();
            y = position.getY();

            for (int i = 0; i < 4; i++) {

                int nx = x + dx[i];
                int ny = y + dy[i];

                if (0 <= nx && nx < n && 0 <= ny && ny < n && !visited[nx][ny]) {
                    int diff = Math.abs(map[x][y] - map[nx][ny]);

                    if (m <= diff && diff <= l) {
                        visited[nx][ny] = true;
                        sum += map[nx][ny];
                        queue.offer(new Position(nx, ny));
                        list.add(new Position(nx, ny));
                        unionCount++;
                    }
                }
            }
        }

        for (int i = 0; i < list.size(); i++) {
            map[list.get(i).getX()][list.get(i).getY()] = sum / unionCount;
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

