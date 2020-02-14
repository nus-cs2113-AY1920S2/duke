package duke.command;

import duke.DukeException;

public class Event extends Task{
    public Event(String command) throws DukeException {
        super("[E][✗] "
                + command.replaceFirst("event\\s","").replaceFirst("/at","(at:").trim()
                + ")");
        if (command.matches("event\\s+")){
            throw new DukeException(1,"event");
        }
        if (!command.matches(".*/at.*")){
            throw new DukeException(2,"event");
        }
    }
}
