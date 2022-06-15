import java.awt.*;
import java.util.Random;

public class SmartEnemy extends GameObject {

    private Handler handler;
    private GameObject player;
    private int SPEED;

    public SmartEnemy(int x, int y, ID id, Handler handler){
        super(x, y, id);
        this.handler = handler;
        this.SPEED = 2;

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
        float px = player.getx();
        float py = player.gety();

        float velx = px - x;
        float vely = py - y;

        double norm = Math.sqrt(velx * velx + vely * vely);
        velx = (float) (SPEED * velx / norm);
        vely = (float) (SPEED * vely / norm);

        x += velx;
        y += vely;

        handler.addObject(new BasicTrail(x, y, ID.Trail, availableColors[couleur], handler, 0.02f, 16));
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
