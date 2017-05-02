package v1;

import java.io.OutputStream;

/**
 * Created by xuan on 2017/5/2 0002.
 */
public class Response {
    private OutputStream outputStream;
    private Request request;

    public Response(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public void sendStaticResource() {
        //todo
    }
}
