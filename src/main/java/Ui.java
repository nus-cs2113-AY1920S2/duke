public class Ui {
    private TaskList tasks;

    public Ui(TaskList tasks) {
        this.tasks = tasks;
    }

    public void showLine() {
        System.out.println("\t____________________________________________________________");
    }

    /**
     * Prints a welcome message to the specified person.
     * @param name the person to be welcomed
     */
    public void showWelcome(String name) {
        showLine();
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("\tHello from\n" + logo);

        showLine();
        System.out.println("\tHello! I'm " + name);
        System.out.println("\tWhat can I do for you?");
        showLine();
    }

    public void bye() {
        System.out.println("\tBye. Hope to see you again soon!");
    }

    public void list() {
        if (tasks.size() == 0) {
            System.out.println("\tThere are no tasks in your list.");
            System.out.println("\tTip: Try adding a task by typing `todo NAME`");
            return;
        }
        System.out.println("\tHere are the tasks in your list:");
        tasks.list();
    }

    public void todo(String description) {
        Task task = new ToDo(description);
        tasks.add(task);
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t  " + task);
        tasks.printSize();
    }

    public void deadline(String[] descriptionArgs) {
        String taskDescription = descriptionArgs[0];
        String taskDescriptionBy = descriptionArgs[1];
        Task task = new Deadline(taskDescription, taskDescriptionBy);
        tasks.add(task);
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t  " + task);
        tasks.printSize();
    }

    public void event(String[] descriptionArgs) {
        String taskDescription = descriptionArgs[0];
        String taskDescriptionAt = descriptionArgs[1];
        Task task = new Event(taskDescription, taskDescriptionAt);
        tasks.add(task);
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t  " + task);
        tasks.printSize();
    }

    public void done(int index) {
        Task taskToBeMarkedAsDone = tasks.getByIndex(index);
        if (taskToBeMarkedAsDone.getDoneInBoolean() == true) {
            System.out.println("\tThis task has already been marked as done!");
        } else {
            tasks.setDoneByIndex(index);
            System.out.println("\tNice! I've marked this task as done:");
        }
        System.out.println("\t  " + taskToBeMarkedAsDone);
    }

    public void delete(int index) {
        Task removedTask = tasks.getByIndex(index);
        tasks.removeByIndex(index);
        System.out.println("\tNoted. I've removed this task:");
        System.out.println("\t  " + removedTask);
        tasks.printSize();
    }

    public void find(String description) {
        System.out.println("\tHere are the matching tasks in your list:");
        int numberOfTasksFound = tasks.find(description);
        if (numberOfTasksFound == 0) {
            System.out.println("\tNo matching tasks found!");
        }
    }
}