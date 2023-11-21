package exercise;

import java.util.List;

// BEGIN
public class App {
    public static List<String> buildApartmentsList(List<Home> homes, int count) {
        return homes.stream()
                .limit(count)
                .sorted((o1, o2) -> (int) (o1.getArea() - o2.getArea()))
                .map(x -> x.toString())
                .toList();
    }
}
// END
