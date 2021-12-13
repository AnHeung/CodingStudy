package test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Chapter6Re {

    final static Scanner sc = new Scanner(System.in);
    static int n;
    static int m;
    static int[] arr;

    static void _6_1() {
        n = sc.nextInt();
        Integer[] arr = new Integer[n];

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        Arrays.sort(arr, Collections.reverseOrder());

        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    static void _6_1_other() {
        n = sc.nextInt();
        arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        for (int i = 0; i < n; i++) {
            int max = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[max] < arr[j]) max = j;
            }
            int temp = arr[i];
            arr[i] = arr[max];
            arr[max] = temp;
        }

        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    static void _6_2() {
        n = sc.nextInt();

        ArrayList<Student> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String name = sc.next();
            int score = sc.nextInt();
            list.add(new Student(name, score));
        }
        Collections.sort(list);

        for (int i = 0; i < n; i++) {
            System.out.print(list.get(i).name + " ");
        }
    }

    static void _6_3() {
        n = sc.nextInt();
        m = sc.nextInt();
        Integer[] arr1 = new Integer[n];
        Integer[] arr2 = new Integer[n];

        for(int i = 0 ; i < n; i++){
            arr1[i] =  sc.nextInt();
        }

        for(int i = 0 ; i < n; i++){
            arr2[i] =  sc.nextInt();
        }

        Arrays.sort(arr1);
        Arrays.sort(arr2,Collections.reverseOrder());


        for(int i = 0; i < m; i++){
            arr1[i] = arr2[i];
        }

        int sum = 0;
        for(int i = 0; i < arr1.length; i++){
            sum += arr1[i];
        }

        System.out.println(sum);
    }


    public static void main(String[] args) {
        _6_3();
    }

    static class Student implements Comparable<Student> {
        String name;
        int score;

        public Student(String name, int score) {
            this.name = name;
            this.score = score;
        }

        @Override
        public int compareTo(Student o) {
            return this.score - o.score;
        }
    }
}

