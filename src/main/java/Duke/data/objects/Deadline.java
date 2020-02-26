package Duke.data.objects;

public class Deadline extends Task {

    protected String by;
    public static final String COMMAND_WORD = "deadline";
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }
    public String getType(){
        return "D";
    }
    public String getExtra(){
        return this.by;
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}