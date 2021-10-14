package test;

import java.util.*;

public class Chapter12Re {

    public static int n, m, l, result, max, min;
    static int N;
    static int plus = 0, sub = 0, mul = 0, div = 0;
    public static int[] arr;
    public static Scanner sc = new Scanner(System.in);
    public static int[][] map;
    public static int[][] union;
    public static String[][] graph;
    public static int[][] temp;
    public static int[] d;
    static int[] parent;
    static boolean[][] visited;
    public static int INF = (int) 1e9;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) {
        _12_6();
    }

    static void _12_1() {
        n = sc.nextInt();

        String temp = Integer.toString(n);

        arr = new int[temp.length()];

        for (int i = 0; i < temp.length(); i++) {
            arr[i] = temp.charAt(i) - '0';
        }

        int half = arr.length / 2;
        int a = 0;
        int b = 0;

        for (int i = 0; i < arr.length; i++) {
            if (i < half) a += arr[i];
            else b += arr[i];
        }
        System.out.println(a == b ? "LUCKY" : "READY");
    }

    static void _12_2() {

        String str = sc.next();

        String onlyStr = str.replaceAll("[\\d]", "");
        String onlyInt = str.replaceAll("[^\\d]", "");

        int sum = 0;

        for (int i = 0; i < onlyInt.length(); i++) {
            sum += onlyInt.charAt(i) - '0';
        }

        char[] charArr = onlyStr.toCharArray();

        Arrays.sort(charArr);
        Arrays.sort(onlyInt.toCharArray());

        String SortedString = new String(charArr);

        StringBuilder sb = new StringBuilder();
        sb.append(SortedString);
        sb.append(sum);

        System.out.println(sb);
    }

    static void _12_3() {

        String str = sc.next();
        result = (int) 1e9;
        int count = 1;

        while (count <= str.length() / 2) {

            int startIdx = count;
            String prev = str.substring(0, count);
            int sum = count;
            int sameCount = 1;


            for (int j = count; j < str.length(); j += count) {

                int lastIdx = j + count;

                if (lastIdx > str.length()) lastIdx = str.length();

                String after = str.substring(startIdx, lastIdx);

                if (prev.equals(after)) {
                    sameCount++;
                } else {
                    if (sameCount > 1) {
                        sum += after.length() + String.valueOf(sameCount).length();
                        sameCount = 1;
                    } else {
                        sum += after.length();
                    }
                }
                startIdx = j + count;
                prev = after;
            }
            if (sameCount > 1) {
                sum += String.valueOf(sameCount).length();
            }
            result = Math.min(result, sum);
            count++;
        }
        System.out.println(result);
    }

    /*
       식 접근자체는 옳게 가져갔으나
       lock 배열에서 key 배열을 어떻게 움직일까에 대해 빠르게 해결하지 못하였다.
       key 배열을 lock 배열에 복사해 해당 배열을 비교하고 해당 부분이 모두 1이면 (2가 있다는건 키가 겹쳤다는 뜻)
       정답이고 아니면 반복해서 돌렸어야 했는데 5중 배열까지 쓸거라곤 생각을 안했다.
     */
    static void _12_4() {

        m = sc.nextInt();
        n = sc.nextInt();

        int[][] keyArr = new int[m][m];
        int[][] lockArr = new int[n * 3][n * 3];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                keyArr[i][j] = sc.nextInt();
            }
        }

        for (int i = n; i < n * 2; i++) {
            for (int j = n; j < n * 2; j++) {
                lockArr[i][j] = sc.nextInt();
            }
        }

        for (int rotation = 0; rotation < 4; rotation++) {
            keyArr = _12_4_rotate(keyArr);


            for (int x = 0; x < n * 2; x++) {
                for (int y = 0; y < n * 2; y++) {

                    /*
                    이부분 식을 짜는데 고민을 했다
                    key 배열값을 lock 배열값에 같은 걸로 위치만 바꾸면서 복사해야하는데
                    키값만큼 이중배열을 한번 더써서 
                    lockArr[i+x][j+y] += keyArr[i][j] 해당식을 짤생각을 못했다 ㅠ
                     */

                    for (int i = 0; i < m; i++) {
                        for (int j = 0; j < m; j++) {
                            lockArr[i + x][j + y] += keyArr[i][j];
                        }
                    }
                    if (_12_4_checkLock(lockArr)) {
                        System.out.println(true);
                        return;
                    }
                    for (int i = 0; i < m; i++) {
                        for (int j = 0; j < m; j++) {
                            lockArr[i + x][j + y] -= keyArr[i][j];
                        }
                    }
                }
            }
        }

        System.out.println(false);
    }

    private static boolean _12_4_checkLock(int[][] lockArr) {

        for (int i = n; i < n * 2; i++) {
            for (int j = n; j < n * 2; j++) {
                if (lockArr[i][j] != 1) return false;
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

    /*
    오답
    뱀의 꼬리위치를 어떻게 처리할까 고민하다가 해결 못함
    뱀의 꼬리 위치를 Position 객체값으로 받아서 저장할 생각을 했는데
    처음부터 Queue 에 담고 루프 돌려서 이전에 이동했을떄 index 값을 알앗어야 했음
    그리고 사이즈에 너무 집중해서 사이즈 크기가 커졋을때 뱀의 크기와 회전하는걸 고민했는데 이게 함정이였음
    애초에 사이즈가 커졋을땐 뱀이 꼬리를 안늘리고 그자리에서 사과만 먹고 끝남 즉 몸이 길어진다 한들
    머리와 꼬리 위치만 queue 에 담고 계속 이동하기 때문에 사과가 없을땐 꼬리가 앞칸으로 한칸 이동하고 사과만 먹을떈 꼬리가 이동이 없다는게 핵심임.
     */
    static void _12_5() {
        n = sc.nextInt(); //보드크기
        m = sc.nextInt();  //사과 갯수

        ArrayList<SnakeInfo> directionList = new ArrayList<>();

        map = new int[n][n];

        for (int i = 0; i < m; i++) {
            int a = sc.nextInt() - 1;
            int b = sc.nextInt() - 1;
            map[a][b] = 1;
        }

        l = sc.nextInt(); //뱀의 방향 변환 횟수

        for (int i = 0; i < l; i++) {
            int time = sc.nextInt();
            String direction = sc.next();
            directionList.add(new SnakeInfo(time, direction));
        }

        //뱀시작
        int x = 0;
        int y = 0;

        map[x][y] = 2;

        int direction = 0; //동쪽
        int count = 0;
        int index = 0; // 회전정보에 관한 index

        Queue<Position> queue = new LinkedList<>();
        queue.offer(new Position(0, 0));

        while (!queue.isEmpty()) {

            int nx = x + dx[direction];
            int ny = y + dy[direction];

            if (0 <= nx && nx < n && 0 <= ny && ny < n && map[nx][ny] != 2) {

                if (map[nx][ny] == 0) {
                    map[nx][ny] = 2;
                    queue.offer(new Position(nx, ny));
                    Position prev = queue.poll();
                    map[prev.getX()][prev.getY()] = 0;
                }

                //사과가 해당칸에 있을떄
                if (map[nx][ny] == 1) {
                    map[nx][ny] = 2;
                    queue.offer(new Position(nx, ny));
                }

            } else {
                System.out.println(count + 1);
                break;
            }
            x = nx;
            y = ny;
            count++;

            if (index < l && directionList.get(index).time == count) {
                direction = _12_5_changeDirection(directionList.get(index).direction, direction);
                index++;
            }
        }
    }

    static int _12_5_changeDirection(String directionStr, int direction) {
        if (directionStr.equals("D")) {
            return _12_5_changeRightDirection(direction);
        } else {
            return _12_5_changeLeftDirection(direction);
        }
    }

    static int _12_5_changeRightDirection(int direction) {
        if (direction == 3) return 1;
        return direction + 1;
    }

    static int _12_5_changeLeftDirection(int direction) {
        if (direction == 0) return 3;
        return direction - 1;
    }

    /*
       설치하는거까진 했으나 삭제하는쪽에서 애먹었다.
       그냥 삭제를 해버린다음에 현재 배열을 돌려 테스트 해보고 다 통과하면 문제없는거고
       실패하면 문제 있는거라 삭제를 취소하면 되는데 그 생각을 못했던거 같다.
     */
    static void _12_6() {

        n = sc.nextInt();
        m = sc.nextInt();
        map = new int[n+1][n+1];
        ArrayList<BuildFrame> buildFrameList = new ArrayList<>();
        ArrayList<BuildItem2> buildItemList = new ArrayList<>();

        for (int i = 0; i < n+1; i++) {
            Arrays.fill(map[i], -1);
        }

        for (int i = 0; i < m; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            int buildType = sc.nextInt();
            int isRemove = sc.nextInt();
            buildFrameList.add(new BuildFrame(x, y, buildType, isRemove));
        }

        for (int i = 0; i < buildFrameList.size(); i++) {

            BuildFrame buildFrame = buildFrameList.get(i);
            int x = buildFrame.x;
            int y = buildFrame.y;
            int buildType = buildFrame.buildType;
            int isRemove = buildFrame.isRemove;
            BuildItem2 buildItem = new BuildItem2(buildFrame.x, buildFrame.y, buildFrame.buildType);

            if (isRemove == 1) { //설치
                if (_12_6_Available(buildFrame)) {
                    buildItemList.add(buildItem);
                }
            } else {

                int idx = -1;
                for (int j = 0; j < buildItemList.size(); j++) {
                    if (buildItemList.get(j).x == x && buildItemList.get(j).y == y && buildItemList.get(j).buildType == buildType) {
                        idx = j;
                        break;
                    }
                }
                buildItemList.remove(idx);
                map[x][y] = -1;
                if (!_12_6_isRemovable(buildItemList)) {
                    buildItemList.add(idx ,buildItem);
                    map[x][y] = buildType;
                }
            }

        }
        Collections.sort(buildItemList);

        for (int i = 0; i < buildItemList.size(); i++) {
            System.out.println(buildItemList.get(i).toString());
        }
    }

    static boolean _12_6_Available(BuildFrame buildFrame) {

        int x = buildFrame.x;
        int y = buildFrame.y;
        int type = buildFrame.buildType;

        //기둥
        if (type == 0) {

            if (y == 0) {
                map[x][y] = 0;
                return true;
            }

            if (map[x][y - 1] == 0 || map[x - 1][y] == 1 || map[x + 1][y] == 1) {
                map[x][y] = 0;
                return true;
            }

        } else { //보
            if (y == 0) {
                return false;
            }

            if (map[x][y - 1] == 0 || map[x + 1][y - 1] == 0 || (map[x + 1][y] == 1 && map[x - 1][y] == 1)) {
                map[x][y] = 1;
                return true;
            }
        }
        return false;
    }

    static boolean _12_6_isRemovable(ArrayList<BuildItem2> list) {

        boolean check = false;

        for (BuildItem2 item : list) {

            int x = item.x;
            int y = item.y;
            int type = item.buildType;

            //기둥
            if (type == 0) {

                if (y == 0) {
                    check = true;
                } else if (map[x][y - 1] == 0 || map[x - 1][y] == 1 || map[x + 1][y] == 1) {
                    check = true;
                }else{
                    return false;
                }
            } else { //보
                if (y == 0) {
                    return false;
                }

                if (map[x][y - 1] == 0 || map[x + 1][y - 1] == 0 || (map[x + 1][y] == 1 && map[x - 1][y] == 1)) {
                    check = true;
                }else{
                    return false;
                }
            }
        }
        return check;
    }

    static void _12_7() {

        n = sc.nextInt();
        m = sc.nextInt();

        ArrayList<Position> chickenList = new ArrayList<>();
        ArrayList<Position> houseList = new ArrayList<>();

        map = new int[n][n];
        temp = new int[n][n];

        result = INF;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int x = sc.nextInt();
                if (x == 2) chickenList.add(new Position(i + 1, j + 1));
                if (x == 1) houseList.add(new Position(i + 1, j + 1));
                map[i][j] = x;
            }
        }

        _12_7_dfs(chickenList, houseList);

        System.out.println(result);

    }

    static void _12_7_dfs(ArrayList<Position> chickenList, ArrayList<Position> houseList) {

        if (chickenList.size() == m) {
            int sum = 0;

            for (int i = 0; i < houseList.size(); i++) {
                int houseX = houseList.get(i).getX();
                int houseY = houseList.get(i).getY();

                int min = INF;

                for (int j = 0; j < chickenList.size(); j++) {
                    int chickenX = chickenList.get(j).getX();
                    int chickenY = chickenList.get(j).getY();
                    int gap = Math.abs(houseX - chickenX) + Math.abs(houseY - chickenY);
                    min = Math.min(min, gap);
                }
                sum += min;
            }
            result = Math.min(result, sum);

            return;
        }

        for (int i = 0; i < chickenList.size(); i++) {
            Position chickenPos = chickenList.get(i);
            chickenList.remove(chickenPos);
            _12_7_dfs(chickenList, houseList);
            chickenList.add(chickenPos);
        }
    }

    static void _12_8() {

    }
}

class SnakeInfo {
    int time;
    String direction;

    public SnakeInfo(int time, String direction) {
        this.time = time;
        this.direction = direction;
    }
}

class BuildFrame {

    int x;
    int y;
    int buildType;
    int isRemove;

    public BuildFrame(int x, int y, int buildType, int isRemove) {
        this.x = x;
        this.y = y;
        this.buildType = buildType;
        this.isRemove = isRemove;
    }
}

class BuildItem2 implements Comparable<BuildItem2> {

    int x;
    int y;
    int buildType;

    public BuildItem2(int x, int y, int buildType) {
        this.x = x;
        this.y = y;
        this.buildType = buildType;
    }

    @Override
    public int compareTo(BuildItem2 other) {
        if (this.x == other.x) {
            return this.y - other.y;
        }
        return this.x - other.x;
    }

    @Override
    public String toString() {
        return "[" + this.x + "," + this.y + "," + this.buildType + "]";
    }
}
