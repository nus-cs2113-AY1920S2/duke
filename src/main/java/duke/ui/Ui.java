package duke.ui;

import duke.commands.Command;
import duke.commands.TaskList;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    public static void showWelcome(){
        System.out.println("    ____________________________________________________________\n"
                + "     Hello! I'm Duke\n"
                + "     What can I do for you?\n"
                + "    ____________________________________________________________\n");
    }

    public static String readInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static void showNonExistentInputError() {
        System.out.println("    ____________________________________________________________\n" +
                "     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" +
                "    ____________________________________________________________");
    }

    public static void showInvalidFindError() {
        System.out.println("    ____________________________________________________________\n" +
                "     There is no such item in the list, try again!\n" +
                "    ____________________________________________________________");

    }

    public void showNonExistentTaskInList(){
        System.out.println("    ____________________________________________________________\n"
                + "     Task does not exist!\n"
                + "    ____________________________________________________________");
    }

    public void showLoadingError() {
        System.out.println("    ____________________________________________________________\n" +
                "     Looks like there is no past records, let's make a new one!\n" +
                "    ____________________________________________________________");
    }

    public static void showEmptyParametersError(String field){
        System.out.println("    ____________________________________________________________\n"
                + "     ☹ OOPS!!! The description of " + field + " cannot be empty.\n"
                + "    ____________________________________________________________\n");
    }

    public static void showIncompleteParametersError(String parameters){
        System.out.println("    ____________________________________________________________\n"
                + "     ☹ OOPS!!! You forgot to add \"" + parameters + " [insert time]\"\n"
                + "    ____________________________________________________________\n");
    }

    public void showListIncrementOutput(String m,int size){
        System.out.println("    ____________________________________________________________\n"
                + "     Got it. I've added this task:\n       "
                + m
                + "\n     Now you have " + size + " tasks in the list."
                + "\n    ____________________________________________________________");
    }

    public void showFindOutput(String target) {
        int printCheck = 0;
        for (int i = 0; i < TaskList.list.size(); i++){
            if (TaskList.list.get(i).command.contains(target)) {
                if (printCheck == 0) {
                    System.out.println("    ____________________________________________________________\n"
                            + "     Here are the matching tasks in your list:");
                    printCheck++;
                }
                System.out.println("     "+ (i+1) + "." + TaskList.list.get(i).command);
            }
        }
        if (printCheck == 1) {
            System.out.println("    ____________________________________________________________");
        } else {
            showInvalidFindError();
        }
    }

    public void showDeleteOutput(String m, int size){
        System.out.println("    ____________________________________________________________\n"
                + "     Noted. I've removed this task: \n" + "       " + m
                + "\n     Now you have " + size + (size>1?" tasks":" task") + " in the list.\n"
                + "    ____________________________________________________________");
    }

    public void showDoneOutput(String m) {
        System.out.println("    ____________________________________________________________\n"
                + "     Nice! I've marked this task as done:\n       " + m
                + "\n    ____________________________________________________________");
    }


    public void showBye() {
        System.out.println("    ____________________________________________________________\n"
                + "     Bye. Hope to see you again soon!\n"
                + "    ____________________________________________________________");
    }

    public void showList(ArrayList<Command> list) {
        try {
        System.out.println("    ____________________________________________________________\n"
                + "     Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println("     " + (i + 1) + "." + list.get(i).command);
        }
        System.out.print("    ____________________________________________________________\n");
        } catch (NullPointerException e) {
            System.out.println("    ____________________________________________________________\n"
                    + "     List is empty!\n"
                    + "    ____________________________________________________________");
        }
    }
}
