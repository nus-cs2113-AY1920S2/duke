import java.text.MessageFormat;
import java.util.regex.MatchResult;

public class DoneCommand extends Command {

    protected String command;
    protected String fullCommand;
    protected String errorMessage;

    public DoneCommand (String command, String fullCommand) {
        this.command = command;
        this.fullCommand = fullCommand;
        this.errorMessage = String.format(Messages.MESSAGE_INVALID_DESCRIPTION,
                command, Messages.DONE_DELETE_ERROR_MESSAGE);
    }

    public Task doneTask() throws DukeException{
        int number = Integer.parseInt(fullCommand.substring(5));
        if (number > TaskList.getSize()) {
            throw new DukeException();
        }
        Task t = TaskList.fetchTask(number-1);
        t.markAsDone();
        return t;
    }

    @Override
    public void execute (TaskList tasks, Ui ui, Storage storage) {
        try {
            Task t = doneTask();
            ui.out.println(String.format(Messages.MESSAGE_DONE_SUCCESS, t));
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
    };

}