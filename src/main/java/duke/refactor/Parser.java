package duke.refactor;

import duke.ui.Ui;
import duke.exception.InvalidCommandException;
import duke.exception.InvalidDateFormatException;
import duke.exception.NoDateException;
import duke.exception.NoDescException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskList;
import duke.task.Todo;

public class Parser {
    public static void parseCommand(String userCommand, String userParams, TaskList taskList, Storage storage) throws NoDescException, NoDateException, InvalidDateFormatException, InvalidCommandException {
        switch (userCommand) {
        case "todo":
            // Check that description exists
            if (userParams.trim().isEmpty()) {
                throw new NoDescException();
            }
            taskList.addTask(new Todo(userParams.trim()), storage);
            break;
        case "deadline":
            // Fallthrough
        case "event":
            int delimIndex = userParams.indexOf("/"); // duke.Duke uses / to define where the date starts

            // If String.indexOf returns -1, the character has not been found
            if (delimIndex == -1) {
                throw new InvalidDateFormatException();
            }

            String desc = userParams.substring(0, delimIndex); // Get description substring (before /)
            String date = userParams.substring(delimIndex+1, userParams.length()); // Get date substring (after /)

            // Check that description and date exist
            if (desc.trim().isEmpty()) {
                throw new NoDescException();
            } else if (date.trim().isEmpty()) {
                throw new NoDateException();
            }
            if (userCommand.equals("deadline")) {
                taskList.addTask(new Deadline(desc.trim(), date.trim()), storage);
            } else {
                taskList.addTask(new Event(desc.trim(), date.trim()), storage);
            }
            break;
        case "done":
            // Fallthrough
        case "delete":
            String stringId = userParams.replaceAll("[^0-9]", ""); // Extract numeric characters
            int taskId = Integer.parseInt(stringId) - 1;
            if (userCommand.equals("done")) {
                taskList.setDone(taskId, storage);
            } else {
                taskList.deleteTask(taskId, storage);
            }
            break;
        case "list":
            Ui.printList(taskList);
            break;
        default:
            throw new InvalidCommandException();
            // Note: break statement not needed here because the exception is thrown by default, which stops flow
        }
    }

}
