import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
        String[] tasks = new String[100];
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
                System.out.printf("%d. %s\n",i +1,tasks[i]);
                }
            } else {
                    tasks[counter] = next;
                    System.out.println("added: "+ next);
                    counter +=1;
            }
            }
        }
    }

