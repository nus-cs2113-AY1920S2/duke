import java.util.Scanner;

public class Duke {
    public static void br () {
        System.out.println("    ...................................................");
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        br();
        String logo =
                "        ┌┬┐┌─┐┌─┐┬┌─\n" +
                "         │││ ││ │├┴┐\n" +
                "        ─┴┘└─┘└─┘┴ ┴";
        System.out.println("     Hello! I am \n" + logo);
        System.out.println("     Does the human have a request today?");
        br();

        String command = " ";
        String[] list = new String[100];
        int num_items = 0;
        while (true) {
            command = input.nextLine();
            if (command.equals("bye")) break;

            switch(command) {
                case "list":
                    br();
                    for (int i=0; i<num_items; i++) {
                        int num = i+1;
                        System.out.println("     " + num + ". " + list[i]);
                    }
                    br();
                    break;
                default:
                    list[num_items] = command; num_items++;
                    br(); System.out.println("     added: " + command); br();
                    break;
            }
        }

        br();
        System.out.println("     Goodbye, see you in the seventh dimension!");
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
