import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private static ArrayList<String> tasks;

    public Duke() {
        this.tasks = new ArrayList<String>();
    }

    private void printGreetingMessage() {
        System.out.println("Hi! I am Duke, your next doooooorrrr friendly elf.....I mean bot");
        System.out.println("How may Dukeeeeee be of service to you today?");
        System.out.println("_______________________________________________________________________");
    }

    private void printLeavingMessage() {
        System.out.println("    Bye! Duke is now a freeeeee elf again!!!!");
    }

    private void printLine() {
        System.out.println(" ======================================================================");
    }

    private void echo(String convertToLowerCase) {
        System.out.println("    " + convertToLowerCase);
    }

    private void add(String description) {
        tasks.add(description);
        System.out.println("    Added: " + description);
        System.out.println("    There are currently " + tasks.size() + " item(s) queued");
    }

    private void printList() {
        int position = 1;
        for (String text: tasks) {
            System.out.println("    " + position + ". " + text);
            position ++;
        }
    }

    private void list() {
        if (tasks.size() == 0) {
            System.out.println("    Hmph!! There are currently no items queued");
        } else {
            System.out.println(" Here are the list of tasks:");
            printList();
        }
    }

    private void run() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String line = sc.nextLine();
            String removeTrailingSpaces = line.trim();
            String convertToLowerCase = removeTrailingSpaces.toLowerCase();
            boolean isBye = convertToLowerCase.equals("bye");
            boolean isList = convertToLowerCase.equals("list");
            printLine();
            if (isBye) {
                printLeavingMessage();
                printLine();
                break;
            } else if(isList) {
                list();
            } else {
                add(removeTrailingSpaces);
            }
            printLine();
        }
        sc.close();
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Duke dukeFeatures = new Duke();
        dukeFeatures.printGreetingMessage();
        dukeFeatures.run();
    }
}
