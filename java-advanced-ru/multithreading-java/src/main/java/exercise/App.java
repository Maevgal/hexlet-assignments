package exercise;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import java.util.logging.Level;

class App {
    private static final Logger LOGGER = Logger.getLogger("AppLogger");

    // BEGIN
    public static Map<String, Integer> getMinMax(int[] mas) {
        Map<String, Integer> result = new HashMap<>();
        MaxThread maxThread = new MaxThread(mas);
        MinThread minThread = new MinThread(mas);
        maxThread.start();
        minThread.start();
        try {
            maxThread.join();
            LOGGER.log(Level.INFO, "Поток " + minThread.getName() + " остановился");
            minThread.join();
            LOGGER.log(Level.INFO, "Поток " + maxThread.getName() + " остановился");
        } catch (InterruptedException ex) {
            Logger.getLogger("Поток был прерван");
        }

        result.put("max", maxThread.getMaxNumber());
        result.put("min", minThread.getMinNumber());
        return result;
    }
    // END
}
