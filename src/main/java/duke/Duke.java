package duke;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String FOUR_SPACE_INDENT = "    ";
    private static final String SIX_SPACE_INDENT = "      ";
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static int taskIndex;
    private static Scanner in = new Scanner(System.in);
    private static boolean isExiting = false;

    public static void main(String[] args) {
        greet();
        takeCommands();
        exit();
    }

    private static void takeCommands() {
        String command;
        while(!isExiting) {
            try {
                command = getCommand();
                executeCommand(command);
            } catch (InvalidCommandException e) {
                System.out.print("\n" + FOUR_SPACE_INDENT);
                System.out.println("Sorry, me don't know what that means :-(");
                if (e.getMessage() != null) {
                    System.out.print(FOUR_SPACE_INDENT);
                    System.out.println(e.getMessage());
                }
            } catch (NumberFormatException e) {
                System.out.print("\n" + FOUR_SPACE_INDENT);
                System.out.println("Sorry, me don't know what that means :-(");
                System.out.print(FOUR_SPACE_INDENT);
                System.out.println("Format: \"done <task index>\"");
            } catch (TaskIndexOutOfBoundsException e) {
                System.out.print("\n" + FOUR_SPACE_INDENT);
                System.out.println("I need a valid task index!");
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
        } else if (commandSubstrings[0].equals("todo")) {
            addTodo(command.substring(5).trim());
        } else if (commandSubstrings[0].equals("deadline")) {
            addDeadline(command.substring(9));
        } else if (commandSubstrings[0].equals("event")) {
            addEvent(command.substring(6));
        }
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
                !inputSubstrings[0].equals("deadline") && !inputSubstrings[0].equals("event")) {
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
        System.out.println(FOUR_SPACE_INDENT + "Bye human! See you next time!");
    }

    private static void greet() {
        System.out.println(FOUR_SPACE_INDENT + "Hello, I'm Taskmaster Yipyap.");
        System.out.println(FOUR_SPACE_INDENT + "What can I do for you, human?");
    }
}
