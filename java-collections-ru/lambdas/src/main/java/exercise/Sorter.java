package exercise;

import java.util.Comparator;
import java.util.Map;
import java.util.List;
import java.time.LocalDate;
import java.util.stream.Collectors;

// BEGIN
class Sorter {
    public static List<String> takeOldestMans(List<Map<String, String>> users) {
        List<String> collect = users.stream()
                .filter(man -> man.get("gender").equals("male"))
                .sorted(Sorter::compareMap)
                .map(man -> man.get("name"))
                .collect((Collectors.toList()));
        return collect;
    }

    private static int compareMap(Map<String, String> map1, Map<String, String> map2) {
        LocalDate date1 = LocalDate.parse(map1.get("birthday"));
        LocalDate date2 = LocalDate.parse(map2.get("birthday"));
        return date1.compareTo(date2);
    }
}
// END
