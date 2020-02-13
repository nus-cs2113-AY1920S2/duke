package duke.tasks;

import duke.Command;
import duke.tasks.exceptions.BadEventFormatException;

import java.util.Arrays;

public class Event extends Task {
    protected String startEndDateTime;

    public Event(Command command) throws BadEventFormatException {
        String[] tokens = command.getTokens();
        if (!Arrays.asList(tokens).contains("/at")) {
            throw new BadEventFormatException("Input does not contain \" /at \"");
        }

        int atIndex = -1;
        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i].equals("/at")) {
                atIndex = i;
                break;
            }
        }

        if (atIndex == tokens.length - 1) {
            throw new BadEventFormatException("Input does not contain a date/time");
        } else if (atIndex == 1) {
            throw new BadEventFormatException("Input does not contain a description");
        }

        this.description = String.join(" ", Arrays.copyOfRange(tokens, 1, atIndex));
        this.startEndDateTime = String.join(" ", Arrays.copyOfRange(tokens, atIndex + 1, tokens.length));
    }

    @Override
    public String toString() {
        return "[E][" + getStatusIcon() + "] " + description + " (at: " + startEndDateTime + ")";
    }
}
