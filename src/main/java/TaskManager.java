public class TaskManager {
    private Task[] tasks;
    private int taskCount;
    private static final String LINE_DIVIDER = "    ____________________________________________________________";
    private static final String LIST_TASKS_PROMPT = "     Here are the tasks in your list:";
    private static final String DONE_TASKS_PROMPT = "     Nice! I've marked this task as done:";
    private static final String LIST_SINGLE_TASK_MESSAGE = "     %d.%s\n";
    private static final String ADD_SINGLE_TASK_MESSAGE = "     added: %s\n";
    private static final String DONE_SINGLE_TASK_MESSAGE = "       %s %s\n";

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

    public void addTasks(String description) {
        Task currentTask = new Task(description);
        tasks[taskCount++] = currentTask;
        System.out.println(LINE_DIVIDER);
        System.out.printf(ADD_SINGLE_TASK_MESSAGE, description);
        System.out.println(LINE_DIVIDER);
    }

    public void markAsDone(int taskID) {
        tasks[taskID].markAsDone();
        System.out.println(LINE_DIVIDER);
        System.out.println(DONE_TASKS_PROMPT);
        System.out.printf(DONE_SINGLE_TASK_MESSAGE, tasks[taskID].getStatusIcon(), tasks[taskID].getTaskDescription());
        System.out.println(LINE_DIVIDER);
    }
}
