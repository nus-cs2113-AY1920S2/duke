import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList tasks;
    private Parser parser;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();

        try {
            tasks = new TaskList(storage.loadFromFile());
        } catch (IOException e) {
             System.out.println(e);
        }

    }

    public void run() throws IOException {
        ui.printWelcomeMessage();
        storage.loadFromFile();
        Scanner in = new Scanner(System.in);
        String input = in.nextLine(); // Get string input

        while (!input.equals("bye")) {
            String[] words = input.split(" ", 2); // Split command from rest of sentence
            try {
                storage.writeToFile(tasks.getTaskList());
                manageCommand(input, words);
            } catch (InvalidTaskIndexException e) {
                System.out.println("\t ☹ OOPS!!! The task index you've entered is invalid.");
            } catch (EmptyTaskException e) {
                System.out.println("\t ☹ OOPS!!! The description of the task cannot be empty.");
            } catch (InvalidCommandException e) {
                System.out.println("\t ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                System.out.println("\t Type 'help' for the list of commands available.");
            } catch (InvalidDeadlineException e) {
                System.out.println("\t ☹ OOPS!!! The description of a deadline must be in this format: ");
                System.out.println("\t deadline <task name> /by <date/time>");
            } catch (InvalidEventException e) {
                System.out.println("\t ☹ OOPS!!! The description of an event must be in this format: ");
                System.out.println("\t event <task name> /at <date/time>");
            } catch (IOException e) {
                System.out.println("\t ☹ OOPS!!! File is not found!");
            } catch (EmptyListException e) {
                System.out.println("\t ☹ OOPS!!! Your task list is empty! Try adding a task first!");
            } catch (NumberFormatException e) {
                System.out.println("\t ☹ OOPS!!! You entered an invalid number!");
            }
            input = in.nextLine(); // Get string input
        }
        ui.printExitMessage(); // Exit
    }

    public static void main(String[] args) throws IOException {
        new Duke("data/duke.txt").run();
    }

    private void manageCommand(String input, String[] words) throws NumberFormatException, EmptyTaskException, EmptyListException, InvalidTaskIndexException, InvalidCommandException, InvalidDeadlineException, InvalidEventException {
        ArrayList<Task> taskList = tasks.getTaskList();
        if (words[0].equals("help")) {
            ui.printHelpMessage();
        } else if (words[0].equals("list")) { // List tasks
            tasks.listCommand();
            ui.printListMessage(taskList);
        } else if (words[0].equals("done")) { // Mark task as done
            parser.parseDone(words);
            int taskListSize = tasks.getTaskListSize();
            int taskIndex = parser.parseTaskIndex(words, taskListSize);
            tasks.markDone(taskIndex);
            ui.printDoneMessage(taskList, taskIndex);
        } else if (words[0].equals("deadline")) { // Deadline
            String[] deadlineWords = parser.parseDeadline(words);
            tasks.addDeadline(deadlineWords);
            ui.printBorder();
            ui.printTaskAdded(taskList);
            ui.printListCount(taskList);
            ui.printBorder();
        } else if (words[0].equals("event")) { // Event
            String[] eventWords = parser.parseEvent(words);
            tasks.addEvent(eventWords);
            ui.printBorder();
            ui.printTaskAdded(taskList);
            ui.printListCount(taskList);
            ui.printBorder();
        } else if (words[0].equals("todo")) { // ToDo
            String toDoTask = parser.parseToDo(words);
            tasks.addToDo(toDoTask);
            ui.printBorder();
            ui.printTaskAdded(taskList);
            ui.printListCount(taskList);
            ui.printBorder();
        } else if (words[0].equals("delete")) {
            parser.parseDelete(words);
            int taskListSize = tasks.getTaskListSize();
            int taskIndex = parser.parseTaskIndex(words, taskListSize);
            ui.printBorder();
            ui.printDeleteMessage(taskList, taskIndex);
            tasks.deleteTask(taskIndex);
            ui.printListCount(taskList);
            ui.printBorder();
        } else {
            throw new InvalidCommandException();
        }
    }

}