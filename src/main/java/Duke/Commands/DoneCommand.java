package Duke.Commands;


import Duke.Asset.IllegalDukeException;
import Duke.Asset.Storage;
import Duke.Asset.Ui;
import Duke.Tasks.Task;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import static java.lang.Integer.parseInt;
/**
 * This is a sub class of the Command class in Duke.
 *
 * This class marks a Task as done based on the task
 * number stated by the User.
 */
public class DoneCommand extends Command  {
    private int index=0 ;
    boolean isAll= false;
    public static final String INDEX_OUT_OF_RANGE = "\t Task number provided is not valid. Press \"list\" to see\n" +
            "\t available list of task numbers";

    public DoneCommand(String[] fullCommand){
        super(fullCommand);
        if(fullCommand[1].equals("all")){
            this.isAll= true;
        }else {
            this.index = parseInt(fullCommand[1]) - 1;
        }
    }
    @Override
    public void execute(ArrayList<Task> l1, Ui ui, Storage storage) throws IllegalDukeException,
            FileNotFoundException {
            if (l1.isEmpty()) {
                ui.printList(l1);
            } else {
                if (this.isAll) {
                    for (int i = 0; i < l1.size(); i++) {
                        Task task = l1.get(i);
                        task.done();
                    }
                    ui.printDoneAll(l1);
                } else {
                    if (this.index >= l1.size()) {
                        throw new IllegalDukeException(INDEX_OUT_OF_RANGE);
                    } else {
                        Task task = l1.get(this.index);
                        task.done();
                        ui.printDone(task);
                    }
                }
            }
        storage.saveFile(l1);
    }
}
