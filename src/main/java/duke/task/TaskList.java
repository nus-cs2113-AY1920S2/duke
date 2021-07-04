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
    protected Storage storage = new Storage(this);
    protected Ui ui;

    /**
     * Constructor for the TaskList class that declares new taskList, Ui, and
     * Storage objects.
     */
    public TaskList(){
        this.tasks = new ArrayList<>();
        this.ui = new Ui(this);
    }

    // Constructor that takes in loaded task message

    /**
     * Second Constructor for the TaskList class that takes in
     * an ArrayList of tasks from the loaded text file instead of
     * creating a new empty list.
     * @param taskList
     */
    public TaskList(ArrayList<Task> taskList){
        this.tasks = taskList;
        this.ui = new Ui(this);
    }

    /**
     * Returns an integer representation of the size/length of the taskList
     */
    public Integer size(){
        return tasks.size();
    }


    /**
     * Function that adds any type of task to the list.
     * It also saves the updated taskList to a text file
     * @param t Task t to be added to taskList
     */
    public void addTask(Task t){
        tasks.add(t);
        storage.save();
        ui.printTask(t);
    }

    /**
     * Function that creates a new todo task and
     * adds to the taskList
     * @param command decription for todo
     * @throws DukeException is the user does not give description
     */
    public void todo(String command) throws DukeException{
        if(command.isEmpty()){
            throw new DukeException();
        } else {
            Task t = new Todo(command);
            addTask(t);
        }
    }


    /**
     * Function that creates a new deadline task and
     * adds it to the taskList
     * @param command description for the task
     * @throws DukeException if no description is given.
     */
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

    /**
     * Function that creates a new event task and
     * adds it to the taskList
     * @param command description for the task
     * @throws DukeException if no description is given.
     */
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

    /**
     * Function that deletes a designated task from the taskList
     * @param command user response of which command to delete
     * @throws DukeException no response is given by user
     */
    public void delete(String command) throws DukeException {
        if (command.isEmpty()) {
            throw new DukeException();
        } else {
            int taskNum = Integer.parseInt(command) - 1;
            if(taskNum < 0 || taskNum > tasks.size()){
                throw new DukeException();
            } else {
                Task toDelete = tasks.get(taskNum);
                tasks.remove(toDelete);
                ui.printDeleted(toDelete);
                storage.save();
            }
        }
    }


    /**
     * Function that lists all the tasks is taskList
     */
    public void listTasks() {
        if(!tasks.isEmpty()){
            System.out.println("Here are the tasks in your list:");
            ui.printList(tasks);
        } else {
            ui.printEmptyList();
        }

    }


    /**
     * Function that marks a designated task as done in the taskList
     * @param command the response from the user that is used to choose which task is marked as done
     * @throws DukeException if no response is given
     */
    public void done(String command) throws DukeException{
        if(command.isEmpty()){
            throw new DukeException();
        } else {
            int taskNum = Integer.parseInt(command) - 1;
            if(taskNum < 0 || taskNum > tasks.size()){
                throw new DukeException();
            } else {
                Task markDone = tasks.get(taskNum);
                markDone.markAsDone();
                ui.printMarkedDone(markDone);
                storage.save();
            }
        }
    }


    /**
     * Function that finds a certain keyword within the taskList and prints out
     * the list where the keyword is found
     * @param command
     * @throws DukeException
     */
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
            if (!foundTasks.isEmpty()){
                System.out.println("Here are the matching tasks in your list:");
                ui.printList(foundTasks);
            } else {
                ui.printEmptyList();
            }
        }
    }

}
