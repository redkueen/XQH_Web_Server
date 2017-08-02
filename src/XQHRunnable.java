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
//            String requestLine = new String(b);

            BufferedReader brClient = new BufferedReader( new InputStreamReader(client.getInputStream()));
            String requestLine1=brClient.readLine();
            String requestFileName = requestLine1.split(" ")[1];
            BufferedReader brFile = new BufferedReader(new FileReader("D:\\XQH_Web_Server\\src\\" + requestFileName));
//            BufferedReader brFile = new BufferedReader(new FileReader("D:\\XQH_Web_Server\\src\\goods.html"));
            String readOneLine = null;
            String n ="";
            while ((readOneLine = brFile.readLine()) != null) {
                n = n + readOneLine;
            }
            String responseBody = n ;
            BufferedWriter bwClient = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
            String responseHead = "HTTP/1.1 200 OK\n" +
                    "Content-Length:" + responseBody.getBytes().length + "\n" ;
            bwClient.write(responseHead +
                    "\n" +
                    responseBody
            );
            bwClient.flush();
            brClient.close();
            client.close();
            brFile.close();
            bwClient.close();

        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }
}
