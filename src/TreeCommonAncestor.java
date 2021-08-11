import java.util.HashMap;

class Tree3 {

    static class Node {
        int data;
        Node left;
        Node right;
        Node parent;

        public Node(int data) {
            this.data = data;
        }
    }

    static class Result{
        Node node;
        boolean isAncestor;

        public Result(Node node, boolean isAncestor) {
            this.node = node;
            this.isAncestor = isAncestor;
        }
    }

    Node root;
    HashMap<Integer, Node> rootMap;

    public Tree3(int size) {
        rootMap = new HashMap<>();
        root = makeBst(0, size - 1, null);
    }

    Node makeBst(int start, int end, Node parent) {
        if (start > end) return null;
        int mid = (start + end) / 2;
        Node node = new Node(mid);
        node.left = makeBst(start, mid - 1, node);
        node.right = makeBst(mid + 1, end, node);
        node.parent = parent;
        rootMap.put(mid, node);
        return node;
    }

    Node getNode(int data) {
        return rootMap.get(data);
    }

    //인자로 받은 값이 root 값의 자손인지 체크
    boolean covers(Node root, Node node) {
        if (root == null) return false;
        if (root == node) return true;
        return covers(root.left, node) || covers(root.right, node);
    }

    //깊이 비교로 찾기
    Node commonAncestor(int d1, int d2) {
        Node p = getNode(d1);
        Node q = getNode(d2);
        int diff = depth(p) - depth(q);
        Node shortNode = diff > 0 ? q : p; //짧은거
        Node longNode = diff > 0 ? p : q; //긴거
        longNode = goUpBy(longNode, Math.abs(diff)); //차이만큼 올라가서 노드길이를 맞춤
        while (shortNode != longNode && shortNode != null && longNode != null) {
            shortNode = shortNode.parent;
            longNode = longNode.parent;
        }
        return shortNode == null || longNode == null ? null : shortNode;
    }


    /*
        ( 4 )
       /    \
      /      \
    (1)      (7)
    / \       / \
   /   \     /   \
 (0)   (2)  (5)   (8)
        \    \      \
        (3)  (6)    (9)

*/

    //밑에서 위로 하나씩 올라가면서 자식인지 체크
    Node commonAncestor2(int d1, int d2) {
        Node p = getNode(d1);
        Node q = getNode(d2);
        if (!covers(root, p) || !covers(root, q)) return null;
        else if (covers(p, q)) return p;
        else if (covers(q, p)) return q;

        Node sibling = getSibling(p);
        Node parent = p.parent;
        while (!(covers(sibling, q))) {
            // 한칸씩 올라가면서 2번째 노드가(q) 형제의 하위트리의 속해있는 노드인지 체크
            sibling = getSibling(parent);
            parent = parent.parent;
        }
        return parent;
    }

    //부모를 모른다는 가정하에 재귀함수를 이용해 풀수있는 방법
    Node commonAncestor3(int d1, int d2) {
        Node p = getNode(d1);
        Node q = getNode(d2);
        if (!covers(root, p) || !covers(root, q)) return null;
        return ancestorHelper(root, p, q);
    }


    Node commonAncestor4(int d1 , int d2){
        Node p = getNode(d1);
        Node q = getNode(d2);
        return commonAncestor4(root ,p, q);
    }
    // postorder 를 이용해 바닥부터 노드에 도착시 왼오가운데 순으로 검색하는 재귀함수 (단 이방법으론 없는 노드를 찾게시키면 null 을 반환하므로 수정이필요)
    Node commonAncestor4(Node root, Node p, Node q) {
        if(root == null) return null;
        if(root == p && root == q ) return root;
        Node x = commonAncestor4(root.left , p , q); //왼쪽부터 시작
        if(x != null && x != p && x!= q) return x; // 값을 찾앗는데 p,q와 다른 노드면 부모노드 이므로 반환하고 종료
        Node y =  commonAncestor4(root.right , p , q); //오른쪽 시작
        if(y != null && y!=p && y!=q) return y;

        // x 랑 y가 둘다 값이 있다는건 해당 값이 p랑 q라는 뜻 즉 root 가 공통부모
        if(x!= null && y!= null) return root;
        else if(root == p || root == q) return root; //하위 트리를 다 찾고나서 나를 찾기 위해 순서를 이렇게 적용 (안그러면 하나만 찾고 위로 올라감)
        else  return x == null ? y : x; // null 일수도 아닐수도 있는노드
    }

    Node ancestorHelper(Node root, Node p, Node q) {
        if(root == null || root == p || root == q) return root;

        boolean pIsOnLeft = covers(root.left, p);
        boolean qIsOnLeft = covers(root.left, q);
        //둘이 다르다는건 갈라진다는뜻 (한놈은 왼쪽 한놈은 오른쪽 즉 그 갈라지는 포인트가 공통부모)
        if(pIsOnLeft != qIsOnLeft) return root;
        //
        Node childSide = pIsOnLeft ? root.left : root.right;
        return ancestorHelper(childSide ,p ,q);
    }

    //부모 노드를 받아와서 내가 왼쪽자식이면 오른쪽 자식을 오른쪽이면 왼쪽을 반환 (즉 형제노드 반환)
    Node getSibling(Node node) {
        if (node == null || node.parent == null) return null;
        Node parent = node.parent;
        return parent.left == node ? parent.right : parent.left;
    }

    Node goUpBy(Node node, int diff) {
        while (diff > 0 && node != null) {
            node = node.parent;
            diff--;
        }
        return node;
    }

    int depth(Node node) {
        int depth = 0;
        while (node != null) {
            node = node.parent;
            depth++;
        }
        return depth;
    }
}


/*
        ( 4 )
       /    \
      /      \
    (1)      (7)
    / \       / \
   /   \     /   \
 (0)   (2)  (5)   (8)
        \    \      \
        (3)  (6)    (9)

*/

public class TreeCommonAncestor {

    public static void main(String[] args) {
        Tree3 t = new Tree3(10);
        Tree3.Node fa = t.commonAncestor(3, 5);
//        Util.println("The first common ancestor is " + fa.data);
//        Util.println("The first common ancestor is " + t.commonAncestor2(3, 5).data);
        Util.println("The first common ancestor is " + t.commonAncestor3(0, 3).data);
        Util.println("The first common ancestor is " + t.commonAncestor4(5, 9).data);
    }
}
