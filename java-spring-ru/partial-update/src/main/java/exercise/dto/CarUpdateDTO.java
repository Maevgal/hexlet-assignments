package exercise.dto;

import lombok.Getter;
import lombok.Setter;
import org.openapitools.jackson.nullable.JsonNullable;

// BEGIN
@Getter
@Setter
public class CarUpdateDTO {
    JsonNullable<String> model;
    JsonNullable<String> manufacturer;
    JsonNullable<Integer> enginePower;
}
// END
