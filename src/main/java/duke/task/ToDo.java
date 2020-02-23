package duke.task;

public class ToDo extends Task{

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString(){
        String taskType="[T]";
        String detail = taskType + " [" + getStatusIcon() + "] " + description;
        return detail;
    }
}
