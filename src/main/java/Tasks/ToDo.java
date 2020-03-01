package Tasks;

/**
 * A subclass of <code>Task</code> class.
 *
 * @see Task
 */
public class ToDo extends Task{
    /**
     * Class constructor of the <code>ToDo</code> class.
     * @param description Description of the <code>ToDo</code> class.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a <code>String</code> of the <code>ToDo</code> task to be printed.
     * @return <code>String</code> of symbol, done status and description of <code>ToDo</code> task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a <code>String</code> of the <code>ToDo</code> task to be written to the text file.
     * @return <code>String</code> of symbol, done status and description of <code>ToDo</code> task.
     */
    @Override
    public String toFileString() {
        return "T," + super.toFileString();
    }
}
