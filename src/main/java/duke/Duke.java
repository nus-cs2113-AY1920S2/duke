package duke;

import duke.exceptions.ChatboxException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static duke.utils.Constants.LIST_COMMAND;
import static duke.utils.Constants.BYE_COMMAND;
import static duke.utils.Constants.DONE_COMMAND;
import static duke.utils.Constants.DELETE_COMMAND;
import static duke.utils.Constants.TODO_COMMAND;
import static duke.utils.Constants.DEADLINE_COMMAND;
import static duke.utils.Constants.EVENT_COMMAND;
import static duke.utils.Constants.DEADLINE_MARKER;
import static duke.utils.Constants.EVENT_MARKER;

public class Duke {
    // a array to store user input task
    private static List<Task> tasks = new ArrayList<>();
    
    public static void main(String[] args) {
        displayWelcomeMessage();
        
        Scanner in = new Scanner(System.in);
        String input = null;

        do {
            input = in.nextLine();
            String[] split = input.split("\\s+", 2); // limit: the number of segments after split
            String command = split[0];
            
            switch (command) {
            case LIST_COMMAND:
                listTasks();
                break;
            case DONE_COMMAND:
                try {
                    markAsDone(Integer.parseInt(split[1].trim()));
                } catch (ArrayIndexOutOfBoundsException e) {
                    displayEmptyDescriptionMessage(command);
                } catch (NumberFormatException e) {
                    displayInvalidTaskNumberMessage();
                } catch (ChatboxException e) {
                    displayInvalidTaskNumberMessage();
                }
                break;
            case DELETE_COMMAND:
                try {
                    deleteTask(Integer.parseInt(split[1].trim()));
                } catch (ArrayIndexOutOfBoundsException e) {
                    displayEmptyDescriptionMessage(command);
                } catch (NumberFormatException e) {
                    displayInvalidTaskNumberMessage();
                } catch (ChatboxException e) {
                    displayInvalidTaskNumberMessage();
                }
                break;
            case TODO_COMMAND:
                try {
                    addTodo(split[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    displayEmptyDescriptionMessage(command);
                }
                break;
            case DEADLINE_COMMAND:
                try {
                    addDeadline(split[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    displayEmptyDescriptionMessage(command);
                } catch (ChatboxException e) {
                    displayTimeMissingMessage();
                }
                break;
            case EVENT_COMMAND:
                try {
                    addEvent(split[1]);
                } catch (ArrayIndexOutOfBoundsException e) {
                    displayEmptyDescriptionMessage(command);
                } catch (ChatboxException e) {
                    displayTimeMissingMessage();
                }
                break;
            case BYE_COMMAND:
                displayExitMessage();
                break;
            default:
                displayCommandNotFoundMessage();
                break;
            }
        } while (!input.equals(BYE_COMMAND));
    }
    
    private static void displayWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?\n");
    }

    private static void listTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            System.out.print(String.format("%d.", i + 1));
            System.out.println(task);
        }
    }

    private static void markAsDone(int taskNumber) throws ChatboxException {
        if (taskNumber > tasks.size() || taskNumber <= 0) {
            throw new ChatboxException();
        }
        Task task = tasks.get(taskNumber - 1);
        task.markAsDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.print("  ");
        System.out.println(task);
    }
    
    private static void deleteTask(int taskNumber) throws ChatboxException {
        if (taskNumber > tasks.size() || taskNumber <= 0) {
            throw new ChatboxException();
        }
        
        System.out.println("Noted. I've removed this task: ");
        System.out.print("  ");
        System.out.println(tasks.get(taskNumber - 1));
        tasks.remove(taskNumber - 1);
        System.out.println(String.format("Now you have %d tasks in the list.", tasks.size()));
    }

    private static void displayInvalidTaskNumberMessage() {
        System.out.println("Please enter a valid task number~");
    }
    
    private static void addTodo(String description) {
        String taskDescription = description.trim();
        Task task = new Todo(taskDescription);
        tasks.add(task);
        displayAddTaskMessage(task);
    }
    
    private static void addDeadline(String description) throws ChatboxException {
        String[] taskBy = description.split(DEADLINE_MARKER);
        
        if (taskBy.length != 2) {
            throw new ChatboxException();
        }
        
        String taskDescription = taskBy[0].trim();
        String by = taskBy[1].trim();
        Task task = new Deadline(taskDescription, by);
        tasks.add(task);
        displayAddTaskMessage(task);
    }
    
    private static void addEvent(String description) throws ChatboxException {
        String[] taskAt = description.split(EVENT_MARKER);
        
        if (taskAt.length != 2) {
            throw new ChatboxException();
        }
        
        String taskDescription = taskAt[0].trim();
        String at = taskAt[1].trim();
        Task task = new Event(taskDescription, at);
        tasks.add(task);
        displayAddTaskMessage(task);
    }

    private static void displayAddTaskMessage(Task task) {
        System.out.println("Got it. I've added this task:");
        System.out.print("  ");
        System.out.println(task);
        System.out.println(String.format("Now you have %d tasks in the list.", tasks.size()));
    }

    private static void displayExitMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void displayCommandNotFoundMessage() {
        System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    private static void displayEmptyDescriptionMessage(String command) {
        System.out.println(String.format("OOPS!!! The description of a %s cannot be empty.", command));
    }

    private static void displayTimeMissingMessage() {
        System.out.println("Oops! Time is missing!");
    }
}
