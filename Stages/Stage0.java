package engineshootout.Stages;

import city.cs.engine.BodyImage;
import engineshootout.Main.EngineShootout;
import engineshootout.Main.GameStage;
import org.jbox2d.common.Vec2;

import engineshootout.GUI.MainMenu;

/**
 *
 * @author james
 */
public class Stage0 extends GameStage {

    /**This is the main menu "stage" of the game. The abstract values are irrelevant (except for the music path) as the other objects are not populated when the stage = 0.
     * @param game The game class
     * @param menu The main menu class
     */
    @Override
    public void populate(EngineShootout game, MainMenu menu) {
        super.populate(game, game.world.getMenu()); 
    }

    /**
     *
     * @return A value that changes the size of the ball
     */
    @Override
    public float ballSizeConstant() {
        return -1;
    }

    /**
     *
     * @return the body image of the ball
     */
    @Override
    public BodyImage ballImage() {
        return null;
    }

    /**
     *
     * @return the number of goals required to progress to the next level
     */
    @Override
    public int goalTarget() {
        return -1;
    }

    /**
     *
     * @return boolean - if the players have reached the goal requirement or not
     */
    @Override
    public boolean isCompleted() {
        return true;
    }

    /**
     *
     * @return the starting position of the left (player 1) car
     */
    @Override
    public Vec2 leftCarPos() {
        return null;
    }

    /**
     *
     * @return the starting position of the right (player 2) car
     */
    @Override
    public Vec2 rightCarPos() {
        return null;
    }

    /**
     *
     * @return the starting position of the ball
     */
    @Override
    public Vec2 ballPos() {
        return null;
    }

    /**
     *
     * @return the image data path for the left car
     */
    @Override
    public String getLeftCarImage() {
        return null;
    }

    /**
     *
     * @return the image data path for the right car
     */
    @Override
    public String getRightCarImage() {
        return null;
    }

    /**
     *
     * @return the audio data path for the background music
     */
    @Override
    public String musicPath() {
        return "data/audio/music/menuMusic.wav";
    }

    /**
     *
     * @return the density of the ball
     */
    @Override
    public float ballDensity() {
        return -1;
    }

    /**
     *
     * @return the restitution of the ball
     */
    @Override
    public float ballRestitution() {
        return -1;
    }
}
