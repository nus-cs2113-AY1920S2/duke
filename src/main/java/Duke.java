import java.util.Scanner;
import java.util.Arrays;

public class Duke {

    public static void main(String[] args) {
        String greeting = "Hello! I'm Duke\n" + "What can I do for you?";
        String goodbye = "Bye. Hope to see you again soon!";
        System.out.println(greeting);

        String[] tasklist = new String[100];

        Scanner scanner = new Scanner(System.in);
        int flag = 0;
        int index = 0;

        while (flag == 0) {
            String x = scanner.nextLine();
            if (x.equals("bye")) {
                System.out.println(goodbye);
                flag = 1;
            } else if (x.equals("list")){
                for (int i = 1; i <= index; i++) {
                    System.out.print(i);
                    System.out.print(". " + tasklist[i-1] + "\n");
                }
            } else {
                System.out.println("added: " + x);
                tasklist[index] = x;
                index++;
            }
        }



    }


}
