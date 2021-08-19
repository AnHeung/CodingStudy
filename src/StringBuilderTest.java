public class StringBuilderTest {

    private char[] value;
    private int size;
    private int index;

    public StringBuilderTest() {
        size = 1;
        value = new char[size];
        index = 0;
    }

    public void append (String str){
        if(str == null) str = "";
        int len = str.length();
        ensureCapacity(len);
        for(int i = 0 ; i < str.length(); i++){
            value[index++] = str.charAt(i);
        }
        Util.println(" *** " + size + " , " + index);
    }

    //시작전 넉넉하게 배열방 크기 늘리기
    void ensureCapacity(int len) {
        if(index + len > size){
            size = (size + len) * 2;
            char[] newValue = new char[size];
            for(int i = 0; i < value.length; i++){
                newValue[i] = value[i];
            }
            value = newValue;
        }
    }

    public String toString(){
        return new String(value, 0, index);
    }

    public static void main(String[] args) {

        StringBuilderTest sb = new StringBuilderTest();
        sb.append("ku");
        sb.append("ma");
        sb.append(" good ");
        Util.println(sb.toString());
    }
}

