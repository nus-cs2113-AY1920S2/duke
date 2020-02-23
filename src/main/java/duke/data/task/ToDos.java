package duke.data.task;

public class ToDos extends Task {

    public ToDos(String description) throws NullPointerException {
        super(description);
        super.setTaskType("T");
    }

    @Override
    public String toString() {
        return "[" + super.getTaskType() + "]" + super.toString();
    }

    @Override
    public boolean isSameTask(Task toCheck) {
        return super.isSameTask(toCheck);
    }
}
