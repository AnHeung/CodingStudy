package test;

import java.io.*;
import java.util.*;

public class Chapter4 {

    public static void main(String[] args) {
//        _4_1();
//        _4_2();
//        _4_3();
        Chapter4 c4 = new Chapter4();
        c4._4_4();

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
        } else return !"E".equals(val);
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

    static void _4_3() {

        int n = 8;

        Scanner sc = new Scanner(System.in);

        String input = sc.next();
        int inputInt = Integer.parseInt(input.replaceAll("[^0-9]", ""));
        int inputStr = Character.getNumericValue(input.replaceAll("[0-9]", "").charAt(0)) - 9;

        int[][] boundaryArr = new int[][]{{1, 2}, {1, -2}, {-1, 2}, {-1, -2}, {2, 1}, {-2, 1}, {2, -1}, {-2, -1}};

        int[] arr = new int[]{inputInt, inputStr};

        System.out.println(arr[0] + " " + arr[1]);

        int count = 0;

        for (int[] boundary : boundaryArr) {
            int row = arr[0];
            int col = arr[1];

            int boundaryRow = boundary[0];
            int boundaryCol = boundary[1];

            if (row + boundaryRow <= n && col + boundaryCol <= n && row + boundaryRow > 0 && col + boundaryCol > 0) {
                count++;
            }
        }
        System.out.println(count);
    }

    void _4_3_sol() {

        Scanner sc = new Scanner(System.in);

        // 현재 나이트의 위치 입력받기
        String inputData = sc.nextLine();
        int row = inputData.charAt(1) - '0';
        int column = inputData.charAt(0) - 'a' + 1;

        // 나이트가 이동할 수 있는 8가지 방향 정의
        int[] dx = {-2, -1, 1, 2, 2, 1, -1, -2};
        int[] dy = {-1, -2, -2, -1, 1, 2, 2, 1};

        // 8가지 방향에 대하여 각 위치로 이동이 가능한지 확인
        int result = 0;
        for (int i = 0; i < 8; i++) {
            // 이동하고자 하는 위치 확인
            int nextRow = row + dx[i];
            int nextColumn = column + dy[i];
            // 해당 위치로 이동이 가능하다면 카운트 증가
            if (nextRow >= 1 && nextRow <= 8 && nextColumn >= 1 && nextColumn <= 8) {
                result += 1;
            }
        }

        System.out.println(result);
    }

    //1단계 북->서  서->남  남->동 동->북
    //2단계 방문했을시 방향 바꾸기 방문 안하면 한칸 이동
    //3단계 4방향 다 다녀왔거나 바다면 방향은 유지 뒤로 한칸이동후 1단계 다시

    void _4_4() {
        Scanner sc = new Scanner(System.in);

        // N, M을 공백을 기준으로 구분하여 입력받기
        int n = sc.nextInt();
        int m = sc.nextInt();
        int x = sc.nextInt();
        int y = sc.nextInt();
        int direction = sc.nextInt();

        int[][] arr = new int[n][m];
        int[][] visit = new int[n][m];

        // 북, 동, 남, 서 방향 정의
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, 1};

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr[i][j] = sc.nextInt();
            }
        }

        //방문처리
        visit[x][y] = 1;

        //1번 방문해서 카운트 추가
        int cnt = 1;
        int turnCnt = 0;

        while(true){
            //일단 왼쪽으로 회전
            direction = _4_4_TurnLeftDirection(direction);

            int xx = x + dx[direction];
            int yy = y + dy[direction];
            // 회전한 이후 정면에 방문 안했고 바다가 아니라면 이동
            if(arr[xx][yy] == 0 && visit[xx][yy] == 0){
                visit[xx][yy] =1 ;
                x = xx;
                y = yy;
                cnt++;
                turnCnt = 0;
                continue;
            }else{
                turnCnt++;
            }

            //다 돌고 나서 더 갈곳이 있나 확인부분
            if(turnCnt == 4){
                //뒤로 한칸 이동
                xx = x - dx[direction];
                yy = y - dy[direction];
                //만약 육지고 방문 x 경우
                if(arr[xx][yy] == 0){
                    x = xx;
                    y = yy;
                }else {
                    //탈출
                    break;
                }
                turnCnt = 0;
            }
            System.out.println(cnt);
        }

    }

    int _4_4_TurnLeftDirection(int direction) {
        direction -= 1;
        if (direction == -1) return 3;
        return direction;
    }

}
