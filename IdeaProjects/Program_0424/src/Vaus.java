import dwon.SpriteManager.SpriteManager;
import processing.core.PApplet;

public class Vaus extends View implements Constants {

    private int tick = 0;
    private int itemState;
    private int vausState;

    public Vaus(float x, float y) {
        this.pos = new Vector2(x, y);
        this.width = VAUS_WIDTH;
        this.height = VAUS_HEIGHT;
        this.vausState = NORMAL_VAUS;
        DataHelper.setVaus(this);
    }

    public void update() {

    }

    @Override
    public void addCollisionObject() {
        CollisionManager.allocate(this, "Vaus");
    }


    public void defineCollisionListener() {
        setCollisionListener((me, collisionObject, collisionDirection) -> {
            if (collisionObject.getName().equals("item")) {
            }
        });
    }

    @Override
    public void render(PApplet pApplet) {
        tick++;
        if (vausState == EXTENDED_VAUS) {
            pApplet.image(SpriteManager.getImage(vausState, tick / 10),
                    pos.x - (VAUS_WIDTH * 2) / 2, pos.y - VAUS_HEIGHT / 2, VAUS_WIDTH * 2, VAUS_HEIGHT);
        } else {
            pApplet.image(SpriteManager.getImage(vausState, tick / 10),
                    pos.x - VAUS_WIDTH / 2, pos.y - VAUS_HEIGHT / 2, VAUS_WIDTH, VAUS_HEIGHT);
        }
    }

    public void setItemState(int itemState) {
        this.itemState = itemState;
        setVausState(itemState);
    }

    public void setVausState(int itemState) {
        switch (itemState) {
            case 2:
                vausState = LASER_VAUS;
                break;
            case 3:
                vausState = EXTENDED_VAUS;
        }
    }

    public int getVausState() {
        return vausState;
    }

    @Override
    public String getShape() {
        return "rect";
    }

    @Override
    public String getName() {
        return "vaus";
    }
}
