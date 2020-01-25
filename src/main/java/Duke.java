import java.util.Scanner; //object takes in user input

public class Duke {
    public static void main(String[] args) {
        
        String introMessage = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" 
        + "Hello! I'm Duke\n"
        + "What can I do for you?\n"
        + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"
        + System.lineSeparator();
        String goodbyeMessage = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
        + "Bye! Hope to see you again soon\n"
        + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"
        + System.lineSeparator();
        String goodbyeMessage2 = "********************CONNECTION TERMINATED********************";
        
        System.out.println(introMessage);
        //Level-1: run echo command loop, break on input "bye"
        while (true){
            System.out.print(">>>");  //easier to identify lines input by user (a la Python)  
            String line;
            Scanner in = new Scanner(System.in);

            line = in.nextLine();
            if (line.equals("bye")){
                break;
            } else{
                System.out.println( "\t____________________________________________________________\n\t"
                + line + System.lineSeparator()
                + "\t____________________________________________________________");
            }
        }

        System.out.println(goodbyeMessage);
        System.out.println(goodbyeMessage2);
    }
}
