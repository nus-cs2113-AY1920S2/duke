import java.util.Scanner;


public class Duke {

    public static final int MAX_TASKS = 100;
    public static int numberOfTasks = 0;

    public static void main(String[] args) {
        displayHello();
        Task[] taskList = initializeTaskList();
        Scanner myInput = initializeScanner();
        String userInput = getUserInput(myInput);
        while (isNotBye(userInput)) {
            String[] splitUserInput = splitUserInput(userInput);
            numberOfTasks = doTaskAndUpdateTaskListNumber(taskList, numberOfTasks, userInput, splitUserInput);
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
        String userInput;
        System.out.println("What else do you want to do?");
        userInput = myInput.nextLine();
        return userInput;
    }

    private static String[] splitUserInput(String userInput) {
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
        return new Task[MAX_TASKS];
    }

    private static int doTaskAndUpdateTaskListNumber(Task[] taskList, int numberOfActions, String userInput, String[] splitUserInput) {
        Task newTask;
        switch (splitUserInput[0].toLowerCase()) {
        case "todo":
            numberOfActions = addTodoToList(taskList, numberOfActions, userInput);
            break;
        case "deadline":
            numberOfActions = addDeadlineToList(taskList, numberOfActions, userInput);
            break;
        case "event":
            numberOfActions = addEventToList(taskList, numberOfActions, userInput);
            break;
        case "list":
            displayList(taskList, numberOfActions);
            break;
        case "done":
            markTaskAsDone(taskList, numberOfActions, splitUserInput[1]);
            break;
        default:
            numberOfActions = addDefaultTaskToList(taskList, numberOfActions, userInput);
            break;
        }
        return numberOfActions;
    }

    private static int addDefaultTaskToList(Task[] taskList, int numberOfActions, String userInput) {
        Task newTask;
        newTask = new Task(userInput);
        taskList[numberOfActions] = newTask;
        numberOfActions = numberOfActions + 1;
        System.out.println("Got it. I've added this task: " + newTask.toString());
        return numberOfActions;
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

    private static int addEventToList(Task[] taskList, int numberOfActions, String userInput) {
        Task newTask;
        newTask = new Event(splitTaskDescription(userInput)[0], splitTaskDescription(userInput)[1]);
        taskList[numberOfActions] = newTask;
        numberOfActions = numberOfActions + 1;
        System.out.println("Got it. I've added this task: " + newTask.toString());
        return numberOfActions;
    }

    private static int addDeadlineToList(Task[] taskList, int numberOfActions, String userInput) {
        Task newTask;
        newTask = new Deadline(splitTaskDescription(userInput)[0], splitTaskDescription(userInput)[1]);
        taskList[numberOfActions] = newTask;
        numberOfActions = numberOfActions + 1;
        System.out.println("Got it. I've added this task: " + newTask.toString());
        return numberOfActions;
    }

    private static int addTodoToList(Task[] taskList, int numberOfActions, String userInput) {
        Task newTask;
        newTask = new Todo(splitTaskDescription(userInput)[0], splitTaskDescription(userInput)[1]);
        taskList[numberOfActions] = newTask;
        numberOfActions = numberOfActions + 1;
        System.out.println("Got it. I've added this task: " + newTask.toString());
        return numberOfActions;
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

    public static String[] splitTaskDescription(String input) {
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
