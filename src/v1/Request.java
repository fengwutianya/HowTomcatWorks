package v1;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by xuan on 2017/5/2 0002.
 */
public class Request {
    private InputStream inputStream;
    private String uri;

    public Request(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public void parse() {
        byte[] buffer = new byte[2048];
        int i;
        try {
            i = inputStream.read(buffer);
        } catch (IOException e) {
            e.printStackTrace();
            i = -1;
        }

        StringBuffer request = new StringBuffer(2048);
        for (int j = 0; j < i; j++) {
            request.append((char)buffer[j]);
        }
        System.out.println(request.toString());
        uri = parseUri(request.toString());
    }

    private String parseUri(String requestString) {
        int index1 = -1, index2 = -1;
        index1 = requestString.indexOf(' ');
        if (index1 != -1) {
            index2 = requestString.indexOf(' ', index1 + 1);
            if (index2 > index1)
                return requestString.substring(index1+1, index2);
        }
        return null;
    }

    public String getUri() {
        return uri;
    }
}
