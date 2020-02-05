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
        boolean ifError = false;
        while (true){
            String next = input.nextLine();
            if (next.equals("bye")){
                System.out.println("Bye. Hope to see you again soon! :)");
                break;
            } else if (next.equals("list")) {
                System.out.println("Here are the tasks on your list:");
                for (int i = 0; i < counter; i++) {
                System.out.printf("%d. %s\n",i +1,tasks[i].toString());
                }
            } else if (next.startsWith("done")){
                String temp = next.replaceAll("\\D+","");
                int FinishedNumber = Integer.parseInt(temp);
                tasks[FinishedNumber-1].markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.printf("  [%s] %s\n",tasks[FinishedNumber-1].getStatusIcon(),tasks[FinishedNumber-1].getDescription());
            } else {

                String[] splitString = next.split(" ", 2);
                if (splitString[0].equals("deadline")){
                    if (splitString.length == 1) {
                        System.out.println("The description of deadline cannot be empty");
                        ifError = true;
                    } else{
                        String[] splitString2 = splitString[1].split("/by", 2);
                        if (splitString2.length == 1) {
                            System.out.println("Deadline requires to be separated by a '/by' statement");
                            ifError = true;
                        } else{
                            gotItMessage();
                            tasks[counter] = new Deadline(splitString2[0],splitString2[1]);
                            System.out.printf("  %s\n",tasks[counter].toString());
                        }

                    }

                } else if (splitString[0].equals("todo")) {
                    if (splitString.length == 1) {
                        System.out.println("OOPS!!! The description of todo cannot be empty");
                        ifError = true;
                    } else{
                        gotItMessage();
                        tasks[counter] = new Todo(splitString[1]);
                        System.out.printf("  %s\n",tasks[counter].toString());
                    }

                } else if (splitString[0].equals("event")) {
                    if (splitString.length == 1) {
                        System.out.println("OOPS!!! The description of event cannot be empty");
                        ifError = true;
                    } else {
                        String[] splitString2 = splitString[1].split("/at", 2);
                        if (splitString2.length == 1) {
                            System.out.println("Event is required to be separated by a '/at' statement");
                            ifError = true;
                        } else {
                            gotItMessage();
                            tasks[counter] = new Event(splitString2[0], splitString2[1]);
                            System.out.printf("  %s\n",tasks[counter].toString());
                        }

                    }

                } else{
                    System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
                    ifError = true;
//                    tasks[counter] = new Task(next);
//                    System.out.println("added: "+ next);
                }
                if (ifError == false) {

                    counter +=1;
                    System.out.printf("Now you have %d tasks in the list\n", counter);
                }
                ifError = false;

            }
            }
        }

    private static void gotItMessage() {
        System.out.println("Got it. I've added this task:");
    }
}

