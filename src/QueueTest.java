import java.util.NoSuchElementException;

class Queue<T> {

    class Node<T> {
        private T data;
        private Node<T> next;

        public Node(T data) {
            this.data = data;
        }
    }

    private Node<T> first;
    private Node<T> last;

    void enqueue(T item) {
        Node<T> t = new Node<>(item);
        if (last != null) {
            last.next = t;
        }
        last = t;
        if (first == null) {
            first = last;
        }
    }

    T dequeue() {
        if (first == null) throw new NoSuchElementException();
        T data = first.data;
        first = first.next;

        if (first == null) {
            last = null;
        }
        return data;
    }

    public T peek() {
        if (first == null) throw new NoSuchElementException();
        return first.data;
    }

    boolean isEmpty() {
        return first == null;
    }
}


public class QueueTest {

    public static void main(String[] args) {
        Queue<Integer> q = new Queue<>();
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        q.enqueue(4);

        Util.println(q.dequeue());
        Util.println(q.dequeue());
        Util.println(q.peek());
        Util.println(q.dequeue());
        Util.println(q.isEmpty());
        Util.println(q.dequeue());
        Util.println(q.isEmpty());
    }

}
