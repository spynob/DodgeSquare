import java.awt.*;
import java.util.Random;

public class MenuParticle extends GameObject {

    private Handler handler;
    Random r = new Random();
    private Color col;

    public MenuParticle(int x, int y, ID id, Handler handler){
        super(x, y, id);
        this.handler = handler;

        vely = (r.nextInt(15)-5);
        velx = (r.nextInt(10)-5);
        if (velx == 0) velx =1;
        if (vely == 0) vely =1;
        col= new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
    }

    public Color getActualColor(){
        return availableColors[couleur];
    }

    @Override
    public void tick() {
        if ((x + velx < 0) || (x + velx >= (Game.WIDTH - 40))) {
            this.setVelx((int)((-1)*velx));
        }
        else if ((y + vely < 0) || (y + vely > (Game.HEIGHT-60))) {
            this.setVely((int) ((-1)*vely));
        }

        handler.addObject(new BasicTrail(x, y, ID.Trail, col,handler, 0.05f, 16));
        x += velx;
        y += vely;

    }

    @Override
    public void render(Graphics g) {

        g.setColor(col);
        g.fillRect((int)x,(int) y, 16, 16);
    }

    public Rectangle getBounds(){
        return new Rectangle((int)x,(int) y, 16, 16);
    }
}