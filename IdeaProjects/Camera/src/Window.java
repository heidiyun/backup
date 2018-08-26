import processing.core.PApplet;

import java.io.IOException;
import java.util.ArrayList;


public class Window extends PApplet {

    public static final int SCREENWIDTH = 640;
    public static final int SCREENHEIGHT = 480;
    public static final int MAPSIZE = 1000;


    public Camera camera;

    public static void main(String[] args) {
        PApplet.main("Window");
    }

    private ArrayList<Tile> map = new ArrayList<>();
    private Player player = new Player();

    public Window() throws IOException {

    }

    @Override
    public void exit() {
        System.out.println("exit");

    }

    @Override
    public void setup() {
        camera = new Camera();
        for (int i = 0; i < 400; i++) {
            map.add(new Tile(i));
        }
        super.setup();
        background(255);
    }

    @Override
    public void settings() {
        super.settings();
        size(640, 480);
    }

    @Override
    public void keyPressed() {

        switch (keyCode) {
            case 39:
                player.move(15, 0);
                break;
            case 37:
                player.move(-15, 0);
                break;
            case 38:
                player.move(0, -15);
                break;
            case 40:
                player.move(0, 15);
                break;
        }
    }

    @Override
    public void draw() {
        background(255);

        camera.position.x = player.getX() - SCREENWIDTH / 2;
        camera.position.y = player.getY() - SCREENHEIGHT / 2;

//        camera.position.x = 0;
//        camera.position.y = 0;
        for (Tile tile : map) {
            tile.render(this, camera);
        }
        player.render(this, camera);

        // DEBUG

        this.text("CAMERA : " + camera.position.x + " , " + camera.position.y,
                10, 20);

        this.text("PLAYER : " + player.getX() + " , " + player.getY(),
                10, 50);
    }


}
