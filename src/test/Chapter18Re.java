package test;

import java.util.*;

public class Chapter18Re {

    public static int n, m, result;
    public static int[] arr;
    public static Scanner sc = new Scanner(System.in);
    public static int[][] map;
    public static int[][] temp;
    public static int[] d;
    static int[] parent;
    static boolean[][] visited;
    public static int INF = (int) 1e9;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) {
        _18_1();
    }

    static void _18_1() {

        n = sc.nextInt();
        m = sc.nextInt();
        parent = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }


        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                int x = sc.nextInt();
                if (x == 1) {
                    unionParent(i, j);
                }
            }
        }

        boolean isConnected = true;
        ArrayList<Integer> plan = new ArrayList<>();

        for(int i = 0 ; i <m ;i++){
            plan.add(sc.nextInt());
        }

        for(int i =0  ; i < m - 1; i++){
            if(findParent(plan.get(i)) != findParent(plan.get(i+1))){
                isConnected = false;
                break;
            }
        }

        System.out.println(isConnected ? "YES" : "NO");
    }

    static int findParent(int x) {
        if (parent[x] != x) parent[x] = findParent(parent[x]);
        return parent[x];
    }

    static void unionParent(int x, int y) {

        int a = findParent(x);
        int b = findParent(y);

        if (a < b) parent[b] = a;
        else parent[a] = b;
    }
}
