package exercise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// BEGIN
public class App {
    public static Map<String, Integer> getWordCount(String str) {
        if (str == null || str.isEmpty() || str.isBlank()) {
            return Map.of();
        }
        Map<String, Integer> map = new HashMap<>();
        List<String> wordStr = new ArrayList<>(Arrays.asList(str.split(" ")));
        for (int i = 0; i < wordStr.size(); i++) {
            map.put(wordStr.get(i), map.getOrDefault(wordStr.get(i), 0) + 1);
        }
        return map;
    }

    public static String toString(Map<String, Integer> map) {
        if (map.isEmpty()) {
            return "{}";
        }
        StringBuilder result2 = new StringBuilder();
        result2.append("{\n");
        for (String key : map.keySet()) {
            result2.append("  " + key + ": " + map.get(key) + "\n");
        }
        result2.append("}");
        return result2.toString();
    }
}
// END
