public class TaskManager {
    //Assume no more than 100 tasks given
    private Task[] taskList;
    private static int taskCount;

    public TaskManager() {
        this.taskList = new Task[100];
        this.taskCount = 0;
    }

    public void addNewTask(String taskName) {
        Task newTask = new Task(taskName);
        taskList[taskCount] = newTask;
        taskCount += 1;
        System.out.println("added: " + taskName);
    }

    public void printTasksAdded() {
        if (taskCount == 0) {
            System.out.println("No tasks added yet");
        } else {
            for (int counter = 0; counter < taskCount; counter += 1) {
                //for correct indentation with the other outputs
                if (counter != 0) System.out.print("      ");
                System.out.printf("%d. [%s] %s \n", counter+1,
                        taskList[counter].getStatusIcon(), taskList[counter].getName());
            }
        }
    }

    public void markTaskCompleted(int index) {
        taskList[index].setIsCompleted();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("      [✓] " + taskList[index].getName());
    }

}
