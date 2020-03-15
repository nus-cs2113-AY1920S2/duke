package duke.util;

public enum TaskType {
    TODO ("1"),
    DEADLINE("2"),
    EVENT("3")
    ;

    public final String taskType;
    TaskType(String taskType) {
        this.taskType = taskType;
    }
}