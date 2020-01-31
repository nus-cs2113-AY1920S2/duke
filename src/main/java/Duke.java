/**
 *  CS2113T Semester 2 AY19/20
 *  Individual Project
 *
 *  Project Duke is a educational software project designed to take you through
 *  the steps of building a small software incrementally,
 *  while applying as many Java and SE techniques as possible along the way.
 *
 *  The project aims to build a product named Duke, a Personal Assistant Chatbot that
 *  helps a person to keep track of various things. The name Duke was chosen as a placeholder name,
 *  in honor of Duke, the Java Mascot. You may give it any other name and personality you wish.
 *
 *  @file/s: Duke.java Printer.java* Storage.java*
 *  @author: Tan Zheng Fu Justin
 */

import java.util.Scanner;

public class Duke {
    static Storage myList = new Storage();

    public static void start() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        Printer.printStart(logo);
    }

    public static void exits() {
        Printer.printLines();
        Printer.printIndentation();
        Printer.printBye();
        Printer.printLines();
    }

    public static void storeInList(String userInput) {
        myList.storeTasks(userInput);
    }

    public static String readFromUser() {
        Scanner in = new Scanner(System.in);
        String userInput;

        userInput = in.nextLine();

        return userInput;
    }

    public static void echoUntilBye() {
        while (true) {
            String userInput = readFromUser();
            switch (userInput) {
            case "bye":
                exits();
                break;
            case "list":
                myList.getTasks();
                continue;
            default:
                storeInList(userInput);
                Printer.printConfirmationMessage(userInput);
                continue;
            }
        }
    }

    public static void main(String[] args) {
        start();
        Printer.printLines();
        Printer.printGreetings();
        Printer.printLines();
        echoUntilBye();
    }
}
