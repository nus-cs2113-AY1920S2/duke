public class ToDoList {
    // assume list holds a maximum of 100 tasks
    private String[] list;
    private int numTasks;

    ToDoList() {
        this.list = new String[100];
        this.numTasks = 0;
    }

    public void addToList(String task) {
        this.list[numTasks] = task;
        numTasks++;
        System.out.println("added: " + task);
    }

    public void viewList() {
        for (int i = 0; i < numTasks; ++i) {
            System.out.printf("%d. %s\n", i + 1, this.list[i]);
        }
    }
}
