package engineshootout.Listeners;

import engineshootout.Objects.RightCar;
import engineshootout.Objects.LeftCar;
import engineshootout.Objects.Key;
import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import city.cs.engine.SoundClip;
import engineshootout.Main.EngineShootout;
import engineshootout.Main.GameStage;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author james
 */
public class NextStageListener implements CollisionListener {
    private static SoundClip progress;
    private EngineShootout game;
    private Key key;
    private LeftCar leftCar;
    private RightCar rightCar;
    
    static{
    try{
        progress = new SoundClip("data/audio/sfx/progress.wav");
        }
        catch(UnsupportedAudioFileException|IOException|LineUnavailableException e){
            System.out.println(e);
        }
    }
    
    /**
     *
     * @param world The current world
     * @param game The game
     * @param key The key that must be picked up in order to progress
     */
    public NextStageListener(GameStage world, EngineShootout game, Key key) {
        this.leftCar = world.getPlayer1();
        this.rightCar = world.getPlayer2();
        this.key = key;
        this.game = game;
    }
        
    /** If a car collides with the key we determine who (if anyone) won that round and the next level loads
     *
     * @param c The collision event
     */
    @Override
        public void collide(CollisionEvent c) {
            if(c.getReportingBody() == key) {
                if (c.getOtherBody() == leftCar || c.getOtherBody() == rightCar) {
                    if (game.isCurrentStageCompleted()) {
                        if (game.world.getScoreP1() > game.world.getScoreP2()) {
                            game.setP1Wins(game.getP1Wins()+1);
                        }
                        if (game.world.getScoreP1() < game.world.getScoreP2()) {
                            game.setP2Wins(game.getP2Wins()+1);
                        }
                        System.out.println("Going to the next stage...");
                        progress.play();
                        game.goNextStage();
                    }
                }
            }
        }
}
