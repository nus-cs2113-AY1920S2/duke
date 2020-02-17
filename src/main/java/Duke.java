import java.util.Scanner;

public class Duke {

    public static final int MAX_TASK = 100;

    public static void main(String[] args) {
        printWelcomeMessage();
        boolean isExit = false;
        Task[] list = new Task[MAX_TASK];
        int itemCount = 0;

        Scanner in = new Scanner(System.in);
        String input = in.nextLine(); // Get string input

        while (!input.equals("bye")) {
            String[] words = input.split(" ", 2); // Split command from rest of sentence
            try {
                itemCount = manageCommand(list, itemCount, input, words);
            } catch (EmptyToDoException e) {
                System.out.println("    ☹ OOPS!!! The description of a todo cannot be empty.");
            } catch (InvalidCommandException e) {
                System.out.println("    ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            } catch (InvalidDeadlineException e) {
                System.out.println("    ☹ OOPS!!! The description of a deadline must be in this format: ");
                System.out.println("    deadline <task name> /by <date/time>");
            } catch (InvalidEventException e) {
                System.out.println("    ☹ OOPS!!! The description of an event must be in this format: ");
                System.out.println("    event <task name> /at <date/time>");
            }

            input = in.nextLine(); // Get string input
        }
        printExitMessage(); // Exit


    }

    private static int manageCommand(Task[] list, int itemCount, String input, String[] words) throws EmptyToDoException, InvalidCommandException, InvalidDeadlineException, InvalidEventException {
        if (words[0].equals("list")) { // List tasks
            printBorder();
            System.out.println("    Here are the tasks in your list:");
            for (int i = 0; i < itemCount; ++i) { // Print list of tasks
                System.out.println("    " + (i + 1) + ". " + list[i].toString());
            }
            printBorder();
        } else if (words[0].equals("done")) { // Mark task as done
            printBorder();
            System.out.println("    Nice! I've marked this task as done: ");
            String doneTask = words[1];
            int taskIndex = Integer.parseInt(doneTask) - 1;
            list[taskIndex].setDone(true);
            System.out.println("    " + list[taskIndex].toString()); // Print task marked as done
            printBorder();
        } else if (words[0].equals("deadline")) { // Deadline
            if ((words.length < 2) || !words[1].contains(" /by ")) {
                throw new InvalidDeadlineException();
            }
            String[] deadlineWords = words[1].split(" /by ", 2); // Split task and date/time
            String deadlineTask = deadlineWords[0];
            String deadlineBy = deadlineWords[1];
            list[itemCount] = new Deadline(deadlineTask, deadlineBy);
            printBorder();
            printTaskAdded(list, itemCount);
            itemCount++;
            printListCount(itemCount);
            printBorder();
        } else if (words[0].equals("event")) { // Event
            if ((words.length < 2) || !words[1].contains(" /at ")) {
                throw new InvalidEventException();
            }
            String[] eventWords = words[1].split(" /at ", 2); // Split task and date/time
            String eventTask = eventWords[0];
            String eventAt = eventWords[1];
            list[itemCount] = new Event(eventTask, eventAt);
            printBorder();
            printTaskAdded(list, itemCount);
            itemCount++;
            printListCount(itemCount);
            printBorder();
        } else if (words[0].equals("todo")) { // ToDo
            if (words.length < 2) {
                throw new EmptyToDoException();
            }
            String toDoTask = words[1];
            list[itemCount] = new ToDo(toDoTask);
            printBorder();
            printTaskAdded(list, itemCount);
            itemCount++;
            printListCount(itemCount);
            printBorder();
        } else {
            throw new InvalidCommandException();
        }
        return itemCount;
    }

    private static void printListCount(int itemCount) {
        System.out.println("    Now you have " + itemCount + " tasks in the list."); // Print list count
    }

    private static void printTaskAdded(Task[] list, int itemCount) {
        System.out.println("    Got it. I've added this task: ");
        System.out.println("      " + list[itemCount].toString()); // Print task info
    }

    private static void printBorder() {
        System.out.println("    ____________________________________________________________");
    }

    private static void printExitMessage() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(" _                \n"
                + "| |               \n"
                + "| |__  _   _  ___ \n"
                + "| '_ \\| | | |/ _ \\\n"
                + "| |_) | |_| |  __/\n"
                + "|_.__/ \\__, |\\___|\n"
                + "        __/ |     \n"
                + "       |___/      \n");
    }

    private static void printWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
    }
}