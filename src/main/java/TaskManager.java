public class TaskManager {
    public static final int MAXIMUM_TASK = 100;
    private Task[] tasks = new Task[MAXIMUM_TASK];
    private int numOfTasks = 0;

    public void addTask(String command) {
        Task task = new Task();
        task.name = command;
        tasks[numOfTasks++] = task;
    }

    public void listTask() {
        System.out.println("------------------------------------");
        for(int i = 0; i < numOfTasks; i++) {
            System.out.println(String.format("%d. %s", i+1, tasks[i].name));
        }
        System.out.println("------------------------------------");
        System.out.println();
    }
}
