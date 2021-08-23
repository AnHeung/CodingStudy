import java.util.Scanner;

public class TileMake {

    int [] d = new int[1001];
    int result = 0;

    public static void main (String [] args){

        TileMake t = new TileMake();
        Scanner scanner = new Scanner(System.in);
        int nextInt = scanner.nextInt();
        Util.println(t.dp2(nextInt));

    }

    int dp(int x){
        if(x == 1) return 1;
        if(x == 2) return 3;
        if(d[x] != 0) return d[x];
        return d[x] = (dp(x-1) + 2 * dp(x-2)) % 10007;
    }

    int dp2(int x){
        if(x == 0) return 1;
        if(x == 1) return 0;
        if(x == 2) return 3;
        if(d[x] != 0) return d[x];
        int result = 3 * dp2(x-2);
        for(int i = 3; i <= x; i++){
            if(i % 2  == 0) result += 2 * dp2(x-i);
        }
        return d[x] = result;
    }

    int _18452(int x){
        if(x == 0) return 1;
        if(x == 1) return 2;
        if(x == 2) return 7;

        if(d[x] != 0) return d[x];
        result = 2 * _18452(x-1) + 3 * _18452(x-2);
        for(int i = 3; i <= x; i++ ){
            result += 2 * _18452(x-i) % 1000000007;
        }
        return d[x] = result % 1000000007;
    }

}
