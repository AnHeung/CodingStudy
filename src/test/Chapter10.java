package test;

import java.util.Scanner;

public class Chapter10 {


    public static void main(String[] args) {
        _10_1();
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
        if (parent[x] != x) parent[x] = findParent2(parent, x);
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

}
