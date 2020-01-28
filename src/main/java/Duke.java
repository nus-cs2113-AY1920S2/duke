import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = "***John***";
        String[] tasks = new String[100];
        String userInput = "";
        int count = 0;
        boolean isFinished = false;

        System.out.println("Hey! I am " + logo);
        System.out.println("What would you like to do?");

        Scanner in = new Scanner(System.in);
        userInput = in.nextLine();

        while(!isFinished){
            switch (userInput) {
            case "bye":
                isFinished = true;
                break;
            case "list":
                for (int j = 0; j<count; j++) {
                    System.out.println(j+1 + ". " + tasks[j]);
                }
                break;
            default:
                System.out.println("added: " + userInput);
                tasks[count] = userInput;
                count++;
                break;
            }
            userInput = in.nextLine();
        }
        System.out.println("Bye. Hope to see you soon!");
    }
}
