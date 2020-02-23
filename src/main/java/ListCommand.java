import java.io.File;
import java.util.List;

public class ListCommand extends Command {

    public void execute(Storage myTasks, File saveFile, List<String> commands, String command) {
        myTasks.displayTasks();
    }
}
