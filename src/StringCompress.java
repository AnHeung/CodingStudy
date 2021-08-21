public class StringCompress {

    public static void main(String[] args) {
        Util.println(compressString("aabbbbcccdd"));
        Util.println(compressString("abcd"));
    }

    private static String compressString(String str) {
        String newStr = compress(str);
        return newStr.length() > str.length() ? str : newStr;
    }

    static String compress(String str) {
        int count = 0;
        StringBuilder sb = new StringBuilder(getTotal(str));
        int lastIdx = str.length() - 1;
        for (int i = 0; i < str.length(); i++) {
            count++;
            if (i == lastIdx || str.charAt(i) != str.charAt(i + 1)) {
                sb.append(str.charAt(i));
                sb.append(count);
                count = 0;
            }
        }
        return sb.toString();
    }

    //반환할 문자열 갯수
    private static int getTotal(String str) {
        int count = 0; //같은 문자열 갯수
        int total = 0;
        int lastIdx = str.length() - 1;
        for (int i = 0; i < str.length(); i++) {
            count++;
            if (i == lastIdx || str.charAt(i) != str.charAt(i + 1)) { //맨마지막 문자이거나 다음에 나오는 문자열이 전이랑 다른경우
                total += 1 + String.valueOf(count).length();
                count = 0;
            }
        }
        return total;
    }
}
