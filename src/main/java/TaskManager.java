import TaskObjects.Deadline;
import TaskObjects.Event;
import TaskObjects.Task;
import TaskObjects.Todo;

import java.util.ArrayList;

public class TaskManager {
    protected ArrayList<Task> Tasks;
    private Ui ui = new Ui();

    public TaskManager() {
        Tasks = new ArrayList<>();
    }

    public TaskManager(ArrayList<Task> Tasks) {
        this.Tasks = Tasks;
    }

    public ArrayList<Task> getTasks() {
        return Tasks;
    }

    /**
     * Returns the last task that was added to the arrayList
     * @return the last task added to the arrayList
     */
    public Task getLastTask(){
        if (Tasks.size() > 0) {
            return Tasks.get(Tasks.size() - 1);
        } else {
            return null;
        }
    }

    /**
     * Check if the task with specified task number exists. Returns true if exists and false
     * otherwise.
     * @param taskNum The index of the task in order starting from 1. May differ from index of arrayList by 1
     * @return True if task exists and false otherwise
     */
    private boolean existTask(int taskNum){
        return (taskNum <= Tasks.size()  && (taskNum > 0));
    }

    /**
     * Set the task with the specified task number as done
     * @param taskNum The index of the task in order starting from 1. May differ from index of arrayList by 1
     */
    public void markTaskAsDone(int taskNum){
        if (existTask(taskNum)) {
            System.out.println("Nice! I've marked this task as done: ");
            Tasks.get(taskNum - 1).setDone(true);
            System.out.println(Tasks.get(taskNum - 1));
        }else{
            ui.printTaskNotFoundMessage();
        }
    }

    /**
     * Remove the task with the specified task number from the arrayList
     * @param taskNum The index of the task in order starting from 1. May differ from index of arrayList by 1
     */
    public void deleteTask(int taskNum) {
        if (existTask(taskNum)) {
            Task tempTask = Tasks.get(taskNum - 1);
            Tasks.remove(taskNum - 1);
            System.out.println("Noted. I've removed this task: ");
            System.out.println(tempTask);
            printCurrentTaskCount();
        }else {
            ui.printTaskNotFoundMessage();
        }
    }

    public void findTasks(String keyword) {
        ArrayList<Task> copyTasks = new ArrayList<>();
        for (Task task : Tasks) {
            if(task.getDescription().contains(keyword)){
                copyTasks.add(task);
            }
        }
        if(copyTasks.size() > 0) {
            printAllTasks(copyTasks,"matching ");
        } else {
            System.out.println("Jan cannot find a match for the keyword: " + keyword);
        }
    }

    /**
     * Create a new task object and add object to arrayList
     * @param command identifier for action to be taken or task type
     * @param commandDescription details for the command that is to be executed
     * @param divider divider for additional details if any
     */
    public void addTaskToArrayList(String command, String commandDescription, String divider) {
        String[] taskDetails = commandDescription.split(divider);
        switch(command) {
        case "todo":
            Todo todo = new Todo(commandDescription);
            Tasks.add(todo);
            printSuccessfulAddTaskMessage();
            break;
        case "deadline":
            try {
                Deadline deadline = new Deadline(taskDetails[0], taskDetails[1]);
                Tasks.add(deadline);
                printSuccessfulAddTaskMessage();
            } catch (IndexOutOfBoundsException e) {
                ui.printIncorrectFormatMessage();
            }
            break;
        case "event":
            try {
                Event event = new Event(taskDetails[0], taskDetails[1]);
                Tasks.add(event);
                printSuccessfulAddTaskMessage();
            } catch (IndexOutOfBoundsException e) {
                ui.printIncorrectFormatMessage();
            }

            break;
        }
    }

    /**
     * Display all task object within the arrayList. Specify the type of list
     * it is, For example "matching" or "existing".
     * @param TasksToPrint ArrayList with Tasks to be printed
     * @param taskType Type of ArrayList to be printed
     */
    public void printAllTasks(ArrayList<Task> TasksToPrint, String taskType){
        System.out.println("Here are the " + taskType +"tasks in your list :");
        for (int i = 0; i < TasksToPrint.size(); i++) {
            System.out.println( i + 1 + ". " + TasksToPrint.get(i));
        }
    }

    /**
     * Display the number of tasks in the complete task arrayList
     */
    public void printCurrentTaskCount() {
        if (Tasks.size() < 2){
            System.out.println("Now you have " + Tasks.size() + " task in the list");
        } else {
            System.out.println("Now you have " + Tasks.size() + " tasks in the list");
        }
    }

    /**
     * Display message if task addition was successful without any exceptions
     */
    public void printSuccessfulAddTaskMessage() {
        Task task = Tasks.get(Tasks.size() - 1);
        System.out.println("Got it. I've added this task:\n " + task);
        printCurrentTaskCount();
    }
}
