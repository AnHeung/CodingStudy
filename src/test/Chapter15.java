package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Chapter15 {

    public static int n, m, result;
    public static int[] arr;

    public static void main(String[] args) {
        _15_3();
    }

    static void _15_1() {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        int cnt = countByRange(arr, m, m);
        System.out.println(cnt);
    }

    public static int lowerBound(int[] arr, int target, int start, int end) {
        while (start < end) {
            int mid = (start + end) / 2;
            if (arr[mid] >= target) end = mid;
            else start = mid + 1;
        }
        return end;
    }

    public static int upperBound(int[] arr, int target, int start, int end) {
        while (start < end) {
            int mid = (start + end) / 2;
            if (arr[mid] > target) end = mid;
            else start = mid + 1;
        }
        return end;
    }

    // 값이 [left_value, right_value]인 데이터의 개수를 반환하는 함수
    public static int countByRange(int[] arr, int leftValue, int rightValue) {
        // 유의: lowerBound와 upperBound는 end 변수의 값을 배열의 길이로 설정
        int rightIndex = upperBound(arr, rightValue, 0, arr.length);
        int leftIndex = lowerBound(arr, leftValue, 0, arr.length);
        return rightIndex - leftIndex;
    }

    static void _15_2() {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        int idx = _15_2_binary(0, n - 1);
        System.out.println(idx);
    }

    static int _15_2_binary(int start, int end) {

        if (start > end) return -1;

        int mid = (start + end) / 2;

        if (arr[mid] == mid) return mid;

        else if (arr[mid] > mid) return _15_2_binary(start, mid - 1);

        else return _15_2_binary(mid + 1, end);
    }

    static void _15_3() {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int x = sc.nextInt();
            list.add(x);
        }

        Collections.sort(list);

        int start = 1;
        int end = list.get(n-1) - list.get(0);
        int result = 0;

        while(start <= end){

            int mid = (start + end) /2;

            int value = list.get(0);

            int cnt = 1;

            for(int i  =1 ; i < n; i++){
                if(list.get(i) >= value + mid){
                    value = list.get(i);
                    cnt++;
                }
            }
            if(cnt >=m){
                start = mid +1;
                result = mid;
            }else{
                end = mid -1;
            }
        }
        System.out.println(result);
    }
}
