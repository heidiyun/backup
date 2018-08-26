import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

class Game {
    private InputStream is;
    private OutputStream os;

    public Game(ServerSocket serverSocket) throws IOException {
        Socket socket = serverSocket.accept();
        is = socket.getInputStream();
        os = socket.getOutputStream();
    }

    public void play() throws IOException {
        String result;
        String clientData;

        while (true) {
            byte[] bytes = new byte[128];
            int len;

            len = is.read(bytes);
            if (len == -1)
                break;
            clientData = new String(bytes, 0, len);
            System.out.println(clientData);

            result = setServerNum(clientData);

            os.write(result.getBytes());
        }
    }

    public String setServerNum(String clientData) {
        String[] data = {"가위", "바위", "보"};
        Random random = new Random();
        String serverData = data[random.nextInt(data.length)];
        return getResult(clientData, serverData) + "," + serverData;

    }

    public String getResult(String clientData, String serverData) {
        switch (clientData) {
            case "가위":
                switch (serverData) {
                    case "가위":
                        return "draw";
                    case "바위":
                        return "lose";
                    case "보":
                        return "win";
                }
            case "바위":
                switch (serverData) {
                    case "가위":
                        return "win";
                    case "바위":
                        return "draw";
                    case "보":
                        return "lose";
                }

            case "보":
                switch (serverData) {
                    case "가위":
                        return "lose";
                    case "바위":
                        return "win";
                    case "보":
                        return "draw";
                }
        }

        return null;
    }

}

public class GameServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(5000);
        Game game = new Game(serverSocket);

        game.play();
    }
}
