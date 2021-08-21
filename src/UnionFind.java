/*
   합집합 -> 두 인트값을 비교해서 자기보다 크기가 작은쪽을 부모로 설정
   처음에 각자 자기자신이 값인 부모를 가지고 있다가 둘을 비교해 작은쪽 부모값을 가지도록 작업
 */
public class UnionFind {

    public static void main(String[] args) {
        int[] parent = new int[11];
        for (int i = 0; i <= 10; i++) {
            parent[i] = i;
        }
        unionParent(parent, 1, 2);
        unionParent(parent, 2, 3);
        unionParent(parent, 3, 4);
        unionParent(parent, 5, 6);
        unionParent(parent, 6, 7);
        unionParent(parent, 7, 8);
        unionParent(parent, 9, 10);
        Util.println(findParent(parent, 1, 5));
        unionParent(parent, 1, 8); //1,5 뿐 아니라 1과 8을 연결해도 다 연결됨. 왜냐하면 8의 부모가 5이기 때문
        Util.println(findParent(parent, 1, 5));
    }

    static int getParent(int[] parent, int x) {
        if (parent[x] == x) return x;
        return getParent(parent, parent[x]);
    }

    static void unionParent(int[] parent, int a, int b) {
        a = getParent(parent, a);
        b = getParent(parent, b);
        if (a < b) parent[b] = a;
        else parent[a] = b;
    }

    static boolean findParent(int[] parent, int a, int b) {
        a = getParent(parent, a);
        b = getParent(parent, b);
        return a == b;
    }
}
