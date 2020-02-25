package duke.manager;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.*;
import duke.ui.TextUi;

import java.io.IOException;

public class Manager {
    private static final String DIVIDER = "===================================================";
    private static final String SAD_FACE = "(╥_╥)";

    private static boolean isExit = false;

    public static void manager(String[] parseInput, String name, TaskList tasks, String command) {
        //String command = parseInput[0];
        //parseInput[1].isEmpty() for empty string, not empty spaces

        try {
            if (command.equalsIgnoreCase("done")) {
                try {
                    TextUi.printDoneTasks(tasks, parseInput[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println(String.format("%50s", "Please include task number " + SAD_FACE));
                    System.out.println(DIVIDER);
                }
            } else if (command.equalsIgnoreCase("list")) {
                tasks.list();
            } else if (command.equalsIgnoreCase("todo")) {
                tasks.addToDo(parseInput);
            } else if (command.equalsIgnoreCase("bye")) {
                try {
                    Storage.save(tasks);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                TextUi.printByeMessage(name);
                isExit = true;
            } else if (command.equalsIgnoreCase("event")) {
                try {
                    tasks.addEvent(parseInput[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    TextUi.printIncompleteInformation();
                }
            } else if (command.equalsIgnoreCase("deadline")) {
                try {
                    tasks.addDeadline(parseInput[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    TextUi.printIncompleteInformation();
                }
            } else if (command.equalsIgnoreCase("delete")) {
                try {
                    tasks.deleteTask(parseInput[1]);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(String.format("%50s", "Task not included in the list, please try again. " + SAD_FACE));
                    System.out.println(DIVIDER);
                } catch (NumberFormatException e) {
                    TextUi.printIncompleteInformation();
                }
            } else if (command.equalsIgnoreCase("find")) {
                try {
                    tasks.findTasks(parseInput[1]);
                } catch (IndexOutOfBoundsException e) {
                    TextUi.printIncompleteInformation();
                }
            } else {
                throw new DukeException();
            }
        } catch (DukeException e) {
            //catch invalid commands
            TextUi.printInvalidCommand();
        }
    }

    public static boolean isExit() {
        return isExit;
    }
}
