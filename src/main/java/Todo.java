public class Todo {
    private String description;
    private boolean isDone;

    public Todo(String description) {
        this.isDone = false;
        this.description = description;
    }
    private String isDone() {
        return this.isDone ? "[✓] " : "[✗] ";
    }

    public void setDone() {
        this.isDone = true;
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t" + "[✓] " + this.description);
    }
    public char getTaskType() {
        return 'T';
    }
    public String toString() {
        return String.format("[%c]", getTaskType()) + isDone() + this.description;
    }
}
