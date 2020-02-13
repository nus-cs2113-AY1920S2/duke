package duke.tasks;

import duke.Command;
import duke.tasks.exceptions.BadDeadlineFormatException;

import java.util.Arrays;

public class Deadline extends Task {
    protected String dueDateTime;

    public Deadline(Command command) throws BadDeadlineFormatException {
        String[] tokens = command.getTokens();
        if (!Arrays.asList(tokens).contains("/by")) {
            throw new BadDeadlineFormatException("Input does not contain \" /by \"");
        }

        int byIndex = -1;
        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i].equals("/by")) {
                byIndex = i;
                break;
            }
        }

        if (byIndex == tokens.length - 1) {
            throw new BadDeadlineFormatException("Input does not contain a due date");
        } else if (byIndex == 1) {
            throw new BadDeadlineFormatException("Input does not contain a description");
        }

        this.description = String.join(" ", Arrays.copyOfRange(tokens, 1, byIndex));
        this.dueDateTime = String.join(" ", Arrays.copyOfRange(tokens, byIndex + 1, tokens.length));
    }

    @Override
    public String toString() {
        return "[D][" + getStatusIcon() + "] " + description + " (by: " + dueDateTime + ")";
    }
}
