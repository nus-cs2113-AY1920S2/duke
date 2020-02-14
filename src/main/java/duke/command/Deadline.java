package duke.command;

import duke.DukeException;

public class Deadline extends Task {
    public Deadline(String command) throws DukeException {
        super("[D][✗] "
                + command.replaceFirst("deadline\\s","").replaceFirst("/by","(by:").trim()
                + ")");
        if (command.matches("deadline\\s+")){
            throw new DukeException(1,"deadline");
        }
        if (!command.matches(".*/by.*")){
            throw new DukeException(2,"deadline");
        }
    }
}
