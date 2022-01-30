import java.awt.*;

public class BasicTrail extends GameObject{

    private float alpha = 1;
    private Handler handler;
    private Color color;
    private float life;

    public BasicTrail(float x, float y, ID id,Color color, Handler handler, float life){
        super(x, y, id);
        this.handler = handler;
        this.color = color;
        this.life = life;

    }

    public void tick() {
        if (alpha > life){
            alpha-= life - 0.001f;
        }else handler.removeObject(this);
    }

    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setComposite(makeTransparent(alpha));
        g.setColor(color);
        g.fillRect((int)x,(int) y, 16, 16);
        g2d.setComposite(makeTransparent(1));
    }

    public Rectangle getBounds() {
        return null;
    }

    private AlphaComposite makeTransparent(float alpha){
        int type = AlphaComposite.SRC_OVER;
        return (AlphaComposite.getInstance(type, alpha));
    }

    @Override
    public Color getActualColor() {
        return color;
    }
}
