import java.io.File;
import java.util.List;

public class DeleteCommand extends Command {

    public void execute(Storage myTasks, File saveFile, List<String> commands, String command) {
        try {
            String indexAsString = commands.get(LIST_INDEX);
            indexAsString = indexAsString.trim();

            int index = Integer.parseInt(indexAsString);
            Task taskDelete = myTasks.getTask(index);
            myTasks.deleteTask(index);

            FileSaver.deleteSpecificLine(saveFile, index);

            Printer.printConfirmationMessage(command, taskDelete);

        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            Printer.printError(); //TODO add custom error message when accessing OFB index
        }
    }
}
