package engineshootout.Objects;

import city.cs.engine.CircleShape;
import city.cs.engine.DynamicBody;
import city.cs.engine.Shape;
import city.cs.engine.SolidFixture;
import engineshootout.Main.GameStage;
import org.jbox2d.common.Vec2;

/**Ball class - general object that gets changed in size, density, restitution and image depending on the level
 *
 * @author James Mori
 */
public class Ball extends DynamicBody {
    private static Shape ballShape;
    private GameStage world;
    private int scoreP1;
    private int scoreP2;
    
    public Ball(GameStage world) {
        super(world);
        this.world = world;
        ballShape = new CircleShape(1.2f*world.ballSizeConstant());
        setPosition(new Vec2(0, -3f));
        setLinearVelocity(new Vec2(0,7));
        SolidFixture ballFixture = new SolidFixture(this, ballShape);
        ballFixture.setRestitution(world.ballRestitution());
        ballFixture.setDensity(world.ballDensity());
        scoreP1 = 0;
        scoreP2 = 0;
    }

    /**
     *
     * @param scoreP1 Player 1's score in the level
     */
    public void setScoreP1(int scoreP1) {
        this.scoreP1 = scoreP1;
    }

    /**
     *
     * @param scoreP2 Player 2's score in the level
     */
    public void setScoreP2(int scoreP2) {
        this.scoreP2 = scoreP2;
    }
    

}