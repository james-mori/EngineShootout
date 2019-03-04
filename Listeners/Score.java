package engineshootout.Listeners;

import engineshootout.Objects.RightCar;
import engineshootout.Objects.Powerup;
import engineshootout.Objects.LeftCar;
import engineshootout.Objects.Goals;
import engineshootout.Objects.Ball;
import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import city.cs.engine.SoundClip;
import engineshootout.Main.GameStage;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import org.jbox2d.common.Vec2;

/**This is the collision listener for the goal scoring event
 *
 * @author James Mori
 */
public class Score implements CollisionListener {
    private Ball ball;
    private LeftCar leftCar;
    private RightCar rightCar;
    private Powerup powerup;
    private GameStage world;
    private static SoundClip crowd;

    /**Y-position for the ground height
     *
     */
    public float groundLevel = -12.3f;

    static{
    try{
        crowd = new SoundClip("data/audio/sfx/goalCrowd.wav");
        }
        catch(UnsupportedAudioFileException|IOException|LineUnavailableException e){
            System.out.println(e);
        }
    }

    /**
     *
     * @param ball the ball in the world
     * @param leftCar player 1's car
     * @param rightCar player 2's car
     * @param powerup the powerup block
     * @param world the level world
     */
    public Score(Ball ball, LeftCar leftCar, RightCar rightCar, Powerup powerup, GameStage world) {
        this.ball = ball;
        this.leftCar = leftCar;
        this.rightCar = rightCar;
        this.powerup = powerup;
        this.world = world;
    }

    /**
     *
     * @return player 1's car
     */
    public LeftCar getLeftCar() {
        return leftCar;
    }

    /**
     *
     * @return player 2's car
     */
    public RightCar getRightCar() {
        return rightCar;
    }

public void GoalScoredEvent() {
    crowd.play();
    crowd.setVolume(0.6);
    leftCar.setLinearVelocity(new Vec2(0,0));
    rightCar.setLinearVelocity(new Vec2(0,0));
    leftCar.setPosition(new Vec2(-20f, groundLevel+3));
    rightCar.setPosition(new Vec2(20f, groundLevel+3));
}

    /**If the ball collides with a player's goal, the score increments for the opposing player
     *
     * @param e collision event between the ball and the goal
     */
    @Override
    public void collide(CollisionEvent e) {

        if (e.getReportingBody() instanceof Goals && e.getReportingBody().getPosition().x < 0 && e.getOtherBody() == ball) {
            GoalScoredEvent();
            world.incrementScoreP2(); // goal on the left is specified
        }
        if (e.getReportingBody() instanceof Goals && e.getReportingBody().getPosition().x > 0 && e.getOtherBody() == ball) {
            GoalScoredEvent();
            world.incrementScoreP1(); // goal on the right is specified
        }
    }
}
