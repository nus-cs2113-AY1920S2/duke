package duke;

import duke.commands.*;
import duke.task.TaskList;
import duke.ui.UI;

/**
 * Interprets the user input.
 */
public class Parser {

    /**
     * Returns a Command object based on the command parsed from userInput.
     *
     * @param userInput Input obtained from the user.
     * @param tasks TaskList object containing all the tasks added to the list.
     * @return an object that is a child class of Command.
     */
    public static Command parse (String userInput, TaskList tasks) {
        if (userInput.equals("bye")) {
            return new ExitCommand("", tasks);
        }

        String [] parsedInput = userInput.split(" ", 2);
        String command = parsedInput[0];
        String description = "";
        if (parsedInput.length > 1) {
            description = parsedInput[1];
        }

        try {
            switch(command) {
            case "list":
                return new ListCommand(description, tasks);
            case "done": // Mark a task as done
                return new DoneCommand(description, tasks);
            case "delete":
                return new DeleteCommand(description, tasks);
            case "find":
                return new FindCommand(description, tasks);
            default: // Add a task
                return new AddCommand(description, tasks, command);
            }
        } catch (IndexOutOfBoundsException e) {
            UI.br();
            System.out.println("\t I'm sorry, but I don't know what that means :(");
            UI.br();
        }

        return new Command(description, tasks);
    }
}
