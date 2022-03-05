
import animation.AnimationRunner;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import run.GameFlow;
import run.Level1;
import run.Level2;
import run.Level3;
import run.Level4;
import run.LevelInformation;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tomer .
 * The Ass6Game class initializes and runs the game.
 */
public class Ass6Game {

    //The width of the gui window of the game.
    static final int GUI_WIDTH = 800;

    //The height of the gui window of the game.
    static final int GUI_HEIGHT = 600;

    //The number of frames per second that should be displayed by the animation of the game.
    static final int FRAMES_PER_SECOND = 60;

    /**
     * The main method creates an instance of a game, initializes a new game and makes the animation run.
     *
     * @param args - optional input arguments.
     */
    public static void main(String[] args) {

        /*
         * Setting the gui window of the game and it's keyboard. The GUI and keyboard objects are supplied to us with
         * the biuoop package.
         */
        GUI gui = new GUI("Arkanoid", GUI_WIDTH, GUI_HEIGHT);
        KeyboardSensor keyboard = gui.getKeyboardSensor();

        //Creating the animation runner that will handle the animation of the game.
        AnimationRunner animationRunner = new AnimationRunner(gui, FRAMES_PER_SECOND);

        //Creating the levels list that the game will run.
        List<LevelInformation> levels = new ArrayList<LevelInformation>();

        /*
         * If arguments were passed, the game will include the levels which were stated in the arguments in the
         * order they appear in the arguments array.
         */
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("1")) {
                levels.add(new Level1());
            } else if (args[i].equals("2")) {
                levels.add(new Level2());
            } else if (args[i].equals("3")) {
                levels.add(new Level3());
            } else if (args[i].equals("4")) {
                levels.add(new Level4());
            }
        }

        //If no valid arguments were passed the game will include the 4 levels in their ordinal order.
        if (levels.size() == 0) {
            levels.add(new Level1());
            levels.add(new Level2());
            levels.add(new Level3());
            levels.add(new Level4());
        }

        //Creating the object that will run the game level and then running the game itself.
        GameFlow gameFlow = new GameFlow(animationRunner, keyboard);
        gameFlow.runLevels(levels);
    }
}
