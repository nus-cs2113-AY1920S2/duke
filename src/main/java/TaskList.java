public class TaskList {
    // assume list holds a maximum of 100 tasks
    private Task[] list;
    private int numTasks;

    TaskList() {
        this.list = new Task[100];
        this.numTasks = 0;
    }

    public void addTask(Task task) {
        this.list[numTasks] = task;
        numTasks++;
        System.out.println("Task added: ");
        System.out.println("  " + task);
        System.out.println("You have " + numTasks + " task" + (numTasks == 1 ? "" : "s") + " in the list");
    }

    public void showTasks() {
        if (numTasks == 0) {
            System.out.println("Nice, nothing to see here");
        } else {
            System.out.println("Quite a few tasks you got there");
            for (int i = 0; i < numTasks; ++i) {
                System.out.printf("%3d. %s\n", i + 1, list[i]);
            }
        }
    }

    public void markTaskAsDone(int index) {
        list[index].markAsDone();
        System.out.println("Well, that's one task down");
        System.out.println("  " + list[index]);
    }
}
