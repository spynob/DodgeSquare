import java.awt.*;
import java.util.List;
import java.util.Random;

public class Pacman extends GameObject {

    public Pacman(int x, int y, ID id){
        super(x, y, id);
        Random r = new Random();

        //velx = r.nextInt(5);
        //vely = r.nextInt(5);
        vely = 0f;
        velx = 0f;

        availableColors = new Color[]{Color.pink, Color.MAGENTA, Color.YELLOW, Color.orange};
        couleur = r.nextInt(4);
    }

    @Override
    public void tick() {
        if (x+ velx >= Game.WIDTH-20){
            this.x = 1;
        }
        if (x + velx <= 0) {
            this.x = Game.WIDTH;
        }
        if (y+ vely >= Game.HEIGHT-20){
            this.y = 1;
        }
        if (y + vely <= 0) {
            this.y = Game.HEIGHT;
        }

        x += velx;
        y += vely;

    }

    @Override
    public void render(Graphics g) {

        g.setColor(availableColors[couleur]);
        g.fillRect((int)x,(int) y, 32, 32);
    }

    public Rectangle getBounds(){
        return new Rectangle((int)x,(int) y, 32, 32);
    }

    public Color getActualColor() {
        return availableColors[couleur];
    }
}
