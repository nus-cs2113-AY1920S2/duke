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

    public static void printDoneTask(Task[] tasks, int taskNum) {
        tasks[taskNum-1].markAsDone();
        System.out.println("Awesome! I've marked the following task as done:");
        System.out.println(tasks[taskNum-1]);
    }


    public static String taskValidator(int numTasks) {
        String totalTasks = Integer.toString(numTasks);
        if (numTasks <= 1) {
            return "You now have " + totalTasks + " task in the list!";
        } else {
            return "You now have " + totalTasks+ " tasks in the list!";
        }
    }

    public static boolean isTask(String task) {
        String todoCommand = "todo";
        String deadlineCommand = "deadline";
        String eventCommand = "event";

        if (task.equals(todoCommand) || task.equals(deadlineCommand) || task.equals(eventCommand)) {
            return true;
        } else {
            return false;
        }
    }

    public static void inputValidation(Task[] tasks, String userCommand) {
        String[] words = userCommand.split(" ");
        int spacesPadding = 1;
        int taskPadding = 4;

        String todoCommand = "todo";
        String deadlineCommand = "deadline";
        String eventCommand = "event";
        String completeCommand = "done";
        String listCommand = "list";

        if (words[0].equals(todoCommand)) {
            String todoTask = userCommand.substring(todoCommand.length() + spacesPadding);
            Task todo = new Todo(todoTask);
            tasks[todo.getTotalTasks()-1] = todo;
            printIndividualTask(tasks,todo.getTotalTasks());
        } else if (words[0].equals(deadlineCommand)) {
            int indexOfBy = userCommand.indexOf("/by");
            String deadlineTask = userCommand.substring(deadlineCommand.length() + spacesPadding, indexOfBy - 1);
            String byDate = userCommand.substring(indexOfBy + taskPadding);
            Task deadline = new Deadline(deadlineTask, byDate);
            tasks[deadline.getTotalTasks()-1] = deadline;
            printIndividualTask(tasks,deadline.getTotalTasks());
        } else if (words[0].equals(eventCommand)) {
            int indexOfAt = userCommand.indexOf("/at");
            String eventTask = userCommand.substring(eventCommand.length() + spacesPadding, indexOfAt - 1);
            String atDate = userCommand.substring(indexOfAt + taskPadding);
            Task event = new Event(eventTask, atDate);
            tasks[event.getTotalTasks()-1] = event;
            printIndividualTask(tasks,event.getTotalTasks());
        } else if (words[0].equals(completeCommand)) {
            int taskNum = Integer.parseInt(words[1]);
            printDoneTask(tasks, taskNum);
        } else if (words[0].equals(listCommand)) {
            printTaskList(tasks);
        }
    }


    public static void validateInput(String userInput) throws InvalidNumInput {
        String[] words = userInput.split(" ");
        if (words.length <= 1 && isTask(words[0])) {
            throw new InvalidNumInput();
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
            String[] words = userInput.split(" ");

            try {
                validateInput(userInput);
                if (userInput.equals(endCommand)) {
                    System.out.println(DIVIDER);
                    System.out.println(endMessage);
                    System.out.println(DIVIDER);
                    break;
                } else {
                    System.out.println(DIVIDER);
                    inputValidation(tasks, userInput);
                    System.out.println(DIVIDER);
                }
            } catch (InvalidNumInput e) {
                System.out.println(DIVIDER);
                System.out.println("Too little");
                System.out.println(DIVIDER);
            }
/*
            if (userInput.equals(endCommand)) {
                System.out.println(DIVIDER);
                System.out.println(endMessage);
                System.out.println(DIVIDER);
                break;
            } else {
                System.out.println(DIVIDER);
                inputValidation(tasks, userInput);
                System.out.println(DIVIDER);
            }*/

        }
    }
}
