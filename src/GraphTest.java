import java.util.LinkedList;
import java.util.Stack;

class Graph {

    Node[] nodes;

    class Node {
        int data;
        LinkedList<Node> adjacent; //인접한노드 (트리랑 달리 자식관계가 아닌 인접노드관계다)
        boolean marked; //이미 들어온 노든지 아닌지

        Node(int data) {
            this.data = data;
            this.marked = false;
            this.adjacent = new LinkedList<>();
        }
    }

    Graph(int size) {
        nodes = new Node[size];
        for (int i = 0; i < size; i++) {
            nodes[i] = new Node(i);
        }
    }

    //확실하게 mark 값 false 로 이니싱
    void initMarks(){
        for(Node n : nodes){
            n.marked = false;
        }
    }

    boolean search(int i1, int i2){
        return search(nodes[i1] , nodes[i2]);
    }

    boolean search(Node start, Node end) {
        initMarks();
        LinkedList<Node> q = new LinkedList<>();
        q.add(start);
        while(!q.isEmpty()){
            Node root = q.removeFirst();
            if(root == end){
                return true;
            }
            for(Node n : root.adjacent){
                if(!n.marked){
                    n.marked = true;
                    q.add(n);
                    visit(n);
                }
            }
        }
        return false;
    }


    void addEdge(int i1, int i2) {
        Node n1 = nodes[i1];
        Node n2 = nodes[i2];
        if (!n1.adjacent.contains(n2)) {
            n1.adjacent.add(n2);
        }
        if (!n2.adjacent.contains(n1)) {
            n2.adjacent.add(n1);
        }
    }

    void dfs() {
        dfs(0);
    }

    void dfs(int index) {
        Node root = nodes[index];
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        root.marked = true;
        while (!stack.isEmpty()) {
            Node r = stack.pop();
            for (Node n : r.adjacent) {
                if (!n.marked) {
                    n.marked = true;
                    stack.push(n);
                }
            }
            visit(r);
        }
    }

    void bfs() {
        bfs(0);
    }

    void bfs(int index) {
        Node root = nodes[index];
        Queue<Node> queue = new Queue<>();
        queue.enqueue(root);
        root.marked = true;
        while (!queue.isEmpty()) {
            Node r = queue.dequeue();
            for (Node n : r.adjacent) {
                if (!n.marked) {
                    n.marked = true;
                    queue.enqueue(n);
                }
            }
            visit(r);
        }
    }

    void dfsR(Node r) {
        if (r == null) return;
        r.marked = true;
        visit(r);
        for (Node n : r.adjacent) {
            if (!n.marked) {
                dfsR(n);
            }
        }
    }

    void dfsR(int index) {
        Node r = nodes[index];
        dfsR(r);
    }

    void dfsR(){
        dfsR(0);
    }

    void visit(Node n) {
        System.out.print(n.data + " ");
    }
}

/*
 *
 *    0
 *   /
 *   1 -- 3    7
 *   |    | \ /
 *   |    |  5
 *   2 -- 4   \
 *              6 - 8
 *
 */

public class GraphTest {

    public static void main (String [] args){
        Graph g = new Graph(9);
        g.addEdge(0,1);
        g.addEdge(1,2);
        g.addEdge(1,3);
        g.addEdge(2,4);
        g.addEdge(2,3);
        g.addEdge(3,4);
        g.addEdge(3,5);
        g.addEdge(5,6);
        g.addEdge(5,7);
        g.addEdge(6,8);
//        g.dfs(3);
        System.out.println(g.search(1,8));
    }
}

