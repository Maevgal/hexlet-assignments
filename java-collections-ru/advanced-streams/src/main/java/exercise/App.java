package exercise;

import java.util.*;
import java.util.stream.Collectors;

// BEGIN
class App {
    public static String getForwardedVariables(String content) {
        return Arrays.stream(content.split("\n"))
                .filter(x -> x.startsWith("environment"))
                .map(x -> x.substring(13, x.length() - 1))
                .map(x -> x.split(","))
                .flatMap(Arrays::stream)
                .filter(x -> x.startsWith("X_FORWARDED"))
                .map(x -> x.substring(12))
                .collect(Collectors.joining(","));
    }

}
//END
