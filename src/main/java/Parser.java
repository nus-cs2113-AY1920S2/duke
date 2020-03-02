public class Parser {
    public static Command parseInput(String userInput) throws IndexOutOfBoundsException, IllegalArgumentException {
        String[] separatedUserInput = userInput.trim().split(" ");
        String firstWordOfUserInput = separatedUserInput[0];
        Command command;
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
            case "find":
                command = new FindCommand(userInput);
            default:
                command = new NoSuchCommand(userInput);
                break;
        }
        return command;
    }
}
