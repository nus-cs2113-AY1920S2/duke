package duke.Util;

import java.util.Scanner;

public class UI {
    private static Scanner userInput = new Scanner(System.in);
    private final String SPLIT = "==============================\n";

    public UI(){
    }

    public void printRespondToAddTask(String task) {
        System.out.println("    You have successfully added " + task + "!");
        System.out.println("    You have "+ Tasklist.getSize() + " task(s) now in total");
    }

    public String getStringInput() {
        return userInput.nextLine();
    }
    public Integer getIntegerInput() {
        return userInput.nextInt();
    }

    public void getDoneTask() {
        System.out.println("    Please choose the task that you have completed " +
                "(select the no)");
        Tasklist.showList();
    }

    public void printIntro() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("    Hello from\n" + logo);
    }

    public void printExeType() {
        System.out.println("    ================================\n" +
                "    Hi what can I do for you? (please key in the number):" +
                "\n    1. Add a new task, \n" +
                "    2. Show my tasks,\n"+
                "    3. I've completed my task!\n" +
                "    4. Delete a task,  or \n" +
                "    5. Find a task,  or \n" +
                "    6. See you next time! \n" +
                "    to end this conversation \n" +
                "    ================================\n");
    }

    public void printTaskType() {
        System.out.println("    Which category does your task belong to? \n"+
                "   1. ToDos: tasks without any date/time attached to it " +
                "(e.g., visit new theme park)\n" +
                "   2. Deadlines: tasks that need to be done before a " +
                "specific date/time" +
                " (e.g., submit report by 11/10/2019 5pm)\n" +
                "   3. Events: tasks that start at a specific time and ends at " +
                "a specific time" +
                " (e.g., team project meeting on 2/10/2019 2-4pm)") ;
    }

    public void printExit() {
        System.out.println("    ================================\n" +
                "    Bye. Hope to see you again soon!\n" +
                "    ================================" );
    }

    public void clearInput(){
        userInput.nextLine();
    }

}
