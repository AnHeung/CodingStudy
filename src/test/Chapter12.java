package test;

import java.util.*;

public class Chapter12 {

    public static void main(String[] args) {
        _12_8();
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

        _12_4_solution(keyArr, lockArr);

    }

    private static boolean _12_4_solution(int[][] key, int[][] lock) {
        int n = lock.length;
        int m = key.length;

        int[][] newLock = new int[n * 3][n * 3];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                newLock[i + n][j + n] = lock[i][j];
            }
        }

        for (int rotation = 0; rotation < 4; rotation++) {
            key = _12_4_rotate(key); //열쇠 회전
            for (int x = 0; x < n * 2; x++) {
                for (int y = 0; y < n * 2; y++) {

                    /*
                    3*3 배열이 y값이 증가함에 따라 한칸씩 이동하게 된다(동쪽으로)
                    그러다 y값이 다 끝나고 나면 x 값이 증가함에 따라 한줄내려가서(남쪽) 다시 동쪽으로 이동하면서 3*3 배열값을 만들며
                    비교한다. 그리고 다 비교하고 나면 다시 90도 돌려서 축을 바꾼다음 다시 위에 과정을 반복한다.
                     */
                    for (int i = 0; i < m; i++) {
                        for (int j = 0; j < m; j++) {
                            newLock[x + i][y + j] += key[i][j];
                        }
                    }
                    if (_12_4_checkLock(newLock)) return true;
                    for (int i = 0; i < m; i++) {
                        for (int j = 0; j < m; j++) {
                            newLock[x + i][y + j] -= key[i][j];
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

        System.out.println(simulate(info, map, n, l));
    }

    private static int simulate(ArrayList<Node2> info, int[][] map, int n, int l) {
        //동남서북 (0,1) 동 , (1,0) 남 , (0,-1) 서 , (-1,0) 북
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};

        int x = 1, y = 1; //뱀시작위치
        map[x][y] = 2; //뱀이존재하는 위치는 2로 지정
        int direction = 0; // 0은 동쪽
        int time = 0;
        int index = 0; // 다음 회전정보 (info index 값)
        Queue<Position> q = new LinkedList<>();
        q.offer(new Position(x, y));

        while (!q.isEmpty()) {
            int nx = x + dx[direction];
            int ny = y + dy[direction];
            //뱀이 범위안에 있고 범이 몸통이 없는 위치
            if (1 <= nx && nx <= n && 1 <= ny && ny <= n && map[nx][ny] != 2) {
                //사과가 없을때 이동후 꼬리 제거
                if (map[nx][ny] == 0) {
                    map[nx][ny] = 2;
                    q.offer(new Position(nx, ny));
                    Position prev = q.poll();
                    map[prev.getX()][prev.getY()] = 0;
                }
                //사과 존재시 이동후 꼬리 유지
                if (map[nx][ny] == 1) {
                    map[nx][ny] = 2;
                    q.offer(new Position(nx, ny));
                }
            } else {
                time++;
                break;
            }

            x = nx;
            y = ny;
            time++;
            //회전할 시간일떄
            if (index < l && time == info.get(index).getTime()) {
                direction = turn(direction, info.get(index).getDirection());
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

    static void _12_6() {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = 10;
        ArrayList<BuildItem> buildList = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            int type = sc.nextInt();
            boolean isBuild = sc.nextInt() == 1;

            if (!isBuild) { // 삭제하는 경우
                // 일단 삭제를 해 본 뒤에
                int index = 0;
                for (int j = 0; j < buildList.size(); j++) {
                    if (x == buildList.get(j).getX() && y == buildList.get(j).getY() && type == buildList.get(j).getType()) {
                        index = j;
                    }
                }
                BuildItem erased = buildList.get(index);
                buildList.remove(index);
                if (!isPossible(buildList)) { // 가능한 구조물인지 확인
                    buildList.add(erased); // 가능한 구조물이 아니라면 다시 설치
                }
            } else {
                // 일단 설치를 해 본 뒤에
                BuildItem inserted = new BuildItem(x, y, type);
                buildList.add(inserted);
                if (!isPossible(buildList)) { // 가능한 구조물인지 확인
                    buildList.remove(inserted); // 가능한 구조물이 아니라면 다시 제거
                }
            }
        }

        Collections.sort(buildList);
        System.out.println();

    }

    static boolean isPossible(ArrayList<BuildItem> buildList) {

         /*
         - 기둥은 바닥에 있거나 보의 한쪽끝 위에 있거나 다른 기둥위에 있어야됨.
         - 보는 한쪽 끝부분이 기둥위에 있거나 양쪽 끝부분이 다른 보와 동시에 연결되어야 함.
         - 구조물은 겹치게 설치하는 경우가 없고 없는 구조물을 삭제할수도 없음
         - 벽을 벗어나도 안됨
         - 잘못된 구조물 설치가 되면 해당 동작은 무시됨.

            0 0 0 1
            2 0 0 1
            4 0 0 1
            0 1 1 1
            1 1 1 1
            2 1 1 1
            3 1 1 1
            2 0 0 0
            1 1 1 0
            2 2 0 1
         */

        for (int i = 0; i < buildList.size(); i++) {
            int x = buildList.get(i).getX();
            int y = buildList.get(i).getY();
            int type = buildList.get(i).getType();
            if (type == 0) { // 설치된 것이 '기둥'인 경우
                boolean check = false;
                // '바닥 위'라면 정상
                if (y == 0) check = true;
                // '보의 한 쪽 끝 부분 위' 혹은 '다른 기둥 위'라면 정상
                for (int j = 0; j < buildList.size(); j++) {
                    if (x - 1 == buildList.get(j).getX() && y == buildList.get(j).getY() && 1 == buildList.get(j).getType()) { //둘의 관계가
                        check = true;
                    }
                    if (x == buildList.get(j).getX() && y == buildList.get(j).getY() && 1 == buildList.get(j).getType()) {
                        check = true;
                    }
                    if (x == buildList.get(j).getX() && y - 1 == buildList.get(j).getY() && 0 == buildList.get(j).getType()) {
                        check = true;
                    }
                    if (!check) return false;
                }// 아니라면 거짓(False) 반환
            } else if (type == 1) { // 설치된 것이 '보'인 경우
                boolean check = false;
                boolean left = false;
                boolean right = false;
                // '한쪽 끝부분이 기둥 위' 혹은 '양쪽 끝부분이 다른 보와 동시에 연결'이라면 정상
                for (int j = 0; j < buildList.size(); j++) {
                    if (x == buildList.get(j).getX() && y - 1 == buildList.get(j).getY() && 0 == buildList.get(j).getType()) {
                        check = true;
                    }
                    if (x + 1 == buildList.get(j).getX() && y - 1 == buildList.get(j).getY() && 0 == buildList.get(j).getType()) {
                        check = true;
                    }
                    if (x - 1 == buildList.get(j).getX() && y == buildList.get(j).getY() && 1 == buildList.get(j).getType()) {
                        left = true;
                    }
                    if (x + 1 == buildList.get(j).getX() && y == buildList.get(j).getY() && 1 == buildList.get(j).getType()) {
                        right = true;
                    }
                }
                if (left && right) check = true;
                if (!check) return false; // 아니라면 거짓(False) 반환
            }
        }
        return true;
    }

    static void _12_7() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        ArrayList<Position> chickenList = new ArrayList<>();
        ArrayList<Position> houseList = new ArrayList<>();
        ArrayList<Distance> distances = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                int x = sc.nextInt();
                if (x == 1) houseList.add(new Position(i, j));
                if (x == 2) chickenList.add(new Position(i, j));
            }
        }

        for (int i = 0; i < chickenList.size(); i++) {

            int x1 = chickenList.get(i).getX();
            int y1 = chickenList.get(i).getY();
            int count = 0;

            for (int j = 0; j < houseList.size(); j++) {
                int x2 = houseList.get(j).getX();
                int y2 = houseList.get(j).getY();
                count += Math.abs(x1 - x2) + Math.abs(y1 - y2);
            }
            distances.add(new Distance(x1, y1, count));
        }

        Collections.sort(distances);

        for (int i = 0; i < distances.size(); i++) {
            if (i >= m) {
                int x = distances.get(i).x;
                int y = distances.get(i).y;
                int index = 0;
                for (int j = 0; j < chickenList.size(); j++) {
                    if (x == chickenList.get(j).getX() && y == chickenList.get(j).getY()) {
                        index = j;
                    }
                }
                chickenList.remove(index);

            }
        }

        int total = 0;

        for (int i = 0; i < houseList.size(); i++) {
            int min = (int)1e9;
            int x = houseList.get(i).getX();
            int y = houseList.get(i).getY();
            for (int j = 0; j < chickenList.size(); j++) {
                int x2 = chickenList.get(j).getX();
                int y2 = chickenList.get(j).getY();
                min = Math.min(min, Math.abs(x - x2) + Math.abs(y - y2));
            }
            total += min;
        }

        System.out.println(total);
    }

    static void _12_7_sol() {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] arr = new int[50][50];
        ArrayList<Position> house = new ArrayList<>();
        ArrayList<Position> chicken = new ArrayList<>();


        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                arr[r][c] = sc.nextInt();
                if (arr[r][c] == 1) house.add(new Position(r, c)); // 일반 집
                else if (arr[r][c] == 2) chicken.add(new Position(r, c)); // 치킨집
            }
        }

        // 모든 치킨 집 중에서 m개의 치킨 집을 뽑는 조합 계산
        Combination comb = new Combination(chicken.size(), m);
        comb.combination(chicken, 0, 0, 0);
        ArrayList<ArrayList<Position>> chickenList = comb.getResult();

        // 치킨 거리의 합의 최소를 찾아 출력
        int result = (int) 1e9;
        for (int i = 0; i < chickenList.size(); i++) {
            result = Math.min(result, _12_7_sol_getSum(house, chickenList.get(i)));
        }
        System.out.println(result);
    }

    public static int _12_7_sol_getSum(ArrayList<Position> house, ArrayList<Position> candidates) {
        int result = 0;
        // 모든 집에 대하여
        for (int i = 0; i < house.size(); i++) {
            int hx = house.get(i).getX();
            int hy = house.get(i).getY();
            // 가장 가까운 치킨 집을 찾기
            int temp = (int) 1e9;
            for (int j = 0; j < candidates.size(); j++) {
                int cx = candidates.get(j).getX();
                int cy = candidates.get(j).getY();
                temp = Math.min(temp, Math.abs(hx - cx) + Math.abs(hy - cy));
            }
            // 가장 가까운 치킨 집까지의 거리를 더하기
            result += temp;
        }
        // 치킨 거리의 합 반환
        return result;
    }

    static void _12_8(){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] map = new int[n];
        int[] weak = new int[3];
        int[] dist = new int[2];
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();

        for(int i = 0 ; i< n; i++){
            map[i] = i;
            list.add(new ArrayList<>());
        }

        for(int i = 0 ; i < weak.length; i++){
            weak[i] = sc.nextInt();
        }

        for(int i = 0 ; i < dist.length; i++){
            dist[i] = sc.nextInt();
        }

        for(int i = 0 ; i < n; i++){

            int max = 0;

            for(int j = 0 ; j < dist.length; j++){

                int count = 0;

                for(int k = 0 ; k < weak.length; k++){

                    if(map[i] + dist[j] < n ){
                        if(map[i] + dist[j] >=weak[k]) count++;
                    } else if((map[i] + dist[j] > n)){
                        int remain = map[i]+ dist[j] - n;
                        if(remain >= weak[k]){
                            count++;
                        }
                    }
                }
                max = Math.max(count , max);
            }
            list.get(i).add(max);

        }
        System.out.println();
    }
}

class Combination {
    private int n;
    private int r;
    private int[] now;
    ArrayList<ArrayList<Position>> result;

    public Combination(int n, int r) {
        this.n = n; //리스트 길이
        this.r = r; //제한값
        this.now = new int[r]; //리스트의 인덱스값
        this.result = new ArrayList<>(); //최종 조합된 모든 경우의 리스트
    }

    public ArrayList<ArrayList<Position>> getResult() {
        return result;
    }

    void combination(ArrayList<Position> arr, int depth, int index, int target) {
        if (depth == r) {
            ArrayList<Position> temp = new ArrayList<>();
            for (int i = 0; i < now.length; i++) {
                temp.add(arr.get(now[i]));
            }
            result.add(temp);
            return;
        }
        if (target == n) return;
        now[index] = target;
        combination(arr, depth + 1, index + 1, target + 1);
        combination(arr, depth, index, target + 1);
    }
}

class Distance implements Comparable<Distance> {

    int x;
    int y;
    int distance;

    public Distance(int x, int y, int distance) {
        this.x = x;
        this.y = y;
        this.distance = distance;
    }

    @Override
    public int compareTo(Distance o) {
        if (this.distance < o.distance) return -1;
        return 1;
    }
}

class BuildItem implements Comparable<BuildItem> {

    private int x;
    private int y;
    private int type;

    public BuildItem(int x, int y, int type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getType() {
        return type;
    }

    // 정렬 기준 설정 (x, y, type 순서대로 오름차순)
    @Override
    public int compareTo(BuildItem other) {
        if (this.x == other.x && this.y == other.y) {
            return Integer.compare(this.type, other.type);
        }
        if (this.x == other.x) {
            return Integer.compare(this.y, other.y);
        }
        return Integer.compare(this.x, other.x);
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