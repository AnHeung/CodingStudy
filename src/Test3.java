public class Test3 {

    Ref ref;

    static class Ref{
        int data = 1;
        int data2 = 2;
        String data3 = "3";
        Ref ref;
    }

    static  class Test2{
        int data = 1;
        int data2 = 2;
        String data3 = "3";
        Object object;

        public Test2(int data, int data2, String data3, Object object) {
            this.data = data;
            this.data2 = data2;
            this.data3 = data3;
            this.object = object;
        }
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

    private static void swap(Test2 test1 , Test2 test2){

        Object temp;	// 임시 변수
        // 자리 바꾸기
        temp = test1.object;
        test1.object = test2.object;
        test2.object = temp;

    }

    public static void main(String[] args) {

//        Test3 test = new Test3();
//        test.refTest();
        Test2 test1 = new Test2(1,2,"3" , new Object());
        Test2 test2 = new Test2(4,5,"6", new Object());

        swap(test1,test2);
        System.out.println(test1.data);

    }
}
