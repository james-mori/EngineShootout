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
public class Stage2 extends GameStage {

    /**This is the second level of the game.
     *
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
        return 1;
    }

    /**
     *
     * @return the body image of the ball
     */
    @Override
    public BodyImage ballImage() {
        return new BodyImage("data/objects/balls/football.png", 3.25f*ballSizeConstant());
    }

    /**
     *
     * @return the number of goals required to progress to the next level
     */
    @Override
    public int goalTarget() {
        return 2;
    }

    /**
     *
     * @return boolean - if the players have reached the goal requirement or not
     */
    @Override
    public boolean isCompleted() {
        return getScoreP1() >= goalTarget() || getScoreP2() >= goalTarget();
    }

    /**
     *
     * @return the starting position of the left (player 1) car
     */
    @Override
    public Vec2 leftCarPos() {
        return new Vec2(-20, -9.3f);
    }

    /**
     *
     * @return the starting position of the right (player 2) car
     */
    @Override
    public Vec2 rightCarPos() {
        return new Vec2(20, -9.3f);
    }

    /**
     *
     * @return the starting position of the ball
     */
    @Override
    public Vec2 ballPos() {
        return new Vec2(0, -3f);
    }

    /**
     *
     * @return the image data path for the left car
     */
    @Override
    public String getLeftCarImage() {
        return "data/objects/cars/greenCarLeft.png";
    }

    /**
     *
     * @return the image data path for the right car
     */
    @Override
    public String getRightCarImage() {
        return "data/objects/cars/blueCarRight.png";
    }

    /**
     *
     * @return the audio data path for the background music
     */
    @Override
    public String musicPath() {
        return "data/audio/music/stage2Music.wav";
    }

    /**
     *
     * @return the density of the ball
     */
    @Override
    public float ballDensity() {
        return 0.45f;
    }

    /**
     *
     * @return the restitution of the ball
     */
    @Override
    public float ballRestitution() {
        return 0.55f;
    }
}