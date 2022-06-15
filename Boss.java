import java.awt.*;
import java.util.Random;

public class Boss extends GameObject{
    private Handler handler;
    private int timer = 100;
    private int timer2 = 50;

    public Boss(float x, float y, ID id, Handler handler){
        super(x, y, id);
        this.handler = handler;
        Random r = new Random();
        this.dim = 64;

        //velx = r.nextInt(5);
        //vely = r.nextInt(5);
        vely = 1.0f;
        velx = 0f;

        availableColors = new Color[]{Color.cyan, Color.white, Color.red, Color.green};
        couleur = 2;
    }

    public Color getActualColor(){
        return availableColors[couleur];
    }

    @Override
    public void tick() {
        if (timer == 0) vely = 0;
        else timer--;
        if (timer == 0) timer2--;
        if (timer2 == 0) velx = 2;
        if ((x + velx < 0) || (x + velx >= (Game.WIDTH - 40))) {
            this.setVelx((int) ((-1)*velx));
        }
        else if ((y + vely < 0) || (y + vely > (Game.HEIGHT-60))) {
            this.setVely((int) ((-1)*vely));
        }

        //handler.addObject(new BasicTrail(x, y, ID.Trail, availableColors[couleur], handler, 0.03f, dim));
        x += velx;
        y += vely;

    }

    @Override
    public void render(Graphics g) {

        g.setColor(availableColors[couleur]);
        g.fillRect((int)x,(int) y, dim, dim);
    }

    public Rectangle getBounds(){
        return new Rectangle((int)x,(int) y, 64, 64);
    }
}
