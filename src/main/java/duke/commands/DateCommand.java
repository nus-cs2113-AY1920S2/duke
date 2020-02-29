package duke.commands;

import duke.DukeException;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Represents a command to list all events and deadlines occurring
 * on a date specified by the user.
 */
public class DateCommand extends Command {

    /**
     * Constructor to create a new Date command.
     *
     * @param parameters the date the user wishes to search
     * @throws DukeException throws this exception if no date is supplied
     */
    public DateCommand(String parameters) throws DukeException {
        super(parameters.trim());

        if (parameters.isBlank()) {
            throw new DukeException();
        }
    }

    /**
     * Search for all event and deadline tasks with the date inputted
     * by the user.
     *
     * @param tasks the list of tasks
     */
    @Override
    public void execute(TaskList tasks) {
        try {
            boolean dateIsFound = false;
            LocalDate date = LocalDate.parse(parameters);
            for (int i = 0; i < tasks.getSize(); i++) {
                Task task = tasks.get(i);
                if (task instanceof Deadline) {
                    Deadline deadline = (Deadline) task;
                    if (deadline.getDate().equals(date)) {
                        System.out.println(deadline);
                        dateIsFound = true;
                    }
                } else if (task instanceof Event) {
                    Event event = (Event) task;
                    if (event.getDate().equals(date)) {
                        System.out.println(event);
                        dateIsFound = true;
                    }
                }
            }
            if (!dateIsFound) {
                // parameter "date" passed to tell Ui to
                // print that date is not found
                Ui.printNotFound("date");
            }
        } catch (DateTimeParseException e) {
            Ui.printInvalidDate();
        }
    }
}
