import dwon.SpriteManager.SpriteManager;
import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;

public class Window extends PApplet implements Constants {

    private List<View> views = new ArrayList<>();
    private List<View> removeViews = new ArrayList<>();
    private Map map;
    private View vaus;
    private Ball ball;
    private KeyEventManager keyEventManager;

    public void settings() {
        size(WINDOW_WIDTH, WINDOW_HEIGHT);
    }

    public void setup() {
        vaus = new Vaus(WINDOW_WIDTH / 2, WINDOW_HEIGHT - MARGIN_VERTICAL * 3);
        ball = new Ball(vaus.pos.x, vaus.pos.y - VAUS_HEIGHT / 2 - BALL_RADIUS - 2);
        map = new Map(this);

        views.add(ball);
        views.add(vaus);
        views.add(map);

        setKeyListener();

        for (View view : views) {
            view.addCollisionObject();
            view.defineCollisionListener();
        }

        ball.speed = 5;

    }

    public void draw() {
        background(0);
        keyEventManager.update();
        CollisionManager.collide();

        for (View view : views) {

            if (view.getIsCollision())
                removeViews.add(view);

            view.update();
            view.render(this);
        }

        for (View view : removeViews) {
            views.remove(view);
        }

        removeViews.clear();

    }

    public void setKeyListener() {
        keyEventManager = new KeyEventManager();

        keyEventManager.addPressListener(37, (isOnPress, duration) -> {
            if (vaus.pos.x - VAUS_WIDTH / 2 >= MARGIN_HORIZONTAL) vaus.pos.x -= VAUS_SPEED;
        });

        keyEventManager.addPressListener(39, (isOnPress, duration) -> {
            if (vaus.pos.x + VAUS_WIDTH / 2 <= WINDOW_WIDTH - MARGIN_HORIZONTAL) vaus.pos.x += VAUS_SPEED;
        });

        keyEventManager.addPressListener(32, (isOnPress, duration) -> {

        });
        keyEventManager.addReleaseListener(32, duration -> {
            if (DataHelper.getVaus().getVausState() == LASER_VAUS) {
                int i = views.size();
                views.add(new LaserBall(vaus.pos.x - VAUS_WIDTH / 2, vaus.pos.y - VAUS_HEIGHT / 2, 5, 5));
                views.add(new LaserBall(vaus.pos.x + VAUS_WIDTH / 2, vaus.pos.y - VAUS_HEIGHT / 2, 5, 5));
                for (int j = i; j < views.size(); j ++) {
                    views.get(i).addCollisionObject();
                    views.get(i).defineCollisionListener();
                }
            }
        });

    }

    public void keyPressed() {
        keyEventManager.setPress(keyCode);
    }

    public void keyReleased() {
        keyEventManager.setRelease(keyCode);
    }
}
