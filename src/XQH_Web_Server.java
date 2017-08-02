import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class XQH_Web_Server {
    public static void main (String[] args){
        try {
            ServerSocket ss = new ServerSocket(80);
            while (true){
                Socket cli= ss.accept();
                new Thread(new XQHRunnable1(cli)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
