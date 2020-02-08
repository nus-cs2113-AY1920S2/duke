package task;

public class TaskManager {
    private Task[] tasks;
    private int taskCount;
    private static final String LINE_DIVIDER = "    ____________________________________________________________";
    private static final String LIST_TASKS_PROMPT = "     Here are the tasks in your list:";
    private static final String DONE_TASKS_PROMPT = "     Nice! I've marked this task as done:";
    private static final String ADD_TASKS_PROMPT = "     Got it. I've added this task:";
    private static final String ADD_TASKS_POST_PROMPT = "     Now you have %d tasks in the list.\n";
    private static final String LIST_SINGLE_TASK_MESSAGE = "     %d.%s\n";
    private static final String ADD_SINGLE_TASK_MESSAGE = "       %s\n";
    private static final String DONE_SINGLE_TASK_MESSAGE = "       %s\n";

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
        System.out.println(LIST_TASKS_PROMPT);
        for (int i = 0; i < taskCount; ++i) {
            System.out.printf(LIST_SINGLE_TASK_MESSAGE, i, tasks[i]);
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
        System.out.println(ADD_TASKS_PROMPT);
        System.out.printf(ADD_SINGLE_TASK_MESSAGE, currentTask);
        System.out.printf(ADD_TASKS_POST_PROMPT, taskCount);
        System.out.println(LINE_DIVIDER);
    }


    public void markAsDone(int taskID) {
        tasks[taskID].markAsDone();
        System.out.println(LINE_DIVIDER);
        System.out.println(DONE_TASKS_PROMPT);
        System.out.printf(DONE_SINGLE_TASK_MESSAGE, tasks[taskID]);
        System.out.println(LINE_DIVIDER);
    }
}
