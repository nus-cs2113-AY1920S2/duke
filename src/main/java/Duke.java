import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = "***John***";
        Task[] tasks = new Task[100];
        String userInput = "";
        int count = 0;

        System.out.println("Hey! I am " + logo);
        System.out.println("What would you like to do?");

        Scanner in = new Scanner(System.in);

        while(true){
            userInput = in.nextLine();
            if (userInput.equals("bye")){
                break;
            }
            else if (userInput.equals("list")){
                for (int j = 0; j<count; j++) {
                    System.out.println(j+1 + ". " + tasks[j]);
                }
            }
            else if (userInput.startsWith("done ")){
                int itemNumber = Integer.parseInt((userInput.substring(5)));
                tasks[itemNumber-1].markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  " + tasks[itemNumber-1]);
            }
            else {
                System.out.println("added: " + userInput);
                tasks[count] = new Task(userInput);
                count++;
            }
        }
        System.out.println("Bye. Hope to see you soon!");
    }
}
