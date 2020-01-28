package src.main.java;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("----------------------------------------------");
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        System.out.println("----------------------------------------------");
        String userInput;
        ArrayList<Task> toDo = new ArrayList<>();
        do {
            Scanner in = new Scanner(System.in);
            userInput = in.nextLine();
            if (userInput.equalsIgnoreCase("list")){        //user checks list
                System.out.println("----------------------------------------------");
                System.out.println("Here are the tasks in your list");
                for (int i=0; i<toDo.size(); i++){
                    System.out.println(i+1 + ".[" + toDo.get(i).getStatusIcon() + "] " + toDo.get(i).toString());
                }
                System.out.println("----------------------------------------------");

            }else if (userInput.startsWith("done")){
                int indexCompleted=Integer.parseInt(userInput.substring(5));
                if (indexCompleted == 0){
                    System.out.println("Error, please enter a valid integer");
                    System.out.println("----------------------------------------------");
                    continue;
                }
                toDo.get(indexCompleted-1).markAsDone();
                System.out.println("----------------------------------------------");
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("[" + toDo.get(indexCompleted-1).getStatusIcon() + "] " + toDo.get(indexCompleted-1).toString());
                System.out.println("----------------------------------------------");
            }else {                                                     //user adds into list
//                toDo.add(userInput);
                Task t = new Task(userInput);
                toDo.add(t);
                System.out.println("----------------------------------------------");
                System.out.println("added: " + userInput);
                System.out.println("----------------------------------------------");
            }
        } while (!userInput.equalsIgnoreCase("bye"));

        System.out.println("----------------------------------------------");
        System.out.println("Bye. I hope to see u again soon ^^");
        System.out.println("----------------------------------------------");
    }
}
