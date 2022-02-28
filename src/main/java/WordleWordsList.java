import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordleWordsList {
    private List<String> words;
    private HashMap<Character, Double> frequencies;

    public List<String> getWords() {
        return this.words;
    }

    public Double getFrequency(Character character) {
        return this.frequencies.get(character);
    }

    public Double calculateTotalFrequency(String word) {
        Double frequency = 0.0;
        for (Character character : word.toCharArray()) {
            frequency += getFrequency(character);
        }

        return frequency;
    }

    public WordleWordsList(String filepath) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(filepath));
        String allWordEntries;
        try {
            allWordEntries = reader.readLine();
        } finally {
            reader.close();
        }

        Matcher wordFinder = Pattern.compile("[A-Z]{5}").matcher(allWordEntries);
        this.words = new ArrayList<>();

        while (wordFinder.find()) {
            this.words.add(wordFinder.group().toLowerCase());
        }

        HashMap<Character, Integer> charCounts = new HashMap<>();
        Integer totalEntries = 0;

        for (String word : this.words) {
            for (Character character : word.toCharArray()) {
                if (charCounts.containsKey(character)) {
                    charCounts.put(character, charCounts.get(character) + 1);
                } else {
                    charCounts.put(character, 1);
                }
            }
            totalEntries++;
        }

        this.frequencies = new HashMap<>();

        for (Character character : charCounts.keySet()) {
            this.frequencies.put(character, (double) charCounts.get(character) / totalEntries);
        }
    }
}
