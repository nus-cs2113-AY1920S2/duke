package duke.task;

import java.util.ArrayList;
import duke.ui.Ui;
import duke.Exception.DukeException;
import duke.storage.Storage;

/**
 * Represents the list of all Tasks and the commands the user execute on them. This class
 * represents all the operations the user can have on the list, e.g 'list' shows all the
 * tasks in the list.
 */
public class TaskList {

    public static ArrayList<Task> tasks;
    protected Storage storage;
    protected Ui ui;


    public TaskList(){
        this.tasks = new ArrayList<>();
        this.ui = new Ui(this);
        this.storage = new Storage(this);
    }

    // Constructor that takes in loaded task message
    public TaskList(ArrayList<Task> taskList){
        this.tasks = taskList;
    }

    //Helper function to get size of taskList
    public Integer size(){
        return tasks.size();
    }

    // Helper function that adds and saves task to list
    public void addTask(Task t){
        tasks.add(t);
        storage.save();
        ui.printTask(t);
    }

    // Executes the todo command
    public void todo(String command) throws DukeException{
        if(command.isEmpty()){
            throw new DukeException();
        } else {
            Task t = new Todo(command);
            addTask(t);
        }
    }

    // Executes the deadline command
    public void deadline(String command) throws DukeException{
        if(command.isEmpty()){
            throw new DukeException();
        } else {
            String task = command.split(" /by ")[0];
            String by = command.split(" /by ")[1];
            Task d = new Deadline(task, by);
            addTask(d);
        }
    }

    // Executes the event command
    public void event(String command) throws DukeException{
        if(command.isEmpty()){
            throw new DukeException();
        } else {
            String task = command.split(" /at ")[0];
            String at = command.split(" /at ")[1];
            Task e = new Event(task, at);
            addTask(e);
        }
    }

    // Executes the delete command
    public void delete(String command) throws DukeException {
        if (command.isEmpty()) {
            throw new DukeException();
        } else {
            int taskNum = Integer.parseInt(command) - 1;
            System.out.println("Noted. I've removed this task:");
            System.out.println(tasks.get(taskNum));
            tasks.remove(tasks.get(taskNum));
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            storage.save();
        }
    }

   // Executes the list command
    public void listTasks() {
        System.out.println("Here are the tasks in your list:");
        int taskCounter = 1;
        for(Task t: tasks) {
            System.out.println((taskCounter) + "." + t);
            taskCounter++;
        }
    }

    // Executes the done command
    public void done(String command) throws DukeException{
        if(command.isEmpty()){
            throw new DukeException();
        } else {
            int taskNum = Integer.parseInt(command) - 1;
            tasks.get(taskNum).markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(tasks.get(taskNum));
            storage.save();
        }

    }

    // Executes the find command
    public void find(String command) throws DukeException {
        if (command.isEmpty()) {
            throw new DukeException();
        } else {
            ArrayList<Task> foundTasks = new ArrayList<>();
            String search = command.trim().toLowerCase();
            for (Task t : tasks) {
                String taskDescription = t.description.trim().toLowerCase();
                if (taskDescription.contains(search)) {
                    foundTasks.add(t);
                }
            }
            System.out.println("Here are the matching tasks in your list:");
            int taskNum = 1;
            for (Task t : foundTasks) {
                System.out.println((taskNum) + "." + t);
                taskNum++;
            }
        }
    }

}
