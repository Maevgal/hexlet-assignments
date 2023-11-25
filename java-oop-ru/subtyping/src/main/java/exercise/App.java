package exercise;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

// BEGIN
class App {
    public static KeyValueStorage swapKeyValue(KeyValueStorage obj) {
        Map<String, String> map = new HashMap<>(obj.toMap());
        for (Entry<String, String> m : map.entrySet()) {
            obj.unset(m.getKey());
            obj.set(m.getValue(), m.getKey());
        }
        return obj;
    }
}
// END
