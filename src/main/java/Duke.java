import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = "Nyan\n";
        System.out.println("    ____________________________________________________________");
        System.out.print("    Hello! I'm " + logo);
        System.out.println("    ____________________________________________________________");

        Scanner input = new Scanner(System.in);
        String[] buffer = new String[100];
        int counter = 0;

        while(true) {
            String s = input.nextLine();
            System.out.println("    ____________________________________________________________");
            if (s.equalsIgnoreCase("bye")){
                System.out.println("    Bye. Hope to see you soon!");
                System.out.println("    ____________________________________________________________");
                break;
            } else if(s.equalsIgnoreCase("list")) {
                for(int i = 0; i < counter; i++) {
                    System.out.println("    " + (i+1) + ". " + buffer[i]);
                }
            } else {
                buffer[counter] = s;
                System.out.println("    added: " + buffer[counter]);
                counter++;
            }
            System.out.println("    ____________________________________________________________");
        }
    }
}

