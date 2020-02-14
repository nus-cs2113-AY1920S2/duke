public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    public String showFullDescription() {
        return description;
    }

    public String getTypeIcon() {
        return "";
    }

    public void markAsDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        String status = String.format("[%s]", this.getStatusIcon());
        return this.getTypeIcon() + status + " " + this.showFullDescription();
    }
}
