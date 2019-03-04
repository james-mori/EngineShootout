package engineshootout.Objects;

import city.cs.engine.BodyImage;
import city.cs.engine.DynamicBody;
import city.cs.engine.PolygonShape;
import city.cs.engine.Shape;
import city.cs.engine.SolidFixture;
import engineshootout.Main.GameStage;

/**This is the key that gets dropped in order to progress to the next level
 *
 * @author James Mori
 */
public class Key extends DynamicBody {
    
    private static final Shape keyShape = new PolygonShape(
            -0.834f,0.923f, -0.041f,0.997f, 1.015f,-0.585f, 1.034f,-0.994f, 0.634f,-0.948f, -0.997f,-0.158f, -1.031f,0.622f);
    //public Key key;
    
    public Key(GameStage world) {
        super(world);
        addImage(new BodyImage("data/objects/other/key.png", 2.4f));
        SolidFixture keyFixture = new SolidFixture(this, keyShape);
        keyFixture.setFriction(0);
        keyFixture.setDensity(1);
    }
}
