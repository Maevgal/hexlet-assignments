package exercise;

// BEGIN
public class ReversedSequence implements CharSequence {

    @Override
    public int length() {
        return str.length();
    }

    @Override
    public char charAt(int index) {
        byte[] b = str.getBytes();
        char c = (char) (b[index] & 0xff);
        return c;
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        return str.subSequence(start, end);
    }

    @Override
    public String toString() {
        str = new StringBuilder(str).reverse().toString();
        return str;
    }

    String str;

    public ReversedSequence(String str) {
        this.str = str;
    }

}
// END
