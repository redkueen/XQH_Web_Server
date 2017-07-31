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
//            BufferedReader bis = new BufferedReader( client.getInputStream());
//            byte[] b= new byte[bis.available()];
//            bis.read(b);
//            String s=new String(b);

            BufferedReader br2 = new BufferedReader( new InputStreamReader(client.getInputStream()));
            String L=br2.readLine();
            String f = L.split(" ")[1];
            BufferedReader BR = new BufferedReader(new FileReader("D:\\XQH_Web_Server\\src\\" + f));
//            BufferedReader BR = new BufferedReader(new FileReader("D:\\XQH_Web_Server\\src\\goods.html"));
            String k = null;
            String n ="";
            while ((k = BR.readLine()) != null) {
                n = n + k;
            }
            BufferedWriter BW = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
            BW.write("HTTP/1.1 200 OK\n" +
                    "Content-Length:" + n.getBytes().length + "\n" +
                    "\n" +
                    n
            );
            BW.flush();
            br2.close();
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
