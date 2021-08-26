package test;

import java.lang.reflect.Array;
import java.util.*;

public class Chapter5 {

    public static void main(String[] args) {
        _5_2_sol();
    }

    //실패 요인 -> dfs 만 고려해서 어떻게든 스택을 사용해 보려햇음 , 처음 값을 주입함에 있어서 (그래프생성) 방향을 못잡았음
    static void _5_1_sol() {

        Scanner sc = new Scanner(System.in);

        // N, M을 공백을 기준으로 구분하여 입력 받기
        int n = sc.nextInt();
        int m = sc.nextInt();
        sc.nextLine(); // 버퍼 지우기

        int[][] graph = new int[n][m];

        // 2차원 리스트의 맵 정보 입력 받기
        for (int i = 0; i < n; i++) {
            String str = sc.nextLine();
            for (int j = 0; j < m; j++) {
                graph[i][j] = str.charAt(j) - '0';
            }
        }

        // 모든 노드(위치)에 대하여 음료수 채우기
        int result = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // 현재 위치에서 DFS 수행
                if (dfs(n, m, graph, i, j)) {
                    result += 1;
                }
            }
        }
        System.out.println(result); // 정답 출력
    }


    // DFS로 특정 노드를 방문하고 연결된 모든 노드들도 방문
    public static boolean dfs(int n, int m, int[][] graph, int x, int y) {
        // 주어진 범위를 벗어나는 경우에는 즉시 종료
        if (x <= -1 || x >= n || y <= -1 || y >= m) {
            return false;
        }
        // 현재 노드를 아직 방문하지 않았다면
        if (graph[x][y] == 0) {
            // 해당 노드 방문 처리
            graph[x][y] = 1;
            // 상, 하, 좌, 우의 위치들도 모두 재귀적으로 호출
            dfs(n, m, graph, x - 1, y);
            dfs(n, m, graph, x, y - 1);
            dfs(n, m, graph, x + 1, y);
            dfs(n, m, graph, x, y + 1);
            return true;
        }
        return false;
    }

    static class Point {

        int row;
        int col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    static void _5_2() {

        Scanner sc = new Scanner(System.in);

        int row = sc.nextInt();
        int col = sc.nextInt();
        sc.nextLine();

        int[][] arr = new int[row][col];

        for (int i = 0; i < row; i++) {
            String line = sc.nextLine();
            for (int j = 0; j < col; j++) {
                arr[i][j] = line.charAt(j) - '0';
            }
        }

        ArrayList<Point> list = new ArrayList<>();

        test(arr, row - 1, col - 1, list);

        for (Point p : list) {
            System.out.print(p.row + "" + p.col + " -> ");
        }
    }

    static boolean isRange(int[][] arr, int row, int col) {
        return row >= 0 && row <= arr.length - 1 && col >= 0 && col <= arr[row].length - 1;
    }

    static boolean test(int[][] arr, int row, int col, ArrayList<Point> list) {
        if (!isRange(arr, row, col) || arr[row][col] == 0) return false;

        if (row == 0 && col == 0
                || test(arr, row - 1, col, list)
                || test(arr, row, col - 1, list)) {
            list.add(new Point(row, col));
            return true;
        }
        return false;
    }

    static void _5_2_sol() {
        Scanner sc = new Scanner(System.in);

        // N, M을 공백을 기준으로 구분하여 입력 받기
        int n = sc.nextInt();
        int m = sc.nextInt();
        sc.nextLine(); // 버퍼 지우기
        int[][] graph = new int[n][m];

        // 2차원 리스트의 맵 정보 입력 받기
        for (int i = 0; i < n; i++) {
            String str = sc.nextLine();
            for (int j = 0; j < m; j++) {
                graph[i][j] = str.charAt(j) - '0';
            }
        }

        // BFS를 수행한 결과 출력
        System.out.println(_5_2_sol_bfs(graph, n, m));
    }

    static int _5_2_sol_bfs(int[][] graph, int n, int m) {

        //상하좌우
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        int initX = 0;
        int initY = 0;

        // 큐(Queue) 구현을 위해 queue 라이브러리 사용
        Queue<Point> q = new LinkedList<>();
        //초기값 추가
        q.offer(new Point(initX, initY));
        // 큐가 빌 때까지 반복하기
        while (!q.isEmpty()) {
            //가장앞에 값 가져오기
            Point node = q.poll();
            int row = node.row;
            int col = node.col;
            // 현재 위치에서 4가지 방향으로의 위치 확인
            for (int i = 0; i < 4; i++) {
                int nx = row + dx[i];
                int ny = col + dy[i];
                // 미로 찾기 공간을 벗어난 경우 무시
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                // 벽인 경우 무시
                if (graph[nx][ny] == 0) continue;
                // 해당 노드를 처음 방문하는 경우에만 최단 거리 기록
                if (graph[nx][ny] == 1) {
                    graph[nx][ny] = graph[row][col] + 1;
                    q.offer(new Point(nx, ny));
                }
            }
        }
        // 가장 오른쪽 아래까지의 최단 거리 반환
        return graph[n - 1][m - 1];
    }
}
