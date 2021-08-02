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

    //버퍼 공간을 둿으면 hashSet을 설정해 쉽게 할수 있지만 버퍼공간을 주지 않고 작업시 해당처럼 로직을 짠다.
    void removeDups() {
         //최초 header 값으로 참조
        Node n = header;
        while (n!= null && n.next != null) {
            //r 값은 n.next 로 변경된 값으로 계속 루틴
            Node r = n;
            while (r.next != null) {
                if(n.data == r.next.data){
                    //중복이 있을경우 해당 참조에 대한 전체값을 바꿈 (header, n ,r 전부)
                    r.next = r.next.next;
                }else{
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
        System.out.print(n.data);
    }

}


public class UpgradeOneWayLinkedList {

    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list.append(2);
        list.append(2);
        list.append(3);
        list.append(4);
        list.removeDups();
        list.retrieve();
    }


}





