package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Chapter11 {

    public static void main(String[] args) {
        _11_6();
    }

    static void _11_1() {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[] fear = new int[n];
        sc.nextLine();

        for (int i = 0; i < n; i++) {
            fear[i] = sc.nextInt();
        }

        Arrays.sort(fear);

        int groupCount = 0;
        int index = 0;

        for (int i = 0; i < n; i++) {
            int fearNo = fear[i];
            int count = 0;
            for (int j = index; j < n; j++) {
                if (fearNo == count) break;
                if (fearNo == fear[j] || fearNo > fear[j]) count++;
            }
            if (count >= fearNo) {
                index += count;
                groupCount++;
            }
            if (count + index > n) break;
        }
        System.out.println(groupCount);
    }

    static void _11_2() {

        Scanner sc = new Scanner(System.in);

        String lines = sc.nextLine();

        Integer[] arr = new Integer[lines.length()];

        for (int i = 0; i < lines.length(); i++) {
            arr[i] = lines.charAt(i) - '0';
        }

        Arrays.sort(arr, Collections.reverseOrder());

        int count = 0;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 1) count += 1;
            else if (arr[i] != 0) {
                if (count == 0) count += arr[i];
                else count *= arr[i];
            }
        }
        System.out.println(count);
    }

    static void _11_3() {

        Scanner sc = new Scanner(System.in);

        String lines = sc.nextLine();
        int zeroCount = 0;
        int oneCount = 0;

        for (int i = 0; i < lines.length(); i++) {
            int no = lines.charAt(i) - '0';
            if (no == 0) zeroCount++;
            if (no == 1) oneCount++;
        }

        System.out.println(zeroCount == 0 || oneCount == 0 ? 0 : 1);
    }

    static void _11_4() {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        ArrayList<Integer> arrayList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            arrayList.add(sc.nextInt());
        }

        Collections.sort(arrayList);

        int target = 1;
        for (int i = 0; i < n; i++) {
            // 만들 수 없는 금액을 찾았을 때 반복 종료
            if (target < arrayList.get(i)) break;
            target += arrayList.get(i);
        }

        System.out.println(target);
    }

    static void _11_5() {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] weight = new int[n + 1];
        int[][] arr = new int[n+1][n+1];

        sc.nextLine();

        for (int i = 1; i <= n; i++) {
            weight[i] = sc.nextInt();
        }

        int count = 0;

        for (int i = 1; i <= n; i++) {
            int a = weight[i];
            for (int j = 1; j <= n; j++) {
                int b = weight[j];
                if (a != b && i != j && arr[i][j] == 0 && arr[j][i] == 0) {
                    arr[i][j]++;
                    arr[j][i]++;
                    count++;
                }
            }
        }
        System.out.println(count);
    }

    static void _11_6(){

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int [] arr =  new int[n+1];
        sc.nextLine();

        for(int i =1 ; i <= n; i++){
            arr[i] = sc.nextInt();
        }

        sc.nextLine();

        int k = sc.nextInt();

        int count = 0;
        int value = 1;

        while(count != k){

            if(value-1 == n) value = 0;

            if(arr[value] -1 < 0){
                value++;
                continue;
            }

            System.out.println((count)+ " ~ " + (count+1) + "초 동안 "+ value + "번 음식을 섭취 남은시간은 : " + --arr[value]);
            value++;
            count++;
        }

        value =  (value == n? 1 : value);

       System.out.println(k + "초에서 네트워크 장애 발생 . " + value+ "번 음식을 섭취할때 중단됨 " + value +"번 음식부터 식사시작");
    }
}
