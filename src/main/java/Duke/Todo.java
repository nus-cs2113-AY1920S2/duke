package Duke;

/** Manages todo task operations */
public class Todo extends Task {

    /**
     * Constructor for Todo.
     *
     * @param description The description of Todo.
     */
    public Todo (String description) {
        super(description);
    }

    /**
     * Constructor for Todo.
     * @param description The description of Todo.
     * @param isDone isDone is true if task is done, else false.
     */
    public Todo (String description,  Boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Format String differently from String shown on terminal
     * to store in duke.txt file.
     * @return Formatted String.
     */
    @Override
    public String toText() {
        return "T | " + (this.isDone? "1" : "0") + " | " + this.description;
    }

}
