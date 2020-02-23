import data.*;

public class Parser {

    public static final int MAX_SUBSTRING_FIELDS = 2;
    private static String underscoredLine = "\t____________________________________________________________";
    public boolean isExitCommandInvoked = false;
    private TaskList taskList;

    public Parser(TaskList taskListInput) {
        this.taskList = taskListInput;
    }

    //command to validate task
    public void processCommand(String userInput) {
        String[] tokenizedInput = userInput.split(" ");
        switch (tokenizedInput[0].toLowerCase()) {
        case ("bye"):
            isExitCommandInvoked = true;
            break;
        case ("list"):
            printTaskList(taskList);
            break;
        case ("done"):
            updateTaskDone(tokenizedInput[1], taskList);
            break;
        case ("remove"):
            try {
                removeTask(userInput, taskList);
            } catch (NumberFieldException e) {
                System.out.println(underscoredLine);
                System.out.println("\t\u2639 !!ERROR!! The task number you have provided is not valid.");
                System.out.println(underscoredLine);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println(underscoredLine);
                System.out.println("\t\u2639 !!ERROR!! The remove command is missing additional parameters.");
                System.out.println(underscoredLine);
            }
            break;
        default:
            try {
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
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println(underscoredLine);
                System.out.println("\t\u2639 !!ERROR!! " + tokenizedInput[0] + " command is missing additional parameters.");
                System.out.println(underscoredLine);
            }
        }
    }

    public static String[] splitUserInput(String originalInput) throws ArrayIndexOutOfBoundsException,
            NoDescriptionException, NoRemarkException, IllegalKeywordException {
        String[] returnValue = new String[MAX_SUBSTRING_FIELDS];
        if (originalInput.contains(" /")){
            String[] separatedSections = originalInput.split(" /");
            String commandWord = separatedSections[0].split(" ", 2)[0];

            //todo should not have a remark section
            if (commandWord.toLowerCase().equals("todo")) {
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
            boolean isRemarksEmpty = ((commandWord.toLowerCase().equals("event") || commandWord.toLowerCase().equals("deadline"))
                    && returnValue[1].trim().length() == 0);
            if (isRemarksEmpty){
                throw new NoRemarkException();
            }
            return returnValue;
        } else {
            // get description part of userInput without the command word
            String[] separatedSections = originalInput.split(" /");
            String commandWord = separatedSections[0].split(" ", 2)[0];
            //similar to above, event and deadline should not be missing a description section
            if (commandWord.toLowerCase().equals("event") || commandWord.toLowerCase().equals("deadline")) {
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


    public static void printTaskList(TaskList listInput) {
        //if list empty, inform user and await next command
        if (listInput.getTaskCount() == 0) {
            System.out.println(underscoredLine + System.lineSeparator() + "\tThe list is empty."
                    + System.lineSeparator() + underscoredLine);
            return;
        }
        //if list non-empty, print out all existing tasks
        System.out.println(underscoredLine + System.lineSeparator());
        for (int i = 0; i < listInput.getTaskCount(); i++) {
            System.out.println("\t" + Integer.toString(i + 1) + "." + listInput.getTaskList().get(i).toString());
        }
        System.out.println(underscoredLine);
        return;
    }

    public static void updateTaskDone(String taskNumberInput, TaskList listInput){
        int queryNumber = Integer.parseInt(taskNumberInput);
        boolean isOutOfRange = queryNumber < 1 || queryNumber > listInput.getTaskCount();

        //handle case where user inputs non-existing task number to mark as done
        if (isOutOfRange){
            System.out.println(underscoredLine + System.lineSeparator() + "\tInvalid task number."
                    + System.lineSeparator() + underscoredLine);
            return;
        }
        //handle case where user tries to mark as done an already completed task
        boolean isTaskAlreadyDone = listInput.getTaskDoneStatus(queryNumber-1);

        if (isTaskAlreadyDone) {
            System.out.println(underscoredLine + System.lineSeparator()
                    + "\tThis task has already been marked completed." + System.lineSeparator() + underscoredLine);
            return;
        }
        listInput.markTaskAsDone(queryNumber-1);

        String taskDoneMessage = "\tGreat job! I've marked this task as done:\n\t" + Integer.toString(queryNumber)
                + ".[" + listInput.getTaskStatusIcon(queryNumber-1) + "] " + listInput.getTaskDescription(queryNumber-1);
        System.out.println(underscoredLine + System.lineSeparator() + taskDoneMessage + System.lineSeparator()
                + underscoredLine);
    }

    private static void insertNewTask(TaskList taskList, String userInput, String[] tokenizedInput) throws
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

        taskList.addTask(newTask);

        String taskAddedMessage = "\tGot it. I've added this task: \n\t" + newTask.toString() + System.lineSeparator()
                + "\tNow you have " + taskList.getTaskCount() + " tasks in the list.\n";
        System.out.println(underscoredLine + System.lineSeparator() + taskAddedMessage + System.lineSeparator()
                + underscoredLine);
    }

    private static void removeTask(String userInput, TaskList taskList) throws NumberFieldException {
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
        boolean isOutOfBounds = (taskNumberForRemoval <= 0 || taskNumberForRemoval > taskList.getTaskCount());
        if (isOutOfBounds) {
            throw new NumberFieldException();
        }
        Task removedTask = taskList.removeTask(Integer.valueOf(taskNumberForRemoval)-1);

        String taskRemovedMessage = "\tGot it. I've removed this task: \n\t" + removedTask.toString() + System.lineSeparator()
                + "\tNow you have " + taskList.getTaskCount() + " tasks in the list.\n";
        System.out.println(underscoredLine + System.lineSeparator() + taskRemovedMessage + System.lineSeparator()
                + underscoredLine);
    }
}
