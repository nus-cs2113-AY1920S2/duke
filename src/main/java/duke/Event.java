package duke;

import duke.Task;

public class Event extends Task {



    public Event(String description) {
        super(description);

    }

    @Override
    public String getTypeIcon() {

        return ("[E]");
    }


}
