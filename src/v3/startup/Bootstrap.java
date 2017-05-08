package v3.startup;

import v3.connector.http.HttpConnector;

/**
 * Created by xuan on 2017/5/8 0008.
 */
public class Bootstrap {
    public static void main(String[] args) {
        HttpConnector connector = new HttpConnector();
        connector.start();
    }
}
