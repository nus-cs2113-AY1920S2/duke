/**
 * Makes sense of the user's input and allocate them to the different commands.
 */

public class Parser {

    /**
     * Breaks down the user's input to allocate to the different commands.
     * @param userInput the command that the user typed in.
     * @return command.
     * @throws IndexOutOfBoundsException when one of the method throws IndexOutOfBoundsException.
     * @throws IllegalArgumentException when one of the method throws IllegalArgumentException.
     */

    public static Command parseInput(String userInput) throws IndexOutOfBoundsException, IllegalArgumentException {
        String[] separatedUserInput = userInput.trim().split(" ");
        String firstWordOfUserInput = separatedUserInput[0];
        Command command = new Command("wait ah");
        switch (firstWordOfUserInput) {
            case "todo":
                command = new TodoCommand(userInput);
                break;
            case "event":
                command = new EventCommand(userInput);
                break;
            case "deadline":
                command = new DeadlineCommand(userInput);
                break;
            case "done":
                command = new DoneCommand(userInput);
                break;
            case "delete":
                command = new DeleteCommand(userInput);
                break;
            case "list":
                command = new ListCommand(userInput);
                break;
            default:
                command = new NoSuchCommand(userInput);
                break;
        }
        return command;
    }
}
