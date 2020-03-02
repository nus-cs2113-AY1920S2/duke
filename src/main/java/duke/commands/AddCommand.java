package duke.commands;

import duke.asset.IllegalDukeException;
import duke.asset.Storage;
import duke.asset.Ui;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import duke.parser.*;
/**
 * This is the sub class of the Command class in Duke.<br>
 * This class adds tasks into the ArrayList based on User input.<br>
 *
 */
public class AddCommand extends Command   {
    private String action;
    private String timing;
    private Task task;
    /**
     * This is the constructor that creates a new AddCommand class.<br>
     * @param fullCommand This is the input entered by User that has already<br>
     *                    been split into an array of Strings.<br>
     * @throws IllegalDukeException if the command entered is not supported by the software.<br>
     */
    public AddCommand(String[] fullCommand) throws IllegalDukeException {
        super(fullCommand);
        String [] temp = Parser.getTaskInfo(fullCommand);
        this.action=temp[0];
        this.timing=temp[1];
    }
    /**
     * This method adds a Task.<br>
     * @param l1 This is the list of currently available Tasks.<br>
     */
    public void addTodo(ArrayList<Task> l1){
        Todo todo = new Todo(this.action);
        l1.add(todo);
        this.task=todo;
    }
    /**
     * This method adds a Task of type Deadline.<br>
     * @param l1 This is the list of currently available Tasks.<br>
     */
    public void addDeadline(ArrayList<Task> l1){
        Deadline deadline= new Deadline(this.action);
        deadline.setBy(this.timing);
        l1.add(deadline);
        this.task=deadline;
    }
    /**
     * This method adds a Task of type Event.<br>
     * @param l1 This is the list of currently available Tasks.<br>
     */
    public void addEvent(ArrayList<Task> l1){
        Event event = new Event(this.action);
        event.setAt(this.timing);
        l1.add(event);
        this.task=event;
    }

    @Override
    public void execute(ArrayList<Task> l1, Ui ui, Storage storage) throws FileNotFoundException {
        switch(this.fullCommand[0]){
            case "todo" :
                addTodo(l1);
                break;
            case "deadline" :
                addDeadline(l1);
                break;
            case "event" :
                addEvent(l1);
                break;
        }
        ui.printAddTask(l1, this.task);
        storage.saveFile(l1);
        return;
    }

}

