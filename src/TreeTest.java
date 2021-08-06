
class Tree {

    Node root;

    static class Node {
        int data;
        Node left;
        Node right;

        public Node(int data) {
            this.data = data;
        }
    }

    public void makeTree(int[] a) {
        root = makeRecursiveTree(a, 0, a.length - 1);
    }

    Node makeRecursiveTree(int[] a, int start, int end) {
        if(start > end) return null;

        int mid = (start + end) / 2;
        Node node = new Node(a[mid]);
        node.left = makeRecursiveTree(a, start , mid - 1);
        node.right = makeRecursiveTree(a, mid + 1 , end);
        return node;
    }

    void searchBinaryTree(Node n, int find) {

        if(n.data > find){
            Util.println(find + " Smaller than " + n.data);
            searchBinaryTree(n.left ,find);
        }else if(n.data < find){
            Util.println(find + " Bigger than " + n.data);
            searchBinaryTree(n.right ,find);
        }else{
            Util.println("find data  " + n.data);
        }
    }
}

public class TreeTest {

    public static void main(String[] args) {
        int[] a = new int[10];
        for (int i = 0; i < 10; i++) {
            a[i] = i;
        }
        Tree tree = new Tree();
        tree.makeTree(a);
        tree.searchBinaryTree(tree.root , 6);
    }


}
