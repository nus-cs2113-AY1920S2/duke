package duke;

import java.time.LocalDate;
import java.util.ArrayList;

import duke.command.AddCommand;
import duke.command.DeleteCommand;
import duke.command.ManageCommand;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * TaskList contains the task list and it can handle many operations on tasks
 */
public class TaskList {

    private ArrayList<Task> tasks = new ArrayList<Task>();

    public TaskList(ArrayList<Task> tasks){
        this.tasks=tasks;
    }

    public TaskList() {
    }

    /**
     * This methods finds out all the deadline tasks or events which are due or holds
     * on the date given by the manage command.
     *
     * @param manageCommand A "show" manage command which contains a specified date.
     */
    public void showOneDayTasks(ManageCommand manageCommand){
        LocalDate date = manageCommand.getDate();
        for(Task task:tasks){
            boolean isDeadline = (task.getCategory().equals("deadline"));
            boolean isEvent = (task.getCategory().equals("event"));
            if(isDeadline){
                Deadline d = (Deadline) task;
                LocalDate by = d.getBy().toLocalDate();
                if(date.equals(by)){
                    System.out.println(d);
                }
            }
            if(isEvent){
                Event e = (Event) task;
                LocalDate at = e.getPeriod().toLocalDate();
                if(date.equals(at)){
                    System.out.println(e);
                }
            }
        }
    }

    /**
     * Deletes a task from the current task list according to the index given by the command.
     *
     * @param deleteCommand A command which specifies the index of the task which is going to be deleted.
     */
    public void deleteTask(DeleteCommand deleteCommand) {
        int index = deleteCommand.getIndex()-1;
        if(index < tasks.size()){
            System.out.println("Noted. I've removed this task: ");
            System.out.println(tasks.remove(index));
            printNumOfTasks();
        }else{
            System.out.println("There is not a task No."+deleteCommand.getIndex());
        }
    }

    /**
     * The method prints out the number of the tasks in the task list.
     */
    public void printNumOfTasks(){
        System.out.println("Now you have "+ tasks.size()+" tasks in the list.");
    }

    /**
     * Adds an event to the task list according to the command.
     *
     * @param addCommand A command including the description and time of the event to be added
     */
    public void addEvent(AddCommand addCommand){
        Event newEvent = new Event(addCommand);
        getTasks().add(newEvent);
        System.out.println("Got it. I've added this task:");
        System.out.println(newEvent);
        printNumOfTasks();
    }

    /**
     * Adds an deadline to the task list according to the command.
     *
     * @param addCommand A command including the description and due date of the deadline to be added
     */
    public void addDeadline(AddCommand addCommand){
        Deadline newDeadline = new Deadline(addCommand);
        getTasks().add(newDeadline);
        System.out.println("Got it. I've added this task:");
        System.out.println(newDeadline);
        printNumOfTasks();
    }

    /**
     * Adds a todo task to the task list according to the command.
     *
     * @param addCommand A command including the description of the new todo task
     */
    public void addToDo  (AddCommand addCommand){
        ToDo newTodo = new ToDo(addCommand);
        tasks.add(newTodo);
        System.out.println("Got it. I've added this task:");
        System.out.println(newTodo);
        printNumOfTasks();
    }

    /**
     *Finds the specified task and mark it as done.
     *
     * @param manageCommand A command that specifies the index of the task which has been done.
     */
    public void doneTask(ManageCommand manageCommand) {
        int taskIndex=manageCommand.getIndex()-1;
        if(taskIndex < tasks.size()) {
            getTasks().get(taskIndex).markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("  ["+ getTasks().get(taskIndex).getStatusIcon() + "] " + getTasks().get(taskIndex).getDescription());
        }else{
            System.out.println("Sorry. There is no task No."+manageCommand.getIndex());
        }
    }

    /**
     * Lists out all the tasks in the list with details.
     */
    public void listTasks() {
        System.out.println("Here are the task(s) in your list:");
        for(int i = 0; i<tasks.size(); i++){
            if(getTasks().get(i)==null){
                break;
            }
            System.out.println((i+1)+". "+ getTasks().get(i));
        }
    }

    public void searchTasks(ManageCommand manageCommand){
        System.out.println("Here are the matching task(s) in your list:");
        String searchTerm = manageCommand.getKeywords();
        int index = 0;
        for(Task t : tasks){
            String description = t.getDescription();
            if (description.contains(searchTerm)){
                index++;
                System.out.print(index + ". ");
                System.out.println(t);
            }
        }
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
}
