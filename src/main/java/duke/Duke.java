package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import duke.excpetions.EmptyDescriptionException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

public class Duke {

    private static Task[] tasks = new Task[100];;
    private static int taskCount = 0;

    public static void main(String[] args) {
        try {
            welcomeMessage();
            Scanner read=new Scanner(System.in);
            String command=read.nextLine();
            while(!command.equals("bye")){
                printDividingLine();
                executeCommand(command);
                command=read.nextLine();
            }
            writeFile();
            exitMessage();
        }catch (FileNotFoundException e){
            System.out.println("File can not be found!");
        }catch (IOException e){
            System.out.println("Something goes wrong.");
        }
    }

    private static void executeCommand(String command) {
        String commandType = commandDivider(command);
        try{
            switch(commandType) {
            case "list":
                listTasks();
                break;
            case "done":
                doneTask(command);
                break;
            case "todo":
                addToDo(command);
                break;
            case "deadline":
                addDeadline(command);
                break;
            case "event":
                addEvent(command);
                break;
            default:
                System.out.println(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            printDividingLine();
            System.out.println("Do you have any other commands? ");
        } catch (EmptyDescriptionException e){
            System.out.println("Please re-enter your command with a description.");
        }
    }

    private static void writeFile() throws IOException {
        FileWriter fw = new FileWriter("src/tasksList.txt");
        for (int i=0;i<100;i++){
            if (tasks[i]!=null){
                fw.write(tasks[i].toString());
                fw.write("\n");
            }
        }
        fw.close();
    }

    private static String commandDivider(String command){
        if (command.contains(" ")){
             return command.substring(0,command.indexOf(" "));
        }else{
            return command;
        }
    }

    private static void printNumOfTasks(){
        System.out.println("Now you have "+taskCount+" tasks in the list.");
    }

    private static void addEvent(String command) throws EmptyDescriptionException {
        if(command.indexOf(" ")==-1) {
            System.out.println("☹ OOPS!!! The description of an event cannot be empty.");
            throw new EmptyDescriptionException();
        }
        String description=command.substring(command.indexOf(" "),command.indexOf("/"));
        String period=command.substring(command.indexOf("/at")+4);
        tasks[taskCount]=new Event(description, period);
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks[taskCount]);
        taskCount++;
        printNumOfTasks();
    }

    private static void addDeadline(String command) throws EmptyDescriptionException {
        if(command.indexOf(" ")==-1) {
            System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
            throw new EmptyDescriptionException();
        }
        String description=command.substring(command.indexOf(" "),command.indexOf("/"));
        String by=command.substring(command.indexOf("/by")+4);
        tasks[taskCount]=new Deadline(description,by);
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks[taskCount]);
        taskCount++;
        printNumOfTasks();
    }

    private static void addToDo  (String command) throws EmptyDescriptionException {
        if(command.indexOf(" ")==-1) {
            System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
            throw new EmptyDescriptionException();
        }
        String description=command.substring(command.indexOf(" "));
        tasks[taskCount]=new ToDo(description);
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks[taskCount]);
        taskCount++;
        printNumOfTasks();
    }

    private static void doneTask(String command) {
        String[] splitCommand=command.split(" ");
        int taskIndex=Integer.parseInt(splitCommand[1])-1;
        if(taskIndex< taskCount) {
            tasks[taskIndex].markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("  ["+ tasks[taskIndex].getStatusIcon() + "] " + tasks[taskIndex].description);
        }else{
            System.out.println("There is no task No."+(taskIndex+1));
        }
    }

    private static void printDividingLine() {
        System.out.println("------------***------------");
    }

    private static void listTasks() {
        System.out.println("Here are the tasks in your list:");
        for(int i=0;i<100;i++){
            if(tasks[i]==null){
                break;
            }
            System.out.println((i+1)+". "+tasks[i]);
        }
    }

    private static void exitMessage() {
        printDividingLine();
        System.out.println("Bye. Hope to see you again soon!");
        printDividingLine();
    }

    private static void welcomeMessage() {
        printDividingLine();
        System.out.println("Hello! I'm Momo");
        System.out.println("What can I do for you?");
        printDividingLine();
    }
}
