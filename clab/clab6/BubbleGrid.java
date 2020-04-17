/**
 * 1. The hit one is 0, it can connect nothing to the ceiling.
 * 2. The hit one is 1, but it cannot connect to top, then no
 * one can connect to top by it.
 * 3. The hit one is 1, it can connect to the ceiling, but the near
 * ones can connect to the ceiling by other bubbles too; for this case
 * when remove the hit one, ones that connected to it will not drop.
 * 4. The hit one is 1, it can connect to the ceiling, and the near
 * ones can connect to the ceiling by it and there are no other ways
 * for them to connect to the ceiling; for this case, there will be
 * some bubbles drop after the hit one been removed.
 */
public class BubbleGrid {
    private int[][] grid;
    private UnionFind unionGrid;
    private int rowNum;
    private int colNum;
    private int ceiling = 0;
    private int[] dx = {-1, 1, 0, 0};
    private int[] dy = {0, 0, -1, 1};

    /* Create new BubbleGrid with bubble/space locations specified by grid.
     * Grid is composed of only 1's and 0's, where 1's denote a bubble, and
     * 0's denote a space. */
    public BubbleGrid(int[][] grid) {
        this.grid = grid;
        rowNum = grid.length;
        colNum = grid[0].length;
        unionGrid = new UnionFind(rowNum * colNum + 1);
    }

    private int xy2index(int row, int col) {
        return row * colNum + col;
    }

    private void orthogonallyUnion(int[][] grid, int row, int col) {
        for (int i = 0; i < 4; i++) {
            if ((row + dx[i] >= 0 && row + dx[i] < rowNum) && (col + dy[i] >= 0 && col + dy[i] < colNum)) {
                if (grid[row + dx[i]][col + dy[i]] == 1) {
                    unionGrid.union(xy2index(row, col), xy2index(row + dx[i], col + dy[i]));
                }
            }
        }
    }

    private void gridToUnionGrid(int[][] grid) {
        for (int row = 0; row < rowNum; row++) {
            for (int col = 0; col < colNum; col++) {
                if (grid[row][col] == 1) {
                    if (row == 0) {
                        unionGrid.union(rowNum * colNum, col);
                    } else {
                        orthogonallyUnion(grid, row, col);
                    }
                }
            }
        }
    }

    /* Returns an array whose i-th element is the number of bubbles that
     * fall after the i-th dart is thrown. Assume all elements of darts
     * are unique, valid locations in the grid. Must be non-destructive
     * and have no side-effects to grid. */
    public int[] popBubbles(int[][] darts) {
        // define count list
        int[] count = new int[darts.length];
        int[] same_dart = new int[darts.length];
        // define temp grid
        int[][] gridTemp = new int[rowNum][colNum];
        for (int i = 0; i < rowNum; i++) {
            gridTemp[i] = grid[i].clone();
        }

        // avoid same dart
        for (int i = 0; i < darts.length; i++) {
            for (int j = i + 1; j < darts.length; j++) {
                if (darts[i][0] == darts[j][0] && darts[i][1] == darts[i][1]) {
                    same_dart[j] = 1;
                } else {
                    same_dart[j] = 0;
                }
            }
        }

        for (int[] dart : darts) {
            gridTemp[dart[0]][dart[1]] = 0;
        }

        gridToUnionGrid(gridTemp);
        int connectToCeiling = unionGrid.sizeOf(rowNum * colNum);

        for (int i = darts.length - 1; i >= 0; i--) {
            int r = darts[i][0];
            int c = darts[i][1];
            if (grid[r][c] == 0 || same_dart[i] == 1) {
                count[i] = 0;
            } else {
                gridTemp[r][c] = 1;
                if (r == 0) {
                    unionGrid.union(rowNum * colNum, xy2index(r, c));
                }
                orthogonallyUnion(gridTemp, r, c);
                int cur = unionGrid.sizeOf(rowNum * colNum);
                int inc = cur - connectToCeiling;
                if (inc != 0) {
                    count[i] = inc - 1;
                } else {
                    count[i] = 0;
                }
                connectToCeiling = cur;
            }
        }
        return count;
    }
}
