/* *****************************************************************************
 *  Name: Yigit Korkmaz
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class FastCollinearPoints {

    private LineSegment[] segments = null;

    public FastCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException();
        }
        Point[] pointsCopy = new Point[points.length];
        for (int i = 0; i < points.length; i++) {
            Point p = points[i];
            if (p == null) {
                throw new IllegalArgumentException();
            }
            pointsCopy[i] = p;
        }

        Arrays.sort(pointsCopy);
        List<LineSegment> allSegments = new LinkedList<LineSegment>();
        Point prevPoint = null;

        for (int i = 0; i < pointsCopy.length; i++) {
            Point p = pointsCopy[i];

            if (prevPoint != null && p.compareTo(prevPoint) == 0) {
                throw new IllegalArgumentException();
            }
            else {
                prevPoint = p;
            }

            Point[] slopeOrderedPoints = pointsCopy.clone();
            Arrays.sort(slopeOrderedPoints, p.slopeOrder());

            double lastSlope = Double.NEGATIVE_INFINITY;
            int slopeStartIndex = 0;

            for (int j = 1; j < slopeOrderedPoints.length; j++) {
                Point q = slopeOrderedPoints[j];
                double currentSlope = p.slopeTo(q);

                boolean lastPoint = j == slopeOrderedPoints.length - 1;

                if (Double.compare(currentSlope, lastSlope) != 0) {
                    if (j - slopeStartIndex >= 3) {
                        if (p.compareTo(slopeOrderedPoints[slopeStartIndex]) <= 0) {
                            LineSegment segment = new LineSegment(p, slopeOrderedPoints[j - 1]);
                            allSegments.add(segment);
                        }
                    }

                    slopeStartIndex = j;
                }
                else if (lastPoint) {
                    if (j - slopeStartIndex >= 2) {
                        if (p.compareTo(slopeOrderedPoints[slopeStartIndex]) <= 0) {
                            LineSegment segment = new LineSegment(p, q);
                            allSegments.add(segment);
                        }
                    }
                }

                lastSlope = currentSlope;
            }
        }

        segments = allSegments.toArray(new LineSegment[allSegments.size()]);

    }

    public int numberOfSegments() {
        return segments.length;
    }

    public LineSegment[] segments() {
        return segments.clone();
    }

    public static void main(String[] args) {
        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
