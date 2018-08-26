import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;

public class Window2 extends PApplet {
    private MessageCommunicator messageCommunicator;
    private List<Word2> words;
    private List<Word2> removeWords;
    private List<Character> characters;
    private StringBuilder checkWord;

    public void settings() {
        size(960, 640);
    }

    public void setup() {
        checkWord = new StringBuilder();
        words = new ArrayList<>();
        removeWords = new ArrayList<>();
        characters = new ArrayList<>();
        messageCommunicator = new MessageCommunicator("localhost", 5000);
        messageCommunicator.setCreateReceiver(word -> {
            synchronized (words) {
                words.add(new Word2(word));
            }
        });
        messageCommunicator.setRemoveReceiver(new MessageCommunicator.OnRemoveReceiveListener() {
            @Override
            public void onReceive(String removeWord) {

                System.out.println(removeWord);
                for (Word2 word : words) {
                    if (word.getWord().equals(removeWord))
                        removeWords.add(word);
                }
            }
        });
        messageCommunicator.connect();

    }

    public void draw() {
        background(255);

        for (Word2 word : words) {
            word.update();
            word.render(this);
            if (!word.getIsAppear())
                removeWords.add(word);
        }


        for (Word2 removeWord : removeWords) {
            words.remove(removeWord);
        }

        StringBuilder inputWords = new StringBuilder();
        for (Character character : characters) {
            inputWords.append(character);
        }

        text(inputWords.toString(), 400, 550);

        for (Word2 word : words) {
            if (word.getWord().equals(checkWord.toString())) {
                messageCommunicator.send(word.getWord());
                checkWord.setLength(0);
            } else messageCommunicator.send("");
        }

        removeWords.clear();
    }

    public void keyPressed() {
        if (keyCode == ENTER) {
            checkWord.setLength(0);
            for (Character character : characters) {
                checkWord.append(character);
            }
            characters.clear();
        } else if (keyCode == BACKSPACE) {
            if (characters.size() > 0)
                characters.remove(characters.size() - 1);
        } else {
            characters.add(key);
        }
    }
}

