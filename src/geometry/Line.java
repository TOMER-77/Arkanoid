
package geometry;

/**
 * @author Tomer .
 * The class Line represents a two-dimensional segment of a Line with a starting and ending point.
 */

public class Line {

    //EPSILON will be used to overcome precision bugs.
    static final double EPSILON = Math.pow(10, -8);

    //The starting point of the segment.
    private Point start;

    //The ending point of the segment.
    private Point end;

    /**
     * A constructor method for Point class.It creates a new instance of Point.
     *
     * @param start - the starting point of the line.
     * @param end   - the ending point of the line.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * A constructor method for Point class. it receives the x and y coordinates of the points instead of the points
     * themselves.
     *
     * @param x1 - the x value of the first point.
     * @param y1 - the y value of the first point.
     * @param x2 - the x value of the second point.
     * @param y2 - the y value of the second point.
     */
    public Line(double x1, double y1, double x2, double y2) {

        //New points are created with the given x and y values and sent to another constructor.
        this(new Point(x1, y1), new Point(x2, y2));
    }

    /**
     * The method creates an instance of the Line which have the original edge points but the start is the point
     * with the lowest x value to make calculations easier.
     *
     * @return an instance of line which will be used for calculations.
     */
    public Line copyForCalculations() {

        /*
         * start will be the point with the lowest x value. If the x values are equal start will be the point with
         * the lowest y value.
         */
        if (start.getX() < end.getX()) {
            return this;
        }
        if (start.getX() == end.getX()) {
            if (start.getY() <= end.getY()) {
                return this;
            } else {
                return new Line(this.end, this.start);
            }
        } else {
            return new Line(this.end, this.start);
        }
    }

    /**
     * The method gets the length of the line segment.
     *
     * @return the length of the line.
     */
    public double length() {

        //The length of the line is the distance between the start and the end Points of the line.
        return this.start.distance(this.end);
    }

    /**
     * The method gets the start point of the line.
     *
     * @return the starting point of the line segment.
     */
    public Point start() {
        return this.start;
    }

    /**
     * The method gets the end point of the line.
     *
     * @return the ending point of the line segment.
     */
    public Point end() {
        return this.end;
    }

    /**
     * The method gets the middle point of the line.
     *
     * @return the middle point of the line segment.
     */
    public Point middle() {
        double midX = (this.start.getX() + this.end.getX()) / 2;
        double midY = (this.start.getY() + this.end.getY()) / 2;
        return (new Point(midX, midY));
    }

    /**
     * The method Gets the point on the line which have the highest y value.
     *
     * @return the highest point of on the line.
     */
    public Point getHighestPoint() {
        if (this.start.getY() > this.end.getY()) {
            return this.start;
        }
        return this.end;
    }

    /**
     * The method Gets the point on the line which have the lowest y value.
     *
     * @return the lowest point of on the line segment.
     */
    public Point getLowestPoint() {
        if (this.start.getY() <= this.end.getY()) {
            return this.start;
        }
        return this.end;
    }

    /**
     * The method gets the slope of the line segment.
     *
     * @return the slope of the line.
     */
    public double getSlope() {

        //checking the edge case of a vertical line.
        if (this.end.getX() - this.start.getX() == 0) {
            return Double.POSITIVE_INFINITY;
        }

        //the slope calculated from the formula: m=(y2-y1)/(x2-x1) .
        return ((this.end.getY() - this.start.getY()) / (this.end.getX() - this.start.getX()));
    }

    /**
     * The method gets the y value of the intersection of the line with the y axis.
     *
     * @return y value of the intersection with y axis.
     */
    public double getInterceptionWithYAxis() {
        double slope = this.getSlope();

        //using the formula: y=mx+b --> b=y-mx .
        return (this.end.getY() - slope * this.end.getX());
    }

    /**
     * The method gets the x value of the intersection point of two lines.
     *
     * @param other - other instance of line.
     * @return the x value of intersection point if the two lines.
     */
    public double getXOfIntersection(Line other) {

        //given two line segments that are parts of two lines that can be represented with the linear equations:
        //y1=m1x+b1 , y2=m2x+b2 we calculate the x of the intersection using the formula: x=(b2-b1)/(m1-m2) .
        return ((this.getInterceptionWithYAxis() - other.getInterceptionWithYAxis())
                / (other.getSlope() - this.getSlope()));
    }

    /**
     * The method Gets y value on a line depending on an x value. The major use of the method is getting the y of
     * intersection point between two lines after we found it's x value.
     *
     * @param x - the x value of the intersection point.
     * @return the y value of the intersection point.
     */
    public double getYOfIntersection(double x) {

        //using the liner equation: y=mx+b .
        return ((this.getSlope() * x) + this.getInterceptionWithYAxis());
    }

    /**
     * The method checks if a point which is an intersection of two lines is on a segment of these two lines. This
     * is a safer method which is written to prevent the program from crashing when one of the lines is vertical.
     *
     * @param x     - the x value of the intersection point.
     * @param other - other instance of Line.
     * @return true if the x and y of the intersections are on the lines, false is returned if not.
     */
    public boolean verticalIsOnTheLine(double x, Line other) {

        /*
         * getYOfIntersection uses the getSlope method.When the line is vertical it's slope is infinite,
         * this method checks the slope of the not vertical line.
         */
        double y = this.getYOfIntersection(x);
        Point intersection = new Point(x, y);
        return intersection.isOnTheLines(this, other);
    }

    /**
     * The method checks if two lines are intersecting on their edge points. The method assumes that the two line
     * segments are both vertical or have the same slope and the same intersection point with the y axis so these two
     * things should be checked before calling the method.
     *
     * @param other - other line instance.
     * @return true if the start of one line touches the end of the other line. If not false is returned.
     */
    public boolean isTouching(Line other) {

        /*
         * The start is necessarily the most left point on the line and the end is necessarily the most right point
         * on the line. Thus we just compare the start point of the line with the end point of the other without
         * comparing both of the two starting points or two ending points with each other.
         */
        if (this.getHighestPoint().equals(other.getLowestPoint())) {
            return true;
        }
        if (this.getLowestPoint().equals(other.getHighestPoint())) {
            return true;
        }
        return false;
    }

    /**
     * The method checks if two lines are intersecting.
     *
     * @param other - other instance of Line.
     * @return true if the lines intersects, false if not.
     */
    public boolean isIntersecting(Line other) {

        /*
         * crating instances of the lines where the start point is the edge point with the lowest x value (or lowest
         * y value if the x value of the start and end are the same, It is done to make the calculations easier.
         */
        Line copyOfThis = this.copyForCalculations();
        Line otherCopy = other.copyForCalculations();

        /*
         * Checking the case of two vertical lines or if both lines are actually points. If the lines do intersect but
         * in more than one point count as intersection for this task .Thus isTouching method only checks the edges
         * of the line. If both lines are points we check if they equal. If one of the lines is a point we check if
         * it's in the other line.
         */
        if ((copyOfThis.getSlope() == Double.POSITIVE_INFINITY)
                && (otherCopy.getSlope() == Double.POSITIVE_INFINITY)) {
            if ((copyOfThis.start.equals(copyOfThis.end)) && (otherCopy.start.equals(otherCopy.end))) {
                return copyOfThis.equals(otherCopy);
            }
            if ((copyOfThis.start.equals(copyOfThis.end))) {
                return copyOfThis.start.isOnTheLine(otherCopy);
            }
            if ((otherCopy.start.equals(otherCopy.end))) {
                return otherCopy.start.isOnTheLine(copyOfThis);
            }
            return copyOfThis.isTouching(otherCopy);
        }

        //Checking the case if the line is vertical or a point.
        if (copyOfThis.getSlope() == Double.POSITIVE_INFINITY) {
            return (otherCopy.verticalIsOnTheLine(copyOfThis.end.getX(), copyOfThis));


        }

        //Checking the case if the other line is vertical or a point.
        if (otherCopy.getSlope() == Double.POSITIVE_INFINITY) {
            return (copyOfThis.verticalIsOnTheLine(otherCopy.end.getX(), otherCopy));
        }

        //Checking the case of two lines with the same slope.
        if (copyOfThis.getSlope() == otherCopy.getSlope()) {

            /*
             * Two lines with the same slope who has different intersection point with y axis are parallel and thus
             * can not intersect.
             */
            if (copyOfThis.getInterceptionWithYAxis() != otherCopy.getInterceptionWithYAxis()) {
                return false;
            }

            /*
             * If the lines do intersect but in more than one point it doesn't count as intersection for this task.
             * Thus isTouching method only checks the edges of the line.
             */
            return copyOfThis.isTouching(otherCopy);
        }

        /*
         * for any other case the x value of the potential intersection point is calculated, then the y value is
         * calculated and the a method checks if the point is on both lines.
         */
        double x = copyOfThis.getXOfIntersection(otherCopy);
        Point intersection = new Point(x, copyOfThis.getYOfIntersection(x));
        return intersection.isOnTheLines(copyOfThis, otherCopy);
    }

    /**
     * The method gets the intersection point of two lines.
     *
     * @param other - other instance of Line.
     * @return a Point instance of the intersection point.
     */
    public Point intersectionWith(Line other) {

        /*
         * crating instances of the lines where the start point is the edge point with the lowest x value (or lowest
         * y value if the x value of the start and end are the same, It is done to make the calculations easier.
         */
        Line copyOfThis = this.copyForCalculations();
        Line otherCopy = other.copyForCalculations();

        /*
         * If the lines doesn't intersect or intersect on more than one point null is returned
         * (according to the instructions). If the lines do intersect we proceed to find the intersection point.
         */
        if (!copyOfThis.isIntersecting(otherCopy)) {
            return null;
        }

        //Checking the case of two vertical lines,two points,line and a point or two lines with the same slope.
        if (((copyOfThis.getSlope() == Double.POSITIVE_INFINITY)
                && (otherCopy.getSlope() == Double.POSITIVE_INFINITY))
                || (copyOfThis.getSlope() == otherCopy.getSlope())) {

            //If both lines are points which intersect, or one of the lines is a point we return the point itself.
            if ((copyOfThis.start().equals(copyOfThis.end())) && (otherCopy.start().equals(otherCopy.end()))) {
                return copyOfThis.start;
            }

            //If one of the lines is a point so their intersection must be the point itself.
            if ((copyOfThis.start.equals(copyOfThis.end))) {
                return copyOfThis.start;
            }
            if ((otherCopy.start.equals(otherCopy.end))) {
                return otherCopy.start;
            }

            //if both lines are not points we check which of the edge points of the lines intersect.
            if (copyOfThis.getHighestPoint().equals(otherCopy.getLowestPoint())) {
                return new Point(copyOfThis.getHighestPoint().getX(), copyOfThis.getHighestPoint().getY());
            }
            if (copyOfThis.getLowestPoint().equals(otherCopy.getHighestPoint())) {
                return new Point(copyOfThis.getLowestPoint().getX(), copyOfThis.getLowestPoint().getY());
            }
        }

        //Checking the case if the line is vertical.
        if (copyOfThis.getSlope() == Double.POSITIVE_INFINITY) {
            return new Point(copyOfThis.end.getX(), otherCopy.getYOfIntersection(copyOfThis.end.getX()));
        }

        //Checking the case if the other line is vertical.
        if (otherCopy.getSlope() == Double.POSITIVE_INFINITY) {
            return new Point(otherCopy.end.getX(), copyOfThis.getYOfIntersection(otherCopy.end.getX()));
        }

        //for other cases the x value of the intersection point is calculated, then the y value is calculated.
        double intersectionX = copyOfThis.getXOfIntersection(otherCopy);
        return new Point(intersectionX, copyOfThis.getYOfIntersection(intersectionX));
    }

    /**
     * The method checks if two lines has the same edge points.
     *
     * @param other - other instance of Line.
     * @return true if both lines equals, false if they don't.
     */
    public boolean equals(Line other) {

        //If one of the lines has a null value the lines can't be compared and false is returned.
        if ((this == null) || (other == null)) {
            return false;
        }
        if (((this.start.equals(other.start)) && (this.end.equals(other.end)))
                || ((this.start.equals(other.end)) && (this.end.equals(other.start)))) {
            return true;
        }
        return false;
    }

    /**
     * The method checks if two lines might overlap with each other.
     *
     * @param other - other line instance.
     * @return true for lines who has potential for overlapping, false if they don't.
     */
    public boolean canOverlap(Line other) {

        /*
         * In order to overlap both lines must be vertical or have the same slope and the same intersection point
         * with the y axis. This is checked in the if statement.
         */
        if (((this.getSlope() == Double.POSITIVE_INFINITY) && (other.getSlope() == Double.POSITIVE_INFINITY))
                || ((this.getSlope() == other.getSlope())
                && ((this.getInterceptionWithYAxis() == other.getInterceptionWithYAxis())))) {
            return true;
        }
        return false;
    }

    /**
     * The method checks if two lines are overlapping.
     *
     * @param other - other line instance.
     * @return true if the lines overlap, false if they don't.
     */
    public boolean isOverlapping(Line other) {

        /*
         * crating instances of the lines where the start point is the edge point with the lowest x value (or lowest
         * y value if the x value of the start and end are the same, It is done to make the calculations easier.
         */
        Line copyOfThis = this.copyForCalculations();
        Line otherCopy = other.copyForCalculations();

        /*
         * A method checks if both lines are vertical or have the same slope and the same intersection point with
         * the y axis. If they don't they can't intersect and false is returned.
         */
        if (!(copyOfThis.canOverlap(otherCopy))) {
            return false;
        }

        //if at least one of the lines is a point the "lines" can't overlap.
        if ((copyOfThis.start().equals(copyOfThis.end())) || (otherCopy.start().equals(otherCopy.end()))) {
            return false;
        }

        //Lines with the same start and end point are the equal and thus they overlap.
        if (copyOfThis.equals(otherCopy)) {
            return true;
        }

        /*
         * Assuming both line segments are vertical or have the same slope and the same intersection point with the y
         * axis (which means that both segments are part of the same infinite line) we know that both lines
         * overlap if the y value of one of the edge points of the first line is between the y value of both edge
         * points of the second line. We add epsilon when comparing the y values to avoid counting lines that
         * intersect only on the edge points as overlapping lines because of precision miscalculations.
         */
        if ((otherCopy.getLowestPoint().getY() + EPSILON < copyOfThis.getHighestPoint().getY())
                && (copyOfThis.getHighestPoint().getY() < otherCopy.getHighestPoint().getY() - EPSILON)) {
            return true;
        }
        if ((otherCopy.getLowestPoint().getY() + EPSILON < copyOfThis.getLowestPoint().getY())
                && (copyOfThis.getLowestPoint().getY() < otherCopy.getHighestPoint().getY() - EPSILON)) {
            return true;
        }
        if ((this.getLowestPoint().getY() + EPSILON < otherCopy.getHighestPoint().getY())
                && (otherCopy.getHighestPoint().getY() < copyOfThis.getHighestPoint().getY() - EPSILON)) {
            return true;
        }
        if ((this.getLowestPoint().getY() + EPSILON < otherCopy.getLowestPoint().getY())
                && (otherCopy.getLowestPoint().getY() < copyOfThis.getHighestPoint().getY() - EPSILON)) {
            return true;
        }

        /*
         * Checking the edge case of two horizontal lines. We know that both lines have the same slope so it's
         * enough to check the slope of one line. In this case both lines have the same permanent y value so we
         * check the x values of their edge points as we did before with their y values.
         */
        if (copyOfThis.getSlope() == 0) {
            if ((otherCopy.start.getX() + EPSILON < copyOfThis.end.getX())
                    && (copyOfThis.end.getX() < otherCopy.end.getX() - EPSILON)) {
                return true;
            }
            if ((otherCopy.start.getX() + EPSILON < copyOfThis.start.getX())
                    && (copyOfThis.start.getX() < otherCopy.end.getX() - EPSILON)) {
                return true;
            }
            if ((copyOfThis.start.getX() + EPSILON < otherCopy.end.getX())
                    && (otherCopy.end.getX() < copyOfThis.end.getX() - EPSILON)) {
                return true;
            }
            if ((copyOfThis.start.getX() + EPSILON < otherCopy.start.getX())
                    && (otherCopy.start.getX() < copyOfThis.end.getX() - EPSILON)) {
                return true;
            }
        }
        return false;
    }

    /**
     * The method returns the closest intersection point of the line with the rectangle to the start of the line.
     *
     * @param rect - An instance of Rectangle object.
     * @return - The closest intersection point to the start of the line or null if there is no intersection.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {

        //Receiving a list of all possible intersection points.
        java.util.List<Point> intersections = rect.intersectionPoints(this);

        //Checking for a case when there is no intersection at all.
        if (intersections.isEmpty()) {
            return null;
        }
        int i = 0;

        /*
         * We will find the closest point by comparing the distances between the intersection points and the start of
         * the line
         */
        double minimalDistance = intersections.get(i).distance(this.start);
        Point closestPoint = intersections.get(i);
        for (i = 1; i < intersections.size(); i++) {
            double distance = intersections.get(i).distance(this.start);
            if (distance < minimalDistance) {
                minimalDistance = distance;
                closestPoint = intersections.get(i);
            }
        }
        return closestPoint;
    }
}