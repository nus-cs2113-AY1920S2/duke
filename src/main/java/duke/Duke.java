package duke;

import java.util.Scanner;
import java.util.ArrayList;



public class Duke {
    /**
     * Keeps track of Class variable taskList.
     */
    private ArrayList<Task> taskList;

    /**
     * Constructor
     */
    public Duke() {
        this.taskList = new ArrayList<Task>();
    }

    Scanner scanner = new Scanner(System.in);

    public void printStraightLine(){
        System.out.println("___________________________________________________________________________\n");
    }

    /**
     * Generates Logo.
     */
    public void printLogo(){
        for (int i = 0; i < 5; i++){
            String addOne = "*";
            String addTwo = "     *";
            String addThree = "*";
            String addFour = "     *";
            String addFive = "*";
            String addSix = "     *";
            for (int j = 0; j < i; j++){
                addOne += "*";
                addTwo += "*";
                addThree += "*";
                addFour += "*";
                addFive += "*";
                addSix += "*";
            }
            System.out.println(addOne + addTwo + addThree + addFour + addFive + addSix +
                                    addOne + addTwo + addThree + addFour + addFive + addSix +
                                        addOne + addTwo + addThree + addFour + addFive + addSix +
                                            addOne + addTwo + addThree + addFour + addFive + addSix);
        }
    }

    /**
     * Print all available tasks.
     * @param taskList Tasklist of all available tasks
     */
    public void printTaskList(ArrayList<Task> taskList){
        int taskCounter = 1;
        for (Task task : taskList){
            String description = task.getDescription();
            String statusIcon = task.getStatusIcon();
            String typeIcon = task.getTypeIcon();
            System.out.println(taskCounter + ". "  + typeIcon +  " [" +  statusIcon + "] " +  description);
            taskCounter+= 1;
        }
    }


    /**
     * Returns true if taskType is Delete. If it is indeed delete, then it also calls another method to delete
     * @param ogString Original String that is inputted by the user into the command line
     * @param taskList Tasklist of all available tasks
     * @return isTaskTypeDelete Returns true if the type of the task is delete
     */
    public Boolean isTaskTypeDelete(String ogString, ArrayList<Task> taskList) {
        String[] words = ogString.split(" ");
        Boolean isTaskTypeDelete = false;
        String taskType = words[0].toUpperCase();
        if (taskType.equals("DELETE")) {
            try {
                int taskNumber = Integer.parseInt(words[1]) - 1;
                if (taskNumber < 0 || taskNumber > taskList.size() -1) {
                    System.out.println("That task number isn't in our task list or your input is not in the" +
                            " correct format (eg: delete 1), please try again!");
                    return true;
                } else {
                    deleteTask(taskList, taskNumber);
                    isTaskTypeDelete = true;
                }
            } catch (IndexOutOfBoundsException e){
                System.out.println("Please also specify task number, i.e. delete 3");
                return true;
            }
        }
        return isTaskTypeDelete;
    }

    /**
     * Deletes the task at index taskNumber from the tasklist
     * @param taskList Tasklist of all available tasks
     * @param taskNumber The index of the task we are trying delete
     */
    public void deleteTask(ArrayList<Task> taskList, int taskNumber) {
        Task task = taskList.get(taskNumber);
        String description = task.getDescription();
        String statusIcon = task.getStatusIcon();
        String typeIcon = task.getTypeIcon();
        System.out.println("Cool, we will remove the following task:");
        System.out.println(typeIcon +  " [" +  statusIcon + "] " +  description);
        taskList.remove(taskNumber);
        System.out.println("Now you have " + Integer.toString(taskList.size()) + " items in your list" );
    }


    /**
     * Checks if the given task is valid
     * @param type Type of task
     * @return Checks if the task type is valid
      */
    public Boolean checkIfValidTask(String type){
        String DONE = "done";
        String DEADLINE = "deadline";
        String TODO = "todo";
        String EVENT = "event";
        String LISTE = "list";
        String DELETE = "delete";
        if (type.equals(DONE) || type.equals(TODO) || type.equals(DEADLINE)
                || type.equals(EVENT) || type.equals(LISTE) || type.equals(DELETE) ){
             return true;
        }
        return false;
    }


    /**
     * Returns a boolean if a given task is marked as done
     *
     * @param line  Line that represents the task that is supposed to marked as done.
     * @param taskList TaskList of all available tasks.
     * @return isDone Is the task marked as Done.
     */
    public Boolean isTaskTypeDone(String line, ArrayList<Task> taskList) {
        String[] words = line.split(" ");
        int LENG_LIST = words.length;
        Boolean isDone = false;
        String taskType = words[0].toUpperCase();

        if (taskType.equals("DONE")){
            if (LENG_LIST == 1) {
                    System.out.println("Please specify (eg: done 2) or just add a new one");
                    isDone = true;
                    return isDone;
                } else {
                         int IDX_WORDS = Integer.parseInt(words[1]) - 1;
                         if (IDX_WORDS < 0 || IDX_WORDS > taskList.size() - 1) {
                             System.out.println("That task number isn't in our task list, please try again!");
                             return true;
                    } else {
                             markTaskAsDone(taskList, words);
                             isDone = true;
                             return isDone;
                         }
                }
        }
        System.out.println("here");
        return isDone;
    }
    /**
     * Marks a task as done
     * @param taskList TaskList containg all tasks
     * @param words Inputted command by the user that is turned into a string array
     */
    public void markTaskAsDone(ArrayList<Task> taskList, String[] words ) {
            taskList.get(Integer.parseInt(words[1]) - 1).markIt();
            String statusIcon = taskList.get(Integer.parseInt(words[1]) - 1).getStatusIcon();
            String typeIcon = taskList.get(Integer.parseInt(words[1]) - 1).getTypeIcon();
            String description = taskList.get(Integer.parseInt(words[1]) - 1).getDescription();
            System.out.println((Integer.parseInt(words[1])) + ". "+ typeIcon +  "[" + statusIcon + "]" + " " + description);
            System.out.println("Done! We have checked " + words[1] + "!");
            }


    /**
     * Returns the description of the task in the required format
     * @param line Line that represents the task that is supposed to marked as done.
     * @param type  Type of subclass of task.
     * @return description Description of the task in the required format.
     */
    public String returnStringToAdd(String line, String type) {
        if (type.equals("todo") ){
            String[] arrOfStr = line.split(type);
            String toReturn= arrOfStr[1].trim();
            return toReturn;
        } else {
            String preposition;
            String splitter;
            if (type.equals("deadline")){
                preposition = "by: ";
                splitter = "/by";
            }
            else {
                preposition = "at: ";
                splitter = "/at";
            }
            String[] arrOfStr = line.split(splitter);
            String[] toReturn = new String[2];
            String[] todo = arrOfStr[0].split(type);
            toReturn[0] = (todo[1].substring(1, todo[1].length()));       // task

            toReturn[1] = arrOfStr[1].substring(1, arrOfStr[1].length()); // date
            String description = toReturn[0] + "(" + preposition + toReturn[1] + ")";
            return description;
        }
    }

    /**
     * Reads what type of task if given and calls the required method accordingly.
     * @param line Line that represents the task that is supposed to marked as done.
     * @param taskList Tasklist of all available tasks.
     */
    public void readCommands(String line, ArrayList<Task> taskList) {
        String ogString = line;
        while (!ogString.toUpperCase().equals("BYE")) {
            String[] words = ogString.split(" ");
            String eventType = words[0];

            if (!checkIfValidTask(eventType)) {
                System.out.println("Please specify the task type more clearly (eg: done, todo, deadline, list, event)");
            }
            else {
                performTasks(taskList, ogString, eventType);
            }
//            Scanner scanner = new Scanner(System.in);
            ogString = scanner.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!\n");
        printStraightLine();
    }

    /**
     * Performs the requested task
     * @param taskList TaskList of all available tasks.
     * @param ogString Original string that is inputted by the user using the command line.
     * @param eventType event Type, meaning the nature of the command (eg. LIST, DEADLINE).
     */
    public void performTasks(ArrayList<Task> taskList, String ogString, String eventType) {
        if (isTaskTypeDone(ogString, taskList)){
            printStraightLine();
        } else if (ogString.toUpperCase().equals("LIST")) {
            printStraightLine();
            this.printTaskList(taskList);
            printStraightLine();
        } else if (isTaskTypeDelete(ogString, taskList)){
            printStraightLine();
        } else {
            try {
                printStraightLine();
                String todoOrDeadlineOrEvent = returnStringToAdd(ogString, eventType);
                if (eventType.equals("event") || eventType.equals("deadline")) {
                    if (eventType.equals("event")) {
                        Event t = new Event(todoOrDeadlineOrEvent);
                        taskList.add(t);
                    } else {
                        Deadline t = new Deadline(todoOrDeadlineOrEvent);
                        taskList.add(t);
                    }
                } else {
                    Todo e = new Todo(todoOrDeadlineOrEvent);
                    taskList.add(e);
                }
                Task t = taskList.get(taskList.size() - 1);
                String description = t.getDescription();
                String statusIcon = t.getStatusIcon();
                String typeIcon = t.getTypeIcon();
                System.out.println("Got it. I have added this task: \n");
                System.out.println(typeIcon +  " [" +  statusIcon + "] " +  description + "\n");
                System.out.println("Now you have " + taskList.size() +  " item/s in the list \n");
                printStraightLine();
            } catch (IndexOutOfBoundsException e){
                System.out.println("Your inputs can only be of the following forms: \n 1. todo {task description} \n 2." +
                                        " deadline {task description} \\by {dedline eg. 6 PM} \n 3. event {event description} " +
                                            "\\at {event date\\time eg. 6 PM}");
                System.out.println("Try again!");
                printStraightLine();
            }
        }
    }


    /**
     * Gets the program statted by adding an initial task or taking the initial command
     */
    public void startThingsOff(){
        printLogo();
        String line;
        printStraightLine();
        System.out.println("Hello! I'm Hiroshi");
        System.out.println("Lets plan your day buddy! Things are looking good.\n");
        printStraightLine();
        line = scanner.nextLine();
        readCommands(line, taskList);
        }


    public static void main(String[] args) {
        Duke hiroshiNagai = new Duke();
        hiroshiNagai.startThingsOff();
    }

}
