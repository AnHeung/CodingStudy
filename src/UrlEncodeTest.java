public class UrlEncodeTest {

    public static void main(String[] args) {
        Util.println(URLify("Mr John Smith So S         " , 18));
    }

    private static String URLify (String str, int len){
        return URLify(str.toCharArray() , len);
    }

    private static String URLify(char[] str , int len){
        int spaces = 0;
        //공백 갯수 세기
        for(int i = 0 ; i < len; i++){
            if(str[i] == ' ') spaces++;
        }
        // 공백당 글자칸이 2칸씩 더 필요하므로 총 3을 곱함 그리고 인덱스 번호이므로 1을 뺌
        int index = len - spaces + (spaces * 3) - 1; // >> len - (2 * spaces)-1

        for(int i = len -1 ; i >= 0; i--){
            if(str[i] == ' '){
                str[index--] = '0';
                str[index--] = '2';
                str[index--] = '%';
            }else{
                str[index--] = str[i];
            }
        }
        return new String(str);
    }
}
