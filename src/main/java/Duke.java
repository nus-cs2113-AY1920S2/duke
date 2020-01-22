import java.util.Scanner;

public class Duke {
    public static void doLine(){
        String line = "_".repeat(60);
        System.out.println("\t"+line);
    }
    public static void doGreeting(){
        doLine();
        System.out.println("\tHello! I'm Duke\n\tWhat can I do for you?");
        doLine();
    }
    public static void doFarewell(){
        doLine();
        System.out.println("\tBye. Hope to see you again soon!");
        doLine();
    }
    public static void main(String[] args) {
        doGreeting();
        while(true){
            String line;
            Scanner in = new Scanner(System.in);
            line = in.nextLine();
            if(line.equals("bye")){
                doFarewell();
                break;
            }else{
                doLine();
                System.out.println("\t"+line);
                doLine();
            }

        }

    }
}
