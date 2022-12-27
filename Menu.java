import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class Menu extends MouseAdapter {
    private Game game;
    private Handler handler;
    private Random r = new Random();
    private HUD hud;

    public Menu(Game game, Handler handler, HUD hud) {
        this.game = game;
        this.handler = handler;
        this.hud = hud;
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
            if (mouseOver(mx, my, 220, 200, 200, 64)) {game.gameState = Game.STATE.Help; handler.clearMenu();}

            //QUIT button
            if (mouseOver(mx, my, 220, 300, 200, 64)) System.exit(1);
        }
        else if (game.gameState == Game.STATE.Help) {
            //Back button
            if (mouseOver(mx, my, 220, 300, 200, 64)){
                game.gameState = Game.STATE.Menu;
                for (int i=0; i<10; i++){
                    handler.addObject(new MenuParticle(r.nextInt(Game.WIDTH-32)+16, r.nextInt(Game.HEIGHT-32)+16, ID.MenuParticle, handler));
                }
            }
        }
        else if (game.gameState == Game.STATE.End){
            //Try again button
            if (mouseOver(mx, my, 220, 200, 200, 64)) {
                game.gameState = Game.STATE.Game;
                hud.score = 0;
                handler.addObject(new Player(310, 200, ID.Player, handler));
                handler.addObject(new Enemy(300, 0, ID.Enemy, handler));
            }
            if (mouseOver(mx, my, 220, 300, 200, 64)){
                game.gameState = Game.STATE.Menu;
                for (int i=0; i<10; i++){
                    handler.addObject(new MenuParticle(r.nextInt(Game.WIDTH-32)+16, r.nextInt(Game.HEIGHT-32)+16, ID.MenuParticle, handler));
                }
            }
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
        else if (game.gameState == Game.STATE.End) {
            Font fnt = new Font("arial", 1, 50);
            Font fnt2 = new Font("arial", 1, 30);
            Font fnt3 = new Font("arial", 1, 18);

            g.setFont(fnt);
            g.setColor(Color.white);
            g.drawString("GAME OVER", 180, 70);
            g.setFont(fnt2);
            g.drawRect(220, 200, 200, 64);
            g.drawString("TRY AGAIN", 239, 245);
            g.drawRect(220, 300, 200, 64);
            g.drawString("MENU", 275, 345);
            g.setFont(fnt3);
            g.drawString(String.format("You lost with a score of : %d!", hud.score), 200, 120);
        }
    }
}
