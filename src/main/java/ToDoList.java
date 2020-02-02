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
        int slashIndex = input.indexOf(" /", spaceIndex);

        // split input into 3 main parts
        String taskName, date = null;
        String taskTypeLC = input.substring(0, spaceIndex).trim().toLowerCase();
        if (slashIndex != -1) {
            // -1 means that input does not contain a date
            taskName = input.substring(spaceIndex + 1, slashIndex).trim();
            date = input.substring(slashIndex + 4).trim();
        } else {
            taskName = input.substring(spaceIndex + 1).trim();
        }

        Task task;
        if (taskTypeLC.equals("todo")) {
            task = new ToDos(taskName);
        } else if (taskTypeLC.equals("deadline")) {
            task = new Deadlines(taskName, date);
        } else {
            // taskTypeLC == "event"
            task = new Events(taskName, date);
        }

        this.list[numTasks] = task;
        numTasks++;
        System.out.println("Task added: ");
        System.out.println("  " + task);
        System.out.println("You have " + numTasks + " task" + (numTasks == 1 ? "" : "s") + " in the list");
    }

    public void viewList() {
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
