class Tree2 {

    Node root;

    static class Node {
        int data;
        Node left;
        Node right;
        Node parent;

        public Node(int data) {
            this.data = data;
        }
    }

    public Tree2(int size) {
        root = makeBST(0, size - 1, null);
    }

    /*
        ( 4 )
       /    \
      /      \
    (1)      (7)
    / \       / \
   /   \     /   \
 (0)   (2)  (5)   (8)
        \    \    / \
        (3)  (6)(11)(9)
                      \
                      (10)
*/


    Node makeBST(int start, int end, Node parent) {
        if (start > end) return null;
        int mid = (start + end) / 2;
        Node node = new Node(mid);
        node.left = makeBST(start, mid - 1, node);
        node.right = makeBST(mid + 1, end, node);
        node.parent = parent;
        return node;
    }

    void findNext(Node node) {
        if (node.right == null) {
            Util.println(findAbove(node.parent, node).data + " is " + node.data + " 's next");
        } else {
            Util.println(findBelow(node.right).data + " is " + node.data + " 's next");
        }
    }

    Node findBelow(Node parent) {
        //돌다가 왼쪽에 자식이 없다 => 해당노드는 다음노드
        if (parent.left == null) return parent;
        return findBelow(parent.left);
    }

    Node findAbove(Node parent, Node child) {
        if (parent == null) return null;
        //부모의 왼쪽 노드가 나랑 같으면 내 부모
        if (parent.left == child) return parent;
        return findAbove(parent.parent, parent);
    }
}


class Tree {

    Node root;
    int size;

    static class Level {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
    }

    Tree(int size) {
        this.size = size;
        root = makeRecursiveTree(0, size - 1);
        root.right.right.right.left = new Node(10);
        this.size++;
//        root.right.right.left = new Node(11);
    }

    static class Node {
        int data;
        Node left;
        Node right;

        public Node(int data) {
            this.data = data;
        }
    }

    Node makeRecursiveTree(int start, int end) {
        if (start > end) return null;
        int mid = (start + end) / 2;
        Node node = new Node(mid);
        node.left = makeRecursiveTree(start, mid - 1);
        node.right = makeRecursiveTree(mid + 1, end);
        return node;
    }

    //왼쪽 노드부터 좌우 비교하면서 끝까지 가다가 하나라도 좌우값이 2개 차이나면 false
    boolean isBalanced(Node root) {
        if (root == null) return true;
        int heightDiff = getHeight(root.left) - getHeight(root.right);
        if (Math.abs(heightDiff) > 1) {
            return false;
        } else {
            return isBalanced(root.left) && isBalanced(root.right);
        }
    }


    // int 형을 리턴하기 위해 Integer.MIN_VALUE 값을 false 라고 간주하고 호출
    boolean isBalanced2(Node root) {
        return checkHeight(root) != Integer.MIN_VALUE;
    }

    boolean isBalanced3(Node root) {
        Level obj = new Level();
        checkBalanced(root, obj, 0);
        if (Math.abs(obj.min - obj.max) > 1) return false;
        else return true;
    }

    void checkBalanced(Node node, Level obj, int level) {
        if (node == null) {
            if (level < obj.min) obj.min = level;
            else if (level > obj.max) obj.max = level;
            return;
        }
        checkBalanced(node.left, obj, level + 1);
        checkBalanced(node.right, obj, level + 1);
    }

    int checkHeight(Node root) {
        if (root == null) return -1;
        int leftHeight = checkHeight(root.left);
        if (leftHeight == Integer.MIN_VALUE) return Integer.MIN_VALUE;
        int rightHeight = checkHeight(root.right);
        if (rightHeight == Integer.MIN_VALUE) return Integer.MIN_VALUE;
        int heightDiff = leftHeight - rightHeight;
        if (Math.abs(heightDiff) > 1) {
            return Integer.MIN_VALUE;
        } else {
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }

    int getHeight(Node root) {
        if (root == null) return -1;
        return Math.max(getHeight(root.left), getHeight(root.right)) + 1;
    }


    //배열에 inorder 순서대로 담아서 후에 비교로 binary search tree 인지 분간
    boolean isValidateBst1() {
        int[] array = new int[size];
        inorder(root, array);
        for (int i = 1; i < array.length; i++) {
            if (array[i] < array[i - 1]) {
                return false;
            }
        }
        return true;
    }


    boolean isValidateBst2() {
        return isValidateBst2(root);
    }

    Integer lastPrinted = null;

    // 배열없이 값을 담아서 왼쪽값이 클경우 바로 return false
    boolean isValidateBst2(Node n) {
        if (n == null) return true;
        if (!isValidateBst2(n.left)) return false;
        if (lastPrinted != null && n.data < lastPrinted) {
            return false;
        }
        lastPrinted = n.data;
        if (!isValidateBst2(n.right)) return false;
        return true;
    }

    boolean isValidateBst3() {
        return isValidateBst3(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    // 조건을 걸어서 조건이 어긋나면 return false 로 식 종료
    boolean isValidateBst3(Node n, int min, int max) {
        if (n == null) return true;
        if (n.data < min || n.data > max) return false;
        if (!isValidateBst3(n.left, min, n.data) || !isValidateBst3(n.right, n.data, max)) return false;
        return true;
    }

    int index = 0;

    void inorder(Node root, int[] array) {
        if (root != null) {
            inorder(root.left, array);
            array[index] = root.data;
            index++;
            inorder(root.right, array);
        }
    }

    void searchBinaryTree(Node n, int find) {
        if (n.data > find) {
            Util.println(find + " Smaller than " + n.data);
            searchBinaryTree(n.left, find);
        } else if (n.data < find) {
            Util.println(find + " Bigger than " + n.data);
            searchBinaryTree(n.right, find);
        } else {
            Util.println("find data  " + n.data);
        }
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
        \    \    / \
        (3)  (6)(11)(9)
                      \
                      (10)
*/

public class TreeTest {

    public static void main(String[] args) {
        Tree tree = new Tree(10);
//        tree.searchBinaryTree(tree.root, 6);
        Util.println("using inorder validate1 : " + tree.isValidateBst1());
        Util.println("using inorder validate2 : " + tree.isValidateBst2());
        Util.println("using inorder validate3 : " + tree.isValidateBst3());
//        Util.println(tree.isBalanced(tree.root));
//        Util.println(tree.isBalanced2(tree.root));
//        Util.println(tree.isBalanced3(tree.root));
        Tree2 t2 = new Tree2(10);
        t2.findNext(t2.root.left.right.right); //3
        t2.findNext(t2.root.left); //1
        t2.findNext(t2.root); //4
        t2.findNext(t2.root.left.left); //0
    }


}
