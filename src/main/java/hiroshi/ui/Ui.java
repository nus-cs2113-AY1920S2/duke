package hiroshi.ui;
import hiroshi.tasks.Task;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Text UI of the application.
 */
public class Ui {

    /** Prints Logo.  */
    public void printLogo() {
        int rows = 5;
        for (int i=1; i<= rows ; i++) {
            for (int j = rows; j > i ; j--) {
                System.out.print("       ");
            }
            System.out.print("           -*-");
            for (int k = 1; k < 2*(i -1) ;k++) {
                System.out.print("*-");
            }
            if( i==1) {
                System.out.println("--**--");
            } else {
                System.out.println("*-*-*-");
            }
        }
        for (int i=rows-1; i>= 1 ; i--) {
            for (int j = rows; j > i ; j--) {
                System.out.print("       ");
            }
            System.out.print("-*-*-");
            for (int k = 1; k < 2*(i -1) ;k++) {
                System.out.print("-*-");
            }
            if( i==1)
                System.out.println(" ");
            else
                System.out.println("--**--");
        }
    }

    /** Prints a straight line.  */
    public static void printStraightLine() {
        System.out.println("_________________________________________________________________________________\n");
    }

    /** Prints a fancy line.  */
    public static void printFancyLine() {
        System.out.println("****--****--****--****--****--****--****--****--****--****--****--****--****--***\n");
    }

    /** Prints message that is shown at the begenning of the program.  */
    public Ui(){
        printLogo();
        printFancyLine();
        System.out.println("Hello! I'm Hiroshi");
        System.out.println("Lets plan your day buddy! Things are looking good.\n");
        printStraightLine();
        System.out.println("Your inputs can only be of the following forms: \n 1. todo {task description} \n 2." +
                " deadline {task description} /by {YYYY-MM-DD} \n 3. event {event description} " +
                "/at {YYYY-MM-DD} \n 4. delete {taskNumber} \n 5. list \n 6. done {task number}" +
                "\n 7. find {keyword} \n 8. clear");
        printFancyLine();
    }

    /**
     * Prints a statement about the item when the item has been added to the task list.
     *
     * @param taskList List that stores the tasks mentioned until now.
     */
    public static void printAddedStatement(ArrayList<Task> taskList) {
        Task t = taskList.get(taskList.size() - 1);
        String description = t.getDescription();
        String statusIcon = t.getStatusIcon();
        String typeIcon = t.getTypeIcon();
        System.out.println("Got it. I have added this task: \n");
        System.out.println(typeIcon + " [" + statusIcon + "] " + description + "\n");
        System.out.println("Now you have " + taskList.size() + " item/s in the list \n");
        Ui.printStraightLine();
    }

    /**
     * Reads the command inputted by the user and returns it.
     *
     * @return line Line inputted by the user.
     */
    public static String readCommand() {
        System.out.println("Enter what you would like to do:");
        System.out.println("\n");
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        if (line.toUpperCase().equals("BYE")){
            scanner.close();
        }
        printStraightLine();
        return line;
    }

    /** Prints a bye message when the user exits the program.  */
    public static void showByeMessage(){
        System.out.println("Bye. Hope to see you again soon!\n");
        Ui.printFancyLine();
    }

    public static void markLoadingError(){
        System.out.println("File does not have the correct data format. We cannot read the data, we will create a fresh task list for you!");
        Ui.printStraightLine();
    }


    public static void markAsIncorrectFormat() {
        System.out.println("Your inputs can only be of the following forms: \n 1. todo {task description} \n 2." +
                " deadline {task description} /by {YYYY-MM-DD} \n 3. event {event description} " +
                "/at {YYYY-MM-DD} \n 4. delete {taskNumber} \n 5. list \n 6. done {task number}" +
                "\n 7. find {keyword} \n 8. clear");
        System.out.println("Try again!");
        printStraightLine();
    }

    public static void markIncorrectFindStatement(){
        System.out.println("OOPS! Please specify (eg: find 2)");
        printStraightLine();
    }

    public static void markIncorrectListStatement(){
        System.out.println("OOPS! Please just write \"list\" with the correct index number");
        printStraightLine();
    }

    public static void markIncorrectClearStatement(){
        System.out.println("OOPS! Please just write clear");
        printStraightLine();
    }

    public static void markIncorrectDoneStatement(){
        System.out.println("OOPS! Please specify (eg: done 2)");
        printStraightLine();
    }

    public static void markDoneStatementOutOfBounds(){
        System.out.println("That task number isn't in our task list, please try again making a valid task!");
        printStraightLine();
    }

    public static void markDoneStatementAsNonInt(){
        System.out.println("The output format should be of the format \"done 2\", not \"done + {string}\".");
        printStraightLine();
    }

    public static void markIncorrectDeleteStatement(){
        System.out.println("OOPS! Please specify (eg: delete 2)");
        printStraightLine();
    }

    public static void markDeleteStatementOutOfBounds(){
        System.out.println("That task number isn't in our task list, please try deleting with a task that exists!");
        printStraightLine();
    }

    public static void markDeleteStatementAsNonInt(){
        System.out.println("The output format should be of the format \"delete 2\", not \"delete + {string}\".");
        printStraightLine();
    }

    public static void markDateTimeException(){
        System.out.println("The date should be of the format \"YYYY-MM-DD\"");
        printStraightLine();
    }
}