package test;

import java.util.*;

public class Chapter13 {

    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static int n, m, result = 0;


    public static void main(String[] args) {
        _13_2();
    }


    static void _13_1() {

        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        int k = sc.nextInt();
        int start = sc.nextInt();
        int[] d = new int[3000001];

        ArrayList<ArrayList<Integer>> list = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
            d[i] = -1;
        }

        for (int i = 0; i <= n; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            list.get(a).add(b);
        }

        d[start] = 0;

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);

        while (!queue.isEmpty()) {
            int now = queue.poll();

            for (int i = 0; i < list.size(); i++) {

                int next = list.get(now).get(i);

                if (d[next] == -1) {
                    d[next] = d[now] + 1;
                    queue.offer(next);
                }
            }
        }

        boolean result = false;
        for (int i = 0; i < list.size(); i++) {
            if (d[i] == k) {
                System.out.println(i);
                result = true;
            }
        }
        System.out.println(!result ? -1 : "");
    }

    static void _13_2() {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        int[][] map = new int[n][m];
        int[][] temp = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = sc.nextInt();
                temp[i][j] = map[i][j];
            }
        }

        _13_2_dfs(0, map, temp);

        System.out.println(result);
    }

    static void _13_2_dfs(int count,  int[][] map, int[][] temp) {

        if (count == 3) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    temp[i][j] = map[i][j];
                }
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (temp[i][j] == 2) {
                        _13_2_virus(temp, i, j);
                    }
                }
            }
            result = Math.max(result, _13_2_getScore(temp));
            return;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 0) {
                    map[i][j] = 1;
                    count += 1;
                    _13_2_dfs(count, map, temp);
                    map[i][j] = 0;
                    count -= 1;
                }
            }
        }
    }

    static int _13_2_getScore(int[][] temp) {
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (temp[i][j] == 0) count++;
            }
        }
        return count;
    }

    static void _13_2_virus(int[][] temp, int x, int y) {
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && nx < n && ny >= 0 && ny < m && temp[nx][ny] == 0) {
                temp[nx][ny] = 2;
                _13_2_virus(temp, nx, ny);
            }
        }
    }
}
