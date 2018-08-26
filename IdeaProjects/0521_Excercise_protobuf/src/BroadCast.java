

import ex1.protobuf.Chat;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.List;

public class BroadCast extends Thread {

    private List<Session> sessions;
    private String text;

    {
        try {
            text = TextFileManager.readFile("./data/Text.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String[] words = text.split("\n");

    BroadCast(List<Session> sessions) {
        this.sessions = sessions;
    }

    public void addWord(String word) {
        Chat.Protocol protocol = new Chat.Protocol.Builder().setProto("a").setWord(word).build();
        broadcast(protocol);
//        broadcast("ADD," + s);
    }

    public void deleteWord(String s) {
        for (String word : words) {
            if (word.equals(s)) {
                Chat.Protocol protocol = new Chat.Protocol.Builder().setProto("r").setWord(word).build();
                broadcast(protocol);
//                broadcast("DEL," + s);
                break;
            }
        }

    }

    public void addColorWord(String word) {
        Chat.Protocol protocol = new Chat.Protocol.Builder().setProto("c").setWord(word).build();
        broadcast(protocol);
    }


    public void broadcast(Chat.Protocol protocol) {

        byte[] bytes = protocol.toByteArray();
        for (Session session : sessions) {
            try {
                OutputStream os = session.getOutputStream();
                DataOutputStream dos = new DataOutputStream(os);
                dos.writeShort(bytes.length);
                dos.write(bytes);

                System.out.println(bytes.length);
            } catch (IOException e) {
                // e.printStackTrace();
            }
        }


    }

    @Override
    public void run() {

        while (true) {

            try {
                sleep(1000);
                int random = (int) (Math.random() * words.length);
                String word = words[random];
                if (random % 10 == 0)
                    addColorWord(word);
                else
                    addWord(word);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }


}
