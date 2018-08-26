

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Accept extends Thread {

    private int port;
    private BroadCast broadCast;
    private List<Session> sessions = new CopyOnWriteArrayList<>();

    Accept(int port){
        this.port = port;
    }

    public void addSession(Session session){
        sessions.add(session);
    }

    public void removeSession(Session session){
        sessions.remove(session);
    }

    @Override
    public void run(){

        try {
            ServerSocket serverSocket = new ServerSocket(port);
            serverSocket.setReuseAddress(true);

            broadCast = new BroadCast(sessions);
            broadCast.start();

            while(true){

                Socket socket = serverSocket.accept();
                Session session = new Session(socket, broadCast);
                System.out.println(socket.getRemoteSocketAddress());

                session.sessionDisconnect(s-> {
                    removeSession(session);
                });

                session.start();
                addSession(session);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
