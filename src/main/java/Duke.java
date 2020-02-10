import commands.Deadline;
import commands.Event;
import commands.Task;
import commands.Todo;
import exceptions.*;

import java.util.Scanner;

public class Duke {

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

    public static int getListSize(Task[] tasks) {
        int size = 0;
        for (int i = 0; i < tasks.length; i++) {
            if (tasks[i] != null) {
                size++;
            } else {
                return size;
            }
        }
        return size;
    }

    public static void listCommand(Task[] tasks) throws EmptyListException {
        int sizeOfList = getListSize(tasks);
        if (sizeOfList != 0) {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < Task.getListCount(); i += 1) {
                System.out.println(i + 1 + ". " + tasks[i].toString());
            }
        } else {
            throw new EmptyListException("No tasks in the list.");
        }
    }

    public static void doneCommand(Task[] tasks, String userData) throws TaskNumberException, TaskDescriptionException {
        if (!userData.trim().toLowerCase().equals("done")) {
            String[] newData = userData.split(" ");
            int index = Integer.parseInt(newData[1]);
            int sizeOfList = getListSize(tasks);
            if (index <= sizeOfList) {
                System.out.println("Nice! I've marked this task as done: ");
                tasks[index - 1].updateIsDone();
                System.out.println(tasks[index - 1].toString());
            } else {
                throw new TaskNumberException("Task number does not exist in the list.");
            }
        } else {
            throw new TaskDescriptionException("Add a task number after \'done\' to mark task as done.");
        }
    }

    public static void deadlineCommand(Task[] tasks, String userData) throws DeadlineTaskException, DeadlineDateException {
        if (!userData.trim().toLowerCase().equals("deadline")) {
            int indexOfBy = userData.indexOf("/by");
            if (indexOfBy == -1) {
                throw new DeadlineDateException("Please specify a date by using \'/by\'!");
            }
            String[] newData = userData.split(" /by ");
            if(newData.length == 1){
                throw new DeadlineDateException("Please specify a date after \'/by\'!");
            }
            tasks[Task.getListCount()] = new Deadline(newData[0], newData[1]);
            System.out.println("Got it. I've added this task:" + System.lineSeparator()
                    + tasks[Task.getListCount() - 1].toString() + System.lineSeparator()
                    + "Now you have " + Task.getListCount() + " task(s) in the list.");
        }else{
            throw new DeadlineTaskException("Add a task behind 'deadline', task cannot be left empty.");
        }
    }

    public static void eventCommand(Task[] tasks, String userData) throws TaskException, EventDateException {
        if (!userData.trim().toLowerCase().equals("event")) {
            int indexOfAt = userData.indexOf("/at");
            if (indexOfAt == -1) {
                throw new EventDateException("Please specify a date for event by using \'at\'!");
            }
            String[] newData = userData.split(" /at ");
            if(newData.length == 1){
                throw new EventDateException("Please specify a date after \'/at\'!");
            }
            tasks[Task.getListCount()] = new Event(newData[0], newData[1]);
            System.out.println("Got it. I've added this task:" + System.lineSeparator()
                    + tasks[Task.getListCount() - 1].toString() + System.lineSeparator()
                    + "Now you have " + Task.getListCount() + " task(s) in the list.");
        }else{
            throw new TaskException("Add a task behind 'event', task cannot be left empty.");
        }
    }

    public static void todoCommand(Task[] tasks, String userData) throws TaskException {
        if (!userData.trim().toLowerCase().equals("todo")) {
            String[] removeTodo = userData.split(" ", 2);
            tasks[Task.getListCount()] = new Todo(removeTodo[1]);
            System.out.println("Got it. I've added this task:" + System.lineSeparator()
                    + tasks[Task.getListCount() - 1].toString() + System.lineSeparator()
                    + "Now you have " + Task.getListCount() + " task(s) in the list.");
        } else {
            throw new TaskException("Add a task behind 'todo', task cannot be left empty.");
        }
    }

    public static void executeCommand(Task[] tasks, String userData) throws InvalidCommandException {
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
        } else {
            throw new InvalidCommandException("Invalid command.");
        }
    }

    public static void main(String[] args) {
        greeting();
        Scanner scan = new Scanner(System.in);
        Task[] tasks = new Task[101];

        while (true) {
            String userData = scan.nextLine();
            if (userData.toLowerCase().equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!\n");
                break;
            } else {
                try {
                    executeCommand(tasks, userData);
                } catch (DukeException err) {
                    System.out.println(err.toString());
                }
            }
        }
    }
}