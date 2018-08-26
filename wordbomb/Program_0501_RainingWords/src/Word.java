import processing.core.PApplet;

public class Word {
    private String word;
    private float x;
    private float y;
    private float score;
    private boolean isAppear = true;
    private PApplet pApplet;

    public Word(PApplet pApplet, String word) {
        this.x = (float) (Math.random() * 700 + 10);
        this.y = 50;
        this.word = word;
        this.pApplet = pApplet;
        this.score = word.length() * 5;
    }

    public void update() {
        if (y > 450)
            isAppear = false;
        y += 2;
    }

    public void render() {
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
