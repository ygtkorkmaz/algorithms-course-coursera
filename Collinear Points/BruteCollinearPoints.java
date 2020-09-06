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

public class BruteCollinearPoints {
    private LineSegment[] allSegments = null;

    public BruteCollinearPoints(Point[] points) {
        if (points == null) {
            throw new IllegalArgumentException();
        }
        Point[] pointsCopy = new Point[points.length];
        for (int i = 0; i < points.length; i++) {
            Point p = points[i];
            pointsCopy[i] = p;
        }

        Arrays.sort(pointsCopy);
        LinkedList<LineSegment> lineSegments = new LinkedList<LineSegment>();
        Point prevPoint = null;

        for (int i = 0; i < pointsCopy.length; i++) {
            if (prevPoint != null && pointsCopy[i].compareTo(prevPoint) == 0) {
                throw new IllegalArgumentException();
            }
            else {
                prevPoint = pointsCopy[i];
            }
            for (int j = i + 1; j < pointsCopy.length; j++) {
                for (int k = j + 1; k < pointsCopy.length; k++) {
                    for (int l = k + 1; l < pointsCopy.length; l++) {
                        Point p = pointsCopy[i];
                        Point q = pointsCopy[j];
                        Point r = pointsCopy[k];
                        Point s = pointsCopy[l];

                        if (p.slopeOrder().compare(q, r) == 0
                                && p.slopeOrder().compare(r, s) == 0) {
                            LineSegment segment = new LineSegment(p, s);
                            lineSegments.add(segment);
                        }
                    }
                }
            }

        }
        allSegments = lineSegments.toArray(new LineSegment[lineSegments.size()]);

    }

    public int numberOfSegments() {
        return allSegments.length;
    }

    public LineSegment[] segments() {
        return allSegments.clone();
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
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
