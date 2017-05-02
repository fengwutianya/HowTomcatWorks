package v2;

import java.net.URLClassLoader;

/**
 * Created by xuan on 2017/5/2 0002.
 */
public class ServletProcessor1 {
    public void process(Request request, Response response) {
        String uri = request.getUri();
        String servletName = uri.substring(uri.lastIndexOf("/") + 1);
        URLClassLoader loader = null;

        //todo
    }
}
