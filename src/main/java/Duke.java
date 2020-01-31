import java.util.Scanner;

public class Duke {

    public static final int MAX_NO_OF_TASKS = 100;
    public static final String TODO = "todo";
    public static final String DEADLINE = "deadline";
    public static final String EVENT = "event";
    public static final String LIST = "list";
    public static final String DONE = "done";
    public static final int INITIAL_NO_OF_TASKS = 0;

    public static void main(String[] args) {
        displayHello();
        Task[] taskList = initializeTaskList();
        Scanner myInput = initializeScanner();
        String userInput = getUserInput(myInput);
        int numberOfTasks = INITIAL_NO_OF_TASKS;
        while (isNotBye(userInput)) {
            String[] splitUserInput = splitTheUserInput(userInput);
            numberOfTasks = doTaskAndGetNewNumberOfTasks(taskList, numberOfTasks, userInput, splitUserInput);
            userInput = getNextUserInput(myInput);
        }
        displayGoodbye();
        closeScanner(myInput);
    }

    private static boolean isNotBye(String userInput) {
        return !userInput.equals("bye");
    }

    private static String getUserInput(Scanner myInput) {
        return myInput.nextLine();
    }

    private static String getNextUserInput(Scanner myInput) {
        System.out.println("What else do you want to do?");
        return myInput.nextLine();
    }

    private static String[] splitTheUserInput(String userInput) {
        System.out.println(System.lineSeparator());
        return userInput.split(" ");
    }

    private static void closeScanner(Scanner myInput) {
        myInput.close();
    }

    private static Scanner initializeScanner() {
        return new Scanner(System.in);
    }

    private static Task[] initializeTaskList() {
        return new Task[MAX_NO_OF_TASKS];
    }

    private static int doTaskAndGetNewNumberOfTasks(Task[] taskList, int numberOfTasks, String userInput,
                                                    String[] splitUserInput) {
        switch (splitUserInput[0].toLowerCase()) {
        case TODO:
            numberOfTasks = addTodoToList(taskList, numberOfTasks, userInput);
            break;
        case DEADLINE:
            numberOfTasks = addDeadlineToList(taskList, numberOfTasks, userInput);
            break;
        case EVENT:
            numberOfTasks = addEventToList(taskList, numberOfTasks, userInput);
            break;
        case LIST:
            displayList(taskList, numberOfTasks);
            break;
        case DONE:
            markTaskAsDone(taskList, numberOfTasks, splitUserInput[1]);
            break;
        default:
            numberOfTasks = addDefaultTaskToList(taskList, numberOfTasks, userInput);
            break;
        }
        return numberOfTasks;
    }

    private static int addDefaultTaskToList(Task[] taskList, int numberOfTasks, String userInput) {
        Task newTask;
        newTask = new Task(userInput);
        numberOfTasks = addToList(newTask, taskList, numberOfTasks);
        return numberOfTasks;
    }

    private static void markTaskAsDone(Task[] taskList, int numberOfActions, String s) {
        int actionListNumber = Integer.parseInt(s);
        if ((actionListNumber - 1) > numberOfActions || actionListNumber == 0) {
            System.out.println("Out of range");
        } else {
            taskList[actionListNumber - 1].markAsDone();
            System.out.println(
                    "Nice! I marked this as done: " + taskList[actionListNumber - 1].toString());
        }
    }

    private static void displayList(Task[] taskList, int numberOfActions) {
        if (numberOfActions > 0) {
            for (int i = 0; i < numberOfActions; i++) {
                System.out.println(
                        Integer.toString(i + 1) + ". " + taskList[i].toString());
            }
        } else if (numberOfActions == 0) {
            System.out.println("Nothing yet");
        }
    }

    private static int addEventToList(Task[] taskList, int numberOfTasks, String userInput) {
        Task newTask;
        newTask = new Event(splitTaskDescription(userInput)[0], splitTaskDescription(userInput)[1]);
        numberOfTasks = addToList(newTask, taskList, numberOfTasks);
        return numberOfTasks;
    }

    private static int addDeadlineToList(Task[] taskList, int numberOfTasks, String userInput) {
        Task newTask;
        newTask = new Deadline(splitTaskDescription(userInput)[0], splitTaskDescription(userInput)[1]);
        numberOfTasks = addToList(newTask, taskList, numberOfTasks);
        return numberOfTasks;
    }

    private static int addTodoToList(Task[] taskList, int numberOfTasks, String userInput) {
        Task newTask;
        newTask = new Todo(splitTaskDescription(userInput)[0], splitTaskDescription(userInput)[1]);
        numberOfTasks = addToList(newTask, taskList, numberOfTasks);
        return numberOfTasks;
    }

    private static int addToList(Task newTask, Task[] taskList, int numberOfTasks) {
        taskList[numberOfTasks] = newTask;
        numberOfTasks = numberOfTasks + 1;
        System.out.println("Got it. I've added this task: " + newTask.toString());
        return numberOfTasks;

    }

    private static void displayGoodbye() {
        System.out.println(System.lineSeparator());
        System.out.println("Bye. Hope to see you again soon! Maybe next time");
    }

    private static void displayHello() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    private static String[] splitTaskDescription(String input) {
        String[] returnSplit = new String[2];
        if (!input.contains("/")) {
            returnSplit[0] = input.split(" ", 2)[1];
            returnSplit[1] = "";
            return returnSplit;
        }
        String[] obtainedSplit = input.split("/");
        String[] obtainedDescription = obtainedSplit[0].split(" ", 2);
        returnSplit[0] = obtainedDescription[1];
        returnSplit[1] = obtainedSplit[1];
        return returnSplit;
    }


}
