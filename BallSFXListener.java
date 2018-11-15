package engineshootout.Listeners;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import city.cs.engine.SoundClip;
import engineshootout.Objects.Ball;
import java.io.IOException;
import java.util.Random;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**This is the collision listener for the ball - every time the ball collides with something, a random sound effect is played
 *
 * @author James Mori
 */
public class BallSFXListener implements CollisionListener {
    private Ball ball;
    private SoundClip ballSFX;
    private int randomSoundInt;
    
    private static SoundClip ballHit1;
    private static SoundClip ballHit2;
    private static SoundClip ballHit3;
    private static SoundClip ballHit4;
    
    static{
        try{
        ballHit1 = new SoundClip("data/audio/sfx/1.wav");
        ballHit2 = new SoundClip("data/audio/sfx/2.wav");
        ballHit3 = new SoundClip("data/audio/sfx/3.wav");
        ballHit4 = new SoundClip("data/audio/sfx/4.wav");
        }
        catch(UnsupportedAudioFileException|IOException|LineUnavailableException e){
            System.out.println(e);
        }
    }
    
    /**
     *
     * @param ball The ball in the game
     */
    public BallSFXListener(Ball ball) {
        this.ball = ball;
    }
    
    /**A random sound is picked out of the 4 possible ones for each collision.
     *
     * @param g Collision event
     */
    @Override
    public void collide(CollisionEvent g) {
        Random rand = new Random();
        randomSoundInt = rand.nextInt(4)+1;

        if (g.getReportingBody() == ball) {
                switch(randomSoundInt) {
            case 1:
                ballSFX = ballHit1;
                break;
            case 2:
                ballSFX = ballHit2;
                break;
            case 3:
                ballSFX = ballHit3;
                break;
            case 4:
                ballSFX = ballHit4;
                break;
                }
            ballSFX.play();
            ballSFX.setVolume(0.6);
        }
    }
}
