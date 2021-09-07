package test;

import java.util.*;

public class Chapter10 {


    public static void main(String[] args) {
        _10_7();
    }

    //계속 부모를 찾아 내려가서 부모를 찾으면 해당값 리턴
    public static int findParent(int[] parent, int x) {
        if (parent[x] == x) return x;
        return findParent(parent, parent[x]);
    }

    /*
    위의 방식대로 가면 계속 찾아들어가서 찾기때문에 노드 갯수가 V고 union 갯수가 M개면
    O(VM) 만큼 시간복잡도가 생기지만 경로압축기법을 이용해 재귀 호출후 부모 테이블값을 갱신함으로써 더 효율적으로
    검색할수 있다.
    */
    public static int findParent2(int[] parent, int x) {
        if (parent[x] != x) parent[x] = findParent2(parent, parent[x]);
        return parent[x];
    }

    // 두 원소가 속한 집합 합치기
    public static void unionParent(int[] parent, int a, int b) {
        //각각 부모를 추출해 부모가 큰쪽이 작은쪽을 바라보게 작업
        //부모가 작은쪽이 부모가 된다.
        a = findParent(parent, a);
        b = findParent(parent, b);
        if (a < b) parent[b] = a;
        else parent[a] = b;
    }

    static void _10_1() {

        Scanner sc = new Scanner(System.in);
        //노드수
        int v = sc.nextInt();
        //간선수
        int e = sc.nextInt();

        int[] parent = new int[10000];

        for (int i = 1; i <= v; i++) {
            //부모를 자기자신으로 초기화 (1부터)
            parent[i] = i;
        }

        for (int i = 0; i < e; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            unionParent(parent, a, b);
        }

        // 각 원소가 속한 집합 출력
        for (int i = 1; i <= v; i++) {
            System.out.print(findParent(parent, i) + " ");
        }
    }

    //서로소 집합을 활용한 사이클 판별코드
    void _10_2() {
        Scanner sc = new Scanner(System.in);

        int v = sc.nextInt();
        int e = sc.nextInt();


        int[] parent = new int[10000];

        for (int i = 1; i <= v; i++) {
            parent[i] = i;
        }

        //사이클 발생 유무 체크를 위함
        boolean isCycle = false;

        for (int i = 0; i < e; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();

            if (findParent(parent, a) == findParent(parent, b)) {
                isCycle = true;
                break;
            } else {
                unionParent(parent, a, b);
            }
        }

        if (isCycle) System.out.println("사이클 발생");
        else System.out.println("사이클 발생 안함");
    }

    void _10_3() {
        Scanner sc = new Scanner(System.in);

        int v = sc.nextInt();
        int e = sc.nextInt();

        //모든 간선을 담을 리스트
        ArrayList<Edge> edges = new ArrayList<>();
        //최종 비용을 담을 변수
        int result = 0;

        int[] parent = new int[10000];

        for (int i = 1; i <= v; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < e; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int cost = sc.nextInt();
            edges.add(new Edge(a, b, cost));
        }

        //간선을 비용이 작은순으로 정렬
        Collections.sort(edges);

        for (int i = 0; i < edges.size(); i++) {
            int cost = edges.get(i).getDistance();
            int nodeA = edges.get(i).getNodeA();
            int nodeB = edges.get(i).getNodeB();
            //사이클이 발생하지 않는 경우 합집합 처리하고 거리에 더함
            if (findParent(parent, nodeA) != findParent(parent, nodeB)) {
                unionParent(parent, nodeA, nodeB);
                result += cost;
            }
        }
        System.out.println(result);
    }

    //위상정렬 함수
    void _10_4() {

        Scanner sc = new Scanner(System.in);

        int v = sc.nextInt();
        int e = sc.nextInt();

        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        //진입 차수 (진입 차수가 0이라는건 말그대로 한번도 누가 건드리지 않았다는 뜻)
        int[] indegree = new int[10001];

        for (int i = 1; i <= v; i++) {
            graph.add(new ArrayList<>());
        }

        //간선 수 만큼 돌아서 간선 그래프 정보및 간선 진입차수 정보 초기화
        for (int i = 0; i < e; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            graph.get(a).add(b);
            indegree[b] += 1;
        }

        //알고리즘 수행결과 담을 리스트
        ArrayList<Integer> result = new ArrayList<>();
        Queue<Integer> q = new LinkedList<>();

        //진입차수가 0인 노드를 큐에 담기
        for (int i = 1; i <= v; i++) {
            if (indegree[i] == 0) q.offer(i);
        }

        //큐가 빌떄까지
        while (!q.isEmpty()) {
            int now = q.poll();
            result.add(now);

            //위상 정렬 특성상 진입차수를 0을 만들어 0이 된 노드를 기준으로 다시 정렬을 진행하는식으로 반복
            for (int i = 0; i < graph.get(now).size(); i++) {
                // 해당 원소와 연결된 노드들의 진입차수에서 1 빼기
                indegree[graph.get(now).get(i)] -= -1;

                //진입차수가 0이 되는 노드를 큐에 삽입
                if (indegree[graph.get(now).get(i)] == 0) {
                    q.offer(graph.get(now).get(i));
                }
            }
        }

        for (int i = 0; i < result.size(); i++) {
            System.out.println(result.get(i) + " ");
        }
    }

    static void _10_5() {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] parent = new int[100000];

        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        sc.nextLine();

        for (int i = 0; i < m; i++) {
            String lines = sc.nextLine();
            int type = Integer.parseInt(lines.split(" ")[0]);
            int a = Integer.parseInt(lines.split(" ")[1]);
            int b = Integer.parseInt(lines.split(" ")[2]);

            //합연산
            if (type == 0) {
                _10_5_union(parent, a, b);
            } else { // 해당팀 맞나 체크
                String result = _10_5_isCommonParent(parent, a, b) ? "YES" : "NO";
                System.out.println(result);
            }
        }
    }

    static void _10_5_union(int[] parent, int a, int b) {
        a = _10_5_findParent(parent, a);
        b = _10_5_findParent(parent, b);

        if (a < b) parent[b] = a;
        else parent[a] = b;
    }

    static int _10_5_findParent(int[] parent, int x) {
        if (parent[x] != x) parent[x] = _10_5_findParent(parent, parent[x]);
        return parent[x];
    }

    static boolean _10_5_isCommonParent(int[] parent, int a, int b) {
        a = _10_5_findParent(parent, a);
        b = _10_5_findParent(parent, b);
        return a == b;
    }

    /*
        마을을 2개로 나누고 최소하나이상이면 마을이라 했으므로 신장트리를 크루스칼 알고리즘을 사용해 나눠서
        모든 노드는 포함되지만 사이클이 존재 하지 않도록 설정하고
        그 중 비용이 가장큰 마을 하나를 빼서 하나의 마을로 만들면 모든조건이 성립된다.
    */
    static void _10_6() {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        int[] parent = new int[100001];
        ArrayList<Edge> edges = new ArrayList<>();

        for (int i = 1; i < n; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int cost = sc.nextInt();
            edges.add(new Edge(cost, a, b));
        }

        Collections.sort(edges);

        int result = 0;
        int last = 0;

        for (int i = 0; i < edges.size(); i++) {
            int cost = edges.get(i).getDistance();
            int nodeA = edges.get(i).getNodeA();
            int nodeB = edges.get(i).getNodeB();

            if (findParent(parent, nodeA) != findParent(parent, nodeB)) {
                unionParent(parent, nodeA, nodeB);
                result += cost;
                last = cost;
            }
        }
        System.out.println(result - last);
    }

    static void _10_7() {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        int[] indegree = new int[100001];
        int[] times = new int[100001];

        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }


        for (int i = 1; i <= n; i++) {
            int a = sc.nextInt();
            times[i] = a;

            while (true) {
                a = sc.nextInt();
                if (a == -1) break;
                graph.get(a).add(i);
                indegree[i]++;
            }
        }

        int[] result = new int[100001];
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 1; i <= n; i++) {
            result[i] = (times[i]);
        }

        for (int i = 1; i <= n; i++) {
            if (indegree[i] == 0) queue.offer(i);
        }

        while (!queue.isEmpty()) {

            int now = queue.poll();

            for (int i = 0; i < graph.get(now).size(); i++) {

                result[graph.get(now).get(i)] = Math.max(result[graph.get(now).get(i)], result[now] + times[graph.get(now).get(i)]);
                indegree[graph.get(now).get(i)] -= 1;

                if(indegree[graph.get(now).get(i)]== 0) queue.offer(graph.get(now).get(i));
            }
        }
        for (int i = 1; i <= n; i++) {
            System.out.println(result[i]);
        }
    }

}

class Edge implements Comparable<Edge> {


    private int distance;
    private int nodeA;
    private int nodeB;

    public Edge(int distance, int nodeA, int nodeB) {
        this.distance = distance;
        this.nodeA = nodeA;
        this.nodeB = nodeB;
    }

    public int getDistance() {
        return distance;
    }

    public int getNodeA() {
        return nodeA;
    }

    public int getNodeB() {
        return nodeB;
    }

    @Override
    public int compareTo(Edge other) {
        if (this.distance < other.distance) return -1;
        return 1;
    }
}