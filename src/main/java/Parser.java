import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Parser {
    public static List<String> parseCommand(String description) {
        List<String> commands = new ArrayList<>(Arrays.asList(description.split(" ", Duke.LIMIT)));
        return commands;
    }

    public static List<String> parseDeadlineDate(String descriptionAndDate){
        List<String> separated = new ArrayList<>(Arrays.asList(descriptionAndDate.split(" /by ", Duke.LIMIT)));
        Collections.reverse(separated);
        return separated;
    }

    public static List<String> parseEventAt(String descriptionAndDate){
        List<String> separated = new ArrayList<>(Arrays.asList(descriptionAndDate.split(" /at ", Duke.LIMIT)));
        Collections.reverse(separated);
        return separated;
    }

}
