import javax.net.ssl.HandshakeCompletedListener;
import java.awt.*;
import java.util.List;
import java.util.Random;

public class Player extends GameObject {
    Handler handler;

    public Player(float x, float y, ID id, Handler handler){
        super(x, y, id);
        Random r = new Random();
        this.handler = handler;
        vely = 0;
        velx = 0;

        availableColors = new Color[]{Color.cyan, Color.white, Color.ORANGE, Color.green};
        couleur = r.nextInt(4);
    }

    @Override
    public Color getActualColor() {
        return availableColors[couleur];
    }

    @Override
    public void tick() {
        x += velx;
        y += vely;

        x = Game.clamp((int)x, 0 , Game.WIDTH -40);
        y = Game.clamp((int)y, 0, Game.HEIGHT -60);

        collision();

    }

    private void collision(){
        for (int i =0 ; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);

            if(tempObject.getid() == ID.Enemy || tempObject.getid() == ID.BossBullet || tempObject.getid() == ID.FastEnemy || tempObject.getid() == ID.SmartEnemy || tempObject.getid() == ID.Boss){
                if(getBounds().intersects(tempObject.getBounds())) {
                    HUD.HEALTH -= 2;
                    if (tempObject.getid() == ID.BossBullet){
                        handler.object.remove(i);
                        HUD.HEALTH-= 5;
                        i--;
                    }
                }
            }
        }
    }

    @Override
    public void render(Graphics g) {

        g.setColor(availableColors[couleur]);
        g.fillRect((int) x,(int) y, 32, 32);
    }

    public Rectangle getBounds(){
        return new Rectangle((int)x,(int) y, 32, 32);
    }
}
