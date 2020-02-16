package data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner; //Scanner object takes in user input
import java.util.ArrayList;

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
            NoDescriptionException, NoRemarkException, IllegalKeywordException {
        String[] returnValue = new String[MAX_SUBSTRING_FIELDS];
        if (originalInput.contains(" /")){
            String[] separatedSections = originalInput.split(" /");
            String commandWord = separatedSections[0].split(" ", 2)[0];

            //todo should not have a remark section
            if (commandWord.equals("todo")) {
                throw new IllegalKeywordException();
            }
            // get description part of userInput without the command word
            try{
                returnValue[0] = separatedSections[0].split(" ", 2)[1];
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new ArrayIndexOutOfBoundsException();
            }
            
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
            String[] separatedSections = originalInput.split(" /");
            String commandWord = separatedSections[0].split(" ", 2)[0];
            if (commandWord.equals("event") || commandWord.equals("deadline")) {
                throw new IllegalKeywordException();
            }
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

    

    public static void printTaskList(ArrayList<Task> listInput) {
        //if list empty, inform user and await next command
        if (taskCount == 0) {
            System.out.println(underscoredLine + System.lineSeparator() + "\tThe list is empty."
                    + System.lineSeparator() + underscoredLine);
            return;
        }
        //if list non-empty, print out all existing tasks
        System.out.println(underscoredLine + System.lineSeparator());
        for (int i = 0; i < taskCount; i++) {
            System.out.println("\t" + Integer.toString(i + 1) + "." + listInput.get(i).toString());
        }
        System.out.println(underscoredLine);
        return;
    }

    public static void updateTaskDone(String taskNumberInput, ArrayList<Task> listInput){
        int queryNumber = Integer.parseInt(taskNumberInput);
        boolean isOutOfRange = queryNumber < 1 || queryNumber > taskCount;
        
        //handle case where user inputs non-existing task number to mark as done
        if (isOutOfRange){
            System.out.println(underscoredLine + System.lineSeparator() + "\tInvalid task number."
                    + System.lineSeparator() + underscoredLine);
            return;
        }
        //handle case where user tries to mark as done an already completed task
        boolean isTaskAlreadyDone = listInput.get(queryNumber-1).getIsDone();
        
        if (isTaskAlreadyDone){
            System.out.println(underscoredLine + System.lineSeparator()
                    + "\tThis task has already been marked completed." + System.lineSeparator() + underscoredLine);
            return;
        }
        listInput.get(queryNumber-1).markAsDone();

        String taskDoneMessage = "\tGreat job! I've marked this task as done:\n\t" + Integer.toString(queryNumber)
                + ".[" + listInput.get(queryNumber-1).getStatusIcon() + "] " + listInput.get(queryNumber-1).getDescription();
        System.out.println(underscoredLine + System.lineSeparator() + taskDoneMessage + System.lineSeparator()
                + underscoredLine);
    }

    private static void insertNewTask(ArrayList<Task> taskList, String userInput, String[] tokenizedInput) throws
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

        taskList.add(newTask);
        taskCount++;

        String taskAddedMessage = "\tGot it. I've added this task: \n\t" + newTask.toString() + System.lineSeparator()
                + "\tNow you have " + taskCount + " tasks in the list.\n";
        System.out.println(underscoredLine + System.lineSeparator() + taskAddedMessage + System.lineSeparator()
                + underscoredLine);
    }

    private static void removeTask(String userInput, ArrayList<Task> taskList) throws NumberFieldException {
        int taskNumberForRemoval;
        //TODO: exceptions - second input out of bounds, not integer, no second input, only whitespaces after firstinput
        try {
            taskNumberForRemoval = Integer.parseInt(userInput.split(" ", 2)[1]);
        } catch (NumberFormatException e) {
            //throw NumberFieldException if taskNumber is a string eg. "remove foo" OR whitespaces only
            throw new NumberFieldException();
        } catch (ArrayIndexOutOfBoundsException e) {
            //throw AIOOB exception if remove cmd given without 2nd input (ie "remove")
            throw new ArrayIndexOutOfBoundsException();
        }

        //throw NumberFieldException if task number out of range
        boolean isOutOfBounds = (taskNumberForRemoval <= 0 || taskNumberForRemoval > taskCount);
        if (isOutOfBounds) {
            throw new NumberFieldException();
        }
        Task removedTask = taskList.remove(Integer.valueOf(taskNumberForRemoval)-1);
        taskCount--;

        String taskRemovedMessage = "\tGot it. I've removed this task: \n\t" + removedTask.toString() + System.lineSeparator()
                + "\tNow you have " + taskCount + " tasks in the list.\n";
        System.out.println(underscoredLine + System.lineSeparator() + taskRemovedMessage + System.lineSeparator()
                + underscoredLine);
    }
    
    //TODO: load tasklist from file data/duke.txt on startup
    private static void loadFileToTaskList(String filePath, ArrayList<Task> taskList) throws FileNotFoundException {
        File f = new File(filePath);
        if (!f.exists()) {
            throw new FileNotFoundException();
        }
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            //add task (each line) to ArrayList taskList
            //1. process each line first, construct new Todo/Event/Deadline object
            String taskString = s.nextLine();
            String[] tokenizedTaskString = taskString.split(" \\| ");
            Task newTaskToLoad;
            switch(tokenizedTaskString[0].toUpperCase()) {
            case ("T"):
                newTaskToLoad = new Todo(tokenizedTaskString[2], "");
                break;
            case ("E"):
                newTaskToLoad = new Event(tokenizedTaskString[2], tokenizedTaskString[3]);
                break;
            case ("D"):
                newTaskToLoad = new Deadline(tokenizedTaskString[2], tokenizedTaskString[3]);
                break;
            default:
                newTaskToLoad = new Todo("hello", "world");
                //break;
            }
            //if task was previously marked done already, make sure to mark it as done when loading to taskList
            if (tokenizedTaskString[1].equals("1")) {
                newTaskToLoad.markAsDone();
            }
            taskList.add(newTaskToLoad);
            taskCount++;

        }
    }

    //TODO: write loop to iterate through arraylist, write each task to file data/duke.txt on any changes
    private static void saveTaskListToFile(String filePath, ArrayList<Task> taskList) throws IOException {
        FileWriter fw;
        try{
            fw = new FileWriter(filePath); //overwrites existing file contents every time it is called
        } catch (IOException e) {
            throw new IOException();
        }
        //convert newTaskData to string format for storing in duke.txt
        String newTaskString;
        boolean hasDescription = false;

        for (int i=0; i < taskList.size(); i++) {
            Task newTaskData = taskList.get(i);
            if (newTaskData instanceof Todo) {
                newTaskString = newTaskData.getTaskData()[0] + " | " + newTaskData.getTaskData()[1] + " | "
                        + newTaskData.getTaskData()[2] + System.lineSeparator();
            } else {
                newTaskString = newTaskData.getTaskData()[0] + " | " + newTaskData.getTaskData()[1] + " | "
                        + newTaskData.getTaskData()[2] + " | " + newTaskData.getTaskData()[3] + System.lineSeparator();
            }
            fw.write(newTaskString);

        }
        fw.close();

    }

    public static void main(String[] args) {
        String userInput;
        Scanner in = new Scanner(System.in);
        boolean isExitCommandInvoked = false;
        ArrayList<Task> taskList = new ArrayList<>();
        
        //load duke.txt on startup. If duke.txt does not exist create duke.txt in relative path
        try {
            loadFileToTaskList("src/main/java/data/duke.txt", taskList);
        } catch (FileNotFoundException e) {
            File f = new File("src/main/java/data/duke.txt");
        }

        sayIntro();
        //easier to identify lines input by user (per Python)
        System.out.print(">>>");

        while (in.hasNextLine()) {
            userInput = in.nextLine();
            try {
                String testBlankInput = userInput.split(" ")[0];
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println(underscoredLine);
                System.out.println("\t\u2639 !!ERROR!! Command cannot be whitespaces.");
                System.out.println(underscoredLine);
                System.out.print(">>>");
                continue;
            }
            String[] tokenizedInput = userInput.split(" ");
            switch (tokenizedInput[0].toLowerCase()) {
            case ("bye"):
                isExitCommandInvoked = true;
                break;
            case ("list"):
                printTaskList(taskList);
                break;
            case("done"):
                updateTaskDone(tokenizedInput[1], taskList);
                try {
                    saveTaskListToFile("src/main/java/data/duke.txt", taskList);
                } catch (IOException e) {
                    System.out.println(underscoredLine);
                    System.out.println("\tError saving taskList to duke.txt");
                    System.out.println(underscoredLine);
                }
                break;
            case("remove"):
                //TODO: put remove under try-catch block, NumberFieldException
                try {
                    removeTask(userInput, taskList);
                    saveTaskListToFile("src/main/java/data/duke.txt", taskList);
                } catch (NumberFieldException e) {
                    System.out.println(underscoredLine);
                    System.out.println("\t\u2639 !!ERROR!! The task number you have provided is not valid.");
                    System.out.println(underscoredLine);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println(underscoredLine);
                    System.out.println("\t\u2639 !!ERROR!! The remove command is missing additional parameters.");
                    System.out.println(underscoredLine);
                } catch (IOException e) {
                    System.out.println(underscoredLine);
                    System.out.println("\tError saving taskList to duke.txt");
                    System.out.println(underscoredLine);
                }
                break;
            default:
                try{
                    insertNewTask(taskList, userInput, tokenizedInput);
                    saveTaskListToFile("src/main/java/data/duke.txt", taskList);
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
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println(underscoredLine);
                    System.out.println("\t\u2639 !!ERROR!! " + tokenizedInput[0] + " command is missing additional parameters.");
                    System.out.println(underscoredLine);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


            if (isExitCommandInvoked) {
                break;
            } else {
                System.out.print(">>>");
            }
        }

        sayGoodbye();
    }
    
}
