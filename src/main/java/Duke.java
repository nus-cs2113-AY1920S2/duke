import java.util.Scanner;

public class Duke {

    /**
     * Prints horizontal line for chat bot
     */
    public static void printLine (boolean hasNewline) {
        System.out.println("  ________________________________________________________________");

        if (hasNewline) {
            System.out.println();
        }
    }

    /**
     * Prints logo for the bot
     */
    public static void printLogo () {

        String logo = "\t ________  ________  ________  ________  _________  ________     \n" +
                "\t|\\_____  \\|\\   __  \\|\\   __  \\|\\   __  \\|\\___   ___\\\\   __  \\    \n" +
                "\t \\|___/  /\\ \\  \\|\\  \\ \\  \\|\\  \\ \\  \\|\\  \\|___ \\  \\_\\ \\  \\|\\  \\   \n" +
                "\t     /  / /\\ \\   __  \\ \\   ____\\ \\   __  \\   \\ \\  \\ \\ \\  \\\\\\  \\  \n" +
                "\t    /  /_/__\\ \\  \\ \\  \\ \\  \\___|\\ \\  \\ \\  \\   \\ \\  \\ \\ \\  \\\\\\  \\ \n" +
                "\t   |\\________\\ \\__\\ \\__\\ \\__\\    \\ \\__\\ \\__\\   \\ \\__\\ \\ \\_______\\\n" +
                "\t    \\|_______|\\|__|\\|__|\\|__|     \\|__|\\|__|    \\|__|  \\|_______|\n" +
                "\t                                                                 ";

        System.out.println(logo + "\n");
    }

    /**
     * Greets user
     */
    public static void greetUser () {
        String name = "Zapato";

        printLine(false);
        printLogo();

        System.out.println("\tHello from " + name + ":)");
        System.out.println("\tWhat can I do for you?");
        printLine(true);
    }

    /**
     * Displays farewell message
     */
    public static void displayFarewell () {
        printLine(false);
        System.out.println("\tBye. Hope to see you soon :)!");
        printLine(true);
    }

    /**
     * Repeats whatever message it receives
     *
     * @param echo Message to print
     */
    public static void replayBack (String echo) {
        printLine(false);
        System.out.println("\t" + echo);
        printLine(true);
        System.out.println();
    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        greetUser();

        String userResponse = "";

        while (!userResponse.equals("bye")) {
            userResponse = input.nextLine();

            if (!userResponse.equals("bye")) {
                replayBack(userResponse);
            }
        }

        displayFarewell();

    }
}
