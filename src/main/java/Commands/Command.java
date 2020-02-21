package Commands;


import Tasks.Task;
import Asset.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
/**
 * This is the parent class of the Command class in Duke.
 *
 * This class stores a status which determines the continuation
 * of the run() method in Duke.
 *
 * This class also stores the full command entered by User in the
 * form of an array of Strings.
 *
 * This class does basic execution like listing available tasks
 * and commands in Duke.
 */
public class  Command  {
    protected int status;
    protected String[] fullCommand;
    public static final String OUT_OF_BOUND_INDEX = "\t Task number provided is not valid. Press \"list\" to see\n" +
            "\t available list of task numbers";

    public Command(String[] fullCommand) {
        this.status = 1;
        this.fullCommand = fullCommand;
    }
    public void execute(ArrayList<Task> l1, Ui ui, Storage storage) throws IllegalDukeException,
            FileNotFoundException {
        if(this.fullCommand[0].equals("help")){
            ui.printHelp();
        }else if(this.fullCommand[0].equals("list")){
            ui.printList(l1);
        }
        return;
    }
    public int getStatus(){
        return this.status;
    }

}
