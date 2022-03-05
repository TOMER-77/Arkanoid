
package spritesandcollidables;


import biuoop.DrawSurface;

import java.util.List;

/**
 * @author Tomer .
 * The Game environment holds a collection of all the sprite objects of the game.
 */
public class SpriteCollection {

    //A list of all the sprite objects of the game.
    private java.util.List<Sprite> spriteList;

    /**
     * A constructor method of SpriteCollection class. It creates a instance of a sprites collection..
     */
    public SpriteCollection() {
        this.spriteList = new java.util.LinkedList<Sprite>();
    }

    /**
     * A getter method for the spriteList field of the game environment.
     *
     * @return a list of the sprite objects of the game.
     */
    public List<Sprite> getSpriteList() {
        return this.spriteList;
    }

    /**
     * The method adds a sprite object to the list of sprite objects of the sprite collection.
     *
     * @param s - the sprite object to be added to the sprite collection.
     */
    public void addSprite(Sprite s) {
        this.spriteList.add(s);
    }

    /**
     * The method removes a sprite from the list of sprites of the sprite collection.
     *
     * @param s - the sprite to be removed from the sprites collection.
     */
    public void removeSprite(Sprite s) {
        this.spriteList.remove(s);
    }

    /**
     * The method draw all the the sprites in the sprites collection list.
     *
     * @param d - the surface on which the sprites will be drawn. DrawSurface class is part of the biuoop package.
     */
    public void drawAllOn(DrawSurface d) {
        for (int i = 0; i < this.spriteList.size(); i++) {
            this.spriteList.get(i).drawOn(d);
        }
    }


    /**
     * The method applies the timePassed method on all the sprites in the sprites collection list.
     */
    public void notifyAllTimePassed() {
        for (int i = 0; i < this.spriteList.size(); i++) {
            this.spriteList.get(i).timePassed();
        }
    }
}
