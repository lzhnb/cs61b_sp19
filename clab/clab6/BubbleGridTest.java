import org.junit.Test;
import static org.junit.Assert.*;

public class BubbleGridTest {

    @Test
    public void testBasic() {
        int[][] grid = {{1, 0, 0, 0},
                {1, 1, 1, 0}};
        int[][] darts = {{1, 0}};
        int[] expected = {2};
        validate(grid, darts, expected);
    }

    @Test
    public void testBasic2() {
        int[][] grid2 = {{1, 0, 0, 0},
                {1, 0, 0, 0},
                {1, 1, 1, 1},
                {0, 0, 1, 0},
                {0, 0, 1, 0}};
        int[][] darts2 = {{2, 2}, {1, 0}};
        int[] expected2 = {3, 2};

        validate(grid2, darts2, expected2);
    }

    @Test
    public void testBasic3() {
        int[][] grid3 = {{1, 0, 0, 0},
                {1, 0, 0, 0},
                {1, 1, 1, 1},
                {0, 0, 1, 0},
                {0, 0, 1, 0}};
        int[][] darts3 = {{2, 2}, {2, 2}, {1, 0}};
        int[] expected3 = {3, 0, 2};

        validate(grid3, darts3, expected3);
    }

    private void validate(int[][] grid, int[][] darts, int[] expected) {
        BubbleGrid sol = new BubbleGrid(grid);
        assertArrayEquals(expected, sol.popBubbles(darts));
    }
}
