package exercise;

import java.util.Arrays;

// BEGIN
public class MinThread extends Thread {

    private int[] mas;

    private int minNumber;

    public int getMinNumber() {
        return minNumber;
    }

    public MinThread(int[] numbers) {
        this.mas = numbers;
    }

    @Override
    public void run() {
        this.minNumber = Arrays.stream(mas).min().getAsInt();
    }
}
// END
