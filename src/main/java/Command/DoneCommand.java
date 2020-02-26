package Command;

import Task.TaskList;

public class DoneCommand extends Command {

    private int indexCompleteTask;

    public DoneCommand( int indexCompleteTask) {
        super();
        this.indexCompleteTask = indexCompleteTask;
    }

    @Override
    public void execute() {
        try {
            boolean successfulUpdate = tasks.completeTask(indexCompleteTask);
            if (successfulUpdate) {
                storage.rebuildTaskFile(tasks.getTaskList());
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("[Error] Please input a task within the range of: 1 - " + tasks.getTaskListCounter() + "\n");
        }
    }
}
