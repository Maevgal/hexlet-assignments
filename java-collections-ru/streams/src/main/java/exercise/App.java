package exercise;

import java.util.List;

// BEGIN
public class App {
    public static int getCountOfFreeEmails(List<String> emails) {
        long countFreeDomen = emails.stream()
                .filter(domen -> domen.contains("@gmail.com") || domen.contains("@yandex.ru") || domen.contains("@hotmail.com"))
                .count();
        return (int) countFreeDomen;
    }
}
// END
