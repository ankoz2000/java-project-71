package hexlet.code;

public final class DTO implements Comparable<DTO> {

    private String key;
    private Object value;
    private String differ;
    private Object oldValue;

    public DTO(String key, Object value) {
        this.key = key;
        this.value = value;
    }

    public DTO(String key, Object value, String differ) {
        this.key = key;
        this.value = value;
        this.differ = differ;
    }

    public DTO(String key, Object value, String differ, Object oldValue) {
        this.key = key;
        this.value = value;
        this.differ = differ;
        this.oldValue = oldValue;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getDiffer() {
        return differ;
    }

    public void setDiffer(String differ) {
        this.differ = differ;
    }

    @Override
    public int compareTo(DTO o) {
        return this.getKey().compareTo(o.getKey());
    }

    public Object getOldValue() {
        return oldValue;
    }

    public void setOldValue(Object oldValue) {
        this.oldValue = oldValue;
    }
}
