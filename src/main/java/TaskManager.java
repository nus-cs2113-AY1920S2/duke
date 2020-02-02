public class TaskManager {
    public static final int MAXIMUM_TASK = 100;
    private Task[] tasks = new Task[MAXIMUM_TASK];
    private int numOfTasks = 0;

    public void addTask(String command) {
        System.out.println("------------------------------------");
        System.out.println("added: "+ command);
        System.out.println("You added a new task, remember to finish it on time!");
        System.out.println("------------------------------------");
        System.out.println();
        Task task = new Task();
        task.name = command;
        task.isDone = false;
        tasks[numOfTasks++] = task;
    }

    public void listTask() {
        System.out.println("------------------------------------");
        System.out.println("Here is the list of all tasks you have:");
        for(int i = 0; i < numOfTasks; i++) {
            System.out.println(String.format("%d.[%c] %s", i+1,(tasks[i].isDone? '✓':'✗'), tasks[i].name));
        }
        System.out.println("------------------------------------");
        System.out.println();
    }

    public void markTask(int taskNo) {
        System.out.println("------------------------------------");
        System.out.println("Good job! You have done this task:");
        System.out.println("  [✓] " + tasks[taskNo-1].name);
        System.out.println("------------------------------------");
        tasks[taskNo-1].isDone = true;
    }
}
