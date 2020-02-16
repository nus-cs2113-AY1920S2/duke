import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static int NUM_OF_TASK = 0;
    private static String LINE = "____________________________________________________________";
    private static final String BYE_COMMAND = "bye";
    private static final String LIST_COMMAND = "list";
    private static final String DONE_COMMAND = "done";
    private static final String TODO_COMMAND = "todo";
    private static final String EVENT_COMMAND = "event";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String DELETE_COMMAND = "delete";

    public static void main(String[] args) {
        printWelcomeMessage();
        runChatbot();
    }

    private static void runChatbot() {

        ArrayList<Task> Tasks = new ArrayList<Task>();
        try {
            Tasks = openData();
        } catch (IOException e) {
            System.out.println("Previous data cannot be loaded.");
        }
        Scanner in = new Scanner(System.in);
        String arr[] = getCommand(in);
        while (true) {
            runCommand(arr, Tasks, in);
            arr = getCommand(in);
        }
    }

    private static String[] getCommand(Scanner in) {
        String s = in.nextLine();
        String arr[] = s.split(" ", 2);
        return arr;
    }

    private static void runCommand(String[] arr, ArrayList<Task> Tasks, Scanner in)  {
        System.out.println(LINE);
        switch (arr[0]) {
        case (BYE_COMMAND):
            try {
                saveData(Tasks);
                printExitMessage();
            } catch (IOException e) {
                System.out.println("File cannot be saved. Please try again.");
            }
            break;
        case (LIST_COMMAND):
            printList(Tasks);
            break;
        case (DONE_COMMAND):
            try {
                int taskNum = Integer.parseInt(arr[1]);
                taskNum--;
                Tasks.get(taskNum).setDone(true);
                printDone(Tasks.get(taskNum));
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Oops! Please include the task number.");
            } catch (NumberFormatException e) {
                System.out.println("Oops! Please include the task number instead of '" + arr[1] + "'.");
            } catch (NullPointerException e) {
                System.out.println("Sorry but that task does not exist! Please try again.");
            }
            break;
        case (TODO_COMMAND):
            try {
                Todo todo = new Todo(arr[1]);
                Tasks.add(todo);
                printConfirm(Tasks.get(NUM_OF_TASK));
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Oops! Todo description cannot be empty!");
            }
            break;
        case (DEADLINE_COMMAND):
            try {
                String arr2[] = arr[1].split("/by ", 2);
                Deadline deadline = new Deadline(arr2[0], arr2[1]);
                Tasks.add(deadline);
                printConfirm(Tasks.get(NUM_OF_TASK));
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Oops! Deadline description is incomplete!");
            }
            break;
        case (EVENT_COMMAND):
            try {
                String arr2[] = arr[1].split("/at ", 2);
                Event event = new Event(arr2[0], arr2[1]);
                Tasks.add(event);
                printConfirm(Tasks.get(NUM_OF_TASK));
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Oops! Event description is incomplete!");
            }
            break;
        case (DELETE_COMMAND):
            try {
                int taskNum = Integer.parseInt(arr[1]) - 1;
                printDelete(Tasks.get(taskNum));
                Tasks.remove(taskNum);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Oops! Please include the task number!");
            } catch (NumberFormatException e) {
                System.out.println("Oops! Please include the task number instead of '" + arr[1] + "'.");
            } catch (NullPointerException e) {
                System.out.println("Sorry but that task does not exist! Please try again.");
            }
            break;
        default:
            System.out.println("Oops! I'm sorry but I don't know what that means :(");
            break;
        }
        System.out.println(LINE);
    }


    private static void saveData(ArrayList<Task> Tasks) throws IOException {
        FileWriter fileWriter = new FileWriter("data/duke.txt", false);
        for (Task task : Tasks) {
            fileWriter.write(task.saveTask());
            fileWriter.write(System.lineSeparator());
        }
        fileWriter.close();
        System.out.println("Data saved successfully.");
    }

    private static ArrayList<Task> openData() throws IOException {
        ArrayList<Task> Tasks = new ArrayList<Task>();
        int pointer = 0;
        FileReader fileReader = new FileReader("data/duke.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line = bufferedReader.readLine();
        while (line != null) {
            String arr[] = line.split("[|]", 4);
            switch (arr[0]) {
            case ("T"):
                Todo todo = new Todo(arr[2]);
                if (arr[1].equals("1")) {
                    todo.setDone(true);
                }
                Tasks.add(todo);
                break;
            case ("D"):
                Deadline deadline = new Deadline(arr[2], arr[3]);
                if (arr[1].equals("1")) {
                    deadline.setDone(true);
                }
                Tasks.add(deadline);
                break;
            case ("E"):
                Event event = new Event(arr[2], arr[3]);
                if (arr[1].equals("1")) {
                    event.setDone(true);
                }
                Tasks.add(event);
                break;
            }
            line = bufferedReader.readLine();
            pointer++;
        }
        NUM_OF_TASK = pointer;
        return Tasks;
    }

    private static void printDelete(Task task) {
        System.out.println("Noted. I've removed this task: ");
        System.out.println("   " + task);
        NUM_OF_TASK--;
        System.out.println("Now you have " + NUM_OF_TASK + " task(s) in the list.");
    }

    private static void printDone(Task task) {
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println("   " + task);
    }

    private static void printConfirm(Task task) {
        System.out.println("Got it! I've added this task:");
        System.out.println("   " + task);
        NUM_OF_TASK++;
        System.out.println("Now you have " + NUM_OF_TASK + " task(s) in the list.");
    }

    private static void printExitMessage() {
        String outro = "Bye. Hope to see you again soon!";
        System.out.println(outro);
        System.exit(0);
    }

    private static void printWelcomeMessage() {
        String tos = "──────────▄▄▄▄▄▄▄▄▄▄▄──────────\n" +
                "─────▄▄▀▀▀▀──────────▀▀▄▄──────\n" +
                "───▄▀───────────────────▀▀▄────\n" +
                "──█────────────────────────█───\n" +
                "─█─────────────────────▄▀▀▀▀▀█▄\n" +
                "█▀────────────────────█────▄███\n" +
                "█─────────────────────█────▀███\n" +
                "█─────▄▀▀██▀▄─────────█───────█\n" +
                "█────█──████─█─────────▀▄▄▄▄▄█─\n" +
                "█────█──▀██▀─█───────────────█─\n" +
                "█────█───────█──────────────▄▀─\n" +
                "█────▀▄─────▄▀──▄▄▄▄▄▄▄▄▄───█──\n" +
                "█──────▀▀▀▀▀────█─█─█─█─█──▄▀──\n" +
                "─█──────────────▀▄█▄█▄█▀──▄▀───\n" +
                "──█──────────────────────▄▀────\n" +
                "───▀▀▀▄──────────▄▄▄▄▄▄▀▀──────\n" +
                "────▄▀─────────▀▀──▄▀──────────\n" +
                "──▄▀───────────────█───────────\n" +
                "─▄▀────────────────█──▄▀▀▀█▀▀▄─\n" +
                "─█────█──█▀▀▀▄─────█▀▀────█──█─\n" +
                "▄█────▀▀▀────█─────█────▀▀───█─\n" +
                "█▀▄──────────█─────█▄────────█─\n" +
                "█──▀▀▀▀▀█▄▄▄▄▀─────▀█▀▀▀▄▄▄▄▀──\n" +
                "█───────────────────▀▄─────────\n";

        System.out.println("What is up my dudes!\n" + tos);

        String intro = " It is I, Bob!\n" +
                " How may I spook you today?";

        System.out.println(LINE);
        System.out.println(intro);
        System.out.println(LINE);
    }

    public static void printList(ArrayList<Task> Task) {
        System.out.println("Here are the tasks in your list: \n");
        for (int i = 0; i < NUM_OF_TASK; i++) {
            int num = i + 1;
            System.out.println(num + ". " + Task.get(i));
        }
    }
}

