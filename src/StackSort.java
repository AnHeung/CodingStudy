public class StackSort {


    public static void main(String[] args) {

        Stack<Integer> s1 = new Stack<>();
        s1.push(4);
        s1.push(6);
        s1.push(3);
        s1.push(2);
        s1.push(8);
        sort(s1);
        System.out.println(s1.pop());
        System.out.println(s1.pop());
        System.out.println(s1.pop());
        System.out.println(s1.pop());
        System.out.println(s1.pop());
    }

    private static void sort (Stack<Integer> s1) {

        Stack<Integer> s2 = new Stack<>();

        while (!s1.isEmpty()){
            int temp = s1.pop();
            while(!s2.isEmpty() && s2.peek() > temp){
                s1.push(s2.pop());
            }
            s2.push(temp);
        }

        while(!s2.isEmpty()){
            s1.push(s2.pop());
        }
    }
}
