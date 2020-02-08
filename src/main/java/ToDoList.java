public class ToDoList {
    // assume list holds a maximum of 100 tasks
    private Task[] list;
    private int numTasks;

    ToDoList() {
        this.list = new Task[100];
        this.numTasks = 0;
    }

    public void addToList(String input) {
        // input follows format <taskType> <taskName> /<date>
        int spaceIndex = input.indexOf(" ");

        String taskTypeLC = input.substring(0, spaceIndex).trim().toLowerCase();
        String taskName = null;
        String date = null;
        Task task = null;
        if (taskTypeLC.equals("todo")) {
            taskName = input.substring(spaceIndex + 1).trim();
            task = new ToDos(taskName);
        } else {
            int slashIndex = input.indexOf(" /", spaceIndex);
            taskName = input.substring(spaceIndex + 1, slashIndex).trim();
            date = input.substring(slashIndex + 4).trim();
            if (taskTypeLC.equals("deadline")) {
                task = new Deadlines(taskName, date);
            } else {
                // taskType == event
                task = new Events(taskName, date);
            }
        }

        this.list[numTasks] = task;
        numTasks++;
        System.out.println("Task added: ");
        System.out.println("  " + task);
        System.out.println("You have " + numTasks + " task" + (numTasks == 1 ? "" : "s") + " in the list");
    }

    public void printList() {
        if (numTasks == 0) {
            System.out.println("Nice, nothing to see here");
        } else {
            System.out.println("Quite a few tasks you got there");
            for (int i = 0; i < numTasks; ++i) {
                System.out.printf("%3d. %s\n", i + 1, list[i]);
            }
        }
    }

    public void markAsDone(int index) {
        list[index].markAsDone();
        System.out.println("Well, that's one task down");
        System.out.println("  " + list[index]);
    }
}
