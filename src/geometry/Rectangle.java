
package geometry;

/**
 * @author Tomer .
 * The class Rectangle represents a two-dimensional colored ball. The class will be used to represent objects in the
 * game.
 */
public class Rectangle {

    //The width of the rectangle.
    private double width;

    //The height of the rectangle.
    private double height;

    //The upper left point of the rectangle.
    private Point upperLeft;

    //The upper side line of the rectangle.
    private Line upperSide;

    //The bottom side line of the rectangle.
    private Line bottomSide;

    //The left side line of the rectangle.
    private Line leftSide;

    //The right side line of the rectangle.
    private Line rightSide;

    /**
     * A constructor method for Rectangle class.It is creates a new instance of Point.
     *
     * @param upperLeft - the upper left point of the rectangle.
     * @param width     - the width of the rectangle.
     * @param height    - the height of the rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.width = width;
        this.height = height;
        this.upperLeft = upperLeft;

        //Creating the lines of the rectangle.
        this.upperSide = new Line(upperLeft.getX(), upperLeft.getY(), upperLeft.getX() + width, upperLeft.getY());
        this.bottomSide = new Line(upperLeft.getX(), upperLeft.getY() + height, upperLeft.getX() + width,
                upperLeft.getY() + height);
        this.leftSide = new Line(upperLeft.getX(), upperLeft.getY(), upperLeft.getX(), upperLeft.getY() + height);
        this.rightSide = new Line(upperLeft.getX() + width, upperLeft.getY(), upperLeft.getX() + width,
                upperLeft.getY() + height);
    }

    /**
     * A getter method for the width field of the rectangle.
     *
     * @return the width of the rectangle.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * A getter method for the height field of the rectangle.
     *
     * @return the height of the rectangle.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * A getter method for the upperLeft field of the rectangle.
     *
     * @return the upper left point of the rectangle.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * A getter method for the upperSide field of the rectangle.
     *
     * @return the upper line of the rectangle.
     */
    public Line getUpperSide() {
        return this.upperSide;
    }

    /**
     * A getter method for the bottomSide field of the rectangle.
     *
     * @return the bottom line of the rectangle.
     */
    public Line getBottomSide() {
        return this.bottomSide;
    }

    /**
     * A getter method for the leftSide field of the rectangle.
     *
     * @return the left line of the rectangle.
     */
    public Line getLeftSide() {
        return this.leftSide;
    }

    /**
     * A getter method for the rightSide field of the rectangle.
     *
     * @return the right line of the rectangle.
     */
    public Line getRightSide() {
        return this.rightSide;
    }

    /**
     * The method finds the intersection points of an rectangle with a line.
     *
     * @param line - an instance of line.
     * @return a List of intersection points with the line. The list might be empty if there are no intersections.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        java.util.List<Point> intersections = new java.util.ArrayList<Point>();
        Line[] sides = {this.upperSide, this.bottomSide, this.leftSide, this.rightSide};
        Point temp;

        /*
         * The loops checks if each of the rectangle sides is intersecting with the line. The intersection points are
         * added to the list.
         */
        for (int i = 0; i < sides.length; i++) {
            temp = sides[i].intersectionWith(line);
            if (temp != null) {
                intersections.add(temp);
            }
        }
        return intersections;
    }
}
