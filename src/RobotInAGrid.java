import java.util.ArrayList;


class Constants {

    public static final boolean[][] grid = {
            {false, false, false, false},
            {false, false, false, true},
            {true, true, false, false},
            {false, false, false, false}
    };

}

class Point {

    int row;
    int col;

    public Point(int row, int col) {
        this.row = row;
        this.col = col;
    }
}

class Solution {

    public ArrayList<Point> findPath(boolean[][] grid) {
        if (grid == null || grid.length == 0) return null;
        ArrayList<Point> path = new ArrayList<>();
        int row = grid.length - 1;
        int col = grid[0].length - 1;

        if (findPath(grid, row, col, path)) return path;
        else return null;
    }

    private boolean findPath(boolean[][] grid, int row, int col, ArrayList<Point> path) {

        //해당범위(총 사이즈)를 벗어낫는지와 제한구역(true)인지를 체크
        if (!isRange(grid, row, col) || grid[row][col]) return false;

        //최초시작지점 혹은 좌측으로 한칸이동먼저 하고 결과가 false 면 위로 이동 그것도 false 면 그 전함수로 이동해서 반복
        if ((row == 0 && col == 0)
                || findPath(grid, row, col - 1, path)
                || findPath(grid, row - 1, col , path)) {

            Point p = new Point(row, col);
            path.add(p);
            return true;
        }

        return false;
    }

    private boolean isRange(boolean[][] grid, int row, int col) {
        return row >= 0 && row <= grid.length - 1 && col >= 0 && col <= grid[row].length - 1;
    }
}

class Test {

    public static void main(String[] args) {

        Solution solution = new Solution();
        ArrayList<Point> path = solution.findPath(Constants.grid);

        if (path != null) {
            for (Point p : path) {
                System.out.print(p.row + "" + p.col + " -> ");
            }
        }
    }
}

