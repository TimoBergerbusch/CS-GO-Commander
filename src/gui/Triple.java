package gui;

public class Triple {

    private String key;
    private Object obj;
    private String value;

    public Triple(String key, Object obj, String value) {
        this.key = key;
        this.obj = obj;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
