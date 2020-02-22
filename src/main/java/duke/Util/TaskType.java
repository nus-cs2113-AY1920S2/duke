package duke.Util;

public enum TaskType {
    TODO ("1"),
    DEADLINE("2"),
    EVENT("3")
    ;

    private final String taskType;
    TaskType(String taskType) {
        this.taskType = taskType;
    }
    public String getTaskType() {
        return this.taskType;
    }
}