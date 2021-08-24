package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Chapter4 {

    public static void main(String[] args) {
//        _4_1();
        _4_2();
    }

    static void _4_1() {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[] arr = new int[]{1, 1};

        boolean result = true;

        while (result) {
            String val = sc.next();
            result = _4_1_checkBoundary(val, arr, n);
        }
        System.out.println(arr[0] + " " + arr[1]);
    }


    static boolean _4_1_checkBoundary(String val, int[] arr, int length) {
        if ("R".equals(val)) {
            if (arr[1] + 1 <= length) arr[1]++;
        } else if ("L".equals(val)) {
            if (arr[1] - 1 >= 1) arr[1]--;
        } else if ("U".equals(val)) {
            if (arr[0] - 1 >= 1) arr[0]--;
        } else if ("D".equals(val)) {
            if (arr[0] + 1 <= length) arr[0]++;
        } else if ("E".equals(val)) {
            return false;
        }
        return true;
    }

    static void _4_1_sol() {
        Scanner sc = new Scanner(System.in);

        // N을 입력받기
        int n = sc.nextInt();
        sc.nextLine(); // 버퍼 비우기
        String[] plans = sc.nextLine().split(" ");
        int x = 1, y = 1;

        // L, R, U, D에 따른 이동 방향
        int[] dx = {0, 0, -1, 1};
        int[] dy = {-1, 1, 0, 0};
        char[] moveTypes = {'L', 'R', 'U', 'D'};

        // 이동 계획을 하나씩 확인
        for (int i = 0; i < plans.length; i++) {
            char plan = plans[i].charAt(0);
            // 이동 후 좌표 구하기
            int nx = -1, ny = -1;
            for (int j = 0; j < 4; j++) {
                if (plan == moveTypes[j]) {
                    nx = x + dx[j];
                    ny = y + dy[j];
                }
            }
            // 공간을 벗어나는 경우 무시
            if (nx < 1 || ny < 1 || nx > n || ny > n) continue;
            // 이동 수행
            x = nx;
            y = ny;
        }

        System.out.println(x + " " + y);
    }

    static void _4_2() {

        int second = 60;
        int minute = 60;
        int count = 0;

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        for (int h = 0; h <= n; h++) {
            for (int m = 0; m < minute; m++) {
                for (int s = 0; s < second; s++) {
                    if (_4_2_checkNum(h) || _4_2_checkNum(m) || _4_2_checkNum(s)) {
                        count++;
                    }
                }
            }
        }
        System.out.println(count);
    }


    static boolean _4_2_checkNum(int num) {
        int[] arr = new int[]{3, 13, 23, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 43, 53};
        return Arrays.stream(arr).anyMatch(i -> i == num);
    }

    static void _4_2_sol() {
        Scanner sc = new Scanner(System.in);

        // H를 입력받기
        int h = sc.nextInt();
        int cnt = 0;

        for (int i = 0; i <= h; i++) {
            for (int j = 0; j < 60; j++) {
                for (int k = 0; k < 60; k++) {
                    // 매 시각 안에 '3'이 포함되어 있다면 카운트 증가
                    if (_sol_4_2_check(i, j, k)) cnt++;
                }
            }
        }

        System.out.println(cnt);
    }

    // 특정한 시각 안에 '3'이 포함되어 있는지의 여부
    public static boolean _sol_4_2_check(int h, int m, int s) {
        // 10보다 큰거 10보다 작은거 각각 하나라도 포함되는경우
        if (h % 10 == 3 || m / 10 == 3 || m % 10 == 3 || s / 10 == 3 || s % 10 == 3)
            return true;
        return false;
    }

}
