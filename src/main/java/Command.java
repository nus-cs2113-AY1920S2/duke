import java.text.MessageFormat;

public abstract class Command {

    protected String editWord;
    protected Task task;
    protected String fullCommand;

    public Command () {
        this.editWord = null;
    }

    public String checkSingular() {
        if (TaskList.taskList.size() == 1) {
            return "";
        }
        return "s";
    }

    @Override
    public String toString() {
        return MessageFormat.format("Got it. I''ve {0} this task: \n{1}\n Now you have {2} task{3} in the list.",
                editWord, task, TaskList.taskList.size(), checkSingular());
    }
}
