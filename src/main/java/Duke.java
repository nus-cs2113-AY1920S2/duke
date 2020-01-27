import java.util.Scanner;

public class Duke {

    private static Task[] taskList;
    private static int numTasks;

    public static void main(String[] args) {
        taskList = new Task[100];
        numTasks = 0;
        System.out.println("____________________________________");
        System.out.println("    Hey! I'm Jamun");
        System.out.println("    How can I help you?");
        System.out.println("____________________________________");
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();

        while (!command.equals("bye")) {
            System.out.println("____________________________________");
            if (command.equals("list")) {
                for (int i = 0; i < numTasks; i++) {
                    taskList[i].printTask();
                }
                System.out.println("____________________________________");
                command = sc.nextLine();
            } else {
                Task t = new Task(command, numTasks);
                taskList[numTasks] = t;
                numTasks++;
                System.out.println("    added: " + t.getName());
                System.out.println("____________________________________");
                command = sc.nextLine();
            }
        }

        System.out.println("____________________________________");
        System.out.println("    Bye! See ya next time :)");
        System.out.println("____________________________________");
    }



}

