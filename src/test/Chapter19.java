package test;

import java.util.*;

public class Chapter19 {


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
        _19_1();
    }

    static void _19_1() {

        n = sc.nextInt();
        int x = 0;
        int y = 0;
        int time = 0;
        int eatCount = 0;
        boolean eatFlag = false;
        int size = 2;

        map = new int[n][n];
        visited = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int a = sc.nextInt();
                map[i][j] = a;
                if (a == 9) {
                    map[i][j] = 0;
                    x = i;
                    y = j;
                }

            }
        }

        ArrayList<SharkPosition> queue = new ArrayList<>();
        queue.add(new SharkPosition(x, y));

        while (!queue.isEmpty()) {

            Collections.sort(queue);
            int qSize = queue.size();

            for (int i = 0; i < qSize; i++) {

                SharkPosition pos = queue.remove(0);
                x = pos.x;
                y = pos.y;

                if (0 < map[x][y] && map[x][y] < size) {
                    map[x][y] = 0;
                    eatCount++;

                    if (eatCount == size) {
                        size++;
                        eatCount = 0;
                    }
                    queue.clear();
                    visited = new boolean[n][n];
                    visited[x][y] = true;
                    result = time;
                    eatFlag = true;
                }

                for (int j = 0; j < 4; j++) {

                    int nx = x + dx[j];
                    int ny = y + dy[j];

                    if (nx >= 0 && nx < n && ny >= 0 && ny < n && !visited[nx][ny] && map[nx][ny] <= size) {
                        queue.add(new SharkPosition(nx, ny));
                        visited[nx][ny] = true;
                    }
                }
                if(eatFlag){
                    eatFlag = false;
                    break;
                }
            }
            time++;
        }

        System.out.println(result);
    }

    static boolean isEmpty() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] != 9 && map[i][j] != 0) {
                    return true;
                }
            }
        }
        return false;
    }
}

class SharkPosition implements Comparable<SharkPosition> {

    int x;
    int y;

    public SharkPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(SharkPosition o) {
        if(this.x == o.x) return this.y - o.y;
        return this.x - o.x;
    }
}

