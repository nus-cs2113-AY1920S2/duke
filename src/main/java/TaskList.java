import java.util.ArrayList;


/**
 * Represents a tasklist object with various methods that
 * helps Duke to keep track of the tasks details.
 */
public class TaskList {

    private Ui ui = new Ui();
    public ArrayList<Task> listOfTasks;

    /**
     * Constructor for the tasklist object.
     * @param listOfTasks an array list of tasks that will be used
     * to populate the listOfTasks.
     */
    public TaskList(ArrayList<Task> listOfTasks) {
        this.listOfTasks = listOfTasks;
    }

    /**
     * Constructor for the tasklist object.
     * Used to initialise an empty listOfTasks.
     */
    public TaskList() {
        this.listOfTasks = new ArrayList<>();
    }

    /**
     * Method used to store tasks into the listOfTasks.
     * Formats the task information into two components
     * (description and dateAndTime) before storing.
     * @param taskInformation full information about the task.
     * @param separator       used to format the taskInformation into its components.
     * @param command         differentiate between deadline and event tasks.
     */
    public void storeTaskIntoList(String taskInformation, String separator, String command) {
        int dividerPosition = taskInformation.indexOf(separator);
        String description = taskInformation.substring(0, dividerPosition);
        String dateAndTime = taskInformation.substring(dividerPosition + 5);
        if (command.equals("deadline")) {
            Deadline deadlineToAdd = new Deadline(description, dateAndTime);
            listOfTasks.add(deadlineToAdd);
        } else if (command.equals("event")) {
            Event eventToAdd = new Event(description, dateAndTime);
            listOfTasks.add(eventToAdd);
        }
    }

    /**
     * Method used to store tasks into the listOfTasks.
     * @param taskInformation full information about the task.
     * @param command         differentiate between the different type of tasks.
     */
    public void storeTaskIntoList(String taskInformation, String command) {
        switch (command) {
        case "todo":
            Todo todoToAdd = new Todo(taskInformation);
            listOfTasks.add(todoToAdd);
            break;
        case "deadline":
            storeTaskIntoList(taskInformation, " /by ", command);
            break;
        case "event":
            storeTaskIntoList(taskInformation, " /at ", command);
            break;
        }
        showStoredTask();
    }

    /**
     * Method used to print an acknowledgement message when
     * the user adds a task.
     */
    private void showStoredTask() {
        int sizeOfList = listOfTasks.size();
        Task lastTask = listOfTasks.get(sizeOfList - 1);

        ui.printLine();
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + lastTask);

        System.out.println(" Now you have " + sizeOfList + " tasks in the list.");
        ui.printLine();
    }

    /**
     * Method used to print all the tasks in the listOfTasks.
     */
    public void listAllTasks() {
        ui.printLine();
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < listOfTasks.size(); i += 1) {
            Task currTask = listOfTasks.get(i);
            System.out.println(" " + (i + 1) + ". " + currTask);

        }
        ui.printLine();
    }

    /**
     * Method used to print an acknowledgement message when
     * the user completes a task.
     * @param line a string that represents the task that is completed.
     */
    public void markTaskAsDone(String line) {
        int dividerPosition = line.indexOf("done");
        int taskNumber = Integer.parseInt(line.substring(dividerPosition + 5));
        Task taskDone = listOfTasks.get(taskNumber - 1);
        taskDone.markAsDone();
        ui.printLine();
        System.out.println(" Nice! I've marked this task as done:");
        System.out.println("   " + taskDone);
        ui.printLine();
    }

    /**
     * Method used to delete a particular task from the listOfTasks.
     * @param line a string that represents the task to be deleted.
     */
    public void deleteTask(String line) {
        String[] splitLine = line.split(" ");
        int taskNumber = Integer.parseInt(splitLine[1]);
        Task taskToDelete = listOfTasks.get(taskNumber - 1);
        listOfTasks.remove(taskNumber - 1);
        int numOfTasksLeft = listOfTasks.size();

        ui.printLine();
        System.out.println(" Noted. I've removed this task: ");
        System.out.println("   " + taskToDelete);
        System.out.println(" Now you have " + numOfTasksLeft + " tasks in the list.");
        ui.printLine();
    }

    /**
     * Method used to find a particular task from the listOfTasks.
     * @param line a string that contains a keyword that will be used to find the task.
     */
    public void findTask(String line) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        String[] splitLine = line.split(" ");
        String keyword = splitLine[1];
        for (int i = 0; i < listOfTasks.size(); i += 1) {
            String taskDescription = listOfTasks.get(i).toString();
            if (taskDescription.contains(keyword)) {
                matchingTasks.add(listOfTasks.get(i));
            }
        }
        ui.printLine();
        System.out.println(" Here are the matching tasks in your list:");
        for (int i = 0; i < matchingTasks.size(); i += 1) {
            Task currTask = matchingTasks.get(i);
            System.out.println(" " + (i + 1) + ". " + currTask);

        }
        ui.printLine();
    }
}
