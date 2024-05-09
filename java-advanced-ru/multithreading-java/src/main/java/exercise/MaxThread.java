package exercise;

import java.util.Arrays;

// BEGIN
public class MaxThread extends Thread {
    private int[] mas;
    private int maxNumber;

    public MaxThread(int[] mas) {
        this.mas = mas;
    }

    public int getMaxNumber() {
        return maxNumber;
    }

    @Override
    public void run() {
        this.maxNumber = Arrays.stream(mas).max().getAsInt();
    }
}
// END
