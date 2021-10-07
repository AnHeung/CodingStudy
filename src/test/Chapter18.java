package test;

import java.util.*;

public class Chapter18 {

    public static int n, m, l, result;
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
        _18_2();
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

        for (int i = 0; i < list.size(); i++) {
            int a = list.get(i).getNodeA();
            int b = list.get(i).getNodeB();

            if (findParent(a) != findParent(b)) {
                unionParent(a, b);
            }
        }

        boolean isConnected = true;
        int x = 0;

        for (int i = 0; i < m; i++) {
            if (i == 0) {
                x = parent[sc.nextInt()];
                continue;
            }
            if (parent[x] != parent[sc.nextInt()]) {
                isConnected = false;
                break;
            }
        }

        System.out.println(isConnected ? "YES" : "NO");
    }

    static void _18_2() {

        n = sc.nextInt(); //탑승구수
        m = sc.nextInt(); //비행기수

        parent = new int[n+1];

        for (int i = 0; i <= n; i++) {
            parent[i] = i;
        }

        /*
        비행기가 1번부터 ~m 번까지 날라오고
        예를 들어 탑승구 정보가 4면
        1번 비행기가 1~4까지 탑승이 가능하다는 말
         이걸 쉽게 풀기 위해 합집합을 사용해서 푸는데
         0번 탑승구는 없지만 만들어서 예를 들면 1번 비행기가 탑승구 정보가 1이면
         1,0을 합집합해서 둘다 부모를 0으로 만들고 최종적으로 다음 탑승구 정보값을 가지고 
         비행기 도킹된 값과 비교해서 값이 0 이 나오면 거기서 종료
         */


        for(int i = 0 ; i < m; i++){
            int x = sc.nextInt();
            int root = findParent(x);
            if(root == 0) break;
            unionParent(root, root-1);
            result++;
        }

        System.out.println(result);

    }

    static void _18_3() {

        n = sc.nextInt();
        m = sc.nextInt();
        parent = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        ArrayList<Edge> list = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();
            list.add(new Edge(c, a, b));
        }

        Collections.sort(list);

        int result = 0;
        int total = 0;

        for (int i = 0; i < list.size(); i++) {

            int a = list.get(i).getNodeA();
            int b = list.get(i).getNodeB();
            int dist = list.get(i).getDistance();
            total += dist;

            if (findParent(a) != findParent(b)) {
                unionParent(a, b);
                result += dist;
            }
        }
        System.out.println(total - result);
    }

    static void _18_4() {

        n = sc.nextInt();

        ArrayList<Planet> planetList = new ArrayList<>();
        ArrayList<Edge> list = new ArrayList<>();
        parent = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < n; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();
            planetList.add(new Planet(i, a, b, c));
        }

        for (int i = 0; i < planetList.size(); i++) {
            int no = planetList.get(i).no;
            int x = planetList.get(i).x;
            int y = planetList.get(i).y;
            int z = planetList.get(i).z;

            for (int j = i; j < planetList.size(); j++) {
                int otherNo = planetList.get(j).no;
                if (no != otherNo) {
                    int nx = planetList.get(j).x;
                    int ny = planetList.get(j).y;
                    int nz = planetList.get(j).z;
                    list.add(new Edge(Math.min(Math.abs(x - nx), Math.min(Math.abs(y - ny), Math.abs(z - nz))), no, otherNo));
                }
            }
        }

        Collections.sort(list);

        for (int i = 0; i < list.size(); i++) {

            int a = list.get(i).getNodeA();
            int b = list.get(i).getNodeB();
            int dist = list.get(i).getDistance();

            if (findParent(a) != findParent(b)) {
                unionParent(a, b);
                result += dist;
            }
        }

        System.out.println(result);
    }

    static void _18_5() {

        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        int[] indegree = new int[501];
        visited = new boolean[501][501];

        for (int tc = 0; tc < t; tc++) {
            Arrays.fill(indegree, 0);
            for (int i = 0; i < 501; i++) {
                Arrays.fill(visited[i], false);
            }

            n = sc.nextInt();
            // 작년 순위 정보 입력
            ArrayList<Integer> rankList = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                int x = sc.nextInt();
                rankList.add(x);
            }


            /*
              위상정렬을 사용해 우선순위를 잡아두고 시작
              작년 순위가 5,4,3,2,1 이므로 5번은 4,3,2,1 을 다 가지고 있고
              진입 차수 (진입 차수가 0이라는건 말그대로 한번도 누가 건드리지 않았다는 뜻)를 0으로 설정
              반대로 1의 경우 하나도 가지고 있지 않고 진입차수만 4로 설정
             */

            // 방향 그래프의 간선 정보 초기화
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    visited[rankList.get(i)][rankList.get(j)] = true;
                    indegree[rankList.get(j)] += 1;
                }
            }

            /*
               해당 이중배열은 작년 순위에 대한값을 가지고 있는 배열이므로
               visited[a][b] 가 true 란 말은 a 가 b를 가지고있다는 말인데
               순위 변동이 생겼으므로 해당 부분과 진입차수 부분을 모두 변경해준다.
             */

            // 올해 변경된 순위 정보 입력
            m = sc.nextInt();
            for (int i = 0; i < m; i++) {
                int a = sc.nextInt();
                int b = sc.nextInt();
                // 간선의 방향 뒤집기
                if (visited[a][b]) {
                    visited[a][b] = false;
                    visited[b][a] = true;
                    indegree[a] += 1;
                    indegree[b] -= 1;
                } else {
                    visited[a][b] = true;
                    visited[b][a] = false;
                    indegree[a] -= 1;
                    indegree[b] += 1;
                }
            }

            // 위상 정렬(Topology Sort) 시작
            ArrayList<Integer> result = new ArrayList<>(); // 알고리즘 수행 결과를 담을 리스트
            Queue<Integer> q = new LinkedList<>(); // 큐 라이브러리 사용

            // 처음 시작할 때는 진입차수가 0인 노드를 큐에 삽입
            for (int i = 1; i <= rankList.size(); i++) {
                if (indegree[i] == 0) {
                    q.offer(i);
                }
            }

            boolean certain = true; // 위상 정렬 결과가 오직 하나인지의 여부
            boolean cycle = false; // 그래프 내 사이클이 존재하는지 여부

            // 정확히 노드의 개수만큼 반복
            for (int i = 0; i < n; i++) {
                // 큐가 비어 있다면 사이클이 발생했다는 의미
                if (q.size() == 0) {
                    cycle = true;
                    break;
                }
                // 큐의 원소가 2개 이상이라면 가능한 정렬 결과가 여러 개라는 의미
                if (q.size() >= 2) {
                    certain = false;
                    break;
                }
                // 큐에서 원소 꺼내기
                int now = q.poll();
                result.add(now);
                // 해당 원소와 연결된 노드들의 진입차수에서 1 빼기
                for (int j = 1; j <= n; j++) {
                    if (visited[now][j]) {
                        indegree[j] -= 1;
                        // 새롭게 진입차수가 0이 되는 노드를 큐에 삽입
                        if (indegree[j] == 0) {
                            q.offer(j);
                        }
                    }
                }
            }

            // 사이클이 발생하는 경우(일관성이 없는 경우)
            if (cycle) System.out.println("IMPOSSIBLE");
                // 위상 정렬 결과가 여러 개인 경우
            else if (!certain) System.out.println("?");
                // 위상 정렬을 수행한 결과 출력
            else {
                for (int i = 0; i < result.size(); i++) {
                    System.out.print(result.get(i) + " ");
                }
                System.out.println();
            }
        }
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

class Planet {

    int no;
    int x;
    int y;
    int z;

    public Planet(int no, int x, int y, int z) {
        this.no = no;
        this.x = x;
        this.y = y;
        this.z = z;
    }
}
