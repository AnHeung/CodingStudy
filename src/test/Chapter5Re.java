package test;

import java.util.*;

public class Chapter5Re {

    public static int n, m, result;
    public static int[] arr;
    public static Scanner sc = new Scanner(System.in);
    public static int[][] map;
    public static int[] d;
    static int[] parent;
    static boolean[][] visited;
    public static int INF = (int) 1e9;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) {
        _5_2();
    }

    static void _5_1() {
        n = sc.nextInt();
        m = sc.nextInt();

        map = new int[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            String lines = sc.next();
            for (int j = 0; j < m; j++) {
                map[i][j] = lines.charAt(j) - '0';
            }
        }

        visited[0][0] = true;
        map[0][0] = 1;

        int cnt = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (_5_1_dfs(i, j)) {
                    cnt++;
                }
            }
        }
        System.out.println(cnt);
    }

    static boolean _5_1_dfs(int x, int y) {

        boolean result = false;

        for (int i = 0; i < 4; i++) {

            int nx = x + dx[i];
            int ny = y + dy[i];

            if (0 <= nx && nx < n && 0 <= ny && ny < m && !visited[nx][ny] && map[nx][ny] == 0) {
                visited[nx][ny] = true;
                map[nx][ny] = 1;
                _5_1_dfs(nx, ny);
                result = true;
            }
        }
        return result;
    }

    /*
    다풀고 이동칸수를 뽑아낼때 count 라는 변수를 더하는 식으로 하려 했으나
    그렇게 되면 이동시 되돌아오는 칸수까지 더 해지게 되서 문제를 못풀게된다.
    그래서 한칸씩 이동하는걸 map 자체에 기록해서 그걸 가지고 답을 구하면 된다.
    dfs 로는 쉽게 풀수 있으나 bfs 로 풀려다 좀 헤깔렷다.
     */
    static void _5_2() {
        n = sc.nextInt();
        m = sc.nextInt();

        map = new int[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            String lines = sc.next();
            for (int j = 0; j < m; j++) {
                map[i][j] = lines.charAt(j) - '0';
            }
        }

        int x = 0;
        int y = 0;
        visited[x][y] = true;

        PriorityQueue<MazeInfo> q = new PriorityQueue<>();
        q.offer(new MazeInfo(x, y));

        while (!q.isEmpty()) {

            MazeInfo now = q.poll();
            x = now.x;
            y = now.y;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (0 <= nx && nx < n && 0 <= ny && ny < m && map[nx][ny] == 1 && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    map[nx][ny] = map[x][y] + 1;
                    q.offer(new MazeInfo(nx, ny));
                }
            }
        }
        System.out.println(map[n-1][m-1]);
    }
}

class MazeInfo implements Comparable<MazeInfo> {

    int x;
    int y;

    public MazeInfo(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(MazeInfo o) {
        if (x == o.x) {
            return o.y - this.y;
        }
        return o.x - this.x;
    }
}
