/**
 * Extends from Task. Stores the information needed for ToDo.
 */

public class ToDo extends Task {

    /**
     * Constructor for ToDo.
     * @param taskName description of the toDo.
     */

    public ToDo(String taskName) {
        super(taskName);
    }

    @Override
    public String toString() {
        return String.format("[T][%c] %s", isDone() ? '✓' : '✗', taskName);
    }
}
