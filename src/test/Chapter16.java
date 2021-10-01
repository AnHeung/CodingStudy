package test;

import java.util.ArrayList;
import java.util.Scanner;

public class Chapter16 {

    public static int n, m, result;
    public static int[] arr;
    public static Scanner sc = new Scanner(System.in);
    public static int[][] map;
    static int[] dx = {-1, 0, 1};
    static int[] dy = {1, 1, 1};

    public static void main(String[] args) {
        _16_1();
    }

    static void _16_1() {

/*
2
3 4
1 3 3 2 2 1 4 1 0 6 4 7
4 4
1 3 1 5 2 2 4 1 5 0 2 3 0 6 1 2
*/
        int t = sc.nextInt();
        ArrayList<Integer> resultList = new ArrayList<>();

        for (int i = 0; i < t; i++) {

            result = 0;

            n = sc.nextInt();
            m = sc.nextInt();

            map = new int[n][m];

            for (int k = 0; k < n; k++) {
                for (int j = 0; j < m; j++) {
                    map[k][j] = sc.nextInt();
                }
            }

            for (int k = 0; k < n; k++) {
                _16_1_sol(k, 0, map[k][0]);
            }
            resultList.add(result);
        }

        for (int i = 0; i < resultList.size(); i++) {
            System.out.println(resultList.get(i));
        }
    }

    static void _16_1_sol(int x, int y, int sum) {

        for (int i = 0; i < 3; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
                _16_1_sol(nx, ny, sum + map[nx][ny]);
            } else {
                result = Math.max(result, sum);
                return;
            }
        }
    }

    static void _16_2() {
        n = sc.nextInt();
        map = new int[n][n];


        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = -1;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        _16_2_sol(0, 0, map[0][0]);

        System.out.println(result);
    }

    static void _16_2_sol(int x, int y, int sum) {

        int[] dx = {1, 1};
        int[] dy = {0, 1};

        for (int i = 0; i < 2; i++) {

            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && nx < n && ny >= 0 && ny < n && map[nx][ny] != -1) {
                sum += map[nx][ny];
                _16_2_sol(nx, ny, sum);
                sum -= map[nx][ny];
            } else {
                result = Math.max(result, sum);
                return;
            }
        }
    }

    //오답
    //재귀함수로 풀려다가 자꾸 식이 꼬여서 못풀었음 ㅠ
    static void _16_3() {
        n = sc.nextInt();
        result = 0;
        int[] t = new int[n];
        int[] c = new int[n];
        int[] dp = new int[16];

        for (int i = 0; i < n; i++) {
            t[i] = sc.nextInt();
            c[i] = sc.nextInt();
        }

        _16_3_sol(t, c, dp);
        _16_3_dfs(0, 0, t, c);
        System.out.println(result);

    }

    //맞게 풀이된 dfs
    static void _16_3_dfs(int x, int sum, int[] time, int[] cost) {
        if (x >= n) {
            result = Math.max(result, sum);
            return;
        }
        if (x + time[x] <= n) _16_3_dfs(x + time[x], sum + cost[x], time, cost); //값을 넣어서 다시 찾기
        else _16_3_dfs(x + time[x], sum, time, cost); //값이 더 나올게 없으니 return 유도
        _16_3_dfs(x + 1, sum, time, cost); //값을 하나씩 올려서 찾기
    }

    static void _16_3_sol(int[] t, int[] c, int[] dp) {
        for (int i = n - 1; i >= 0; i--) {
            int time = t[i] + i;
            // 상담이 기간 안에 끝나는 경우
            if (time <= n) {
                // 점화식에 맞게, 현재까지의 최고 이익 계산
                dp[i] = Math.max(c[i] + dp[time], result);
                result = dp[i];
            }
            // 상담이 기간을 벗어나는 경우
            else dp[i] = result;
        }
    }

    //오답
    //재귀함수로 풀려다가 자꾸 식이 꼬여서 못풀었음 ㅠ
    static void _16_3_sol(int x, int[] time, int[] cost, int sum) {

        if (x >= 0 && x <= n && x + time[x] < n) {
            sum += cost[x];
            x += time[x];
            for (int i = x; i < n; i++) {
                _16_3_sol(x, time, cost, sum);
            }
            x -= time[x];
            sum -= cost[x];
        } else {
            result = Math.max(result, sum);
            return;
        }
    }

    static void _16_4() {

        n = sc.nextInt();
        arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        System.out.println();
        int count = 0;
        int str = arr[0];

        for (int i = 0; i < arr.length; i++) {
            if (str < arr[i]) {
                _16_4_swap(i - 1, i);
                _16_4_remove(i);
                count++;
            } else {
                str = arr[i];
            }
        }
        System.out.println(count);
    }

    static void _16_4_swap(int x, int y) {
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }

    static void _16_4_remove(int x) {
        for (int i = x; i < arr.length; i++) {
            if (i + 1 < arr.length) {
                int temp = arr[i];
                arr[i] = arr[i + 1];
                arr[i + 1] = temp;
            }
        }
        int[] newArr = new int[arr.length - 1];

        for (int i = 0; i < newArr.length; i++) {
            newArr[i] = arr[i];
        }
        arr = newArr;
    }

    //오답
    static void _16_5() {
        n = sc.nextInt();
        int count = 0;
        for (int i = 1; i < 1000; i++) {
            if (i == 1 || i % 2 == 0 || i % 3 == 0 || i % 5 == 0) {
                count++;
            }
            if (n == count) {
                result = i;
                break;
            }
        }
        System.out.println(result);
    }


    static void _16_5_sol(){
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        int i2 = 0  , i3 = 0 , i5 = 0;
        int next2 = 2 , next3 = 3 , next5 = 5;

        arr = new int[1000];
        arr[0] = 1;

        for (int i = 1; i < n; i++) {
            arr[i] = Math.min(next2, Math.min(next3, next5));
            // 인덱스에 따라서 곱셈 결과를 증가
            if (arr[i] == next2) {
                i2 += 1;
                next2 = arr[i2] * 2;
            }
            if (arr[i] == next3) {
                i3 += 1;
                next3 = arr[i3] * 3;
            }
            if (arr[i] == next5) {
                i5 += 1;
                next5 = arr[i5] * 5;
            }
        }
        System.out.println(arr[n-1]);

    }

    static void _16_6() {

        String str1 = sc.next();
        String str2 = sc.next();
        ArrayList<Integer>indexList = new ArrayList<>();

        arr =  new int[128];

        System.out.println();

        for (int i = 0; i < str1.length(); i++) {
            for (int j = 0; j < str2.length(); j++) {
                if(str1.charAt(i) == str2.charAt(j)){
                    indexList.add(j);
                }
            }
        }

        System.out.println(editDist(str1, str2));
    }

    static int editDist(String str1, String str2) {
        int n = str1.length();
        int m = str2.length();

        // 다이나믹 프로그래밍을 위한 2차원 DP 테이블 초기화
        int[][] dp = new int[n + 1][m + 1];

        // DP 테이블 초기 설정
        for (int i = 1; i <= n; i++) {
            dp[i][0] = i;
        }
        for (int j = 1; j <= m; j++) {
            dp[0][j] = j;
        }

        // 최소 편집 거리 계산
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                // 문자가 같다면, 왼쪽 위에 해당하는 수를 그대로 대입
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                }
                // 문자가 다르다면, 세 가지 경우 중에서 최솟값 찾기
                else { // 삽입(왼쪽), 삭제(위쪽), 교체(왼쪽 위) 중에서 최소 비용을 찾아 대입
                    dp[i][j] = 1 + Math.min(dp[i][j - 1], Math.min(dp[i - 1][j], dp[i - 1][j - 1]));
                }
            }
        }
        return dp[n][m];
    }
}

