import java.awt.Color;
import java.awt.Graphics;

public class HUD {
    public static int HEALTH = 100;
    private int greenValue = 255;
    public int score = 0;
    private int level = 1;

    public void tick(){
        //HEALTH --;
        score++;
        HEALTH = (int)Game.clamp(HEALTH, 0, 100);
        greenValue = (int)Game.clamp(greenValue, 0, 255);
        greenValue = HEALTH*2;
        if (HEALTH == 0) System.exit(1);
    }

    public void render(Graphics g){
        g.setColor(Color.gray);
        g.fillRect(230, 15, 200, 32);
        g.setColor(new Color(75, greenValue, 0));
        g.fillRect(230, 15, HEALTH *2, 32);
        g.setColor(Color.gray);
        g.drawRect(230, 15, 200, 32);

        g.drawString("Score: " + score, 230, 64);
        g.drawString("Level: " + level, 230, 80);
    }

    public void setScore(int score){
        this.score = score;
    }

    public int getScore(){
        return score;
    }

    public void setLevel(int level){
        this.level = level;
    }

    public int getLevel(){
        return level;
    }
}
