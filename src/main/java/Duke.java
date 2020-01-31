import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Task[] tasks = new Task[100];
        int count = 1;
        System.out.println("    ____________________________________________________________");
        System.out.println("    Hello! I'm Duke");
        System.out.println("    What can I do for you?");
        System.out.println("    ____________________________________________________________");
        Scanner sc = new Scanner(System.in);
        String string = sc.nextLine();
        while (!string.equals("bye")) {
            System.out.println("    ____________________________________________________________");
            String[] stringSplit = string.split(" ");
            if (string.equals("list")) {
                System.out.println("     Here are the tasks in your list:");
                for (int i = 1; i < count; i++) {
                    System.out.print("     " + (i) + ".[" + tasks[i].getStatusIcon() + "] ");
                    System.out.println(tasks[i].getDescription());
                }
            } else if (stringSplit[0].equals("done")) {
                int done = Integer.parseInt(stringSplit[1]);
                tasks[done].markAsDone();
                System.out.println("     Nice! I've marked this task as done: ");
                System.out.println("       [\u2713] " + tasks[done].getDescription()); //return tick symbol
            } else {
                tasks[count] = new Task(string);
                System.out.println("     added: " + tasks[count].getDescription());
                count++;
            }
            System.out.println("    ____________________________________________________________");
            string = sc.nextLine();
        }
        System.out.println("    ____________________________________________________________");
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
    }
}
