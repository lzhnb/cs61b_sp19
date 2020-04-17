package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[][] grid;
    private int size;
    private int top;
    private int bottom;
    private WeightedQuickUnionUF uf;
    private WeightedQuickUnionUF ufTopOnly;
    private int numOpenSites = 0;

    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException();
        }
        grid = new boolean[N][N];
        size = N;
        top = 0;
        bottom = N * N + 1;
        uf = new WeightedQuickUnionUF(N * N + 2);
        ufTopOnly = new WeightedQuickUnionUF(N * N + 1);
    }

    private int xy2Index (int row, int col) {
        return row * size + col + 1;
    }

    private void validate (int row, int col) {
        if (row < 0 || row >= size || col < 0 || col >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    // open the site (row, col) if it is not open already
    public void open(int row, int col) {
        validate(row, col);
        if (!isOpen(row, col)) {
            grid[row][col] = true;
            numOpenSites += 1;
        }
        if (row == 0) {
            ufTopOnly.union(top, xy2Index(row, col));
        }
        if (row == size - 1) {
            uf.union(bottom, xy2Index(row, col));
        }
        if (row > 0 && isOpen(row - 1, col)) {
            uf.union(xy2Index(row, col), xy2Index(row - 1, col));;
            ufTopOnly.union(xy2Index(row, col), xy2Index(row - 1, col));
        }
        if (row < size - 1 && isOpen(row + 1, col)) {
            uf.union(xy2Index(row, col), xy2Index(row +1, col));;
            ufTopOnly.union(xy2Index(row, col), xy2Index(row + 1, col));
        }
        if (col > 0 && isOpen(row, col - 1)) {
            uf.union(xy2Index(row, col), xy2Index(row, col - 1));;
            ufTopOnly.union(xy2Index(row, col), xy2Index(row, col - 1));
        }
        if (col < size - 1 && isOpen(row, col + 1)) {
            uf.union(xy2Index(row, col), xy2Index(row, col - 1));;
            ufTopOnly.union(xy2Index(row, col), xy2Index(row, col + 1));
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validate(row, col);
        return grid[row][col];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        validate(row, col);
        return ufTopOnly.connected(top, xy2Index(row, col));
    }

    // number of open sites
    public int numberOfOpenSites() {
        return numOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return uf.connected(bottom, top);
    }

    // use for unit testing (not required, but keep this here for the autograder)
    public static void main(String[] args) {

    }

}
