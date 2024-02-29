package exercise.controller;

import exercise.daytime.Daytime;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// BEGIN
@RestController
@RequestMapping
@RequiredArgsConstructor
public class WelcomeController {
    private final Daytime dayTime;

    @GetMapping("/welcome")
    public String welcome() {
        return "It is %s now! Welcome to Spring!".formatted(dayTime.getName());
    }
}
// END
