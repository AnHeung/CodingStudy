package test;

import java.util.*;

public class Chapter6 {


    public static void main(String[] args) {
//        int n = 10;
//        int[] arr = {7, 5, 9, 0, 3, 1, 6, 2, 4, 8};
//
//        quickSort(arr, 0, n - 1);
//
//        for(int i = 0; i < n; i++) {
//            System.out.print(arr[i] + " ");
//        }

//        countSort();
//        _6_2_sol();
        _6_3();
    }

    void selectSort() {

        int[] arr = {7, 5, 9, 0, 3, 1, 6, 2, 4, 8};

        //전체를 검색해서 작은값과 시작점의 위치를 스왑 (전체검색 O(N'2) 의 복잡도)
        for (int i = 0; i < arr.length; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[min] > arr[j]) min = j;
            }
            int temp = arr[i];
            arr[i] = arr[min];
            arr[min] = temp;
        }
    }

    void insertSort() {
        int[] arr = {7, 5, 9, 0, 3, 1, 6, 2, 4, 8};

        // i 부터 0으로 이동하면서 자기보다 작은값이 있으면 둘을 변경 만약 작은값이 없으면 탈출 (운좋으면 O(N)으로 끝나고 운이 나쁘면 O(N'2)의 복잡도)
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[j] < arr[j - 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                } else {
                    //해당부분이 중요
                    break;
                }
            }
        }
    }


/*
배열의 시작과 끝 잡기
시작점에 첫번쨰 값을 피벗(기준점)으로 설정
왼쪽은 시작점보다 하나앞부터 끝점은 그자리부터 시작
왼쪽에선 기준점 보다 큰값이 나올떄 까지 이동
오른쪽에선 기준점이 작은값이 나올떄 까지 이동
왼쪽값이 오른쪽 값보다  작게 나왔다면 각각 위치의 값을 스왑
반대라면 피벗값과 뽑은 오른쪽값을 체인지
그뒤에 왼쪽은 시작점 그대로 끝점만 오른쪽기준점 으로 하나씩 줄여가며 비교
오른쪽은 시작점을 오른쪽값에서 하나씩 늘리고 끝점만 그대로 늘려가며 비교
 int[] arr = {7, 5, 9, 0, 3, 1, 6, 2, 4, 8};
*/

    public static void quickSort(int[] arr, int start, int end) {
        if (start >= end) return; // 원소가 1개인 경우 종료
        int pivot = start; // 피벗은 첫 번째 원소
        int left = start + 1;
        int right = end;
        while (left <= right) {
            // 피벗보다 큰 데이터를 찾을 때까지 반복
            while (left <= end && arr[left] <= arr[pivot]) left++;
            // 피벗보다 작은 데이터를 찾을 때까지 반복
            while (right > start && arr[right] >= arr[pivot]) right--;
            // 엇갈렸다면 작은 데이터와 피벗을 교체
            if (left > right) {
                int temp = arr[pivot];
                arr[pivot] = arr[right];
                arr[right] = temp;
            }
            // 엇갈리지 않았다면 작은 데이터와 큰 데이터를 교체
            else {
                int temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
            }
        }
        // 분할 이후 왼쪽 부분과 오른쪽 부분에서 각각 정렬 수행
        quickSort(arr, start, right - 1);
        quickSort(arr, right + 1, end);
    }

    /*
    - 각각 숫자가 중첩되는 갯수만큼 해당 인덱스번호에 표시하고 뿌려줌 시간복잡도는 O(Nlog2N) 이 나옴
    - 계수정렬은 100만 미만의 숫자로만 할때 효과적임.
    - 계수정렬은 상황에 따라서 비효율적인 경우가 있는데 예를 들어 숫자가 0과 99999뭐 이런식으로 두개만 있다면 구지 필요없는 리스트 사이즈를 만들어야함
    */
    static void countSort() {

        int n = 15;
        int MAX_VALUE = 9; // 숫자중 가장큰 값

        int[] arr = {7, 5, 9, 0, 3, 1, 6, 2, 9, 1, 4, 8, 0, 5, 2};

        int[] cnt = new int[MAX_VALUE + 1];

        for (int i = 0; i < arr.length; i++) {
            cnt[arr[i]]++;
        }

        for (int i = 0; i < MAX_VALUE; i++) {
            for (int j = 0; j < cnt[i]; j++) {
                System.out.println(i);
            }
        }
    }

    static void _6_1() {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            int x = sc.nextInt();
            arr[i] = x;
        }

        //선택정렬
//        for(int i = 0; i < n; i++){
//            int maxIdx = i;
//            for(int j = i; j < n; j++){
//                if(arr[maxIdx] < arr[j]) maxIdx = j;
//            }
//            int temp = arr[maxIdx];
//            arr[maxIdx] = arr[i];
//            arr[i] = temp;
//        }

        //삽입정렬
//        for(int i = 0; i < n; i++){
//            for(int j = i ; j > 0 ; j--){
//                if(arr[j] > arr[j-1]){
//                    int temp = arr[j-1];
//                    arr[j-1] = arr[j];
//                    arr[j] = temp;
//                }else{
//                    break;
//                }
//            }
//        }

        _6_1_quickSort(arr, 0, n - 1);

        for (int i : arr) {
            System.out.print(i);
            System.out.print(" ");
        }
    }

    static void _6_1_quickSort(int[] arr, int start, int end) {

        if (start >= end) return; //원소가 한개

        int pivot = start;
        int left = start + 1;
        int right = end;

        while (left <= right) {

            while (left <= end && arr[pivot] <= arr[left]) left++;

            while (right > start && arr[pivot] >= arr[right]) right--;

            if (left > right) {
                int temp = arr[pivot];
                arr[pivot] = arr[right];
                arr[right] = temp;
            } else {
                int temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
            }
        }

        _6_1_quickSort(arr, start, right - 1);
        _6_1_quickSort(arr, right + 1, end);
    }

    // 클래스 객체를 쓸생각을 안하고 hashMap 이나 배열등 어떻게든 countSort 를 사용해서 점수와 이름을 저장하려 했음;
    static void _6_2() {

        Scanner sc = new Scanner(System.in);
        int stuNo = sc.nextInt();
        sc.nextLine();
        String[] nameArr = new String[101];

        for (int i = 0; i < stuNo; i++) {

            String line = sc.nextLine();

            String name = line.replaceAll("[0-9]", "").trim();
            String score = line.replaceAll("[^0-9]", "");
            String currentName = nameArr[Integer.parseInt(score)] == null ? "" : nameArr[Integer.parseInt(score)];
            if ("".equals(currentName)) nameArr[Integer.parseInt(score)] = name;
            else nameArr[Integer.parseInt(score)] += "/" + name;
        }

        for (int i = nameArr.length - 1; i > 0; i--) {

            if (nameArr[i] != null) {
                for (int j = 0; j < nameArr[i].split("/").length; j++) {
                    System.out.print(nameArr[i].split("/")[j] + " ");
                }
            }
        }
    }

    static class _6_2_sol_Student implements Comparable<_6_2_sol_Student> {
        int score;
        String name;

        _6_2_sol_Student(int score, String name) {
            this.score = score;
            this.name = name;
        }

        @Override
        public int compareTo(_6_2_sol_Student other) {
            if (this.score > other.score) return -1;
            return 1;
        }
    }

    static void _6_2_sol() {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();

        ArrayList<_6_2_sol_Student> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String name = sc.next();
            int score = sc.nextInt();
            list.add(new _6_2_sol_Student(score, name));
        }
        Collections.sort(list);

        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i).name + " ");
        }
    }

    static void _6_3() {

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int k = sc.nextInt();

        Integer[] arr1 = new Integer[n];
        Integer[] arr2 = new Integer[n];

        sc.nextLine();

        String line = sc.nextLine();
        for (int j = 0; j < line.split(" ").length; j++) {
            arr1[j] = line.split(" ")[j].charAt(0) - '0';
        }

        String line2 = sc.nextLine();
        for (int j = 0; j < line2.split(" ").length; j++) {
            arr2[j] = line2.split(" ")[j].charAt(0) - '0';
        }

        //sort 함수를 이용해서 풀면 빠르다. reverseOrder
        Arrays.sort(arr1);
        Arrays.sort(arr2, Collections.reverseOrder());

        int result = 0;

        for(int i = 0; i < n; i++){
            if(i < k) result+= arr2[i];
            else result+= arr1[i];
        }

        System.out.println(result);
    }
}


