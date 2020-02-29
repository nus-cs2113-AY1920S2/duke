package duke;

import duke.commands.Command;
import duke.exceptions.*;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.UI;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Duke {

    public static void main(String[] args) {

        TaskList tasklist = new TaskList();
        UI ui = new UI();
        Storage storage = new Storage();

        ui.displayWelcomeMessage();

        try {
            File dukeData = new File("data" + File.separator + "duke.txt");
            if (dukeData.createNewFile()) {
                System.out.println("File created: " + dukeData.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
/*
        try {
            FileWriter myWriter = new FileWriter("filename.txt");
            myWriter.write("Files in Java might be tricky, but it is fun enough!");
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
*/

        while (!ui.isExitStatus()) {
            try {
                String input = ui.readInput();
                Command command = Parser.parseInput(input);
                command.execute(tasklist, ui, storage);
            } catch (InvalidCommandException e) {
                ui.displayInvalidCommandMessage();
            } catch (InvalidDeadlineException e) {
                ui.displayInvalidDeadlineMessage();
            } catch (InvalidEventException e) {
                ui.displayInvalidEventMessage();
            } catch (InvalidToDoException e) {
                ui.displayInvalidToDoMessage();
            } catch (InvalidFormatException e) {
                ui.displayInvalidFormatMessage();
            } catch (IndexOutOfBoundsException e) {
                ui.displayInvalidIndexMessage();
            }
        }

    }

}
