package test;

import java.util.ArrayList;
import java.util.Scanner;

public class Chapter16 {

    public static int n, m, result;
    public static int[] arr;
    public static int[][] map;
    static int[] dx = {-1, 0, 1};
    static int[] dy = {1, 1, 1};

    public static void main(String[] args) {
        _16_3();
    }

    static void _16_1() {

/*
2
3 4
1 3 3 2 2 1 4 1 0 6 4 7
4 4
1 3 1 5 2 2 4 1 5 0 2 3 0 6 1 2
*/

        Scanner sc = new Scanner(System.in);
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
                sum += map[nx][ny];
                _16_1_sol(nx, ny, sum);
                sum -= map[nx][ny];
            } else {
                result = Math.max(result, sum);
                return;
            }
        }
    }

    static void _16_2() {
        Scanner sc = new Scanner(System.in);
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
        Scanner sc = new Scanner(System.in);
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
        _16_3_dfs(0 , 0 , t, c);
        System.out.println(result);

    }

    //맞게 풀이된 dfs
    static void _16_3_dfs(int x, int sum, int[]time , int[] cost) {
        if(x >= n){
            result = Math.max(result, sum);
            return;
        }
        if(x + time[x] <=n) _16_3_dfs(x+time[x], sum + cost[x], time, cost); //값을 넣어서 다시 찾기
        else _16_3_dfs(x+time[x] ,sum, time ,cost); //값이 더 나올게 없으니 return 유도
        _16_3_dfs(x+1,sum , time , cost); //값을 하나씩 올려서 찾기
    }

    static void _16_3_sol(int[] t, int[] c, int[] dp){
        for(int i = n-1; i >=0; i--){
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
}

