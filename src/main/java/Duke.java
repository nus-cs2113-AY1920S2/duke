import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.Scanner;
import java.util.ArrayList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import duke.task.Todo;
import duke.task.Task;
import duke.task.Event;
import duke.task.Deadline;
import duke.exception.DukeException;
import duke.exception.FindDukeException;

public class Duke {
    private static String HEADER_LINE = "        ╔═══════════════════════════════════════════════════════════╗";
    private static String BOTTOM_LINE = "        ╚═══════════════════════════════════════════════════════════╝";
    public static final String FILE_PATH = "data/duke.txt";
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static final int charLengthToSkip = 5;

    // let statement be printed in center
    public static void printInCenter (@NotNull String str) {
        int left = (60 - str.length()) / 2;
        int right = 60 - left - str.length();
        String repeatedChar = " ";
        String buff = "\t║" + repeatedChar.repeat(Math.max(0, left)) +
                str + repeatedChar.repeat(Math.max(0, right - 1)) + "║";
        System.out.println(buff);
    }

    public static void greet () {
        System.out.println(HEADER_LINE);
        printInCenter("  _   _    _   _ U _____ u             ");
        printInCenter(" |'| |'|U |\"|u| |\\| ___\"|/    ___      ");
        printInCenter("/| |_| |\\\\| |\\| | |  _|\"     |_\"_|     ");
        printInCenter("U|  _  |u | |_| | | |___      | |      ");
        printInCenter(" |_| |_| <<\\___/  |_____|   U/| |\\u    ");
        printInCenter(" //   \\\\(__) )(   <<   >>.-,_|___|_,-. ");
        printInCenter("(_\") (\"_)   (__) (__) (__)\\_)-' '-(_/  ");
        printInCenter("");
        printInCenter("Hello! I'm your chatbot - Huei.");
        System.out.println(BOTTOM_LINE);
        System.out.println("        What can I do for you? Type 'bye' to exit.");
        System.out.println();
    }

    public static void echo (String cmd) {
        System.out.println(HEADER_LINE);
        printInCenter(cmd);
        System.out.println(BOTTOM_LINE);
        System.out.println("        What can I do for you? Type 'bye' to exit.");
        System.out.println();
    }

    public static void add (String cmd) {
        Task todo = new Task(tasks.size(), cmd, false);
        tasks.add(todo);
        System.out.println(HEADER_LINE);
        printInCenter("Added: " + cmd);
        System.out.println(BOTTOM_LINE);
        System.out.println("        What can I do for you? Type 'bye' to exit.");
        System.out.println();
    }

    public static void find (@NotNull String cmd) throws DukeException {
        int startPosition = cmd.indexOf(" ");
        String cmdFind = cmd.substring(startPosition + 1);

        boolean isEmptyKeyword = cmdFind.length()==0;
        if(isEmptyKeyword){
            throw new DukeException("Please enter a keyword.");
        }

        int taskID = 0;
        for (Task t: tasks) {
            if (t.toString().contains(cmdFind)) {
                boolean isFirstOne = (taskID == 0);
                if (isFirstOne) {
                    System.out.println(HEADER_LINE);
                    printInCenter("Here are the matching tasks in your list:");
                }
                taskID++;
                printInCenter(t.toString());
            }
        }

        boolean isNotFound = taskID == 0;
        if (isNotFound) {
            throw new DukeException("Not found this keyword.");
        } else {
            System.out.println(BOTTOM_LINE);
        }
    }

    public static void delete (int taskNumber) {
        Task task = tasks.get(taskNumber);
        tasks.remove(task);

        Iterator itr = tasks.iterator();
        int counter = 0;
        while (itr.hasNext())
        {
            Task t = (Task)itr.next();
            t.taskID = counter;
            counter++;
        }

        System.out.println(HEADER_LINE);
        printInCenter("Noted. I've removed this task: ");
        printInCenter(task.toString());
        printInCenter("Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(BOTTOM_LINE);
        System.out.println("        What can I do for you? Type 'bye' to exit.");
        System.out.println();
    }

    private static void printTask (@NotNull Task t) {
        System.out.println(HEADER_LINE);
        printInCenter("Got it. I've added this task: ");
        printInCenter(t.toString());
        printInCenter("Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(BOTTOM_LINE);
        System.out.println("        What can I do for you? Type 'bye' to exit.");
        System.out.println();
    }

    public static void todo (String cmd) throws DukeException {
        FindDukeException findTodoException = new FindDukeException(cmd);
        findTodoException.toDoException();

        int startPosition = cmd.indexOf(" ");
        String cmdTodo = cmd.substring(startPosition + 1);
        Task todo = new Todo(tasks.size(), cmdTodo, false);
        tasks.add(todo);
        printTask(todo);
    }

    public static void event (String cmd) throws DukeException {
        FindDukeException findEventException = new FindDukeException(cmd);
        findEventException.eventException();

        int startPosition = cmd.indexOf(" ");
        int timePosition = cmd.indexOf(" /at ");
        String cmdEvent = cmd.substring(startPosition + 1, timePosition);
        String cmdTime = cmd.substring(timePosition + charLengthToSkip);
        Task event = new Event(tasks.size(), cmdEvent, false, cmdTime);
        tasks.add(event);
        printTask(event);
    }

    public static void deadline (String cmd) throws DukeException {
        FindDukeException findDeadlineException = new FindDukeException(cmd);
        findDeadlineException.deadlineException();

        int startPosition = cmd.indexOf(" ");
        int timePosition = cmd.indexOf(" /by ");
        String cmdDeadline = cmd.substring(startPosition + 1, timePosition);
        String cmdTime = cmd.substring(timePosition + charLengthToSkip);
        Task deadline = new Deadline(tasks.size(), cmdDeadline, false, cmdTime);
        tasks.add(deadline);
        printTask(deadline);
    }

    public static void done (int taskNumber) {
        tasks.get(taskNumber).setStatus();
        System.out.println(HEADER_LINE);
        printInCenter("Nice! I've marked this task as done: ");
        printInCenter(tasks.get(taskNumber).toString());
        System.out.println(BOTTOM_LINE);
        System.out.println("        What can I do for you? Type 'bye' to exit.");
        System.out.println();
    }

    public static void list () {
        System.out.println(HEADER_LINE);
        printInCenter("Here are the tasks in your list:");
        for (Task t: tasks){
            if (t == null){
                break;
            }
            printInCenter(t.toString());
        }
        System.out.println(BOTTOM_LINE);
        System.out.println("        What can I do for you? Type 'bye' to exit.");
        System.out.println();
    }

    public static void save () {
        try {
            for (Task ignored : tasks) {
                writeToFile();
            }
        } catch (IOException e) {
            System.out.println(HEADER_LINE);
            printInCenter("Error occurred: " + e.getMessage());
            System.out.println(BOTTOM_LINE);
        }
    }

    private static void writeToFile() throws IOException {
        FileWriter fw = new FileWriter(Duke.FILE_PATH);
        for (Task task : tasks) {
            fw.write(task.toString() + System.lineSeparator());
        }
        fw.close();
    }

    public static void load() {
        File duke = new File("./data/duke.txt");
        if (!duke.exists()) {
            try {
                duke.getParentFile().mkdir();
                duke.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            loadFile();
        } catch (FileNotFoundException e) {
            System.out.println(HEADER_LINE);
            printInCenter("File not found.");
            System.out.println(BOTTOM_LINE);
        }
    }

    public static void loadFile() throws FileNotFoundException {
        File file = new File(FILE_PATH);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            int indexOfCommand = 3;
            String cmd = scanner.nextLine().substring(indexOfCommand)
                    .replace("(","/")
                    .replace(")","");
            addLoadedTask(cmd, tasks);
        }
    }

    private static void addLoadedTask (@NotNull String cmd, ArrayList<Task> tasks) {
        String[] words = cmd.split(" ");
        String taskType = words[0];
        String taskStatus = words[1];
        int indexOfCommand = 8;
        int indexOfTimePosition = 6;
        String cmdTask = cmd.substring(indexOfCommand);

        boolean isToDo = taskType.equals("[T]");
        boolean isDeadline = taskType.equals("[D]");
        boolean isEvent = taskType.equals("[E]");
        boolean isDone = taskStatus.equals("[v]");

        FindDukeException findDukeException = new FindDukeException(cmd);
        try {
            if (isToDo) {
                Task todo = new Todo(tasks.size(), cmdTask, isDone);
                tasks.add(todo);
            } else if (isEvent) {
                int timePosition = cmdTask.indexOf(" /at: ");
                String cmdEvent = cmdTask.substring(0, timePosition);
                String cmdTime = cmdTask.substring(timePosition + indexOfTimePosition);
                Task event = new Event(tasks.size(), cmdEvent, isDone, cmdTime);
                tasks.add(event);
            } else if (isDeadline) {
                int timePosition = cmdTask.indexOf(" /by: ");
                String cmdDeadline = cmdTask.substring(0, timePosition);
                String cmdTime = cmdTask.substring(timePosition + indexOfTimePosition);
                Task deadline = new Deadline(tasks.size(), cmdDeadline, isDone, cmdTime);
                tasks.add(deadline);
            } else {
                findDukeException.undefinedTypeException();
            }
        } catch (DukeException dukeException) {
            System.out.println(HEADER_LINE);
            printInCenter(dukeException.getMessage());
            System.out.println(BOTTOM_LINE);
        }
    }

    public static void exit () {
        System.out.println(HEADER_LINE);
        printInCenter("Bye! See you next time. :)");
        System.out.println(BOTTOM_LINE);
        System.exit(0);
    }

    public static void main (String[] args) throws DukeException {
        int spacePosition;
        int taskNumber;

        load();
        greet();

        // read command-line input
        Scanner scanner = new Scanner(System.in);
        String cmd = scanner.nextLine();

        while (!cmd.equals("bye")) {
            String[] cmdSplit = cmd.split(" ");
            switch (cmdSplit[0]) {
            case "list":
                list();
                break;
            case "find":
                find(cmd);
                break;
            case "delete":
                spacePosition = cmd.indexOf(" ");
                taskNumber = Integer.parseInt(cmd.substring(spacePosition + 1)) - 1;
                delete(taskNumber);
                save();
                break;
            case "todo":
                try {
                    todo(cmd);
                } catch (DukeException dukeException) {
                    System.out.println(HEADER_LINE);
                    printInCenter(dukeException.getMessage());
                    System.out.println(BOTTOM_LINE);
                }
                save();
                break;
            case "event":
                try {
                    event(cmd);
                } catch (DukeException dukeException) {
                    System.out.println(HEADER_LINE);
                    printInCenter(dukeException.getMessage());
                    System.out.println(BOTTOM_LINE);
                }
                save();
                break;
            case "deadline":
                try {
                    deadline(cmd);
                } catch (DukeException dukeException) {
                    System.out.println(HEADER_LINE);
                    printInCenter(dukeException.getMessage());
                    System.out.println(BOTTOM_LINE);
                }
                save();
                break;
            case "done":
                spacePosition = cmd.indexOf(" ");
                taskNumber = Integer.parseInt(cmd.substring(spacePosition + 1)) - 1;
                done(taskNumber);
                save();
                break;
            default:
                try {
                    FindDukeException findDukeException = new FindDukeException(cmd);
                    findDukeException.undefinedTypeException();
                } catch (DukeException dukeException) {
                    System.out.println(HEADER_LINE);
                    printInCenter(dukeException.getMessage());
                    System.out.println(BOTTOM_LINE);
                }
                break;
            }
            cmd = scanner.nextLine();
        }
        exit();
    }
}
