package common;

import data.Duke;
import data.task.*;

/**
 * Container for user visible messages.
 */
public class Messages {
    public static final char DONE = 'D';
    public static final char notDONE = 'N';
    public static final String DIVIDER = "===================================================";
    public static final String MESSAGE_WELCOME = "  Hello! I'm Duke\n  What can I do for you?";
    public static final String MESSAGE_FAREWELL = "  Bye. Hope to see you again soon!";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_TASK_DISPLAYED_INDEX = "The task index provided is invalid";
    public static final String MESSAGE_TASK_NOT_IN_TASKLIST = "The Task could not be found in address book";
    public static final String MESSAGE_TODO_LIST = "  %d. [%c][%c] %s";
    public static final String MESSAGE_DEADLINE_LIST = "  %d. [%c][%c] %s (%s)";
    public static final String MESSAGE_EVENT_LIST = "  %d. [%c][%c] %s (%s)";
    public static StringBuilder taskListMessage = new StringBuilder();


    /**
     * Print all tasks in the task list
     */
    public static String printAllTasks(TaskList tasklist){
        String taskMessage = "";
        taskListMessage = new StringBuilder(taskMessage);
        for (int i = 1; i <= tasklist.getInternalList().size() ; i++) {
            Task task = tasklist.getInternalList().get(i-1);
            if (task instanceof TodoTask) {
                taskMessage = printTodoTask((TodoTask) task, i);
            } else if (task instanceof DeadlineTask) {
                taskMessage = printDeadlineTask((DeadlineTask) task, i);
            } else if( task instanceof EventTask) {
                taskMessage = printEventTask((EventTask) task, i);
            }
            taskListMessage = taskListMessage.append(taskMessage).append("\n");
        }
        return taskListMessage.toString();
    }

    public static String printTodoTask(TodoTask todoTask, int index){
        return String.format(
                MESSAGE_TODO_LIST,
                index,
                todoTask.getTaskType(),
                todoTask.getChar(),
                todoTask.getTaskDescription());
    }

    public static String printDeadlineTask(DeadlineTask deadlineTask, int index){
        return String.format(
                MESSAGE_DEADLINE_LIST,
                index,
                deadlineTask.getTaskType(),
                deadlineTask.getChar(),
                deadlineTask.getTaskDescription(),
                deadlineTask.getTaskDeadline());
    }

    public static String printEventTask(EventTask eventTask, int index){
        return String.format(
                MESSAGE_EVENT_LIST,
                index,
                eventTask.getTaskType(),
                eventTask.getChar(),
                eventTask.getTaskDescription(),
                eventTask.getTaskStartTime());
    }
}
