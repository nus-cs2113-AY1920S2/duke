import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String line = "__________________________________________________________";
        String banner="~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
                +"~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
                +"~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
                +"~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
                +"~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n";
        String logo = "~~~~~~~~~~~~~~~~~~ ____        _        ~~~~~~~~~~~~~~~~~~\n"
                + "~~~~~~~~~~~~~~~~~~|  _ \\ _   _| | _____ ~~~~~~~~~~~~~~~~~~\n"
                + "~~~~~~~~~~~~~~~~~~| | | | | | | |/ / _ \\~~~~~~~~~~~~~~~~~~\n"
                + "~~~~~~~~~~~~~~~~~~| |_| | |_| |   <  __/~~~~~~~~~~~~~~~~~~\n"
                + "~~~~~~~~~~~~~~~~~~|____/ \\__,_|_|\\_\\___|~~~~~~~~~~~~~~~~~~\n";
        System.out.println(line);
        System.out.println("Hello from\n" + banner + logo + banner);
        System.out.println("What can I do for you?");

        System.out.println(line);
        try {
            while(in.hasNext()){
                String userIn = in.nextLine();
                if (userIn.equals("bye")) {
                    System.out.println(line);
                    System.out.println("Bye.Hope to see you again soon!");
                    System.out.println(line);
                    break;
                } else {
                    System.out.println(line);
                    System.out.println(userIn);
                    System.out.println(line);
                }
            }
        }
        finally{
            in.close();
        }
    }
}
