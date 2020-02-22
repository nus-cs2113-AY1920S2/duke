package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static final int TASK_LIMIT = 100;
    public static final String LINE_SPLITTING = "\t____________________________________________________________\n";
    public static final String BYE = "bye";
    public static final String BYE_MESSAGE = "\tBye. Hope to see you again soon!";
    public static final String HELLO_MESSAGE = "\tHello! I'm Duke\n";
    public static final String HELP_MESSAGE = "\tIt seems like you are needing some help.\n";
    public static final String DONE = "done";
    public static final String LIST = "list";
    public static final String TASK_MARKING_MESSAGE = "\tNice! I've marked this task as done:";
    public static final String TODO = "todo";
    public static final String DEADLINE = "deadline";
    public static final String EVENT = "event";
    public static final String TASK_LISTING = "\tHere are the tasks in your list:";
    public static final String ADDED_TASK_MESSAGE = "\tGot it. I've added this task:";
    public static final int TODO_WORD_LENGTH = 4;
    public static final int DEADLINE_WORD_LENGTH = 8;
    public static final int EVENT_WORD_LENGTH = 5;
    public static final String SPACE = " ";
    public static final String EMPTY_STRING = "";
    public static final String EMPTY_INPUT_MESSAGE = "\t☹ OOPS!!! Input cannot be empty";
    public static final String DUMMY_INPUT_MESSAGE = "\t☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    public static final String LIMIT_REACHED_MESSAGE = "\t☹ OOPS!!! duke.Task limit has reached";
    public static final String WRONG_NUMBER_FORMAT_MESSAGE = "\t☹ OOPS!!! Wrong number format!";
    public static final String OUT_OF_BOUND_MESSAGE = "\t☹ OOPS!!! The task's index is out of bound or does not exist";
    public static final String TASK_REMOVED_MESSAGE = "\tNoted. I've removed this task:";
    public static final String DELETE = "delete";
    public static final String VERTICAL_BAR = "|";
    public static final String TODO_ABBREVIATION = "T";
    public static final String DEADLINE_ABBREVIATION = "D";
    public static final String EVENT_ABBREVIATION = "E";
    public static final String PATH = "data/duke.txt";
    public static final String ONE = "1";

    public static void printList(ArrayList<Task> tasks) {
        System.out.println(TASK_LISTING);
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("\t" + (i + 1) + "." + tasks.get(i));
        }
    }

    public static void main(String[] args) throws IOException, NullPointerException {
        ArrayList<Task> tasks = new ArrayList<>();
        welcomeMessage();
        loadFileData(tasks);
        Scanner commandScanner = new Scanner(System.in);
        String command = commandScanner.nextLine();
        while (!command.equals(BYE)) {
            String[] commandSplitter = command.split(SPACE);
            System.out.print(LINE_SPLITTING);
            if (command.equals(LIST)) {
                printList(tasks);
            } else {
                String prefix = commandSplitter[0];
                switch (prefix) {
                case DELETE:
                    deleteTaskMessage(tasks, commandSplitter[1]);
                    break;
                case DONE:
                    markTaskMessage(tasks, commandSplitter[1]);
                    break;
                case EMPTY_STRING:
                case TODO:
                case DEADLINE:
                case EVENT:
                default:
                    addTask(tasks, command, prefix);
                }
            }
            System.out.println(LINE_SPLITTING);
            command = commandScanner.nextLine();
        }
        writeFileData(tasks);
        byeMessage();
    }

    private static void writeFileData(ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(PATH);
        for (Task t: tasks) {
            fw.write(t.getType() + VERTICAL_BAR + t.getIsDone() + VERTICAL_BAR + t.getDescription()
                    + VERTICAL_BAR + t.getTime() + System.lineSeparator());
        }
        fw.close();
    }

    private static void deleteTaskMessage(ArrayList<Task> tasks, String number) {
        try {
            System.out.println(TASK_REMOVED_MESSAGE);
            int index = Integer.parseInt(number);
            System.out.println("\t" + tasks.get(index - 1));
            tasks.remove(tasks.get(index - 1));
            String printTaskCount = "\tNow you have " + tasks.size() + " tasks in the list.";
            System.out.println(printTaskCount);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(OUT_OF_BOUND_MESSAGE);
        } catch (NumberFormatException e) {
            System.out.println(WRONG_NUMBER_FORMAT_MESSAGE);
        }
    }

    private static void addTask(ArrayList<Task> tasks, String command, String prefix) {
        try {
            int splitIndex = command.indexOf("/");
            switch (prefix) {
            case EMPTY_STRING:
                throw new NullPointerException();
            case TODO:
                String activity = command.substring(TODO_WORD_LENGTH + 1);
                tasks.add(new ToDo(activity));
                break;
            case DEADLINE:
                String activity2 = command.substring(DEADLINE_WORD_LENGTH + 1, splitIndex - 1);
                String date = command.substring(splitIndex + 4);
                tasks.add(new Deadline(activity2, date));
                break;
            case EVENT:
                String activity3 = command.substring(EVENT_WORD_LENGTH + 1, splitIndex - 1);
                String time = command.substring(splitIndex + 4);
                tasks.add(new Event(activity3, time));
                break;
            default:
                throw new IOException();
            }
            if (tasks.size() > TASK_LIMIT) {
                throw new ArrayIndexOutOfBoundsException();
            }
            System.out.println(ADDED_TASK_MESSAGE);
            String printTask = "\t" + tasks.get(tasks.size() - 1);
            System.out.println(printTask);
            String printTaskCount = "\tNow you have " + tasks.size() + " tasks in the list.";
            System.out.println(printTaskCount);
        } catch (NullPointerException e) {
            System.out.println(EMPTY_INPUT_MESSAGE);
        } catch (StringIndexOutOfBoundsException e) {
            String invalidFormatMessage = "\t☹ OOPS!!! The description of a " + prefix + " cannot be empty or"
                    + " it is in the wrong format.";
            System.out.println(invalidFormatMessage);
        } catch (IOException e) {
            System.out.println(DUMMY_INPUT_MESSAGE);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(LIMIT_REACHED_MESSAGE);
        }
    }

    private static void markTaskMessage(ArrayList<Task> tasks, String word) throws ArrayIndexOutOfBoundsException {
        try {
            int index = Integer.parseInt(word);
            if (index >= tasks.size()) {
                throw new ArrayIndexOutOfBoundsException();
            }
            tasks.get(index - 1).markAsDone();
            System.out.println(TASK_MARKING_MESSAGE);
            String printTask = "\t%s\n";
            System.out.printf(printTask, tasks.get(index - 1));
        } catch (NumberFormatException e) {
            System.out.println(WRONG_NUMBER_FORMAT_MESSAGE);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(OUT_OF_BOUND_MESSAGE);
        }
    }

    private static void byeMessage() {
        System.out.print(LINE_SPLITTING);
        System.out.println(BYE_MESSAGE);
        System.out.println(LINE_SPLITTING);
    }

    private static void welcomeMessage() {
        String greeting = LINE_SPLITTING + HELLO_MESSAGE + HELP_MESSAGE + LINE_SPLITTING;
        System.out.println(greeting);
    }

    private static void loadFileData(ArrayList<Task> tasks) {
        try {
            File f = new File(PATH); // create a File for the given file path
            Scanner s = new Scanner(f); // create a Scanner using the File as the source
            while (s.hasNext()) {
                String[] dataSplitter = s.nextLine().split("\\|");
                switch (dataSplitter[0]) {
                case TODO_ABBREVIATION:
                    tasks.add(new ToDo(dataSplitter[2]));
                    break;
                case DEADLINE_ABBREVIATION:
                    tasks.add(new Deadline(dataSplitter[2], dataSplitter[3]));
                    break;
                case EVENT_ABBREVIATION:
                    tasks.add(new Event(dataSplitter[2], dataSplitter[3]));
                    break;
                default:
                }
                if (dataSplitter[1].equals(ONE)) {
                    tasks.get(tasks.size() - 1).markAsDone();
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File does not exist yet");
        }
    }
}
