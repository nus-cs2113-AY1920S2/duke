package duke.task;

import java.util.ArrayList;
import duke.ui.Ui;
import duke.Exception.DukeException;
import duke.storage.Storage;

public class TaskList {

    public static ArrayList<Task> tasks;
    protected Storage storage;
    protected Ui ui;


    public TaskList(){
        this.tasks = new ArrayList<>();
        this.ui = new Ui(this);
        this.storage = new Storage(this);
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
        ui.printLineSeparator();
        if(command.isEmpty()){
            throw new DukeException();
        } else {
            Task t = new Todo(command);
            addTask(t);
            ui.printLineSeparator();
        }
    }

    // Executes the deadline command
    public void deadline(String command) {
        ui.printLineSeparator();
        String task = command.split(" /by ")[0];
        String by = command.split(" /by ")[1];
        Task d = new Deadline(task, by);
        addTask(d);
        ui.printLineSeparator();
    }

    // Executes the event command
    public void event(String command){
        ui.printLineSeparator();
        String task = command.split(" /at ")[0];
        String at = command.split(" /at ")[1];
        Task e = new Event(task, at);
        addTask(e);
        ui.printLineSeparator();
    }

    // Executes the delete command
    public void delete(String command){
        ui.printLineSeparator();
        int taskNum = Integer.parseInt(command) - 1;
        System.out.println("Noted. I've removed this task:");
        System.out.println(tasks.get(taskNum));
        tasks.remove(tasks.get(taskNum));
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        storage.save();
        ui.printLineSeparator();
    }

   // Executes the list command
    public void listTasks() {
        ui.printLineSeparator();
        System.out.println("Here are the tasks in your list:");
        int taskCounter = 1;
        for(Task t: tasks) {
            System.out.println((taskCounter) + "." + t);
            taskCounter++;
        }
        ui.printLineSeparator();
    }

    // Executes the done command
    public void done(String command) {
        ui.printLineSeparator();
        int taskNum = Integer.parseInt(command) - 1;
        tasks.get(taskNum).markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks.get(taskNum));
        storage.save();
        ui.printLineSeparator();

    }

    // Executes the bye command
    public void bye(){
        ui.goodbye();
        System.exit(0);
    }

}
