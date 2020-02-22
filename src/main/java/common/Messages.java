package common;
import data.task.*;

/**
 * Container for user visible messages.
 */
public class Messages {
    public static final int INDEX_OFF_SET = -1;
    public static final char DONE = 'D';
    public static final char notDONE = 'N';
    public static final String DIVIDER = "+----------------------------------------------------+";
    public static final String MESSAGE_WELCOME = "  Hello! I'm Kuri\n  What can I do for you?";
    public static final String MESSAGE_FAREWELL = "  Bye. Hope to see you again soon!";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_TASK_DISPLAYED_INDEX = "The task index provided is invalid";
    public static final String MESSAGE_TASK_NOT_IN_TASKLIST = "The Task could not be found in address book";
    public static final String MESSAGE_TODO_LIST = "  %d. [Id:%d][%c][%c] %s";
    public static final String MESSAGE_DEADLINE_LIST = "  %d. [Id:%d][%c][%c] %s (%s)";
    public static final String MESSAGE_EVENT_LIST = "  %d. [Id:%d][%c][%c] %s (%s)";
    public static final String MESSAGE_FILE_OPERATION_IO_ERROR = "+|Error writing to file: %s|+";
    public static final String MESSAGE_DUPLICATE_TASK_ALERT = "Alert! This task is similar to task Index %d\nDo you want to add a duplicate task? Press Y to add and others to not add: ";
    public static final String MESSAGE_DUPLICATE_TASK_NOT_ADDED = "The duplicate task is not added!";
    public static StringBuilder taskListMessage;

    /**
     * Print all tasks in the task list
     */
    public static String printAllTasks(TaskList tasklist){
        String taskMessage = "";
        taskListMessage = new StringBuilder();
        getTaskListMessage(tasklist, taskMessage);
        return taskListMessage.toString();
    }

    /**
     * get tasklist message
     * @param tasklist
     * @param taskMessage
     */
    private static void getTaskListMessage(TaskList tasklist, String taskMessage) {
        for (int index = 1; index <= tasklist.getInternalList().size() ; index++) {
            Task task = tasklist.getInternalList().get(index+ INDEX_OFF_SET);
            taskMessage = getTaskMessage(taskMessage, index, task);
            taskListMessage = taskListMessage.append(taskMessage).append("\n");
        }
        taskListMessage.append(String.format("There are total %d task(s) in the list",
                tasklist.getInternalList().size()));
    }

    /**
     * get task message
     * @param taskMessage
     * @param index
     * @param task
     * @return
     */
    private static String getTaskMessage(String taskMessage, int index, Task task) {
        if (task instanceof TodoTask) {
            taskMessage = printTodoTask((TodoTask) task, index);
        } else if (task instanceof DeadlineTask) {
            taskMessage = printDeadlineTask((DeadlineTask) task, index);
        } else if( task instanceof EventTask) {
            taskMessage = printEventTask((EventTask) task, index);
        }
        return taskMessage;
    }

    /**
     * print todo-type task
     * @param todoTask
     * @param index
     * @return
     */
    public static String printTodoTask(TodoTask todoTask, int index){
        return String.format(
                MESSAGE_TODO_LIST,
                index,
                todoTask.getTaskIndex(),
                todoTask.getTaskType(),
                todoTask.getChar(),
                todoTask.getTaskDescription());
    }

    /**
     * print deadline-type task
     * @param deadlineTask
     * @param index
     * @return
     */
    public static String printDeadlineTask(DeadlineTask deadlineTask, int index){
        return String.format(
                MESSAGE_DEADLINE_LIST,
                index,
                deadlineTask.getTaskIndex(),
                deadlineTask.getTaskType(),
                deadlineTask.getChar(),
                deadlineTask.getTaskDescription(),
                deadlineTask.getTaskDeadline());
    }

    /**
     * print event-type task
     * @param eventTask
     * @param index
     * @return
     */
    public static String printEventTask(EventTask eventTask, int index){
        return String.format(
                MESSAGE_EVENT_LIST,
                index,
                eventTask.getTaskIndex(),
                eventTask.getTaskType(),
                eventTask.getChar(),
                eventTask.getTaskDescription(),
                eventTask.getTaskStartTime());
    }

    public static void consumeLine(){
        System.out.println("");
    }
}
