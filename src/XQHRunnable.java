import java.io.*;
import java.net.Socket;

public class XQHRunnable implements Runnable{
    public Socket client;
    public XQHRunnable(Socket cli){
        this.client = cli;
    }

    @Override
    public void run() {
        try {
            BufferedReader BR = new BufferedReader(new FileReader("D:\\XQH_Web_Server\\src\\goods.html"));
            String k = null;
            String n ="";
            while ((k = BR.readLine()) != null) {
                n = n + k;
            }
            BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
            BW.write("HTTP/1.1 200 OK\n" +
                    "Content-Length:" + n.length() + "\n" +
                    "\n" +
                    n
            );
            BW.flush();
            client.close();
            BR.close();
            BW.close();

        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }
}
