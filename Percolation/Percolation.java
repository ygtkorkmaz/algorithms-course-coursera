/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int topRoot = 0;
    private int bottomRoot = 0;
    private int openSites = 0;


    private int gridDimension;
    private WeightedQuickUnionUF unionFind;
    private WeightedQuickUnionUF fullSites;
    private boolean[][] sites;

    public Percolation(int n) {
        if (n < 1) {
            throw new IllegalArgumentException();
        }

        gridDimension = n;
        unionFind = new WeightedQuickUnionUF(n * n + 2);
        fullSites = new WeightedQuickUnionUF(n * n + 1);
        topRoot = n * n;
        bottomRoot = n * n + 1;
        sites = new boolean[n][n];

    }

    public void open(int row, int column) {
        check(row, column);
        if (isOpen(row, column)) return;
        int index = index(row, column);

        //check top row, and make upward union
        if (row == 1) {
            unionFind.union(index, topRoot);
            fullSites.union(index, topRoot);
        }
        else {
            int topRow = row - 1;
            unionIfOpen(topRow, column, index);
        }

        //check bottom row and make downward union
        if (row == gridDimension) {
            unionFind.union(index, bottomRoot);
        }
        else {
            int bottomRow = row + 1;
            unionIfOpen(bottomRow, column, index);
        }

        //check left side and make left union
        if (column > 1) {
            int leftColumn = column - 1;
            unionIfOpen(row, leftColumn, index);
        }

        //check right side and make right union
        if (column < gridDimension) {
            int rightColumn = column + 1;
            unionIfOpen(row, rightColumn, index);
        }

        sites[row - 1][column - 1] = true;
        openSites++;

    }


    public int numberOfOpenSites() {
        return openSites;
    }

    private void unionIfOpen(int row, int column, int indexToUnion) {
        if (isOpen(row, column)) {
            int index = index(row, column);
            unionFind.union(index, indexToUnion);
            fullSites.union(index, indexToUnion);
        }
    }

    public boolean isOpen(int row, int col) {
        check(row, col);
        return sites[row - 1][col - 1] == true;
    }

    public boolean isFull(int row, int col) {
        check(row, col);
        int ind = index(row, col);
        return unionFind.connected(ind, topRoot);
    }

    public boolean percolates() {
        return unionFind.connected(topRoot, bottomRoot);

    }

    private void check(int row, int col) {
        if (row < 1 || row > gridDimension || col < 1 || col > gridDimension) {
            throw new IllegalArgumentException();
        }
    }

    private int index(int row, int col) {
        return gridDimension * (row - 1) + (col - 1);
    }
}
