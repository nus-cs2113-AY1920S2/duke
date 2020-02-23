import TaskObjects.Deadline;
import TaskObjects.Event;
import TaskObjects.Task;
import TaskObjects.Todo;

import java.util.ArrayList;

public class TaskManager {
    protected static ArrayList<Task> Tasks;
    private Ui ui = new Ui();

    public TaskManager() {
        Tasks = new ArrayList<>();
    }

    public TaskManager(ArrayList<Task> Tasks) {
        this.Tasks = Tasks;
    }

    public static ArrayList<Task> getTasks() {
        return Tasks;
    }

    public Task getLastTask(){
        if (Tasks.size() > 0) {
            return Tasks.get(Tasks.size() - 1);
        } else {
            return null;
        }
    }

    private boolean existTask(int taskNum){
        return (taskNum <= Tasks.size()  && (taskNum > 0));
    }

    public void markTaskAsDone(int taskNum){
        if (existTask(taskNum)) {
            System.out.println("Nice! I've marked this task as done: ");
            Tasks.get(taskNum - 1).setDone(true);
            System.out.println(Tasks.get(taskNum - 1));
        }else{
            ui.printTaskNotFoundMessage();
        }
    }

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

    public boolean addTaskToArrayList(String command, String commandDescription, String divider) {
        String[] taskDetails = commandDescription.split(divider);
        switch(command) {
        case "todo":
            Todo todo = new Todo(commandDescription);
            Tasks.add(todo);
            printSuccessfulAddTaskMessage();
            return true;
        case "deadline":
            try {
                Deadline deadline = new Deadline(taskDetails[0], taskDetails[1]);
                Tasks.add(deadline);
            } catch (IndexOutOfBoundsException e) {
                ui.printIncorrectFormatMessage();
            }
            printSuccessfulAddTaskMessage();
        case "event":
            try {
                Event event = new Event(taskDetails[0], taskDetails[1]);
                Tasks.add(event);
            } catch (IndexOutOfBoundsException e) {
                ui.printIncorrectFormatMessage();
            }
            printSuccessfulAddTaskMessage();
        }
        return false;
    }

    public void printAllTasks(ArrayList<Task> TasksToPrint, String taskType){
        System.out.println("Here are the " + taskType +"tasks in your list :");
        for (int i = 0; i < TasksToPrint.size(); i++) {
            System.out.println( i + 1 + ". " + TasksToPrint.get(i));
        }
    }

    public void printCurrentTaskCount() {
        if (Tasks.size() < 2){
            System.out.println("Now you have " + Tasks.size() + " task in the list");
        } else {
            System.out.println("Now you have " + Tasks.size() + " tasks in the list");
        }
    }

    public void printSuccessfulAddTaskMessage() {
        Task task = Tasks.get(Tasks.size() - 1);
        System.out.println("Got it. I've added this task:\n " + task);
        printCurrentTaskCount();
    }

    public void printNotSuccessfulAddTaskMessage(){
        System.out.println("Something went wrong. Please try again.");
    }

}
