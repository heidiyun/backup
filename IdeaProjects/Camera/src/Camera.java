public class Camera {
    public Vector2 position;

    public Camera() {
        this.position = new Vector2();
    }

    public Vector2 getWorldToScreen(Vector2 worldPosition) {
        return new Vector2(worldPosition.x - position.x,
                worldPosition.y - position.y);
    }

    public Vector2 getWorldToScreen(float worldX, float worldY) {
        float x = worldX - position.x;
        float y = worldY - position.y;

        x %= Window.MAPSIZE;
        y %= Window.MAPSIZE;

        if (x > Window.SCREENWIDTH) x -= Window.MAPSIZE;
        if (y > Window.SCREENHEIGHT) y -= Window.MAPSIZE;

        if (x < -Window.SCREENWIDTH + Window.SCREENWIDTH / 2) x += Window.MAPSIZE;
        if (y < -Window.SCREENHEIGHT) y += Window.MAPSIZE;

        return new Vector2(x, y);
    }

    public Vector2 getScreenToWorld(Vector2 screenPosition) {
        return new Vector2(screenPosition.x + position.x,
                screenPosition.y + position.y);
    }
}
