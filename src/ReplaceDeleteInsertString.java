public class ReplaceDeleteInsertString {

    public static void main(String[] args) {

        Util.println(isOneWay("pal","pale"));
        Util.println(isOneWay("pale","pal"));
        Util.println(isOneWay("pale","bale"));

        Util.println(isOneWay("pal","pales"));
        Util.println(isOneWay("pale","pel"));
        Util.println(isOneWay("pale","bake"));
    }

    //string 을 한번만 바꿧는지 체크
    private static boolean isOneWay(String s1, String s2) {

        String longStr , shortStr;

        //길이가 길고 짧은거 부터 분류
        if(s1.length() > s2.length()){
            longStr = s1;
            shortStr =s2;
        }else{
            longStr = s2;
            shortStr =s1;
        }

        //길이차가 2이상 난다는건 두번이상 수정이 이루어졌다는뜼
        if(longStr.length() - shortStr.length() > 1) return false;

        //변화가 한번이라도 있으면 true 로 변경
        boolean isChange = false;

        for(int i = 0 , j = 0; i < shortStr.length(); i++ , j++){

            if(shortStr.charAt(i) != longStr.charAt(j)){
                if(isChange) return false;
                isChange = true;
                if(longStr.length() != shortStr.length()){
                    j++;
                }
            }
        }
        return true;
    }
}
