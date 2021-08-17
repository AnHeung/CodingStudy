class Tree4{

    class Node{

        int data;
        Node left;
        Node right;

        public Node(int data) {
            this.data = data;
        }
    }


    Node root;

    Node makeBst(int start , int end){
        if(start > end ) return null;
        int mid  = (start + end) / 2;
        Node node = new Node(mid);
        node.left = makeBst(start , mid-1);
        node.right = makeBst(mid+1 , end);
        return node;
    }

    boolean containsTrees(Node t1 , Node t2){
        if(t2 == null) return true;
        return subTree(t1,t2);
    }

    //root 값 같은거 찾는 함수 ( preorder 로 순회 root -> left -> right )
     boolean subTree(Node t1, Node t2) {
        if(t1 == null) return false;
        else if(t1.data == t2.data && matchTree(t1,t2)) return true;
        return subTree(t1.left,t2) || subTree(t1.right , t2);
    }
     //루트가 같으면 트리비교를 통해 두 구조가 같은지 찾는함수
     boolean matchTree(Node t1, Node t2) {
        if(t1 == null && t2 == null) return true; //둘다 null 이 나왔다는건 값이 일치했다는것
        else if(t1 == null || t2 == null) return false;  //둘중하나만 null 이라는건 값이 일치하지 않았다는것
        else if(t1.data != t2.data) return false; // 둘다 null 이 아니라도 값이 일치하지 않으면 일치하지 않는것
        else return matchTree(t1.left , t2.left) && matchTree(t1.right, t2.right);
        /*
        왼쪽부터 다 돌고 결국 다 돌아 true 값이 나오면 일치 만약 노드값 7을 예로 들면 왼쪽 5돌고 다시 거기서 right 6돌고 해당값 다 빠져나와도
        조건이 matchTree(t1.left , t2.left) && matchTree(t1.right, t2.right) 이므로 오른쪽 탐색을 시작함 (즉 왼오 둘다 true >> null 나와서 탈출할때 까지 다 검사)
         */
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


public class SubTreeCheck {

    public  static void main(String[] args){
        Tree4 t1 = new Tree4();
        Tree4 t2 = new Tree4();

        t1.root = t1.makeBst(0,9);
        t2.root = t1.makeBst(5,9);
        boolean result = t1.containsTrees(t1.root, t2.root);
        Util.println(result);

        t2.root = t2.makeBst(7,9);
        result = t1.containsTrees(t1.root ,t2.root);
        Util.println(result);
    }
}
