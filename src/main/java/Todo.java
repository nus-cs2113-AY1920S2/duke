public class Todo extends Task {

    protected String by;

    public Todo(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        if (by.equals("")) {
            return "[T]" + super.toString();
        }
        String[] bySplit = by.split(" ", 2);
        return "[T]" + super.toString() + "(" + bySplit[0] + ": " + bySplit[1] + ")";
    }
}
