import processing.core.PApplet;

public class Window extends PApplet {

    public void settings() {
        size(960, 640);
        try {
            SpriteManager.loadImage(this,
                    "./data/ani.png",
                    0,0,
                    32, 32,
                    3, 0, true);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

//        SpriteManager.loadImage(this,
//                0,
//                "./data/ani.png",
//                32, 32,
//                new int[] {0,1,2,1});

    }

    public void setup() {

    }

    int tick = 0;
    int aniCount = 0;

    public void draw() {
        background(204, 204, 204);
        tick++;
        if (tick % 10 == 0) aniCount++;
        image(SpriteManager.getImage(0, aniCount), 10, 10);
    }
}

