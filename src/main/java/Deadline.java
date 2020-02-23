import java.util.List;

public class Deadline extends Task {
    protected String icon = "[D]";
    protected String deadline;

    /**
     * Constructor used when loading from the save file.
     *
     * @param isDone integer representing a boolean, where 1 is done and 0 is not done, from save file.
     * @param description string representing task description, from the save file
     * @param deadline string representing deadline, from the save file
     */
    public Deadline(int isDone, String description, String deadline) {
        super(description);
        this.deadline = deadline;

        if (isDone == 1) {
            super.markAsDone();
        }
    }

    public Deadline(String description, String deadline){
        super(description);
        this.deadline = deadline;

    }

    /**
     * Overrides the default toString so that Task gets printed in a specific format
     *
     * @return the formatted String to print
     */
    public String toString() {
        String toPrint = super.toString();
        toPrint = String.format("%s%s (by: %s)", this.icon, toPrint, this.deadline);
        return toPrint;
    }

    public String getDeadline() {
        return this.deadline;
    }

    @Override
    public void addIfMatchesKeyword(Task t, List<Task> foundTasks, String keyword) {
        String description = t.getDescription();
        String deadline = getDeadline();
        if (description.contains(keyword) || deadline.contains(keyword)) {
            foundTasks.add(t);
        }
    }
}
