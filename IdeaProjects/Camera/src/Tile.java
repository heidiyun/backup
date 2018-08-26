import processing.core.PApplet;

public class Tile {
    private int x;
    private int y;
    private int index;
    public static final int width = 50;
    public static final int height = 50;

    // 타일의 좌표에 RECT를 그렸어.
    // Camera 가 0,0

    public Tile(int index) {
        this.index = index;
        this.x = index % 20 * width;
        this.y = index / 20 * height;
    }


    public void render(PApplet p, Camera camera) {

        if (this.index % 2 == 0) p.fill(210);
        else p.fill(180);

        Vector2 pos = camera.getWorldToScreen(this.x, this.y);
        p.rect(pos.x, pos.y, width, height);
        p.fill(50);
        p.text("" + this.index, pos.x + 10, pos.y + 20);
    }

}
