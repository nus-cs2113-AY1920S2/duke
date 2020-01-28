import java.util.Scanner;

public class Duke {
    private static int counter = 0;
    private static Task[] listOfTasks =  new Task[100];
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
                listAllTasks();
            } else if (line.contains("done")) {
                markTaskAsDone(line);
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

    public static void storeTaskIntoList(String line) {
        listOfTasks[counter] = new Task(line);
        printLine();
        System.out.println(" added: " + line);
        printLine();
        counter += 1;
    }

    public static void listAllTasks() {
        printLine();
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < counter; i += 1) {
            System.out.print(" ");
            System.out.print(i+1);
            System.out.print(".[" + listOfTasks[i].getStatusIcon() + "] ");
            System.out.println(listOfTasks[i].getDescription());
        }
        printLine();
    }

    public static void markTaskAsDone(String line) {
        int dividerPosition = line.indexOf("done");
        int taskNumber = Integer.parseInt(line.substring(dividerPosition+5, line.length()));
        listOfTasks[taskNumber-1].markAsDone();
        printLine();
        System.out.println(" Nice! I've marked this task as done:");
        System.out.print(" ");
        System.out.print("  [" + listOfTasks[taskNumber-1].getStatusIcon() + "] ");
        System.out.println(listOfTasks[taskNumber-1].getDescription());
        printLine();
    }
}
