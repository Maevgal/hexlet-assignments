package exercise;

import exercise.annotation.Inspect;
import exercise.model.Address;

import java.lang.reflect.Method;

public class Application {
    public static void main(String[] args) {
        var address = new Address("London", 12345678);

        // BEGIN
        for (Method method : Address.class.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Inspect.class)) {
                long startTime = System.currentTimeMillis();

                try {
                    method.invoke(address);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                String typeName = method.getGenericReturnType().getTypeName();
                System.out.println("Method " + method.getName()
                        + " returns a value of type " + getShortType(typeName));
            }
        }
        // END
    }

    private static String getShortType(String typeName) {
        return typeName.contains(".") ? typeName.substring(typeName.lastIndexOf(".") + 1) : typeName;
    }
}
