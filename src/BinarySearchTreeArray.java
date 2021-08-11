import java.util.ArrayList;
import java.util.LinkedList;

public class BinarySearchTreeArray {

    public static void main(String[] args){
        Tree3 t = new Tree3(5);
        ArrayList<LinkedList<Integer>> result = allSequences(t.root);
        for(LinkedList<Integer> l : result){
            for(int data  : l){
                System.out.print(data);
            }
            System.out.println();
        }
    }

    /*
        2
       / \
      0   3
       \   \
        1   4

     */

    static ArrayList<LinkedList<Integer>> allSequences(Tree3.Node node) {

        ArrayList<LinkedList<Integer>> result = new ArrayList<>();

        if (node == null) {
            result.add(new LinkedList<>());
            return result;
        }
        LinkedList<Integer> prefix = new LinkedList<>();
        prefix.add(node.data);

        ArrayList<LinkedList<Integer>> leftSeq = allSequences(node.left);
        ArrayList<LinkedList<Integer>> rightSeq = allSequences(node.right);
        for (LinkedList<Integer> left : leftSeq) {
            for (LinkedList<Integer> right : rightSeq) {
                ArrayList<LinkedList<Integer>> weaved = new ArrayList<>();
                weaveList(left, right , weaved , prefix);
                result.addAll(weaved);
            }
        }
        return result;
    }

    static void weaveList(LinkedList<Integer> first, LinkedList<Integer> second, ArrayList<LinkedList<Integer>> results, LinkedList<Integer> prefix) {
        //더이상 선택의 여지가 없는경우
        if(first.size() == 0 || second.size() == 0){
            LinkedList<Integer> result = new LinkedList<>(prefix);
            result.addAll(first);
            result.addAll(second);
            results.add(result);
            return;
        }
        int headFirst = first.removeFirst();
        prefix.addLast(headFirst);
        weaveList(first, second, results, prefix);
        prefix.removeLast();
        first.addFirst(headFirst);

        int headSecond = second.removeFirst();
        prefix.addLast(headSecond);
        weaveList(first, second, results, prefix);
        prefix.removeLast();
        second.addFirst(headSecond);
    }
}
