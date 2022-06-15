import java.awt.*;

public abstract class GameObject {
    protected float x, y;
    protected ID id;
    protected float velx, vely;
    protected int couleur;
    protected int dim;
    public Color[] availableColors;

    public GameObject(float x, float y, ID id){
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public abstract void tick();
    public abstract void render(Graphics g);
    public abstract Rectangle getBounds();
    public abstract Color getActualColor();

    public void setx(int x){
        this.x = x;
    }

    public void sety(int y){
        this.y = y;
    }

    public void setid(ID id){
        this.id = id;
    }

    public void setColor(int color){
        this.couleur = color;
    }

    public int getColor(){
        return this.couleur;
    }

    public float getx(){
        return this.x;
    }

    public float gety(){
        return this.y;
    }

    public ID getid(){
        return id;
    }

    public void setVelx(int velx){
        this.velx = velx;
    }

    public void setVely(int vely){
        this.vely = vely;
    }

    public float getVelx(){
        return this.velx;
    }

    public float getVely(){
        return this.vely;
    }
    public int getDim() {return dim;}
    public void setDim(int dim) {this.dim = dim;}

}
