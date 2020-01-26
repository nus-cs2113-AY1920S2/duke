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

        Task[] taskList = new Task[100];
        int taskCount = 0;

        sayIntro();
        
        while (true){
            //easier to identify lines input by user (a la Python)  
            System.out.print(">>>");
            String userInput;
            Scanner in = new Scanner(System.in);

            userInput = in.nextLine();
            String[] tokenizedInput = userInput.split(" ");
            if (tokenizedInput[0].equals("bye")){
                break;
            } else if (tokenizedInput[0].equals("list")) {     //Level-2: add list functionality
                //empty list
                if (taskCount == 0){
                    System.out.println("\t____________________________________________________________\n"
                    + "\tThe list is empty." + System.lineSeparator()
                    + "\t____________________________________________________________");
                    continue;
                }
                //non-empty list enters this loop
                System.out.println("\t____________________________________________________________\n");
                for (int i=0; i<taskCount;i++){
                    System.out.println("\t" + Integer.toString(i+1)
                    + ".[" + taskList[i].getStatusIcon() + "] " + taskList[i].getDescription());
                }
                System.out.println("\t____________________________________________________________");
            } else if(tokenizedInput[0].equals("done")) {       //Level-3: add MarkAsDone functionality
                int queryNumber = Integer.parseInt(tokenizedInput[1]);

                //handle out of range done input
                if (queryNumber < 1 || queryNumber > taskCount){
                    System.out.println("\t____________________________________________________________\n"
                    + "\tInvalid task number." + System.lineSeparator()
                    + "\t____________________________________________________________");
                    continue;
                }
                //handle tasks already marked done
                if (taskList[queryNumber-1].getIsDone()){
                    System.out.println("\t____________________________________________________________\n"
                    + "\tThis task has already been marked completed." + System.lineSeparator()
                    + "\t____________________________________________________________");
                    continue;
                }
                taskList[queryNumber-1].markAsDone();
                System.out.println("\t____________________________________________________________\n"
                + "\tGreat job! I've marked this task as done:\n" + "\t" + Integer.toString(queryNumber) 
                + ".[" + taskList[queryNumber-1].getStatusIcon() + "] " + taskList[queryNumber-1].getDescription() 
                + "\n\t____________________________________________________________");
            } else{
                taskList[taskCount] = new Task(userInput);
                taskCount++;
                System.out.println( "\t____________________________________________________________\n\t"
                + "added: " + userInput + System.lineSeparator()
                + "\t____________________________________________________________");
            }
        }

        sayGoodbye();
    }
}
