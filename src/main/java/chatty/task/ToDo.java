package chatty.task;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    @Override
    public String getFileString() {
        return String.format("T|%s|%s", this.isDone, this.description);
    }
}
