package duke;

import java.util.ArrayList;

import duke.command.AddCommand;
import duke.command.DeleteCommand;
import duke.command.ManageCommand;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

public class TaskList {

    private ArrayList<Task> tasks = new ArrayList<Task>();

    public TaskList(ArrayList<Task> tasks){
        this.tasks=tasks;
    }

    public TaskList() {
    }

    public void deleteTask(DeleteCommand deleteCommand){
        int index = deleteCommand.getIndex()-1;
        System.out.println("Noted. I've removed this task: ");
        System.out.println(tasks.remove(index));
        printNumOfTasks();
    }


    public void printNumOfTasks(){
        System.out.println("Now you have "+ tasks.size()+" tasks in the list.");
    }

    public void addEvent(AddCommand addCommand){
        Event newEvent = new Event(addCommand);
        getTasks().add(newEvent);
        System.out.println("Got it. I've added this task:");
        System.out.println(newEvent);
        printNumOfTasks();
    }

    public void addDeadline(AddCommand addCommand){
        Deadline newDeadline = new Deadline(addCommand);
        getTasks().add(newDeadline);
        System.out.println("Got it. I've added this task:");
        System.out.println(newDeadline);
        printNumOfTasks();
    }

    public void addToDo  (AddCommand addCommand){
        ToDo newTodo = new ToDo(addCommand);
        tasks.add(newTodo);
        System.out.println("Got it. I've added this task:");
        System.out.println(newTodo);
        printNumOfTasks();
    }

    public void doneTask(ManageCommand manageCommand) {
        int taskIndex=manageCommand.getIndex()-1;
        if(taskIndex < tasks.size()) {
            getTasks().get(taskIndex).markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("  ["+ getTasks().get(taskIndex).getStatusIcon() + "] " + getTasks().get(taskIndex).description);
        }else{
            System.out.println("There is no task No."+(taskIndex+1));
        }
    }

    public void listTasks() {
        System.out.println("Here are the tasks in your list:");
        for(int i = 0; i<tasks.size(); i++){
            if(getTasks().get(i)==null){
                break;
            }
            System.out.println((i+1)+". "+ getTasks().get(i));
        }
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
}
