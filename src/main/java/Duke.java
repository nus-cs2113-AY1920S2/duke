import java.util.Scanner;

public class Duke {

    public static void printList(String[] array, int curr) {
        for (int i = 0; i < curr; i++) {
            System.out.println(i+1 + ". " + array[i]);
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String intro = "____________________________________________________________\n" +
                " Hello! I'm Duke\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n";

        String outro = "____________________________________________________________\n" +
                " Bye. Hope to see you again soon!\n" +
                "____________________________________________________________";

        String line = "____________________________________________________________\n";

        String[] array;
        array = new String[100];
        int curr = 0;

        System.out.println(intro);

        Scanner in = new Scanner(System.in);
        String s = in.nextLine();

        while (!s.equals("bye")) {
            if (s.equals(("list"))) {
                //function
                System.out.println(line);
                printList(array, curr);
                System.out.println(line);
            }
            else {
                System.out.println(line);
                System.out.println("added: " + s);
                System.out.println(line);
                array[curr] = s;
                curr++;
            }
            in = new Scanner(System.in);
            s = in.nextLine();
        }

        System.out.println(outro);
    }
}
