package test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Chapter18 {

    public static int n, m, result;
    public static int[] arr;
    public static Scanner sc = new Scanner(System.in);
    public static int[][] map;
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
        ArrayList<Edge> list = new ArrayList<>();

        parent = new int[n + 1];
        visited = new boolean[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                int a = sc.nextInt();
                if (a == 1) {
                    if (!visited[i][j] && !visited[j][i]) {
                        list.add(new Edge(1, i, j));
                        visited[i][j] = true;
                        visited[j][i] = true;
                    }
                }
            }
        }
        Collections.sort(list);

        System.out.println();

        for(int i = 0 ; i <list.size(); i++){
            int a = list.get(i).getNodeA();
            int b = list.get(i).getNodeB();

           if(findParent(a) != findParent(b)){
                unionParent(a,b);
           }
        }

        boolean isConnected = true;
        int x = 0;

        for(int i = 0 ; i < m; i++){
            if(i == 0){
                x = parent[sc.nextInt()];
                continue;
            }
            if(parent[x] != parent[sc.nextInt()]){
                isConnected = false;
                break;
            }
        }

        System.out.println(isConnected ? "YES" : "NO");
    }

    static void unionParent(int x, int y) {
        int a = findParent(x);
        int b = findParent(y);
        if (a < b) parent[b] = a;
        else parent[a] = b;
    }

    static int findParent(int x) {
        if (parent[x] != x) parent[x] = findParent(parent[x]);
        return parent[x];
    }
}
