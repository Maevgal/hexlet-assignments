package exercise.dto.users;

import exercise.model.User;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

// BEGIN
@AllArgsConstructor
@Getter
public final class UserPage {
    private User user;
}
// END
