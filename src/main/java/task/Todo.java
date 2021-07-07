package task;

/**
 * A sub-class of the Task class for todos.
 *
 * @see Task
 */

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of todo task to be output to
     * the bot's UI.
     *
     * @return string of symbol, done status, description of
     * todo task
     * */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a string representation of todo task to be written to
     * the storage file.
     *
     * @return string of symbol, done status, description of
     * todo task.
     * */
    @Override
    public String toFileString() {
        return "T," + super.toFileString();
    }
}