import java.util.EmptyStackException;

class Stack<T> {

    private Node<T> top;

    class Node<T> {
        T data;
        Node<T> next;

        public Node(T data) {
            this.data = data;
        }
    }

    T pop() {
        if (top == null) throw new EmptyStackException();
        T item = top.data;
        top = top.next;
        return item;
    }

    T peek(){
        if(top == null) throw new EmptyStackException();
        return top.data;
    }

    void push(T item){
        Node<T> node = new Node<>(item);
        node.next = top;
        top = node;
    }

    boolean isEmpty(){
        return top == null;
    }
}

public class StackTest {

    public static void main(String[] args) {
        Stack<Integer> s = new Stack<>();
        s.push(1);
        s.push(2);
        s.push(3);
        s.push(4);
        Util.println(s.pop());
        Util.println(s.pop());
        Util.println(s.peek());
        Util.println(s.pop());
        Util.println(s.pop());
        Util.println(s.isEmpty());
    }
}
