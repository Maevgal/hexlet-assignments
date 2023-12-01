package exercise;

import java.util.Map;

// BEGIN
public abstract class Tag {
    protected String tag;
    protected Map<String, String> attribute;

    public Tag(String tag, Map<String, String> attribute) {
        this.tag = tag;
        this.attribute = attribute;
    }

    public abstract String toString();
}
// END
