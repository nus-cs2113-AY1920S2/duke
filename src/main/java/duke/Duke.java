package duke;

import duke.task.*;
import duke.exception.*;
import duke.format.Printer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private static ArrayList<Task> list = new ArrayList<>();
    private static final String TASK_LIST_PATH = "./data/taskList.txt";
    Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Duke chatBot = new Duke();
        try {
            chatBot.runChat();
        } catch (IOException e) {
            System.out.println(ExceptionMessage.IO_ERROR_MESSAGE);
        }
    }

    private void runChat() throws IOException {
        Printer.printWelcomeMessage();

        Printer.printLoadMessage();
        try {
            loadTaskList(list);
        } catch (FileNotFoundException e) {
            // Create new task list file
            File taskListFile = new File(TASK_LIST_PATH);
            taskListFile.getParentFile().mkdirs();
            taskListFile.createNewFile();

            System.out.println(ExceptionMessage.FILE_NOT_FOUND_MESSAGE);
        } catch (CorruptedFileException e) {
            System.out.println(ExceptionMessage.CORRUPTED_FILE_MESSAGE);
        }

        Printer.printReadyMessage();

        readInput();

        scanner.close();
        Printer.printExitMessage();
    }

    private void readInput() {
        while (true) {
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("bye")) {
                try {
                    saveTaskList(list);
                    Printer.printGoodbyeMessage();
                    Printer.printSuccessfulSaveMessage();
                    return;
                } catch (IOException e) {
                    System.out.println(ExceptionMessage.FILE_SAVE_ERROR_MESSAGE);
                }
            } else if (input.equals("list")) {
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
        case "delete":
            try {
                deleteTask(words);
            } catch (InvalidFormatException e) {
                System.out.println(ExceptionMessage.INVALID_DELETE_FORMAT_MESSAGE);
            } catch (InvalidListNumberException e) {
                System.out.println(ExceptionMessage.INVALID_LIST_NUMBER_MESSAGE);
                Printer.printList(list, false);
            } catch (NumberFormatException e) {
                System.out.println(ExceptionMessage.ILLEGAL_LIST_NUMBER_MESSAGE);
            } catch (IndexOutOfBoundsException e) {
                System.out.println(ExceptionMessage.MISSING_LIST_NUMBER_MESSAGE);
            }
            break;
        default:
            throw new InvalidActionException();
        }
    }

    private void doTask(String[] words) throws InvalidFormatException, InvalidListNumberException {
        if (words.length > 2) {
            throw new InvalidFormatException();
        }

        int listNumber = Integer.parseInt(words[1]) - 1; // 0-based indexing

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

    private void deleteTask(String[] words) throws InvalidFormatException, InvalidListNumberException {
        if (words.length > 2) {
            throw new InvalidFormatException();
        }

        int listNumber = Integer.parseInt(words[1]) - 1; // 0-based indexing

        if (listNumber < 0 || listNumber >= list.size()) {
            throw new InvalidListNumberException();
        }

        Printer.printDeleteTaskConfirmationMessage(list, listNumber);
        boolean isConfirmDelete = getDeleteConfirmation();

        if (isConfirmDelete) {
            Printer.printDeleteTaskMessage(list, listNumber);
            list.remove(listNumber);
        } else {
            Printer.printAbortDeleteMessage();
        }
    }

    private boolean getDeleteConfirmation() {
        while (true) {
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("yes") || input.equals("y")) {
                return true;
            } else if (input.equals("no") || input.equals("n")) {
                return false;
            } else {
                Printer.printPromptValidConfirmationMessage();
            }
        }
    }

    private void loadTaskList(ArrayList<Task> list) throws FileNotFoundException, CorruptedFileException {
        File taskListFile = new File(TASK_LIST_PATH);
        Scanner fileScanner = new Scanner(taskListFile);

        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine().trim();

            if (line.isEmpty()) {
                break;
            }

            String[] taskInformation = line.split("__");
            String taskType = taskInformation[0];
            String doneStatus = taskInformation[1];
            String taskDescription = taskInformation[2];
            String taskDetail = taskInformation[3];

            if (!doneStatus.equals("1") && !doneStatus.equals("0")) {
                throw new CorruptedFileException();
            }

            switch (taskType) {
            case "T":
                ToDo newToDoTask = new ToDo(taskDescription);
                newToDoTask.setIsDone(doneStatus.equals("1"));
                list.add(newToDoTask);
                break;
            case "D":
                Deadline newDeadlineTask = new Deadline(taskDescription, taskDetail);
                newDeadlineTask.setIsDone(doneStatus.equals("1"));
                list.add(newDeadlineTask);
                break;
            case "E":
                Event newEventTask = new Event(taskDescription, taskDetail);
                newEventTask.setIsDone(doneStatus.equals("1"));
                list.add(newEventTask);
                break;
            default:
                throw new CorruptedFileException();
            }
        }

        fileScanner.close();
    }

    private void saveTaskList(ArrayList<Task> list) throws IOException {
        // Ensure all directories are made first
        File taskListFile = new File(TASK_LIST_PATH);
        taskListFile.getParentFile().mkdirs();

        FileWriter writer = new FileWriter(TASK_LIST_PATH);

        for (Task task : list) {
            String taskType = task.getClass().getSimpleName().substring(0, 1);
            String doneStatus = task.getIsDone() ? "1" : "0";
            String taskDescription = task.getTask();
            String taskDetail = task.getDetails();

            String taskData = String.join("__", new String[]{taskType, doneStatus, taskDescription, taskDetail});
            writer.write(taskData + System.lineSeparator());
        }

        writer.close();
    }
}
