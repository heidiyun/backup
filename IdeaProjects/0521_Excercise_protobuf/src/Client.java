import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Client extends Thread {

    private Socket socket;
    private List<TextObjects> texts = new ArrayList<>();

    Client(String host, int port) throws IOException {
        socket = new Socket(host, port);
    }

    public void send(String s) {
        OutputStream os = null;
        try {
            os = socket.getOutputStream();
            System.out.println(s);
            os.write(s.getBytes());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {

        try {
            InputStream is = socket.getInputStream();

            int len;
            byte[] buf = new byte[128];
            while ((len = is.read(buf)) != -1) {

                String word = new String(buf, 0, len);
                String[] string = word.split(",");
                if (string[0].equals("ADD")) {
                    texts.add(new TextObjects(string[1]));
                } else if (string[0].equals("DEL")) {
                    for (int i = 0; i < texts.size(); i++) {
                        if (texts.get(i).getText().equals(string[1])) {
                            texts.remove(i);
                            break;
                        }
                    }
//                    if (texts.contains(string[1])) {
//                        System.out.println(string[1] + texts.size() + "asdf");
//                        texts.remove(string[1]);
//                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void update() {

//        List<TextObjects> current = texts;
//        for (TextObjects t : current) {
//            t.update();
//        }

        for(int i = 0; i < texts.size(); i++){
            texts.get(i).update();
        }
    }

    public void render(Window window) {

//        List<TextObjects> current = texts;
//        for (TextObjects t : current) {
//            t.render(window);
//        }

        for(int i = 0; i < texts.size(); i++){
            texts.get(i).render(window);
        }
    }

    public void checkText(){
        for(int i = 0; i < texts.size(); i++){
            if(texts.get(i).getY() > 480){
                texts.remove(i);
                break;
            }
        }
    }


}
