package duke;

import duke.command.*;
import java.util.Scanner;

/**
 * Parses user input.
 */
public class Parser {
    private static final String LIST = "list";
    private static final String TODO = "todo";
    private static final String DEADLINE = "deadline";
    private static final String EVENT = "event";
    private static final String DONE = "done";
    private static final String DELETE = "delete";
    private static final String FIND = "find";
    private static final String BYE = "bye";
    public static final String BORDER = "____________________________________________________________\n";

    Scanner input;

    public Parser() {
        input = new Scanner(System.in);
    }

    /**
     * Parses user input into command for execution.
     * @return the command based on the user input
     */
    public Command getCommand() {
        Command command = null;
        String line;
        line = input.nextLine();
        String[] sentence = line.split(" ",2);
        String taskType = sentence[0].toLowerCase();

        try {
            switch (taskType) {
            case (LIST):
                command = new ListCommand("");
                break;
            case (TODO):
                command =  new AddCommand(sentence[1], sentence[0]);
                break;
            case (DEADLINE):
                command =  new AddCommand(sentence[1], sentence[0]);
                break;
            case (EVENT):
                command =  new AddCommand(sentence[1], sentence[0]);
                break;
            case (DONE):
                command = new DoneCommand(sentence[1]);
                break;
            case (DELETE):
                command = new DeleteCommand(sentence[1]);
                break;
            case (FIND):
                command = new FindCommand(sentence[1]);
                break;
            case (BYE):
                command = new ExitCommand("");
                break;
            default:
                System.out.println(BORDER + "☹ OH NO!!! I'm sorry, but I don't know what that means! :o(\n" + BORDER);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(BORDER + "☹ OH NO!!! The description of the command '" + taskType + "' cannot be empty! :o(\n" + BORDER);
            command = null;
        }
        return command;
    }

}
