package exercise;

import java.util.Map;

// BEGIN
public final class SingleTag extends Tag {
    public SingleTag(String tag, Map<String, String> attribute) {
        super(tag, attribute);
    }

    public String toString() {
        final StringBuffer sb = new StringBuffer("<");
        sb.append(this.tag);
        for (Map.Entry<String, String> atr : this.attribute.entrySet()) {
            sb.append(" ").append(atr.getKey()).append("=\"").append(atr.getValue()).append("\"");
        }
        sb.append(">");
        return sb.toString();
    }
}
// END
