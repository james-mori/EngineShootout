package engineshootout.Main;

import engineshootout.Listeners.PowerupEvent;
import engineshootout.Listeners.Score;
import engineshootout.Objects.RightCar;
import engineshootout.Objects.Powerup;
import engineshootout.Objects.LeftCar;
import engineshootout.Objects.Goals;
import engineshootout.Objects.Ball;
import city.cs.engine.Body;
import city.cs.engine.BodyImage;
import city.cs.engine.BoxShape;
import city.cs.engine.PolygonShape;
import city.cs.engine.Shape;
import city.cs.engine.SolidFixture;
import city.cs.engine.SoundClip;
import city.cs.engine.StaticBody;
import city.cs.engine.World;
import engineshootout.Listeners.BallSFXListener;
import java.awt.Color;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import org.jbox2d.common.Vec2;

import engineshootout.GUI.MainMenu;

/**The world class for the game
 *
 * @author James Mori
 */
public abstract class GameStage extends World{
    private LeftCar leftCar;
    private RightCar rightCar;
    private Ball ball;
    private Goals leftGoal;
    private Goals rightGoal;
    private Powerup powerup;
    private PowerupEvent powerupEvent;
    private int scoreP1;
    private int scoreP2;
    private float groundLevel = -12.3f;
    private int ballScoreP1;
    private int ballScoreP2;
    /**The music for each level
     */
    public SoundClip stageMusic;
    private static SoundClip backgroundCrowd, engineNoise;
    private MainMenu menu;
    
        static{
    try{
        backgroundCrowd = new SoundClip("data/audio/music/backgroundCrowdShort.wav");
        engineNoise = new SoundClip("data/audio/sfx/engineNoise.wav");
        }
        catch(UnsupportedAudioFileException|IOException|LineUnavailableException e){
            System.out.println(e);
        }
    }
    
    /**
     *
     * @param game the game
     * @param menu the main menu
     */
    public void populate(EngineShootout game, MainMenu menu) {
        this.menu = menu;
        try {
            stageMusic = new SoundClip(musicPath());
            stageMusic.play();
            stageMusic.setVolume(0.4);
            if (game.getStageNo() != 0) {
            backgroundCrowd.play();
            backgroundCrowd.loop();
            engineNoise.play();
            engineNoise.setVolume(0.3);
            engineNoise.loop();
            }
            if ("data/audio/music/stage2Music.wav".equals(musicPath()) || "data/audio/music/stage3Music.wav".equals(musicPath())) {
            stageMusic.setVolume(0.25);
            }
            stageMusic.loop();
            } catch (UnsupportedAudioFileException|IOException|LineUnavailableException e) {
                System.out.println(e);
        }
        
    if (game.getStageNo() != 0) {
        // Football
        ball = new Ball(this);
        ball.addImage(ballImage());
        ball.addCollisionListener(new BallSFXListener(ball));

        scoreP1 = 0;
        scoreP2 = 0;
        ballScoreP1 = 0;
        ballScoreP2 = 0;
        
        // Roof
        Shape roofShape = new BoxShape(30, 0.001f);
        Body roof = new StaticBody(this, roofShape);
        roof.setPosition(new Vec2(0, 11.3f));
        roof.setFillColor(new Color(0,0,0,0));
        roof.setLineColor(new Color(0,0,0,0));
        SolidFixture roofFixture = new SolidFixture(roof, roofShape);
        roofFixture.setRestitution(0.3f);
        
        // Ground
        Shape groundShape = new BoxShape(30, 1);
        Body ground = new StaticBody(this, groundShape);
        ground.setFillColor(new Color(0,0,0,0));
        ground.setLineColor(new Color(0,0,0,0));
        ground.setPosition(new Vec2(0, groundLevel));
        SolidFixture groundFixture = new SolidFixture(ground, groundShape);
        groundFixture.setFriction(0.9f);
        
        // Arena Top Corners
        Shape leftCornerShape = new PolygonShape(1.81f,8.14f, -9.19f,8.19f, -9.14f,-9.39f, -3.45f,-9.39f);
        Shape rightCornerShape = new PolygonShape(-7.91f,8.19f, 1.81f,8.14f, 1.81f,-9.39f, -2.65f,-9.39f);
        StaticBody leftCorner = new StaticBody(this, leftCornerShape);
        StaticBody rightCorner = new StaticBody(this, rightCornerShape);
        leftCorner.setFillColor(new Color(0,0,0,0));
        rightCorner.setFillColor(new Color(0,0,0,0));
        leftCorner.setLineColor(new Color(0,0,0,0));
        rightCorner.setLineColor(new Color(0,0,0,0));
        leftCorner.setPosition(new Vec2(-19,9));
        rightCorner.setPosition(new Vec2(25.1f,9));
        SolidFixture leftCornerFixture = new SolidFixture(leftCorner, leftCornerShape);
        leftCornerFixture.setRestitution(0.3f);
        SolidFixture rightCornerFixture = new SolidFixture(rightCorner, rightCornerShape);
        rightCornerFixture.setRestitution(0.3f);
        
        // Player 1 Car (left side)
        leftCar = new LeftCar(this, game);
        leftCar.setPosition(new Vec2(-20f, groundLevel+3));
        leftCar.setGravityScale(1.5f);
        
        // Player 2 Car (right side)
        rightCar = new RightCar(this, game);
        rightCar.setPosition(new Vec2(20f, groundLevel+3));
        rightCar.setGravityScale(1.5f);
        
        // Left Goal
        leftGoal = new Goals(this);
        leftGoal.setPosition(new Vec2(-23f, -6.1f));
        leftGoal.addCollisionListener(new Score(ball, leftCar, rightCar, powerup, this));
        
        // Right Goal
        rightGoal = new Goals(this);
        rightGoal.setPosition(new Vec2(23f, -6.1f));
        rightGoal.addCollisionListener(new Score(ball, leftCar, rightCar, powerup, this));

        // Powerup Block
        powerup = new Powerup(this);
        powerup.setPosition(new Vec2(0, 8.5f));
        powerup.addCollisionListener(new PowerupEvent(ball, powerup, game, leftCar, rightCar));
        powerup.addImage(new BodyImage("data/objects/other/powerupGrey.png", 3.2f));
        }
    }

    /**
     *
     * @return the main menu class
     */
    public MainMenu getMenu() {
        return menu;
    }
    
    /**Increments the score for player 1 (by 1 goal)
     *
     */
    public void incrementScoreP1() {
        scoreP1++;
        ball.setPosition(new Vec2(0, 0));
        ball.setLinearVelocity(new Vec2(0,7));
        ball.setAngularVelocity(0);
        System.out.println("Goal! Player 1 scored!");
        System.out.println("PLAYER 1 | " + scoreP1 + " : " + scoreP2 + " | PLAYER 2"); 
        
    }

    /**Increments the score for player 1 (by 1 goal)
     *
     */
    public void incrementScoreP2() {
        scoreP2++;
        ball.setPosition(new Vec2(0, 0));
        ball.setLinearVelocity(new Vec2(0,7));
        ball.setAngularVelocity(0);
        System.out.println("Goal! Player 2 scored!");
        System.out.println("PLAYER 1 | " + scoreP1 + " : " + scoreP2 + " | PLAYER 2");
    }
        
    /**Gets the score for player 1
     *
     * @return the score for player 1
     */
    public int getScoreP1() {
        return scoreP1;
    }

    /**Gets the score for player 2
     *
     * @return the score for player 2
     */
    public int getScoreP2() {
        return scoreP2;
    }

    /**
     *
     * @return player 1's car
     */
    public LeftCar getPlayer1() {
        return leftCar;
    }

    /**
     *
     * @return player 2's car
     */
    public RightCar getPlayer2() {
        return rightCar;
    }

    /**
     *
     * @return the ball
     */
    public Ball getBall() {
        return ball;
    }

    /**Sets the number of rounds won by player 1
     *
     * @param ballScoreP1 the number of rounds won by player 1
     */
    public void setP1BallScore(int ballScoreP1) {
        this.ballScoreP1 = ballScoreP1;
    }

    /**Sets the number of rounds won by player 2
     *
     * @param ballScoreP2 the number of rounds won by player 2
     */
    public void setP2BallScore(int ballScoreP2) {
        this.ballScoreP2 = ballScoreP2;
    }
  
    /**
     *
     * @return the level-specific requirement to set the stage complete boolean to true
     */
    public abstract boolean isCompleted();

    /**
     *
     * @return the level-specific image of the ball
     */
    public abstract BodyImage ballImage();

    /**
     *
     * @return the starting position of the left car
     */
    public abstract Vec2 leftCarPos();

    /**
     *
     * @return the starting position of the right car
     */
    public abstract Vec2 rightCarPos();

    /**
     *
     * @return the starting position of the ball
     */
    public abstract Vec2 ballPos();

    /**
     *
     * @return the level-specific number of goals required to progress to the next level
     */
    public abstract int goalTarget();

    /**
     *
     * @return the level-specific image path for the left car
     */
    public abstract String getLeftCarImage();

    /**
     *
     * @return the level-specific image path for the right car
     */
    public abstract String getRightCarImage();

    /**
     *
     * @return the level-specific size factor of the ball
     */
    public abstract float ballSizeConstant();

    /**
     *
     * @return the level-specific audio path for the background music
     */
    public abstract String musicPath();

    /**
     *
     * @return the level-specific density of the ball
     */
    public abstract float ballDensity();

    /**
     *
     * @return the level-specific restitution of the ball
     */
    public abstract float ballRestitution();
}
