import Exceptions.InvalidCommandException;
import Exceptions.ToDoEmptyException;
import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.ToDo;

import java.io.IOException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;

public class Duke {

    private static String DUKE_LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private static String LINE_SEPARATOR = "____________________________________________________________";
    private static String WELCOME_MESSAGE = "Hello! I'm Duke\n What can I do for you?";
    private static String GOODBYE_MESSAGE = "Goodbye. Hope to see you again soon!";
    private static String DISPLAY_LIST_MESSAGE = "Here are the tasks in your list:";
    private static String ADD_TODO_MESSAGE = "Noted! I have added a new todo.";
    private static String ADD_DEADLINE_MESSAGE = "Noted! I have added a new deadline.";
    private static String ADD_EVENT_MESSAGE = "Noted! I have added a new event.";
    private static String LIST_CLEAR_MESSAGE = "Your task list has been cleared successfully!";
    private static String LIST_CLEAR_CONFIRMATION_MESSAGE = "Are you sure you want to clear all tasks in your list? (Y/N)";
    private static String LIST_SIZE_MESSAGE = "You now have %d tasks in the list.\n";
    private static String DELETE_TASK_MESSAGE = "Got it! The following task has been successfully deleted.";
    private static String INVALID_TODO_MESSAGE = "Oops! Please follow the following format when adding a todo:";
    private static String INVALID_DEADLINE_MESSAGE = "Oops! Please follow the following format when adding a deadline:";
    private static String INVALID_EVENT_MESSAGE = "Oops! Please follow the following format when adding an event:";
    private static String INVALID_COMMAND_MESSAGE = "I'm sorry, but I don't know what that means :(.";
    private static String ERROR_MESSAGE = "Hmm, an error has occurred...";
    private static String PROMPT_HELP_MESSAGE = "Type /help for a list of available commands";



    public static void main(String[] args) {

        System.out.println(DUKE_LOGO);
        System.out.println(LINE_SEPARATOR);
        System.out.println(WELCOME_MESSAGE);
        System.out.println(LINE_SEPARATOR);

        try {
            File newFile = new File("./src/main/java/data/duke.txt");
            if (newFile.createNewFile()) {
                System.out.println("File created: " + newFile.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        Scanner sc = new Scanner(System.in);
        List<Task> tasks = new ArrayList<Task>();
        Boolean exitProgram = false;

        while (!exitProgram) {
            String input = sc.nextLine();
            String[] inputArray = input.split(" ", 2);

            System.out.println("____________________________________________________________");

            try {
                FileWriter fileWriter = new FileWriter("./src/main/java/data/duke.txt");
                if (input.equals("list")) {
                    System.out.println(DISPLAY_LIST_MESSAGE);
                    int counter = 0;
                    for (Task task : tasks) {
                        counter++;
                        System.out.println(counter + ". " + task);
                    }
                } else if (inputArray[0].equals("todo")) {
                    if (inputArray.length < 2) throw new ToDoEmptyException();
                    ToDo newToDo = new ToDo(inputArray[1]);
                    tasks.add(newToDo);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(newToDo);
                    System.out.format(LIST_SIZE_MESSAGE, tasks.size());
                } else if (inputArray[0].equals("deadline")) {
                    String[] deadlineInfo = inputArray[1].split("/by ", 2);
                    Deadline newDeadline = new Deadline(deadlineInfo[0], deadlineInfo[1]);
                    tasks.add(newDeadline);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(newDeadline);
                    System.out.format("Now you have %d tasks in the list.\n", tasks.size());
                } else if (inputArray[0].equals("event")) {
                    String[] eventInfo = inputArray[1].split("/at ", 2);
                    Event newEvent = new Event(eventInfo[0], eventInfo[1]);
                    tasks.add(newEvent);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(newEvent);
                    System.out.format("Now you have %d tasks in the list.\n", tasks.size());
                } else if (inputArray[0].equals("done")) {
                    int taskIndex = Integer.parseInt(inputArray[1]) - 1;
                    Task doneTask = tasks.get(taskIndex);
                    doneTask.setDone(true);
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(doneTask);
                } else if (inputArray[0].equals("delete")) {
                    int taskIndex = Integer.parseInt(inputArray[1]) - 1;
                    Task deleteTask = tasks.get(taskIndex);
                    tasks.remove(deleteTask);
                    System.out.println("Noted. I've removed this task:");
                    System.out.println(deleteTask);
                    System.out.format("Now you have %d tasks in the list.\n", tasks.size());
                } else if (input.equals("bye")) {
                    System.out.println(GOODBYE_MESSAGE);
                    exitProgram = true;
                } else {
                    throw new InvalidCommandException();
                }
                int counter = 0;
                for (Task task : tasks) {
                    counter++;
                    fileWriter.write(counter + ". " + task);
                }
                fileWriter.close();
            } catch (ToDoEmptyException e) {
                System.out.println("OOPS!!! The description of a todo cannot be empty");
            } catch (InvalidCommandException e) {
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means :(");
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }

            System.out.println("____________________________________________________________");

        }

    }

}
