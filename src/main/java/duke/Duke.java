package duke;

import duke.command.Deadline;
import duke.command.Event;
import duke.command.Task;
import duke.command.Todo;
import duke.exception.*;

import java.util.Scanner;

public class Duke {

    public static final String DIVIDER = "_________________________________________________";
    public static final String DONE_COMMAND = "done";
    public static final String LIST_COMMAND = "list";
    public static final String TODO_COMMAND = "todo";
    public static final String EVENT_COMMAND = "event";
    public static final String DEADLINE_COMMAND = "deadline";
    public static final String END_COMMAND = "bye";
    public static final String END_MESSAGE = "Bob thanks you for coming! See you again soon!";


    public static void printIndividualTask(Task[] tasks, int taskNum) {
        if (tasks[taskNum - 1].getTaskDescription().equals(TODO_COMMAND)) {
            System.out.println("Got it! You've added a todo task: ");
            System.out.println(tasks[taskNum - 1]);
            System.out.println(taskValidator(taskNum));
        } else if (tasks[taskNum - 1].getTaskDescription().equals(DEADLINE_COMMAND)) {
            System.out.println("Got it! You've added a deadline task: ");
            System.out.println(tasks[taskNum - 1]);
            System.out.println(taskValidator(taskNum));
        } else if (tasks[taskNum - 1].getTaskDescription().equals(EVENT_COMMAND)) {
            System.out.println("Got it! You've added an event task: ");
            System.out.println(tasks[taskNum - 1]);
            System.out.println(taskValidator(taskNum));
        }
    }

    public static void printTaskList(Task[] tasks) throws EmptyListException {
        int sizeofArray = getArraySize(tasks);
        if (sizeofArray != 0) {
            System.out.println("Here are the tasks on your list: ");
            for (int i = 1; i < sizeofArray + 1; i++) {
                String taskNum = Integer.toString(i);
                System.out.println(taskNum + "." + tasks[i - 1]);
            }
        } else {
            throw new EmptyListException("There are no tasks in the list! Please add some tasks!");
        }
    }

    public static int getArraySize(Task[] tasks) {
        int sizeOfArray = 0;
        for (int i = 0; i < tasks.length; i++) {
            if (tasks[i] != null) {
                sizeOfArray++;
            } else {
                return sizeOfArray;
            }
        }
        return sizeOfArray;
    }

    public static void executeDoneTask(Task[] tasks, String userInput) throws MissingTaskNumberException, MissingTaskNumberDescriptionException {
        if (!userInput.trim().equals(DONE_COMMAND)) {
            String[] words = userInput.split(" ");
            int taskNum = Integer.parseInt(words[1]);
            int sizeOfArray = getArraySize(tasks);
            if (taskNum <= sizeOfArray) {
                tasks[taskNum - 1].markAsDone();
                System.out.println("Awesome! I've marked the following task as done:");
                System.out.println(tasks[taskNum - 1]);
            } else {
                throw new MissingTaskNumberException("This task number does not exist on the list!");
            }
        } else {
            throw new MissingTaskNumberDescriptionException("Please add a task number to \'done\' to mark task as done!");
        }
    }


    public static String taskValidator(int numTasks) {
        String totalTasks = Integer.toString(numTasks);
        if (numTasks <= 1) {
            return "You now have " + totalTasks + " task in the list!";
        } else {
            return "You now have " + totalTasks+ " tasks in the list!";
        }
    }


    public static void executeToDo(Task[] tasks, String userInput) throws MissingTaskException {
        if (!userInput.trim().equals(TODO_COMMAND)) {
            String todoTask = userInput.substring(TODO_COMMAND.length() + 1);
            Task todo = new Todo(todoTask);
            tasks[todo.getTotalTasks()-1] = todo;
            printIndividualTask(tasks,todo.getTotalTasks());
        } else {
            throw new MissingTaskException("Todo tasks cannot be empty!");
        }
    }

    public static void executeEvent(Task[] tasks, String userInput) throws MissingTaskException, MissingEventDateException {
        if (!userInput.trim().equals(EVENT_COMMAND)) {
            int indexOfAt = userInput.indexOf("/at");
            if (indexOfAt == -1) {
                throw new MissingEventDateException("Please specify the date for event using \'at\'!");
            }
            String eventTask = userInput.substring(EVENT_COMMAND.length() + 1, indexOfAt - 1);
            String atDate = userInput.substring(indexOfAt + "/at".length() + 1);
            Task event = new Event(eventTask, atDate);
            tasks[event.getTotalTasks()-1] = event;
            printIndividualTask(tasks,event.getTotalTasks());
        } else {
            throw new MissingTaskException("Event tasks cannot be empty!");
        }
    }

    public static void executeDeadline(Task[] tasks, String userInput) throws MissingTaskException, MissingDeadlineDateException {
        if (!userInput.trim().equals(DEADLINE_COMMAND)) {
            int indexOfBy = userInput.indexOf("/by");
            if (indexOfBy == -1) {
                throw new MissingDeadlineDateException("Please specify a deadline using \'/by\'!");
            }
            String deadlineTask = userInput.substring(DEADLINE_COMMAND.length() + 1, indexOfBy - 1);
            String byDate = userInput.substring(indexOfBy + "/by".length() + 1);
            Task deadline = new Deadline(deadlineTask, byDate);
            tasks[deadline.getTotalTasks()-1] = deadline;
            printIndividualTask(tasks,deadline.getTotalTasks());
        } else {
            throw new MissingTaskException("Deadline tasks cannot be empty!");
        }
    }

    public static void getExecuteCommand(Task[] tasks, String userInput) throws UnknownInputException {
        String[] words = userInput.split(" ");

        if (words[0].equals(TODO_COMMAND)) {
            try {
                executeToDo(tasks, userInput);
            } catch (DukeException err) {
                System.out.println(err.toString());
            }
        } else if (words[0].equals(DEADLINE_COMMAND)) {
            try {
                executeDeadline(tasks, userInput);
            } catch (DukeException err) {
                System.out.println(err.toString());
            }
        } else if (words[0].equals(EVENT_COMMAND)) {
            try {
                executeEvent(tasks, userInput);
            } catch (DukeException err) {
                System.out.println(err.toString());
            }
        } else if (words[0].equals(DONE_COMMAND)) {
            try {
                executeDoneTask(tasks, userInput);
            } catch (DukeException err) {
                System.out.println(err.toString());
            }
        } else if (words[0].equals(LIST_COMMAND)) {
            try {
                printTaskList(tasks);
            } catch (DukeException err) {
                System.out.println(err.toString());
            }
        } else {
            throw new UnknownInputException("There is no such input!");
        }
    }

    public static void initialisation() {
        String logo = ".______     ______   .______   \n"
                + "|   _  \\   /  __  \\  |   _  \\  \n"
                + "|  |_)  | |  |  |  | |  |_)  | \n"
                + "|   _  <  |  |  |  | |   _  <  \n"
                + "|  |_)  | |  `--'  | |  |_)  | \n"
                + "|______/   \\______/  |______/  \n";
        String welcomeMessage = "Hello human! I am Bob.\n" + "What can I do for you?";

        System.out.println("Bob the chatbot\n" + logo);
        System.out.println(welcomeMessage);
        System.out.println(DIVIDER);
    }

    public static void main(String[] args) {
        initialisation();
        Scanner command = new Scanner(System.in);

        Task[] tasks = new Task[100];

        while (true) {
            String userInput = command.nextLine();
            if (userInput.equals(END_COMMAND)) {
                System.out.println(DIVIDER);
                System.out.println(END_MESSAGE);
                System.out.println(DIVIDER);
                break;
            } else {
                System.out.println(DIVIDER);
                try {
                    getExecuteCommand(tasks, userInput);
                } catch (DukeException err) {
                    System.out.println(err.toString());
                }
                System.out.println(DIVIDER);
            }

        }
    }
}
