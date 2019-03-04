package engineshootout.Main;

import engineshootout.Objects.Ball;
import city.cs.engine.UserView;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;

/**Handles the viewing of the game. Allows the changing of background when switching levels as well
 * as any overlays. Also produces a message at the end of the game telling the players who won and
 * and what the score was.
 * @author James Mori
 */

public class View extends UserView {
    private final Image backgroundImage1, backgroundImage2, backgroundImage3, backgroundImage4, backgroundImage5;
    private final Image gui;
    private final Image ballIcon;
    private final Image snowOverlay, darknessOverlay, darknessOverlayPS, snowWhiteOverlay, startGameOverlay, endGameOverlay;
    private GameStage world;
    private EngineShootout game;
    private String drawLine, p1WinLine, p2WinLine, escLine;
    
    /**
     *
     * @param world the level world
     * @param width the width of the game screen
     * @param height the height of the game screen
     * @param game the game
     */
    public View(GameStage world, int width, int height, EngineShootout game) {
        super(world, width, height);
        this.game = game;
        this.world = world;
        this.backgroundImage1 = new ImageIcon("data/bg/backgroundLevel1.png").getImage();
        this.backgroundImage2 = new ImageIcon("data/bg/backgroundLevel2.png").getImage();
        this.backgroundImage3 = new ImageIcon("data/bg/backgroundLevel3.png").getImage();
        this.backgroundImage4 = new ImageIcon("data/bg/backgroundLevel4.png").getImage();
        this.backgroundImage5 = new ImageIcon("data/bg/backgroundLevel5.png").getImage();
        gui = new ImageIcon("data/overlays/gui.png").getImage();
        ballIcon = new ImageIcon("data/objects/balls/iconFootballGold.png").getImage();
        snowOverlay = new ImageIcon("data/overlays/snowOverlay.gif").getImage();
        darknessOverlayPS = new ImageIcon("data/overlays/darknessOverlayMoonlight.png").getImage();
        darknessOverlay = new ImageIcon("data/overlays/darknessOverlay.png").getImage();
        snowWhiteOverlay = new ImageIcon("data/overlays/snowWhiteOverlay.png").getImage();
        startGameOverlay = new ImageIcon("data/overlays/startGameOverlay.png").getImage();
        endGameOverlay = new ImageIcon("data/overlays/endGameOverlay.png").getImage();
        drawLine = "It's a draw! You are both equally terrible!";
        escLine = "Press Esc to quit";
    }
    
    /**
     *
     * @param g the background graphics handler
     */
    @Override
    protected void paintBackground(Graphics2D g) {
        switch (game.getStageNo()) {
            case 1:
                g.drawImage(backgroundImage1, 0, -20, this);
                break;
            case 2:
                g.drawImage(backgroundImage2, 0, -20, this);
                break;
            case 3:
                g.drawImage(backgroundImage3, 0, -20, this);
                break;
            case 4:
                g.drawImage(backgroundImage4, 0, -20, this);
                break;
            case 5:
                g.drawImage(backgroundImage5, 0, -20, this);
        }
    }
    
    /**
     *
     * @param r the foreground graphics handler
     */
    @Override
    protected void paintForeground(Graphics2D r) {
        r.setColor(Color.WHITE);
        r.setFont(new Font("Ariel", Font.BOLD, 40));
        if (game.getStageNo() == 0) {
        }
        else {
            r.drawImage(gui, 0, 0, this);
        }
        if (game.getStageNo() == 2) {
            r.drawImage(snowOverlay, 0, 50, this);
            r.drawImage(snowOverlay, 400, 50, this);
            r.drawImage(snowOverlay, 800, 50, this);
            r.drawImage(snowOverlay, 0, 350, this);
            r.drawImage(snowOverlay, 400, 350, this);
            r.drawImage(snowOverlay, 800, 350, this);
            r.drawImage(snowWhiteOverlay, 0, 50, this);
        }
        if (game.getStageNo() == 4) {
            r.drawImage(darknessOverlayPS, 0, 50, this);
        }
        if (game.getStageNo() == 3) {
            r.drawImage(darknessOverlay, 0, 50, this);
        }
        if (game.getStageNo() != 0) {
            int stringLengthScoreline = (int)r.getFontMetrics().getStringBounds(game.world.getScoreP1() + " : " + game.world.getScoreP2(), r).getWidth();
            int startPos = 600-(stringLengthScoreline/2);
            r.drawString(game.world.getScoreP1() + " : " + game.world.getScoreP2(), startPos, 30);
        }
        
        for (int i = 0; i < game.getP1Wins(); i++) {
            for (int spacing = 45*i; spacing < 45*game.getP1Wins(); spacing=spacing+45) {
                r.drawImage(ballIcon, 30+spacing, -5, this);
            }
        }
        for (int i = 0; i < game.getP2Wins(); i++) {
            for (int spacing = 944+45*i; spacing < 944+(45*game.getP2Wins()); spacing=spacing+45) {
                r.drawImage(ballIcon, 30+spacing, -5, this);
            }
        }
        
        if (game.getStageNo() == 6) {
            p1WinLine = game.mainMenu.getP1TextField() + " wins!";
            p2WinLine = game.mainMenu.getP2TextField() + " wins!";
            r.setColor(Color.black);
            r.drawImage(endGameOverlay, 0, -20, this);
            int stringLength1 = (int)r.getFontMetrics().getStringBounds("Press Esc to quit", r).getWidth();
            if (game.getP1Wins() > game.getP2Wins()) {
                int stringLength2 = (int)r.getFontMetrics().getStringBounds(p1WinLine, r).getWidth();
                    r.drawString(p1WinLine, 600-(stringLength2/2), 300);
                    r.setFont(new Font("Ariel", Font.BOLD, 30));
                    int stringLength3 = (int)r.getFontMetrics().getStringBounds(game.getP1Wins() + " rounds won", r).getWidth();
                    r.drawString(game.getP1Wins() + " rounds won", 600-(stringLength3/2), 400);
                    r.setFont(new Font("Ariel", Font.ITALIC, 30));
                    r.drawString(escLine, 650-(stringLength1/2), 480);
                }
                else if (game.getP1Wins() == game.getP2Wins()) {
                    int stringLengthD = (int)r.getFontMetrics().getStringBounds(drawLine, r).getWidth(); // draw line length
                    r.drawString(drawLine, 600-(stringLengthD/2), 300); // writing the draw line
                    r.setFont(new Font("Ariel", Font.BOLD, 30));
                    String string4 = "You both won " + game.getP1Wins() + " rounds";
                    int stringLength4 = (int)r.getFontMetrics().getStringBounds(string4, r).getWidth();
                    r.drawString(string4, 600-(stringLength4/2), 400);
                    r.setFont(new Font("Ariel", Font.ITALIC, 30));
                    r.drawString(escLine, 650-(stringLength1/2), 480);
                }
                else if (game.getP1Wins() < game.getP2Wins()) {
                    int stringLength2 = (int)r.getFontMetrics().getStringBounds(p2WinLine, r).getWidth();
                    r.drawString(p2WinLine, 600-(stringLength2/2), 300);
                    r.setFont(new Font("Ariel", Font.BOLD, 30));
                    String string5 = game.getP2Wins() + " rounds won!";
                    int stringLength5 = (int)r.getFontMetrics().getStringBounds(string5, r).getWidth();
                    r.drawString(string5, 600-(stringLength5/2), 400);
                    r.setFont(new Font("Ariel", Font.ITALIC, 30));
                    r.drawString(escLine, 650-(stringLength1/2), 480);
                }
        }
        r.setColor(Color.RED);
        r.setFont(new Font("Ariel", Font.ITALIC, 20));
        switch (game.getStageNo()) {
            case 1:
                r.drawString("Goal target: 1", 30, 70);
                break;
            case 2:
                r.drawString("Goal target: 2", 30, 70);
                break;
            case 3:
                r.drawString("Goal target: 3", 30, 70);
                break;
            case 4:
                r.drawString("Goal target: 4", 30, 70);
                break;
            case 5:
                r.drawString("Goal target: 5", 30, 70);
        }
    }
}
