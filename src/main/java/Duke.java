//Scanner object takes in user input
import java.util.Scanner;

public class Duke {
    public static final int MAX_NO_OF_TASKS = 100;
    public static final int MAX_SUBSTRING_FIELDS = 2;
    public static int taskCount = 0;
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

    public static String[] splitUserInput(String originalInput) throws ArrayIndexOutOfBoundsException, 
    NoDescriptionException, NoRemarkException {
        String[] returnValue = new String[MAX_SUBSTRING_FIELDS];
        if (originalInput.contains(" /")){
            String[] separatedSections = originalInput.split(" /");
            String commandWord = separatedSections[0].split(" ", 2)[0];
            // get description part of userInput without the command word
            returnValue[0] = separatedSections[0].split(" ", 2)[1];
            if (returnValue[0].trim().length() == 0) {
                throw new NoDescriptionException();
            }
            // get additional remark part of userInput
            returnValue[1] = separatedSections[1];
            boolean isRemarksEmpty = ((commandWord.equals("event") || commandWord.equals("deadline")) 
            && returnValue[1].trim().length() == 0);
            if (isRemarksEmpty){
                throw new NoRemarkException();
            }
            return returnValue;
        } else {
            // get description part of userInput without the command word
            try {
                returnValue[0] = originalInput.trim().split(" ", 2)[1];
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new NoDescriptionException();
                
            }
            
            // remark column is an empty string
            returnValue[1] = "";
            return returnValue;
        }
    }

    public static void addTaskToList(Task[] listInput) {
        //if list empty, inform user and await next command
        if (taskCount == 0) {
            System.out.println(underscoredLine + System.lineSeparator() + "\tThe list is empty." 
            + System.lineSeparator() + underscoredLine);
            return;
        }
        //if list non-empty, print out all existing tasks
        System.out.println(underscoredLine + System.lineSeparator());
        for (int i = 0; i < taskCount; i++) {
            System.out.println("\t" + Integer.toString(i + 1) + "." + listInput[i].toString());
        }
        System.out.println(underscoredLine);
        return;
    }

    public static void updateTaskDone(String taskNumberInput, Task[] listInput){
        int queryNumber = Integer.parseInt(taskNumberInput);
        boolean isOutOfRange = queryNumber < 1 || queryNumber > taskCount;
        
        //handle case where user inputs non-existing task number to mark as done
        if (isOutOfRange){
            System.out.println(underscoredLine + System.lineSeparator() + "\tInvalid task number."
            + System.lineSeparator() + underscoredLine);
            return;
        }
        //handle case where user tries to mark as done an already completed task
        boolean isTaskAlreadyDone = listInput[queryNumber-1].getIsDone();
        
        if (isTaskAlreadyDone){
            System.out.println(underscoredLine + System.lineSeparator()
            + "\tThis task has already been marked completed." + System.lineSeparator() + underscoredLine);
            return;
        }
        listInput[queryNumber-1].markAsDone();
        System.out.println(underscoredLine + System.lineSeparator() + "\tGreat job! I've marked this task as done:\n"
        + "\t" + Integer.toString(queryNumber) + ".[" + listInput[queryNumber-1].getStatusIcon() + "] "
        + listInput[queryNumber-1].getDescription() + System.lineSeparator() + underscoredLine);
    }

    private static void insertNewTask(Task[] taskList, String userInput, String[] tokenizedInput) throws 
    IllegalKeywordException, NoDescriptionException, NoRemarkException {
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
            throw new IllegalKeywordException();
            //break;
        }

        taskList[taskCount] = newTask;
        taskCount++;
        System.out.println(underscoredLine + System.lineSeparator() + "\tGot it. I've added this task: \n\t"
        + newTask.toString() + System.lineSeparator() + "\tNow you have " + taskCount + " tasks in the list.\n"
        + underscoredLine);
    }

    public static void main(String[] args) {
        Task[] taskList = new Task[MAX_NO_OF_TASKS];
        taskCount = 0;
        String userInput;
        Scanner in = new Scanner(System.in);

        sayIntro();
        //easier to identify lines input by user (per Python)
        System.out.print(">>>");
        
        while (in.hasNextLine()) {
            userInput = in.nextLine();
            String[] tokenizedInput = userInput.split(" ");
            if (tokenizedInput[0].equals("bye")) {
                break;
            } else if (tokenizedInput[0].equals("list")) {     
                addTaskToList(taskList);
            } else if (tokenizedInput[0].equals("done")) {
                updateTaskDone(tokenizedInput[1], taskList);
            } else {
                try{
                    insertNewTask(taskList, userInput, tokenizedInput);
                } catch (IllegalKeywordException e) {
                    System.out.println(underscoredLine);
                    System.out.println("\t\u2639 !!ERROR!! I'm sorry, but I don't know what that means :-(");
                    System.out.println(underscoredLine);
                } catch (NoDescriptionException e) {
                    System.out.println(underscoredLine);
                    System.out.println("\t\u2639 !!ERROR!! The description of a " + tokenizedInput[0] + " cannot be empty.");
                    System.out.println(underscoredLine);
                } catch (NoRemarkException e) {
                    System.out.println(underscoredLine);
                    System.out.println("\t\u2639 !!ERROR!! The remarks section of a " + tokenizedInput[0] + " cannot be empty.");
                    System.out.println(underscoredLine);
                }
            }
            System.out.print(">>>");
        }

        sayGoodbye();
    }

}
