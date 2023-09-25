package exercise;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

// BEGIN
public class App {
    public static boolean scrabble(String symbols, String word) {
        List<String> symbolList = new ArrayList<>(Arrays.asList(symbols.toLowerCase().split("")));
        List<String> wordSymbols = new ArrayList<>(Arrays.asList(word.toLowerCase().split("")));
        if (symbolList.size() < wordSymbols.size()) {
            return false;
        }
        for (int i = 0; i < wordSymbols.size(); i++) {
            if (symbolList.contains(wordSymbols.get(i))) {
                symbolList.remove(wordSymbols.get(i));
            } else {
                return false;
            }
        }
        return true;
    }

}
//END
