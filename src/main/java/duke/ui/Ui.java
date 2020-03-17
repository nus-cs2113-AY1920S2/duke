package duke.ui;

import duke.commands.Command;
import duke.commands.TaskList;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *  Ui class to handle CLI text displays for user interaction.
 */
public class Ui {
    public static void showWelcome() {
        System.out.println("    ____________________________________________________________\n"
                + "     Hello! I'm Duke, your personal task genie :D\n"
                + "     You can wield my powers through these magic words...\n\n"
                + "     list\n"
                + "     todo [insert item]\n"
                + "     event [insert item] /at [insert time]\n"
                + "     deadline [insert item] /by [insert time]\n"
                + "     done [insert item number]\n"
                + "     delete [insert item number]\n"
                + "     find [insert item name]\n"
                + "     bye\n"
                + "    ____________________________________________________________\n");
    }

    public static String readInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static void showNonExistentInputError() {
        System.out.println("    ____________________________________________________________\n" +
                "     OOPS! I'm sorry, but I don't know what that means :-(\n" +
                "    ____________________________________________________________");
    }

    public static void showInvalidFindError() {
        System.out.println("    ____________________________________________________________\n" +
                "     There is no such item in the list, try again!\n" +
                "    ____________________________________________________________");

    }

    public static void showAlreadyDone() {
        System.out.println("    ____________________________________________________________\n" +
                "     This task is already completed! Good job :D\n" +
                "    ____________________________________________________________");
    }

    public static void showNegativeError() {
        System.out.println("    ____________________________________________________________\n"
                + "     What does a negative item even mean...\n"
                + "    ____________________________________________________________");

    }

    public static void showTamperedFile() {
        System.out.println("    ____________________________________________________________\n" +
                "     Past records are corrupted, let's make a new one!\n" +
                "    ____________________________________________________________");
    }

    public void showLoadingError() {
        System.out.println("    ____________________________________________________________\n" +
                "     Looks like there is no past records, let's make a new one!\n" +
                "    ____________________________________________________________");
    }

    public static void showEmptyParametersError(String field) {
        System.out.println("    ____________________________________________________________\n"
                + "     OOPS! The description of " + field + " cannot be empty.\n"
                + "    ____________________________________________________________\n");
    }

    public static void showIncompleteParametersError(String parameters) {
        System.out.println("    ____________________________________________________________\n"
                + "     OOPS! You forgot to add \"" + parameters + " [insert time]\"\n"
                + "    ____________________________________________________________\n");
    }

    public void showListIncrementOutput(String m,int size) {
        System.out.println("    ____________________________________________________________\n"
                + "     Got it. I've added this task:\n       "
                + m
                + "\n     Now you have " + size + " tasks in the list."
                + "\n    ____________________________________________________________");
    }

    public void showFindOutput(String target) {
        int printCheck = 0;
        for (int i = 0; i < TaskList.list.size(); i++) {
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

    public void showDeleteOutput(String m, int size) {
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

    public void showDoneOutOfBound(int size) {
        System.out.println("    ____________________________________________________________\n"
                + "     Task does not exist.\n"
                + "     Your list starts from index 1 and ends at index " + size
                + "\n    ____________________________________________________________");
    }

    public void showErrorInput() {
        System.out.println("    ____________________________________________________________\n"
                + "     Please give me the item number instead :)\n"
                + "    ____________________________________________________________");
    }
}
