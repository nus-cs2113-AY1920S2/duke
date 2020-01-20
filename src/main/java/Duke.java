import java.util.Scanner;

public class Duke {
    public static void br () {
        System.out.println("...................................................");
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String logo =
                "┌┬┐┌─┐┌─┐┬┌─\n" +
                " │││ ││ │├┴┐\n" +
                "─┴┘└─┘└─┘┴ ┴";
        System.out.println("Hello! I am \n" + logo);
        System.out.println("Does the human have a request today?");
        br();

        String command = " ";
        while (true) {
            command = input.next();
            if (command.equals("bye")) break;
            System.out.println(command); br();
        }

        System.out.println("Goodbye, see you in the seventh dimension!");
        System.out.println("                 *       +\n" +
                            "           '                  |\n" +
                            "       ()    .-.,=\"``\"=.    - o -\n" +
                            "             '=/_       \\     |\n" +
                            "          *   |  '=._    |\n" +
                            "               \\     `=./`,        '\n" +
                            "            .   '=.__.=' `='      *\n" +
                            "   +                         +\n" +
                            "        O      *        '       .");
        br();
    }
}
