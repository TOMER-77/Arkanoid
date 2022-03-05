
package spritesandcollidables;

import biuoop.DrawSurface;
import geometry.Point;
import geometry.Rectangle;
import run.GameLevel;

/**
 * @author Tomer .
 * The class describes backgrounds of levels in the game.
 */
public class Background implements Sprite {

    //A rectangle at the size of the background.
    private Rectangle backgroundRectangle;

    //The color of the background.
    private java.awt.Color color;

    /**
     * A constructor method of the Background class. It creates a new instance of a background.
     *
     * @param upperLeft - the upper left point from which the background is drawn.
     * @param width     - the width of the background
     * @param height    - the height of the background
     * @param color     - the color of the background.
     */
    public Background(Point upperLeft, double width, double height, java.awt.Color color) {
        this.backgroundRectangle = new Rectangle(upperLeft, width, height);
        this.color = color;

    }

    @Override
    public void drawOn(DrawSurface d) {

        //Drawing a rectangle shaped background,
        d.setColor(this.color);
        d.fillRectangle((int) backgroundRectangle.getUpperLeft().getX(),
                (int) backgroundRectangle.getUpperLeft().getY(), (int) backgroundRectangle.getWidth(),
                (int) backgroundRectangle.getHeight());
    }


    @Override
    public void timePassed() {
        return;
    }


    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
