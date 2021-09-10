package test;

import java.util.*;

public class Chapter12 {

    public static void main(String[] args) {
        _12_5();
    }

    static void _12_1() {

        Scanner sc = new Scanner(System.in);

        String lines = sc.nextLine();

        int[] arr = new int[lines.length()];
        int left = lines.length() / 2 + lines.length() % 2 - 1;
        int right = lines.length() - 1;

        for (int i = 0; i < lines.length(); i++) {
            arr[i] = lines.charAt(i) - '0';
        }

        int leftValue = 0;
        int rightValue = 0;

        for (int i = 0; i < lines.length(); i++) {
            if (i <= left) leftValue += arr[i];
            else if (i <= right) rightValue += arr[i];
        }
        System.out.println(leftValue + " " + rightValue);
        System.out.println(leftValue == rightValue ? "LUCKY" : "READY");
    }

    static void _12_2() {

        Scanner sc = new Scanner(System.in);
        String lines = sc.nextLine();
        StringBuilder sb = new StringBuilder();

        char[] test = lines.toCharArray();

        int result = 0;

        Arrays.sort(test);

        for (char c : test) {
            //숫자타입이면 값을 저장해두기
            if (48 <= c && c <= 57) {
                result += c - '0';
            } else {
                //일반 문자타입을 위에서 정렬했으므로 순서대로 나옴
                sb.append(c);
            }
        }

        sb.append(result);
        System.out.println(sb);
    }

    static void _12_3() {

        Scanner sc = new Scanner(System.in);
        String lines = sc.nextLine();

        int[] result = new int[128];

        int length = 1;

        while (length <= lines.length() / 2) {

            int count = 0;
            String prev = "";

            for (int i = 0; i < lines.length(); i += length + length) {

                int endStartIdx = i + length;
                int endIdx = i + (2 * length);
                if (endIdx > lines.length()) {
                    count += (lines.length() - i);
                    break;
                }

                String front = lines.substring(i, endStartIdx);
                String end = lines.substring(endStartIdx, endIdx);

                if (prev.equals(front)) {
                    count++;
                } else {
                    if (front.equals(end)) {
                        count += length + 1;
                    } else {
                        count += 2 * length;
                    }
                }
                prev = end;
            }
            result[length] = count;
            length++;
        }

        int min = 0;
        int num = 0;

        for (int i = 0; i < result.length; i++) {
            if (result[i] != 0) {
                if (result[i] < min || min == 0) {
                    min = result[i];
                    num = i;
                }
            }
        }
        System.out.println("최대값  : " + min + " 자르는 문자열 단위 : " + num);
    }

    static void _12_4() {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        int[][] keyArr = new int[m][m];
        int[][] lockArr = new int[n][n];

        sc.nextLine();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                keyArr[i][j] = sc.nextInt();
            }
        }

//        sc.nextLine();
//
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                lockArr[i][j] = sc.nextInt();
//            }
//        }

        /*
        00 01 02
        11 12 13
        21 22 23
         */

        keyArr = rotate(keyArr);

        System.out.println();
    }

    static void _12_5(){
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int k = sc.nextInt();

        int[][] map = new int[n+1][n+1];

        sc.nextLine();

        for(int i = 0; i < k; i++){
            int a = sc.nextInt();
            int b = sc.nextInt();
            map[a][b] = 1;
        }
        int l = sc.nextInt();

        sc.nextLine();

        for(int i = 0 ; i < l; i++){
            String lines = sc.nextLine();
            int position = Integer.parseInt(lines.split(" ")[0]);
            String direction = lines.split(" ")[1];
           findMap(map, position, direction);
        }
    }

    static void findMap(int [][] map , int time , String direction){
        int row = 1;
        int col = 1;
        int count =0 ;
        int size = 1;
        int start = 1;
        int end = 1;

        String currentDirection = "R";


        if("R".equals(currentDirection)){
            col+=1;
        }else if("L".equals(currentDirection)){
            col-=1;
        }else if("N".equals(currentDirection)){
            row+=1;
        }else if("S".equals(currentDirection)){
            row-=1;
        }

        if(time == count){

        }


    }

    static int[][] rotate(int[][] arr) {
        int n = arr.length;
        int m = arr[0].length;
        int[][] rotate = new int[m][n];

        for (int i = 0; i < rotate.length; i++) {
            for (int j = 0; j < rotate[i].length; j++) {
                rotate[i][j] = arr[n-1-j][i];
            }
        }

        return rotate;
    }
}
