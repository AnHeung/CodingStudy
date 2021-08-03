class Storage {
    int carry = 0;
    LinkedList.Node result = null;
}

class LinkedList {
    Node header;

    static class Node {
        int data;
        Node next;
    }

    public LinkedList() {
        header = new Node();
    }

    void append(int data) {
        Node end = new Node();
        end.data = data;
        Node n = header;
        while (n.next != null) {
            n = n.next;
        }
        n.next = end;
    }

    void delete(int data) {
        Node n = header;
        while (n.next != null) {
            if (n.next.data == data) {
                n.next = n.next.next;
            } else {
                n = n.next;
            }
        }
    }

    boolean deleteNode(Node n) {
        if (n == null || n.next == null) return false;

        Node next = n.next;
        n.data = next.data;
        n.next = next.next;
        return true;

    }

    Node getNode(int num) {
        int count = 0;
        Node n = header;

        while (n != null && n.next != null) {
            if (num == count) return n;
            n = n.next;
            count++;
        }
        return n;
    }


    //버퍼 공간을 둿으면 hashSet을 설정해 쉽게 할수 있지만 버퍼공간을 주지 않고 작업시 해당처럼 로직을 짠다.
    void removeDups() {
        //최초 header 값으로 참조
        Node n = header;
        while (n != null && n.next != null) {
            //r 값은 n.next 로 변경된 값으로 계속 루틴
            Node r = n;
            while (r.next != null) {
                if (n.data == r.next.data) {
                    //중복이 있을경우 해당 참조에 대한 전체값을 바꿈 (header, n ,r 전부)
                    r.next = r.next.next;
                } else {
                    //r 값을 assign 해서 다음 node 값으로 루틴
                    r = r.next;
                }
            }
            n = n.next;
        }
    }

    void retrieve() {
        Node n = header.next;
        while (n.next != null) {
            System.out.print(n.data + " -> ");
            n = n.next;
        }
        System.out.println(n.data);
    }

}


public class UpgradeOneWayLinkedList {

    public static void main(String[] args) {
//        LinkedList list = new LinkedList();
//        list.append(2);
//        list.append(2);
//        list.append(3);
//        list.append(4);
//        list.removeDups();
//        list.retrieve();
//        list.deleteNode(list.header.next.next);
//        list.retrieve();

        LinkedList l1 = new LinkedList();
        LinkedList l2 = new LinkedList();

        l1.append(5);
        l1.append(7);
        l1.append(3);
        l1.retrieve();

        l2.append(5);
        l2.append(4);
        l2.append(5);
        l2.retrieve();
        LinkedList.Node n = sumLists(l1.getNode(1), l2.getNode(1));
        print(n);

    }

    private static void print(LinkedList.Node n) {
        while (n != null && n.next != null) {
            System.out.print(n.data + " -> ");
            n = n.next;
        }
        System.out.println(n.data);
    }

    //carry 를 파라미터 값으로 던져서 뒤에서 부터 뽑아내는 로직
    private static LinkedList.Node sumLists(LinkedList.Node l1, LinkedList.Node l2, int carry) {
        if (l1 == null && l2 == null && carry == 0) return null;

        LinkedList.Node result = new LinkedList.Node();
        int value = carry;
        if (l1 != null) value += l1.data;
        if (l2 != null) value += l2.data;

        result.data = value % 10;

        result.next = sumLists(l1 == null ? null : l1.next,
                l2 == null ? null : l2.next,
                value >= 10 ? 1 : 0);

        return result;

    }

    private static LinkedList.Node sumLists(LinkedList.Node l1, LinkedList.Node l2) {
        int len1 = getListLength(l1);
        int len2 = getListLength(l2);

        if (len1 > len2) l1 = LPadList(l1, len1 - len2);
        if (len2 > len1) l2 = LPadList(l1, len2 - len1);

        Storage storage = addLists(l1, l2);
        if(storage.carry > 0){
            storage.result = insertBefore(storage.result , 1);
        }
        return storage.result;
    }


    private static Storage addLists(LinkedList.Node l1, LinkedList.Node l2){
        if(l1 == null && l2 == null) return new Storage();

        Storage storage = addLists(l1.next, l2.next);
        int value = storage.carry + l1.data + l2.data;
        int data = value % 10;
        int carry = value / 10;

        storage.carry = carry;
        storage.result = insertBefore(storage.result , data);

        return storage;
    }


    private static int getListLength(LinkedList.Node l) {
        int count = 0;

        while (l != null) {
            count++;
            l = l.next;
        }
        return count;
    }



    //길이 만큼 돌면서 노드에 0을 채우주는 함수
    private static LinkedList.Node LPadList(LinkedList.Node l, int length) {

        int zero = 0;

        for (int i = 0; i < length; i++) {
            l = insertBefore(l , zero);
        }
        return l;
    }

    //앞 노드에 값 집어넣기
    private static LinkedList.Node insertBefore(LinkedList.Node n, int data) {

        LinkedList.Node before = new LinkedList.Node();
        before.data = data;

        if (n != null) before.next = n;

        return before;
    }


//    private static LinkedList.Node LPadList (LinkedList.Node l , int length){
//        LinkedList.Node head = l;
//        int zero = 0;
//
//        for(int i = 0 ; i < length; i++){
//            head = insertBefore(head ,zero);
//        }
//
//        return head;
//    }
}





