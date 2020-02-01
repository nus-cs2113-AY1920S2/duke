public class Event extends Task {
    
    private String at;
    private final String TAG = "[E]";
    private final String OPEN_BRACKET = "(";
    private final String CLOSE_BRACKET = ")";
    
    public Event(String description, String at) {
        super(description);
        this.at = OPEN_BRACKET + at + CLOSE_BRACKET;
        this.at = stringBuilder(this.at);
    }

    @Override
    public String toString() {
        return TAG + super.toString() + at;
    }
    
}
