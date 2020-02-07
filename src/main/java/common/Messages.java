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

    /**
     * Print all tasks in the task list
     */
    public static void printAllTasks(TaskList tasklist){
        for (int i = 1; i <= tasklist.getInternalList().size() ; i++) {
            Task task = tasklist.getInternalList().get(i-1);
            if (task instanceof TodoTask) {
                printTodoTask((TodoTask) task, i);
            } else if (task instanceof DeadlineTask) {
                printDeadlineTask((DeadlineTask) task, i);
            } else if( task instanceof EventTask) {
                printEventTask((EventTask) task, i);
            }
        }
    }
    public static void printTodoTask(TodoTask todoTask, int index){
        System.out.println(String.format(
                MESSAGE_TODO_LIST,
                index,
                todoTask.getTaskType(),
                todoTask.getChar(),
                todoTask.getTaskDescription()));
    }

    public static void printDeadlineTask(DeadlineTask deadlineTask, int index){
        System.out.println(String.format(
                MESSAGE_DEADLINE_LIST,
                index,
                deadlineTask.getTaskType(),
                deadlineTask.getChar(),
                deadlineTask.getTaskDescription(),
                deadlineTask.getTaskDeadline()));
    }

    public static void printEventTask(EventTask eventTask, int index){
        System.out.println(String.format(
                MESSAGE_EVENT_LIST,
                index,
                eventTask.getTaskType(),
                eventTask.getChar(),
                eventTask.getTaskDescription(),
                eventTask.getTaskStartTime()));
    }
}
