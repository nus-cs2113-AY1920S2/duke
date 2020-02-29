package duke.commands;

import duke.DukeException;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a command to find a task in the list of tasks.
 */
public class FindCommand extends Command {

    private static final String LINE = "____________________________________________________________ \n";

    /**
     * Constructor to create a new find command.
     *
     * @param parameters key word to find
     */
    public FindCommand(String parameters) throws DukeException {
        super(parameters.trim());

        if (parameters.isBlank()) {
            throw new DukeException();
        }
    }

    /**
     * Finds all tasks that contain the word inputted by the
     * user and prints to the screen. Input is case insensitive.
     *
     * @param tasks the list of tasks
     */
    @Override
    public void execute(TaskList tasks) {
        boolean isFound = false;
        System.out.println(LINE);
        for (int i = 0; i < tasks.getSize(); i++) {
            if (tasks.get(i).getDescription().toLowerCase().contains(parameters.toLowerCase())) {
                System.out.println("  " + tasks.get(i));
                isFound = true;
            }
        }
        if (!isFound) {
            // parameter "keyword" passed to tell Ui to
            // print that the keyword is not found
            Ui.printNotFound("keyword");
        } else {
            System.out.println(LINE);
        }
    }
}
