package exercise;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

class AppTest {

    @Test
    void testTake() {
        // BEGIN
        List<Integer> numbers1 = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        List<Integer> expected1 = new ArrayList<>(Arrays.asList(1, 2));
        assertThat(App.take(numbers1, 2)).isEqualTo(expected1);
        List<Integer> numbers2 = new ArrayList<>(Arrays.asList(7, 3, 10));
        List<Integer> expected2 = new ArrayList<>(Arrays.asList(7, 3, 10));
        assertThat(App.take(numbers2, 8)).isEqualTo(expected2);
        List<Integer> numbers3 = new ArrayList<>();
        List<Integer> expected3 = new ArrayList<>();
        assertThat(App.take(numbers3, 8)).isEqualTo(expected3);
        // END
    }
}
