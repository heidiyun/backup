
import processing.core.PApplet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Window extends PApplet {

    public static void main(String[] args) {
        Window.main("Window");
    }

    private Client client;
    private StringBuilder answer;

    public void settings() {
        size(500, 500);
    }

    public void setup() {
        try {
            client = new Client("localhost", 3535);
            client.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        answer = new StringBuilder();
    }

    public void draw() {
        background(255);

        fill(0, 0, 255);
        text(answer.toString(), 200, 400);
        client.checkText();
        client.update();
        client.render(this);
    }

    public void keyPressed() {

        if ('a' <= key && 'z' >= key) {
            answer.append(key);
        } else if (keyCode == 32) {
            client.send(answer.toString());
            answer.delete(0, answer.length());
        } else if (key == BACKSPACE)
            if (answer.toString().length() > 0)
                answer.delete(answer.toString().length() - 1, answer.toString().length());

    }

}
