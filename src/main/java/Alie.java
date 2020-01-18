import java.util.Scanner;

public class Alie {
    public static void welcomeMsg() {
        String logo =
                "    /\\       |        |   |‾‾‾‾‾    \n"
              + "   /  \\      |        |   |         \n"
              + "  /____\\     |        |   |----     \n"
              + " /      \\    |        |   |         \n"
              + "/        \\ . |_____ . | . |_____ .  \n";
        System.out.println("Hello from\n" + logo);
        indentation();
        System.out.println("What would you like to do?");
    }
    public static void indentation() {
        System.out.print("ALIE: ");
    }
    public static boolean processInput(String userInput) {
        indentation();
        if (userInput.equals("bye")) {
            System.out.println("Bye-bye!");
            return false;
        } else {
            System.out.println(userInput);
        }
        return true;
    }

    public static void main (String[] args) {
        welcomeMsg();

        Scanner userInput = new Scanner(System.in);
        boolean hasMoreCmds = true;
        while (hasMoreCmds) {
            String cmd = userInput.nextLine();
            hasMoreCmds = processInput(cmd);
        }
    }
}