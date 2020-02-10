package duke.tasks;

import duke.tasks.exceptions.BadToDoFormatException;

public class ToDo extends Task {
    public ToDo(String userInput) throws BadToDoFormatException {
        if (!userInput.contains(" ")) {
            throw new BadToDoFormatException("input does not contain a space");
        } else if (userInput.indexOf(" ") + 1 > userInput.length() - 1) {
            throw new BadToDoFormatException("input does not contain a description");
        }

        this.description = userInput.substring(userInput.indexOf(" ") + 1);
    }

    @Override
    public String toString() {
        return "[T][" + getStatusIcon() + "] " + description;
    }
}
