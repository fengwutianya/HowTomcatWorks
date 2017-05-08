package v3.connector.http;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by xuan on 2017/5/8 0008.
 */
public class HttpProcessor {
    private HttpConnector connector;
    private HttpRequest request;
    private HttpResponse response;

    public HttpProcessor(HttpConnector httpConnector) {
        this.connector = httpConnector;
    }

    public void process(Socket socket) {
        SocketInputStream input = null;
        OutputStream output = null;

        try {
            input = new SocketInputStream(socket.getInputStream(), 2048);
            output = socket.getOutputStream();

            request = new HttpRequest(input);
            response = new HttpResponse(output);
            response.setRequest(request);
            response.setHeader("Server", "MyTomcat");

            parseRequest(input, output);
            parseHeaders(input);

            if (request.getRequestURI().startsWith("/servlet/")) {
                ServletProcessor processor = new ServletProcessor();
                processor.process(request, response);
            } else {
                StaticResourceProcessor processor = new StaticResourceProcessor();
                processor.process(request, response);
            }

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
