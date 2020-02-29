package duke.commands;


import duke.tasks.Task;
import duke.asset.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
/**
 * This is the parent class of the Command class in Duke.<br>
 * This class stores a status which determines the continuation<br>
 * of the run() method in Duke.<br>
 * This class also stores the full command entered by User in the<br>
 * form of an array of Strings.<br>
 * This class does basic execution like listing available tasks<br>
 * and commands in Duke.<br>
 */
public class  Command  {
    protected int status;
    protected String[] fullCommand;
    public static final String WRONG_INPUT="\t OOPS!!! I'm sorry, but I don't know what that means. :(" +
            " Input command is wrong. Enter \"help\" for list of accepted commands";

    /**
     * This constructor creates a Command.<br>
     * @param fullCommand This is the input entered by user that has<br>
     *                    been split into an array of Strings.<br>
     */
    public Command(String[] fullCommand) {
        this.status = 1;
        this.fullCommand = fullCommand;
    }
    /**
     * This method executes the Command.<br>
     * @param l1 This is the list of currently available Tasks.<br>
     * @param ui This is the Ui class used in Duke.<br>
     * @param storage This is the Storage class used to store data in Duke.<br>
     * @throws IllegalDukeException if command entered by User is invalid.<br>
     * @throws FileNotFoundException if duke.txt does not exist<br>
     */
    public void execute(ArrayList<Task> l1, Ui ui, Storage storage) throws IllegalDukeException,
            FileNotFoundException {
        if(this.fullCommand[0].equals("help")){
            ui.printHelp();
        }else if(this.fullCommand[0].equals("list")){
            ui.printList(l1);
        }else{
            throw new IllegalDukeException(WRONG_INPUT);
        }
        storage.saveFile(l1);
        return;
    }

    /**
     * This method gets the status of the command.<br>
     * @return status of the Task. 1 means Command continues the program<br>
     *     -1 means the Command ends the program<br>.
     */
    public int getStatus(){
        return this.status;
    }

}
