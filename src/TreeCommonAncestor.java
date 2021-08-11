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

public class TreeCommonAncestor {

    public static void main(String[] args) {
        Tree3 t = new Tree3(10);
        Tree3.Node fa = t.commonAncestor(3,5);
        Util.println("The first common ancestor is " + fa.data);
    }
}
