import java.util.List;

public class ToDo extends Task {
    protected String icon = "[T]";

    public ToDo(int isDone, String description ) {
        super(description);

        if (isDone == 1) {
            super.markAsDone();
        }
    }

    public ToDo(String description) {
        super(description);
    }

    public String toString() {
        String toPrint = super.toString();
        toPrint = String.format("%s%s", this.icon, toPrint);
        return toPrint;
    }

    @Override
    public void addIfMatchesKeyword(Task t, List<Task> foundTasks, String keyword) {
        String description = t.getDescription();
        if (description.contains(keyword)) {
            foundTasks.add(t);
        }
    }
}
