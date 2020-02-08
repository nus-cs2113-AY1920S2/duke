package duke.util;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import static duke.util.Constants.*;

public class TaskManager {
    private Task[] tasks;
    private int taskCount;

    public TaskManager() {
        tasks = new Task[100];
        this.taskCount = 0;
    }

    public TaskManager(int taskCount) {
        tasks = new Task[taskCount];
        this.taskCount = 0;
    }

    public int getTaskCount() {
        return taskCount;
    }

    public void listTasks() {
        System.out.println(LINE_DIVIDER);
        System.out.println(FIVE_SPACES+LIST_TASKS_PROMPT);
        for (int i = 0; i < taskCount; ++i) {
            System.out.printf(SEVEN_SPACES+LIST_SINGLE_TASK_MESSAGE, i, tasks[i]);
        }
        System.out.println(LINE_DIVIDER);
    }

    /**
     *
     * @param args
     *
     * todo: addTasks(description, "todo")
     * deadline/event: addTasks(description, time, "deadline"/"event")
     */
    public void addTask(String... args) {
        String taskDescription = args[0].trim();
        try {
            Task currentTask;
            switch (args.length) {
            case 2:
                currentTask = new Todo(taskDescription);
                break;
            default:
                // add deadline/event
                if (args[2].equals("deadline")) {
                    currentTask = new Deadline(taskDescription, args[1]);
                } else {
                    currentTask = new Event(taskDescription, args[1]);
                }
                break;
            }
            tasks[taskCount++] = currentTask;
            printAddTaskSuccessfulPrompt(currentTask);
        } catch (DukeException e) {
            System.out.println(FIVE_SPACES+CRYING_FACE+TASK_DESCRIPTION_EMPTY_ERROR_MESSAGE);
        }
    }

    private void printAddTaskSuccessfulPrompt(Task currentTask) {
        System.out.println(LINE_DIVIDER);
        System.out.println(FIVE_SPACES+ADD_TASKS_PROMPT);
        System.out.printf(SEVEN_SPACES+ADD_SINGLE_TASK_MESSAGE, currentTask);
        System.out.printf(FIVE_SPACES+ADD_TASKS_POST_PROMPT, taskCount);
        System.out.println(LINE_DIVIDER);
    }


    public void markAsDone(int taskID) {
        System.out.println(LINE_DIVIDER);
        try {
            tasks[taskID].markAsDone();
            System.out.println(FIVE_SPACES + DONE_TASKS_PROMPT);
            System.out.printf(SEVEN_SPACES + DONE_SINGLE_TASK_MESSAGE, tasks[taskID]);
        } catch (NullPointerException e) {
            System.out.println(FIVE_SPACES+CRYING_FACE+TASK_ID_NOT_EXIST_ERROR_MESSAGE);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(FIVE_SPACES+CRYING_FACE+TASK_ID_NOT_PROVIDED_OR_INVALID_ERROR_MESSAGE);
        }
        finally {
            System.out.println(LINE_DIVIDER);
        }
    }
}
