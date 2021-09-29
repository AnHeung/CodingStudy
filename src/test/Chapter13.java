package test;

import java.util.*;

public class Chapter13 {

    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static int n, m, l, r, result = 0;
    int[] d;
    static int add;
    static int sub;
    static int mul;
    static int div;
    static int min = (int) 1e9;
    static int max = -(int) 1e9;

    public static char[][] board = new char[6][6]; // 복도 정보 (N x N)
    public static ArrayList<Position> teachers = new ArrayList<>(); // 모든 선생님 위치 정보
    public static ArrayList<Position> spaces = new ArrayList<>(); // 모든 빈 공간 위치 정보


    public static void main(String[] args) {
        _13_7();
    }


    static void _13_1() {

        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        int k = sc.nextInt();
        int start = sc.nextInt();
        int[] d = new int[3000001];

        ArrayList<ArrayList<Integer>> list = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
            d[i] = -1;
        }

        for (int i = 0; i <= n; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            list.get(a).add(b);
        }

        d[start] = 0;

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);

        while (!queue.isEmpty()) {
            int now = queue.poll();

            for (int i = 0; i < list.size(); i++) {

                int next = list.get(now).get(i);

                if (d[next] == -1) {
                    d[next] = d[now] + 1;
                    queue.offer(next);
                }
            }
        }

        boolean result = false;
        for (int i = 0; i < list.size(); i++) {
            if (d[i] == k) {
                System.out.println(i);
                result = true;
            }
        }
        System.out.println(!result ? -1 : "");
    }

    static void _13_2() {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        int[][] map = new int[n][m];
        int[][] temp = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        _13_2_dfs(0, map, temp);

        System.out.println(result);
    }

    static void _13_2_dfs(int count, int[][] map, int[][] temp) {

        if (count == 3) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    temp[i][j] = map[i][j];
                }
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (temp[i][j] == 2) {
                        _13_2_virus(temp, i, j);
                    }
                }
            }
            result = Math.max(result, _13_2_getScore(temp));
            return;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 0) {
                    map[i][j] = 1;
                    count += 1;
                    _13_2_dfs(count, map, temp);
                    map[i][j] = 0;
                    count -= 1;
                }
            }
        }
    }

    static int _13_2_getScore(int[][] temp) {
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (temp[i][j] == 0) count++;
            }
        }
        return count;
    }

    static void _13_2_virus(int[][] temp, int x, int y) {
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && nx < n && ny >= 0 && ny < m && temp[nx][ny] == 0) {
                temp[nx][ny] = 2;
                _13_2_virus(temp, nx, ny);
            }
        }
    }

    static void _13_3() {

        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        int[][] arr = new int[n][n];
        ArrayList<Virus> virusList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = sc.nextInt();
                if (arr[i][j] != 0) virusList.add(new Virus(i, j, arr[i][j], 0));
            }
        }

        int s = sc.nextInt();
        int x = sc.nextInt();
        int y = sc.nextInt();

        Collections.sort(virusList);

        Queue<Virus> queue = new LinkedList<>();

        for (int i = 0; i < virusList.size(); i++) {
            queue.offer(virusList.get(i));
        }

        while (!queue.isEmpty()) {

            Virus virus = queue.poll();

            if (virus.second == s) break;

            for (int i = 0; i < 4; i++) {
                int nx = virus.x + dx[i];
                int ny = virus.y + dy[i];

                if (nx >= 0 && nx < n && ny >= 0 && ny < n && arr[nx][ny] == 0) {
                    arr[nx][ny] = virus.virusType;
                    queue.offer(new Virus(nx, ny, virus.virusType, virus.second + 1));
                }
            }

        }

        System.out.println(arr[x - 1][y - 1]);
    }

    static void _13_4() {

        Scanner sc = new Scanner(System.in);
        String input = sc.next();

        System.out.println(_13_4_sol(input));

    }

    static String _13_4_sol(String input) {

        String answer;

        if ("".equals(input)) return "";

        int idx = _13_4_balanceIndex(input);
        String u = input.substring(0, idx + 1);
        String v = input.substring(idx + 1);
        if (_13_4_isProperString(u)) answer = u + _13_4_sol(v);
        else {
            answer = "(";
            answer += _13_4_sol(v);
            answer += ")";
            u = u.substring(1, u.length() - 1);
            String temp = _13_4_reverse(u);
            answer += temp;
        }
        return answer;
    }

    static int _13_4_balanceIndex(String str) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(') count++;
            else count--;
            if (count == 0) return i;
        }
        return -1;
    }

    static boolean _13_4_isProperString(String str) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(') count++;
            else {
                if (count == 0) return false;
                count--;
            }
        }
        return true;
    }

    static String _13_4_reverse(String str) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(') builder.append(")");
            else builder.append(")");
        }
        return builder.toString();
    }

    static void _13_5() {

        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        add = sc.nextInt();
        sub = sc.nextInt();
        mul = sc.nextInt();
        div = sc.nextInt();
        //1,2,3,4
        //+ + - *

//        int sum = arr[0];
//
//        for(int i = 0 ; i < n; i++){
//
//            if(i+1 < n){
//                if(add > 0){
//                     sum += arr[i+1];
//                     add--;
//                }
//
//                if(sub > 0){
//                    sum -= arr[i+1];
//                    sub--;
//                }
//
//                if(mul > 0){
//                    sum *= arr[i+1];
//                    mul--;
//                }
//
//                if(div > 0){
//                    sum /= arr[i+1];
//                    div--;
//                }
//            }
//        }

        _13_5_dfs(1, arr[0], arr);

        System.out.println(min);
        System.out.println(max);

    }


    static void _13_5_dfs(int i, int now, int[] arr) {

        if (i == n) {
            min = Math.min(min, now);
            max = Math.max(max, now);
        } else {
            // 각 연산자에 대하여 재귀적으로 수행
            if (add > 0) {
                add -= 1;
                _13_5_dfs(i + 1, now + arr[i], arr);
                add += 1;
            }
            if (sub > 0) {
                sub -= 1;
                _13_5_dfs(i + 1, now - arr[i], arr);
                sub += 1;
            }
            if (mul > 0) {
                mul -= 1;
                _13_5_dfs(i + 1, now * arr[i], arr);
                mul += 1;
            }
            if (div > 0) {
                div -= 1;
                _13_5_dfs(i + 1, now / arr[i], arr);
                div += 1;
            }
        }
    }

    static void _13_6() {

        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        int[][] map = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                String next = sc.next();
                map[i][j] = _13_6_setType(next);
            }
        }
        System.out.println(_13_6_dfs(map, 0, 0, 0));
    }

    static String _13_6_dfs(int[][] map, int x, int y, int count) {

        if (count == 3) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (map[i][j] == 2) {
                        return _13_6_checkStudent(i, j, map);
                    }
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 0) {
                    map[i][j] = 3;
                    count++;
                    if ("YES".equals(_13_6_dfs(map, x, y, count))) {
                        return "YES";
                    }
                    map[i][j] = 0;
                    count--;
                }
            }
        }
        return "NO";
    }

    static String _13_6_checkStudent(int x, int y, int[][] map) {

        for (int i = 0; i < 4; i++) {

            for (int j = 0; j < n; j++) {

                int nx = x;
                int ny = y;

                if (i == 0) ny += j;
                if (i == 1) nx += j;
                if (i == 2) ny -= j;
                if (i == 3) nx -= j;

                if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
                    if (map[nx][ny] == 3) break;
                    if (map[nx][ny] == 1)
                        return "NO";
                }
            }
        }
        return "YES";
    }

    static int _13_6_setType(String type) {
        switch (type) {
            case "S":
                return 1;
            case "T":
                return 2;
            default:
                return 0;
        }
    }

    static void _13_6_sol() {

        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = sc.next().charAt(0);
                // 선생님이 존재하는 위치 저장
                if (board[i][j] == 'T') {
                    teachers.add(new Position(i, j));
                }
                // 장애물을 설치할 수 있는 (빈 공간) 위치 저장
                if (board[i][j] == 'X') {
                    spaces.add(new Position(i, j));
                }
            }
        }

        // 빈 공간에서 3개를 뽑는 모든 조합을 확인
        Combination comb = new Combination(spaces.size(), 3);
        comb.combination(spaces, 0, 0, 0);
        ArrayList<ArrayList<Position>> spaceList = comb.getResult();

        // 학생이 한 명도 감지되지 않도록 설치할 수 있는지의 여부
        boolean found = false;
        for (int i = 0; i < spaceList.size(); i++) {
            // 장애물들을 설치해보기
            for (int j = 0; j < spaceList.get(i).size(); j++) {
                int x = spaceList.get(i).get(j).getX();
                int y = spaceList.get(i).get(j).getY();
                board[x][y] = 'O';
            }
            // 학생이 한 명도 감지되지 않는 경우
            if (!_13_6_process()) {
                // 원하는 경우를 발견한 것임
                found = true;
                break;
            }
            // 설치된 장애물을 다시 없애기
            for (int j = 0; j < spaceList.get(i).size(); j++) {
                int x = spaceList.get(i).get(j).getX();
                int y = spaceList.get(i).get(j).getY();
                board[x][y] = 'X';
            }
        }

        if (found) System.out.println("YES");
        else System.out.println("NO");
    }

    // 장애물 설치 이후에, 한 명이라도 학생이 감지되는지 검사
    public static boolean _13_6_process() {
        // 모든 선생의 위치를 하나씩 확인
        for (int i = 0; i < teachers.size(); i++) {
            int x = teachers.get(i).getX();
            int y = teachers.get(i).getY();
            // 4가지 방향으로 학생을 감지할 수 있는지 확인
            for (int j = 0; j < 4; j++) {
                if (_13_6_watch(x, y, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    // 특정 방향으로 감시를 진행 (학생 발견: true, 학생 미발견: false)
    public static boolean _13_6_watch(int x, int y, int direction) {
        // 왼쪽 방향으로 감시
        if (direction == 0) {
            while (y >= 0) {
                if (board[x][y] == 'S') { // 학생이 있는 경우
                    return true;
                }
                if (board[x][y] == 'O') { // 장애물이 있는 경우
                    return false;
                }
                y -= 1;
            }
        }
        // 오른쪽 방향으로 감시
        if (direction == 1) {
            while (y < n) {
                if (board[x][y] == 'S') { // 학생이 있는 경우
                    return true;
                }
                if (board[x][y] == 'O') { // 장애물이 있는 경우
                    return false;
                }
                y += 1;
            }
        }
        // 위쪽 방향으로 감시
        if (direction == 2) {
            while (x >= 0) {
                if (board[x][y] == 'S') { // 학생이 있는 경우
                    return true;
                }
                if (board[x][y] == 'O') { // 장애물이 있는 경우
                    return false;
                }
                x -= 1;
            }
        }
        // 아래쪽 방향으로 감시
        if (direction == 3) {
            while (x < n) {
                if (board[x][y] == 'S') { // 학생이 있는 경우
                    return true;
                }
                if (board[x][y] == 'O') { // 장애물이 있는 경우
                    return false;
                }
                x += 1;
            }
        }
        return false;
    }

    static void _13_7() {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        l = sc.nextInt(); //몇명 이상
        r = sc.nextInt(); // 몇명 이하

        int[][] map = new int[n][n];
        int[][] unions = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = sc.nextInt();
            }
        }
        //각 나라 인덱스
        int index = 0;

        while (true) {
            //-1 로 초기화 시켜 모두 방문안했다고 가정함
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    unions[i][j] = -1;
                }
            }
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (unions[i][j] == -1) { // 해당 나라가 아직 처리되지 않았다면
                        _13_7_process(map, unions, i, j, index);
                        index += 1;
                    }
                }
            }
            if (index == n * n) break;
            result++;
        }
        System.out.println(result);
    }

    static void _13_7_process(int[][] map, int[][] unions, int x, int y, int idx) {

        // (x, y)의 위치와 연결된 나라(연합) 정보를 담는 리스트
        ArrayList<Position> united = new ArrayList<>();
        united.add(new Position(x, y));
        // 너비 우선 탐색 (BFS)을 위한 큐 라이브러리 사용
        Queue<Position> q = new LinkedList<>();
        q.offer(new Position(x, y));
        unions[x][y] = idx; // 현재 연합의 번호 할당
        int summary = map[x][y]; // 현재 연합의 전체 인구 수
        int count = 1; // 현재 연합의 국가 수
        // 큐가 빌 때까지 반복(BFS)
        while (!q.isEmpty()) {
            Position pos = q.poll();
            x = pos.getX();
            y = pos.getY();
            // 현재 위치에서 4가지 방향을 확인하며
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                // 바로 옆에 있는 나라를 확인하여
                if (0 <= nx && nx < n && 0 <= ny && ny < n && unions[nx][ny] == -1) {
                    // 옆에 있는 나라와 인구 차이가 L명 이상, R명 이하라면
                    int gap = Math.abs(map[nx][ny] - map[x][y]);
                    if (l <= gap && gap <= r) {
                        q.offer(new Position(nx, ny));
                        // 연합에 추가하기
                        unions[nx][ny] = idx;
                        summary += map[nx][ny];
                        count += 1;
                        united.add(new Position(nx, ny));
                    }
                }
            }
        }
        // 연합 국가끼리 인구를 분배
        for (int i = 0; i < united.size(); i++) {
            x = united.get(i).getX();
            y = united.get(i).getY();
            map[x][y] = summary / count;
        }
    }

    static void _13_8() {

        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        int[][] map = new int[n][n];
        int[][] temp = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = sc.nextInt();
            }
        }
        System.out.println(_13_8_solution(map));
    }

    static int _13_8_solution(int[][] map) {
        int answer = 0;
        int x = 0;
        int y = 0;

        Queue<Position> queue = new LinkedList<>();
        int direction = 0;
        queue.offer(new Position(x,y));
        map[x][y] = 2;
        map[x][y+1] = 2;

        if(!queue.isEmpty()){

            int nx = x+ dx[direction];
            int ny = x+ dx[direction];

            if(nx >= 0 && nx < n && ny >= 0 && ny < n && map[nx][ny] != 1){
                queue.offer(new Position(nx,ny));
                map[nx][ny] = 2;
                Position position = queue.poll();
                map[position.getX()][position.getY()] = 0;
            }

        }

        return answer;
    }

    int _13_8_TurnLeftDirection(int direction) {
        direction -= 1;
        if (direction == -1) return 3;
        return direction;
    }
}

class Virus implements Comparable<Virus> {

    int x;
    int y;
    int virusType;
    int second;

    public Virus(int x, int y, int virusType, int second) {
        this.x = x;
        this.y = y;
        this.virusType = virusType;
        this.second = second;
    }

    @Override
    public int compareTo(Virus other) {
        if (this.virusType < other.virusType) {
            return -1;
        }
        return 1;
    }
}
