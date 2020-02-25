package duke.task;

import duke.exception.DukeNullDescriptionException;

import static duke.util.Constants.TODO_ICON;

/**
 * This is the Todo class inherited from the Task class.
 *
 * @author A11riseforme
 */
public class Todo extends Task {
    protected String typeIcon;

    /**
     * Default constructor of Todo class.
     *
     * @param description the description of the todo task.
     * @throws DukeNullDescriptionException exception is thrown if description is empty.
     */
    public Todo(String description) throws DukeNullDescriptionException {
        super(description);
        this.typeIcon = TODO_ICON;
    }

    /**
     * Get the Icon representation of the todo task.
     *
     * @return a icon string `[T]` which represents the todo task.
     */
    public String getIcon() {
        return typeIcon;
    }

    /**
     * Cast the task to String type when called by System.out.println() or other similar methods.
     *
     * @return the String printout of the task.
     */
    @Override
    public String toString() {
        return typeIcon + super.toString();
    }
}
