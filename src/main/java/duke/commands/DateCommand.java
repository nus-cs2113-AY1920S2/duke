package duke.commands;

import duke.DukeException;
import duke.TaskList;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class DateCommand extends Command {
    public DateCommand(String parameters) throws DukeException {
        super(parameters.trim());

        if (parameters.isBlank()) {
            throw new DukeException();
        }
    }

    @Override
    public void Execute(TaskList tasks) {
        try {
            LocalDate date = LocalDate.parse(parameters);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date!");
        }
        LocalDate date = LocalDate.parse(parameters);
        for (int i = 0; i < tasks.getSize(); i++) {
            Task task = tasks.get(i);
            if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                if (deadline.getDate().equals(date)) {
                    System.out.println(deadline);
                }
            } else if (task instanceof Event) {
                Event event = (Event) task;
                if (event.getDate().equals(date)) {
                    System.out.println(event);
                }
            }
        }
    }
}
