package Duke.Parser;

import Duke.Exception.DukeException;
import Duke.Library.ErrorMessage;
import Duke.Task.Deadline;
import Duke.Task.Event;
import Duke.Task.Task;
import Duke.Task.Todo;

import static Duke.Library.ErrorMessage.CORRUPTED_TASK;

/**
 * Parser for Storage related operations.
 */
public class ParserStorage {

    public static Task createTaskFromStorage(String line) throws DukeException {
        String[] taskParts = line.split("\\|");
        try {
            String type = taskParts[0].strip();
            String status = taskParts[1].strip();
            String description = taskParts[2].strip();
            String completion = taskParts[3].strip();
            Task task;
            if (type.equals("D")) {
                    task = new Deadline(description, completion);
            } else if (type.equals("E")) {
                    task = new Event(description, completion);
            } else {
                task = new Todo(description);
            }
            task.setDone(status.equals("true"));
            return task;
        } catch (Exception e) {
            throw new DukeException(CORRUPTED_TASK);
        }
    }

    public static String toStorageString(Task task) throws DukeException {
        if (task instanceof Deadline) {
            return "D | " + task.isDone() + " | " + task.getDescription() + " | " + ((Deadline) task).getDeadline();
        } else if (task instanceof Todo) {
            return  "T | " + task.isDone() + " | " + task.getDescription();
        } else if (task instanceof Event) {
            return "E | " + task.isDone() + " | " + task.getDescription() + " | " + ((Event) task).getEvent();
        }
        throw new DukeException(ErrorMessage.CORRUPTED_TASK);
    }

}
