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

    public static void main(String[] args) {
        User owner = new User(1, "Ivan", "P", 25);
// Вызываем автоматически сгенерированный геттер
        System.out.println(owner.getFirstName());
        Car car = new Car(1, "bmv", "x5", "black", owner);
        String jsonRepresentation = car.serialize();
        System.out.println(jsonRepresentation); // =>
/*
{
  "id":1,
  "brand":"bmv",
  "model":"x5",
  "color":"black",
  "owner":{
      "id":1,
      "firstName":"Ivan",
      "lastName":"P",
      "age":25
  }
}
*/

        Car instance = Car.unserialize(jsonRepresentation);
        System.out.println(instance.getBrand()); // "bmv"
        System.out.println(instance.getModel()); // "x5"
    }
}
