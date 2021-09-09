package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Chapter12 {

    public static void main(String[] args) {
        _12_3();
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
        StringBuilder sb = new StringBuilder();

        char[] test = lines.toCharArray();

        int result = 0;

        Arrays.sort(test);

        for (char c : test) {
            //숫자타입이면 값을 저장해두기
            if (48 <= c && c <= 57) {
                result += c - '0';
            } else {
                //일반 문자타입을 위에서 정렬했으므로 순서대로 나옴
                sb.append(c);
            }
        }

        sb.append(result);
        System.out.println(sb);
    }

    static void _12_3() {

        Scanner sc = new Scanner(System.in);
        String lines = sc.nextLine();

        char[] input = lines.toCharArray();
        int[] result = new int[128];


        int length = 1;

        while (length <= lines.length() / 2) {

            int count = 0;
            String prev = "";

            for (int i = 0; i < lines.length(); i += length + length) {

                int endStartIdx = i + length;
                int endIdx = i + (2 * length);
                if (endIdx > lines.length()) {
                    count +=(lines.length() - i+1);
                    break;
                }

                String front = lines.substring(i, endStartIdx);
                String end = lines.substring(endStartIdx, endIdx);

                if (prev.equals(front)) {
                    count++;
                } else {
                    if (front.equals(end)) {
                        count += length + 1;
                    } else {
                        count += 2 * length;
                    }
                }
                prev = end;

                System.out.println(count);
            }
            result[length] = count;
            length++;
        }
        System.out.println();
    }
}
