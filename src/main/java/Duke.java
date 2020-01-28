import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = "***John***";
        String userInput;
        System.out.println("Hey! I am " + logo);
        System.out.println("What would you like to do?");

        Scanner in = new Scanner(System.in);
        userInput = in.nextLine();
        while(!userInput.equals("bye")){
            System.out.println(userInput);
            userInput = in.nextLine();
        }
        System.out.println("Bye. Hope to see you soon!");
    }
}
