import java.util.Scanner;

public class WordleSolver {
    Scanner input;
    WordleFilter filter;

    public WordleSolver(Scanner input, WordleFilter filter) {
        this.input = input;
        this.filter = filter;
    }

    public String makeGreenRule(Character character, Integer position) {
        String first = "\\w";
        String second = "\\w";
        String third = "\\w";
        String fourth = "\\w";
        String fifth = "\\w";

        if (position == 0) {
            first = character.toString();
        } else if (position == 1) {
            second = character.toString();
        } else if (position == 2) {
            third = character.toString();
        } else if (position == 3) {
            fourth = character.toString();
        } else if (position == 4) {
            fifth = character.toString();
        }

        return first + second + third + fourth + fifth;
    }

    public String makeYellowRule(Character character, Integer position) {
        String first = "\\w";
        String second = "\\w";
        String third = "\\w";
        String fourth = "\\w";
        String fifth = "\\w";

        if (position == 0) {
            first = "[^" + character.toString() + "]";
        } else if (position == 1) {
            second = "[^" + character.toString() + "]";
        } else if (position == 2) {
            third = "[^" + character.toString() + "]";
        } else if (position == 3) {
            fourth = "[^" + character.toString() + "]";
        } else if (position == 4) {
            fifth = "[^" + character.toString() + "]";
        }

        return first + second + third + fourth + fifth;
    }



    public void solve() {
        String guess = "later";
        String response = "";

        while (!response.equals("ggggg")) {
            System.out.println("guess: " + guess);
            System.out.print("response: ");
            response = input.nextLine();

            for (int i = 0; i < 5; i++) {
                // if char is green, make new rule, add to included characters
                if (response.charAt(i) == 'g') {
                    filter.addRule(makeGreenRule(guess.charAt(i), i));
                    filter.addIncludedCharacter(guess.charAt(i));
                } else if (response.charAt(i) == 'y') {
                    // if char is yellow, make new rule, add to included characters
                    filter.addRule(makeYellowRule(guess.charAt(i), i));
                    filter.addIncludedCharacter(guess.charAt(i));
                } else if (response.charAt(i) == 'b') {
                    // if char is black and not included, add to excluded characters
                    filter.addExcludedCharacter(guess.charAt(i));
                }
            }

            guess = filter.getGuess();
        }
    }
}
