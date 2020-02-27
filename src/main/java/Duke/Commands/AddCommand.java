package Duke.Commands;

import Duke.Asset.IllegalDukeException;
import Duke.Asset.Storage;
import Duke.Asset.Ui;
import Duke.Tasks.Deadline;
import Duke.Tasks.Event;
import Duke.Tasks.Task;
import Duke.Tasks.Todo;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import Duke.Parser.*;
/**
 * This is the sub class of the Command class in Duke.
 *
 * This class adds tasks into the ArrayList based on User input.
 *
 */
public class AddCommand extends Command   {
    private String action;
    private String timing;
    private Task task;

    public AddCommand(String[] fullCommand) throws IllegalDukeException {
        super(fullCommand);
        String [] temp = Parser.getTaskInfo(fullCommand);
        this.action=temp[0];
        this.timing=temp[1];
    }

    public void addTodo(ArrayList<Task> l1){
        Todo todo = new Todo(this.action);
        l1.add(todo);
        this.task=todo;
    }

    public void addDeadline(ArrayList<Task> l1){
        Deadline deadline= new Deadline(this.action);
        deadline.setBy(this.timing);
        l1.add(deadline);
        this.task=deadline;
    }

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

