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
    public static void printConfirmationMessage(Task task) {
        printLines();
        printIndentation();
        System.out.printf("Nice! I've marked this task as done:\n");
        printIndentation();
        System.out.printf("  [%s] %s\n", task.getStatusIcon(), task.getDescription());
        printLines();
    }

    public static void printTasks(List<Task> myList) {
        printLines();
        if (myList.isEmpty()) {
            printIndentation();
            System.out.println("List is empty.");
        }
        else {
            for (int i = 0; i < myList.size(); i++) {
                printIndentation();
                Task temp = myList.get(i);
                String symbol = temp.getStatusIcon();
                System.out.printf("%d.[%s] %s\n", i + 1, symbol, temp.getDescription());
            }
        }
        printLines();
    }

    public static void printError() {
        printLines();
        printIndentation();
        System.out.println("Error.. ;-;");
        printLines();
    }
}
