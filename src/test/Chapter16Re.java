package test;

import java.util.*;

public class Chapter16Re {

    public static int n, m, l, result;
    public static int max = -(int) 1e9;
    public static int[] arr;
    public static Scanner sc = new Scanner(System.in);
    public static int[][] map;
    public static int[][] temp;
    public static int[] d, t;
    static int[] parent;
    static boolean[][] visited;
    public static int INF = (int) 1e9;
    static int[] dx = {-1, 0, 1};
    static int[] dy = {1, 1, 1};

    public static void main(String[] args) {
        _16_5_sol();
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
//                    _16_1_dfs(map[i][j], i, j);
                    _16_1_bfs(i, j);
                }
            }
            System.out.println(result);
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
                    result = Math.max(result, temp[nx][ny]);
                    q.offer(new MineInfo(nx, ny, temp[nx][ny]));
                }
            }
        }
    }

    static void _16_1_dfs(int sum, int x, int y) {


        for (int i = 0; i < 3; i++) {

            int nx = x + dx[i];
            int ny = y + dy[i];

            if (0 <= nx && nx < n && 0 <= ny && ny < m) {
                _16_1_dfs(sum + map[nx][ny], nx, ny);
            } else {
                result = Math.max(result, sum);
                return;
            }
        }
    }

    static void _16_1_dynamic() {
        // 테스트 케이스(Test Case) 입력
        int t = sc.nextInt();

        for (int tc = 0; tc < t; tc++) {
            // 금광 정보 입력
            n = sc.nextInt();
            m = sc.nextInt();
            map = new int[n][m];
            temp = new int[n][m];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    map[i][j] = sc.nextInt();
                }
            }
            // 다이나믹 프로그래밍을 위한 2차원 DP 테이블 초기화
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    temp[i][j] = map[i][j];
                }
            }
            // 다이나믹 프로그래밍 진행
            for (int j = 1; j < m; j++) {
                for (int i = 0; i < n; i++) {
                    int leftUp, leftDown, left;
                    // 왼쪽 위에서 오는 경우
                    if (i == 0) leftUp = 0;
                    else leftUp = temp[i - 1][j - 1];
                    // 왼쪽 아래에서 오는 경우
                    if (i == n - 1) leftDown = 0;
                    else leftDown = temp[i + 1][j - 1];
                    // 왼쪽에서 오는 경우
                    left = temp[i][j - 1];
                    temp[i][j] = temp[i][j] + Math.max(leftUp, Math.max(leftDown, left));
                }
            }
            int result = 0;
            for (int i = 0; i < n; i++) {
                result = Math.max(result, temp[i][m - 1]);
            }
            System.out.println(result);
        }
    }

    static void _16_2() {

        n = sc.nextInt();

        map = new int[n][n];
        temp = new int[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(map[i], -1);
            Arrays.fill(temp[i], -1);
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                map[i][j] = sc.nextInt();
                temp[i][j] = map[i][j];
            }
        }
//        _16_2_bfs();
//        _16_2_dfs(map[0][0] , 0 ,0);
        _16_2_dynamic();

        System.out.println(result);
    }

    static void _16_2_dfs(int sum, int x, int y) {

        int[] dx = {1, 1};
        int[] dy = {0, 1};

        for (int i = 0; i < 2; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (0 <= nx && nx < n && 0 <= ny && ny < n && map[nx][ny] != -1) {
                _16_2_dfs(sum + map[nx][ny], nx, ny);
            } else {
                result = Math.max(result, sum);
                return;
            }
        }
    }

    static void _16_2_dynamic() {

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] != -1) {
                    if (j > 0) map[i][j] = Math.max(map[i - 1][j] + map[i][j], map[i - 1][j - 1] + map[i][j]);
                    else map[i][j] = map[i - 1][j] + map[i][j];
                }
            }
        }
    }

    static void _16_2_bfs() {

        int x = 0, y = 0, sum = map[x][y];
        Queue<MineInfo> q = new LinkedList<>();
        q.offer(new MineInfo(x, y, sum));

        int[] dx = {1, 1};
        int[] dy = {0, 1};

        while (!q.isEmpty()) {

            MineInfo now = q.poll();
            x = now.x;
            y = now.y;
            sum = now.sum;

            for (int i = 0; i < 2; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (0 <= nx && nx < n && 0 <= ny && ny < n && map[nx][ny] != -1) {
                    temp[nx][ny] = Math.max(temp[nx][ny], sum + map[nx][ny]);
                    result = Math.max(result, temp[nx][ny]);
                    q.offer(new MineInfo(nx, ny, temp[nx][ny]));
                }
            }
        }
    }

    static void _16_3() {
        n = sc.nextInt();
        int[] t = new int[15];
        int[] p = new int[15];
        d = new int[16];

        for (int i = 0; i < n; i++) {
            t[i] = sc.nextInt();
            p[i] = sc.nextInt();
        }

        for (int i = n - 1; i >= 0; i--) {
            int time = t[i] + i;

            if (time <= n) {
                d[i] = Math.max(p[i] + d[time], result);
                result = d[i];
            } else {
                d[i] = result;
            }
        }

        System.out.println(result);
    }

    /*
    dfs 로 풀려다 식을 잘못짜서 못풀었음 ㅠ
    for 문으로 하나씩 값을 넣고(합계까지) 풀려햇으나
    재귀에서 탈출조건 설정을 잘못해서 실패했다. 밑에건 정답 풀이
     */
    static void _16_3_sol() {
        n = sc.nextInt();

        t = new int[n];
        d = new int[n];

        for (int i = 0; i < n; i++) {
            t[i] = sc.nextInt();
            d[i] = sc.nextInt();
        }

        _16_3_dfs(0, 0);
        System.out.println(result);
    }

    static void _16_3_dfs(int idx, int pay) {
        if (idx >= n) {
            result = Math.max(result, pay);
            return;
        }

        if (idx + t[idx] <= n) _16_3_dfs(idx + t[idx], pay + d[idx]);
        else _16_3_dfs(idx + t[idx], pay);
        _16_3_dfs(idx + 1, pay);
    }

    static void _16_4() {
        n = sc.nextInt();
        d = new int[1000];
        arr = new int[1000];

        for (int i = 1; i <= n; i++) {
            arr[i] = sc.nextInt();
        }

        d[1] = arr[1];

        int cnt = 0;

        for (int i = 1; i <= n; i++) {
            if (arr[i] < arr[i + 1]) {
                if (i + 2 <= n) {
                    i = i + 1;
                }
                cnt++;
            }
        }
        System.out.println(cnt);
    }

    static void _16_5() {

        n = sc.nextInt();

        d = new int[1001];

        int idx = 2;
        int currentValue = 1;

        d[1] = 1;
        arr =  new int[1001];
        arr[0] = 1;

        while(currentValue <= n){

            if(idx%2 == 0){
                if(d[idx/2] == 0){
                    idx++;
                    continue;
                }
                d[idx]++;
                arr[currentValue++] = idx;
            } else if(idx %3 == 0){
                if(d[idx/3] == 0){
                    idx++;
                    continue;
                }
                d[idx]++;
                arr[currentValue++] = idx;
            }

            else if(idx %5 == 0){
                if(d[idx/5] == 0){
                    idx++;
                    continue;
                }
                d[idx]++;
                arr[currentValue++] = idx;
            }
            idx++;
        }

        System.out.println(arr[n-1]);
    }

    static void _16_5_sol(){

        n = sc.nextInt();
        int i2 = 0, i3 = 0 , i5 = 0;
        int next2 = 2 , next3 = 3 , next5 = 5;

        d = new int[1001];
        d[0] = 1;

        for(int i = 1 ; i < n; i++){

            d[i] = Math.min(next2, Math.min(next3, next5));

            if(d[i] == next2){
                i2++;
                next2 = d[i2]*2;
            }

            if(d[i] == next3){
                i3++;
                next3 = d[i3]*3;
            }

            if(d[i] == next5){
                i5++;
                next5 = d[i5]*5;
            }
        }

        System.out.println(d[n-1]);
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
