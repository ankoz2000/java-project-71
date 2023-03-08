package hexlet.code;

public class DTO implements Comparable<DTO> {

    private String key;
    private String value;
    private String differ;

    public DTO(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public DTO(String key, String value, String differ) {
        this.key = key;
        this.value = value;
        this.differ = differ;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDiffer() {
        return differ;
    }

    public void setDiffer(String differ) {
        this.differ = differ;
    }

    @Override
    public String toString() {
        return (differ != null ? "\s\s" + differ + " " : "\s\s\s\s")
                + key + ": " + value;
    }

    @Override
    public int compareTo(DTO o) {
        return this.getKey().compareTo(o.getKey());
    }
}
