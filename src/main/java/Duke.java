import java.io.IOException;
import java.util.Scanner;
import java.io.File;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Initialising...");

        Scanner reader = new Scanner(System.in);
        String input;
        String[] command = new String[2];
        String taskType;
        String taskDescription;
        String[] taskDescriptionArgs;
        String taskDescriptionBy;
        String taskDescriptionAt;
        TaskList tasks = new TaskList();
        int indexOfTasks;

        // Indicate location to store tasks in an external file
        // ASSUMPTION: working directory is .../duke/
        File f = new File("./data/duke.txt");
        if (!f.exists()) {
            System.out.println("Storage file not found.");
            try {
                new File(f.getParent()).mkdir();    // mkdir
                f.createNewFile();
                System.out.println("A storage file is created.");
            } catch (IOException m) {
                System.out.println("... but storage file already exists??");
            }
        }

        FileIO file = new FileIO(f, tasks);

        String name = "Duke";
        welcome(name);

        do {
            input = reader.nextLine();
            command = inputProcessing(input);
            taskType = command[0];
            taskDescription = command[1];
            printLine();

            try {
                switch (taskType) {
                case "bye":
                    // close the interpreter
                    System.out.println("\tBye. Hope to see you again soon!");
                    file.storeAllFrom(tasks);
                    break;
                case "list":
                    tasks.list();
                    break;
                case "todo":
                    if (taskDescription == null || taskDescription.isEmpty() || taskDescription.isBlank()) {
                        throw new NoDescriptionException();
                    }
                    tasks.add(new ToDo(taskDescription));
                    break;
                case "deadline":
                    if (taskDescription == null || taskDescription.isEmpty() || taskDescription.isBlank()) {
                        throw new NoDescriptionException();
                    }
                    taskDescriptionArgs = processArgs(taskDescription);
                    taskDescription = taskDescriptionArgs[0];
                    taskDescriptionBy = taskDescriptionArgs[1];
                    tasks.add(new Deadline(taskDescription, taskDescriptionBy));
                    break;
                case "event":
                    if (taskDescription == null || taskDescription.isEmpty() || taskDescription.isBlank()) {
                        throw new NoDescriptionException();
                    }
                    taskDescriptionArgs = processArgs(taskDescription);
                    taskDescription = taskDescriptionArgs[0];
                    taskDescriptionAt = taskDescriptionArgs[1];
                    tasks.add(new Event(taskDescription, taskDescriptionAt));
                    break;
                case "done":
                    // mark a task as done
                    indexOfTasks = Integer.parseInt(command[1]) - 1;
                    tasks.setDoneByIndex(indexOfTasks);
                    System.out.println("\tNice! I've marked this task as done:");
                    System.out.println("\t" + tasks.getByIndex(indexOfTasks));
                    break;
                case "delete":
                    indexOfTasks = Integer.parseInt(command[1]) - 1;
                    Task removedTask = tasks.getByIndex(indexOfTasks);
                    tasks.removeByIndex(indexOfTasks);
                    System.out.println("\tNoted. I've removed this task:");
                    System.out.println("\t  " + removedTask);
                    tasks.printSize();
                    break;
                default:
                    throw new IllegalArgumentException();
                }
            } catch (NoDescriptionException m) {
                System.out.println("\t ☹ OOPS!!! The description of a " + taskType + " cannot be empty.");
            } catch (IllegalArgumentException m) {
                System.out.println("\t ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            } catch (IndexOutOfBoundsException m) {
                System.out.println("\t ☹ OOPS!!! " + m);
            } catch (IOException m) {
                System.out.println(m);
            }
            printLine();
        } while (!taskType.equals("bye"));

        try {
            file.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * Split first word from the rest of String.
     * @param input: one-line string
     * @return array of 2 String, [0]: first word, [1]: subsequent words.
     */
    private static String[] inputProcessing(String input) {
        String[] output = new String[2];

        input = input.trim();   // strip leading & trailing spaces
        int startIndexOfDescription = input.indexOf(' ');

        if (startIndexOfDescription == -1) {
            // only has 1 word
            output[0] = input.toLowerCase();
        } else {
            // has 2 words
            String cmdType = input.substring(0, startIndexOfDescription).toLowerCase();
            String cmdDescription = input.substring(startIndexOfDescription + 1);
            output[0] = cmdType;
            output[1] = cmdDescription;
        }

        return output;
    }

    public static void printLine() {
        System.out.println("\t____________________________________________________________");
    }

    public static void welcome(String name) {
        printLine();
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("\tHello from\n" + logo);

        printLine();
        System.out.println("\tHello! I'm " + name);
        System.out.println("\tWhat can I do for you?");
        printLine();
    }

    private static String[] processArgs(String s) {
        String[] tokens = new String[2];
        if (s.indexOf('/') == -1) {
            tokens[0] = s.trim();
            tokens[1] = "";
        } else {
            tokens = s.split("/");
            tokens[0] = tokens[0].trim();
            tokens[1] = tokens[1].substring(tokens[1].indexOf(' ') + 1);
        }
        return tokens;
    }
}
