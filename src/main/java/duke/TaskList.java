package duke;

import java.util.ArrayList;
import duke.excpetions.EmptyDescriptionException;
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

    public void deleteTask(String command) throws EmptyDescriptionException{
        if(command.indexOf(" ")==-1) {
            System.out.println("☹ OOPS!!! Please specify the index of the task you want to delete.");
            throw new EmptyDescriptionException();
        }
        int index = Integer.parseInt(command.split(" ")[1])-1;
        System.out.println("Noted. I've removed this task: ");
        System.out.println(tasks.remove(index));
        printNumOfTasks();
    }


    public void printNumOfTasks(){
        System.out.println("Now you have "+ tasks.size()+" tasks in the list.");
    }

    public void addEvent(String command) throws EmptyDescriptionException {
        if(command.indexOf(" ")==-1) {
            System.out.println("☹ OOPS!!! The description of an event cannot be empty.");
            throw new EmptyDescriptionException();
        }
        String description=command.substring(command.indexOf(" "),command.indexOf("/"));
        String period=command.substring(command.indexOf("/at")+4);
        Event newEvent = new Event(description, period);
        getTasks().add(newEvent);
        System.out.println("Got it. I've added this task:");
        System.out.println(newEvent);
        printNumOfTasks();
    }

    public void addDeadline(String command) throws EmptyDescriptionException {
        if(command.indexOf(" ")==-1) {
            System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
            throw new EmptyDescriptionException();
        }
        String description=command.substring(command.indexOf(" "),command.indexOf("/"));
        String by=command.substring(command.indexOf("/by")+4);
        Deadline newDeadline = new Deadline(description,by);
        getTasks().add(newDeadline);
        System.out.println("Got it. I've added this task:");
        System.out.println(newDeadline);
        printNumOfTasks();
    }

    public void addToDo  (String command) throws EmptyDescriptionException {
        if(command.indexOf(" ")==-1) {
            System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
            throw new EmptyDescriptionException();
        }
        String description=command.substring(command.indexOf(" "));
        ToDo newTodo = new ToDo(description);
        tasks.add(newTodo);
        System.out.println("Got it. I've added this task:");
        System.out.println(newTodo);
        printNumOfTasks();
    }

    public void doneTask(String command) {
        String[] splitCommand=command.split(" ");
        int taskIndex=Integer.parseInt(splitCommand[1])-1;
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
