package duke.command;

import duke.Data;
import duke.DukeException;
import duke.Parser;
import duke.Ui;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Command {
    public boolean isActive = true;
    Data data;


    public Command(String url) throws FileNotFoundException {
        data = new Data(url);
    }

    public void commandInit() throws IOException, DukeException {
        try {
            Parser commandParser = new Parser();
            String input = commandParser.getCommand();
            String[] buffer = input.split(Ui.SPACE_SYMBOL);
            String command = buffer[0];
            switch (command) {
                case Ui.COMMAND_BYE:
                    byeCommand();
                    break;
                case Ui.COMMAND_LIST:
                    listCommand();
                    break;
                case Ui.COMMAND_DELETE:
                    deleteCommand(input);
                    break;
                case Ui.COMMAND_TODO:
                case Ui.COMMAND_EVENT:
                case Ui.COMMAND_DEADLINE:
                    addCommand(input);
                    break;
                case Ui.COMMAND_DONE:
                    markDoneCommand(input);
                    break;
                default:
                    System.out.println("Please specify a correct mode.");
                    break;
            }
        } catch(Exception e) {
            System.out.println(e);
        }

    }
    private void byeCommand() throws IOException {
        isActive = false;
        data.saveToFile();
    }

    private void addCommand(String input) {
        String[] buffer = input.split(Ui.SPACE_SYMBOL);
        String taskType = buffer[0];
        String[] subBuffer;
        switch (taskType) {
            case Ui.COMMAND_TODO:
                subBuffer = input.split(Ui.COMMAND_TODO + Ui.SPACE_SYMBOL);
                data.add(new Todo(subBuffer[1]));
                break;
            case Ui.COMMAND_DEADLINE:
                subBuffer = input.split(Ui.DEADLINE_SYMBOL);
                data.add(new Deadline(subBuffer[0].substring(9), subBuffer[1]));
                break;
            case Ui.COMMAND_EVENT:
                subBuffer = input.split(Ui.EVENT_SYMBOL);
                data.add(new Event(subBuffer[0].substring(6), subBuffer[1]));
                break;
            default:
                break;
        }
        Ui.printBreak();
        Ui.addTaskSuccesssful();
        System.out.println("     " + data.getLastTask());
        System.out.println("  Now you have " + data.getSize() + " tasks in the list.");
    }

    private void listCommand() {
        data.printList();
    }

    private void deleteCommand(String input) throws ArrayIndexOutOfBoundsException {
        try {
            String[] buffer = input.split(Ui.SPACE_SYMBOL);
            int index = Integer.parseInt(buffer[1]);
            Todo temp = data.removeItem(index);
            Ui.printBreak();
            Ui.deleteSuccessful();
            System.out.println("    " + temp);
            System.out.println("  Now you have " + data.getSize() + " tasks in the list.");
        } catch (Exception e) {
            System.out.println("Integer index doesn't exist");
        }

    }

    private void markDoneCommand(String input) throws DukeException {
        try {
            String[] buffer = input.split(Ui.SPACE_SYMBOL);
            int index = Integer.parseInt(buffer[1]);
            data.setDone(index);
            Ui.printBreak();
            Ui.markDoneSuccessful();
            data.printItem(index);
        } catch(Exception e) {
            throw new DukeException("Please specify an index in the range.");
        }
    }
}

