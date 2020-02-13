import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String name = "Duke";
        welcome(name);

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
                    System.out.println("\tNice! I've marked this task as done:");
                    indexOfTasks = Integer.parseInt(command[1]) - 1;
                    tasks.setDoneByIndex(indexOfTasks);
                    System.out.println("\t" + tasks.getByIndex(indexOfTasks));
                    break;
                case "delete":
                    System.out.println("\tNoted. I've removed this task:");
                    indexOfTasks = Integer.parseInt(command[1]) - 1;
                    Task removedTask = tasks.getByIndex(indexOfTasks);
                    tasks.removeByIndex(indexOfTasks);
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
            }
            printLine();
        } while (!taskType.equals("bye")) ;
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
        String[] tokens = s.split("/");
        tokens[0] = tokens[0].trim();
        tokens[1] = tokens[1].substring(tokens[1].indexOf(' ') + 1);
        return tokens;
    }
}
