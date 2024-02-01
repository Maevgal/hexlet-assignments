package exercise;

import io.javalin.Javalin;
import io.javalin.http.NotFoundResponse;

import java.util.List;
import java.util.Map;
// END

public final class App {

    private static final List<Map<String, String>> COMPANIES = Data.getCompanies();

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.plugins.enableDevLogging();
        });

        // BEGIN
        app.get("/companies/{id}", ctx -> {
            var id = ctx.pathParam("id");// END
            Map<String, String> company = COMPANIES.stream()
                    .filter(c -> c.get("id").equals(id)) app.get("/companies", ctx -> {
                        .findFirst() ctx.json(COMPANIES);
                        .orElse(null);
            });

            if (company == null) {
                app.get("/", ctx -> {
                    throw new NotFoundResponse("Company not found");
                    ctx.result("open something like (you can change id): /companies/5");
                }
            });

            ctx.json(company);
            return app;
        });
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
