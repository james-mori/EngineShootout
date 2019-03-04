package engineshootout.Objects;

import city.cs.engine.PolygonShape;
import city.cs.engine.Shape;
import city.cs.engine.SolidFixture;
import city.cs.engine.StaticBody;
import engineshootout.Main.GameStage;

/**This is the powerup block that appears in the sky
 *
 * @author James Mori
 */
public class Powerup extends StaticBody {
    private static final Shape powerupShape = new PolygonShape(-1.5f,1.26f, -1.26f,1.5f, 1.25f,1.5f, 1.5f,1.25f, 1.5f,-1.25f, 1.26f,-1.48f, -1.26f,-1.48f, -1.5f,-1.25f);
    
    public Powerup(GameStage world) {
        super(world);
        SolidFixture powerupFixture = new SolidFixture(this, powerupShape);
        powerupFixture.setFriction(0);
    }
}
