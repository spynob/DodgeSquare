import java.awt.*;
import java.util.Random;

public class SmartEnemy extends GameObject {

    private Handler handler;
    private GameObject player;

    public SmartEnemy(int x, int y, ID id, Handler handler){
        super(x, y, id);
        this.handler = handler;

        for(int i =0; i < handler.object.size(); i++){
            if (handler.object.get(i).getid() == ID.Player) player = handler.object.get(i);
        }

        availableColors = new Color[]{Color.cyan, Color.white, Color.red, Color.green};
        couleur = 3;
    }

    public Color getActualColor(){
        return availableColors[couleur];
    }

    @Override
    public void tick() {

        //float diffx = x - player.getx();
        //float diffy = y - player.gety();
        //float distance = (float) Math.sqrt((x-player.getx())*(x-player.getx()) + (y-player.gety())*(y-player.gety()));

        //velx =((-1/distance) * diffx);
        //vely =((-1/distance) * diffy);

        float px = player.getx();
        float py = player.gety();

        float dx = px - x;
        float dy = py - y;

        double norm = Math.sqrt(dx * dx + dy * dy);
        velx = (float) (dx / norm * 1);
        vely = (float) (dy / norm * 1);


        //float rx = px - x;
        //float ry = py - y;
        //float angle = (float) (Math.atan(ry/rx));
        //System.out.println("------------------------");
        //System.out.println(px);
        //System.out.println(py);
        //System.out.println(x);
        //System.out.println(y);
        //System.out.println(angle);
//
        //float vel = 3;
//
        //velx = (float) (vel * Math.cos(angle));
        //vely = (float) (vel * Math.sin(angle));
//
        //System.out.println(velx);
        //System.out.println(vely);

        x += velx;
        y += vely;

        handler.addObject(new BasicTrail(x, y, ID.Trail, availableColors[couleur], handler, 0.02f));




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
