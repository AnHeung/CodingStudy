//회문 (Palindrome) mom , racecar 앞뒤가 같음
//치환 (Permutation) mmo , omm 글자 구성이 같음 (섞여있어도)
//회문을 만들수 있는 조건은 문자를 딱 반으로 나눳을때 모든 문자갯수가 짝수이면 됨 단 가운데 한글자는 홀수일 수 있다.

public class StringPalindrome {
    public static void main(String[] args) {
        Util.println(isPermutationOfPalindrome2("aa bb cc dd")); //문자열 갯수가 짝수
        Util.println(isPermutationOfPalindrome2("aa bb cc dd e")); //홀수인경우 하나
        Util.println(isPermutationOfPalindrome2("aa bb cc dd e fff")); //홀수인경우 두개
    }

    //o(n)의 시간복잡도
    private static boolean isPermutationOfPalindrome(String s) {
        int[] table = buildCharFrequencyTable(s);
        return checkMaxOneOdd(table);
    }

    //좀더 효울적으로 개선한 코드
    private static boolean isPermutationOfPalindrome2(String s) {
        int countOdd = 0;
        int[] table = new int[Character.getNumericValue('z') - Character.getNumericValue('a') + 1];
        for (char c : s.toCharArray()) {
            int x = getCharNumber(c);
            if (x != -1) {
                table[x]++;
                if (table[x] % 2 == 1) {
                    countOdd++;
                } else {
                    countOdd--;
                }
            }
        }
        //홀수인경우 홀수 카운터 추가를 해서 최종적으로 홀수면 false (하나의 홀수 까진 허용하므로 <=1 조건)
        return countOdd <= 1;
    }

    //알파벳 크기만큼의 인트 배열에 담아 해당 알파벳이 유효하면 값을 추가해 예를 들어 aa bb 는 [2,2,0,0,.....0] 이런식으로 만듬
    private static int[] buildCharFrequencyTable(String s) {
        //알파벳 갯수 만큼의 인트배열 생성
        int[] table = new int[Character.getNumericValue('z') - Character.getNumericValue('a') + 1];
        for (char c : s.toCharArray()) {
            int x = getCharNumber(c);
            if (x != -1) table[x]++;
        }
        return table;
    }

    //아스키 숫자값 a~z를 0~26으로 바꿔주는 함수
    private static int getCharNumber(Character c) {
        int a = Character.getNumericValue('a');
        int z = Character.getNumericValue('z');
        int val = Character.getNumericValue(c);

        if (a <= val && val <= z) {
            return val - a;
        }
        return -1;
    }

    private static boolean checkMaxOneOdd(int[] table) {
        boolean foundOdd = false;
        for (int count : table) {
            //문자열을 갯수가 홀수면 회문을 만들수 없으므로 false 딱 하나만 있는 경우는 회문의 경우에 포함되므로 true로 바꾼뒤 그뒤에서도 홀수가 나오면 false
            if (count % 2 == 1) {
                if (!foundOdd) foundOdd = true;
                else return false;
            }
        }
        return true;
    }
}
