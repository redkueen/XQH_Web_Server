import java.io.*;
import java.net.Socket;

/**
 * 添加html 模板
 */
public class XQHRunnable1 implements Runnable{
    public Socket client;
    public XQHRunnable1(Socket cli){
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

           String template = "<!DOCTYPE html>\n" +
                   "<html lang=\"en\">\n" +
                   "<head> \n" +
                   "    <meta charset=\"UTF-8\">\n" +
                   "    <title>用户</title>\n" +
                   "</head>\n" +
                   "<body>\n" +
                   "<div>用户名：usernameX</div>\n" +
                   "<div>电话：telephoneX</div>\n" +
                   "<div>性别:sexX</div>\n" +
                   "<div>地址：addressX</div>\n" +
                   "</body>\n" +
                   "</html>";


           int id =Integer.parseInt(requestFileName.replace("/user.html?id=",""));
           User[] users = {null,new User("xqh","156","女","月球"),new User("桂林","145","男","地球")};
            String username = users[id].username;
            String telephone = users[id].telphone;
            String sex =users[id].sex;
            String address = users[id].address;
            template=template.replace("usernameX",username);
            template=template.replace("telephoneX",telephone);
            template=template.replace("sexX",sex);
            template=template.replace("addressX",address);

            String responseBody = template ;
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
