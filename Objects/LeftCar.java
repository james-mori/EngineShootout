package engineshootout.Objects;
import city.cs.engine.BodyImage;
import city.cs.engine.PolygonShape;
import city.cs.engine.Shape;
import city.cs.engine.SolidFixture;
import city.cs.engine.Walker;
import engineshootout.Main.EngineShootout;
import engineshootout.Main.GameStage;

/**This is the car that appears on the left - for player 1
 *
 * @author James Mori
 */
public class LeftCar extends Walker {
    
    private EngineShootout game;
    private static final Shape leftBodyShape = new PolygonShape(
            0.5f,2.0f, 2.13f,1.01f, 2.69f,0.26f, 2.64f,-0.08f, -0.01f,-0.68f, -2.5f,0.03f, -2.21f,1.29f);
    private static final Shape leftWheelShape = new PolygonShape(
            -1.87f,-0.04f, -2.49f,-0.38f, -2.74f,-1.08f, -2.44f,-1.74f, -1.74f,-1.99f, -1.07f,-1.66f, -0.83f,-0.97f, -1.15f,-0.28f);
    private static final Shape rightWheelShape = new PolygonShape(
            1.12f,-0.31f, 0.79f,-1.06f, 1.12f,-1.74f, 1.8f,-2.0f, 2.42f,-1.75f, 2.76f,-1.08f, 2.5f,-0.39f, 1.85f,-0.06f);
    
    public LeftCar(GameStage world, EngineShootout game){
        super(world);
        this.game = game;
        SolidFixture bodyFixture = new SolidFixture(this, leftBodyShape, 10);
        addImage(new BodyImage("data/objects/cars/" + game.getColourP1() + "CarLeft.png", 4f));
        SolidFixture leftWheelFixture = new SolidFixture(this, leftWheelShape, 2);
        SolidFixture rightWheelFixture = new SolidFixture(this, rightWheelShape, 2);
        bodyFixture.setDensity(0.8f);
        leftWheelFixture.setFriction(0.6f);
        leftWheelFixture.setRestitution(0.3f);
        rightWheelFixture.setFriction(0.6f);
    }
}
