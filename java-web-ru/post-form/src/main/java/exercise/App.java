package exercise;

import exercise.dto.users.UsersPage;
import exercise.model.User;
import exercise.repository.UserRepository;
import exercise.util.Security;
import io.javalin.Javalin;
import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.List;

public final class App {

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.plugins.enableDevLogging();
        });

        app.get("/", ctx -> {
            ctx.render("index.jte");
        });

        app.get("/users", ctx -> {
            List<User> users = UserRepository.getEntities();
            var page = new UsersPage(users);
            ctx.render("users/index.jte", Collections.singletonMap("page", page));
        });

        // BEGIN
        app.get("/users/build", ctx -> {
            ctx.render("users/build.jte");
        });

        app.post("/users", ctx -> {
            var firstName = StringUtils.capitalize(ctx.formParam("firstName").trim());
            var lastName = ctx.formParam("lastName").trim();
            var email = ctx.formParam("email").trim().toLowerCase();
            var password = Security.encrypt(ctx.formParam("password"));
            var passwordConfirmation = ctx.formParam("passwordConfirmation");

            var user = new User(firstName, lastName, email, password);
            UserRepository.save(user);
            ctx.redirect("/users");
        });
        // END

        return app;
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
