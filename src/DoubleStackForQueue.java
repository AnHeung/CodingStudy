class MyQueue<T> {

    Stack<T> stackNew , stackOld;

    public MyQueue() {
        stackNew = new Stack<>();
        stackOld = new Stack<>();
    }

    public void add(T value){
        stackNew.push(value);
    }

    private void shiftStacks(){
        if(stackOld.isEmpty()){
            while(!stackNew.isEmpty()){
                stackOld.push(stackNew.pop());
            }
        }
    }

    public T peek(){
        shiftStacks();
        return stackOld.peek();
    }

    public T remove(){
        shiftStacks();
        return stackOld.pop();
    }
}


public class DoubleStackForQueue {


    public static void main(String[] args) {
        MyQueue<Integer> q = new MyQueue<>();
        q.add(1);
        q.add(2);
        q.add(3);
        Util.println(q.remove());
        q.add(4);
        Util.println(q.remove());
        Util.println(q.peek());
        Util.println(q.remove());
    }

}
