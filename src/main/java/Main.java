import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        WordleWordsList wordsList = new WordleWordsList("wordleWords.txt");
        WordleFilter filter = new WordleFilter(wordsList.getWords());
        Scanner input = new Scanner(System.in);
        WordleSolver solver = new WordleSolver(input, filter);

        solver.solve();

        input.close();
    }
}