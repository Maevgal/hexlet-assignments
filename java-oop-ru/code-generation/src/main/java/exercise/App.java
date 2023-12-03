package exercise;

import lombok.SneakyThrows;

import java.nio.file.Files;
import java.nio.file.Path;

// BEGIN
public class App {
    @SneakyThrows
    public static void save(Path path, Car car) {
        Files.writeString(path, car.serialize());
    }

    @SneakyThrows
    public static Car extract(Path path) {
        String str = Files.readString(path);
        return Car.unserialize(str);
    }
}
// END
