public class Vector2 {
    public float x;
    public float y;

    public Vector2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vector2 setXY(float x, float y) {
        return new Vector2(x, y);
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public Vector2 add(float x, float y) {
        this.x += x;
        this.y += y;
        return this;
    }

    public Vector2 sub(float x, float y) {
        this.x -= x;
        this.y -= y;
        return this;
    }

    public Vector2 mul(float x, float y) {
        this.x *= x;
        this.y *= y;
        return this;
    }

    public Vector2 div(float x, float y) {
        this.x /= x;
        this.y /= y;
        return this;
    }

    public static Vector2 add(Vector2 v1, Vector2 v2) {
        return new Vector2(v1.x + v2.x, v1.y + v2.y);
    }

    public Vector2 sub(Vector2 v1, Vector2 v2) {
        return new Vector2(v1.x - v2.x, v1.y - v2.y);
    }

    public Vector2 mul(Vector2 v1, Vector2 v2) {
        return new Vector2(v1.x * v2.x, v1.y * v2.y);
    }

    public Vector2 div(Vector2 v1, Vector2 v2) {
        return new Vector2(v1.x / v2.x, v1.y / v2.y);
    }

    public float magnitude(Vector2 v1) {
        return (float) Math.sqrt(Math.pow(v1.x, 2) + Math.pow(v1.y, 2));
    }

    public float magnitude(float x, float y) {
        return (float) Math.sqrt(Math.pow(x,2) + Math.pow(y,2));
    }

    public Vector2 nomalize(Vector2 v1) {
        this.x /= magnitude(v1);
        this.y /= magnitude(v1);
        return this;
    }

    public Vector2 normalize() {
        this.x /= magnitude(x, y);
        this.y /= magnitude(x, y);
        return this;
    }

    public float distance(Vector2 v1) {
        return (float) Math.sqrt(Math.pow(this.x - v1.x, 2) + Math.pow(this.y - v1.y,2));
    }

    public float distance(float x, float y) {
        return (float) Math.sqrt(Math.pow(this.x - x, 2) + Math.pow(this.y - y,2));
    }
}
