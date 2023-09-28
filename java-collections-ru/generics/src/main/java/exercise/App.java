package exercise;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;

// BEGIN
public class App {
    public static List<Map<String, String>> findWhere(List<Map<String, String>> books, Map<String, String> filter) {
        List<Map<String, String>> result = new ArrayList<>();
        for (Map<String, String> book : books) {
            if (isSuitableBook(book, filter)) {
                result.add(book);
            }
        }
        return result;
    }

    private static boolean isSuitableBook(Map<String, String> book, Map<String, String> filter) {
        for (Map.Entry<String, String> entry : filter.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (!value.equals(book.get(key))) {
                return false;
            }
        }
        return true;
    }
}
//END
