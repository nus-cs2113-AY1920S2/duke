import java.util.Scanner;
import java.util.Vector;

public class Duke {

    private static Vector<Task> list = new Vector<>();
    private static Printer printer = new Printer();

    private boolean isValidDoneInput(String[] words) {
        if (words.length != 2) {
            return false;
        }
        // Checks if second word is a valid list number
        return words[1].matches("\\d+") && Integer.parseInt(words[1]) <= list.size();
    }

    private void doTask(String[] words) {
        if (!isValidDoneInput(words)) {
            return;
        }
        int listNumber = Integer.parseInt(words[1]) - 1;
        if (list.get(listNumber).isDone) {
            printer.printAlreadyCompletedTaskMessage(list, listNumber);
        } else {
            list.get(listNumber).isDone = true;
            printer.printCompleteTaskMessage(list, listNumber);
        }
    }

    private void addToDo(String input) {
        int indexOfTask = "todo ".length();
        String task = input.substring(indexOfTask);

        list.add(new ToDo(task));
        printer.printAddTaskMessage(list);
    }

    private void addDeadline(String input) {
        int indexOfTask = "deadline ".length();
        int endIndexOfTask = input.indexOf(" /by ");
        int indexOfDeadline = endIndexOfTask + " /by ".length();
        String task = input.substring(indexOfTask, endIndexOfTask);
        String deadline = input.substring(indexOfDeadline);

        list.add(new Deadline(task, deadline));
        printer.printAddTaskMessage(list);
    }

    private void addEvent(String input) {
        int indexOfTask = "event".length() + 1;
        int endIndexOfTask = input.indexOf(" /at ");
        int indexOfEvent = endIndexOfTask + " /at ".length();
        String task = input.substring(indexOfTask, endIndexOfTask);
        String duration = input.substring(indexOfEvent);

        list.add(new Event(task, duration));
        printer.printAddTaskMessage(list);
    }


    private void completeAction(String input) {
        String[] words = input.split(" ");

        switch (words[0].toLowerCase()) {
        case "done":
            doTask(words);
            break;
        case "todo":
            addToDo(input);
            break;
        case "deadline":
            addDeadline(input);
            break;
        case "event":
            addEvent(input);
            break;
        default:
            printer.printInvalidAction();
            break;
        }
    }

    private void readInput() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.toLowerCase().equals("bye")) {
                scanner.close();
                return;
            } else if (input.toLowerCase().equals("list")) {
                printer.printList(list);
            } else {
                completeAction(input);
            }
        }
    }

    private void runChat() {
        printer.printWelcomeMessage();
        printer.printLoadMessage();

        readInput();

        printer.printExitMessage();
    }

    public static void main(String[] args) {
        Duke chatBot = new Duke();
        chatBot.runChat();
    }
}
