import processing.core.PApplet;


public class LaserBall extends View {

    private boolean isCollision;

    public LaserBall(float x, float y, float width, float height) {
      this.pos = new Vector2(x, y);
        this.width = width;
        this.height = height;
    }

    public void update() {
        pos = new Vector2(pos.x, pos.y - 2);
    }

    @Override
    public void render(PApplet pApplet) {
        pApplet.fill(255, 255, 255);
        pApplet.ellipse(pos.x, pos.y, width, height);
    }

    @Override
    public void addCollisionObject() {
        System.out.println("add");
        CollisionManager.allocate(this, "Ball");
    }

    @Override
    public void defineCollisionListener() {
        setCollisionListener((me, collisionObject, collisionDirection) -> {
            if (collisionObject.getName().equals("block")) {
                System.out.println("block & laserball col");
                isCollision = true;
            }
        });
    }

    @Override
    public String getShape() {
        return "circle";
    }

    @Override
    public String getName() {
        return "laserBall";
    }
}
