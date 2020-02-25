package duke;

import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Duke {
    private static final String FOUR_SPACE_INDENT = "    ";
    private static final String SIX_SPACE_INDENT = "      ";
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static int taskIndex;
    private static Scanner in = new Scanner(System.in);
    private static boolean isExiting = false;

    public static void main(String[] args) {
        loadDataFromDisk();
        greet();
        takeCommands();
        exit();
    }

    private static void loadDataFromDisk() {
        String description;
        int dividerIndex;
        try {
            File dir = new File("data");
            if (!dir.exists()) {
                System.out.println(FOUR_SPACE_INDENT + "Making directory: \"data\"");
                dir.mkdir();
            }
            File file = new File("data/duke.txt");
            if (!file.exists()) {
                System.out.println(FOUR_SPACE_INDENT + "Creating file: \"duke.txt\"");
            }
            file.createNewFile();

            System.out.println(FOUR_SPACE_INDENT + "Loading tasks from \"data/duke.txt\", if there are any...");
            Scanner s = new Scanner(file);
            while (s.hasNext()) {
                String line = s.nextLine();
                switch (line.charAt(1)) {
                case 'T':
                    description = line.substring(7);
                    tasks.add(new ToDo(description));
                    break;
                case 'D':
                    dividerIndex = line.indexOf("(by");
                    description = line.substring(7, (dividerIndex - 1));
                    String by = line.substring(dividerIndex + 5, line.length() - 1);
                    tasks.add(new Deadline(description, by));
                    break;
                case 'E':
                    dividerIndex = line.indexOf("(at");
                    description = line.substring(7, (dividerIndex - 1));
                    String at = line.substring(dividerIndex + 5, line.length() - 1);
                    tasks.add(new Event(description, at));
                    break;
                default:
                    break;
                }
                if (line.charAt(4) == '\u2713') {
                    tasks.get(tasks.size() - 1).markAsDone();
                }
            }
            System.out.println(FOUR_SPACE_INDENT + "Loading done." + "\n");
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    private static void takeCommands() {
        String command;
        while(!isExiting) {
            try {
                command = getCommand();
                executeCommand(command);
            } catch (InvalidCommandException e) {
                System.out.print("\n");
                System.out.println(FOUR_SPACE_INDENT + "Sorry, me don't know what that means :-(");
                if (e.getMessage() != null) {
                    System.out.print(FOUR_SPACE_INDENT);
                    System.out.println(e.getMessage());
                }
            } catch (NumberFormatException e) {
                System.out.print("\n");
                System.out.println(FOUR_SPACE_INDENT + "Index must be an integer, like \"1\", but not \"one\".");
            } catch (TaskIndexOutOfBoundsException e) {
                System.out.print("\n");
                System.out.println(FOUR_SPACE_INDENT + "That index is outta range!");
            }
        }
    }


    private static void executeCommand(String command) {
        String[] commandSubstrings = command.split("\\s+");
        if (command.equals("bye")) {
            isExiting = true;
        } else if (command.equals("list")) {
            listTasks();
        } else if (commandSubstrings[0].equals("done")) {
            checkOffTask();
            saveTaskstoDisk();
        } else if (commandSubstrings[0].equals("delete")) {
            deleteTask();
            saveTaskstoDisk();
        } else if (commandSubstrings[0].equals("todo")) {
            addTodo(command.substring(5).trim());
            saveTaskstoDisk();
        } else if (commandSubstrings[0].equals("deadline")) {
            addDeadline(command.substring(9));
            saveTaskstoDisk();
        } else if (commandSubstrings[0].equals("event")) {
            addEvent(command.substring(6));
            saveTaskstoDisk();
        }
    }

    private static void saveTaskstoDisk() {
        try {
            FileWriter fw = new FileWriter("data/duke.txt");
            for (Task task : tasks) {
                fw.write(task.toString() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    private static void deleteTask() {
        System.out.print("\n");
        System.out.println(FOUR_SPACE_INDENT + "Noted. I've removed this task:");
        System.out.println(SIX_SPACE_INDENT + tasks.get(taskIndex - 1).toString());
        tasks.remove(taskIndex - 1);
        System.out.println(FOUR_SPACE_INDENT + "Now you have " + tasks.size() + " tasks in the list.");
    }

    private static void addEvent(String info) {
        int dividerIndex = info.indexOf("/at");
        String description = info.substring(0, (dividerIndex - 1)).trim();
        String at = info.substring(dividerIndex + 4).trim();
        tasks.add(new Event(description, at));
        printAddedTask();
    }

    private static void addDeadline(String info) {
        int dividerIndex = info.indexOf("/by");
        String description = info.substring(0, (dividerIndex - 1)).trim();
        String by = info.substring(dividerIndex + 4).trim();
        tasks.add(new Deadline(description, by));
        printAddedTask();
    }

    private static void addTodo(String description) {
        tasks.add(new ToDo(description));
        printAddedTask();
    }

    private static void printAddedTask() {
        System.out.print("\n");
        System.out.println(FOUR_SPACE_INDENT + "Got it. I've added this task:");
        System.out.println(SIX_SPACE_INDENT + tasks.get(tasks.size() - 1).toString());
        System.out.println(FOUR_SPACE_INDENT + "Now you have " + tasks.size() + " tasks in the list.");
    }

    private static void checkOffTask() {
        tasks.get(taskIndex - 1).markAsDone();
        System.out.print("\n");
        System.out.println(FOUR_SPACE_INDENT + "Nice! I've marked this task as done:");
        System.out.println(SIX_SPACE_INDENT + tasks.get(taskIndex - 1).toString());
    }

    private static void listTasks() {
        int bulletNum = 1;
        System.out.print("\n");
        System.out.println(FOUR_SPACE_INDENT + "Here are the tasks in your list:");
        for (Task task : tasks) {
            System.out.println(FOUR_SPACE_INDENT + bulletNum + "." + task.toString());
            bulletNum++;
        }
    }

    private static String getCommand() throws InvalidCommandException, TaskIndexOutOfBoundsException {
        String input = "";
        while (input.isEmpty()) {
            input = in.nextLine();
            input = input.trim();
        }
        // Input is not empty
        String[] inputSubstrings = input.split("\\s+");
        if (inputSubstrings.length == 1) {
            if (!inputSubstrings[0].equals("list") && !inputSubstrings[0].equals("bye")) {
                throw new InvalidCommandException();
            }
        } else if (!inputSubstrings[0].equals("done") && !inputSubstrings[0].equals("todo") &&
                !inputSubstrings[0].equals("deadline") && !inputSubstrings[0].equals("event") &&
                !inputSubstrings[0].equals("delete")) {
            throw new InvalidCommandException();
        } else if (inputSubstrings[0].equals("done")) {
            if (inputSubstrings.length > 2) {
                throw new InvalidCommandException("Format: \"done <task index>\"");
            } else {
               taskIndex = Integer.parseInt(inputSubstrings[1]);
               if (taskIndex < 1 || taskIndex > tasks.size()) {
                   throw new TaskIndexOutOfBoundsException();
               }
            }
        } else if (inputSubstrings[0].equals("delete")) {
            if (inputSubstrings.length > 2) {
                throw new InvalidCommandException("Format: \"delete <task index>\"");
            } else {
                taskIndex = Integer.parseInt(inputSubstrings[1]);
                if (taskIndex < 1 || taskIndex > tasks.size()) {
                    throw new TaskIndexOutOfBoundsException();
                }
            }
        } else if (inputSubstrings[0].equals("deadline")) {
            if (inputSubstrings.length < 4) {
                throw new InvalidCommandException("Format: \"deadline <description> /by <deadline>\"");
            }
            for (int i = 1; i < inputSubstrings.length; i++) {
                if (i == (inputSubstrings.length - 1)) {
                    throw new InvalidCommandException("Format: \"deadline <description> /by <deadline>\"");
                }
                if (inputSubstrings[i].equals("/by")) {
                    if (i == 1) {
                        throw new InvalidCommandException("Format: \"deadline <description> /by <deadline>\"");
                    } else {
                        break;
                    }
                }
            }
        } else if (inputSubstrings[0].equals("event")) {
            if (inputSubstrings.length < 4) {
                throw new InvalidCommandException("Format: \"event <description> /at <date/time>\"");
            }
            for (int i = 1; i < inputSubstrings.length; i++) {
                if (i == (inputSubstrings.length - 1)) {
                    throw new InvalidCommandException("Format: \"event <description> /at <date/time>\"");
                }
                if (inputSubstrings[i].equals("/at")) {
                    if (i == 1) {
                        throw new InvalidCommandException("Format: \"event <description> /at <date/time>\"");
                    } else {
                        break;
                    }
                }
            }
        }
        return input;
    }

    private static void exit() {
        System.out.print("\n");
        System.out.println(FOUR_SPACE_INDENT + "Ok, see ya!");
    }

    private static void greet() {
        System.out.println(FOUR_SPACE_INDENT + "Hello, I'm Taskmaster Yipyap.");
        System.out.println(FOUR_SPACE_INDENT + "I can help manage your tasks, and save them automatically!");
        System.out.println(FOUR_SPACE_INDENT + "What can I do for you, human?");
    }
}
