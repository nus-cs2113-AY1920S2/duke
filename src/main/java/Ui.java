package src.main.java;

import src.main.java.duke.task.Task;
import java.util.Scanner;

public class Ui {

    private static final String DIVIDER = "_________________________________________________";

    private static final String GREETING = "Hello from Optimus Prime";

    private static final String ASK_FOR_INSTRUCTION = "What can I do for you?";

    private static final String TASK_ADDED = "Got it. I've added this task";

    private static final String TASK_COUNT_FRONT_PART = "Now you have ";

    private static final String TASK_COUNT_BACK_PART = " tasks in the list";

    private static final String LIST_TASK ="Here are the tasks in your list:";

    private static final String ERROR_HEADER = "ERROR!";

    private static final String MARKED_AS_COMPLETED_MESSAGE = "Nice! I've marked this task as done:";

    private static final String DELETE_TASK_MESSAGE = "Noted. I've removed this task:";

    private static final String INDENT = "  ";

    private static final String FILE_NOT_FOUND = "Unable to find File";

    private static final String END_MESSAGE = "Bye. Hope to see you again soon!";

    private static final String FOUND_MESSAGE = "Here are the matching tasks in your list:";

    static String readCommand(){
        String userInput;
        Scanner scan = new Scanner(System.in);
        userInput = scan.nextLine();
        return userInput;
    }

    static void showLine(){
        showToUser(DIVIDER);
    }

    private static void showToUser(String... messages){
        for (String m: messages) {
            System.out.println(m);
        }
    }

    public static void greetUser() {
        showToUser(GREETING,
                ASK_FOR_INSTRUCTION ,
                DIVIDER);
    }

    public static void printAddedTask(Task task) {
        showToUser(TASK_ADDED,
                INDENT + task.toString(),
                TASK_COUNT_FRONT_PART + Task.getTotalNumberOfTask() + TASK_COUNT_BACK_PART,
                DIVIDER);
    }

    public static void printCompletedMessage(Task task){
        showToUser(MARKED_AS_COMPLETED_MESSAGE,
                INDENT + task.toString(),
                DIVIDER);
    }

    public static void printDeletedMessage(Task task){
        showToUser(DELETE_TASK_MESSAGE,
                INDENT + task.toString(),
                TASK_COUNT_FRONT_PART + Task.getTotalNumberOfTask() + TASK_COUNT_BACK_PART,
                DIVIDER);
    }

    public static void printEndMessage(){
        showToUser(END_MESSAGE,DIVIDER);
    }

    public static void listTasks(String fullList) {
        showToUser(LIST_TASK,
                fullList,
                DIVIDER);
    }

    public static void listFoundTasks(String foundTasks) {
        showToUser(FOUND_MESSAGE,
                foundTasks,
                DIVIDER);
    }

    public static void showLoadingError(){
        System.out.println(FILE_NOT_FOUND);
    }

    public static void showError(String errorMessage){
        showToUser(ERROR_HEADER, errorMessage, DIVIDER);
    }
}
