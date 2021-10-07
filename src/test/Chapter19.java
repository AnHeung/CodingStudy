package test;

import java.util.*;

public class Chapter19 {


    public static int n, m, result;
    public static int[] arr;
    public static Scanner sc = new Scanner(System.in);
    public static int[][] map;
    public static int[] d;
    static int[] parent;
    static boolean[][] visited;
    public static int INF = (int) 1e9;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) {
        _19_1_sol();
    }

    static void _19_1() {

        n = sc.nextInt();
        int x = 0;
        int y = 0;
        int time = 0;
        int eatCount = 0;
        boolean eatFlag = false;
        int size = 2;

        map = new int[n][n];
        visited = new boolean[n][n];

        //최초 상어위치를 알아두고 해당 값도 0으로 초기화
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int a = sc.nextInt();
                map[i][j] = a;
                if (a == 9) {
                    map[i][j] = 0;
                    x = i;
                    y = j;
                    visited[x][y] = true;
                }

            }
        }

        ArrayList<SharkPosition> queue = new ArrayList<>();
        queue.add(new SharkPosition(x, y));

        while (!queue.isEmpty()) {

            Collections.sort(queue); //정렬을 사용해 최우선을 위쪽 그 다음 우선을 왼쪽으로 하도록 정렬한다
            int qSize = queue.size(); //사이즈를 변수로 받아둬서 딱 큐사이즈 만큼만 돈다

            for (int i = 0; i < qSize; i++) {

                SharkPosition pos = queue.remove(0);
                x = pos.x;
                y = pos.y;

                //상어가 0이면 먹을게 없고 사이즈보다 작아야 먹을수 있다.
                if (0 < map[x][y] && map[x][y] < size) {
                    map[x][y] = 0;
                    eatCount++;

                    //먹는크기가 이르르면 커진다.
                    if (eatCount == size) {
                        size++;
                        eatCount = 0;
                    }
                    //한번 먹었기 때문에 큐를 초기화 하고 다시 돈다.
                    queue.clear();
                    visited = new boolean[n][n];
                    visited[x][y] = true;
                    result = time;
                    eatFlag = true;
                }

                for (int j = 0; j < 4; j++) {

                    int nx = x + dx[j];
                    int ny = y + dy[j];

                    if (nx >= 0 && nx < n && ny >= 0 && ny < n && !visited[nx][ny] && map[nx][ny] <= size) {
                        queue.add(new SharkPosition(nx, ny));
                        visited[nx][ny] = true;
                    }
                }
                if (eatFlag) {
                    eatFlag = false;
                    break;
                }
            }
            time++;
        }

        System.out.println(result);
    }

    static boolean isEmpty() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] != 9 && map[i][j] != 0) {
                    return true;
                }
            }
        }
        return false;
    }

    static void _19_1_sol() {

        n = sc.nextInt();
        int x = 0;
        int y = 0;
        int eatCount = 0;
        int size = 2;
        ArrayList<SharkPosition2> fishList = new ArrayList<>();

        map = new int[n][n];
        visited = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int a = sc.nextInt();
                map[i][j] = a;
                if (a == 9) {
                    map[i][j] = 0;
                    x = i;
                    y = j;
                }
            }
        }

        Queue<SharkPosition2> q = new LinkedList<>();
        q.add(new SharkPosition2(x,y,0));
        visited[x][y] = true;

        while(true) {
            while(!q.isEmpty()) {
                SharkPosition2 now = q.poll();
                int time = now.time;


                for (int k = 0; k < 4; k++) {
                    int nx = now.x + dx[k];
                    int ny = now.y + dy[k];

                    // 범위 밖 -> 아웃
                    if(!(0 <= nx && nx < n && 0 <= ny && ny < n) || visited[nx][ny]) continue;

                    // [식사 가능] 먹이 리스트에 추가
                    if(map[nx][ny] < size && map[nx][ny] != 0) {
                        q.add(new SharkPosition2(nx, ny, time+1));
                        visited[nx][ny] = true;
                        fishList.add(new SharkPosition2(nx, ny, time+1));
                    }

                    // [이동만 가능] 사이즈랑 동일 OR 비어있음(0)
                    if(map[nx][ny] == size || map[nx][ny] == 0) {
                        q.add(new SharkPosition2(nx, ny, time+1));
                        visited[nx][ny] = true;
                    }
                }
            }

            if(!fishList.isEmpty()) {
                // 정렬을 통해 우선순위별로 먹이 리스트 재정렬
                Collections.sort(fishList);

                SharkPosition2 now = fishList.get(0);

                // 상어 위치 이동, 상어가 먹은 횟수 증가, 상어 걸린 시간 증가
                x = now.x;
                y = now.y;

                if(++eatCount == size) {
                    size++;
                    eatCount = 0;
                }

                result += now.time;

                // 지도 위에 해당 위치 0 처리
                map[now.x][now.y] = 0;

                // 먹이 리스트 초기화
                fishList.clear();
                // 식사가 끝났으면, 방문 배열 초기화

                q.clear();
                visited = new boolean[n][n];

                // 다시 이동하는 큐(q)에다가 현재 상어 추가
                q.add(new SharkPosition2(x,y ,0));
                visited[x][y] = true;
            }else {
                System.out.println(result);
                return;
            }
        }
    }

}

class SharkPosition implements Comparable<SharkPosition> {

    int x;
    int y;

    public SharkPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(SharkPosition o) {
        if (this.x == o.x) return this.y - o.y;
        return this.x - o.x;
    }
}

class SharkPosition2 implements Comparable<SharkPosition2> {

    int x;
    int y;
    int time;

    public SharkPosition2(int x, int y, int time) {
        this.x = x;
        this.y = y;
        this.time = time;
    }

    @Override
    public int compareTo(SharkPosition2 other) {
        if (this.time == other.time) {
            if (this.x == other.x) {
                if (this.y > other.y) {
                    return 1;
                } else {
                    return -1;
                }
            } else {
                if (this.x > other.x) {
                    return 1;
                } else {
                    return -1;
                }
            }
        } else if (this.time > other.time) {
            return 1;
        } else {
            return -1;
        }
    }
}

