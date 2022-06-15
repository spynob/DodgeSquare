import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{
    private Handler handler;
    private Game game;

    private boolean[] keyDown = {false, false, false, false};

    public KeyInput(Handler handler, Game game){
        this.handler = handler;
        this.game = game;
    }
    @Override
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_ESCAPE) System.exit(1);
        if (key == KeyEvent.VK_M) {
            game.gameState = Game.STATE.Menu;
            game.close();
        }

        for (int i = 0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);
            int speed = Game.gameSpeed;

            if (tempObject.getid() == ID.Player){
                //key events for player

                if (key == KeyEvent.VK_W) {tempObject.setVely((-1)*speed); keyDown[0] = true;}
                if (key == KeyEvent.VK_S) {tempObject.setVely(speed); keyDown[1] = true;}
                if (key == KeyEvent.VK_A) {tempObject.setVelx((-1)*speed); keyDown[2] = true;}
                if (key == KeyEvent.VK_D) {tempObject.setVelx(speed); keyDown[3] = true;}

            }

            if (tempObject.getid() == ID.Player){
                //key events for player

                if (key == KeyEvent.VK_UP) {tempObject.setVely((-1)*speed); keyDown[0] = true;}
                if (key == KeyEvent.VK_DOWN) {tempObject.setVely(speed); keyDown[1] = true;}
                if (key == KeyEvent.VK_LEFT) {tempObject.setVelx((-1)*speed); keyDown[2] = true;}
                if (key == KeyEvent.VK_RIGHT) {tempObject.setVelx(speed); keyDown[3] = true;}

            }

            if (tempObject.getid() == ID.Pacman){
                //key events for pacman

                if (key == KeyEvent.VK_UP) tempObject.setVely((-1)*speed);
                if (key == KeyEvent.VK_DOWN) tempObject.setVely(speed);
                if (key == KeyEvent.VK_LEFT) tempObject.setVelx((-1)*speed);
                if (key == KeyEvent.VK_RIGHT) tempObject.setVelx(speed);

            }
        }
    }

    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();

        for (int i = 0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);

            if (tempObject.getid() == ID.Player){
                //key events for player

                if (key == KeyEvent.VK_W) keyDown[0] = false;
                if (key == KeyEvent.VK_S) keyDown[1] = false;
                if (key == KeyEvent.VK_A) keyDown[2] = false;
                if (key == KeyEvent.VK_D) keyDown[3] = false;
                if (key == KeyEvent.VK_C) tempObject.setColor((tempObject.getColor() + 1)%4);

                //vertical movement
                if (!keyDown[0] & !keyDown[1]) tempObject.setVely(0);
                //horizontal movement
                if (!keyDown[2] & !keyDown[3]) tempObject.setVelx(0);

            }

            if (tempObject.getid() == ID.Pacman){
                //key events for pacman

                if (key == KeyEvent.VK_UP) tempObject.setVely(0);
                if (key == KeyEvent.VK_DOWN) tempObject.setVely(0);
                if (key == KeyEvent.VK_LEFT) tempObject.setVelx(0);
                if (key == KeyEvent.VK_RIGHT) tempObject.setVelx(0);
                if (key == KeyEvent.VK_SPACE) tempObject.setColor((tempObject.getColor() + 1)%4);


            }
        }
    }

    public void keyTyped(KeyEvent e){
        int key = e.getKeyCode();
    }
}
