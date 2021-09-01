package test;

import java.util.*;

public class Chapter7 {

    public static void main(String[] args) {

//        int[] arr = {0, 2, 4, 6, 8, 10, 12, 14, 16, 18};
//        int target = 12;
//        int result = binarySearchRecursive(arr, target, 0, arr.length - 1);
//        int result = binarySearch(arr, target, 0, arr.length - 1);
//        System.out.println(result);

        _7_2_sol();

    }

    static void _7_1() {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] arr = new int[n];
        sc.nextLine();

        String[] lines = sc.nextLine().split(" ");

        for (int i = 0; i < lines.length; i++) {
            arr[i] = lines[i].charAt(0) - '0';
        }
        int m = sc.nextInt();
        sc.nextLine();
        String[] orders = sc.nextLine().split(" ");
        int[] order = new int[m];
        for (int i = 0; i < orders.length; i++) {
            order[i] = orders[i].charAt(0) - '0';
        }


        for (int i = 0; i < order.length; i++) {
            boolean result = false;
            for (int j = 0; j < arr.length; j++) {
                if (order[i] == arr[j]) result = true;
            }
            System.out.print(result ? "yes " : "no ");
        }
    }

    static void _7_1_sol() {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] arr = new int[n];
        sc.nextLine();

        String[] lines = sc.nextLine().split(" ");

        for (int i = 0; i < lines.length; i++) {
            arr[i] = lines[i].charAt(0) - '0';
        }

        //이진 탐색은 배열이 정렬되있음을 기본전제로 한다.
        Arrays.sort(arr);

        int m = sc.nextInt();
        sc.nextLine();
        String[] orders = sc.nextLine().split(" ");
        int[] order = new int[m];
        for (int i = 0; i < orders.length; i++) {
            order[i] = orders[i].charAt(0) - '0';
        }

        for (int i = 0; i < order.length; i++) {
            int result = _7_1_binarySearch(arr, order[i], 0, arr.length - 1);
            if (result == -1) {
                System.out.print("no ");
            } else {
                System.out.print("yes ");
            }
        }

    }

    static void _7_1_sol_2() {

        Scanner sc = new Scanner(System.in);

        // N(가게의 부품 개수)
        int n = sc.nextInt();
        int[] arr = new int[1000001];
        for (int i = 0; i < n; i++) {
            int x = sc.nextInt();
            arr[x] = 1;
        }

        // M(손님이 확인 요청한 부품 개수)
        int m = sc.nextInt();
        int[] targets = new int[n];
        for (int i = 0; i < m; i++) {
            targets[i] = sc.nextInt();
        }

        // 손님이 확인 요청한 부품 번호를 하나씩 확인
        for (int i = 0; i < m; i++) {
            // 해당 부품이 존재하는지 확인
            if (arr[targets[i]] == 1) {
                System.out.print("yes ");
            } else {
                System.out.print("no ");
            }
        }
    }

    //hashSet 사용
    static void _7_1_sol_3() {
        Scanner sc = new Scanner(System.in);

        // N(가게의 부품 개수)
        int n = sc.nextInt();
        // 집합(Set) 정보를 처리하기 위한 HashSet 라이브러리
        HashSet<Integer> s = new HashSet<>();
        for (int i = 0; i < n; i++) {
            int x = sc.nextInt();
            s.add(x);
        }

        // M(손님이 확인 요청한 부품 개수)
        int m = sc.nextInt();
        int[] targets = new int[n];
        for (int i = 0; i < m; i++) {
            targets[i] = sc.nextInt();
        }

        // 손님이 확인 요청한 부품 번호를 하나씩 확인
        for (int i = 0; i < m; i++) {
            // 해당 부품이 존재하는지 확인
            if (s.contains(targets[i])) {
                System.out.print("yes ");
            } else {
                System.out.print("no ");
            }
        }
    }

    static void _7_2() {
        /*
        길이 보다 짧은건 안잘림 긴건 짤림 (떡길이 - 설정길이)
        처음 줄에 떡의 개수 n 및 요청한 떡의 길이 m 설정
        설정해야 하는 절단기 최대값
         */

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int target = sc.nextInt();

        int[] arr = new int[n];
        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {
            arr[i] = sc.nextInt();
        }

        Arrays.sort(arr);

        int limit = arr[arr.length - 1];
        int minValue = arr[0];


        for (int i = 0; i < minValue; i++) {
            int result = 0;
            for (int j = 0; j < arr.length; j++) {
                if (limit < arr[j]) result += arr[j] - limit;
            }
            if (result == target) list.add(limit);
            limit--;

        }

        list.sort(Collections.reverseOrder());
        if (list.size() == 0) System.out.println("자료없음");
        else System.out.println(list.get(0));
    }

    //절단기 길이 값이 10억도 가능하기 때문에 이진탐색트리로 해결 안하면 답없다.
    static void _7_2_sol() {

        Scanner sc = new Scanner(System.in);

        // 떡의 개수(N)와 요청한 떡의 길이(M)
        int n = sc.nextInt();
        int m = sc.nextInt();

        // 각 떡의 개별 높이 정보
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        // 이진 탐색을 위한 시작점과 끝점 설정
        int start = 0;
        int end = (int) 1e9; // 1 * 10^9 = 1000000000 >> 10억
        // 이진 탐색 수행 (반복적)
        int result = 0;
        while (start <= end) {
            long total = 0;
            int mid = (start + end) / 2;
            for (int i = 0; i < n; i++) {
                // 잘랐을 때의 떡의 양 계산
                if (arr[i] > mid) total += arr[i] - mid;
            }
            if (total < m) { // 떡의 양이 부족한 경우 더 많이 자르기(왼쪽 부분 탐색)
                end = mid - 1;
            }
            else { // 떡의 양이 충분한 경우 덜 자르기(오른쪽 부분 탐색)
                result = mid; // 최대한 덜 잘랐을 때가 정답이므로, 여기에서 result에 기록
                start = mid + 1;
            }
        }

        System.out.println(result);
    }

    static int _7_1_binarySearch(int[] arr, int target, int start, int end) {

        if (start > end) return -1;

        int mid = (start + end) / 2;

        if (arr[mid] == target) return mid;
            //중간점 > 타겟 = 왼쪽부터
        else if (arr[mid] > target) return _7_1_binarySearch(arr, target, start, mid - 1);

        else return _7_1_binarySearch(arr, target, mid + 1, end);

    }

    static int binarySearchRecursive(int arr[], int target, int start, int end) {

        if (start > end) return -1;

        int mid = (start + end) / 2;
        // 찾은 경우 중간점 인덱스 반환
        if (arr[mid] == target) return mid;
            // 중간점의 값보다 찾고자 하는 값이 작은 경우 왼쪽 확인
        else if (arr[mid] > target) return binarySearchRecursive(arr, target, start, mid - 1);
            // 중간점의 값보다 찾고자 하는 값이 큰 경우 오른쪽 확인
        else return binarySearchRecursive(arr, target, mid + 1, end);
    }

    static int binarySearch(int[] arr, int target, int start, int end) {

        while (start <= end) {

            int mid = (start + end) / 2;

            if (arr[mid] == target) return mid;

            if (arr[mid] > target) end = mid - 1;

            else start = mid + 1;
        }
        return -1;
    }
}
