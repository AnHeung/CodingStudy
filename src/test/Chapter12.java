package test;

import java.util.*;

public class Chapter12 {

    public static void main(String[] args) {
        _12_4();
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

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                lockArr[i][j] = sc.nextInt();
            }
        }

        _12_4_solution(keyArr,lockArr);

    }

    private static boolean _12_4_solution (int[][] key , int [][] lock){
        int n = lock.length;
        int m = key.length;

        int[][] newLock = new int[n*3][n*3];

        for(int i = 0 ; i < n; i++){
            for(int j = 0; j <n; j++){
                newLock[i+n][j+n] = lock[i][j];
            }
        }

        for(int rotation = 0 ; rotation < 4; rotation++){
            key = _12_4_rotate(key); //열쇠 회전
            for(int x = 0 ; x< n*2; x++){
                for(int y =0; y < n*2; y++){

                    /*
                    3*3 배열이 y값이 증가함에 따라 한칸씩 이동하게 된다(동쪽으로)
                    그러다 y값이 다 끝나고 나면 x 값이 증가함에 따라 한줄내려가서(남쪽) 다시 동쪽으로 이동하면서 3*3 배열값을 만들며
                    비교한다. 그리고 다 비교하고 나면 다시 90도 돌려서 축을 바꾼다음 다시 위에 과정을 반복한다.
                     */
                    for(int i = 0; i < m; i++){
                        for(int j = 0; j < m; j++){
                            newLock[x+i][y+j] += key[i][j];
                        }
                    }
                    if(_12_4_checkLock(newLock)) return true;
                    for(int i = 0; i < m; i++){
                        for(int j = 0 ; j < m; j++){
                            newLock[x+i][y+j] -= key[i][j];
                        }
                    }
                }
            }
        }
        return false;
    }

    private static boolean _12_4_checkLock(int[][] newLock) {
        int lockLength = newLock.length / 3;
        for (int i = lockLength; i < lockLength * 2; i++) {
            for (int j = lockLength; j < lockLength * 2; j++) {
                if (newLock[i][j] != 1) {
                    return false;
                }
            }
        }
        return true;
    }

    static int[][] _12_4_rotate(int[][] arr) {
        int n = arr.length;
        int m = arr[0].length;
        int[][] rotate = new int[m][n];

        for (int i = 0; i < rotate.length; i++) {
            for (int j = 0; j < rotate[i].length; j++) {
                rotate[i][j] = arr[n - 1 - j][i];
            }
        }

        return rotate;
    }

    static void _12_5() {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int k = sc.nextInt();

        //방향 회전 정보
        ArrayList<Node2> info = new ArrayList<>();

        int[][] map = new int[n + 1][n + 1];
        sc.nextLine();

        for (int i = 0; i < k; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            map[a][b] = 1;
        }
        int l = sc.nextInt();

        for (int i = 0; i < l; i++) {
            int x = sc.nextInt();
            char c = sc.next().charAt(0);
            info.add(new Node2(x, c));
        }

        System.out.println(simulate(info, map, n , l));
    }

    private static int simulate(ArrayList<Node2> info, int[][] map , int n , int l) {
        //동남서북 (0,1) 동 , (1,0) 남 , (0,-1) 서 , (-1,0) 북
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};

        int x = 1, y = 1; //뱀시작위치
        map[x][y] = 2; //뱀이존재하는 위치는 2로 지정
        int direction = 0; // 0은 동쪽
        int time = 0;
        int index =0; // 다음 회전정보 (info index 값)
        Queue<Position> q = new LinkedList<>();
        q.offer(new Position(x,y));

        while(!q.isEmpty()){
            int nx = x + dx[direction];
            int ny = y + dy[direction];
            //뱀이 범위안에 있고 범이 몸통이 없는 위치
            if(1 <= nx && nx <= n && 1<=ny && ny <= n && map[nx][ny] != 2){
                //사과가 없을때 이동후 꼬리 제거
                if(map[nx][ny] ==0){
                    map[nx][ny] = 2;
                    q.offer(new Position(nx, ny));
                    Position prev = q.poll();
                    map[prev.getX()][prev.getY()] = 0;
                }
                //사과 존재시 이동후 꼬리 유지
                if(map[nx][ny] == 1){
                    map[nx][ny] = 2;
                    q.offer(new Position(nx,ny));
                }
            }else{
                time++;
                break;
            }

            x = nx;
            y = ny;
            time++;
            //회전할 시간일떄
            if(index < l && time == info.get(index).getTime()){
                direction = turn(direction , info.get(index).getDirection());
                index++;
            }
        }
        return time;
    }

    /*
      L 이라는건 90도 반시계회전 동->북->서->남 순으로 돌고 dx ={0,1,0,-1} dy = {1,0,-1,0} 이므로 해당 direction 값 index 로
      접근 하면 현위치에서 동쪽(0,1) 을 더하고 남쪽 (1,0)을 더하면 됨. 단 direction 이 0 값 (즉 동쪽일때는 다시 뒤로 돌아가서
      3번 인덱스인 북으로 가게 됨  동->북
      R 이라는건 90도 회전 즉 동->남->서->북 순으로 돌고 위와 같은 조건으로 direction 값에 맞는 index 를 찾아가면됨.
      시계방향으로 90도 돌때 %4 를 하는 이유는 값이 3 일떄 다시 처음으로 가면 %4 를 해서 0으로 초기화(동쪽) 위함
      북->동
     */
    static int turn(int direction, char c) {
        if (c == 'L') direction = (direction == 0) ? 3 : direction - 1;
        else direction = (direction + 1) % 4;
        return direction;
    }
}

//뱀의 위치값 저장할 클래스
class Position {
    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}

class Node2 {
    private int time;
    private char direction;

    public Node2(int time, char direction) {
        this.time = time;
        this.direction = direction;
    }

    public int getTime() {
        return time;
    }

    public char getDirection() {
        return direction;
    }
}