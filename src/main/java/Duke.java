import java.io.IOException;

public class Duke {
    private Parser io;
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filepath) {
        System.out.println("Initialising...");

        io = new Parser();
        tasks = new TaskList();
        storage = new Storage("./data/duke.txt", tasks);
        ui = new Ui();
    }

    public void run() {
        String[] command = new String[2];
        String taskType;
        String taskDescription;
        String[] taskDescriptionArgs;
        String taskDescriptionBy;
        String taskDescriptionAt;
        int indexOfTasks;

        ui.showWelcome("Duke");

        do {
            command = io.readUserInput();
            taskType = command[0];
            taskDescription = command[1];
            ui.showLine();

            try {
                switch (taskType) {
                case "bye":
                    // close the interpreter
                    System.out.println("\tBye. Hope to see you again soon!");
                    storage.store();
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
                case "find":
                    if (taskDescription == null || taskDescription.isEmpty() || taskDescription.isBlank()) {
                        throw new NoDescriptionException();
                    }
                    tasks.find(taskDescription);
                    break;
                default:
                    throw new IllegalArgumentException();
                }
            } catch (NoDescriptionException m) {
                System.out.println("\t \u2639 OOPS!!! The description of a " + taskType + " cannot be empty.");
            } catch (IllegalArgumentException m) {
                System.out.println("\t \u2639 OOPS!!! I'm sorry, but I don't know what that means :-(");
            } catch (IndexOutOfBoundsException m) {
                System.out.println("\t \u2639 OOPS!!! " + m);
            } catch (IOException m) {
                System.out.println(m);
            }
            ui.showLine();
        } while (!taskType.equals("bye"));

        try {
            storage.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private String[] processArgs(String s) {
        String[] tokens = new String[2];
        if (s.indexOf('/') == -1) {
            tokens[0] = s.trim();
            tokens[1] = "";
        } else {
            tokens = s.split("/");
            tokens[0] = tokens[0].trim();
            int indexAfterSpace = tokens[1].indexOf(' ') + 1;
            if (indexAfterSpace == 0) {
                // if nothing after /??
                tokens[1] = "";
            } else {
                tokens[1] = tokens[1].substring(indexAfterSpace);
            }
        }
        return tokens;
    }

    public static void main(String[] args) {
        new Duke("./data/duke.txt").run();
    }
}
