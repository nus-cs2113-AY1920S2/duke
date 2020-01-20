import java.util.Scanner;

public class Duke {
    public static class Task {
        protected String description;
        protected boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public String getStatusIcon() {
            return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
        }

        public void taskDone() {
            this.isDone = true;
        }
    }

    public static void main(String[] args) {
        String logo = "Nyan\n";
        System.out.println("    ____________________________________________________________");
        System.out.print("    Hello! I'm " + logo);
        System.out.println("    ____________________________________________________________");

        Scanner input = new Scanner(System.in);
        Task[] buffer = new Task[100];
        int counter = 0;

        while(true) {
            String s = input.next();
            System.out.println("    ____________________________________________________________");
            if (s.equalsIgnoreCase("bye")) {
                System.out.println("    Bye. Hope to see you soon!");
                System.out.println("    ____________________________________________________________");
                break;
            } else if (s.equalsIgnoreCase("list")) {
                System.out.println("    Here are the tasks in your list:");
                for (int i = 0; i < counter; i++) {
                    System.out.println("    " + (i + 1) + ".[" + buffer[i].getStatusIcon() + "] " + buffer[i].description);
                }
            } else if (s.equalsIgnoreCase("done")){
                int num = input.nextInt() - 1;
                buffer[num].taskDone();
                System.out.println("    Nice! I've marked this task as done:");
                System.out.println("      [" + buffer[num].getStatusIcon() + "] " + buffer[num].description);
            } else {
                s = s + input.nextLine();
                buffer[counter] = new Task(s);
                System.out.println("    added: " + buffer[counter].description);
                counter++;
            }
            System.out.println("    ____________________________________________________________");
        }
    }
}

