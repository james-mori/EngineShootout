package engineshootout.Objects;

import city.cs.engine.BoxShape;
import city.cs.engine.Shape;
import city.cs.engine.SolidFixture;
import city.cs.engine.StaticBody;
import engineshootout.Main.GameStage;
import java.awt.Color;

/**Goal class for the game. Has no friction to prevent the players from sliding up it. It is invisible and just acting as a sensor. The actual image comes from the background.
 * @author James Mori
 */
public class Goals extends StaticBody {
    
    private static final Shape goalShape = new BoxShape(0.01f, 5.1f);
    
    public Goals(GameStage world) {
        super(world);
        setFillColor(new Color(0,0,0,0));
        setLineColor(new Color(255,255,255,0));
        SolidFixture goalFixture = new SolidFixture(this, goalShape);
        goalFixture.setFriction(0);
    }    
}
