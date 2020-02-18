import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static void main(String[] args) {
        printWelcomeMessage();
        boolean isExit = false;
        ArrayList<Task> taskList = new ArrayList<>();

        Scanner in = new Scanner(System.in);
        String input = in.nextLine(); // Get string input

        while (!input.equals("bye")) {
            String[] words = input.split(" ", 2); // Split command from rest of sentence
            try {
                manageCommand(taskList, input, words);
            } catch (EmptyToDoException e) {
                System.out.println("    ☹ OOPS!!! The description of a todo cannot be empty.");
            } catch (InvalidTaskIndexException e) {
                System.out.println("    ☹ OOPS!!! The task index you've entered is invalid.");
            } catch (EmptyDoneException e) {
                System.out.println("    ☹ OOPS!!! The description of the task cannot be empty.");
            }  catch (InvalidCommandException e) {
                System.out.println("    ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            } catch (InvalidDeadlineException e) {
                System.out.println("    ☹ OOPS!!! The description of a deadline must be in this format: ");
                System.out.println("    deadline <task name> /by <date/time>");
            } catch (InvalidEventException e) {
                System.out.println("    ☹ OOPS!!! The description of an event must be in this format: ");
                System.out.println("    event <task name> /at <date/time>");
            } catch (EmptyListException e) {
                System.out.println("    ☹ OOPS!!! Your task list is empty! Try adding a task first!");
            } catch (NumberFormatException e) {
                System.out.println("    ☹ OOPS!!! You entered an invalid number!");
            }

            input = in.nextLine(); // Get string input
        }
        printExitMessage(); // Exit


    }

    private static void manageCommand(ArrayList<Task> taskList, String input, String[] words) throws NumberFormatException, EmptyToDoException, EmptyDoneException, EmptyListException, InvalidTaskIndexException, InvalidCommandException, InvalidDeadlineException, InvalidEventException {
        if (words[0].equals("list")) { // List tasks
            if (taskList.isEmpty() ) {
                throw new EmptyListException();
            }
            printListMessage(taskList);
        } else if (words[0].equals("done")) { // Mark task as done
            if (words.length < 2) {
                throw new EmptyDoneException();
            }
            String doneTask = words[1];
            int taskIndex = Integer.parseInt(doneTask) - 1;
            if ((taskIndex < 0) || (taskIndex >= taskList.size() ) ) {
                throw new InvalidTaskIndexException();
            }
            taskList.get(taskIndex).setDone(true);
            printDoneMessage(taskList, taskIndex);
        } else if (words[0].equals("deadline")) { // Deadline
            if ((words.length < 2) || !words[1].contains(" /by ")) {
                throw new InvalidDeadlineException();
            }
            String[] deadlineWords = words[1].split(" /by ", 2); // Split task and date/time
            String deadlineTask = deadlineWords[0];
            String deadlineBy = deadlineWords[1];
            taskList.add(new Deadline(deadlineTask, deadlineBy));
            printBorder();
            printTaskAdded(taskList);
            printListCount(taskList);
            printBorder();
        } else if (words[0].equals("event")) { // Event
            if ((words.length < 2) || !words[1].contains(" /at ")) {
                throw new InvalidEventException();
            }
            String[] eventWords = words[1].split(" /at ", 2); // Split task and date/time
            String eventTask = eventWords[0];
            String eventAt = eventWords[1];
            taskList.add(new Event(eventTask, eventAt));
            printBorder();
            printTaskAdded(taskList);
            printListCount(taskList);
            printBorder();
        } else if (words[0].equals("todo")) { // ToDo
            if (words.length < 2) {
                throw new EmptyToDoException();
            }
            String toDoTask = words[1];
            taskList.add(new ToDo(toDoTask));
            printBorder();
            printTaskAdded(taskList);
            printListCount(taskList);
            printBorder();
        } else if (words[0].equals("delete")) {
            if(taskList.isEmpty()){
                throw new EmptyListException();
            }
            String deleteTask = words[1];
            int taskIndex = Integer.parseInt(deleteTask) - 1;
            if ((taskIndex < 0) || (taskIndex >= taskList.size() ) ) {
                throw new InvalidTaskIndexException();
            }
            printDeleteMessage(taskList, taskIndex);
            taskList.remove(taskList.get(taskIndex) );
            printListCount(taskList);
        } else {
            throw new InvalidCommandException();
        }
    }

    private static void printDeleteMessage(ArrayList<Task> taskList, int taskIndex) {
        printBorder();
        System.out.println("    Noted. I've removed this task: ");
        System.out.println("    " + taskList.get(taskIndex).toString()); // Print task deleted
        printBorder();
    }

    private static void printDoneMessage(ArrayList<Task> taskList, int taskIndex) {
        printBorder();
        System.out.println("    Nice! I've marked this task as done: ");
        System.out.println("    " + taskList.get(taskIndex).toString()); // Print task marked as done
        printBorder();
    }

    private static void printListMessage(ArrayList<Task> taskList) {
        printBorder();
        System.out.println("    Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); ++i) { // Print list of tasks
            System.out.println("    " + (i + 1) + ". " + taskList.get(i).toString());
        }
        printBorder();
    }

    private static void printListCount(ArrayList<Task> taskList) {
        System.out.println("    Now you have " + taskList.size() + " tasks in the list."); // Print list count
    }

    private static void printTaskAdded(ArrayList<Task> taskList) {
        System.out.println("    Got it. I've added this task: ");
        System.out.println("      " + taskList.get(taskList.size() - 1) ); // Print task info
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