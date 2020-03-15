package duke.taskmanager;

public class ToDo extends Task {
    public ToDo(String task) {
        super(task);
        isDone = false;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String contentToFile() { return "T" + "|" + super.contentToFile(); }
}