package exercise;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// BEGIN
public class Validator {
    public static List<String> validate(Address address) {
        Field[] fields = address.getClass().getDeclaredFields();
        List<String> result = new ArrayList<>();
        for (Field field : fields) {
            if (field.getAnnotation(NotNull.class) != null) {
                try {
                    field.setAccessible(true);
                    if (field.get(address) == null) {
                        result.add(field.getName());
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    public static Map<String, List<String>> advancedValidate(Address address) {
        Field[] fields = address.getClass().getDeclaredFields();
        Map<String, List<String>> result = new HashMap<>();
        List<String> exception = new ArrayList<>();
        for (Field field : fields) {
            try {
                if (field.getAnnotation(NotNull.class) instanceof NotNull) {
                    field.setAccessible(true);
                    if (field.get(address) == null) {
                        result.put(field.getName(), Collections.singletonList("can not be null"));
                    }
                }
                if (field.getAnnotation(MinLength.class) instanceof MinLength) {
                    field.setAccessible(true);
                    String f = (String) field.get(address);
                    int lengthField = f.length();
                    int lenghtAnnotation = field.getAnnotation(MinLength.class).minLength();
                    if (lenghtAnnotation > lengthField) {
                        result.put(field.getName(), Collections.singletonList("length less than " + lenghtAnnotation));
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
// END
