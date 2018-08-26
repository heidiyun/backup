import dwon.SpriteManager.SpriteManager;
import processing.core.PApplet;

public class Block extends View implements Constants {
    private int color;
    private int item;
    private int life;
    private Vector2 itemPos;

    public Block(int x, int y) {
        this.pos = Util.IndexToPosition(x, y);
        this.width = BLOCK_WIDTH;
        this.height = BLOCK_HEIGHT;
    }

    public Block(int color, int item, int life, int row_index, int column_index) {
        this.pos = Util.IndexToPosition(row_index, column_index);
        this.itemPos = Util.IndexToPosition(row_index, column_index);
        this.color = color;
        this.item = item;
        this.life = life;
        this.width = BLOCK_WIDTH;
        this.height = BLOCK_HEIGHT;


    }

    @Override
    public void render(PApplet pApplet) {
//        pApplet.fill(255,0,0);
//        pApplet.rect(pos.x - BLOCK_WIDTH / 2, pos.y - BLOCK_HEIGHT / 2, BLOCK_WIDTH, BLOCK_HEIGHT);
            pApplet.image(SpriteManager.getImage(color, 0), pos.x - BLOCK_WIDTH / 2,
                    pos.y - BLOCK_HEIGHT / 2, BLOCK_WIDTH, BLOCK_HEIGHT);

    }


    public void minusBlockLife() {
        if (life > 0) life--;
    }

    public int getLife() {
        return life;
    }

    public int getItem() {
        return item;
    }

    @Override
    public String getShape() {
        return "rect";
    }

    @Override
    public String getName() {
        return "block";
    }
}
