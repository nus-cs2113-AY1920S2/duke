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
        ArrayList<String> toDo = new ArrayList<>();
        do {
            Scanner in = new Scanner(System.in);
            userInput = in.nextLine();
            if (userInput.equalsIgnoreCase("list")){
                System.out.println("----------------------------------------------");
                for (int i=0; i<toDo.size(); i++){
                    System.out.println(i+1 + ". " + toDo.get(i));
                }
                System.out.println("----------------------------------------------");
            }else {
                toDo.add(userInput);
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
