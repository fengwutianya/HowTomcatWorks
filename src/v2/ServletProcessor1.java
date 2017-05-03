package v2;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;

/**
 * Created by xuan on 2017/5/2 0002.
 */
public class ServletProcessor1 {
    public void process(Request request, Response response) {
        String uri = request.getUri();
        String servletName = uri.substring(uri.lastIndexOf("/") + 1);
        URLClassLoader loader = null;

        try {
            URL[] urls = new URL[1];
            URLStreamHandler streamHandler = null;
            File classpath = new File(Constants.WEB_ROOT);
            String repository = (new URL("file", null,
                    classpath.getCanonicalPath() + File.separator)).toString();
            urls[0] = new URL(null, repository, streamHandler);
            loader = new URLClassLoader(urls);
        } catch (IOException e) {
            System.out.println(e.toString());
        }

        Class myClass = null;
        try {
            myClass = loader.loadClass(servletName);
        } catch (ClassNotFoundException e) {
            System.out.println(e.toString());
        }

        Servlet servlet = null;
        try {
            servlet = (Servlet) myClass.newInstance();
            servlet.service((ServletRequest)request, (ServletResponse)response);
        } catch (InstantiationException | IOException | ServletException | IllegalAccessException e) {
            System.out.println(e.toString());
        }
    }
}
