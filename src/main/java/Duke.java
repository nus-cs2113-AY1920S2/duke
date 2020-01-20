import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
//        String logo = " __    __       \n"
//                +     "|  | /  /___      \n"
//                +     "|  |/  /  _  \\  \n"
//                +     "|      \\   __/   \n"
//                +     "|__|\\___\\ __|    \n";

        String logo = " __    __      __    __            \n"
                +     "|  | /  /___  |  | /  /___        \n"
                +     "|  |/  /  _  \\|  |/  /  _  \\    \n"
                +     "|      \\   __/|      \\   __/    \n"
                +     "|__|\\___\\ __| |__|\\___\\ __|   \n";
        System.out.println("Hello from\n" + logo);
        System.out.println("===================================================");
        String s1 = String.format("%50s","What's your name?");
        System.out.println(s1);
        Scanner sc = new Scanner(System.in);
        String name = sc.nextLine();
        System.out.println("===================================================");


        System.out.println(String.format("%50s","Hello " + name + ", Anything I can help you with? "));
        System.out.println("===================================================");

        String[] stringArray = new String[100];
        int counter = 0;

        while (true) {
            String input = sc.nextLine();
            if (input.equalsIgnoreCase("list")) {
                for (int i = 1; i <= counter; i++) {
                    System.out.println(i +". "+ stringArray[i]);
                }
                System.out.println("===================================================");
            } else if (input.equalsIgnoreCase("bye")) {
                System.out.println(String.format("%50s","Bye, "+ name + ". Hope to see you again soon!"));
                System.out.println("===================================================");
                break;
            } else {
                counter ++;
                System.out.println(String.format("%50s","added: "+ input));
                stringArray[counter] = input;
                System.out.println("===================================================");
            }
        }

    }
}
