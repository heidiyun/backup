
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Session extends Thread {

    private Socket socket;
    private BroadCast broadCast;
    private setListener listener;

    Session(Socket socket, BroadCast broadCast) {
        this.socket = socket;
        this.broadCast = broadCast;
    }

    interface setListener {
        void disconnect(Session session);
    }

    public void sessionDisconnect(setListener listener) {
        this.listener = listener;
    }

    public OutputStream getOutputStream() throws IOException {
        return socket.getOutputStream();
    }


    @Override
    public void run() {
        try {

            InputStream is = socket.getInputStream();
            int len;
            byte[] buf = new byte[128];

            while ((len = is.read(buf)) != -1) {
                String message = new String(buf, 0, len);
                broadCast.deleteWord(message);
            }

        } catch (IOException e) {
            disconnect();
            listener.disconnect(this);
        }
    }

    public void disconnect(){
        try {
            getOutputStream().close();
            socket.getInputStream().close();
            socket.getOutputStream().close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
