package v3.connector.http;

/**
 * Created by xuan on 2017/5/9 0009.
 */
public class HttpHeader {
    public char[] name;
    public int nameEnd;
    public char[] value;
    public int valueEnd;
    protected  int hashCode = 0;
}
