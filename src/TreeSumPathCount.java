import java.util.ArrayList;
import java.util.HashMap;

class Tree6 {

    class Node {

        int data;
        Node left;
        Node right;

        public Node(int data) {
            this.data = data;
        }
    }

    Node root;

    Node makeBst(int start, int end) {
        if (start > end) return null;
        int mid = (start + end) / 2;
        Node node = new Node(mid);
        node.left = makeBst(start, mid - 1);
        node.right = makeBst(mid + 1, end);
        return node;
    }


    public Tree6(int size) {
        root = makeBst(0, size - 1);
    }

    int countPathsWithSum(int targetSum) {
        return countPathsWithSum(root, targetSum);
    }

    //가장 기본적인 방법 대신 이렇게 되면 새로 노드가 들어올때마다 자식노드쪽을 모두 검색하게된다.
    int countPathsWithSum(Node root, int targetSum) {
        if (root == null) return 0;
        int pathsFromRoot = countPathsWithSumFromNode(root, targetSum, 0);
        int pathsOnLeft = countPathsWithSum(root.left, targetSum);
        int pathsOnRight = countPathsWithSum(root.right, targetSum);
        return pathsFromRoot + pathsOnLeft + pathsOnRight;
    }

    int countPathsWithSumFromNode(Node node, int targetSum, int currentSum) {
        if (node == null) return 0;
        currentSum += node.data;
        int totalPaths = 0;
        if (currentSum == targetSum) totalPaths++;
        totalPaths += countPathsWithSumFromNode(node.left, targetSum, currentSum);
        totalPaths += countPathsWithSumFromNode(node.right, targetSum, currentSum);
        return totalPaths;
    }

    // 메모리에 저장해서 푸는방법
    int countPathsWithSum2(int targetSum) {
        ArrayList<Integer> array = new ArrayList<>();
        return countPathsWithSum2(root, targetSum, array);
    }

    int countPathsWithSum2(Node root, int targetSum, ArrayList<Integer> array) {
        if (root == null) return 0;
        int totalPaths;
        addValue(array, root.data);
        totalPaths = countPaths(array, targetSum);
        totalPaths += countPathsWithSum2(root.left, targetSum, array);
        totalPaths += countPathsWithSum2(root.right, targetSum, array);
        removeLast(array);
        return totalPaths;
    }

    //현재 배열에서 targetSum 값이 존재하는지 체크하고 있으면 path에 추가
    int countPaths(ArrayList<Integer> array, int targetSum) {
        int totalPaths = 0;
        for (int i = 0; i < array.size(); i++) {
            if (array.get(i) == targetSum) totalPaths++;
        }
        return totalPaths;
    }

    //배열에 값을 업데이트 해주고 마지막에 배열에 데이터값 추가  ex) data 값 3 [1,2,3] -> [4,5,6,3]
    void addValue(ArrayList<Integer> array, int data) {
        for (int i = 0; i < array.size(); i++) {
            array.set(i, array.get(i) + data);
        }
        array.add(data);
    }

    //자식에서 부모배열로 빠져나오면서 더해준값을 빼고 업데이트 쳐줌 ex ) [4,6,3,1] -> [3,5,2]
    void removeLast(ArrayList<Integer> array) {
        int value = array.remove(array.size() - 1);
        for (int i = 0; i < array.size(); i++) {
            array.set(i, array.get(i) - value);
        }
    }

    //hashMap 을 사용해 시간복잡도를 O(n)으로 줄인 방법
    int countPathsWithSum3(int targetSum) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        hashMap.put(0, 1);
        return countPathsWithSum3(root, targetSum, 0, hashMap);
    }

    //전체 탐색을 하면서 hashMap 에 합계값을 집어넣어 만약 현재의 합계값에서 지정값을 뺀값이 hashMap 에 존재할 경우 path 값에 추가
    private int countPathsWithSum3(Node node, int targetSum, int currentSum, HashMap<Integer, Integer> hashMap) {
        if (node == null) return 0;
        currentSum += node.data;
        int key = currentSum - targetSum;
        //hashMap 안에 해당 현재값 - 지정값이 있다는 것은 지정값이 존재한다는뜻
        int totalPaths = hashMap.getOrDefault(key, 0);
        //키값을 합계로 하고 만약 합계에서 target 값이 존재할경우 hashMap 의 키값안에 존재하므로 그럴경우  totalPaths 의 값이 올라가서 최종적으로 전역으로 한번씩만 훝고 식이 종료됨.
        incrementHashMap(hashMap, currentSum, 1);
        totalPaths += countPathsWithSum3(node.left , targetSum, currentSum, hashMap);
        totalPaths += countPathsWithSum3(node.right , targetSum, currentSum, hashMap);
        incrementHashMap(hashMap, currentSum, -1);
        return totalPaths;
    }


    void incrementHashMap(HashMap<Integer, Integer> hashMap, int key, int value) {
        int newCount = hashMap.getOrDefault(key, 0) + value;
        if(newCount == 0) hashMap.remove(key);
        else hashMap.put(key, newCount);
    }
}

    /*
        ( 4 )
       /    \
      /      \
    (1)      (7)
    / \       / \
   /   \     /   \
 (0)   (2)  (5)   (8)
        \    \      \
        (3)  (6)    (9)
*/

public class TreeSumPathCount {

    public static void main(String[] args) {
        Tree6 t = new Tree6(10);
        Util.println(t.countPathsWithSum(10));
        Util.println(t.countPathsWithSum2(10));
        Util.println(t.countPathsWithSum3(10));
    }

}
