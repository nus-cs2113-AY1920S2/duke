import java.text.MessageFormat;

public class DeleteCommand extends Command {

    protected String command;
    protected String fullCommand;
    protected String errorMessage;
    protected String editType;

    public DeleteCommand(String command, String fullCommand) {
        this.command = command;
        this.fullCommand = fullCommand;
        this.errorMessage = String.format(Messages.MESSAGE_INVALID_DESCRIPTION,
                command, Messages.DONE_DELETE_ERROR_MESSAGE);
        this.editType = "removed";
    }

    public Task removeTask() throws DukeException{
        int number = Integer.parseInt(fullCommand.substring(7));
        if (number > TaskList.getSize()) {
            throw new DukeException();
        }
        Task t = TaskList.fetchTask(number-1);
        TaskList.deleteTask(t);
        return t;
    }

    @Override
    public void execute (TaskList tasks, Ui ui, Storage storage) {
        try {
            Task t = removeTask();
            ui.out.println(String.format(Messages.MESSAGE_ADD_DELETE_SUCCESS,
                    editType, t, tasks.getSize(), tasks.checkSingular()));
        } catch (NumberFormatException e) {
            ui.out.println(errorMessage);
        } catch (IndexOutOfBoundsException e) {
            ui.out.println(errorMessage);
        } catch (DukeException e) {
            ui.out.println(errorMessage);
        }
    }

    @Override
    public boolean isExit(){
        return false;
    }

}