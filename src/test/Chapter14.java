package test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Chapter14 {

    public static int n, m, result;

    public static void main(String[] args) {
        _14_4();
    }

    static void _14_1() {

        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        ArrayList<Student> students = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String name = sc.next();
            int korean = sc.nextInt();
            int english = sc.nextInt();
            int math = sc.nextInt();
            students.add(new Student(name, korean, english, math));
        }

        Collections.sort(students);
        System.out.println();
    }

    static void _14_2() {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        int[] arr = new int[100];

        for (int i = 0; i < n; i++) {
            int x = sc.nextInt();
            arr[x] = 1;
        }

        result = (int) 1e9;

        for (int i = 0; i < arr.length; i++) {

            int sum = 0;

            for (int j = 0; j < arr.length; j++) {
                if (arr[j] == 1) {
                    int data = Math.abs(i - j);
                    sum += data;
                }
            }
            result = Math.min(result, sum);
        }

        System.out.println(result);
    }

    static void _14_3() {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        int[] arr = new int[10];

        for (int i = 0; i < m; i++) {
            int x = sc.nextInt();
            arr[x]++;
        }

        ArrayList<Stage> list = new ArrayList<>();
        int total = m;

        for (int i = 1; i <= n; i++) {
            if (total == 0) {
                list.add(new Stage(i, arr[i], total));
            } else {
                list.add(new Stage(i, arr[i], total));
                total -= arr[i];
            }
        }
        Collections.sort(list);
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i).stage + " ");
        }
    }

    static void _14_4() {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            pq.add(sc.nextInt());
        }

        while(pq.size() != 1){
            int one = pq.poll();
            int two = pq.poll();

            int summary = one + two;
            result += summary;
            pq.offer(summary);
        }
        System.out.println(result);
    }

    static void _solution(int[] arr) {


    }
}

class Stage implements Comparable<Stage> {

    int stage;
    int count;
    int total;

    public Stage(int stage, int count, int total) {
        this.stage = stage;
        this.count = count;
        this.total = total;
    }

    @Override
    public int compareTo(Stage o) {

        if ((double) this.count / (double) this.total > (double) o.count / (double) o.total) return -1;
        return 1;
    }
}


class Student implements Comparable<Student> {
    String name;
    int korean;
    int english;
    int math;

    public Student(String name, int korean, int english, int math) {
        this.name = name;
        this.korean = korean;
        this.english = english;
        this.math = math;
    }

    @Override
    public int compareTo(Student other) {
        if (this.korean == other.korean && this.english == other.english && this.math == other.math) {
            return this.name.compareTo(other.name);
        }
        if (this.korean == other.korean && this.english == other.english) {
            return Integer.compare(other.math, this.math);
        }
        if (this.korean == other.korean) {
            return Integer.compare(this.english, other.english);
        }
        return Integer.compare(other.korean, this.korean);
    }
}
