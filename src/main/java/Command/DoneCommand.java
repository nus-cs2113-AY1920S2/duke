package Command;

/**
 * Changes the status of a task to complete
 * updates data file upon completion
 */
public class DoneCommand extends Command {

    private String indexCompleteTask;

    /**
     * Convenience constructor using raw values
     *
     * @param indexCompleteTask String input of task index, will be checked later if it is a valid integer
     */
    public DoneCommand( String indexCompleteTask) {
        super();
        this.indexCompleteTask = indexCompleteTask;
    }

    @Override
    public void execute() {
        try {
            boolean successfulUpdate = tasks.completeTask(Integer.parseInt(indexCompleteTask));
            if (successfulUpdate) {
                storage.rebuildTaskFile(tasks.getTaskList());
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("[Error] Please input a task within the range of: 1 - " + tasks.getTaskListCounter() + "\n");
        } catch (NumberFormatException e) {
            System.out.println("[Error] Please input task number as a number, instead of spelling it out\n");
        }
    }
}
