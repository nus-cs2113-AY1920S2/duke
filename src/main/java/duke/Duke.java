package duke;

import duke.exception.DukeException;
import duke.exception.TestDukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

public class Duke {

    public static final int OFFSITE_OF_TIME = 4;
    public static final String CUTTING_LINE = "    ____________________________________________________________";
    public static final String FILE_PATH = "data/duke.txt";
    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void greet(String logo){
        System.out.println(CUTTING_LINE);
        System.out.println(logo);
        System.out.println("\tHello! I'm Renzo");
        System.out.println("\tWhat can I do for you?");
        System.out.println(CUTTING_LINE);
        System.out.println("\nPlease enter your command or enter \"bye\" to exit:");
    }

    public static void list(ArrayList<Task> tasks){
        System.out.println(CUTTING_LINE);
        System.out.println("\tHere are the tasks in your list:");
        for (Task task: tasks) {
            int taskID = tasks.indexOf(task) + 1;
            System.out.println("\t" + taskID + "." + task.toString());
        }
        System.out.println(CUTTING_LINE);
        System.out.println("\nPlease enter your command or enter \"bye\" to exit:");
    }

    public static Todo processToDoDescription(String description) throws DukeException {
        /*Process Todo Exception */
        TestDukeException testTodoException = new TestDukeException(description);
        testTodoException.throwToDoException();

        /*Set up a new Todo Task */
        int lengthOfTodo = 5;       //including a space
        String todoDescription = description.substring(lengthOfTodo);
        return new Todo(todoDescription);
    }

    public static Deadline processDeadlineDescription(String description) throws DukeException{
        /*Process Deadline Exception */
        TestDukeException testDeadlineException = new TestDukeException(description);
        testDeadlineException.throwDeadlineException();

        /*Set up a new Deadline Task */
        int lengthOfDeadline = 9;   //including a space
        String deadlineDescription = description.substring(lengthOfDeadline);
        int indexOfBy = deadlineDescription.indexOf("/by");
        int nameStartIndex = 0;
        int nameEndIndex = indexOfBy - 1;
        int timeStartIndex = indexOfBy + OFFSITE_OF_TIME;
        String name = deadlineDescription.substring(nameStartIndex, nameEndIndex);
        String time = deadlineDescription.substring(timeStartIndex);
        return new Deadline(name, time);
    }

    public static Event processEventDescription(String description) throws DukeException{
        /*Process Event Exception */
        TestDukeException testEventException = new TestDukeException(description);
        testEventException.throwEventException();

        /*Set up a new Event Task */
        int lengthOfEvent = 6;      //including a space
        String eventDescription = description.substring(lengthOfEvent);
        int indexOfAt = eventDescription.indexOf("/at");
        int nameStartIndex = 0;
        int nameEndIndex = indexOfAt - 1;
        int timeStartIndex = indexOfAt + OFFSITE_OF_TIME;
        String name = eventDescription.substring(nameStartIndex, nameEndIndex);
        String time = eventDescription.substring(timeStartIndex);
        return new Event(name, time);
    }

    public static void addTask(String description, ArrayList<Task> tasks){
        Task newTask;
        String words[] = description.split(" ");
        String taskType = words[0];
        boolean isToDo = taskType.equalsIgnoreCase("todo");
        boolean isDeadline = taskType.equalsIgnoreCase("deadline");
        boolean isEvent = taskType.equalsIgnoreCase("event");

        TestDukeException testDukeException = new TestDukeException(description);
        try {
            if (isToDo) {
                newTask = processToDoDescription(description);
            } else if (isDeadline) {
                newTask = processDeadlineDescription(description);
            } else if (isEvent) {
                newTask = processEventDescription(description);
            } else {
                testDukeException.throwTaskTypeException();
                newTask = null;
            }
            tasks.add(newTask);
            printAddedTask(newTask);
        } catch (DukeException dukeException){
            System.out.println(dukeException.getMessage());
        }

    }

    public static void printAddedTask(Task newTask) {
        System.out.println(CUTTING_LINE);
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t" + newTask.toString());
        System.out.printf("\tNow you have %d task(s) in the list\n", tasks.size());
        System.out.println(CUTTING_LINE);
        System.out.println("\nPlease enter your command or enter \"bye\" to exit:");
    }

    public static void markAsDone(String command, ArrayList<Task> tasks){
        int taskID = getIdFromCommand(command);

        /* Exit if enter a wrong task id */
        boolean isWrongID = taskID > tasks.size() || taskID < 1;
        if (isWrongID){
            printIdOutOfRangeError();
            return;
        }

        Task taskToBeMarked = tasks.get(taskID-1);
        taskToBeMarked.markAsDone();
        printMarkedTask(taskToBeMarked);
    }

    public static void printMarkedTask(Task markedTask) {
        System.out.println(CUTTING_LINE);
        System.out.println("\tI've marked this task as done:");
        System.out.println("\t" + markedTask.getStatusIcon() + markedTask.getName());
        System.out.println(CUTTING_LINE);
        System.out.println("\nPlease enter your command or enter \"bye\" to exit:");
    }

    public static void printIdOutOfRangeError() {
        System.out.println(CUTTING_LINE);
        System.out.println("\tWrong Number!");
        System.out.println(CUTTING_LINE);
        System.out.println("\nPlease enter your command again or enter \"bye\" to exit:");
    }

    public static int getIdFromCommand(String command){
        String[] words = command.split("\\s+");
        return Integer.parseInt(words[1]);
    }

    public static void bye(){
        System.out.println(CUTTING_LINE);
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println(CUTTING_LINE);
    }

    public static void echo(String command){
        System.out.println(CUTTING_LINE);
        System.out.println("\t" + command);
        System.out.println(CUTTING_LINE);
        System.out.println("\nPlease enter your command or enter \"bye\" to exit:");
    }


    public static void delete(String command, ArrayList<Task> tasks){
        int taskID = getIdFromCommand(command);

        /* Exit if enter a wrong task id */
        boolean isWrongID = taskID > tasks.size() || taskID < 1;
        if (isWrongID){
            printIdOutOfRangeError();
            return;
        }
        printDeleteTask(tasks.get(taskID-1));
        tasks.remove(taskID-1);
    }

    public static void printDeleteTask(Task task) {
        System.out.println(CUTTING_LINE);
        System.out.println("\tNoted: I've removed this task:");
        System.out.println("\t" + task.getStatusIcon() + task.getName());
        int numberOfRemainedTasks = tasks.size() - 1;
        System.out.printf("\tNow you have %d task(s) in the list.\n", numberOfRemainedTasks);
        System.out.println(CUTTING_LINE);
        System.out.println("\nPlease enter your command or enter \"bye\" to exit:");
    }

    public static void save() {
        String fileName = FILE_PATH;

        try {
            for (Task task : tasks) {
                writeToFile(fileName);
            }
        } catch (IOException e){
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public static void writeToFile(String fileName) throws IOException{
        FileWriter fw = new FileWriter(fileName);
        for (Task task : tasks) {
            int taskID = tasks.indexOf(task) + 1;
            fw.write(taskID + ". " + task.toString() + System.lineSeparator());
        }
        fw.close();
    }

    public static void load(){
        try {
            loadFileContents();
        }catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    public static void loadFileContents() throws FileNotFoundException {
        File fileToBeLoad = new File(FILE_PATH);
        Scanner s = new Scanner(fileToBeLoad);
        while (s.hasNext()){
            int indexOfCommand = 3;
            String command = s.nextLine().substring(indexOfCommand).replace("(","/")
                    .replace(")","")
                    .replace(": ",":");
            addLoadedTask(command,tasks);
        }
    }

    public static void addLoadedTask(String description, ArrayList<Task> tasks){
        Task newTask;
        String[] words = description.split(" ");
        String taskType = words[0];
        String taskStatus = words[1];
        String isDoneIcon = "[\u2713]";

        boolean isToDo = taskType.equals("[T]");
        boolean isDeadline = taskType.equals("[D]");
        boolean isEvent = taskType.equals("[E]");

        boolean isDone =taskStatus.equals(isDoneIcon);
        int indexOfCommand = 7;

        TestDukeException testDukeException = new TestDukeException(description);
        try {
            if (isToDo) {
                String command = "todo" + description.substring(indexOfCommand);
                newTask = processToDoDescription(command);
            } else if (isDeadline) {
                String command = "deadline" + description.substring(indexOfCommand);
                newTask = processDeadlineDescription(command);
            } else if (isEvent) {
                String command = "event" + description.substring(indexOfCommand);
                newTask = processEventDescription(command);
            } else {
                testDukeException.throwTaskTypeException();
                newTask = null;
            }
            if (isDone) {
                newTask.markAsDone();
            }
            tasks.add(newTask);
        } catch (DukeException dukeException){
            System.out.println(dukeException.getMessage());
        }

    }

    public static void main(String[] args) {

        /* A logo for Renzo */
        String logo = "\t _____    _____   __   _   ______  _____  \n"
                +"\t|  _  \\  | ____| |  \\ | | |___  / /  _  \\ \n"
                +"\t| |_| |  | |__   |   \\| |    / /  | | | | \n"
                +"\t|  _  /  |  __|  | |\\   |   / /   | | | | \n"
                +"\t| | \\ \\  | |___  | | \\  |  / /__  | |_| | \n"
                +"\t|_|  \\_\\ |_____| |_|  \\_| /_____| \\_____/ \n";


        //Task[] tasks = new Task[MAX_NUMBER_OF_TASKS];
        load();
        /* Greet to user */
        greet(logo);

        /* Enable to get command from Command Line */
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();

        /* Process command */
        processCommand(tasks, scanner, command);

        /* Exit */
        bye();
    }

    public static void processCommand(ArrayList<Task> tasks, Scanner scanner, String command) {
        while (!command.equals("bye")) {
            if (command.equals("list")) {
               list(tasks);
            } else if (command.contains("done")) {
                markAsDone(command, tasks);
                save();
            } else if (command.contains("delete")) {
                delete(command, tasks);
                save();
            } else {
                addTask(command, tasks);
                save();
            }
            command = scanner.nextLine();
        }
    }
}
