package test;

public class Chapter6 {


    public static void main(String[] args) {
        int n = 10;
        int[] arr = {7, 5, 9, 0, 3, 1, 6, 2, 4, 8};

        quickSort(arr, 0, n - 1);

        for(int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    void selectSort() {

        int[] arr = {7, 5, 9, 0, 3, 1, 6, 2, 4, 8};

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

        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[j] < arr[j - 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                } else {
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


}


