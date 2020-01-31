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

    //this one can put in Task.java
    public static void storeInList(Task userInput) {
        myList.storeTasks(userInput);
    }

    public static Task readFromUser() {
        Scanner in = new Scanner(System.in);
        String userInput;

        userInput = in.nextLine();
        Task task = new Task(userInput);

        return task;
    }

    public static String parseCommand(String description) {
        String[] parseStorage = description.split(" ");
        return parseStorage[0];
    }

    public static void echoUntilBye() {
        boolean isBye = false;
        while (!isBye) {
            Task task = readFromUser();
            String command = parseCommand(task.description);
            switch (command) {
            case "bye":
                isBye = true;
                exits();
                continue;
            case "list":
                myList.getTasks();
                continue;
            default:
                storeInList(task);
                Printer.printConfirmationMessage(task.description);
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
