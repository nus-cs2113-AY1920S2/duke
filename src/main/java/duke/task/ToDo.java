package duke.task;

import duke.command.AddCommand;

public class ToDo extends Task{

    public ToDo(String description) {
        super(description);
        this.category="todo";
    }

    public ToDo(AddCommand addCommand){
        super(addCommand);
    }

    @Override
    public String toString(){
        String taskType="[T]";
        String detail = taskType + " [" + getStatusIcon() + "] " + getDescription();
        return detail;
    }
}
