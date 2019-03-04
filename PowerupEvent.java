package engineshootout.Listeners;

import engineshootout.Objects.RightCar;
import engineshootout.Objects.Powerup;
import engineshootout.Objects.LeftCar;
import engineshootout.Objects.Key;
import engineshootout.Objects.Ball;
import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import city.cs.engine.SoundClip;
import engineshootout.Main.EngineShootout;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import org.jbox2d.common.Vec2;

/**Collision event class for the powerup block and the ball. If a player has scored more than the required number for that level, a key can drop to take them to the next level
 *
 * @author James Mori
 */
public class PowerupEvent implements CollisionListener {
    private Ball ball;
    private Powerup powerup;
    private Key key;
    private EngineShootout game;
    private LeftCar leftCar;
    private RightCar rightCar;
    private static SoundClip powerupSound;
    
    static{
    try{
        powerupSound = new SoundClip("data/audio/sfx/powerupSound.wav");
        }
        catch(UnsupportedAudioFileException|IOException|LineUnavailableException e){
            System.out.println(e);
        }
    }
    
    /**
     *
     * @param ball the ball in this level
     * @param powerup the powerup block
     * @param game the game
     * @param leftCar player 1's car
     * @param rightCar player 2's car
     */
    public PowerupEvent(Ball ball, Powerup powerup, EngineShootout game, LeftCar leftCar, RightCar rightCar) {
        this.ball = ball;
        this.powerup = powerup;
        this.game = game;
        this.leftCar = leftCar;
        this.rightCar = rightCar;
    }
    
    /**If the ball hits the block and either player has reached the goal requirement a kay will drop
     *
     * @param g collision event between the ball and the block
     */
    @Override
    public void collide(CollisionEvent g) {
        if (g.getReportingBody() instanceof Powerup && g.getOtherBody() == ball && (game.world.getScoreP1() >= game.world.goalTarget() || game.world.getScoreP2() >= game.world.goalTarget())) {
            powerupSound.play();
            key = new Key(game.world);
            key.setPosition(new Vec2(0,8));
            key.addCollisionListener(new NextStageListener(game.world, game, key));
            g.getReportingBody().removeCollisionListener(this);
        }
    }
}