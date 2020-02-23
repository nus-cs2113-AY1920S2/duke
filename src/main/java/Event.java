import java.util.List;

public class Event extends Task {
    protected String icon = "[E]";
    protected String eventAt;

    public Event(int isDone, String description, String evenAt) {
        super(description);
        this.eventAt = evenAt;

        if (isDone == 1) {
            super.markAsDone();
        }
    }

    public Event(String description, String eventAt){
        super(description);
        this.eventAt = eventAt;
    }

    public String toString() {
        String toPrint = super.toString();
        toPrint = String.format("%s%s (at: %s)", this.icon, toPrint, this.eventAt);
        return toPrint;
    }

    public String getEventAt() {
        return this.eventAt;
    }

    @Override
    public void addIfMatchesKeyword(Task t, List<Task> foundTasks, String keyword) {
        String description = t.getDescription();
        String eventAt = getEventAt();
        if (description.contains(keyword) || eventAt.contains(keyword)) {
            foundTasks.add(t);
        }

    }
}
