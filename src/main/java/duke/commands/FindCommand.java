package duke.commands;

import duke.asset.Storage;
import duke.asset.Ui;
import duke.tasks.Task;
import java.util.ArrayList;
/**
 * This is a sub class of the Command class in Duke.<br>
 * This class finds any Task which description matches<br>
 * the key entered by User.<br>
 */

public class FindCommand extends Command {
    /**
     * This constructor creates a FindCommand.<br>
     * @param fullCommand This is the input entered by user that has<br>
     *                    been split into an array of Strings.<br>
     */
    public FindCommand(String[] fullCommand){
        super(fullCommand);
    }

    @Override
    public void execute(ArrayList<Task> l1, Ui ui, Storage storage){
        String key=this.fullCommand[1];
        ArrayList<Integer> l2= new ArrayList<>();
        for(Task task : l1){
            if(task.getAction().contains(key)){
                l2.add(l1.indexOf(task));
            }
        }
        ui.printFindList(l1, l2);
    }
}
