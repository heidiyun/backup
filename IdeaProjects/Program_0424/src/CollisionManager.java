import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CollisionManager {

    private static HashMap<String, ArrayList<View>> collisionObjects = new HashMap<>();

    public enum Direction {
        T, LT, L, LB, B, RB, R, RT, NONE
    }

    public static boolean rectCollision(Vector2 rect1, Vector2 rect2, float rect1Width, float rect1Height, float rect2Width, float rect2Height) {
        return (rect1.x - rect1Width / 2 <= rect2.x + rect2Width / 2
                && rect1.x + rect1Width / 2 >= rect2.x - rect2Width / 2
                && rect1.y - rect1Height / 2 <= rect2.y + rect2Height / 2
                && rect1.y + rect1Height / 2 >= rect2.y - rect2Height / 2);
    }

    public static boolean rectCircleCollision(Vector2 rect, Vector2 circle, float rectWidth, float rectHeight, float circleRadius) {
        if (circle.x > (rect.x - (rectWidth / 2) - circleRadius)
                && circle.x < (rect.x + (rectWidth / 2) + circleRadius)
                && circle.y > (rect.y - (rectHeight / 2) - circleRadius)
                && circle.y < (rect.y + (rectHeight / 2) + circleRadius)) {
            if (Math.abs(rect.x - circle.x) > rectWidth / 2
                    && Math.abs(rect.y - circle.y) > rectHeight / 2) {
                if (circle.distance(rect.x - rectWidth / 2, rect.y - rectHeight / 2) < circleRadius
                        || circle.distance(rect.x + rectWidth / 2, rect.y - rectHeight / 2) < circleRadius
                        || circle.distance(rect.x - rectWidth / 2, rect.y + rectHeight / 2) < circleRadius
                        || circle.distance(rect.x + rectWidth / 2, rect.y + rectHeight / 2) < circleRadius)
                    return true;
                else return false;
            } else return true;
        } else return false;
    }

    public static boolean circleCollision(Vector2 circle1, Vector2 circle2, float circle1Radius, float circle2Radius) {
        return circle1.distance(circle2.x, circle2.y) <= circle1Radius + circle2Radius;
    }

    public static boolean rectPointCollision(Vector2 rect, Vector2 point, float rectWidth, float rectHeight) {
        return rect.x - rectWidth / 2 <= point.x
                && rect.x + rectWidth / 2 >= point.x
                && rect.y - rectHeight / 2 >= point.y
                && rect.y + rectHeight / 2 <= point.y;
    }

    public static boolean circlePointCollision(Vector2 circle, Vector2 point, float circleRadius) {
        return circle.distance(point.x, point.y) <= circleRadius;
    }

    public static Direction alertCollisionDirection(Vector2 target, Vector2 compareTarget, float targetWidth, float targetHeight) {

//        if (Math.abs(target.x - compareTarget.x) > targetWidth / 2
//                && Math.abs(target.y - compareTarget.y) > targetHeight / 2) {
//            if (target.x > compareTarget.x
//                    && target.y > compareTarget.y) return Direction.LT;
//            else if (target.x > compareTarget.x
//                    && target.y < compareTarget.y) return Direction.LB;
//            else if (target.x < compareTarget.x
//                    && target.y > compareTarget.y) return Direction.RT;
//            else return Direction.RB;
//
//        } else {

        if (target.x - targetWidth / 2 > compareTarget.x) {
            return Direction.L;
        } else if (target.x + targetWidth / 2 < compareTarget.x) {
            return Direction.R;
        } else if (target.y - targetHeight / 2 > compareTarget.y
                || target.y > compareTarget.y) {
            return Direction.T;
        } else {
            return Direction.B;
        }

//        return Direction.NONE;
    }

    public static void allocate(View object, String layer) {
        ArrayList<View> views;

        if (!collisionObjects.containsKey(layer)) {
            views = new ArrayList<>();
            views.add(object);
            collisionObjects.put(layer, views);
        } else {
            views = collisionObjects.get(layer);
            views.add(object);
        }

    }


    public static void removeObject(View object, String layer) {
        if (!collisionObjects.containsKey(layer)) return;
        collisionObjects.get(layer).remove(object);
    }


    public interface OnCollisionListener {
        void onCollide(View me, View collisionObject, Direction collisionDirection);
    }

    public static void collide() {

        for (String layer : collisionObjects.keySet()) {
            ArrayList<View> views = collisionObjects.get(layer);
            for (View view : views) {
                view.isSend = false;
            }
        }


        for (String layer : collisionObjects.keySet()) {
            ArrayList<String> preList = new ArrayList<>(collisionObjects.keySet());

            ArrayList<View> targetList = collisionObjects.get(layer);
            preList.remove(layer);

            for (String layer2 : preList) {
                ArrayList<View> compareList = collisionObjects.get(layer2);

                for (View target : targetList) {
                    for (View compareTarget : compareList) {
                        if (target.getShape().equals("rect") && compareTarget.getShape().equals("rect")) {
                            if (rectCollision(target.pos, compareTarget.pos, target.width, target.height,
                                    compareTarget.width, compareTarget.height)) {

                                target.onCollisionListener.onCollide(target, compareTarget,
                                        alertCollisionDirection(target.pos, compareTarget.pos,
                                                target.width, target.height));


                                compareTarget.onCollisionListener.onCollide(compareTarget, target,
                                        alertCollisionDirection(target.pos, compareTarget.pos,
                                                target.width, target.height));

                            }

                        } else if (target.getShape().equals("rect") && compareTarget.getShape().equals("circle")) {
                            if (rectCircleCollision(target.pos, compareTarget.pos, target.width, target.height,
                                    compareTarget.width / 2)) {

                                    target.onCollisionListener.onCollide(target, compareTarget,
                                            alertCollisionDirection(target.pos, compareTarget.pos, target.width, target.height));

                                    compareTarget.onCollisionListener.onCollide(compareTarget, target,
                                            alertCollisionDirection(target.pos, compareTarget.pos, target.width, target.height));


                            }
                        } else if (target.getShape().equals("rect") && compareTarget.getShape().equals("point")) {
                            if (rectPointCollision(target.pos, compareTarget.pos, target.width, target.height)) {

                                target.onCollisionListener.onCollide(target, compareTarget,
                                        alertCollisionDirection(target.pos, compareTarget.pos,
                                                target.width, target.height));
                                compareTarget.onCollisionListener.onCollide(compareTarget, target,
                                        Direction.NONE);

                            }
                        }
//                            }
                        else if (target.getShape().equals("circle") && compareTarget.getShape().equals("circle")) {

                            System.out.println("Circle Col!");
                            if (circleCollision(target.pos, compareTarget.pos, target.width / 2, compareTarget.width / 2)) {

                                target.onCollisionListener.onCollide(target, compareTarget,
                                        Direction.NONE);
                                compareTarget.onCollisionListener.onCollide(compareTarget, target,
                                        Direction.NONE);
                            }

                        } else if (target.getShape().equals("circle") && compareTarget.getShape().equals("point")) {
                            if (circlePointCollision(target.pos, compareTarget.pos, target.width / 2)) {

                                target.onCollisionListener.onCollide(target, compareTarget,
                                        Direction.NONE);
                                compareTarget.onCollisionListener.onCollide(compareTarget, target,
                                        Direction.NONE);

                            }
                        }
                    }
                }
            }
        }

    }
}








