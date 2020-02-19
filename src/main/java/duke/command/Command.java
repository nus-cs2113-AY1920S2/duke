package duke.command;

import duke.data.TaskList;
import duke.exception.DukeException;
import duke.exception.TestDukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.ui.Ui;

public class Command {
    protected String fullCommand;
    protected String taskType;
    protected String args;
    protected boolean isTodo;
    protected boolean isDeadline;
    protected boolean isEvent;
    protected boolean isExit;
    protected boolean isDone;
    protected boolean isList;
    protected boolean isDelete;
    protected boolean isAdd;
    protected boolean isWrongType;
    public static final String CUTTING_LINE = "    ____________________________________________________________";

    public Command(){
        isExit = true;
    }


    public Command(String fullCommand, String taskType, String args){
        this.taskType = taskType;
        this.args = args;
        this.fullCommand = fullCommand;
        isTodo = taskType.equalsIgnoreCase("todo");
        isDeadline = taskType.equalsIgnoreCase("deadline");
        isEvent = taskType.equalsIgnoreCase("event");
        isExit = taskType.equalsIgnoreCase("bye");
        isList = taskType.equalsIgnoreCase("list");
        isDone = taskType.equalsIgnoreCase("done");
        isDelete = taskType.equalsIgnoreCase("delete");
        isWrongType = !(isTodo || isDeadline || isEvent);
        isAdd = isTodo || isDeadline || isEvent;
    }


    public boolean isExit() {
        return isExit;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
    }


}
