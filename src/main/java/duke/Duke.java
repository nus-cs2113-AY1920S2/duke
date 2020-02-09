package duke;

import duke.command.Deadline;
import duke.command.Event;
import duke.command.Task;
import duke.command.Todo;
import duke.exception.*;

import java.util.Scanner;

public class Duke {

    public static final String DIVIDER = "_________________________________________________";

    public static void printIndividualTask(Task[] tasks, int taskNum) {
        if (tasks[taskNum - 1].getTaskDescription().equals("todo")) {
            System.out.println("Got it! You've added a todo task: ");
            System.out.println(tasks[taskNum - 1]);
            System.out.println(taskValidator(taskNum));
        } else if (tasks[taskNum - 1].getTaskDescription().equals("deadline")) {
            System.out.println("Got it! You've added a deadline task: ");
            System.out.println(tasks[taskNum - 1]);
            System.out.println(taskValidator(taskNum));
        } else if (tasks[taskNum - 1].getTaskDescription().equals("event")) {
            System.out.println("Got it! You've added an event task: ");
            System.out.println(tasks[taskNum - 1]);
            System.out.println(taskValidator(taskNum));
        }
    }

    public static void printTaskList(Task[] tasks) {
        System.out.println("Here are the tasks on your list: ");
        for (int i = 1; i < tasks.length + 1; i++) {
            if (tasks[i-1] == null) {
                break;
            } else {
                String taskNum = Integer.toString(i);
                System.out.println(taskNum + "." + tasks[i - 1]);
            }
        }
    }

    public static void executeDoneTask(Task[] tasks, String userInput) throws MissingTaskNumberException, MissingTaskNumberDescriptionException {
        String doneCommand = "done";
        if (!userInput.trim().equals(doneCommand)) {
            String[] words = userInput.split(" ");
            int taskNum = Integer.parseInt(words[1]);
            int sizeOfArray = 0;
            for (int i = 0; i < tasks.length; i++) {
                if (tasks[i] != null) {
                    sizeOfArray++;
                } else {
                    break;
                }
            }
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
        String todoCommand = "todo";
        if (!userInput.trim().equals(todoCommand)) {
            String todoTask = userInput.substring(todoCommand.length() + 1);
            Task todo = new Todo(todoTask);
            tasks[todo.getTotalTasks()-1] = todo;
            printIndividualTask(tasks,todo.getTotalTasks());
        } else {
            throw new MissingTaskException("Todo tasks cannot be empty!");
        }
    }

    public static void executeEvent(Task[] tasks, String userInput) throws MissingTaskException, MissingEventDateException {
        String eventCommand = "event";
        if (!userInput.trim().equals(eventCommand)) {
            int indexOfAt = userInput.indexOf("/at");
            if (indexOfAt == -1) {
                throw new MissingEventDateException("Please specify the date for event using \'at\'!");
            }
            String eventTask = userInput.substring(eventCommand.length() + 1, indexOfAt - 1);
            String atDate = userInput.substring(indexOfAt + "/at".length() + 1);
            Task event = new Event(eventTask, atDate);
            tasks[event.getTotalTasks()-1] = event;
            printIndividualTask(tasks,event.getTotalTasks());
        } else {
            throw new MissingTaskException("Event tasks cannot be empty!");
        }
    }

    public static void executeDeadline(Task[] tasks, String userInput) throws MissingTaskException, MissingDeadlineDateException {
        String deadlineCommand = "deadline";
        if (!userInput.trim().equals(deadlineCommand)) {
            int indexOfBy = userInput.indexOf("/by");
            if (indexOfBy == -1) {
                throw new MissingDeadlineDateException("Please specify a deadline using \'/by\'!");
            }
            String deadlineTask = userInput.substring(deadlineCommand.length() + 1, indexOfBy - 1);
            String byDate = userInput.substring(indexOfBy + "/by".length() + 1);
            Task deadline = new Deadline(deadlineTask, byDate);
            tasks[deadline.getTotalTasks()-1] = deadline;
            printIndividualTask(tasks,deadline.getTotalTasks());
        } else {
            throw new MissingTaskException("Deadline tasks cannot be empty!");
        }
    }

    public static void getExecuteCommand(Task[] tasks, String userInput) throws UnknownInputException {
        String deadlineCommand = "deadline";
        String eventCommand = "event";
        String todoCommand = "todo";
        String completeCommand = "done";
        String listCommand = "list";

        String[] words = userInput.split(" ");

        if (words[0].equals(todoCommand)) {
            try {
                executeToDo(tasks, userInput);
            } catch (DukeException err) {
                System.out.println(err.toString());
            }
        } else if (words[0].equals(deadlineCommand)) {
            try {
                executeDeadline(tasks, userInput);
            } catch (DukeException err) {
                System.out.println(err.toString());
            }
        } else if (words[0].equals(eventCommand)) {
            try {
                executeEvent(tasks, userInput);
            } catch (DukeException err) {
                System.out.println(err.toString());
            }
        } else if (words[0].equals(completeCommand)) {
            try {
                executeDoneTask(tasks, userInput);
            } catch (DukeException err) {
                System.out.println(err.toString());
            }
        } else if (words[0].equals(listCommand)) {
            printTaskList(tasks);
        } else {
            throw new UnknownInputException("There is no such input!");
        }
    }



    public static void main(String[] args) {
        String logo = ".______     ______   .______   \n"
                + "|   _  \\   /  __  \\  |   _  \\  \n"
                + "|  |_)  | |  |  |  | |  |_)  | \n"
                + "|   _  <  |  |  |  | |   _  <  \n"
                + "|  |_)  | |  `--'  | |  |_)  | \n"
                + "|______/   \\______/  |______/  \n";
        String welcomeMessage = "Hello human! I am Bob.\n" + "What can I do for you?";

        //Welcome and sign out messages
        System.out.println("Bob the chatbot\n" + logo);
        System.out.println(welcomeMessage);
        System.out.println(DIVIDER);

        Scanner command = new Scanner(System.in);
        String endCommand = "bye";
        String endMessage = "Bob thanks you for coming! See you again soon!";

        //Storing text
        Task[] tasks = new Task[100];

        String listCommand = "list";
        String completeCommand = "done";

        while (true) {
            String userInput = command.nextLine();
            if (userInput.equals(endCommand)) {
                System.out.println(DIVIDER);
                System.out.println(endMessage);
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
