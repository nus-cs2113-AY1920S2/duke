import java.util.Scanner; //object takes in user input

public class Duke {

    public static void sayIntro(){
         String introMessage = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n" 
        + "Hello! I'm Duke\n"
        + "What can I do for you?\n"
        + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"
        + System.lineSeparator();

        System.out.println(introMessage);
    }

    public static void sayGoodbye(){
        String goodbyeMessage = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
        + "Bye! Hope to see you again soon\n"
        + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"
        + System.lineSeparator();
        String goodbyeMessage2 = "********************CONNECTION TERMINATED********************";

        System.out.println(goodbyeMessage);
        System.out.println(goodbyeMessage2);
    }

    public static void main(String[] args) {

        String[] taskList = new String[100];
        int taskCount = 0;

        sayIntro();
        
        while (true){
            //easier to identify lines input by user (a la Python)  
            System.out.print(">>>");
            String line;
            Scanner in = new Scanner(System.in);

            line = in.nextLine();
            if (line.equals("bye")){
                break;
            } 
            //Level-2: add list functionality
            else if (line.equals("list")) {
                if (taskCount == 0){
                    System.out.println("\t____________________________________________________________\n"
                        + "\tThe list is empty." + System.lineSeparator()
                        + "\t____________________________________________________________");
                    continue;
                }
                System.out.println( "\t____________________________________________________________\n");
                for (int i=0; i<taskCount;i++){
                    System.out.println("\t" + Integer.toString(i+1) + ". " + taskList[i]);
                }
                System.out.println("\t____________________________________________________________");
            } else{
                taskList[taskCount] = line;
                taskCount++;
                System.out.println( "\t____________________________________________________________\n\t"
                + "added: " + line + System.lineSeparator()
                + "\t____________________________________________________________");
            }
        }

        sayGoodbye();
    }
}
