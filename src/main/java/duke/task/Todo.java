package duke.task;

import java.time.format.DateTimeParseException;

/**
 * Type of Task that is not tied to a date.
 */
public class Todo extends Task {
    /** Icon used to represent a Todo */
    public static final char TODO_ICON = 'T';

    public Todo(String description) {
        super(description);
    }

    @Override
    public String encodeTask() {
        return String.format("%s|%s|%s", TODO_ICON, isDone, description);
    }

    /**
     * Returns the object representation of an encoded Todo.
     * @param encodedTask a string returned by method Task.encodeTask()
     * @return a Todo object whose information was stored in encodedTask
     * @throws IndexOutOfBoundsException if encodedTask is not a string returned by Task.encodeTask()
     * @throws DateTimeParseException if date or time fields in encodedTask are of incorrect format
     */
    public static Todo decodeTask(String encodedTask) throws DateTimeParseException, IndexOutOfBoundsException {
        String[] tokens = encodedTask.split("\\" + DELIMITER);
        boolean isDone = Boolean.parseBoolean(tokens[1]);
        String description = tokens[2];
        Todo todo = new Todo(description);
        if (isDone) {
            todo.markAsDone();
        }
        return todo;
    }

    @Override
    public String toString() {
        return "[" + TODO_ICON + "]" + super.toString();
    }
}
