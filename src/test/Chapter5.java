package test;

import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

public class Chapter5 {

    public static void main(String[] args) {

    }


    class Node {

        int data;
        LinkedList<Node> adj;
        boolean marked;

        public Node(int data) {
            this.data = data;
            adj = new LinkedList<>();
            marked = false;
        }
    }


    void _5_1() {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] arr = new int[n][m];

        Stack<Node> stack = new Stack<>();


        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr[i][j]=sc.nextInt();
            }
        }
    }
}
