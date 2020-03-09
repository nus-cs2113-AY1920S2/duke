package Command;

import Exceptions.EmptyListException;

/**
 * Deletes a task from the given task list object,
 * updates data file upon completion
 */
public class DeleteCommand extends Command {

    private String indexToDelete;

    /**
     * Convenience constructor using raw values
     *
     * @param indexToDelete String input of task index, will be checked later if it is a valid integer
     */
    public DeleteCommand(String indexToDelete) {
        this.indexToDelete = indexToDelete;
    }

    @Override
    public void execute() {
        try{
            tasks.deleteTask(Integer.parseInt(indexToDelete));
            storage.rebuildTaskFile(tasks.getTaskList());
        } catch (EmptyListException e) {
            System.out.println("[Error][Delete]: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("[Error][Delete]: Please input task number as a number, instead of spelling it out");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("[Error][Delete]: Please input a task within the range of: 1 - " + tasks.getTaskListCounter());
        }
    }
}
