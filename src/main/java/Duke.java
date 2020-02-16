import commands.Deadline;
import commands.Event;
import commands.Task;
import commands.Todo;
import exceptions.*;
import java.util.ArrayList;
import java.awt.image.AreaAveragingScaleFilter;
import java.io.FileNotFoundException;

import java.util.Scanner;

public class Duke {

    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void greeting() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\n");
        System.out.println("What can I do for you?\n");
    }

    public static void listCommand(ArrayList<Task> tasks) throws EmptyListException {
        int sizeOfList = tasks.size();
        if (sizeOfList != 0) {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < sizeOfList ; i += 1) {
                System.out.println(i + 1 + ". " + tasks.get(i));
                System.out.println(i + 1 + ". " + tasks.get(i));
            }
        } else {
            throw new EmptyListException("No tasks in the list.");
        }
    }

    public static void doneCommand(ArrayList<Task> tasks, String userData) throws TaskNumberException, TaskDescriptionException {
        if (!userData.trim().toLowerCase().equals("done")) {
            String[] newData = userData.split(" ");
            int index = Integer.parseInt(newData[1]);
            int sizeOfList = tasks.size();
            if (index <= sizeOfList) {
                System.out.println("Nice! I've marked this task as done: ");
                tasks.get(index - 1).updateIsDone();
                System.out.println(tasks.get(index - 1));
            } else {
                throw new TaskNumberException("Task number does not exist in the list.");
            }
        } else {
            throw new TaskDescriptionException("Add a task number after \'done\' to mark task as done.");
        }
    }

    public static void deleteCommand(ArrayList<Task> tasks, String userData) throws TaskNumberException, TaskDescriptionException {
        if (!userData.trim().toLowerCase().equals("delete")) {
            String[] newData = userData.split(" ");
            int index = Integer.parseInt(newData[1]);
            int sizeOfList = tasks.size();
            if (index <= sizeOfList) {
                System.out.println("Got it! I've removed this task: ");
                System.out.println(tasks.get(index - 1));
                tasks.remove(index - 1);
                System.out.println( "You now have " + tasks.size() + " task(s) in the list.");
            } else {
                throw new TaskNumberException("Task number does not exist in the list.");
            }
        } else {
            throw new TaskDescriptionException("Add a task number after \'done\' to mark task as done.");
        }
    }

    public static void deadlineCommand(ArrayList<Task> tasks, String userData) throws DeadlineTaskException, DeadlineDateException {
        if (!userData.trim().toLowerCase().equals("deadline")) {
            int indexOfBy = userData.indexOf("/by");
            if (indexOfBy == -1) {
                throw new DeadlineDateException("Please specify a date by using \'/by\'!");
            }
            String[] newData = userData.split(" /by ");
            if(newData.length == 1){
                throw new DeadlineDateException("Please specify a date after \'/by\'!");
            }
            Task deadline = new Deadline(newData[0], newData[1]);
            tasks.add(deadline);
            System.out.println("Got it. I've added this task:" + System.lineSeparator()
                    + tasks.get(tasks.size() - 1) + System.lineSeparator()
                    + "You now have " + tasks.size() + " task(s) in the list.");
        }else{
            throw new DeadlineTaskException("Add a task behind 'deadline', task cannot be left empty.");
        }
    }

    public static void eventCommand(ArrayList<Task> tasks, String userData) throws TaskException, EventDateException {
        if (!userData.trim().toLowerCase().equals("event")) {
            int indexOfAt = userData.indexOf("/at");
            if (indexOfAt == -1) {
                throw new EventDateException("Please specify a date for event by using \'at\'!");
            }
            String[] newData = userData.split(" /at ");
            if(newData.length == 1){
                throw new EventDateException("Please specify a date after \'/at\'!");
            }
            Task event = new Event(newData[0], newData[1]);
            tasks.add(event);
            System.out.println("Got it. I've added this task:" + System.lineSeparator()
                    + tasks.get(tasks.size() - 1) + System.lineSeparator()
                    + "You now have " + tasks.size() + " task(s) in the list.");
        }else{
            throw new TaskException("Add a task behind 'event', task cannot be left empty.");
        }
    }

    public static void todoCommand(ArrayList<Task> tasks, String userData) throws TaskException {
        if (!userData.trim().toLowerCase().equals("todo")) {
            String[] removeTodo = userData.split(" ", 2);
            Task todo = new Todo(removeTodo[1]);
            tasks.add(todo);
            System.out.println("Got it. I've added this task:" + System.lineSeparator()
                    + tasks.get(tasks.size() - 1) + System.lineSeparator()
                    + "You now have " + tasks.size() + " task(s) in the list.");
        } else {
            throw new TaskException("Add a task behind 'todo', task cannot be left empty.");
        }
    }

    public static void executeCommand(ArrayList<Task> tasks, String userData) throws InvalidCommandException {
        String[] newData = userData.split(" ");

        if (newData[0].toLowerCase().equals("list")) {
            try {
                listCommand(tasks);
            } catch (DukeException err) {
                System.out.println(err.toString());
            }
        } else if (newData[0].toLowerCase().equals("done")) {
            try {
                doneCommand(tasks, userData);
            } catch (DukeException err) {
                System.out.println(err.toString());
            }
        } else if (newData[0].toLowerCase().equals("deadline")) {
            try {
                deadlineCommand(tasks, userData);
            } catch (DukeException err) {
                System.out.println(err.toString());
            }
        } else if (newData[0].toLowerCase().equals("event")) {
            try {
                eventCommand(tasks, userData);
            } catch (DukeException err) {
                System.out.println(err.toString());
            }
        } else if (newData[0].toLowerCase().equals("todo")) {
            try {
                todoCommand(tasks, userData);
            } catch (DukeException err) {
                System.out.println(err.toString());
            }
        } else if(newData[0].toLowerCase().equals("delete")){
            try {
                deleteCommand(tasks, userData);
            } catch (DukeException err) {
                System.out.println(err.toString());
            }
        } else {
            throw new InvalidCommandException("Invalid command.");
        }
    }

    public static void main(String[] args) throws InvalidCommandException {
        greeting();
        Scanner scan = new Scanner(System.in);
        Storage storage = new Storage("output.txt");

        while (true) {
            String userData = scan.nextLine();
            if (userData.toLowerCase().equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!\n");
                break;
            } else {
                try {
                    ArrayList<Task> tasks = storage.getData();
                    executeCommand(tasks, userData);
                    storage.saveData(tasks);
                } catch (DukeException err) {
                    System.out.println(err.toString());
                } catch (FileNotFoundException err) {
                    ArrayList<Task> tasks = new ArrayList<>();
                    executeCommand(tasks, userData);
                    storage.saveData(tasks);
                }
            }
        }
    }
}