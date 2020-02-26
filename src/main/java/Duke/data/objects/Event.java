package Duke.data.objects;

public class Event extends Task {

    protected String at;
    public static final String COMMAND_WORD = "event";
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }
    public String getType(){
        return "E";
    }
    public String getExtra(){
        return this.at;
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}