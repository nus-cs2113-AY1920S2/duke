package duke;

import duke.Task;

public class Event extends Task implements DatedEvents{

    protected String date;

    public Event(String description, String at) {
        super(description);
        this.date = at;

    }

    public String returnDate(){
        return this.date;
    }



    @Override
    public String getTypeIcon() {

        return ("[E]");
    }


}
