import java.util.List;

public class Printer {
    public static void printIndentation() {
        System.out.print("    ");
    }

    public static void printStart(String logo) {
        System.out.println("Hello from\n" + logo);
    }

    public static void printGreetings() {
        printIndentation();
        System.out.println("Hello! I'm Duke");
        printIndentation();
        System.out.println("What can I do for you?");
    }

    public static void printLines() {
        printIndentation();
        System.out.println("---------------------------------------");
    }

    public static void printBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void printConfirmationMessage(String text) {
        printLines();
        printIndentation();
        System.out.println("Added: " + text);
        printLines();
    }

    public static void printTasks(List<String> myList) {
        printLines();
        for (int i = 0; i < myList.size(); i ++) {
            printIndentation();
            System.out.printf("%d. %s\n", i + 1, myList.get(i));
        }
        printLines();
    }
}
