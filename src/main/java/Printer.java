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
        System.out.println("Hello! I'm Duke (￣▽￣*)ゞ");
        printIndentation();
        System.out.println("What can I do for you?");
    }

    public static void printLines() {
        printIndentation();
        System.out.print("---------------------------------------");
        System.out.println("---------------------------------------");
    }

    public static void printBye() {
        System.out.println("Bye ~ Hope to see you again soon! o(〒﹏〒)o");
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
        System.out.printf("Nice! I've marked this task as done ヽ(・∀・)ﾉ :\n");
        printIndentation();
        System.out.printf("  %s\n", task);
        printLines();
    }

    public static void printConfirmationMessage(ToDo task) {
        printLines();
        printIndentation();
        System.out.println("Got it! I've added this task ＠＾◡＾) :");
        printIndentation();
        System.out.println("  " + task);
        printIndentation();
        System.out.printf("Now you have %d tasks in the list\n", Storage.getSize());
        printLines();
    }

    public static void printConfirmationMessage(Deadline task) {
        printLines();
        printIndentation();
        System.out.println("Oh a deadline huh! Don't worry, I have added this task <(￣︶￣)> :");
        printIndentation();
        System.out.println("  " + task);
        printIndentation();
        System.out.printf("Now you have %d tasks in the list\n", Storage.getSize());
        printLines();
    }

    public static void printTasks(List<Task> myList) {
        printLines();
        if (myList.isEmpty()) {
            printIndentation();
            System.out.println("List is empty.");
        } else {
            for (int i = 0; i < myList.size(); i++) {
                printIndentation();
                Task temp = myList.get(i);
                System.out.printf("%d. %s\n", i + 1, temp);
            }
        }
        printLines();
    }

    public static void printError() {
        printLines();
        printIndentation();
        System.out.println("Error.. ٩(× ×)۶");
        printLines();
    }
}
