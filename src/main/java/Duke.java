import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.ArrayList;

import exceptions.DukeException;
import tasks.Task;
import tasks.Deadline;
import tasks.Event;
import tasks.TaskType;

// TODO: Extract existing error handling to exceptions.DukeException where appropriate
// TODO: Assess if there is a better way to implement task adding i.e. with splitString() or otherwise
// TODO: Check for further modularisation
// TODO: A general add to arrayList method? reduce LOC

public class Duke {
    private static ArrayList<Task> taskArrList = new ArrayList<>();

    /** Main method Start **/
    public static void main(String[] args) throws DukeException {
        Scanner sc = new Scanner(System.in);
        boolean continueRun = true;
        String userCmd = "";
        Ui.printGreeting();

        try {
            taskArrList = Storage.loadDuke(taskArrList);

            while (continueRun) {
                System.out.println("==========================");
                System.out.println("How can I help you?");
                userCmd = sc.nextLine();

                //immediate exit if userCmd has 'bye'
                taskArrList = Parser.runParser(userCmd, taskArrList);
            }
        }
        catch (DukeException e){
            System.out.println(e +"\nPlease try again");
        }
    }
}

