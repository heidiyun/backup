import processing.core.PApplet;

public class Ball extends View implements Constants {

    public Ball(float x, float y) {
        this.pos = new Vector2(x, y);
        this.width = BALL_RADIUS * 2;
        this.height = BALL_RADIUS * 2;
        this.direction = new Vector2(1, -1);
        this.speed = 3;
    }

    @Override
    public void update() {
        checkWall();
        this.pos = new Vector2(pos.x + (speed * direction.x), pos.y + (speed * direction.y));

    }

    @Override
    public void render(PApplet pApplet) {
        pApplet.fill(255);
        pApplet.ellipse(pos.x, pos.y, BALL_RADIUS * 2, BALL_RADIUS * 2);
    }


    @Override
    public void addCollisionObject() {
        CollisionManager.allocate(this, "Ball");
    }

    public void defineCollisionListener() {

        setCollisionListener((me, collisionObject, collisionDirection) -> {
            if (collisionObject.getName().equals("block")) {
                System.out.println("col");
                switch (collisionDirection) {
                    case L:
                        me.pos = new Vector2(this.pos.x
                                - (BALL_RADIUS - (collisionObject.pos.x - this.pos.x - BLOCK_WIDTH / 2)),
                                this.pos.y);
                        me.direction = new Vector2(me.direction.x * -1, me.direction.y);
                        break;
//                    case LB:
//                        me.direction = new Vector2(me.direction.x * -1, me.direction.y);
//                        me.direction = new Vector2(me.direction.x, me.direction.y * -1);
//                        break;
                    case B:
                        me.pos = new Vector2(this.pos.x,
                                this.pos.y + (BALL_RADIUS - (this.pos.y - collisionObject.pos.y - BLOCK_HEIGHT / 2)));
                        me.direction = new Vector2(me.direction.x, me.direction.y * -1);
                        break;
//                    case RB:
//                        me.direction = new Vector2(me.direction.x * -1, me.direction.y);
//                        me.direction = new Vector2(me.direction.x, me.direction.y * -1);
//                        break;
                    case R:
                        me.pos = new Vector2(this.pos.x
                                + (BALL_RADIUS - (this.pos.x - collisionObject.pos.x - BLOCK_WIDTH / 2)),
                                this.pos.y);
                        me.direction = new Vector2(me.direction.x * -1, me.direction.y);
                        break;
//                    case RT:
//                        me.direction = new Vector2(me.direction.x * -1, me.direction.y);
//                        me.direction = new Vector2(me.direction.x, me.direction.y * -1);
//                        break;
                    case T:
                        me.pos = new Vector2(this.pos.x,
                                this.pos.y - (BALL_RADIUS - (collisionObject.pos.y - this.pos.y - BLOCK_HEIGHT / 2)));
                        me.direction = new Vector2(me.direction.x, me.direction.y * -1);
                        break;
//                    case LT:
//                        me.direction = new Vector2(me.direction.x * -1, me.direction.y);
//                        me.direction = new Vector2(me.direction.x, me.direction.y * -1);
//                        break;

                }
            } else if (collisionObject.getName().equals("vaus")) {
                float diff = pos.x - DataHelper.getVaus().pos.x;
                float max = BALL_RADIUS + DataHelper.getVaus().width / 2;
                float val = diff / max;
                System.out.println(val);
                System.out.println("past x : " + direction.x);
                System.out.println("past y : " + direction.y);
                direction = new Vector2(val * 2, -1).normalize();
                System.out.println("x : " + direction.x);
                System.out.println("y : " + direction.y);
            }
        });

    }

    public void checkWall() {
        if (this.pos.x - BALL_RADIUS / 2 <= MARGIN_HORIZONTAL) {
            this.direction = new Vector2(this.direction.x * -1, this.direction.y);
        }
        if (this.pos.x + BALL_RADIUS / 2 >= WINDOW_WIDTH - MARGIN_HORIZONTAL) {
            this.direction = new Vector2(this.direction.x * -1, this.direction.y);
        }
        if (this.pos.y - BALL_RADIUS / 2 <= MARGIN_VERTICAL) {
            this.direction = new Vector2(this.direction.x, this.direction.y * -1);
        }
    }

    @Override
    public String getShape() {
        return "circle";
    }

    @Override
    public String getName() {
        return "ball";
    }
}
