public class ToDoList {
    // assume list holds a maximum of 100 tasks
    private Task[] list;
    private int numTasks;

    ToDoList() {
        this.list = new Task[100];
        this.numTasks = 0;
    }

    public void addToList(String taskName) {
        Task task = new Task(taskName);
        this.list[numTasks] = task;
        numTasks++;
        System.out.println("added: " + taskName);
    }

    public void viewList() {
        if (numTasks == 0) {
            System.out.println("Nice, nothing to see here");
        } else {
            System.out.println("Quite a few tasks you got there");
            for (int i = 0; i < numTasks; ++i) {
                System.out.printf("%3d.[%s] %s\n", i + 1, list[i].getStatusIcon(), list[i].description);
            }
        }
    }

    public void markAsDone(int index) {
        list[index].markAsDone();
        System.out.println("Well, that's one task down");
        System.out.printf("   [%s] %s\n", list[index].getStatusIcon(), list[index].description);
    }
}
