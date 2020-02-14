package duke;

import duke.exceptions.ChatboxException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static duke.utils.Constants.LIST_COMMAND;
import static duke.utils.Constants.BYE_COMMAND;
import static duke.utils.Constants.DONE_COMMAND;
import static duke.utils.Constants.TODO_COMMAND;
import static duke.utils.Constants.DEADLINE_COMMAND;
import static duke.utils.Constants.EVENT_COMMAND;
import static duke.utils.Constants.DEADLINE_MARKER;
import static duke.utils.Constants.EVENT_MARKER;

public class Duke {
    // a array to store user input task
    private static List<Task> tasks = new ArrayList<>();

    private static String filePath = "data" + File.separator + "duke.txt";
    
    public static void main(String[] args) {
        displayWelcomeMessage();

        // for debugging
        // System.out.println(java.nio.file.Paths.get("").toAbsolutePath().toString());
        
        try {
            loadFileContents(filePath);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        
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
                    updateTasksToFile(filePath, tasks);
                } catch (ArrayIndexOutOfBoundsException e) {
                    displayEmptyDescriptionMessage(command);
                } catch (NumberFormatException e) {
                    displayInvalidTaskNumberMessage();
                } catch (IOException e) {
                    displayIOExceptionMessage(e);
                } catch (ChatboxException e) {
                    displayInvalidTaskNumberMessage();
                }
                break;
            case BYE_COMMAND:
                displayExitMessage();
                break;
            case TODO_COMMAND:
                try {
                    addTodo(split[1]);
                    updateTasksToFile(filePath, tasks);
                } catch (ArrayIndexOutOfBoundsException e) {
                    displayEmptyDescriptionMessage(command);
                } catch (IOException e) {
                    displayIOExceptionMessage(e);
                }
                break;
            case DEADLINE_COMMAND:
                try {
                    addDeadline(split[1]);
                    updateTasksToFile(filePath, tasks);
                } catch (ArrayIndexOutOfBoundsException e) {
                    displayEmptyDescriptionMessage(command);
                } catch (IOException e) {
                    displayIOExceptionMessage(e);
                }
                break;
            case EVENT_COMMAND:
                try {
                    addEvent(split[1]);
                    updateTasksToFile(filePath, tasks);
                } catch (ArrayIndexOutOfBoundsException e) {
                    displayEmptyDescriptionMessage(command);
                } catch (IOException e) {
                    displayIOExceptionMessage(e);
                }
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

    private static void displayInvalidTaskNumberMessage() {
        System.out.println("Please enter a valid task number~");
    }

    private static void displayExitMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }
    
    private static void addTodo(String description) {
        String taskDescription = description.trim();
        Task task = new Todo(taskDescription);
        tasks.add(task);
        displayAddTaskMessage(task);
    }
    
    private static void addDeadline(String description) {
        String[] taskBy = description.split(DEADLINE_MARKER);
        String taskDescription = taskBy[0].trim();
        String by = taskBy[1].trim();
        Task task = new Deadline(taskDescription, by);
        tasks.add(task);
        displayAddTaskMessage(task);
    }
    
    private static void addEvent(String description) {
        String[] taskAt = description.split(EVENT_MARKER);
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

    private static void displayCommandNotFoundMessage() {
        System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    private static void displayEmptyDescriptionMessage(String command) {
        System.out.println(String.format("OOPS!!! The description of a %s cannot be empty.", command));
    }

    private static void displayIOExceptionMessage(IOException e) {
        System.out.println("Something went wrong: " + e.getMessage());
    }

    private static void loadFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        System.out.println("Tasks on the list: ");
        if (!s.hasNext()) {
            System.out.println("Empty list");
        }
        
        while (s.hasNext()) {
            String taskLine = s.nextLine();
            System.out.println(taskLine);
            saveStringtoTask(taskLine);
        }
    }
    
    private static void updateTasksToFile(String filePath, List<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task task: tasks) {
            fw.write(task.getFileString() + System.lineSeparator());
        }
        fw.close();
    }
    
    private static void saveStringtoTask(String fileLine) {
        String[] arrays = fileLine.split("\\|");
        String type = arrays[0].trim();
        String isDone = arrays[1].trim();
        String description = arrays[2].trim();
        switch (type) {
        case "T":
            Task todo = new Todo(description);
            if (isDone == "1") {
                todo.markAsDone();
            }
            tasks.add(todo);
            break;
        case "D":
            String by = arrays[3].trim();
            Task deadline = new Deadline(description, by);
            if (isDone == "1") {
                deadline.markAsDone();
            }
            tasks.add(deadline);
            break;
        case "E":
            String at = arrays[3].trim();
            Task event = new Event(description, at);
            if (isDone == "1") {
                event.markAsDone();
            }
            tasks.add(event);
            break;
        }         
    }
}
