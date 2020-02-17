package duke;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    //private static Task[] tasks = new Task[100];
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static int taskQty = 0;
    private static final String LINE = "____________________________________________________________ \n";
    private static final String LIST = "list";
    private static final String DONE = "done";
    private static final String TODO = "todo";
    private static final String DEADLINE = "deadline";
    private static final String EVENT = "event";
    private static final String DELETE = "delete";

    public static void main(String[] args) {

        try {
            readFromFile();
        } catch (IOException e) {
            System.out.println("error reading");
        }

        System.out.println(LINE +
                " Hello! I'm duke.Duke :)\n" +
                " What can I do for you?\n" + LINE);

        Scanner scanner = new Scanner(System.in);
        //String inputString;

        while (true) {
            String inputString = scanner.nextLine();
            if (inputString.equals("bye bye")) {
                break;
            } else {
                runCommands(inputString);
            }
        }

        System.out.println(" Bye. Hope to see you again soon!\n" + LINE);
    }

    private static void runCommands(String inputString) {
        //String inputString;
        //inputString = scanner.nextLine();
        String instruction[] = inputString.split(" ", 2);
        Task new_task;

        switch (instruction[0]) {
        case (LIST):
            printList(tasks);
            break;

        case (DONE):
            try {
                int index = Integer.parseInt((instruction[1]));
                System.out.println(index);
                if (tasks.get(index - 1).getIsDone() == false) {
                    tasks.get(index - 1).markAsDone();
                    System.out.println(LINE + "  Yay! You have done: " + tasks.get(index - 1).getDescription() + "\n" + LINE);
                } else {
                    System.out.println("You have already done this task!");
                }
                try {
                    saveToFile();
                } catch (IOException e) {
                    System.out.println("ERROR");
                }
            } catch (NullPointerException e) {
                System.out.println("This task does not exist!");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("This task does not exist!");
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("Please specify a task number!");
            }
            break;

        case (DELETE):
            try {
                int index = Integer.parseInt((instruction[1]));
                System.out.println(LINE + "  You have deleted: " + tasks.get(index - 1).getDescription() + "\n" + LINE);
                tasks.remove(index - 1);
                try {
                    saveToFile();
                } catch (IOException e) {
                    System.out.println("ERROR");
                }
            } catch (NullPointerException e) {
                System.out.println("This task does not exist!");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("This task does not exist!");
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("Please specify a task number!");
            }
            break;

        case (TODO):
            try {
                new_task = new Todo(instruction[1]);
                tasks.add(new_task);
                taskQty += 1;
                try {
                    saveToFile();
                } catch (IOException e) {
                    System.out.println("ERROR");
                }

                printAddMessage();
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("There is no task specified!");
            }
            break;

        case (DEADLINE):
            try {
                int findSeparator = instruction[1].indexOf('/');
                new_task = new Deadline(instruction[1].substring(0, findSeparator), instruction[1].substring(findSeparator + 1));
                tasks.add(new_task);
                taskQty++;
                printAddMessage();
                try {
                    saveToFile();
                } catch (IOException e) {
                    System.out.println("ERROR");
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("There is no task specified!");
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("Please input task in the format: deadline task_name/deadline");
            } catch (DukeException e) {
                System.out.println("Task description or deadline field is empty.");
            }
            break;

        case (EVENT):
            try {
                int findSeparator = instruction[1].indexOf('/');
                new_task = new Event(instruction[1].substring(0, findSeparator), instruction[1].substring(findSeparator + 1));
                tasks.add(new_task);
                taskQty++;
                printAddMessage();
                try {
                    saveToFile();
                } catch (IOException e) {
                    System.out.println("ERROR");
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("There is no task specified!");
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("Please input task in the format: event task_name/event_date");
            } catch (DukeException e) {
                System.out.println("Task description or event date field is empty.");
            }
            break;

        default:
            System.out.println("huh? I do not understand :( \n" +
                    "I can help you with the following: \n" +
                    "[list] - lists tasks \n" +
                    "[todo <task_name>] - adds a todo task \n" +
                    "[deadline <task_name> / deadline] - adds a deadline task \n" +
                    "[event <event_name> / event_date] - adds an event \n" +
                    "[done <task_num>] - marks a task as done \n" +
                    "[bye bye] - exits duke \n" + LINE);
            break;
        }
    }

    public static void printList(ArrayList<Task> tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.print(String.valueOf(i + 1) + ". ");
            System.out.println(tasks.get(i));
        }
    }

    private static void printAddMessage() {
        System.out.println(LINE +
                "  Ok! I've added this task. \n" +
                "  Now you have " + String.valueOf(taskQty) + " tasks on your list.\n " +
                LINE);
    }

    private static void saveToFile() throws IOException {
        //FileWriter writer= new FileWriter("savedDate.txt");
        File file = new File("save.txt");

        FileOutputStream fo = new FileOutputStream(file);
        PrintWriter pw = new PrintWriter(fo);

        for (Task task : tasks) {
            pw.println(task.saveFormat());
        }
        pw.close();
        fo.close();
    }

    private static void readFromFile() throws IOException {
        File file = new File("save.txt");
        Scanner scanner = new Scanner(file);
        try {
            while (scanner.hasNextLine()) {
                String task = scanner.nextLine();
                String fields[] = task.split("//");
                /*for(String s : fields) {
                    System.out.println(s);
                }*/
                switch (fields[0]) {
                case ("t"):
                    tasks.add(new Todo(fields[2]));
                    if (fields[1].equals("true")) {
                        tasks.get(taskQty).markAsDone();
                    }
                    taskQty++;
                    break;
                case ("d"):
                    tasks.add(new Deadline(fields[2], fields[3]));
                    if (fields[1] == "true") {
                        tasks.get(taskQty).markAsDone();
                    }
                    taskQty++;
                    break;
                case ("e"):
                    tasks.add(new Event(fields[2], fields[3]));
                    if (fields[1] == "true") {
                        tasks.get(taskQty).markAsDone();
                    }
                    taskQty++;
                    break;
                }
            }
        } catch (DukeException e) {
            System.out.println("error");
        }
        scanner.close();
    }
}
