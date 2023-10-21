package exercise;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

// BEGIN
class App {
    public static Map genDiff(Map<String, Object> map1, Map<String, Object> map2) {
        Map<String, Object> comparison = new LinkedHashMap<>();
        SortedSet<String> key = new TreeSet<>();
        key.addAll(map1.keySet());
        key.addAll(map2.keySet());
        for (String k : key) {
            if (map2.containsKey(k) && map1.containsKey(k)) {
                if (map2.get(k).equals(map1.get(k))) {
                    comparison.put(k, "unchanged");
                } else {
                    comparison.put(k, "changed");
                }
            } else if (map1.containsKey(k)) {
                comparison.put(k, "deleted");
            } else {
                comparison.put(k, "added");
            }
        }
        System.out.println(comparison);
        return comparison;
    }
}
//END
