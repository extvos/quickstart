package plus.extvos.example.refs;

import java.util.*;

public class Geometry {

    public static final double EPSILON = 0.000001;

    /**
     * Calculate the cross product of two points.
     *
     * @param a first point
     * @param b second point
     * @return the value of the cross product
     */
    public static double crossProduct(Point a, Point b) {
        return a.lat * b.lng - b.lat * a.lng;
    }

    /**
     * Check if bounding boxes do intersect. If one bounding box
     * touches the other, they do intersect.
     *
     * @param a first bounding box
     * @param b second bounding box
     * @return <code>true</code> if they intersect,
     * <code>false</code> otherwise.
     */
    public static boolean doBoundingBoxesIntersect(Point[] a, Point[] b) {
        return a[0].lat <= b[1].lat && a[1].lat >= b[0].lat && a[0].lng <= b[1].lng
            && a[1].lng >= b[0].lng;
    }

    /**
     * Checks if a Point is on a line
     *
     * @param a line (interpreted as line, although given as line
     *          segment)
     * @param b point
     * @return <code>true</code> if point is on line, otherwise
     * <code>false</code>
     */
    public static boolean isPointOnLine(LineSegment a, Point b) {
        // Move the image, so that a.first is on (0|0)
        LineSegment aTmp = new LineSegment(new Point(0, 0), new Point(
            a.second.lat - a.first.lat, a.second.lng - a.first.lng));
        Point bTmp = new Point(b.lat - a.first.lat, b.lng - a.first.lng);
        double r = crossProduct(aTmp.second, bTmp);
        return Math.abs(r) < EPSILON;
    }

    /**
     * Checks if a point is right of a line. If the point is on the
     * line, it is not right of the line.
     *
     * @param a line segment interpreted as a line
     * @param b the point
     * @return <code>true</code> if the point is right of the line,
     * <code>false</code> otherwise
     */
    public static boolean isPointRightOfLine(LineSegment a, Point b) {
        // Move the image, so that a.first is on (0|0)
        LineSegment aTmp = new LineSegment(new Point(0, 0), new Point(
            a.second.lat - a.first.lat, a.second.lng - a.first.lng));
        Point bTmp = new Point(b.lat - a.first.lat, b.lng - a.first.lng);
        return crossProduct(aTmp.second, bTmp) < 0;
    }

    /**
     * Check if line segment first touches or crosses the line that is
     * defined by line segment second.
     *
     * @param first  line segment interpreted as line
     * @param second line segment
     * @return <code>true</code> if line segment first touches or
     * crosses line second,
     * <code>false</code> otherwise.
     */
    public static boolean lineSegmentTouchesOrCrossesLine(LineSegment a,
                                                          LineSegment b) {
        return isPointOnLine(a, b.first)
            || isPointOnLine(a, b.second)
            || (isPointRightOfLine(a, b.first) ^ isPointRightOfLine(a,
            b.second));
    }

    /**
     * Check if line segments intersect
     *
     * @param a first line segment
     * @param b second line segment
     * @return <code>true</code> if lines do intersect,
     * <code>false</code> otherwise
     */
    public static boolean doLinesIntersect(LineSegment a, LineSegment b) {
        Point[] box1 = a.getBoundingBox();
        Point[] box2 = b.getBoundingBox();
        return doBoundingBoxesIntersect(box1, box2)
            && lineSegmentTouchesOrCrossesLine(a, b)
            && lineSegmentTouchesOrCrossesLine(b, a);
    }

    /**
     * Check if x is right end of l
     *
     * @param x an x-coordinate of one endpoint
     * @param l a line
     * @return <code>true</code> if p is right end of l
     * <code>false</code> otherwise
     */
    private static boolean isRightEnd(double x, LineSegment l) {
        // TODO: Do I need EPSILON here?
        return x >= l.first.lat && x >= l.second.lat;
    }

    /**
     * Get all interectionLines by applying a sweep line algorithm.
     *
     * @param lines all lines you want to check, in no order
     * @return a list that contains all pairs of intersecting lines
     */
    public static Set<LineSegment[]> getAllIntersectingLines(LineSegment[] lines) {
        // TODO: This one is buggy! See tests
        class EventPointLine implements Comparable<EventPointLine> {
            Double sortingKey;
            LineSegment line;

            public EventPointLine(double sortingKey, LineSegment line) {
                this.sortingKey = sortingKey;
                this.line = line;
            }

            @Override
            public int compareTo(EventPointLine o) {
                return sortingKey.compareTo(o.sortingKey);
            }
        }

        class SweepLineComperator implements Comparator<LineSegment> {
            @Override
            public int compare(LineSegment o1, LineSegment o2) {
                double o1FirstX = o1.first.lat < o1.second.lat ? o1.first.lng
                    : o1.second.lng;
                double o2FirstX = o2.first.lat < o2.second.lat ? o2.first.lng
                    : o2.second.lng;

                if (Math.abs(o1FirstX - o2FirstX) < EPSILON) {
                    return 0;
                } else if (o1FirstX > o2FirstX) {
                    return 1;
                } else {
                    return -1;
                }
            }
        }

        Set<LineSegment[]> intersections = new HashSet<LineSegment[]>();
        List<EventPointLine> eventPointSchedule = new ArrayList<EventPointLine>();

        for (LineSegment line : lines) {
            eventPointSchedule.add(new EventPointLine(line.first.lat, line));
            eventPointSchedule.add(new EventPointLine(line.second.lat, line));
        }

        Collections.sort(eventPointSchedule);

        SweepLineComperator comperator = new SweepLineComperator();
        TreeSet<LineSegment> sweepLine = new TreeSet<LineSegment>(comperator);

        for (EventPointLine p : eventPointSchedule) {
            // TODO: an schnittpunkten aendert sich die Reihenfolge
            // der Kanten
            if (isRightEnd(p.sortingKey, p.line)) {
                LineSegment above = sweepLine.higher(p.line);
                LineSegment below = sweepLine.lower(p.line);
                sweepLine.remove(p.line);

                if (below != null && above != null
                    && doLinesIntersect(above, below)) {
                    LineSegment[] tmp = new LineSegment[2];
                    tmp[0] = above;
                    tmp[1] = p.line;
                    intersections.add(tmp);
                }
            } else {
                if (Math.abs(p.line.first.lat - p.line.second.lat) < EPSILON) {
                    // this is a vertical line

                    for (LineSegment tmpLine : sweepLine) {
                        if (doLinesIntersect(tmpLine, p.line)) {
                            LineSegment[] tmp = new LineSegment[2];
                            tmp[0] = tmpLine;
                            tmp[1] = p.line;
                            intersections.add(tmp);
                        }
                    }
                } else {

                    // Get identical lines
                    NavigableSet<LineSegment> h = sweepLine.subSet(p.line,
                        true, p.line, true);

                    for (LineSegment tmpLine : h) {
                        if (doLinesIntersect(tmpLine, p.line)) {
                            LineSegment[] tmp = new LineSegment[2];
                            tmp[0] = tmpLine;
                            tmp[1] = p.line;
                            intersections.add(tmp);
                        }

                    }

                    sweepLine.add(p.line);

                    // check if it intersects with line above or below
                    LineSegment above = sweepLine.higher(p.line);
                    LineSegment below = sweepLine.lower(p.line);

                    if (above != null && doLinesIntersect(above, p.line)) {
                        LineSegment[] tmp = new LineSegment[2];
                        tmp[0] = above;
                        tmp[1] = p.line;
                        intersections.add(tmp);
                    }

                    if (below != null && doLinesIntersect(below, p.line)) {
                        LineSegment[] tmp = new LineSegment[2];
                        tmp[0] = below;
                        tmp[1] = p.line;
                        intersections.add(tmp);
                    }
                }
            }
        }

        /* Check if endpoints are equal */
        for (int i = 0; i < eventPointSchedule.size(); i++) {
            int j = i + 1;
            while (j < eventPointSchedule.size()
                && Math.abs(eventPointSchedule.get(i).sortingKey
                - eventPointSchedule.get(j).sortingKey) < EPSILON) {
                j += 1;

                LineSegment[] tmp = new LineSegment[2];
                tmp[0] = eventPointSchedule.get(i).line;
                tmp[1] = eventPointSchedule.get(j).line;
                if (doLinesIntersect(tmp[0], tmp[1])
                    && !intersections.contains(tmp)) {
                    intersections.add(tmp);
                }
            }
        }

        return intersections;
    }

    /**
     * Get all interectionLines by applying a brute force algorithm.
     *
     * @param lines all lines you want to check, in no order
     * @return a list that contains all pairs of intersecting lines
     */
    public static Set<LineSegment[]> getAllIntersectingLinesByBruteForce(
        LineSegment[] lines) {
        Set<LineSegment[]> intersections = new HashSet<LineSegment[]>();

        for (int i = 0; i < lines.length; i++) {
            for (int j = i + 1; j < lines.length; j++) {
                if (doLinesIntersect(lines[i], lines[j])) {
                    LineSegment[] tmp = new LineSegment[2];
                    tmp[0] = lines[i];
                    tmp[1] = lines[j];
                    intersections.add(tmp);
                }
            }
        }

        return intersections;
    }

    public static boolean isLeftBend(Point i, Point j, Point k) {
        Point pi = new Point(i.lat, i.lng);
        Point pj = new Point(j.lat, j.lng);
        Point pk = new Point(k.lat, k.lng);

        // Move pi to (0,0) and pj and pk with it
        pj.lat -= pi.lat;
        pk.lat -= pi.lat;
        pj.lng -= pi.lng;
        pk.lng -= pi.lng;
        LineSegment s = new LineSegment(pi, pj);

        // Move pj to (0,0) and pk with it
        pk.lat -= pj.lat;
        pk.lng -= pj.lng;

        return !(isPointRightOfLine(s, pk) || isPointOnLine(s, pk));
    }

    /**
     * Calculate the convex hull of points with Graham Scan
     *
     * @param points a list of points in any order
     * @return the convex hull (can be rotated)
     */
    public static List<Point> getConvexHull(List<Point> points) {
        // TODO: Doesn't work by now
        List<Point> l = new ArrayList<Point>();

        // find lowest point. If there is more than one lowest point
        // take the one that is left
        Point pLow = new Point(0, Double.POSITIVE_INFINITY);
        for (Point point : points) {
            if (point.lng < pLow.lng || (point.lng == pLow.lng && point.lat < pLow.lat)) {
                pLow = point;
            }
        }

        // Order all other points by angle
        class PointComparator implements Comparator<Point> {
            Point pLow;

            public PointComparator(Point pLow) {
                this.pLow = pLow;
            }

            private double getAngle(Point p) {
                // TODO: This is buggy
                double deltaX = pLow.lat - p.lat;
                double deltaY = pLow.lng - p.lng;
                if (deltaX < EPSILON) {
                    return 0;
                } else {
                    return deltaY / deltaX;
                }
            }

            @Override
            public int compare(Point o1, Point o2) {
                double a1 = getAngle(o1);
                double a2 = getAngle(o2);
                if (Math.abs(a1 - a2) < EPSILON) {
                    return 0;
                } else {
                    return a1 < a2 ? -1 : 1;
                }
            }
        }

        PointComparator comparator = new PointComparator(pLow);

        Collections.sort(points, comparator);

        // go through all points
        for (Point tmp : points) {
            boolean loop = true;

            while (loop) {
                if (l.size() < 3) {
                    l.add(tmp);
                    loop = false;
                } else if (!isLeftBend(l.get(l.size() - 2),
                    l.get(l.size() - 1), tmp)) {
                    l.add(tmp);
                    loop = false;
                } else {
                    l.remove(l.size() - 1);
                }
            }
        }

        return l;
    }

    public static void main(String[] args) {
        Point x1 = new Point(121.480100, 31.151753), y1 = new Point(121.465978, 31.149806);
        Point x2 = new Point(121.466050, 31.149806), y2 = new Point(121.465754, 31.150517);
        if (doLinesIntersect(new LineSegment(x1, y1), new LineSegment(x2, y2))) {
            System.out.println("doLinesIntersect:> Crossed!");
        } else {
            System.out.println("doLinesIntersect:> Not Crossed!");
        }
    }
}
