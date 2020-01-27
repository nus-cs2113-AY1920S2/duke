import java.util.Scanner;

public class Duke {
    public static final int maxCmd = 100;

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Scanner sc = new Scanner(System.in);

        boolean continueRun = true;
        String userCmd = "";
        String[] cmdArr = new String[maxCmd];
        int cmdCounter = 0;

        while (continueRun == true){
            System.out.println("==========================");
            System.out.println("What can I do for you?");
            userCmd = sc.nextLine();

            System.out.println("You have typed: " +userCmd);

            if (userCmd.toLowerCase().equals("bye")){
                System.out.println("Bye. Hope to see you again!");
                continueRun = false;
            }
            else if (userCmd.toLowerCase().equals("list")){
                System.out.println("Current command list: ");

                for (int i = 0; i < cmdCounter; i++){
                    int cmdNum = i + 1;
                    System.out.println( cmdNum +". " +cmdArr[i]);
                }
            }
            else{
                cmdArr[cmdCounter] = userCmd;
                cmdCounter++;

                System.out.println("Text added: " +userCmd);
            }
        }
    }
}
