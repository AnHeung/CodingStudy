public class ReverseOneWayLinkedList {

    //재귀함수를 이용한 풀이
    private static int KthToLastToRecursive(LinkedList.Node n, int k) {
        if (n == null) return 0;

        int count = KthToLastToRecursive(n.next, k) + 1;
        if (count == k) {
            System.out.println(k + "th to last node is " + n.data);
        }
        return count;
    }

    static class Reference {
        public int count = 0;
    }


    //참조객체를 파라미터로 받아 재귀함수로 푸는방법
    private static LinkedList.Node getKthToLastNodeToRecursive(LinkedList.Node n, int k, Reference reference) {
        if (n == null) return null;

        LinkedList.Node found = getKthToLastNodeToRecursive(n.next, k, reference);
        reference.count++;
        if (reference.count == k) {
            return n;
        }
        return found;
    }

    //포인터를 이용해 푸는방법
    private static LinkedList.Node getKthToLastNodeUsePointer(LinkedList.Node first, int k) {

        LinkedList.Node p1 = first;
        LinkedList.Node p2 = first;

        for (int i = 0; i < k; i++) {
            if (p1 == null) return null;
            p1 = p1.next;
        }

        while (p1 != null) {
            p1 = p1.next;
            p2 = p2.next;
        }

        return p2;
    }


    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list.append(2);
        list.append(2);
        list.append(3);
        list.append(4);
        list.append(7);
        list.append(6);
        list.removeDups();
//        list.retrieve();
        int k = 2;
//        int last = KthToLastToRecursive(list.header , k);
//        Reference reference = new Reference();
//        LinkedList.Node lastNode = getKthToLastNodeToRecursive(list.header, k, reference);
        LinkedList.Node lastNode = getKthToLastNodeUsePointer(list.header, k);
        System.out.println(lastNode.data);
    }

}