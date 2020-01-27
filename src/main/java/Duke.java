import java.util.Scanner;
import java.util.Arrays;

public class Duke {
    private static String[] listOfTasks = new String[101];
    private static int counter = 0;
    public static void main(String[] args) {
        String logo = "  ____        _        \n"
                + " |  _ \\ _   _| | _____ \n"
                + " | | | | | | | |/ / _ \\\n"
                + " | |_| | |_| |   <  __/\n"
                + " |____/ \\__,_|_|\\_\\___|\n";
        printLine();
        System.out.println(logo);
        System.out.println(" Hello! I'm Duke");
        System.out.println(" What can I do for you?");
        printLine();
        String line;
        Scanner in = new Scanner(System.in);
        while (true) {
            line = in.nextLine();
            if (line.equals("bye")) {
                exitFromApp();
                break;
            } else if (line.equals("list")) {
                showAllTasks();
            } else {
                storeTaskIntoList(line);
            }
        }
    }

    public static void printLine() {
        for (int i = 0; i < 60; i += 1) {
            System.out.print("_");
        }
        System.out.println();
    }

    public static void exitFromApp() {
        printLine();
        System.out.println(" Bye. Hope to see you again soon!");
        printLine();
    }

    public static void storeTaskIntoList(String task) {
        listOfTasks[counter] = task;
        printLine();
        System.out.println(" added: " + task);
        printLine();
        counter += 1;
    }

    public static void showAllTasks() {
        printLine();
        for (int i = 0; i < counter; i += 1) {
            System.out.print(" ");
            System.out.print(i+1);
            System.out.println(". " + listOfTasks[i]);
        }
        printLine();
    }
}