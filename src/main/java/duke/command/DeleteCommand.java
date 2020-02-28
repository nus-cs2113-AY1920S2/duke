package duke.command;

import duke.TaskList;

public class DeleteCommand extends Command {

    private int index;

    public DeleteCommand(String type,int index) {
        super(type);
        this.index=index;
    }

    @Override
    public void execute(TaskList tasks) {
        tasks.deleteTask(this);
    }

    public int getIndex() {
        return index;
    }
}
