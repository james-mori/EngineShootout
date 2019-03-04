package engineshootout.Objects;

import city.cs.engine.BodyImage;
import city.cs.engine.PolygonShape;
import city.cs.engine.Shape;
import city.cs.engine.SolidFixture;
import city.cs.engine.Walker;
import engineshootout.Main.EngineShootout;
import engineshootout.Main.GameStage;

/**This is the car that appears on the right - for player 2
 *
 * @author James Mori
 */
public class RightCar extends Walker {
    private EngineShootout game;
    private static final Shape rightBodyShape = new PolygonShape(
            -0.56f,1.99f, -1.97f,1.56f, -2.77f,0.24f, -2.68f,-0.08f, -0.02f,-0.67f, 2.4f,-0.07f, 2.16f,1.28f);
    private static final Shape leftWheelShape = new PolygonShape(
            -1.84f,-0.06f, -2.51f,-0.38f, -2.75f,-1.06f, -2.44f,-1.73f, -1.74f,-1.99f, -1.05f,-1.67f, -0.82f,-0.98f, -1.13f,-0.33f);
    private static final Shape rightWheelShape = new PolygonShape(
            1.74f,-0.07f, 1.09f,-0.4f, 0.83f,-1.08f, 1.14f,-1.75f, 1.86f,-2.0f, 2.53f,-1.66f, 2.77f,-0.96f, 2.49f,-0.37f);
    
    public RightCar(GameStage world, EngineShootout game){
        super(world);
        this.game = game;
        SolidFixture bodyFixture = new SolidFixture(this, rightBodyShape, 10);
        addImage(new BodyImage("data/objects/cars/" + game.getColourP2() + "CarRight.png", 4f));
        SolidFixture leftWheelFixture = new SolidFixture(this, leftWheelShape, 2);
        SolidFixture rightWheelFixture = new SolidFixture(this, rightWheelShape, 2);
        bodyFixture.setDensity(0.8f);
        leftWheelFixture.setFriction(0.7f);
        rightWheelFixture.setFriction(0.7f);
        leftWheelFixture.setRestitution(0.3f);
    }
}

