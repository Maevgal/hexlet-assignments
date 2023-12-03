package exercise;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.Value;

// BEGIN
@Value
// END
class Car {
    int id;
    String brand;
    String model;
    String color;
    User owner;

    // BEGIN
    @SneakyThrows
    public String serialize() {
        ObjectMapper mapper = new ObjectMapper();
        String jsonRepresentation = mapper.writeValueAsString(this);
        return jsonRepresentation;
    }

    @SneakyThrows
    public static Car unserialize(String jsonString) {
        Car car = new ObjectMapper().readValue(jsonString, Car.class);
        return car;
    }
    // END
}
