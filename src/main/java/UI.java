import java.util.Scanner;

public class UI {
    protected static Scanner input;

    public static void initUI() {
        input = new Scanner(System.in);
    }

    /** Reads the next line of user input */
    public static String getNextLine() {
        return input.nextLine();
    }

    /** Prints line divider */
    public static void br () {
        System.out.println("    ...................................................");
    }

    /** Prints the greeting message */
    public static void printGreetMessage() {
        br();
        String logo =
                "        ┌┬┐┌─┐┌─┐┬┌─\n" +
                        "         │││ ││ │├┴┐\n" +
                        "        ─┴┘└─┘└─┘┴ ┴";
        System.out.println("\t Hello! I am \n" + logo);
        System.out.println("\t Does the human have a request today?");
        br();
    }

    /** Prints the goodbye message */
    public static void printEndMessage() {
        br();
        System.out.println("\t Goodbye, see you in the seventh dimension!");
        System.out.println("                   *       +\n" +
                "             '                  |\n" +
                "         ()    .-.,=\"``\"=.    - o -\n" +
                "               '=/_       \\     |\n" +
                "            *   |  '=._    |\n" +
                "                 \\     `=./`,        '\n" +
                "              .   '=.__.=' `='      *\n" +
                "     +                         +\n" +
                "          O      *        '       .");
        br();
    }
}
