package Commands;


import Tasks.Task;
import Asset.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;

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
