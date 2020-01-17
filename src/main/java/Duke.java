import java.util.Scanner;

public class Duke {

    public static void greet() {
        System.out.println("    ===================================================================");
        System.out.println("    Hi! I am Duke, your next doooooorrrr friendly elf.....I mean bot");
        System.out.println("    ===================================================================");
    }

    public static void bye() {
        System.out.println("    ===================================================================");
        System.out.println("    Bye! Duke is now a freeeeee elf again!!!!");
        System.out.println("    ===================================================================");
    }

    public static void echo(String convertToLowerCase) {
        System.out.println("    ===================================================================");
        System.out.println("    " + convertToLowerCase);
        System.out.println("    ===================================================================");
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        greet();
        Scanner sc = new Scanner(System.in);
        while (true) {
            String line = sc.nextLine();
            String convertToLowerCase = line.toLowerCase();
            boolean isBye = convertToLowerCase.equals("bye");
            if (isBye) {
                bye();
                break;
            } else {
                echo(line);
            }
        }
        sc.close();
    }
}
