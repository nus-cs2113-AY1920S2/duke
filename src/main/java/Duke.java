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
        ui = new Ui(tasks);
    }

    public void run() {
        String[] command;
        String taskType;
        String taskDescription;
        String[] taskDescriptionArgs;
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
                    ui.bye();
                    storage.store();
                    break;
                case "list":
                    ui.list();
                    break;
                case "todo":
                    ensureValidDescription(taskDescription);
                    ui.todo(taskDescription);
                    break;
                case "deadline":
                    ensureValidDescription(taskDescription);
                    taskDescriptionArgs = processArgs(taskDescription);
                    ui.deadline(taskDescriptionArgs);
                    break;
                case "event":
                    ensureValidDescription(taskDescription);
                    taskDescriptionArgs = processArgs(taskDescription);
                    ui.event(taskDescriptionArgs);
                    break;
                case "done":
                    // mark a task as done
                    indexOfTasks = Integer.parseInt(command[1]) - 1;
                    ui.done(indexOfTasks);
                    break;
                case "delete":
                    indexOfTasks = Integer.parseInt(command[1]) - 1;
                    ui.delete(indexOfTasks);
                    break;
                case "find":
                    ensureValidDescription(taskDescription);
                    ui.find(taskDescription);
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
        } while (hasNotSaidBye(String taskType));
    }

    private void ensureValidDescription(String description) throws NoDescriptionException {
        if (description == null || description.isEmpty() || description.isBlank()) {
            throw new NoDescriptionException();
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

    private boolean hasNotSaidBye(String command) {
        return !command.equals("bye");
    }

    /**
     * Close all files used in Duke.
     */
    public void close() {
        try {
            storage.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke("./data/duke.txt");
        duke.run();
        duke.close();
    }
}
