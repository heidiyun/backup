import processing.core.PApplet;

public class Player {
    private float x;
    private float y;

    public void move(float x, float y) {
        this.x += x;
        this.y += y;
    }

    public void render(PApplet p, Camera camera) {
        p.fill(255, 100, 0);
        Vector2 pos = camera.getWorldToScreen(new Vector2(this.x, this.y));
        p.ellipse(pos.x, pos.y, 30, 30);
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }
}
