package commands;

import exceptions.InvalidCommandException;
import exceptions.MissingDescriptionException;
import task.*;
import ui.UI;

/**
 * Represents the command to add any type of task to the task list.
 */
public class AddCommand extends Command {
    protected String command;

    public AddCommand (String description, TaskList tasks, String command) {
        super(description, tasks);
        this.command = command;
    }

    /**
     * Identifies and adds the correct Task object to the task list.
     * @throws MissingDescriptionException If the task description is incomplete.
     * @throws InvalidCommandException If the command word is invalid.
     */
    @Override
    public void execute() throws MissingDescriptionException, InvalidCommandException {
        Task t;
        // Parse input to obtain text and timeDescriptor
        String timeDescriptor = "";
        String text = "";
        try {
            int slashPos = description.indexOf('/');
            if (slashPos != -1) {
                timeDescriptor = description.substring(slashPos + 4);
                text = description.substring(0, slashPos);
            }
        } catch (IndexOutOfBoundsException e) {
            throw new MissingDescriptionException();
        }

        if (command.equals("todo")) {
            t = new Todo(description);
            if (description.equals("")) {
                throw new MissingDescriptionException();
            }
        } else if (command.equals("deadline")) {
            t = new Deadline(text, timeDescriptor);
            if (text.equals("") || timeDescriptor.equals("")) {
                throw new MissingDescriptionException();
            }
        } else if (command.equals("event")) {
            t = new Event(text, timeDescriptor);
            if (text.equals("") || timeDescriptor.equals("")) {
                throw new MissingDescriptionException();
            }
        } else {
            throw new InvalidCommandException();
        }

        tasks.addTask(t);
        UI.printAddedTaskMessage(t, tasks.getListSize());
    }
}