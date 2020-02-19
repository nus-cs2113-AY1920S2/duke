import java.util.Scanner;

public class Parser {
    private Scanner reader;

    public Parser () {
        this.reader = new Scanner(System.in);
    }

    public String[] readUserInput() {
        String input = reader.nextLine();
        return inputProcessing(input);
    }

    /**
     * Split first word from the rest of String.
     * @param input: one-line string
     * @return array of 2 String, [0]: first word, [1]: subsequent words.
     */
    private String[] inputProcessing(String input) {
        String[] output = new String[2];

        input = input.trim();   // strip leading & trailing spaces
        int startIndexOfDescription = input.indexOf(' ');

        if (startIndexOfDescription == -1) {
            // only has 1 word
            output[0] = input.toLowerCase();
        } else {
            // has 2 words
            String cmdType = input.substring(0, startIndexOfDescription).toLowerCase();
            String cmdDescription = input.substring(startIndexOfDescription + 1);
            output[0] = cmdType;
            output[1] = cmdDescription;
        }

        return output;
    }
}