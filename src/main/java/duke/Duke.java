package duke;

import duke.task.*;
import duke.exception.*;

import static duke.exception.ExceptionMessage.*;
import static duke.format.Printer.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private static ArrayList<Task> list = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Duke chatBot = new Duke();
        try {
            chatBot.runChat();
        } catch (IOException e) {
            System.out.println(IO_ERROR_MESSAGE);
            printExitMessage();
        }
    }

    private void runChat() throws IOException {
        printWelcomeMessage();

        printLoadMessage();
        try {
            FileManager.loadTaskList(list);
        } catch (FileNotFoundException e) {
            System.out.println(FILE_NOT_FOUND_MESSAGE);

            // Create new task list file
            FileManager.createTaskListFile();
        } catch (CorruptedFileException | IndexOutOfBoundsException e) {
            System.out.println(CORRUPTED_FILE_MESSAGE);

            boolean canCreate = getConfirmation(PROMPT_VALID_CREATE_CONFIRMATION_MESSAGE);
            if (canCreate) {
                printCreateNewFileMessage();
            } else {
                printAbortCreateNewFileMessage();
                printExitMessage();
                return;
            }

        }

        printReadyMessage();

        readInput();

        scanner.close();
        printExitMessage();
    }

    private boolean getConfirmation(String validConfirmationMessage) {
        while (true) {
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("yes") || input.equals("y")) {
                return true;
            } else if (input.equals("no") || input.equals("n")) {
                return false;
            } else {
                System.out.println(validConfirmationMessage);
            }
        }
    }

    private void readInput() {
        while (true) {
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("bye")) {
                try {
                    FileManager.saveTaskList(list);
                    printSuccessfulSaveMessage();
                    printGoodbyeMessage();
                    return;
                } catch (IOException e) {
                    System.out.println(FILE_SAVE_ERROR_MESSAGE);

                    boolean canExit = getConfirmation(PROMPT_VALID_EXIT_CONFIRMATION_MESSAGE);
                    if (canExit) {
                        printUnsuccessfulSaveMessage();
                        printGoodbyeMessage();
                        return;
                    } else {
                        printAbortExitMessage();
                    }
                }
            } else if (input.equals("list")) {
                printList(list, true);
            } else {
                try {
                    completeAction(input);
                } catch (InvalidActionException e) {
                    System.out.println(INVALID_ACTION_MESSAGE);
                } catch (EmptyInputException e) {
                    System.out.println(EMPTY_INPUT_MESSAGE);
                }
            }
        }
    }


    private void completeAction(String input) throws InvalidActionException, EmptyInputException {
        String[] words = input.split(" ");
        String action = words[0].toLowerCase();

        switch (action) {
        case "done":
            try {
                doTask(words);
            } catch (InvalidFormatException e) {
                System.out.println(INVALID_DONE_FORMAT_MESSAGE);
            } catch (InvalidListNumberException e) {
                System.out.println(INVALID_LIST_NUMBER_MESSAGE);
                printList(list, false);
            } catch (NumberFormatException e) {
                System.out.println(ILLEGAL_LIST_NUMBER_MESSAGE);
            } catch (IndexOutOfBoundsException e) {
                System.out.println(MISSING_LIST_NUMBER_MESSAGE);
            }
            break;
        case "todo":
            try {
                addToDo(input);
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println(INVALID_TODO_FORMAT_MESSAGE);
            } catch (InvalidFormatException e) {
                System.out.println(MISSING_TODO_DESCRIPTION_MESSAGE);
            }
            break;
        case "deadline":
            try {
                addDeadline(input);
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println(INVALID_DEADLINE_FORMAT_MESSAGE);
            } catch (InvalidFormatException e) {
                System.out.println(MISSING_DEADLINE_DESCRIPTION_MESSAGE);
            }
            break;
        case "event":
            try {
                addEvent(input);
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println(INVALID_EVENT_FORMAT_MESSAGE);
            } catch (InvalidFormatException e) {
                System.out.println(MISSING_EVENT_DESCRIPTION_MESSAGE);
            }
            break;
        case "delete":
            try {
                deleteTask(words);
            } catch (InvalidFormatException e) {
                System.out.println(INVALID_DELETE_FORMAT_MESSAGE);
            } catch (InvalidListNumberException e) {
                System.out.println(INVALID_LIST_NUMBER_MESSAGE);
                printList(list, false);
            } catch (NumberFormatException e) {
                System.out.println(ILLEGAL_LIST_NUMBER_MESSAGE);
            } catch (IndexOutOfBoundsException e) {
                System.out.println(MISSING_LIST_NUMBER_MESSAGE);
            }
            break;
        case "":
            throw new EmptyInputException();
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
            printAlreadyCompletedTaskMessage(list, listNumber);
        } else {
            list.get(listNumber).setIsDone(true);
            printCompleteTaskMessage(list, listNumber);
        }
    }

    private void addToDo(String input) throws InvalidFormatException {
        int indexOfTask = "todo ".length();
        String task = input.substring(indexOfTask).trim();

        if (task.length() == 0) {
            throw new InvalidFormatException();
        }

        list.add(new ToDo(task));
        printAddTaskMessage(list);
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
        printAddTaskMessage(list);
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
        printAddTaskMessage(list);
    }

    private void deleteTask(String[] words) throws InvalidFormatException, InvalidListNumberException {
        if (words.length > 2) {
            throw new InvalidFormatException();
        }

        int listNumber = Integer.parseInt(words[1]) - 1; // 0-based indexing

        if (listNumber < 0 || listNumber >= list.size()) {
            throw new InvalidListNumberException();
        }

        printDeleteTaskConfirmationMessage(list, listNumber);
        boolean canDelete = getConfirmation(PROMPT_VALID_DELETE_CONFIRMATION_MESSAGE);

        if (canDelete) {
            printDeleteTaskMessage(list, listNumber);
            list.remove(listNumber);
        } else {
            printAbortDeleteMessage();
        }
    }

}
