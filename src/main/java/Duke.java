import java.util.Scanner;
import java.util.Arrays;

public class Duke {

    public static void main(String[] args) {
        String greeting = "Hello! I'm Duke\n" + "What can I do for you?";
        String goodbye = "Bye. Hope to see you again soon!";
        System.out.println(greeting);

        Task[] tasklist = new Task[100];

        Scanner scanner = new Scanner(System.in);
        int flag = 0;
        int index = 0;

        while (flag == 0) {
            String userinput = scanner.nextLine();
            if (userinput.equals("bye")) {
                System.out.println(goodbye);
                flag = 1;
            } else if (userinput.equals("list")){
                for (int i = 1; i <= index; i++) {
                    System.out.print(i);
                    System.out.print(".[" + tasklist[i-1].getStatusIcon() + "] " + tasklist[i-1].description + "\n");
                }
            } else if (userinput.startsWith("done")){
                String IndexOfItem = userinput.substring(5);
                int ID = Integer.valueOf(IndexOfItem) - 1;
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println(tasklist[ID].markAsdone());
            } else {
                System.out.println("added: " + userinput);
                tasklist[index] = new Task(userinput);
                index++;
            }
        }



    }


}
