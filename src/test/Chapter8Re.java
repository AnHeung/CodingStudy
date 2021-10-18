package test;

import java.util.Scanner;

public class Chapter8Re {

    public static int n, m, result;
    public static int min = (int) 1e9;
    public static int max = -(int) 1e9;
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
        _8_2();
    }

    static void _8_1() {

        n = sc.nextInt();

        _8_1_recursive(n, 0);

        System.out.println(min);

    }

    static void _8_1_recursive(int n, int count) {

        if (n == 1 || n == 0) {
            min = Math.min(min, count);
            return;
        }

        if (n % 5 == 0) {
            _8_1_recursive(n / 5, count + 1);
        }

        if (n % 3 == 0) {
            _8_1_recursive(n / 3, count + 1);
        }

        if (n % 2 == 0) {
            _8_1_recursive(n / 2, count + 1);
        }
        _8_1_recursive(n - 1, count + 1);
    }

    static void _8_1_bottom_up() {

        d = new int[30001];

        d[1] = 1;
        //역으로 더하고 올리면서 최소값을 저장

        for (int i = 2; i < n; i++) {

            d[i] = d[i - 1] + 1; //기본적으로 전단계의 값에 하나를 더한값을 최소값으로 지정 예) 7이면 전단계가 6이고 6은 2으로 나눌경우
            // 3이 나오고 d[3] 까지 나온 최소값에 한번더 연산한다는 의미에서 1을 더해 기록

            if (i % 2 == 0) {
                d[i] = Math.min(d[i], d[i / 2] + 1);
            }

            if (i % 3 == 0) {
                d[i] = Math.min(d[i], d[i / 3] + 1);
            }

            if (i % 5 == 0) {
                d[i] = Math.min(d[i], d[i / 5] + 1);
            }
        }

        System.out.println(d[n]);
    }

    static void _8_2() {

        n = sc.nextInt();
        d = new int[n];

        for (int i = 0; i < n; i++) {
            d[i] = sc.nextInt();
        }

        for (int i = 0; i < n; i++) {
            if (i + 2 < n) {
                d[i] += d[i + 2];
            }
        }

        for (int i = 0; i < n; i++) {
            _8_2_recursive(0 , i);
        }

        System.out.println();

    }

    static void _8_2_recursive(int sum, int idx) {

        if (idx + 1 > n) {
            min = Math.min(min, sum);
            return;
        }
        _8_2_recursive(sum + d[idx + 1], idx);
    }
}
