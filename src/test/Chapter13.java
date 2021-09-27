package test;

import java.util.*;

public class Chapter13 {

    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static int n, m, result = 0;
    static int add;
    static int sub;
    static int mul;
    static int div;
    static int min = (int) 1e9;
    static int max = -(int) 1e9;


    public static void main(String[] args) {
        _13_6();
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
                map[i][j] = setType(next);
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
                    if("YES".equals(_13_6_dfs(map, x, y, count))){
                        return "YES";
                    };
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


                if (nx >= 0  && nx < n && ny >= 0 && ny < n) {
                    if(map[nx][ny] == 3) break;
                    if(map[nx][ny] == 1)
                        return "NO";
                }
            }
        }
        return "YES";
    }

    static int setType(String type) {

        return switch (type) {
            case "S" -> 1;
            case "T" -> 2;
            default -> 0;
        };
    }
}

class School {
    int x;
    int y;
    int type;

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
