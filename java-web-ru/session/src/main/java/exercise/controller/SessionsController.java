package exercise.controller;

import exercise.dto.LoginPage;
import exercise.dto.MainPage;
import exercise.model.User;
import exercise.repository.UsersRepository;
import exercise.util.NamedRoutes;
import exercise.util.Security;
import io.javalin.http.Context;

import java.util.Collections;

public class SessionsController {

    // BEGIN
    public static void index(Context ctx) {
        MainPage page = new MainPage(ctx.sessionAttribute("currentUser"));
        ctx.render("index.jte", Collections.singletonMap("page", page));
    }

    public static void build(Context ctx) {
        LoginPage page = new LoginPage("", "");
        ctx.render("build.jte", Collections.singletonMap("page", page));
    }

    public static void create(Context ctx) {
        var getNickname = ctx.formParam("name");
        var getPassword = ctx.formParam("password");
        var getEncryptPassword = Security.encrypt(getPassword);
        User user = UsersRepository.findByName(getNickname);
        if (user == null || !user.getPassword().equals(getEncryptPassword)) {
            LoginPage page = new LoginPage(getNickname, "Wrong username or password");
            ctx.render("build.jte", Collections.singletonMap("page", page));
        } else {
            ctx.sessionAttribute("currentUser", getNickname);
            ctx.redirect(NamedRoutes.rootPath());
        }
    }

    public static void destroy(Context ctx) {
        ctx.sessionAttribute("currentUser", null);
        ctx.redirect(NamedRoutes.rootPath());
    }
    // END
}
