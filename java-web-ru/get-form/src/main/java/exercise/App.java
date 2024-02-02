package exercise;

import exercise.dto.users.UsersPage;
import exercise.model.User;
import io.javalin.Javalin;
import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.List;

public final class App {

    // Каждый пользователь представлен объектом класса User
    private static final List<User> USERS = Data.getUsers();

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.plugins.enableDevLogging();
        });

        // BEGIN
        app.get("/users", ctx -> {
            var term = ctx.queryParam("term");
            List<User> users;
            if (term == null) {
                users = USERS;
            } else {
                users = USERS
                        .stream()
                        .filter(u -> {
                            return StringUtils.startsWithIgnoreCase(u.getFirstName(), term);
                        })
                        .toList();
            }

            var page = new UsersPage(users, term);
            ctx.render("users/index.jte", Collections.singletonMap("page", page));

        });
        // END

        app.get("/", ctx -> {
            ctx.render("index.jte");
        });

        return app;
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
