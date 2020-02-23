import Exceptions.MissingDescriptionException;
import Exceptions.UnknownCommandException;

import java.io.IOException;

public class Command {

    protected String instruction;
    protected String details;

    public Command(String instruction, String details) {
        this.instruction = instruction;
        this.details = details;
    }

    public void execute(Ui ui, TaskManager taskManager, StorageManager storageManager) throws MissingDescriptionException,
            UnknownCommandException{
        switch(instruction) {
        case "list":
            taskManager.printAllTasks(taskManager.getTasks(),"");
            break;
        case "done":
            if (details.isEmpty()) {
                throw new MissingDescriptionException();
            }
            int taskNum = Integer.parseInt(details);
            taskManager.markTaskAsDone(taskNum);
            break;
        case "todo":
            if (details.isEmpty()) {
                throw new MissingDescriptionException();
            }
            taskManager.addTaskToArrayList(instruction, details, "");
            try {
                storageManager.addTaskToFile(taskManager.getLastTask().stringToSave());
            } catch (IOException e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }
            break;
        case "deadline":
            if (details.isEmpty()) {
                throw new MissingDescriptionException();
            }
            taskManager.addTaskToArrayList(instruction, details, "/by");
            try {
                storageManager.addTaskToFile(taskManager.getLastTask().stringToSave());
            } catch (IOException e) {
                ui.printGeneralErrorMessage(e.getMessage());
            }
            break;
        case "event":
            if (details.isEmpty()) {
                throw new MissingDescriptionException();
            }
            taskManager.addTaskToArrayList(instruction, details, "/at");
            try {
                storageManager.addTaskToFile(taskManager.getLastTask().stringToSave());
            } catch (IOException e) {
                ui.printGeneralErrorMessage(e.getMessage());
            }
            break;
        case "delete":
            if (details.isEmpty()) {
                throw new MissingDescriptionException();
            }
            taskNum = Integer.parseInt(details);
            taskManager.deleteTask(taskNum);
            try {
                storageManager.saveToFile(taskManager.getTasks());
            } catch (IOException e) {
                System.out.println("Something went wrong");
            }
            break;
        case "find":
            taskManager.findTasks(details);
            break;
        default:
            throw new UnknownCommandException();
        }
    }
}
