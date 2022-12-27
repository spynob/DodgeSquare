import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Menu extends MouseAdapter {
    private Game game;
    private Handler handler;

    public Menu(Game game, Handler handler) {
        this.game = game;
        this.handler = handler;
    }
    @Override
    public void mouseReleased(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e){
        int mx = e.getX();
        int my = e.getY();

        //PLAY Button
        if (game.gameState == Game.STATE.Menu) {
            if (mouseOver(mx, my, 220, 100, 200, 64)) {
                handler.clearMenu();
                game.gameState = Game.STATE.Game;
                handler.addObject(new Player(310, 200, ID.Player, handler));
                handler.addObject(new Enemy(300, 0, ID.Enemy, handler));
            }

            //HELP button
            if (mouseOver(mx, my, 220, 200, 200, 64)) game.gameState = Game.STATE.Help;

            //QUIT button
            if (mouseOver(mx, my, 220, 300, 200, 64)) System.exit(1);
        }
        else if (game.gameState == Game.STATE.Help) {
            //Back button
            if (mouseOver(mx, my, 220, 300, 200, 64)) game.gameState = Game.STATE.Menu;
        }

    }
    public void tick (){
    }

    private boolean mouseOver(int mx, int my, int x, int y, int width, int height){
        return (mx > x && mx < x+width && my > y && my < y+height);
    }
    public void render (Graphics g) {
        if (game.gameState == Game.STATE.Menu) {
            Font fnt = new Font("arial", 1, 50);
            Font fnt2 = new Font("arial", 1, 30);
            g.setFont(fnt);
            g.setColor(Color.white);
            g.drawString("MENU", 247, 70);

            g.setFont(fnt2);
            //g.setColor(Color.white);
            g.drawRect(220, 100, 200, 64);
            g.drawString("PLAY", 275, 145);
            //g.setColor(Color.white);
            g.drawRect(220, 200, 200, 64);
            g.drawString("HELP", 275, 245);
            //g.setColor(Color.white);
            g.drawRect(220, 300, 200, 64);
            g.drawString("QUIT", 275, 345);
        }
        else if (game.gameState == Game.STATE.Help) {
            Font fnt = new Font("arial", 1, 50);
            Font fnt2 = new Font("arial", 1, 30);
            Font fnt3 = new Font("arial", 1, 18);

            g.setFont(fnt);
            g.setColor(Color.white);
            g.drawString("HELP", 247, 70);
            g.setFont(fnt2);
            g.drawRect(220, 300, 200, 64);
            g.drawString("BACK", 280, 345);
            g.setFont(fnt3);
            g.drawString("Use WASD to move your character and dodge the enemies.", 70, 120);
            g.drawString("The Green bar represents your health.", 70, 150);
            g.drawString("Press C to change color.", 70, 190);
            g.drawString("Press M to return to Menu.", 70, 220);
            g.drawString("Press ESC to quit.", 70, 250);
        }
    }
}
