package duke.parser;

public class Parser {
    /**
     * Used for initial separation of command word and args.
     *
     * @param input full user input string
     * @return the parsed input
     */
    public static String[] parser(String input) {
        String[] parseInput = input.split(" ", 2);
        return parseInput;
    }

    /**
     * Used for retrieval of the command word.
     *
     * @param parseInput parsed user input string
     * @return the command based on the user input
     */
    public static String getCommand(String[] parseInput) {
        String command = parseInput[0];
        return command;
    }
}
