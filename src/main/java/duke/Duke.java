package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import duke.excpetions.EmptyDescriptionException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

public class Duke {

    private static ArrayList<Task> tasks = new ArrayList<Task>();

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
        for (int i=0;i<tasks.size();i++){
            if (tasks.get(i)!=null){
                fw.write(tasks.get(i).toString());
                fw.write("\n");
            }
        }
        fw.close();
    }

    private static void deleteTask(String command) throws EmptyDescriptionException{
        if(command.indexOf(" ")==-1) {
            System.out.println("☹ OOPS!!! Please specify the index of the task you want to delete.");
            throw new EmptyDescriptionException();
        }
        int index = Integer.parseInt(command.split(" ")[1])-1;
        System.out.println("Noted. I've removed this task: ");
        System.out.println(tasks.remove(index));
        printNumOfTasks();
    }

    private static String commandDivider(String command){
        if (command.contains(" ")){
             return command.substring(0,command.indexOf(" "));
        }else{
            return command;
        }
    }

    private static void printNumOfTasks(){
        System.out.println("Now you have "+tasks.size()+" tasks in the list.");
    }

    private static void addEvent(String command) throws EmptyDescriptionException {
        if(command.indexOf(" ")==-1) {
            System.out.println("☹ OOPS!!! The description of an event cannot be empty.");
            throw new EmptyDescriptionException();
        }
        String description=command.substring(command.indexOf(" "),command.indexOf("/"));
        String period=command.substring(command.indexOf("/at")+4);
        Event newEvent = new Event(description, period);
        tasks.add(newEvent);
        System.out.println("Got it. I've added this task:");
        System.out.println(newEvent);
        printNumOfTasks();
    }

    private static void addDeadline(String command) throws EmptyDescriptionException {
        if(command.indexOf(" ")==-1) {
            System.out.println("☹ OOPS!!! The description of a deadline cannot be empty.");
            throw new EmptyDescriptionException();
        }
        String description=command.substring(command.indexOf(" "),command.indexOf("/"));
        String by=command.substring(command.indexOf("/by")+4);
        Deadline newDeadline = new Deadline(description,by);
        tasks.add(newDeadline);
        System.out.println("Got it. I've added this task:");
        System.out.println(newDeadline);
        printNumOfTasks();
    }

    private static void addToDo  (String command) throws EmptyDescriptionException {
        if(command.indexOf(" ")==-1) {
            System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
            throw new EmptyDescriptionException();
        }
        String description=command.substring(command.indexOf(" "));
        ToDo newTodo = new ToDo(description);
        tasks.add(newTodo);
        System.out.println("Got it. I've added this task:");
        System.out.println(newTodo);
        printNumOfTasks();
    }

    private static void doneTask(String command) {
        String[] splitCommand=command.split(" ");
        int taskIndex=Integer.parseInt(splitCommand[1])-1;
        if(taskIndex< tasks.size()) {
            tasks.get(taskIndex).markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("  ["+ tasks.get(taskIndex).getStatusIcon() + "] " + tasks.get(taskIndex).description);
        }else{
            System.out.println("There is no task No."+(taskIndex+1));
        }
    }

    private static void printDividingLine() {
        System.out.println("------------***------------");
    }

    private static void listTasks() {
        System.out.println("Here are the tasks in your list:");
        for(int i=0;i<tasks.size();i++){
            if(tasks.get(i)==null){
                break;
            }
            System.out.println((i+1)+". "+tasks.get(i));
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
