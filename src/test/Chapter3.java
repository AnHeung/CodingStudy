package test;

import java.util.Arrays;
import java.util.Scanner;

public class Chapter3 {


    public static void main(String[] args) {

        int coin = 1260;
        int[] arr = {500, 100, 50, 10};
        int count = 0;


        for (int cnt : arr) {
            count += coin / cnt;
            coin %= cnt;
        }
//        System.out.println("count " + count);

//        bigNum();
        makeNumOne();
//        NumCardGame();

    }

    static void bigNumSol(){
        Scanner sc = new Scanner(System.in);

        // N, M, K를 공백을 기준으로 구분하여 입력 받기
        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();

        // N개의 수를 공백을 기준으로 구분하여 입력 받기
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        Arrays.sort(arr); // 입력 받은 수들 정렬하기
        int first = arr[n - 1]; // 가장 큰 수
        int second = arr[n - 2]; // 두 번째로 큰 수

        // 가장 큰 수가 더해지는 횟수 계산
        int cnt = (m / (k + 1)) * k;
        cnt += m % (k + 1);

        int result = 0;
        result += cnt * first; // 가장 큰 수 더하기
        result += (m - cnt) * second; // 두 번째로 큰 수 더하기

        System.out.println(result);
    }

    static void bigNum() {

        Scanner scan = new Scanner(System.in);


        int n = scan.nextInt();
        int m = scan.nextInt();
        int k = scan.nextInt();

        int[] arr = new int[n];

        for(int i = 0 ; i < arr.length; i++){
            arr[i] = scan.nextInt();
        }

        int topNum = 0;
        int secondNum = 0;


        for (int i = 0; i < arr.length; i++) {
            if (topNum < arr[i]) {
                if(i == 0) topNum = arr[i];
                secondNum = topNum;
                topNum = arr[i];
            }
        }

        int total = 0;
        int cnt = 0;

        for (int i = 0; i < m; i++) {
            if(secondNum != topNum && cnt == k){
                total+=secondNum;
                cnt = 0 ;
                System.out.println(secondNum);
            }else{
                System.out.println(topNum);
                total += topNum;
                cnt++;
            }
        }

        System.out.println(total);
    }

    static void NumCardGame(){

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        int rowMin = 0;

        for(int i = 0 ; i < n; i++){
            int min = 0;
            for (int j = 0 ; j < m; j++){
                int col = sc.nextInt();
                if(min > col || j == 0) min = col;
            }
            if(rowMin < min) rowMin = min;
        }
        System.out.println(rowMin);
    }

    static void makeNumOne(){

       Scanner sc = new Scanner(System.in);

       int n = sc.nextInt();
       int k = sc.nextInt();
       int count = 0;

        while(n != 1){
            count++;
            if(n % k == 0) {
                n /= k;
            }else{
                n -= 1;
            }
        }
        System.out.println(count);
    }
}
