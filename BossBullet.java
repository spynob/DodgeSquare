import java.awt.*;
import java.util.List;
import java.util.Random;

public class BossBullet extends GameObject {

    private Handler handler;

    public BossBullet(float x, float y, ID id, Handler handler){
        super(x, y, id);
        this.handler = handler;
        Random r = new Random();

        velx = r.nextInt(10)-5;
        //vely = r.nextInt(5);
        vely = 3.0f;
        //velx = 3.0f;

        availableColors = new Color[]{Color.cyan, Color.white, Color.red, Color.green};
        couleur = 2;
    }

    public Color getActualColor(){
        return availableColors[couleur];
    }

    @Override
    public void tick() {
        handler.addObject(new BasicTrail(x, y, ID.Trail, availableColors[couleur], handler, 0.1f, 16));
        if ((x + velx < 0) || (x + velx >= (Game.WIDTH - 40))) {
            this.setVelx((int) ((-1)*velx));
        }
        x += velx;
        y += vely;
        if (y >= Game.HEIGHT) handler.removeObject(this);

    }

    @Override
    public void render(Graphics g) {

        g.setColor(availableColors[couleur]);
        g.fillRect((int)x,(int) y, 16, 16);
    }

    public Rectangle getBounds(){
        return new Rectangle((int)x,(int) y, 16, 16);
    }
}
