public class Deadline extends Todo {
    private String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }
    public char getTaskType() {
        return 'D';
    }

    public String toString() {
        return super.toString() + " (by: " + by + ")";
    }
}
