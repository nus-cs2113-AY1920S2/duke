import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Parser {
    public static final int LIMIT = 2;

    public static List<String> parseCommand(String description) {
        return new ArrayList<>(Arrays.asList(description.split(" ", LIMIT)));
    }

    public static List<String> parseDeadlineDate(String descriptionAndDate) {
        List<String> separated = new ArrayList<>(Arrays.asList(descriptionAndDate.split(" /by ", LIMIT)));
        Collections.reverse(separated);
        return separated;
    }

    public static List<String> parseEventAt(String descriptionAndDate) {
        List<String> separated = new ArrayList<>(Arrays.asList(descriptionAndDate.split(" /at ", LIMIT)));
        Collections.reverse(separated);
        return separated;
    }

    public static List<String> parseFile(String sentence){
        return new ArrayList<>(Arrays.asList(sentence.split(" [|] ")));
    }

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
        case "find" :
            return new FindCommand();
        default :
            DukeExceptionHandler.unknownCommand();
            return null;
        }
    }
}

