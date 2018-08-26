import dwon.SpriteManager.SpriteManager;
import processing.core.PApplet;

public class Item extends View implements Constants {
    private int itemIndex;
    private boolean itemVisible;
    private boolean isCollision;
    private int tick = 0;

    public Item(float x, float y, int itemIndex) {
        pos = new Vector2(x, y);
        this.itemIndex = itemIndex;
        this.width = BLOCK_WIDTH;
        this.height = BLOCK_HEIGHT;
        addCollisionObject();
        defineCollisionListener();
    }

    @Override
    public void render(PApplet pApplet) {
        tick++;
        if (itemVisible) {
            pApplet.image(SpriteManager.getImage(itemIndex, tick / 10), pos.x - BLOCK_WIDTH / 2,
                    pos.y - BLOCK_HEIGHT / 2, BLOCK_WIDTH, BLOCK_HEIGHT);
            pos = new Vector2(pos.x, pos.y + 2);
        }
    }


    @Override
    public void addCollisionObject() {
        CollisionManager.allocate(this, "Block");
    }

    public void defineCollisionListener() {

        setCollisionListener((me, collisionObject, collisionDirection) -> {
            if (collisionObject.getName() == "vaus") {
                itemVisible = false;
                isCollision = true;
                DataHelper.getVaus().setItemState(this.itemIndex);
            }
        });
    }

    public void setItemVisible(boolean itemVisible) {
        this.itemVisible = itemVisible;
    }

    public boolean getIsCollision() {
        return isCollision;
    }


    @Override
    public String getShape() {
        return "rect";
    }

    @Override
    public String getName() {
        return "item";
    }
}
