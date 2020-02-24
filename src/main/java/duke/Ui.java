package duke;

import java.util.Scanner;

public class Ui {
    protected static Scanner scannerObject;

    public static void initializeUi() {
        scannerObject = new Scanner(System.in);
    }

    protected static void displayLineSeparator() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    public static void greet() {
        System.out.println();
        displayLineSeparator();
        System.out.println("Hello! This is Quinn's chat bot");
        System.out.println("Currently supported task types: todo, deadline, event");
        System.out.println("Currently supported commands: list, done, delete");
        System.out.println("Example usage:");
        System.out.println("todo math homework");
        System.out.println("deadline finish math homework /by Monday");
        System.out.println("event math class /at Monday 8am-10am");
        System.out.println("list");
        System.out.println("done <Task Number>");
        System.out.println("delete <Task Number>");
        System.out.println("Type \"" + Duke.END_STRING + "\" to exit");
        displayLineSeparator();
        System.out.println();
    }

    public static void sayGoodbye() {
        System.out.println();
        displayLineSeparator();
        System.out.println("Leaving so soon? :(");
        displayLineSeparator();
        System.out.println();
    }

    public static void printPretty(String message) {
        System.out.println();
        displayLineSeparator();
        System.out.println(message);
        displayLineSeparator();
        System.out.println();
    }

    public static String getNextLine() {
        return scannerObject.nextLine();
    }
}
