import processing.core.PApplet;

public abstract class View {
    public Vector2 pos;
    public Vector2 direction;
    public float speed;
    public float width;
    public float height;
    public boolean isSend;

    public View() {

    }

    public boolean getIsCollision() {
        return false;
    }

    public void update() {

    }


    public void addCollisionObject() {
    }

    public CollisionManager.OnCollisionListener onCollisionListener = (me, collisionObject, collisionDirection) -> {

    };

    public void defineCollisionListener() {

    }
    public void setCollisionListener(CollisionManager.OnCollisionListener listener) {
        this.onCollisionListener = listener;
    }

    public abstract void render(PApplet pApplet);

    public abstract String getShape();

    public abstract String getName();

}
