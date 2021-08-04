import java.util.NoSuchElementException;

class Queue <T>{

    class Node<T>{
        private T data;
        private Node<T> next;

        public Node(T data) {
            this.data = data;
        }
    }
    private Node<T> first;
    private Node<T> last;

    void add (T item){
        Node<T> t = new Node<>(item);
        if(last!= null){
            last.next = t;
        }
        last = t;
        if(first == null){
            first = last;
        }
    }

     T remove (){
        if(first == null) throw new NoSuchElementException();
         T data = first.data;
         first = first.next;

         if(first == null){
             last = null;
         }
         return data;
     }

     public T peek(){
         if(first == null) throw new NoSuchElementException();
         return first.data;
     }

     boolean isEmpty(){
        return first == null;
     }
}


public class QueueTest {

    public static void main(String[] args) {
        Queue<Integer> q = new Queue<>();
        q.add(1);
        q.add(2);
        q.add(3);
        q.add(4);

        Util.println(q.remove());
        Util.println(q.remove());
        Util.println(q.peek());
        Util.println(q.remove());
        Util.println(q.isEmpty());
        Util.println(q.remove());
        Util.println(q.isEmpty());
    }

}
