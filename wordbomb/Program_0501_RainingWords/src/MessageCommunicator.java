import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class MessageCommunicator {
    private String host;
    private int portNumber;
    private InputStream is;
    private OutputStream os;
    private OnCreateReceiveListener createWordReceiver;
    private OnRemoveReceiveListener removeWordReceiver;

    public MessageCommunicator(String host, int portNumber) {
        this.host = host;
        this.portNumber = portNumber;
        this.removeWordReceiver = removeWord -> { };
    }

    public void connect() {
        Socket socket = new Socket();
        InetSocketAddress endPoint = new InetSocketAddress(host, portNumber);
        try {
            socket.connect(endPoint);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            is = socket.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            os = socket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            int len;
            byte[] bytes = new byte[128];

            try {
                while ((len = is.read(bytes)) != -1) {
                    String readString = new String(bytes, 0, len);
                    String[] splitString = readString.split(",");
                    if (splitString[0].equals("a"))
                        createWordReceiver.onReceive(splitString[1]);
                    else removeWordReceiver.onReceive(splitString[1]);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void send(String word) {
        try {
            os.write(word.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    interface OnCreateReceiveListener {
        void onReceive(String word);
    }

    public void setCreateReceiver(OnCreateReceiveListener receiver) {
        this.createWordReceiver = receiver;
    }

    interface OnRemoveReceiveListener {
        void onReceive(String removeWord);
    }


    public void setRemoveReceiver(OnRemoveReceiveListener sender) {
        this.removeWordReceiver = sender;
    }

}