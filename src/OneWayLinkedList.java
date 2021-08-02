class Node1 {
    int data;
    Node1 next;

    public Node1(int data) {
        this.data = data;
    }

    void append(int data) {
        Node1 end = new Node1(data);
        Node1 n = this;
        while (n.next != null) {
            n = n.next;
        }
        n.next = end;
    }

    void delete(int data) {
        Node1 n = this;
        while (n.next != null) {
            if (n.next.data == data) {
                n.next = n.next.next;
            }else{
                n = n.next;
            }
        }
    }

    void retrieve() {
        Node1 n = this;
        while (n.next != null) {
            System.out.print(n.data + " -> ");
            n = n.next;
        }
        System.out.print(n.data);
    }
}

public class OneWayLinkedList {

    public static void main(String[] args) {
        Node1 head = new Node1(1);
        head.append(2);
        head.append(3);
        head.append(4);
        head.delete(2);
        head.retrieve();
    }
}



