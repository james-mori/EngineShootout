package engineshootout.Main;

import engineshootout.GUI.ControlGUI;
import engineshootout.GUI.MainMenu;
import engineshootout.Stages.Stage4;
import engineshootout.Stages.Stage2;
import engineshootout.Stages.Stage5;
import engineshootout.Stages.Stage1;
import engineshootout.Stages.Stage3;
import engineshootout.Stages.Stage0;
import engineshootout.Objects.RightCar;
import engineshootout.Objects.LeftCar;
import city.cs.engine.DebugViewer;
import city.cs.engine.SoundClip;
import city.cs.engine.UserView;
import java.awt.BorderLayout;
import java.awt.event.KeyListener;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;

import javax.swing.JFrame;

/**The main game class
 *
 * @author James Mori
 */
public class EngineShootout {

    /**The level world class
     */
    public GameStage world;
    private UserView view;
    private int stage;
    private SoundClip gameOverMusic;
    private int p1Wins=0;
    private int p2Wins=0;
    JFrame frame = new JFrame("Engine Shootout");
    ControlGUI gui = new ControlGUI(this);
    MainMenu mainMenu = new MainMenu(this, view, gui);
    private String colourP1;
    private String colourP2;
    
    public EngineShootout() {
        stage = 0;
        ImageIcon icon = new ImageIcon("data/objects/cars/redCarLeft.png");
        frame.setIconImage(icon.getImage());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        world = new Stage0();
        world.populate(this, world.getMenu());
        world.setGravity(8);
        view = new View(world, 1200, 600, this);
        view.setFocusable(true);
        frame.add(gui, BorderLayout.NORTH);
        frame.setLocationByPlatform(false);
        frame.setLocation(0, 0);
        frame.add(mainMenu);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
        mainMenu.addKeyListener(new KeyboardHandler(world.getPlayer1(), world.getPlayer2(), world, this));
        mainMenu.setFocusTraversalKeysEnabled(true);
        view.addKeyListener(new KeyboardHandler(world.getPlayer1(), world.getPlayer2(), world, this));
        view.setFocusTraversalKeysEnabled(true);
        view.requestFocus();
        world.start();
    }
    
    /**Returns the colour of player 1's car
     *
     * @return the colour of player 1's car
     */
    public String getColourP1() {
        return colourP1;
    }
    
    /**Returns the colour of player 2's car
     *
     * @return the colour of player 1's car
     */
    public String getColourP2() {
        return colourP2;
    }
    
    /**Returns the level number (max 5)
     *
     * @return the level number
     */
    public int getStageNo() {
        return stage;
    }

    /**Returns whether the current level is completed or not
     *
     * @return boolean
     */
    public boolean isCurrentStageCompleted() {
        return world.isCompleted();
    }
    
    /**Method to advance to the next stage or print out an end-game message if the game is complete
     *
     */
    public void goNextStage() {
        world.stop();
        switch (stage) {
            case 0:
                colourP1 = this.mainMenu.leftCarSelection;
                colourP2 = this.mainMenu.rightCarSelection;
                System.out.println("to stage 1");
                frame.remove(mainMenu);
                frame.add(view);
                frame.invalidate();
                frame.validate();
                stage++;
                world.stageMusic.stop();
                world = new Stage1();
                world.populate(this, this.mainMenu);
                view.setWorld(world);
                //JFrame debugView0 = new DebugViewer(world, 1200, 600);
                
                KeyListener[] kls = view.getKeyListeners();
                for (int i=0; i<kls.length; i++)
                    view.removeKeyListener(kls[i]);
                view.addKeyListener(new KeyboardHandler(world.getPlayer1(), world.getPlayer2(), world, this));
                view.setFocusTraversalKeysEnabled(true);
                view.requestFocus();
                world.start();
                break;
            case 5:
                stage++;
                world.stageMusic.stop();
                try {
                    gameOverMusic = new SoundClip("data/audio/music/menuMusic.wav");
                    gameOverMusic.loop();
                } catch (UnsupportedAudioFileException|IOException|LineUnavailableException e) {
                    System.out.println(e);
                }
                System.out.println("The game is complete");
                if (p1Wins > p2Wins) {
                    System.out.println("Player 1 wins with " + p1Wins + " rounds won!");
                    System.out.println("Press Esc to quit");
                }
                else if (p1Wins == p2Wins) {
                    System.out.println("It's a draw! You are both equally terrible!");
                    System.out.println("Press Esc to quit");
                }
                else if (p1Wins < p2Wins) {
                    System.out.println("Player 2 wins with " + p2Wins + " rounds won!");
                    System.out.println("Press Esc to quit");
                }
                break;
            case 1:
                System.out.println("to stage 2");
                world.stageMusic.stop();
                stage++;
                world = new Stage2();
                world.populate(this, this.mainMenu);
                view.setWorld(world);
                //JFrame debugView1 = new DebugViewer(world, 1200, 600);
                
                KeyListener[] kls2 = view.getKeyListeners();
                for (int i=0; i<kls2.length; i++)
                    view.removeKeyListener(kls2[i]);
                view.addKeyListener(new KeyboardHandler(world.getPlayer1(), world.getPlayer2(), world, this));
                world.start();
                break;
            case 2:
                System.out.println("to stage 3");
                world.stageMusic.stop();
                stage++;
                world = new Stage3();
                world.populate(this, world.getMenu());
                //JFrame debugView2 = new DebugViewer(world, 1200, 600);
                KeyListener[] kls3 = view.getKeyListeners();
                for (int i=0; i<kls3.length; i++)
                    view.removeKeyListener(kls3[i]);
                view.setWorld(world);
                view.addKeyListener(new KeyboardHandler(world.getPlayer1(), world.getPlayer2(), world, this));
                world.start();
                break;
            case 3:
                System.out.println("to stage 4");
                world.stageMusic.stop();
                stage++;
                world = new Stage4(); 
                world.populate(this, this.mainMenu);
                view.setWorld(world);
                //JFrame debugView3 = new DebugViewer(world, 1200, 600);
                KeyListener[] kls4 = view.getKeyListeners();
                for (int i=0; i<kls4.length; i++)
                    view.removeKeyListener(kls4[i]);
                view.addKeyListener(new KeyboardHandler(world.getPlayer1(), world.getPlayer2(), world, this));
                world.start();
                break;
            case 4:
                System.out.println("to stage 5");
                world.stageMusic.stop();
                stage++;
                world = new Stage5();
                world.populate(this, this.mainMenu);
                //JFrame debugView4 = new DebugViewer(world, 1200, 600);
                KeyListener[] kls5 = view.getKeyListeners();
                for (int i=0; i<kls5.length; i++)
                    view.removeKeyListener(kls5[i]);
                view.setWorld(world);
                view.addKeyListener(new KeyboardHandler(world.getPlayer1(), world.getPlayer2(), world, this));
                world.start();
                break;
            default:
                break;
        }
    }

    /**Gets the players' view
     *
     * @return the players' view
     */
    public UserView getView() {
        return view;
    }
    
    /**Gets the number of rounds player 1 has won
     *
     * @return the number of rounds player 1 has won
     */
    public int getP1Wins() {
        return p1Wins;
    }

    /**Sets the number of rounds player 1 has won
     *
     * @param p1Wins the number of rounds player 1 has won
     */
    public void setP1Wins(int p1Wins) {
        this.p1Wins = p1Wins;
    }

    /**Gets the number of rounds player 2 has won
     *
     * @return the number of rounds player 1 has won
     */
    public int getP2Wins() {
        return p2Wins;
    }

    /**Sets the number of rounds player 1 has won
     *
     * @param p2Wins the number of rounds player 1 has won
     */
    public void setP2Wins(int p2Wins) {
        this.p2Wins = p2Wins;
    }
    
    /**Launches the game
     *
     * @param args args
     */
    public static void main(String[] args) {
        new EngineShootout();
    }
}