import java.awt.desktop.SystemSleepEvent;
import java.util.Scanner;

public class Duke {
    public static void greet(){
        System.out.println("    ____________________________________________________________");
        System.out.println("\tHello! I'm Renzo");
        System.out.println("\tWhat can I do for you? Please enter your command:");
        System.out.println("    ____________________________________________________________");
        System.out.println("\nPlease enter your command or enter \"bye\" to exit:");
    }

    public static void list(){
        System.out.println("    ____________________________________________________________");
        System.out.println("\tlist");
        System.out.println("    ____________________________________________________________");
        System.out.println("\nPlease enter your command or enter \"bye\" to exit:");
    }

    public static void bye(){
        System.out.println("    ____________________________________________________________");
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
    }

    public static void echo(String command){
        System.out.println("    ____________________________________________________________");
        System.out.println("\t"+command);
        System.out.println("    ____________________________________________________________");
        System.out.println("\nPlease enter your command or enter \"bye\" to exit:");
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

        /* Enable to get command from Command Line */
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();

        while (!command.equals("bye")){
            echo(command);
            command = scanner.nextLine();
        }

        bye();
    }
}
