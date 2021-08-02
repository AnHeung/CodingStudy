public class Test3 {

    Ref ref;

    static class Ref{
        int data = 1;
        int data2 = 2;
        String data3 = "3";
        Ref ref;
    }

    public Test3() {
        ref = new Ref();
    }

    void refTest(){
        Ref ref1 = ref;
        Ref ref2 = ref1;
        ref1.ref = new Ref();
        ref2 = ref1.ref;
        ref1.ref.data2 = 10;
        ref1.ref.data = 11;
        ref1.ref.ref = new Ref();
        ref1.ref.ref.data3 ="20";

        System.out.print("test");
    }

    public static void main(String[] args) {

        Test3 test = new Test3();
        test.refTest();


    }
}
