import java.text.MessageFormat;

public abstract class ListCommand extends Command {

    protected static final String EMPTY_LIST_MESSAGE = "There is nothing on the list.";
    protected static final String LIST_HEADER_MESSAGE = "Here are the tasks in your list: ";


    public String displayTask (int i, Task t) {
        int number = i + 1;
        return Integer.toString(number) + "." + t;
    }

    public String displayList() {
        String message;
        if (TaskList.getSize() == 0) {
            message = EMPTY_LIST_MESSAGE;
        } else {
            message = LIST_HEADER_MESSAGE;
            for (int i = 0; i < TaskList.getSize(); i++) {
                message += displayTask(i, TaskList.fetchTask(i));
            }
        }
        return message;
    }

    @Override
    public String toString() {
        return displayList();
    }
}
