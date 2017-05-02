package v2;

import java.io.IOException;

/**
 * Created by xuan on 2017/5/2 0002.
 */
public class StaticResourceProcessor {
    public void process(Request request, Response response) {
        try {
            response.sendStaticResource();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
