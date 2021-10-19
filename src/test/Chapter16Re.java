package test;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Chapter16Re {

    public static int n, m, l, result;
    public static int max = -(int) 1e9;
    public static int[] arr;
    public static Scanner sc = new Scanner(System.in);
    public static int[][] map;
    public static int[][] temp;
    public static int[] d;
    static int[] parent;
    static boolean[][] visited;
    public static int INF = (int) 1e9;
    static int[] dx = {-1, 0, 1};
    static int[] dy = {1, 1, 1};

    public static void main(String[] args) {
        _16_1();
    }

    static void _16_1() {
        int t = sc.nextInt();

        for (int time = 0; time < t; time++) {
            n = sc.nextInt();
            m = sc.nextInt();

            result = -(int) 1e9;
            map = new int[n][m];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    map[i][j] = sc.nextInt();
                }
            }

            temp = new int[n][m];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < 1; j++) {
                    temp[i][j] = map[i][j];
                    _16_1_bfs(i, j);
                }
            }
            System.out.println(temp[n - 1][m - 1]);
        }
    }

    static void _16_1_bfs(int x, int y) {

        Queue<MineInfo> q = new LinkedList<>();
        q.offer(new MineInfo(x, y, temp[x][y]));

        while (!q.isEmpty()) {

            MineInfo now = q.poll();
            x = now.x;
            y = now.y;
            int sum = now.sum;

            for (int i = 0; i < 3; i++) {

                int nx = x + dx[i];
                int ny = y + dy[i];

                if (0 <= nx && nx < n && 0 <= ny && ny < m) {
                    temp[nx][ny] = Math.max(temp[nx][ny], map[nx][ny] + sum);
                    q.offer(new MineInfo(nx, ny, temp[nx][ny]));
                }
            }
        }
    }

    static void _16_1_dfs(int sum, int x, int y) {

        if (x == n - 1 && y == m - 1) {
            result = Math.max(result, sum);
            return;
        }

        for (int i = 0; i < 3; i++) {

            int nx = x + dx[i];
            int ny = y + dy[i];

            if (0 <= nx && nx < n && 0 <= ny && ny < m) {
                _16_1_dfs(sum + map[nx][ny], nx, ny);
            }
        }
    }
}

class MineInfo {

    int x;
    int y;
    int sum;

    public MineInfo(int x, int y, int sum) {
        this.x = x;
        this.y = y;
        this.sum = sum;
    }
}
