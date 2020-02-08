package task;
import static util.Constants.*;

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
     * task: addTasks(description) -> treat as todo
     * todo: addTasks(description, "todo")
     * deadline/event: addTasks(description, time, "deadline"/"event")
     */
    public void addTasks(String... args) {
        Task currentTask;
        switch (args.length) {
        case 1:
        case 2:
            // add todo
            currentTask = new Todo(args[0]);
            break;
        default:
            // add deadline/event
            if (args[2].equals("deadline")) {
                currentTask = new Deadline(args[0], args[1]);
            } else {
                currentTask = new Event(args[0], args[1]);
            }
            break;
        }
        tasks[taskCount++] = currentTask;
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
        } finally {
            System.out.println(LINE_DIVIDER);
        }
    }
}
