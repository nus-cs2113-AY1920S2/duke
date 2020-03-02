import java.text.MessageFormat;

public class DoneCommand extends Command {

    public DoneCommand (String fullCommand) {
        this.fullCommand = fullCommand;
    }

    public Task doneTask () {
        int number = Integer.parseInt(fullCommand.substring(5));
        Task t = TaskList.fetchTask(number-1);
        t.markAsDone();
        return t;
    }

    @Override
    public String toString() {
        return ("Nice! I've marked this task as done: \n" + doneTask());
    }


}
