package duke.Util;

public enum CommandType {
    ADD_TASK("1"),
    PRINT_TASKS("2"),
    MARK_AS_DONE("3"),
    DELETE_TASK("4"),
    FIND_TASK("5"),
    ;

    public final String commandType;
    CommandType(String taskType) {
        this.commandType = taskType;
    }
    public String getCommandType() {
        return this.commandType;
    }
}
