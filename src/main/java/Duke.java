import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean continueRun = true;

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        while (continueRun == true){
            System.out.println("==========================");
            System.out.println("What can I do for you?");
            String userCmd = sc.nextLine();

            System.out.println("You have typed: " +userCmd);

            if (userCmd.toLowerCase().equals("bye")){
                System.out.println("Bye. Hope to see you again!");
                continueRun = false;
            }
        }



    }
}
