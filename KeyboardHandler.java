package engineshootout.Main;

import city.cs.engine.Walker;
import org.jbox2d.common.Vec2;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**This is the full set of controls for player 1 (WASD keys)
 * and for player 2 (arrow keys). 
 * Driving, reversing and jumping forces for both cars are defined here.
 * 
 * @author James Mori
 */

public class KeyboardHandler extends KeyAdapter {
    private static final float DRIVING_FORCE = 15;
    private static final float REVERSING_FORCE = 13;
    private static final float JUMPING_FORCE = 11;
    private static final float BOOST_FORCE = 10;
    private final Walker leftCar;
    private final Walker rightCar;
    private final GameStage world;
    private final EngineShootout game;

    /**Boolean to check whether the pause button has been pressed or not
     *
     */
    public boolean pPressed;
    
    /**
     *
     * @param car1 player 1's car
     * @param car2 player 2's car
     * @param world the level world
     * @param game the game
     */
    public KeyboardHandler(Walker car1, Walker car2, GameStage world, EngineShootout game) {
        this.leftCar = car1;
        this.rightCar = car2;
        this.world = world;
        this.game = game;
        pPressed = false;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        
        if (key == KeyEvent.VK_ESCAPE) {
            System.out.println("closing game...");
            System.exit(0);
        }
        if (key == KeyEvent.VK_LEFT) { //                LEFT = PLAYER 2 drive left
            rightCar.startWalking(-DRIVING_FORCE);
            //System.out.println("arrow left");
        }
        if (key == KeyEvent.VK_RIGHT) { //             RIGHT = PLAYER 2 drive right
            rightCar.startWalking(REVERSING_FORCE);
            //System.out.println("arrow right");
        }
        if (key == KeyEvent.VK_UP) { //                          UP = PLAYER 2 jump
            rightCar.jump(JUMPING_FORCE);
            //System.out.println("arrow up jump");
        }
        if (key == KeyEvent.VK_W) { //                            W = PLAYER 1 jump
            leftCar.jump(JUMPING_FORCE);
            //System.out.println("w key (up) jump");
        }
        if (key == KeyEvent.VK_A) { //                      A = PLAYER 1 drive left
            leftCar.startWalking(-REVERSING_FORCE);
            //System.out.println("a key(left)");
            }
        if (key == KeyEvent.VK_D) { //                     D = PLAYER 1 drive right
            leftCar.startWalking(DRIVING_FORCE);
            //System.out.println("d key (right)");
        }
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) { //                LEFT = PLAYER 2 drive left
            //System.out.println("arrow left released");
            rightCar.stopWalking();
        }
        if (key == KeyEvent.VK_RIGHT) { //             RIGHT = PLAYER 2 drive right
            //System.out.println("arrow right released");
            rightCar.stopWalking();
        }
        if (key == KeyEvent.VK_UP) { //                          UP = PLAYER 2 jump
            //System.out.println("arrow up released");
        }
        if (key == KeyEvent.VK_W) { //                            W = PLAYER 1 jump
            //System.out.println("w key (up) released");
        }
        if (key == KeyEvent.VK_A) { //                      A = PLAYER 1 drive left
            //System.out.println("a key(left) released");
            leftCar.stopWalking();
            }
        if (key == KeyEvent.VK_D) { //                     D = PLAYER 1 drive right
            //System.out.println("d key (right) released");
            leftCar.stopWalking();
        }
        if (key == KeyEvent.VK_P && pPressed == true) {
            //System.out.println("p pressed, true>false");
            world.start();
            world.stageMusic.resume();
            pPressed = false;
        }
        else if (key == KeyEvent.VK_P && pPressed == false) {
            //System.out.println("p pressed, false>true");
            world.stop();
            world.stageMusic.pause();
            pPressed = true;
        }
    }
}
