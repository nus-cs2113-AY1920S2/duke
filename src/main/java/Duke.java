import java.util.Scanner;
import java.util.Arrays;

public class Duke {
    public static void br () {
        System.out.println("    ...................................................");
    }

    public static void printStartMessage() {
        br();
        String logo =
                "        ┌┬┐┌─┐┌─┐┬┌─\n" +
                        "         │││ ││ │├┴┐\n" +
                        "        ─┴┘└─┘└─┘┴ ┴";
        System.out.println("     Hello! I am \n" + logo);
        System.out.println("     Does the human have a request today?");
        br();
    }

    public static void printEndMessage() {
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
        printStartMessage();

        String command = " ";
        String[] list = new String[100];
        int numItems = 0;
        boolean [] isDone = new boolean[100];
        Arrays.fill(isDone, false);

        while (true) {
            command = input.nextLine();
            if (command.equals("bye")) {
                break;
            }
            String[] words = command.split(" ");

            switch(words[0]) {
            case "list":
                br();
                System.out.println("\t Dook will list your tasks now:");
                for (int i=0; i<numItems; i++) {
                    String marker;
                    if (isDone[i]) {
                        marker = "✓";
                    } else {
                        marker = "✗";
                    }
                    int num = i+1;
                    System.out.print("\t " + num + ". " + "[" + marker + "] ");
                    System.out.println(list[i]);
                }
                br();
                break;
            case "done":
                br();
                System.out.println("\t Dun dun dun dun! This task is done:");
                int itemCode = Integer.parseInt(words[1]); itemCode--;            // -1 for zero-based indexing
                isDone[itemCode] = true;
                System.out.println("\t   [" + "✓" + "] " + list[itemCode]);
                br();
                break;
            default:
                list[numItems] = command; numItems++;
                br(); System.out.println("     added: " + command); br();
                break;
            }
        }

        printEndMessage();
    }
}
