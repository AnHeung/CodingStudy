package test;

import java.util.Arrays;
import java.util.Collections;
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
        _8_1_bottom_up();
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

        n = sc.nextInt();
        d = new int[30001];

        for (int i = 2; i <= n; i++) {

            d[i] = d[i - 1] + 1; //X에서 1을 뺀다라고 했으니 전단계값이 되고  거기에  한번더 연산했다는 뜻으로 +1을 최소값으로 지정 예) 7이면 전단계가 6이고 6은 2으로 나눌경우
            // 3이 나오고 d[3] 까지 나온 최소값에 한번더 연산한다는 의미에서 1을 더해 기록

            if (i % 2 == 0) {  //i가 10일 경우 9까지 구하는 경우의수 3*3 ->2 에다가 1을 더한 d[10]=3을 기록하고 있지만 여기서 d[5]의값에 2를 곱한 행위수를 더해 1+1=2 로 변경.
                d[i] = Math.min(d[i], d[i / 2] + 1);
            }

            if (i % 3 == 0) { // i 가 6일 경우 d[6]이 2를 3번해서 구하는경우 3이 기록되어있지만 2를 구하는방법에(d[2]) 3을 한번더 곱해서 6을 구하면 경우가 2가 되므로 2로 변경.
                d[i] = Math.min(d[i], d[i / 3] + 1);
            }

            if (i % 5 == 0) { //i 가 5일경우 d[4]의 경우 2*2로 구한경우 2에다가 +1 을 더해서 5를 구한 d[5]가 기록되이 있는데 여기서 i가 5의 배수라 5/5 를 해주면 한번이면 되므로 1로 변경.
                d[i] = Math.min(d[i], d[i / 5] + 1);
            }
        }

        System.out.println(d[n]);
    }

    /*
    점화식 세우는데 어려움을 겪었다.
    결국 뒤부터 보면서 전단계 아이템과  전전단계 아이템에 현창고 아이템의 합의 최대치 비교를 했어야했는데
    예를 들어 1 , 2 ,5 , 10 ,2 , 999 뭐 이런식으로 있을때 맨뒤에 999같은 값이 있는걸 의식해서
    인덱스를 하나씩 늘려서 비교를 해야하나 뭐 이런식으로 접근했다가 결국 점화식을 도출하지 못했다.
     */
    static void _8_2() {

        n = sc.nextInt();
        d = new int[100];
        arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        d[0] = arr[0];
        d[1] = Math.max(arr[0], arr[1]);

        for (int i = 2; i < n; i++) {
            d[i] = Math.max(d[i - 1], d[i - 2] + arr[i]);
        }

        System.out.println(d[n - 1]);
    }

    static void _8_3() {

        n = sc.nextInt();

        d = new int[1001];

        d[1] = 1;
        d[2] = 3;

        for (int i = 3; i < n + 1; i++) {
            d[i] = 2 * d[i - 2] + d[i - 1];
        }
        System.out.println(d[n]);
    }


    static void _8_4() {
        n = sc.nextInt(); //화폐 갯수
        m = sc.nextInt(); //화폐 기준값

        arr = new int[n];
        d = new int[10001];

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        Arrays.fill(d, 10001);

        d[0] = 0;

        for (int i = 0; i < n; i++) {
            int mul = arr[i];
            for (int j = mul; j <= m; j++) {
                if (d[j - mul] != 10001) {
                    d[j] = Math.min(d[j], d[j - mul] + 1);
                }
            }
        }
        System.out.println(d[m] == 10001 ? -1 : d[m]);
    }
}
