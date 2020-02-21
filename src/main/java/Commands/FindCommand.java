package Commands;

import Asset.Storage;
import Asset.Ui;
import Tasks.Task;
import java.util.ArrayList;

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
