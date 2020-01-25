import java.util.Scanner;
import java.util.Arrays;

public class Duke {
    public static void br () {
        System.out.println("    ...................................................");
    }

    public static void startMessage() {
        br();
        String logo =
                "        ┌┬┐┌─┐┌─┐┬┌─\n" +
                        "         │││ ││ │├┴┐\n" +
                        "        ─┴┘└─┘└─┘┴ ┴";
        System.out.println("     Hello! I am \n" + logo);
        System.out.println("     Does the human have a request today?");
        br();
    }

    public static void endMessage() {
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

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        startMessage();

        String command = " ";
        String[] list = new String[100];
        int num_items = 0;
        boolean [] is_done = new boolean[100];
        Arrays.fill(is_done, false);

        while (true) {
            command = input.nextLine();
            if (command.equals("bye")) break;
            String[] words = command.split(" ");

            switch(words[0]) {
            case "list":
                br();
                System.out.println("\t Dook will list your tasks now:");
                for (int i=0; i<num_items; i++) {
                    String marker;
                    if (is_done[i]) marker = "✓";
                    else marker = "✗";
                    int num = i+1;
                    System.out.print("\t " + num + ". " + "[" + marker + "] ");
                    System.out.println(list[i]);
                }
                br();
                break;
            case "done":
                br();
                System.out.println("\t Dun dun dun dun! This task is done:");
                int item_code = Integer.parseInt(words[1]); item_code--;            // -1 for zero-based indexing
                is_done[item_code] = true;
                System.out.println("\t   [" + "✓" + "] " + list[item_code]);
                br();
                break;
            default:
                list[num_items] = command; num_items++;
                br(); System.out.println("     added: " + command); br();
                break;
            }
        }

        endMessage();
    }
}
