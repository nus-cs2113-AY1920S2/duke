package duke.parser;

import duke.commands.*;
import static duke.constants.ConstantCommands.*;

import duke.constants.ConstantCommands;
import duke.exceptions.DukeExceptionHandler;
import duke.exceptions.UnknownCommandException;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;



public class Parser {
    public static final int LIMIT = 2;
    public static final String REGEX_SPACE = " ";
    public static final String REGEX_BY = " /by ";
    public static final String REGEX_AT = " /at ";
    public static final String REGEX_VERTICAL = " [|] ";


    /**
     * Returns the command that User has input.
     * Index 0 will be the duke.commands.Command that User has input, while index 1 will be everything else.
     *
     * @param description The user input that was scanned as an entire String initially. e.g., "event meeting /at NUS"
     * @return An ArrayList that was spilt by " ", limited by 2.
     */
    public static List<String> parseCommand(String description) {
        return new ArrayList<>(Arrays.asList(description.split(REGEX_SPACE, LIMIT)));
    }

    /**
     * Returns the description and date that User has input.
     * The returned List will be reversed, so description will be at index 1 and the date at index 0.
     *
     * @param descriptionAndDate The second half of what User has input after parseCommand. "submit iP /by Monday"
     * @return An ArrayList that was split by " ", reversed, and limited by 2.
     */
    public static List<String> parseDeadlineDate(String descriptionAndDate) {
        List<String> separated = new ArrayList<>(Arrays.asList(descriptionAndDate.split(REGEX_BY, LIMIT)));
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
        List<String> separated = new ArrayList<>(Arrays.asList(descriptionAndPlace.split(REGEX_AT, LIMIT)));
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
        return new ArrayList<>(Arrays.asList(sentence.split(REGEX_VERTICAL)));
    }

    /**
     * Returns a specific command object
     * Throws an duke.exceptions.UnknownCommandException if the User supplied an UnknownCommand
     *
     * @param command The specific command in String
     * @return the specific command object
     * @throws UnknownCommandException If command is Unknown e.g., commands not under this list "edit"
     */
    public static Command whatCommand(String command) throws UnknownCommandException {
        switch(command) {
        case TODO :
            return new ToDoCommand();
        case EVENT :
            return new EventCommand();
        case DEADLINE :
            return new DeadlineCommand();
        case DELETE :
            return new DeleteCommand();
        case LIST :
            return new ListCommand();
        case DONE :
            return new DoneCommand();
        case BYE:
            return new ByeCommand();
        case FIND :
            return new FindCommand();
        case HELP :
            return new HelpCommand();
        default :
            DukeExceptionHandler.unknownCommand();
            return null;
        }
    }

    /**
     * Gets the constant fields declared in the constant class and returns them as an ArrayList
     *
     * @return an ArrayList of commands
     */
    public static List<String> getCommandList() {
        Field[] constants = ConstantCommands.class.getDeclaredFields();
        ConstantCommands constant = new ConstantCommands();
        List<String> commandList = new ArrayList<>();

        try {
            for (Field field : constants) {
                String command = (String) field.get(constant);
                commandList.add(command);
            }
        } catch (Exception ignored) {
        }

        return commandList;
    }
}

