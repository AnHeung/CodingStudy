public class MinCostClimbingStairs {

    public static int minCostClimbingStairs (int [] cost){

        int case1 = 0 , case2 = 0, current = 0;

        for(int i = cost.length -1; i >=0; --i){
            current = cost[i] + Math.min(case1 , case2);
            case2 = case1;
            case1 = current;
        }
        return Math.min(case1 ,case2);
    }

    public static void main(String[] args) {
        int [] cost = {1,1,100, 2, 3, 10 ,2,3,4,5,100,6};
        int result = minCostClimbingStairs(cost);
        System.out.println(result);
    }
}
