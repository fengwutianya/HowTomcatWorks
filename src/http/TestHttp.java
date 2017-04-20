package http;

import java.io.*;
import java.net.Socket;

/**
 * Created by xuan on 2017/4/20 0020.
 */
public class TestHttp {
    public static void main(String[] args) throws IOException, InterruptedException {
        Socket socket = new Socket("127.0.0.1", 12345);
        OutputStream os = socket.getOutputStream();
        boolean autoflash = true;
        PrintWriter out = new PrintWriter(new OutputStreamWriter(os), autoflash);
        InputStream is = socket.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        out.println("GET /javaee/a.jsp HTTP/1.1");
        out.println("Host: localhost:12345");
        out.println("Connection: Close");
        out.println();

        boolean loop = true;
        StringBuilder sb = new StringBuilder(8096);
        while (loop) {
            if (br.ready()) {
                int i;
                while ((i =  br.read()) != -1) {
                    sb.append( (char)i);
                }
                loop = false;
            }
            Thread.sleep(50);
        }

        System.out.println(sb.toString());
        socket.close();
    }
}
