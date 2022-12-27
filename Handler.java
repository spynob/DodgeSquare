import java.awt.*;
import java.util.LinkedList;

public class Handler {
    LinkedList<GameObject> object = new LinkedList<>();

    public void tick(){
        for (int i = 0; i < object.size(); i++){
            GameObject tempObject = object.get(i);

            tempObject.tick();
        }
    }

    public void render(Graphics g){
        for(int i = 0; i < object.size(); i++){
            GameObject tempObject = object.get(i);

            tempObject.render(g);
        }
    }

    public void addObject(GameObject object){
        this.object.add(object);
    }

    public void removeObject(GameObject object){
        this.object.remove(object);
    }

    public void clearEnemies(){
        for (int i=object.size()-1; i>=0; i--){
            if ((object.get(i).getid() == ID.Boss) || object.get(i).getid() == ID.Trail|| (object.get(i).getid() == ID.Enemy) || (object.get(i).getid() == ID.FastEnemy) || (object.get(i).getid() == ID.SmartEnemy)){
                object.remove(i);
            }
        }
    }
    public void clearMenu(){
        for (int i= object.size()-1; i>=0; i--){
            if (object.get(i).getid() == ID.MenuParticle || object.get(i).getid() == ID.Trail){
                object.remove(i);
            }
        }
    }
}
