import java.io.File;
import java.util.List;

public class DoneCommand extends Command {

    public void execute(Storage myTasks, File saveFile, List<String> commands, String command) {
        try {
            String indexAsString = commands.get(LIST_INDEX);
            indexAsString = indexAsString.trim();

            int index = Integer.parseInt(indexAsString);
            Task taskDone = myTasks.getTask(index);
            taskDone.markAsDone(); //TODO QUESTION: How come.. this works?.. is it because its static?

            FileSaver.updateFile(saveFile, index);

            Printer.printConfirmationMessage(command, taskDone);

        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            Printer.printError(); //TODO add custom error message when accessing OFB index
        }
    }
}
