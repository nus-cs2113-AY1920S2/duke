package src.main.java;
import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static int todoSize=0;
    public static void printSeparator() {
        System.out.println("----------------------------------------------");
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);
        printSeparator();
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        printSeparator();
        String userInput;
        ArrayList<Task> toDoList = new ArrayList<>();
        do {
            Scanner in = new Scanner(System.in);
            userInput = in.nextLine();
            if (userInput.equalsIgnoreCase("list")){        //user checks list
                printSeparator();
                System.out.println("Here are the tasks in your list");
                for (int i=0; i<toDoList.size(); i++){
                    System.out.println(i+1 + ". " + toDoList.get(i).toString());
                }
                printSeparator();

            }else if (userInput.startsWith("done")){
                int indexCompleted=Integer.parseInt(userInput.substring(5));
                if (indexCompleted == 0){
                    System.out.println("Error, please enter a valid integer");
                    printSeparator();
                    continue;
                }
                toDoList.get(indexCompleted-1).markAsDone();
                printSeparator();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(toDoList.get(indexCompleted-1).toString());
                printSeparator();
            }else if(userInput.startsWith("todo")) {                                                     //user adds into list
                userInput = userInput.substring(5,userInput.length());
                ToDo t = new ToDo(userInput);
                toDoList.add(t);
                printSeparator();
                System.out.println("Okie dokes, \"" + userInput + "\" has been added to your to do list:");
                System.out.println(t.toString());
                System.out.println("Now there are " +  (todoSize+1) + " in your list");
                printSeparator();
                todoSize++;
            }else if (userInput.startsWith("deadline")){
                userInput = userInput.substring(9,userInput.length());
                Deadline d = new Deadline(userInput);
                toDoList.add(d);
                printSeparator();

                System.out.println("Okie dokes, \"" + userInput + "\" has been added to your to do list:");
                System.out.println(d.toString());
                System.out.println("Now there are " +  (todoSize+1) + " in your list");
                printSeparator();
                todoSize++;
            }else if (userInput.startsWith("event")){
                userInput = userInput.substring(6,userInput.length());
                Events e = new Events(userInput);
                toDoList.add(e);
                printSeparator();
                System.out.println("Okie dokes, \"" + userInput + "\" has been added to your to do list:");
                System.out.println(e.toString());
                System.out.println("Now there are " +  (todoSize+1) + " in your list");
                printSeparator();
                todoSize++;
            }
        } while (!userInput.equalsIgnoreCase("bye"));

        printSeparator();
        System.out.println("Bye. I hope to see u again soon ^^");
        printSeparator();
    }
}
