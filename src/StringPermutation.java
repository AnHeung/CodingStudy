import java.util.Arrays;

public class StringPermutation {

    public static void main(String[] args) {
        Util.println(permutation2("ABC", "BCA"));
        Util.println(permutation2("ABC", "BDA"));
    }

    private static String sort(String s) {
        char[] content = s.toCharArray();
        Arrays.sort(content);
        return new String(content);
    }

    //일반적인 함수를 써서 푸는 치환 (정렬하고 비교)
    private static boolean permutation(String s, String t) {
        if (s.length() != t.length()) return false;
        return sort(s).equals(sort(t));
    }

    //아스키 문자열 128개로 가정하고 풀기
    private static boolean permutation2(String s, String t) {
        if (s.length() != t.length()) return false;
        int[] letters = new int[128];
        //128개의 int 배열을 만들어서 해당문자값에 해당하는 인덱스에 담음 예를 들어 A는 65 -> int Array 65번째 배열에 ++를 해서 값 1이 들어감
        for (int i = 0; i < s.length(); i++) {
            letters[s.charAt(i)]++;
        }
        //반대로 배열에 담았던 값을 뺀다 만약 같은 문자열이라면 이미 1이라는 값이 차있기 때문에 구지 손볼 필요없지만
        //없다면 0에서 --로 1이 빠져서 -1이 나오므로 음수가 나오면서 false 를 반환
        for (int i = 0; i < t.length(); i++) {
            letters[t.charAt(i)]--;
            if (letters[t.charAt(i)] < 0) return false;
        }
        return true;
    }
}
