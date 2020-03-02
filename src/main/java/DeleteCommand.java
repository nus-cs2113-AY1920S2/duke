import java.text.MessageFormat;

public class DeleteCommand extends Command {

    public DeleteCommand() {
        this.editWord = "removed";
    }

    public Task removeTask () {
        int number = Integer.parseInt(fullCommand.substring(7));
        Task t = TaskList.fetchTask(number-1);
        TaskList.deleteTask(t);
        return t;
    }

    @Override
    public String toString() {
        return MessageFormat.format("Got it. I''ve {0} this task: \n{1}\n Now you have {2} task{3} in the list.",
                editWord, removeTask(), TaskList.getSize(), checkSingular());
    }

}
