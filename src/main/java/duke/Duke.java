package duke;

import duke.Deadline;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    public Duke(String filePath, Scanner input) {
        this.ui = new Ui(input);
        this.storage = new Storage(filePath);
        try {
            this.tasks = storage.readFromFile(filePath);
        } catch (FileNotFoundException e){
            ui.showLoadingError();
            this.tasks = new TaskList();
        }
    }
    public void run() {
        ui.showWelcomeMessage();
        while (true) {
            String command = ui.getUserCommand();
            Parser.parseCommand(command, this.tasks);
        }
     }
    public static void main(String[] args) throws IOException {



        Scanner input = new Scanner(System.in);
        new Duke("./duke.txt",input).run();

//        boolean ifError = false;
//        while (true){
//            String next = input.nextLine();
//            if (next.equals("bye")){
//                Storage.writeToFile("./duke.txt",tasks);
//                counter = counter -1;
//                System.out.println("Bye. Hope to see you again soon! :)");
//                break;
//            } else if (next.equals("list")) {
//                System.out.println("Here are the tasks on your list:");
//                for (int i = 0; i < counter; i++) {
//                System.out.printf("%d. %s\n",i +1,tasks.get(i).toString());
//                }
//            } else if (next.startsWith("done")){
//                String temp = next.replaceAll("\\D+","");
//                int FinishedNumber = Integer.parseInt(temp);
//                tasks.get(FinishedNumber-1).markAsDone();
//                System.out.println("Nice! I've marked this task as done:");
//                System.out.printf("  [%s] %s\n",tasks.get(FinishedNumber-1).getStatusIcon(),tasks.get(FinishedNumber-1).getDescription());
//            } else {
//
//                String[] splitString = next.split(" ", 2);
//                if (splitString[0].equals("deadline")){
//                    if (splitString.length == 1) {
//                        System.out.println("The description of deadline cannot be empty");
//                        ifError = true;
//                    } else{
//                        String[] splitString2 = splitString[1].split("/by", 2);
//                        if (splitString2.length == 1) {
//                            System.out.println("duke.Deadline requires to be separated by a '/by' statement");
//                            ifError = true;
//                        } else{
//                            gotItMessage();
//                            tasks.add(counter,new Deadline(splitString2[0],splitString2[1]));
//                            System.out.printf("  %s\n",tasks.get(counter).toString());
//                        }
//
//                    }
//
//                } else if (splitString[0].equals("todo")) {
//                    if (splitString.length == 1) {
//                        System.out.println("OOPS!!! The description of todo cannot be empty");
//                        ifError = true;
//                    } else{
//                        gotItMessage();
//                        tasks.add(counter,new Todo(splitString[1]));
//                        System.out.printf("  %s\n",tasks.get(counter).toString());
//                    }
//
//                } else if (splitString[0].equals("event")) {
//                    if (splitString.length == 1) {
//                        System.out.println("OOPS!!! The description of event cannot be empty");
//                        ifError = true;
//                    } else {
//                        String[] splitString2 = splitString[1].split("/at", 2);
//                        if (splitString2.length == 1) {
//                            System.out.println("duke.Event is required to be separated by a '/at' statement");
//                            ifError = true;
//                        } else {
//                            gotItMessage();
//                            tasks.add(counter,new Event(splitString2[0], splitString2[1]));
//                            System.out.printf("  %s\n",tasks.get(counter).toString());
//                        }
//
//                    }
//
//
//                } else if (splitString[0].equals("delete")){
//                    if (splitString.length == 1) {
//                        System.out.println("OOPS!!! The description of event cannot be empty");
//                        ifError = true;
//                    } else {
//                        System.out.println("Noted: I've removed this task:");
//                        System.out.printf("  %s\n",tasks.get(Integer.parseInt(splitString[1]) - 1).toString());
//                        tasks.remove(Integer.parseInt(splitString[1]) - 1);
//
//                        counter = counter - 2;
//                    }
//                } else if (splitString[0].equals("save")){
//                    Storage.writeToFile("./duke.txt",tasks);
//                    counter = counter -1;
//
//                }
//                else{
//                    System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
//                    ifError = true;
////                    tasks[counter] = new duke.Task(next);
////                    System.out.println("added: "+ next);
//                }
//                if (ifError == false) {
//
//                    counter +=1;
//                    System.out.printf("Now you have %d tasks in the list\n", counter);
//                }
//                ifError = false;
//
//            }
//            }
        }

    private static void gotItMessage() {
        System.out.println("Got it. I've added this task:");
    }
}

