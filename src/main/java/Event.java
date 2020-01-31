public class Event extends Todo {
    private String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }
    public char getTaskType() {
        return 'E';
    }
    public String toString() {
        return super.toString() + " (at: " + at + ")";
    }
}
