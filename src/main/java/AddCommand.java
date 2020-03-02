import java.text.MessageFormat;
import java.util.ArrayList;

public class AddCommand extends Command {
    protected String addType;

    public AddCommand (String command) {
        this.addType = command;
        this.editWord = "added";
    }

    public String getAction() {
        String action;
        if (addType.equals("todo")) {
            action = fullCommand.substring(5);
        } else if (addType.equals("deadline")) {
            int indexOfDate = fullCommand.indexOf("/by");
            action = fullCommand.substring(9, indexOfDate);
        } else {
            int indexOfDate = fullCommand.indexOf("/at");
            action = fullCommand.substring(6, indexOfDate);
        }
        return action;
    }

    public String getDate() {
        int indexOfDate;
        if (addType.equals("deadline")) {
            indexOfDate = fullCommand.indexOf("/by") + 4;
        } else {
            indexOfDate = fullCommand.indexOf("/at") + 4;
        }
        return fullCommand.substring(indexOfDate);
    }

    public Task addToList() {
        Task t;
        if (addType.equals("todo")) {
            t = new Todo(getAction());
        } else if (addType.equals("deadline")) {
            t = new Deadline(getAction(), getDate());
        } else {
            t = new Event(getAction(), getDate());
        }
        TaskList.addTask(t);
        return t;
    }

    @Override
    public String toString() {
        return MessageFormat.format("Got it. I''ve {0} this task: \n{1}\n Now you have {2} task{3} in the list.",
                editWord, addToList(), TaskList.taskList.size(), checkSingular());
    }

}

