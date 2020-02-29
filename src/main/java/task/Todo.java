package task;

public class Todo extends Task{

    /**
     * Constructor for Todo class
     *
     * @param description the description of the todo task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the status and the description as a string prepended with "[T]"
     *
     * @return String of status and description of task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * The description of the task to be saved
     *
     * @return Description of task and its status with T to indicate Todo
     */
    @Override
    public String saveTask() {
        return "T|" + super.isDoneNum() + "|" + super.saveTask();
    }
}
