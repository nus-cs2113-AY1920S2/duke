//Scanner object takes in user input
import java.util.Scanner;

public class Duke {
    private static String curlyLine = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
    private static String underscoredLine = "\t____________________________________________________________";

    private static void sayIntro(){
        String introMessage = curlyLine + System.lineSeparator() + "Hello! I'm Duke\n"
        + "What can I do for you?\n" + curlyLine + System.lineSeparator();

        System.out.println(introMessage);
    }

    private static void sayGoodbye(){
        String goodbyeMessage = curlyLine + System.lineSeparator() + "Bye! Hope to see you again soon\n" 
        + curlyLine + System.lineSeparator();
        String goodbyeMessage2 = "********************CONNECTION TERMINATED********************";

        System.out.println(goodbyeMessage);
        System.out.println(goodbyeMessage2);
    }

    public static String[] splitUserInput(String originalInput){
        String[] returnValue = new String[2];
        if (originalInput.contains(" /")){
            String[] separatedSections = originalInput.split(" /");
            // get description part of userInput without the command word
            returnValue[0] = separatedSections[0].split(" ", 2)[1];
            // get additional remark part of userInput
            returnValue[1] = separatedSections[1];
            return returnValue;
        } else {
            // get description part of userInput without the command word
            returnValue[0] = originalInput.trim().split(" ", 2)[1];
            // remark column is an empty string
            returnValue[1] = "";
            return returnValue;
        }
    }

    public static void main(String[] args) {

        Task[] taskList = new Task[100];
        int taskCount = 0;

        sayIntro();
        
        while (true) {
            //easier to identify lines input by user (per Python)
            System.out.print(">>>");
            String userInput;
            Scanner in = new Scanner(System.in);

            userInput = in.nextLine();
            String[] tokenizedInput = userInput.split(" ");
            if (tokenizedInput[0].equals("bye")) {
                break;
            } else if (tokenizedInput[0].equals("list")) {     
                //if list empty, inform user and await next command
                if (taskCount == 0){
                    System.out.println(underscoredLine + System.lineSeparator() + "\tThe list is empty." 
                    + System.lineSeparator() + underscoredLine);
                    continue;
                }
                //if list non-empty, print out all existing tasks
                System.out.println(underscoredLine + System.lineSeparator());
                for (int i=0; i<taskCount;i++){
                    System.out.println("\t" + Integer.toString(i+1) + "." + taskList[i].toString());
                }
                System.out.println(underscoredLine);
            } else if (tokenizedInput[0].equals("done")) {       
                int queryNumber = Integer.parseInt(tokenizedInput[1]);

                //handle case where user inputs non-existing task number to mark as done
                if (queryNumber < 1 || queryNumber > taskCount){
                    System.out.println(underscoredLine + System.lineSeparator() + "\tInvalid task number." + 
                    System.lineSeparator() + underscoredLine);
                    continue;
                }
                //handle case where user tries to mark as done an already completed task
                if (taskList[queryNumber-1].getIsDone()){
                    System.out.println(underscoredLine + System.lineSeparator()
                    + "\tThis task has already been marked completed." + System.lineSeparator() + underscoredLine);
                    continue;
                }
                taskList[queryNumber-1].markAsDone();
                System.out.println(underscoredLine + System.lineSeparator() + "\tGreat job! I've marked this task as done:\n" 
                + "\t" + Integer.toString(queryNumber) + ".[" + taskList[queryNumber-1].getStatusIcon() + "] "
                + taskList[queryNumber-1].getDescription() + System.lineSeparator() + underscoredLine);
            } else {
                Task newTask;
                switch (tokenizedInput[0]) {
                case ("todo"):
                    newTask = new Todo(splitUserInput(userInput)[0], splitUserInput(userInput)[1]);
                    break;
                case ("deadline"):
                    newTask = new Deadline(splitUserInput(userInput)[0], splitUserInput(userInput)[1]);
                    break;
                case ("event"):
                    newTask = new Event(splitUserInput(userInput)[0], splitUserInput(userInput)[1]);
                    break;
                default:
                    newTask = new Task(userInput);
                    break;
                }

                taskList[taskCount] = newTask;
                taskCount++;
                System.out.println(underscoredLine + System.lineSeparator() + "\tGot it. I've added this task: \n\t"
                + newTask.toString() + System.lineSeparator() + "\tNow you have " + taskCount + " tasks in the list.\n" 
                + underscoredLine);
            }
        }

        sayGoodbye();
    }


}
