package Duke.Commands;

import Duke.Asset.Storage;
import Duke.Asset.Ui;
import Duke.Tasks.Task;
import java.util.ArrayList;
/**
 * This is a sub class of the Command class in Duke.
 *
 * This class finds any Task which description matches
 * the key entered by User.
 */

public class FindCommand extends Command {

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
