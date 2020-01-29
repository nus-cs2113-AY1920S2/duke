
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("____________________________________________________________\n" +
                        " Hello! I'm Duke\n" +
                        " What can I do for you?\n" +
                "____________________________________________________________");

        Task[] list = new Task[100];
        int index = 0;

        Scanner scanner = new Scanner(System.in);
        String inputString = "";
        while(!inputString.equals("bye bye")) {
            inputString = scanner.nextLine();
            Task new_task = new Task(inputString);

            if(inputString.equals("list")) {
                for(int i = 0; i < index; i++) {
                    System.out.println(String.valueOf(i+1) + ". " + list[i].getStatusIcon() + " " + list[i].description);
                }
            } else if(inputString.indexOf("done") >= 0) {
                int ind = Integer.parseInt((inputString.substring(5)));
                System.out.println(ind);
                list[ind-1].markAsDone();
                System.out.println("done: " + list[ind-1].description);
            } else {
                list[index] = new_task;
                index++;
                System.out.println("  added: " + inputString + "\n" +
                        "____________________________________________________________");
            }

        }
        System.out.println(" Bye. Hope to see you again soon!\n" +
                "____________________________________________________________");
    }
}
