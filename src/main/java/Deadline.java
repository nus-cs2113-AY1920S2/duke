public class Deadline extends TaskManager {
    protected String by;
    public Deadline(String task, String by) {
        super(task);
        this.by = by;
    }

    public Deadline(String task) {
        super(task);
    }

    @Override
    public String toString() {
        if (by.equals("")) {
            return "[D]" + super.toString();
        }
        String[] bySplit = by.split(" ", 2);
        return "[D]" + super.toString() + "(" + bySplit[0] + ": " + bySplit[1] + ")";
    }
}
