import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class WordleFilter {
    private List<String> words;
    private List<String> rules;
    private List<Character> excludedCharacters;
    private List<Character> includedCharacters;

    public WordleFilter(List<String> words) {
        this.words = words;
        this.rules = new ArrayList<>();
        this.excludedCharacters = new ArrayList<>();
        this.includedCharacters = new ArrayList<>();
    }

    public List<String> getWords() {
        return words;
    }

    public void addRule(String rule) {
        this.rules.add(rule);
        List<String> temp = new ArrayList<>();

        for (String word : words) {
            if (Pattern.matches(rule, word)) {
                temp.add(word);
            }
        }

        this.words = temp;
    }

    public void addExcludedCharacter(Character character) {
        this.excludedCharacters.add(character);
        List<String> temp = new ArrayList<>();

        for (String word : words) {
            if (!word.contains(character.toString())) {
                temp.add(word);
            }
        }

        this.words = temp;
    }

    public void addIncludedCharacter(Character character) {
        this.includedCharacters.add(character);
        List<String> temp = new ArrayList<>();

        for (String word : words) {
            if (word.contains(character.toString())) {
                temp.add(word);
            }
        }

        this.words = temp;
    }

    public List<Character> getExcludedCharacters() {
        return excludedCharacters;
    }

    public List<Character> getIncludedCharacters() {
        return includedCharacters;
    }

    public String getGuess() {
        return this.words.get(0);
    }
}