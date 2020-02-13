package duke.tasks;

import duke.Command;
import duke.tasks.exceptions.BadToDoFormatException;

import java.util.Arrays;

public class ToDo extends Task {
    public ToDo(boolean isDone, String description) {
        super(description);
        this.isDone = isDone;
    }

    public ToDo(Command command) throws BadToDoFormatException {
        String[] tokens = command.getTokens();
        if (tokens.length < 2) {
            throw new BadToDoFormatException("Input does not contain a description");
        }

        this.description = String.join(" ", Arrays.copyOfRange(tokens, 1, tokens.length));
    }

    @Override
    public String toString() {
        return "[T][" + getStatusIcon() + "] " + description;
    }

    public String toFormattedString() {
        String done = isDone ? "y" : "n";
        return "T," + done + "," + description;
    }
}
