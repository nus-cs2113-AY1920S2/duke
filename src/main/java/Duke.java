import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = "Nyan\n";
        System.out.println("    ____________________________________________________________");
        System.out.print("    Hello! I'm " + logo);
        System.out.println("    ____________________________________________________________");

        Scanner input = new Scanner(System.in);

        while(true){
            String s = input.nextLine();
            System.out.println("    ____________________________________________________________");
            if (s.contains("bye")){
                System.out.println("    Bye. Hope to see you soon!");
                System.out.println("    ____________________________________________________________");
                break;
            } else {
                System.out.println("    " + s);
            }
            System.out.println("    ____________________________________________________________");
        }
    }
}

