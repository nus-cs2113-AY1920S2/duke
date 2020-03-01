package duke;

public class Events extends Task {
    private String at;

    public Events(String description, String at) {
        super(description);
        this.at = at;
    }

    public String getEvent(){
        return at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + getEvent() + ")" ;
    }

}
