import java.util.Scanner;
import java.util.Vector;

public class Duke {

    private static Vector<Task> list = new Vector<>();

    private void doTask(String[] words) throws InvalidFormatException, InvalidListNumberException {
        if (words.length > 2) {
            throw new InvalidFormatException();
        }

        int listNumber = Integer.parseInt(words[1]) - 1;

        if (listNumber < 1 || listNumber > list.size()) {
            throw new InvalidListNumberException();
        }

        if (list.get(listNumber).isDone) {
            Printer.printAlreadyCompletedTaskMessage(list, listNumber);
        } else {
            list.get(listNumber).isDone = true;
            Printer.printCompleteTaskMessage(list, listNumber);
        }
    }

    private void addToDo(String input) {
        int indexOfTask = "todo ".length();
        String task = input.substring(indexOfTask);

        list.add(new ToDo(task));
        Printer.printAddTaskMessage(list);
    }

    private void addDeadline(String input) {
        int indexOfTask = "deadline ".length();
        int endIndexOfTask = input.indexOf(" /by ");
        int indexOfDeadline = endIndexOfTask + " /by ".length();
        String task = input.substring(indexOfTask, endIndexOfTask);
        String deadline = input.substring(indexOfDeadline);

        list.add(new Deadline(task, deadline));
        Printer.printAddTaskMessage(list);
    }

    private void addEvent(String input) {
        int indexOfTask = "event".length() + 1;
        int endIndexOfTask = input.indexOf(" /at ");
        int indexOfEvent = endIndexOfTask + " /at ".length();
        String task = input.substring(indexOfTask, endIndexOfTask);
        String duration = input.substring(indexOfEvent);

        list.add(new Event(task, duration));
        Printer.printAddTaskMessage(list);
    }


    private void completeAction(String input) throws InvalidActionException {
        String[] words = input.split(" ");
        String action = words[0].toLowerCase();

        switch (action) {
        case "done":
            try {
                doTask(words);
            } catch (InvalidFormatException e) {
                System.out.println(ExceptionMessage.INVALID_DONE_FORMAT_MESSAGE);
            } catch (InvalidListNumberException e) {
                System.out.println(ExceptionMessage.INVALID_LIST_NUMBER_MESSAGE);
                Printer.printList(list, false);
            } catch (NumberFormatException e) {
                System.out.println(ExceptionMessage.ILLEGAL_LIST_NUMBER_MESSAGE);
            } catch (IndexOutOfBoundsException e) {
                System.out.println(ExceptionMessage.MISSING_LIST_NUMBER_MESSAGE);
            }
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
            throw new InvalidActionException();
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
                Printer.printList(list, true);
            } else {
                try {
                    completeAction(input);
                } catch (InvalidActionException e) {
                    System.out.println(ExceptionMessage.INVALID_ACTION_MESSAGE);
                }
            }
        }
    }

    private void runChat() {
        Printer.printWelcomeMessage();
        Printer.printLoadMessage();

        readInput();

        Printer.printExitMessage();
    }

    public static void main(String[] args) {
        Duke chatBot = new Duke();
        chatBot.runChat();

    }
}
