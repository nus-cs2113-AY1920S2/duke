import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Parser {
    public static final int LIMIT = 2;

    /**
     * Returns the command that User has input.
     * Index 0 will be the Command that User has input, while index 1 will be everything else.
     *
     * @param description The user input that was scanned as an entire String initially. e.g., "event meeting /at NUS"
     * @return An ArrayList that was spilt by " ", limited by 2.
     */
    public static List<String> parseCommand(String description) {
        return new ArrayList<>(Arrays.asList(description.split(" ", LIMIT)));
    }

    /**
     * Returns the description and date that User has input.
     * The returned List will be reversed, so description will be at index 1 and the date at index 0.
     *
     * @param descriptionAndDate The second half of what User has input after parseCommand. "submit iP /by Monday"
     * @return An ArrayList that was split by " ", reversed, and limited by 2.
     */
    public static List<String> parseDeadlineDate(String descriptionAndDate) {
        List<String> separated = new ArrayList<>(Arrays.asList(descriptionAndDate.split(" /by ", LIMIT)));
        Collections.reverse(separated);
        return separated;
    }

    /**
     * Returns the description and place that User has input.
     * The returned List will be reversed, so description will be at index 1 and the place at index 0.
     *
     * @param descriptionAndPlace The second half of what User has input after parseCommand. "team meeting /at NUS"
     * @return An ArrayList that was split by " ", reversed, and limited by 2.
     */
    public static List<String> parseEventAt(String descriptionAndPlace) {
        List<String> separated = new ArrayList<>(Arrays.asList(descriptionAndPlace.split(" /at ", LIMIT)));
        Collections.reverse(separated);
        return separated;
    }

    /**
     * Returns an ArrayList that was split by " | ".
     *
     * @param sentence One line from the the save file.
     * @return An ArrayList separated by " | "
     */
    public static List<String> parseFile(String sentence){
        return new ArrayList<>(Arrays.asList(sentence.split(" [|] ")));
    }

    /**
     * Returns a specific command object
     * Throws an UnknownCommandException if the User supplied an UnknownCommand
     *
     * @param command The specific command in String
     * @return the specific command object
     * @throws UnknownCommandException If command is Unknown e.g., commands not under this list "edit"
     */
    public static Command whatCommand(String command) throws UnknownCommandException {
        switch(command) {
        case "todo" :
            return new ToDoCommand();
        case "event" :
            return new EventCommand();
        case "deadline" :
            return new DeadlineCommand();
        case "delete" :
            return new DeleteCommand();
        case "list" :
            return new ListCommand();
        case "done" :
            return new DoneCommand();
        case "bye":
            return new ByeCommand();
        default :
            DukeExceptionHandler.unknownCommand();
            return null;
        }
    }
}

