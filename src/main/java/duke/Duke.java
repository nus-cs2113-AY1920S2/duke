package duke;

import duke.exception.ExceptionMessage;
import duke.exception.InvalidActionException;
import duke.exception.InvalidFormatException;
import duke.exception.InvalidListNumberException;
import duke.format.Printer;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

public class Duke {

    private static Vector<Task> list = new Vector<>();
    Scanner scanner = new Scanner(System.in);
    private static final String TASK_LIST_PATH = "./data/taskList.txt";
    //private static final String DATA_DIRECTORY_PATH = "../../../../../data";

    private void doTask(String[] words) throws InvalidFormatException, InvalidListNumberException {
        if (words.length > 2) {
            throw new InvalidFormatException();
        }

        int listNumber = Integer.parseInt(words[1]) - 1;

        if (listNumber < 0 || listNumber >= list.size()) {
            throw new InvalidListNumberException();
        }

        if (list.get(listNumber).getIsDone()) {
            Printer.printAlreadyCompletedTaskMessage(list, listNumber);
        } else {
            list.get(listNumber).setIsDone(true);
            Printer.printCompleteTaskMessage(list, listNumber);
        }
    }

    private void addToDo(String input) throws InvalidFormatException {
        int indexOfTask = "todo ".length();
        String task = input.substring(indexOfTask).trim();

        if (task.length() == 0) {
            throw new InvalidFormatException();
        }

        list.add(new ToDo(task));
        Printer.printAddTaskMessage(list);
    }

    private void addDeadline(String input) throws InvalidFormatException {
        int indexOfTask = "deadline ".length();
        int endIndexOfTask = input.indexOf(" /by ");
        int indexOfDeadline = endIndexOfTask + " /by ".length();
        String task = input.substring(indexOfTask, endIndexOfTask).trim();
        String deadline = input.substring(indexOfDeadline).trim();

        if (task.length() == 0 || deadline.length() == 0) {
            throw new InvalidFormatException();
        }

        list.add(new Deadline(task, deadline));
        Printer.printAddTaskMessage(list);
    }

    private void addEvent(String input) throws InvalidFormatException {
        int indexOfTask = "event".length() + 1;
        int endIndexOfTask = input.indexOf(" /at ");
        int indexOfEvent = endIndexOfTask + " /at ".length();
        String task = input.substring(indexOfTask, endIndexOfTask).trim();
        String duration = input.substring(indexOfEvent).trim();

        if (task.length() == 0 || duration.length() == 0) {
            throw new InvalidFormatException();
        }

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
            try {
                addToDo(input);
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println(ExceptionMessage.INVALID_TODO_FORMAT_MESSAGE);
            } catch (InvalidFormatException e) {
                System.out.println(ExceptionMessage.MISSING_TODO_DESCRIPTION_MESSAGE);
            }
            break;
        case "deadline":
            try {
                addDeadline(input);
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println(ExceptionMessage.INVALID_DEADLINE_FORMAT_MESSAGE);
            } catch (InvalidFormatException e) {
                System.out.println(ExceptionMessage.MISSING_DEADLINE_DESCRIPTION_MESSAGE);
            }
            break;
        case "event":
            try {
                addEvent(input);
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println(ExceptionMessage.INVALID_EVENT_FORMAT_MESSAGE);
            } catch (InvalidFormatException e) {
                System.out.println(ExceptionMessage.MISSING_EVENT_DESCRIPTION_MESSAGE);
            }
            break;
        default:
            throw new InvalidActionException();
        }
    }

    private void readInput() {
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

    private void runChat() throws IOException {
        Printer.printWelcomeMessage();

        Printer.printLoadMessage();
        try {
            loadTaskList(list);
        } catch (FileNotFoundException e) {
            File taskListFile = new File(TASK_LIST_PATH);
            taskListFile.getParentFile().mkdirs();
            taskListFile.createNewFile();

            System.out.println("No file exist");
        }

        Printer.printReadyMessage();

        readInput();

        Printer.printExitMessage();
    }

    private void loadTaskList(Vector<Task> list) throws FileNotFoundException {
        File taskListFile = new File(TASK_LIST_PATH);
        Scanner fileScanner = new Scanner(taskListFile);

        while (fileScanner.hasNextLine()) {
            System.out.println(fileScanner.hasNextLine());
        }
    }

    public static void main(String[] args) {
        Duke chatBot = new Duke();
        try {
            chatBot.runChat();
        } catch (IOException e) {
            System.out.println("An error has occurred!!\n Aborting LumiChat program...");
        }
    }
}
