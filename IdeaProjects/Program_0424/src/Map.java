import dwon.SpriteManager.SpriteManager;
import processing.core.PApplet;

import java.util.ArrayList;
import java.util.HashMap;

public class Map extends View implements Constants {
    private ArrayList<Block> blocks = new ArrayList<>();
    private HashMap<Block, Item> items = new HashMap<>();
    private ArrayList<Block> removeItems = new ArrayList<>();
    private ArrayList<Block> removeBlocks = new ArrayList<>();
    private PApplet pApplet;

    public Map(PApplet pApplet) {
        this.pApplet = pApplet;
        setBlocks();
        loadImage();
    }


    public void setBlocks() {
        String[][] block = CsvFileManager.getCsvArray("./data/map.csv");

        for (int i = 0; i < block.length; i++) {
            for (int j = 0; j < block[i].length; j++) {
                blocks.add(new Block(Integer.parseInt(block[i][j]) / 100 * 10,
                        Integer.parseInt(block[i][j]) / 10 % 10,
                        Integer.parseInt(block[i][j]) % 10, i, j));
            }
        }

        for (Block b : blocks) {
            if (b.getItem() != 0) {
                if (!items.containsKey(b))
                    items.put(b, new Item(b.pos.x, b.pos.y, b.getItem()));
            }
        }

        System.out.println(items.size());
    }

    @Override
    public void addCollisionObject() {
        for (View block : blocks) {
            CollisionManager.allocate(block, "Block");
        }

    }


    public void defineCollisionListener() {
        for (Block block : blocks) {
            block.setCollisionListener((me, collisionObject, collisionDirection) -> {
                if (collisionObject.getName().equals("ball")) {
                    block.minusBlockLife();
                    if (block.getLife() == 0) {
                        if (block.getItem() != 0)
                            items.get(block).setItemVisible(true);
                        removeBlocks.add(block);
                    }

                } else if (collisionObject.getName().equals("laserBall")) {
                    block.minusBlockLife();
                    if (block.getLife() == 0) {
                        if (block.getItem() != 0)
                            items.get(block).setItemVisible(true);
                        removeBlocks.add(block);
                    }
                }
            });
        }

    }


    public void update() {
        for (int i = 0; i < removeItems.size(); i++) {
            items.remove(removeItems.get(i));
            CollisionManager.removeObject(removeItems.get(i), "Block");
        }

        for (int i = 0; i < removeBlocks.size(); i++) {
            blocks.remove(removeBlocks.get(i));
            CollisionManager.removeObject(removeBlocks.get(i), "Block");
        }
    }

    @Override
    public void render(PApplet pApplet) {
        pApplet.image(SpriteManager.getImage(Constants.WALL, 0),
                Constants.MARGIN_HORIZONTAL - Constants.BLOCK_WIDTH / 2 - 10,
                Constants.MARGIN_VERTICAL - Constants.BLOCK_HEIGHT - 10,
                Constants.WINDOW_WIDTH - Constants.MARGIN_HORIZONTAL * 2 + Constants.BLOCK_WIDTH + 13,
                Constants.WINDOW_HEIGHT - Constants.MARGIN_VERTICAL + Constants.BLOCK_HEIGHT);

        for (View block : blocks) {
            block.render(pApplet);
        }

        for (Block block : items.keySet()) {
            items.get(block).render(pApplet);
            if (items.get(block).getIsCollision()) {
                removeItems.add(block);
            }
        }


    }

    @Override
    public String getShape() {
        return null;
    }

    @Override
    public String getName() {
        return "Map";
    }

    public void loadImage() {
        SpriteManager.loadImage(pApplet, BLOCK_BLUE, "./images/block_blue.png");
        SpriteManager.loadImage(pApplet, BLOCK_CYON, "./images/block_skyblue.png");
        SpriteManager.loadImage(pApplet, BLOCK_GOLD, "./images/block_immortal.png");
        SpriteManager.loadImage(pApplet, BLOCK_GRAY, "./images/block_white.png");
        SpriteManager.loadImage(pApplet, BLOCK_GREEN, "./images/block_green.png");
        SpriteManager.loadImage(pApplet, BLOCK_ORANGE, "./images/block_orange.png");
        SpriteManager.loadImage(pApplet, BLOCK_HARD, "./images/block_hard.png");
        SpriteManager.loadImage(pApplet, BLOCK_PINK, "./images/block_pink.png");
        SpriteManager.loadImage(pApplet, BLOCK_YELLOW, "./images/block_yellow.png");
        SpriteManager.loadImage(pApplet, BLOCK_RED, "./images/block_red.png");
        SpriteManager.loadImage(pApplet, WALL, "./images/wall.png");

        SpriteManager.loadSprites(pApplet, NORMAL_VAUS, "./images/vaus.png", 0, 0, 152, 56, 6);
        SpriteManager.loadSprites(pApplet, LASER_VAUS, "./images/vaus_laser.png", 0, 0, 152, 56, 6);
        SpriteManager.loadSprites(pApplet, EXTENDED_VAUS, "./images/vaus_extended.png", 0, 0, 228, 56, 6);

        SpriteManager.loadSprites(pApplet, ITEM_PLAYER, "./images/item_player.png", 0, 0, 80, 44, 8);
        SpriteManager.loadSprites(pApplet, ITEM_LASER, "./images/item_laser.png", 0, 0, 80, 44, 8);
        SpriteManager.loadSprites(pApplet, ITEM_ENLARGE, "./images/item_extend.png", 0, 0, 80, 44, 8);
        SpriteManager.loadSprites(pApplet, ITEM_CATCH, "./images/item_clasp.png", 0, 0, 80, 44, 8);
        SpriteManager.loadSprites(pApplet, ITEM_SLOW, "./images/item_slow.png", 0, 0, 80, 44, 8);
        SpriteManager.loadSprites(pApplet, ITEM_BREAK, "./images/item_bonus.png", 0, 0, 80, 44, 8);
        SpriteManager.loadSprites(pApplet, ITEM_DISRUPTION, "./images/item_doom.png", 0, 0, 80, 44, 8);

    }
}
