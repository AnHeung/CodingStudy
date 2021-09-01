package test;

import java.util.Arrays;
import java.util.Scanner;

public class Chapter8 {

    public static void main(String[] args) {
//        _8_1();
//        _8_1_sol();
//        _8_2_sol();
        _8_4_sol();
    }


    static void _8_1() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        System.out.println(_8_1_cal(n, 0));
    }

    static int _8_1_cal(int n, int count) {
        if (n == 1) return count;
        if (n % 5 == 0) return _8_1_cal(n / 5, ++count);
        else if (n - 1 > 0 && (n - 1) % 5 == 0) return _8_1_cal((n - 1) / 5, count + 2);
        else if (n % 3 == 0) return _8_1_cal(n / 3, ++count);
        else if (n - 1 > 0 && (n - 1) % 3 == 0) return _8_1_cal((n - 1) / 3, count + 2);
        else if (n % 2 == 0) return _8_1_cal(n / 2, ++count);
        else if (n - 1 > 0 && (n - 1) % 2 == 0) return _8_1_cal((n - 1) / 2, count + 2);
        else return _8_1_cal(n - 1, ++count);
    }

    static void _8_1_sol() {
        // 앞서 계산된 결과를 저장하기 위한 DP 테이블 초기화
        int[] d = new int[30001];

        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();

        // 다이나믹 프로그래밍(Dynamic Programming) 진행(보텀업)  //6을 예로
        for (int i = 2; i <= x; i++) {
            // 현재의 수에서 1을 빼는 경우
            d[i] = d[i - 1] + 1;      //5를 구할때 경우 +1<< +1은 연산횟수 하나 더하기 위해
            // 현재의 수가 2로 나누어 떨어지는 경우
            if (i % 2 == 0)    // 3을 구햇을때 횟수에 1회 더 한거와 위에서 구한 일을 하나 뺸 경우중 작은거 비교
                d[i] = Math.min(d[i], d[i / 2] + 1);
            // 현재의 수가 3으로 나누어 떨어지는 경우
            if (i % 3 == 0)   // 2를 구했을때 횟수에 1회 더 한거와 위에서 한 연산중 작은거
                d[i] = Math.min(d[i], d[i / 3] + 1);
            // 현재의 수가 5로 나누어 떨어지는 경우
            if (i % 5 == 0)  //5로는 안나눠 떨어져서 해당없음
                d[i] = Math.min(d[i], d[i / 5] + 1);
        }

        System.out.println(d[x]);
    }


    //오답 -> 2씩증가해서 비교하는식으로 오해했음
    // 예를 들어 2,3,1,5 라면 2,1<->3,5 이런식의 비교가 아니라 2,5 <-> 3,5 이런식으로도 비교가 가능하기 때문에 하나씩 올려가면서
    // 전에 단계에 있는값보다 새로 적용한부분이 더 크면 새로 적용한부분으로 값을 씌우고 아니면 전에단계로 값을 씌우는 형태로 해야됨.
    static void _8_2() {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] arr = new int[n];
        sc.nextLine();

        for (int i = 0; i < arr.length; i++) {
            arr[i] = sc.nextInt();
        }

        int[] sum = new int[100];

        for (int i = 0; i < n; i++) {
            if (i == 0) sum[i] = arr[i];
            if (i == 1) sum[i] = Math.max(arr[0], arr[1]);
            //i-1 의 경우는 현재 식량창고는 못털고 i-2 의 경우는 현재 식량창고를 털수 있다.
            sum[i] = Math.max(sum[i - 1], sum[i - 2] + arr[i]);
        }
        System.out.println(sum[n - 1]);
    }

    static void _8_2_sol() {
        Scanner sc = new Scanner(System.in);

        // 정수 N을 입력받기
        int n = sc.nextInt();
        // 앞서 계산된 결과를 저장하기 위한 DP 테이블 초기화
        int[] d = new int[100];

        // 모든 식량 정보 입력받기
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        // 다이나믹 프로그래밍(Dynamic Programming) 진행(보텀업)
        d[0] = arr[0];
        d[1] = Math.max(arr[0], arr[1]);
        for (int i = 2; i < n; i++) {
            //d[i-1] <<여기를 털면 현재 창고를 털수없음 arr[i]가 현재 창고
            d[i] = Math.max(d[i - 1], d[i - 2] + arr[i]);
        }

        // 계산된 결과 출력
        System.out.println(d[n - 1]);
    }

    static void _8_3() {

        Scanner sc = new Scanner(System.in);

        // 앞서 계산된 결과를 저장하기 위한 DP 테이블 초기화
        int[] d = new int[1001];

        // 정수 N을 입력받기
        int n = sc.nextInt();

        // 다이나믹 프로그래밍(Dynamic Programming) 진행(보텀업)
        d[1] = 1; //2*1 하나
        d[2] = 3; // 앞에 d[1]의 방법에 (1*2 ,1*2) , (2*2) 두개를 더해서 3개 즉 i-1 + i-2+i-2 => i-1 +2(i-1)
        //d[3] 3개 부터는 모든형태의 모양이 중복됨.
        for (int i = 3; i <= n; i++) {
            d[i] = (d[i - 1] + 2 * d[i - 2]) % 796796;
        }

        // 계산된 결과 출력
        System.out.println(d[n]);
    }

    static void _8_4(){

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int [] arr = new int[n];

        for(int i = 0; i < n; i++){
            arr[i] = sc.nextInt();
        }

        //3개 목표 15  값 2,3,4

        int [] sum = new int[m+1];
        Arrays.fill(sum ,10001);
        sum[0] = 0;

        for(int i = 0 ; i < n; i++){
            for(int j = arr[i]; j <= m; j++){ //2,3,4 라고 예를 들면 2부터 시작해서 10001 이 아닐경우 자신의 횟수값을 기록한후 더 작은쪽으로 덮어씌우게된다.
                if(sum[j - arr[i]] != -10001){
                    sum[j] = Math.min(sum[j] , sum[j - arr[i]]+1);
                }
            }
        }
        System.out.println(sum[m]);

    }

    static  void _8_4_sol(){
        Scanner sc = new Scanner(System.in);

        // 정수 N, M을 입력받기
        int n = sc.nextInt();
        int m = sc.nextInt();

        // N개의 화폐 단위 정보를 입력 받기
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        // 앞서 계산된 결과를 저장하기 위한 DP 테이블 초기화
        int[] d = new int[m + 1];
        Arrays.fill(d, 10001);

        // 다이나믹 프로그래밍(Dynamic Programming) 진행(보텀업)
        //최초에 들어오는값이 예를들면 arr[i] = 5라면 5-5 = 0으로 해서 시작시 10001로 초기화 되있는값을 시작이 가능하게 해줌.
        d[0] = 0;
        for (int i = 0; i < n; i++) {
            for (int j = arr[i]; j <= m; j++) {
                // (i - k)원을 만드는 방법이 존재하는 경우
                if (d[j - arr[i]] != 10001) {
                    d[j] = Math.min(d[j], d[j - arr[i]] + 1);
                }
            }
        }

        // 계산된 결과 출력
        if (d[m] == 10001) { // 최종적으로 M원을 만드는 방법이 없는 경우
            System.out.println(-1);
        }
        else {
            System.out.println(d[m]);
        }
    }
}

