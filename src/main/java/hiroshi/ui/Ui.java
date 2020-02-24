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
            System.out.print("           **");
            for (int k = 1; k < 2*(i -1) ;k++) {
                System.out.print("HI");
            }
            if( i==1) {
                System.out.println("RORO");
            } else {
                System.out.println("RORO**");
            }
        }
        for (int i=rows-1; i>= 1 ; i--) {
            for (int j = rows; j > i ; j--) {
                System.out.print("       ");
            }
            System.out.print("**SHI");
            for (int k = 1; k < 2*(i -1) ;k++) {
                System.out.print("RO");
            }
            if( i==1)
                System.out.println(" ");
            else
                System.out.println("SHI**");
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
        return line;
    }

    /** Prints a bye message when the user exits the program.  */
    public static void showByeMessage(){
        System.out.println("Bye. Hope to see you again soon!\n");
        Ui.printFancyLine();
    }

}
