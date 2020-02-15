package duke.task;

public abstract class Task {
    String name;
    boolean isDone;


    public Task(String name) {
        this.name = name;
        isDone = false;
    }

    public String getName() {
        return name;
    }

    public void changeStatus(boolean newStatus) {
        isDone = newStatus;
    }

    public boolean getStatus() {
        return isDone;
    }

    public abstract String print();
}
