import java.util.ArrayList;

public class TaskList {

    private Ui ui = new Ui();
    public ArrayList<Task> listOfTasks;

    public TaskList(ArrayList<Task> listOfTasks) {
        this.listOfTasks = listOfTasks;
    }

    public TaskList() {
        this.listOfTasks = new ArrayList<>();
    }

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

    private void showStoredTask() {
        int sizeOfList = listOfTasks.size();
        Task lastTask = listOfTasks.get(sizeOfList - 1);

        ui.printLine();
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + lastTask);

        System.out.println(" Now you have " + sizeOfList + " tasks in the list.");
        ui.printLine();
    }

    public void listAllTasks() {
        ui.printLine();
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < listOfTasks.size(); i += 1) {
            Task currTask = listOfTasks.get(i);
            System.out.println(" " + (i + 1) + ". " + currTask);

        }
        ui.printLine();
    }

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

    public void deleteTask(String line) {
        String[] splitLine = line.split(" ");
        int taskNumber = Integer.parseInt(splitLine[1]);
        Task taskToDelete = listOfTasks.get(taskNumber - 1);
        listOfTasks.remove(taskNumber - 1);
        int numOfTasksLeft = listOfTasks.size();

        ui.printLine();
        System.out.println("Noted. I've removed this task: ");
        System.out.println("  " + taskToDelete);
        System.out.println("Now you have " + numOfTasksLeft + " tasks in the list.");
        ui.printLine();
    }

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
