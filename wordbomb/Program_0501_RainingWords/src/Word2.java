import processing.core.PApplet;

public class Word2 {
    private String word;
    private float x;
    private float y;
    private float score;
    private boolean isAppear = true;

    public Word2(String word) {
        this.x = (float) (Math.random() * 700 + 10);
        this.y = 50;
        this.word = word;
        this.score = word.length() * 5;
    }

    public void update() {
        if (y > 600)
            isAppear = false;
        y += 1;
    }

    public void render(PApplet pApplet) {
        pApplet.fill(0, 0, 80);
        pApplet.textSize(20);
        pApplet.text(word, x, y);
    }

    public boolean getIsAppear() {
        return isAppear;
    }

    public String getWord() {
        return word;
    }

    public float getScore() {
        return score;
    }
}