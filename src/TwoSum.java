import java.util.HashMap;

class Solution2 {

    public int[] twoSum1(int[] numArr, int target) {
        for (int i = 0; i < numArr.length; i++) {
            for (int j = 0; j < numArr.length; j++) {
                if (target == numArr[i] + numArr[j]) {
                    return new int[]{i, j};
                }
            }
        }
        throw new IllegalArgumentException("No Two Sum Solution");
    }

    public int[] twoSum2(int[] numArr, int target) {

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < numArr.length; i++) {
            map.put(numArr[i], i);
        }
        for (int i = 0; i < numArr.length; i++) {
            if (map.containsKey(target - numArr[i])) return new int[]{ i ,map.get(target - numArr[i])};
        }
        throw new IllegalArgumentException("No Two Sum Solution");
    }

    public int[] twoSum3(int[] numArr, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < numArr.length; i++) {
            if (map.containsKey(target - numArr[i])) return new int[]{map.get(target - numArr[i]), i};
            map.put(numArr[i], i);
        }
        throw new IllegalArgumentException("No Two Sum Solution");
    }
}

class Test2 {

    public static void main(String[] args) {
        int[] numArr = {6, 0, 4, 3, 8, 4, 7, 1, 2};
        Solution2 sol = new Solution2();
        int[] result = sol.twoSum2(numArr, 5);
        System.out.println(result[0] + "," + result[1]);
    }
}