import java.awt.desktop.SystemSleepEvent;

public class Duke {
    public static void greet(){
        System.out.println("    ____________________________________________________________");
        System.out.println("\tHello! I'm Renzo");
        System.out.println("\tWhat can I do for you?");
        System.out.println("    ____________________________________________________________");
    }

    public static void list(){
        System.out.println("\n" +
                "    ____________________________________________________________");
        System.out.println("\tlist");
        System.out.println("    ____________________________________________________________");
    }

    public static void bye(){
        System.out.println("\n" +
                "    ____________________________________________________________");
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
    }

    public static void echo(String command){
        System.out.println("\n" +
                "    ____________________________________________________________");
        System.out.println("\t"+command);
        System.out.println("    ____________________________________________________________");
    }

    public static void main(String[] args) {
        String logo = " _____    _____   __   _   ______  _____  \n"
                +"|  _  \\  | ____| |  \\ | | |___  / /  _  \\ \n"
                +"| |_| |  | |__   |   \\| |    / /  | | | | \n"
                +"|  _  /  |  __|  | |\\   |   / /   | | | | \n"
                +"| | \\ \\  | |___  | | \\  |  / /__  | |_| | \n"
                +"|_|  \\_\\ |_____| |_|  \\_| /_____| \\_____/ \n";
        System.out.println(logo);
        greet();
        String[] commands = {"list","blah","blah","bye"};
        int i = 0;
        String command = commands[i++];
        while (!command.equals("bye")){
            echo(command);
            command = commands[i++];
        }
        bye();
    }
}
