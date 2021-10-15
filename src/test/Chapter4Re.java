package test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Chapter4Re {

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int n, m, x, y, l, r, result, direction = 0;
    int[] d;
    public static Scanner sc = new Scanner(System.in);
    static int[][] map;
    static boolean[][] visited;
    static int min = (int) 1e9;
    static int max = -(int) 1e9;

    public static void main(String[] args) {
        _4_3();
    }

    /*
    풀긴했는데 시간좀 약간 걸림 (동남서북으로 생각해서 조금 헤멧고
    네방향 다 돌고 뒤로 한발 뺄때 방문한칸은 안된다 생각해서 좀 걸렸다.
     */
    static void _4_3() {
        n = sc.nextInt(); //가로
        m = sc.nextInt(); //세로

        x = sc.nextInt();
        y = sc.nextInt();
        direction = sc.nextInt();

        map = new int[n][m];
        visited = new boolean[n][m];

        ArrayList<Position> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        visited[x][y] = true;

        int cnt = 0;

        while (true) {

            boolean isAll = false;

            for (int i = 0; i < 4; i++) {
                direction = turnLeft(direction);

                int nx = x + dx[direction];
                int ny = y + dy[direction];

                if (0 <= nx && nx < n && 0 <= ny && ny < n) {

                    if (!visited[nx][ny] && map[nx][ny] == 0) {
                        visited[nx][ny] = true;
                        x = nx;
                        y = ny;
                        cnt++;
                        isAll = true;
                        break;
                    }
                }
            }

            if (!isAll) {
                int nx = x + dx[turn(direction)];
                int ny = y + dy[turn(direction)];

                if (0 <= nx && nx < n && 0 <= ny && ny < n && map[nx][ny] == 0) {
                    x = nx;
                    y = ny;
                    cnt++;
                } else {
                    break;
                }
            }
        }

        System.out.println(cnt);
    }

    static int turnLeft(int direction) {
        return direction == 0 ? 3 : direction - 1;
    }

    static int turn(int direction) {
        if (direction < 2) return direction + 2;
        return direction - 2;
    }
}
