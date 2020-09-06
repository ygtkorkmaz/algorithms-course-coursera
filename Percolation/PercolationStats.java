/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private double mean = -1;
    private double stddev = -1;
    private double[] thresholds;
    private static final double CONFIDENCE_95 = 1.96;
    private int trialnum;

    public PercolationStats(int n, int trials) {
        if (n < 1 || trials < 1) {
            throw new IllegalArgumentException();
        }

        thresholds = new double[trials];
        trialnum = trials;

        for (int i = 0; i < trials; i++) {
            Percolation percol = new Percolation(n);
            while (!percol.percolates()) {
                int row = StdRandom.uniform(n) + 1;
                int col = StdRandom.uniform(n) + 1;

                percol.open(row, col);
            }
            thresholds[i] = percol.numberOfOpenSites() / (double) (n * n);
        }
    }

    public double mean() {
        if (mean == -1) {
            mean = StdStats.mean(thresholds);
        }

        return mean;
    }

    public double stddev() {
        if (stddev == -1) {
            stddev = StdStats.stddev(thresholds);
        }

        return stddev;
    }

    // low  endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - (CONFIDENCE_95 * stddev()) / Math.sqrt(trialnum);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + (CONFIDENCE_95 * stddev()) / Math.sqrt(trialnum);
    }

    // main method
    public static void main(String[] args) {

        if (args.length < 2) {
            throw new IllegalArgumentException();
        }

        int gridSize = 0;
        int trials = 0;

        gridSize = Integer.parseInt(args[0]);
        trials = Integer.parseInt(args[1]);


        PercolationStats stats = new PercolationStats(gridSize, trials);

        StdOut.println("mean                    = " + stats.mean());
        StdOut.println("stddev                  = " + stats.stddev());
        StdOut.println(
                "95% confidence interval = [" + stats.confidenceLo() + ", " + stats.confidenceHi()
                        + "]");
    }

}
