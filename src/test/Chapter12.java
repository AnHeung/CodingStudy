package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Chapter12 {

    public static void main(String[] args) {
        _12_2();
    }

    static void _12_1() {

        Scanner sc = new Scanner(System.in);

        String lines = sc.nextLine();

        int[] arr = new int[lines.length()];
        int left = lines.length() / 2 + lines.length() % 2 - 1;
        int right = lines.length() - 1;

        for (int i = 0; i < lines.length(); i++) {
            arr[i] = lines.charAt(i) - '0';
        }

        int leftValue = 0;
        int rightValue = 0;

        for (int i = 0; i < lines.length(); i++) {
            if (i <= left) leftValue += arr[i];
            else if (i <= right) rightValue += arr[i];
        }
        System.out.println(leftValue + " " + rightValue);
        System.out.println(leftValue == rightValue ? "LUCKY" : "READY");
    }

    static void _12_2() {

        Scanner sc = new Scanner(System.in);
        String lines = sc.nextLine();
        int[] arr = new int[128];
//        ArrayList<String> strList = new ArrayList<>();
//        ArrayList<Integer> intList = new ArrayList<>();


        for (int i = 0; i < lines.length(); i++) {
            arr[i] = lines.charAt(i);
        }

        for(int i = 0 ; i< arr.length; i++){
            if(48 <= arr[i] && arr[i]<=57){

            }
        }

        System.out.println();
    }
}
