public class Deadline extends Task {
    
    private String by;
    private final String TAG = "[D]";
    private final String OPEN_BRACKET = "(";
    private final String CLOSE_BRACKET = ")";
    
    public Deadline(String description, String by) {
        super(description);
        this.by = OPEN_BRACKET + by + CLOSE_BRACKET;
        this.by = stringBuilder(this.by);
    }
    
    @Override
    public String toString() {
        return TAG + super.toString() + by;
    }
    
    
}
