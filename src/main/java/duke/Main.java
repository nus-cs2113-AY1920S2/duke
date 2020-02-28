package duke;

import duke.commands.Command;
import duke.exceptions.BadLineFormatException;
import duke.exceptions.BadTaskChoiceFormatException;
import duke.parser.Parser;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.time.format.DateTimeFormatter;

public class Main {
    public static final String END_STRING = "bye";
    public static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("d/M/yyyy H:mm");
    public static final DateTimeFormatter DF = DateTimeFormatter.ofPattern("d/M/yyyy");
    private static final String FILE_PATH = "data/tasks.txt";
    private static TaskList taskList;

    public static void main(String[] args) {
        initialize();
        runLoop();
        Ui.sayGoodbye();
    }

    private static void initialize() {
        Ui.initialize();
        Ui.greet();
        taskList = new TaskList(FILE_PATH);
    }

    private static void runLoop() {
        String userInput = Ui.getNextLine();
        Command command;

        while (!userInput.toLowerCase().equals(END_STRING)) {
            try {
                command = Parser.parseUserInput(userInput, taskList);
                command.execute();
                if (command.getIsPersistentCommand()) {
                    taskList.writeTasksToFile();
                }
            } catch (BadLineFormatException | BadTaskChoiceFormatException e) {
                Ui.printPretty(e.getMessage());
            }

            userInput = Ui.getNextLine();
        }
    }
}
