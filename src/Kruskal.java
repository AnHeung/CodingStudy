import java.util.Comparator;
import java.util.LinkedList;

class Edge {
    int[] node = new int[2];
    int distance;

    public Edge(int a, int b, int distance) {
        node[0] = a;
        node[1] = b;
        this.distance = distance;
    }
}

public class Kruskal {

    public static void main(String[] args) {

        int n = 7;

        LinkedList<Edge> list = new LinkedList<>();
        list.push(new Edge(1, 7, 12));
        list.push(new Edge(1, 4, 28));
        list.push(new Edge(1, 2, 67));
        list.push(new Edge(1, 5, 17));
        list.push(new Edge(2, 4, 24));
        list.push(new Edge(2, 5, 62));
        list.push(new Edge(3, 5, 20));
        list.push(new Edge(3, 6, 37));
        list.push(new Edge(4, 7, 13));
        list.push(new Edge(5, 6, 45));
        list.push(new Edge(5, 7, 73));

//        list.sort((o1, o2) -> Integer.compare(o1.distance, o2.distance));
        list.sort(Comparator.comparingInt(o -> o.distance)); //간선의 비용을 기준으로 오름차순

        //합집합을 위한 사전작업 (각 노드를 부모로 세팅)
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        int sum = 0;
        for (int i = 0; i < list.size(); i++) {
            //사이클이 발생하지 않는경우 그래프에 추가
            if (!UnionFind.findParent(parent, list.get(i).node[0] - 1, list.get(i).node[1] - 1)) {
                sum += list.get(i).distance;
                UnionFind.unionParent(parent, list.get(i).node[0] - 1, list.get(i).node[1] - 1);
            }
        }
        Util.println(sum);
    }
}
