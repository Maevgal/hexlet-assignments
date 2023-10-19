package exercise;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

// BEGIN
class App {
    public static String[] duplicateValues(String[] items) {

        return Arrays.stream(items)
                .flatMap(item -> Arrays.stream(new String[] {item, item}))
                .toArray(String[]::new);
    }

    public static String[][] enlargeArrayImage(String[][] image) {

        String[][] horizontalyStretched = Arrays.stream(image)
                .map(App::duplicateValues)
                .toArray(String[][]::new);

        return Arrays.stream(horizontalyStretched)
                .flatMap(item -> Arrays.stream(new String[][] {item, item}))
                .toArray(String[][]::new);
    }

    public static void main(String[] args) {
        String[][] image = {
                {"*", "*", "*", "*"},
                {"*", " ", " ", "*"},
                {"*", " ", " ", "*"},
                {"*", "*", "*", "*"},
        };

        System.out.println(Arrays.deepToString(image));
        String[][] enlargedImage = App.enlargeArrayImage(image);
        System.out.println(Arrays.deepToString(enlargedImage));
    }
}
// END
