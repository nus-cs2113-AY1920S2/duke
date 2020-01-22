import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        Task[] tasks = new Task[100];
        int counter = 0;
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        Scanner input = new Scanner(System.in);
        while (true){
            String next = input.nextLine();
            if (next.equals("bye")){
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (next.equals("list")) {
                for (int i = 0; i < counter; i++) {
                System.out.printf("%d.[%s] %s\n",i +1,tasks[i].getStatusIcon(),tasks[i].getDescription());
                }
            } else if (next.startsWith("done")){
                String temp = next.replaceAll("\\D+","");
                int FinishedNumber = Integer.parseInt(temp);
                tasks[FinishedNumber-1].markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.printf("  [%s] %s\n",tasks[FinishedNumber-1].getStatusIcon(),tasks[FinishedNumber-1].getDescription());
            } else {
                    tasks[counter] = new Task(next);
                    System.out.println("added: "+ next);
                    counter +=1;
            }
            }
        }
    }

