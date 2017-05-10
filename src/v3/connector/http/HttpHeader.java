package v3.connector.http;

/**
 * Created by xuan on 2017/5/9 0009.
 */
public class HttpHeader {
    //-----------------------------fields------------------
    public char[] name;
    public int nameEnd;
    public char[] value;
    public int valueEnd;
    protected  int hashCode = 0;

    //-----------------------------constants--------------
    public static final int INITIAL_NAME_SIZE = 32;
    public static final int INITIAL_VALUE_SIZE = 64;
    public static final int MAX_NAME_SIZE = 128;
    public static final int MAX_VALUE_SIZE = 4096;

    //------------------------------constructors-----------
    public HttpHeader() {
        this(new char[INITIAL_NAME_SIZE], 0,
                new char[INITIAL_VALUE_SIZE], 0);
    }

    public HttpHeader(String name, String value) {
        this(name.toCharArray(), name.length(),
                value.toCharArray(), value.length());
    }

    public HttpHeader(char[] name, int nameEnd, char[] value, int valueEnd) {
        this.name = name;
        this.nameEnd = nameEnd;
        this.value = value;
        this.valueEnd = valueEnd;
    }

    //-------------------recycle------------
    public void recycle() {
        nameEnd = 0;
        valueEnd = 0;
        hashCode = 0;
    }

    //------------------name equals------------
    public boolean equals(HttpHeader header) {
        return equals(header.name, header.nameEnd);
    }

    public boolean headerEquals(HttpHeader header) {
        return (equals(header.name, header.nameEnd))
                &&
                (valueEquals(header.value, header.valueEnd));
    }

    public boolean equals(String buf) {
        return equals(buf.toCharArray());
    }

    public boolean equals(char[] buf) {
        return equals(buf, buf.length);
    }

    public boolean equals(char[] buf, int end) {
        if (end != nameEnd) return false;
        for (int i = 0; i < end; i++) {
            if (buf[i] != name[i]) return false;
        }
        return true;
    }

    //------------------value equals---------------
    public boolean valueEquals(String buf) {
        return valueEquals(buf.toCharArray());
    }

    public boolean valueEquals(char[] buf) {
        return valueEquals(buf, buf.length);
    }

    public boolean valueEquals(char[] buf, int end) {
        if (end != valueEnd) return false;
        for (int i = 0; i < end; i++) {
            if (buf[i] != value[i]) return false;
        }
        return true;
    }

    //-----------------value includes----------------
    public boolean valueIncludes(char[] buf) {
        return valueIncludes(buf, buf.length);
    }

    public boolean valueIncludes(String str) {
        return valueEquals(str.toCharArray());
    }

    public boolean valueIncludes(char[] buf, int end) {
        char firstChar = buf[0];
        int pos = 0;
        while (pos < valueEnd) {
            pos = valueIndexOf(firstChar, pos);
            if (pos == -1)
                return false;
            if (valueEnd - pos < end)
                return false;
            for (int i = 0; i < end; i++) {
                if (value[pos+i] != buf[i])
                    break;
                if (i == (end-1))
                    return true;
            }
            pos++;
        }
        return false;
    }

    //---------------value index of-----------------
    public int valueIndexOf(char c, int start) {
        for (int i = start; i < valueEnd; i++) {
            if (value[i] == c)
                return i;
        }
        return -1;
    }

    //---------------object---------------------------

    @Override
    public int hashCode() {
        int h = hashCode;
        if (h == 0) {
            int off = 0;
            char[] val = name;
            int len = nameEnd;
            for (int i = 0; i < len; i++) {
                h = 31*h + val[off++];
            }
            hashCode = h;
        }
        return h;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof String)
            return equals(((String)obj).toLowerCase());
        if (obj instanceof HttpHeader)
            return equals((HttpHeader)obj);
        return false;
    }
}
