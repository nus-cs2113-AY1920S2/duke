import exception.InexplicitTimeDescription;
import exception.UnknownCommandException;
import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;

import java.util.*;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        showWelcomeMessage(logo);
        Scanner in = new Scanner(System.in);
        String input = getAndProcessInput(in);
        Boolean inputNotValid = Boolean.TRUE;
        chooseOneMode(in, input, inputNotValid);
    }

    private static void chooseOneMode(Scanner in, String input, Boolean inputNotValid) {
        while(inputNotValid) {
            switch(input){
            case "1":
                echoMode(input,in);
                inputNotValid = Boolean.FALSE;
                break;
            case "2":
                commandMode(input,in);
                inputNotValid = Boolean.FALSE;
                break;
            default:
                System.out.println("Unknown mode, please try again.");
                input = getAndProcessInput(in);
                break;
            }
        }
    }

    private static void showWelcomeMessage(String logo) {
        System.out.println("Hello from\n" + logo);
        System.out.println("________________________________");
        System.out.println("Hello,I'm little pepper. Your personal sweetheart.");
        System.out.println("What can I do for you? You can choose two model:");
        System.out.println("1.echo mode\n2.command mode");
        System.out.println("________________________________");
    }

    private static void printTaskDoneInfo(Task cur_task) {
        System.out.println("    Congratulations! You have just finished this task:");
        System.out.println("    "+cur_task.showTaskInfo());
    }

    private static void printTaskRemovedInfo(Task cur_task){
        System.out.println("    Noted. I've removed this task:");
        System.out.println("    "+cur_task.showTaskInfo());
    }

    public static void sayBye(){
        System.out.println("    ________________________________");
        System.out.println("    Don't leave me alone! Please come back soon!");
        System.out.println("    ________________________________");
    }

    public static void echoMode(String input,Scanner in){
        input = getAndProcessInput(in);
        while(!(input.equals("bye"))){
            echo(input);
            input = getAndProcessInput(in);
        }
        sayBye();
    }

    public static void commandMode(String input,Scanner in){
        TaskList taskList = new TaskList();
        int taskNum = 0;
        input = getAndProcessInput(in);
        while(!input.equals("bye")){
            System.out.println("    ________________________________");
            parseInputCommand(input, taskList);
            System.out.println("    ________________________________");
            input = getAndProcessInput(in);
        }
        sayBye();
    }

    private static String getAndProcessInput(Scanner in) {
        String input;
        input = in.nextLine().trim().toLowerCase();
        return input;
    }

    private static void echo(String input) {
        System.out.println("    ________________________________");
        System.out.println("    "+input);
        System.out.println("    ________________________________");
    }

    private static void parseInputCommand(String input, TaskList taskList) {
        if(input.startsWith("done")){
            setTaskDone(input, taskList);
        }else if(input.startsWith("delete")){
            removeCertainTask(input, taskList);
        } else if(!input.equals("list")) {
            addNewTask(input, taskList);
        } else taskList.printTaskList();
    }

    private static void removeCertainTask(String input, TaskList taskList) {
        try{
            int taskIndex = getTaskIndex(input);
            Task cur_task = taskList.getOneTask(taskIndex-1);
            taskList.remove(taskIndex);
            printTaskRemovedInfo(cur_task);
            System.out.println("    There are totally "+Integer.toString(taskList.getLenOfList())+" tasks in the taskList");
        } catch (NumberFormatException e){
            System.out.println("    You have to point out which task to delete!!!");
        } catch (IndexOutOfBoundsException e){
            System.out.println("    Referred task doesn't exist!!!");
            System.out.println("    There are totally "+Integer.toString(taskList.getLenOfList())+" tasks in the taskList");
        }
    }

    private static void addNewTask(String input, TaskList taskList) {
        int dividePosition = input.indexOf(" ");
        try {
            String type = input.substring(0,dividePosition);
            String newTaskName = newSpecificTask(input, taskList, type);
            showFeedback(newTaskName,taskList);
        } catch(StringIndexOutOfBoundsException e){
            System.out.println("    Invalid input! Cannot find description for a task event");
            System.out.println("    Your input: "+input+".");
            System.out.println("    Please use ' ' to split a task type and its description");
        } catch (UnknownCommandException e) {
            System.out.println("    OOPS!!! I'm sorry, but I don't know what that means :-(");
        } catch (InexplicitTimeDescription e){
            System.out.println("    Invalid input!!! Please use '/' to split task name and its time description");
        }
    }

    private static void setTaskDone(String input, TaskList taskList) {
        try{
            int taskIndex = getTaskIndex(input);
            Task cur_task = taskList.getOneTask(taskIndex-1);
            cur_task.setTaskStatus(Task.DONE);
            printTaskDoneInfo(cur_task);
        } catch (NumberFormatException e){
            System.out.println("    You have to point out which task to mark as done!!!");
        } catch (IndexOutOfBoundsException e){
            System.out.println("    Reffered task doesn't exist!!!");
            System.out.println("    There are totally "+Integer.toString(taskList.getLenOfList())+" tasks in the taskList");
        }
    }

    private static void showFeedback(String newTaskName,TaskList taskList) {
        System.out.println("    added: "+newTaskName);
        System.out.println("    Now you have "+Integer.toString(taskList.getLenOfList())+" tasks in the list");
    }

    private static String newSpecificTask(String input, TaskList taskList, String type) throws UnknownCommandException,InexplicitTimeDescription{
        String newTaskName = "";
        switch (type){
        case "todo":
            newTaskName = getNewTodoName(input);
            taskList.append(new ToDo(newTaskName));
            break;
        case "deadline":
            try{
                newTaskName = getDdlOrEventName(input);
            } catch (InexplicitTimeDescription e){
                throw e;
            }
            String by = getByOrDate(input);
            taskList.append(new Deadline(newTaskName,by));
            break;
        case "event":
            try{
                newTaskName = getDdlOrEventName(input);
            } catch (InexplicitTimeDescription e){
                throw e;
            }
            String date = getByOrDate(input);
            taskList.append(new Event(newTaskName,date));
            break;
        default:
            throw new UnknownCommandException();
        }
        return newTaskName;
    }

    private static String getNewTodoName(String input){
        input = input.replace("todo ","");
        return input;
    }

    private static String getDdlOrEventName(String input) throws InexplicitTimeDescription{
        String newName = input.replace("deadline ","").replace("event ","");
        try{
            int cutPosition = newName.indexOf("/");
            newName = newName.substring(0,cutPosition-1);
        } catch (StringIndexOutOfBoundsException e){
            throw new InexplicitTimeDescription();
        }
        return newName;
    }


    private static String getByOrDate(String input){
        int byBeginPosition = input.indexOf("/");
        return input.substring(byBeginPosition+4);
    }


    private static int getTaskIndex(String input) throws NumberFormatException{
        int dividePosition = input.indexOf(" ");
        try{
            return Integer.parseInt(input.substring(dividePosition+1));
        } catch(NumberFormatException e){
            throw e;
        }
    }
}
