// This java project was done by pretty much just copying the series
// Java ProgrammingL Let's Build a Game
// by RealTutsGML on Youtube.

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;

public class Game extends Canvas implements Runnable {

    public static final int WIDTH = 640, HEIGHT = WIDTH/12*9;
    private Thread thread;
    private boolean running = false;
    private Random r;
    private Handler handler;
    public static int gameSpeed = 3;
    private HUD hud;
    private Spawn spawner;


    public Game(){
        handler = new Handler();
        hud = new HUD();
        spawner = new Spawn(handler, hud);
        this.addKeyListener(new KeyInput(handler));
        new Window(WIDTH, HEIGHT, "Game", this);

        r = new Random();
        //for (int i = 0; i < 3; i++){
        //    handler.addObject(new Player(0, 0, ID.Player));
        //}
        handler.addObject(new Player(400, 100, ID.Player, handler));
        //handler.addObject(new Pacman(0, 200, ID.Pacman));
        handler.addObject(new Enemy(320, 320, ID.Enemy, handler));

        this.requestFocusInWindow();
    }

    public synchronized void start(){
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized  void stop(){
        try{
            thread = new Thread(this);
            thread.join();
            running = false;

        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void run(){
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1){
                tick();
                delta --;
            }
            if (running){
                render();
            }
            frames ++;
            if (System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                //System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }

    private void tick(){
        //System.out.println(gameSpeed);
        handler.tick();
        hud.tick();
        spawner.tick();

    }

    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.black);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        handler.render(g);
        hud.render(g);

        g.dispose();
        bs.show();
    }

    public static float clamp(float var, float min, float max){
        if (var >= max) return max;
        else if (var <= min) return min;
        else return var;
    }
    public static void main(String[] args){
    new Game();
    }
}
