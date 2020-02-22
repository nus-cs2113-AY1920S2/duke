public class Deadline extends Task {
    protected String icon = "[D]";
    protected String deadline;

    public Deadline(int isDone, String description, String dateline) {
        super(description);
        this.deadline = dateline;

        if (isDone == 1) {
            super.markAsDone();
        }
    }

    public Deadline(String description, String deadline){
        super(description);
        this.deadline = deadline;

    }

    public String toString() {
        String toPrint = super.toString();
        toPrint = String.format("%s%s (by: %s)", this.icon, toPrint, this.deadline);
        return toPrint;
    }
}
