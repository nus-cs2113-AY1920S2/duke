package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import static duke.Constants.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Duke {

    private static ArrayList<Task> tasks = new ArrayList<>();
    private static int taskCount = 0;

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + LOGO);
        greet();
        loadTasks();
        String userResponde;
        Scanner in = new Scanner(System.in);
        do {
            userResponde = in.nextLine();
            dukeResponde(userResponde);
        } while (!userResponde.equals(BYE));
    }

    public static void greet() {
        printLine();
        System.out.println("\tHello! I'm duke.Duke");
        System.out.println("\tWhat can I do for you?");
        printLine();
        System.out.println();
    }

    public static void loadTasks() {
        File f = new File(FILE_PATH);
        try {
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String oneTask;
                oneTask = s.nextLine();
                loadATask(oneTask);
            }
        } catch (FileNotFoundException e) {
            System.out.println("\tCreating back up file in hard disk... ");
            System.out.println();
            try {
                f.createNewFile();
            } catch (IOException error) {

                System.out.println("\tSomething went wrong: " + error.getMessage());
            }
        }
    }

    public static void loadATask(String oneTask) {
        String[] taskSplit = oneTask.split(" \\| ");
        switch (taskSplit[0]) {
        case "T":
            tasks.add(new Todo(taskSplit[2]));
            break;
        case "D":
            tasks.add(new Deadline(taskSplit[2],taskSplit[3]));
            break;
        case "E":
            tasks.add(new Event(taskSplit[2],taskSplit[3]));
            break;
        default:
            break;
        }
        if (taskSplit[1].equals("1")) {
            tasks.get(taskCount).markAsDone();
        }
        taskCount++;
    }

    public static void dukeResponde(String userResponde) {
        printLine();
        if (userResponde.equals(BYE)) {
            sayBye();
        } else if (userResponde.equals(LIST)) {
            try {
                listTask();
            } catch (DukeException e) {
                System.out.println("\t:(OOPS!!! " + e.getMessage());
            }
        } else if (userResponde.startsWith(DONE)) {
            try {
                markAsDone(userResponde);
            } catch (NumberFormatException e) {
                System.out.println("\t:(OOPS!!! Please enter the task number.");
            } catch (NullPointerException e) {
                System.out.println("\t:(OOPS!!! Please first input the task then mark done.");
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("\t:(OOPS!!! Please enter the task number.");
            }
            outputTasks();
        } else if (userResponde.startsWith(DELETE)) {
            try {
                deleteTask(userResponde);
            } catch (NumberFormatException e) {
                System.out.println("\t:(OOPS!!! Please enter the task number you want to delete.");
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("\t:(OOPS!!! Please enter the task number you want to delete.");
            } catch (DukeException e) {
                System.out.println("\t:(OOPS!!! " + e.getMessage());
            }
            outputTasks();
        } else {
            try {
                addNewTask(userResponde);
            } catch (DukeException e) {
                System.out.println("\t:(OOPS!!! " + e.getMessage());
            }
            outputTasks();
        }
        printLine();
        System.out.println();
    }

    public static void printLine() {
        System.out.println(LINE_BREAK);
    }

    public static void sayBye() {
        System.out.println("\tBye. Hope to see you again soon!");
    }

    public static void listTask() throws DukeException {
        if (taskCount == 0) {
            throw new DukeException("No tasks!!!");
        }
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println("\t" + (i + 1) + "." + tasks.get(i));
        }
    }

    public static void markAsDone(String userResponde) {
        int doneCount = Integer.parseInt(userResponde.substring(DONE_LENGTH)) - 1;
        tasks.get(doneCount).markAsDone();
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t  yes! " + tasks.get(doneCount).getDescription());
    }

    public static void deleteTask(String userResponde) throws DukeException {
        int deleteCount = Integer.parseInt(userResponde.substring(DELETE_LENGTH)) - 1;
        if (deleteCount > taskCount) {
            throw new DukeException("There is no task " + (deleteCount + 1) + ". Please reconsider the index.");
        }
        System.out.println("\tNoted. I've removed this task:");
        System.out.println("\t  " + tasks.get(deleteCount));
        taskCount--;
        tasks.remove(deleteCount);
        System.out.println("\tNow you have " + taskCount + " tasks in the list.");
    }

    public static void addNewTask(String userResponde) throws DukeException {
        String[] responses = userResponde.split(" ");
        if (responses[0].equals("todo")) {
            if (responses.length < 2) {
                throw new DukeException("The description of a todo cannot be empty.");
            }
            tasks.add(new Todo(userResponde.substring(TODO_LENGTH)));
        } else if (responses[0].equals("deadline")) {
            if (responses.length < 2) {
                throw new DukeException("The description of a deadline cannot be empty.");
            }
            int dividerPosition = userResponde.indexOf(" /by");
            if (dividerPosition == -1) {
                throw new DukeException("Please follow the format: deadline thingsToDo /by time");
            }
            String taskName = userResponde.substring(DEADLINE_LENGTH,dividerPosition);
            String deadlineTime = userResponde.substring(dividerPosition + FORMAT_LENGTH);
            tasks.add(new Deadline(taskName,deadlineTime));
        } else if (responses[0].equals("event")) {
            if (responses.length < 2) {
                throw new DukeException("The description of a event cannot be empty.");
            }
            int dividerPosition = userResponde.indexOf(" /at");
            if (dividerPosition == -1) {
                throw new DukeException("Please follow the format: event thingsToDo /at time");
            }
            String taskName = userResponde.substring(EVENT_LENGTH,dividerPosition);
            String deadlineTime = userResponde.substring(dividerPosition + FORMAT_LENGTH);
            tasks.add(new Event(taskName,deadlineTime));
        } else {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t  " + tasks.get(taskCount));
        System.out.println("\tNow you have " + (taskCount + 1) + " tasks in the list");
        taskCount++;
    }

    public static void outputTasks() {
        try {
            FileWriter fw = new FileWriter(FILE_PATH);
            for (int i = 0; i < taskCount; i++) {
                fw.write(tasks.get(i).toFile() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("\tNo backup file in the hard disk.");
        }
    }
}

