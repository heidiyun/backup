import processing.core.PApplet;

import java.util.List;

public class TextObjects {

    private String text;
    private float x, y;

    TextObjects(String text){
        this.text = text;
        x = (float) (Math.random() * 300) + 100;
        y = 0;
    }

    public void update(){
        y += 1;
    }

    public void render(PApplet pApplet){
        pApplet.fill(0);
        pApplet.text(text, x, y);
    }

    public String getText(){
        return text;
    }

    public float getY(){
        return y;
    }

}
