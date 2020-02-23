package duke.format;

import duke.data.TaskList;
import duke.task.Task;

import static duke.format.TextFormatter.*;
import static java.lang.System.lineSeparator;

import java.util.ArrayList;

public class Printer {

    public static void printWelcomeMessage() {
        String logo =
            "  __       _______  _______  ________  _______  _______  _______  ________" + lineSeparator() +
            " |\\_\\     |\\___\\__\\|\\ __\\__\\|\\ ______\\|\\______\\|\\___\\__\\|\\______\\|\\ ______\\" + lineSeparator() +
            " | | |    | |  |  || |  |  | \\|__   _|| |  ___|| |  |  || |     | \\|__   _|" + lineSeparator() +
            " | | |    | |  |  || |  |  |   | | |  | | |    | |  |  || |  |  |   | | |" + lineSeparator() +
            " | | |__  | |  |  || |     | __| | |_ | | |___ | |     || |     |   | | |" + lineSeparator() +
            " | | |__\\ | |     || | | | ||\\__\\| |_\\| | |___\\| |  |  || |  |  |   | | |" + lineSeparator() +
            "  \\|_____| \\|_____| \\|_|_|_| \\|______| \\|_____| \\|__|__| \\|__|__|    \\|_|";
        System.out.println("Welcome to" + lineSeparator() + logo + lineSeparator());
    }

    public static void printLoadMessage() {
        System.out.println("Initializing LumiChat v3.2.1.1..." + lineSeparator());
    }

    public static void printExitMessage() {
        System.out.println("Aborting LumiChat program...");
        System.out.println("LumiChat program has ended.");
    }

    public static void printSuccessfulSaveMessage() {
        System.out.println(HAPPY_FACE + "Lumi saves your task list successfully!" + lineSeparator());
    }

    public static void printUnsuccessfulSaveMessage() {
        System.out.println(SHOCK_FACE + "Lumi is not able to save your task list!" + lineSeparator());
    }

    public static void printReadyMessage() {
        System.out.println("LumiChat is now ready." + lineSeparator());
        System.out.println(HAPPY_FACE + "Hey I'm Lumi!");
        System.out.println("  How may Lumi assist you today?" + lineSeparator());
    }

    public static void printGoodbyeMessage() {
        System.out.println(SAD_FACE + "Goodbye! Lumi will miss you!" + lineSeparator());
    }

    public static final String GOODBYE_MESSAGE = SAD_FACE + "Goodbye! Lumi will miss you!\n";

     public static void printAddTaskMessage(ArrayList<Task> list) {
        int latestTaskIndex = list.size() - 1; // 0-based indexing
        System.out.println(HAPPY_FACE + "Alright, Lumi has added: " + list.get(latestTaskIndex).getTask() + "!");
        System.out.println(createSpaces(8) + list.get(latestTaskIndex).getTaskStatus());
        if (list.size() == 1) {
            System.out.println("  You now have " + list.size() + " task in Lumi's list!" + lineSeparator());
        } else {
            System.out.println("  You now have " + list.size() + " tasks in Lumi's list!" + lineSeparator());
        }
    }

    public static String addTaskMessage() {
        int latestTaskIndex = TaskList.size() - 1; // 0-based indexing

        return HAPPY_FACE + "Alright, Lumi has added: " + TaskList.get(latestTaskIndex).getTask() + "!\n" +
                createSpaces(8) + TaskList.get(latestTaskIndex).getTaskStatus() +
                "\nYou now have " + TaskList.size() + ((TaskList.size() == 1) ? "task" : "tasks") +
                " in Lumi's list!\n";
    }

    public static void printCompleteTaskMessage(ArrayList<Task> list, int listNumber) {
        System.out.println(HAPPY_FACE + "Well done! Lumi marks this task as completed!");
        System.out.println(createSpaces(8) + list.get(listNumber).getTaskStatus() +
                lineSeparator());
    }

    public static void printAlreadyCompletedTaskMessage(ArrayList<Task> list, int listNumber) {
        System.out.println(ANGRY_FACE +
        "Hey!! Lumi already marked <" + list.get(listNumber).getTask() + "> as completed!" + lineSeparator());
    }

    public static String completeTaskMessage(int index) {
        return HAPPY_FACE + "Well done! Lumi marks this task as completed!\n" +
                createSpaces(8) + TaskList.get(index).getTaskStatus() +"\n\n";
    }

    public static String alreadyCompletedTaskMessage(int index) {
        return ANGRY_FACE + "Hey!! Lumi already marked <" + TaskList.get(index).getTask() +
                "> as completed!\n\n";
    }

    public static final String LIST_MESSAGE = HAPPY_FACE + "Sure! Lumi prints your list!\n";

    public static void printList() {

//        if (isStandardPrint) {
//            System.out.println(HAPPY_FACE + "Sure! Lumi prints your list!");
//        }

        final String LIST_TOP =
                "    +---------+" + lineSeparator() +
                "+---| L I S T |------------------------------------------------+" + lineSeparator() +
                "|   +---------+                                                |";
        final String LIST_LEFT = "| ";
        final String LIST_RIGHT = " |";
        final String LIST_BOTTOM = "+--------------------------------------------------------------+" + lineSeparator();
        final int LIST_LENGTH = 60;

        System.out.println(LIST_TOP);

        // Print list items
        for (int i = 0; i < TaskList.size(); i++) {
            String listItem = i+1 + ". " + TaskList.get(i).getTaskStatus();

            System.out.print(LIST_LEFT);
            System.out.print(listItem);
            System.out.print(createSpaces(LIST_LENGTH-listItem.length()-1));
            System.out.println(LIST_RIGHT);
        }

        System.out.print(LIST_BOTTOM);

        // Print total number of tasks
        String totalTaskString = "Total: " + TaskList.size() + (TaskList.size() == 1 ? " task" : " tasks");
        System.out.print(LIST_LEFT);
        System.out.print(totalTaskString);
        System.out.print(createSpaces(LIST_LENGTH-totalTaskString.length()));
        System.out.println(LIST_RIGHT);
        System.out.println(LIST_BOTTOM);
    }

    public static void printDeleteTaskConfirmationMessage(ArrayList<Task> list, int listNumber) {
        System.out.println(THINKING_FACE + "Umm... Lumi needs you to confirm to delete this task:");
        System.out.println(createSpaces(8) + list.get(listNumber).getTaskStatus() +
                lineSeparator());
    }

    public static final String PROMPT_VALID_DELETE_CONFIRMATION_MESSAGE =
        THINKING_FACE + "Uh... Lumi needs you to enter either " +
        toBoldAndItalic("YES") + " to confirm deletion or " +
        toBoldAndItalic("NO") + " to cancel..." + lineSeparator();


    public static void printDeleteTaskMessage(ArrayList<Task> list, int listNumber) {
        System.out.println(HAPPY_FACE + "Bleep, Lumi say bye-bye to: " + list.get(listNumber).getTask() + "!");
        System.out.println(createSpaces(8) + list.get(listNumber).getTaskStatus());

        int newListSize = list.size() - 1;
        if (newListSize == 1) {
            System.out.println("  You now have " + newListSize + " task in Lumi's list!" + lineSeparator());
        } else {
            System.out.println("  You now have " + newListSize + " tasks in Lumi's list!" + lineSeparator());
        }
    }

    public static String deleteTaskMessage(int index) {
        int newListSize = TaskList.size() - 1;

        return HAPPY_FACE + "Bleep, Lumi say bye-bye to: " + TaskList.get(index).getTask() + "!\n" +
                createSpaces(8) + TaskList.get(index).getTaskStatus() +
                "\nYou now have " + newListSize + ((newListSize == 1) ? "task" : "tasks") +
                " in Lumi's list!\n\n";
    }

    public static void printAbortDeleteMessage() {
        System.out.println(HAPPY_FACE + "OK, Lumi continues without deleting!" + lineSeparator());
    }

    public static final String PROMPT_VALID_CREATE_CONFIRMATION_MESSAGE =
            "Please enter either " + toBoldAndItalic("YES") + " to confirm creating a new task list or " +
            toBoldAndItalic("NO") + " to abort the LumiChat program." + lineSeparator();


    public static void printCreateNewFileMessage() {
        System.out.println("New empty task list file created." + lineSeparator());

    }

    public static void printAbortCreateNewFileMessage() {
        System.out.println("New task list is not created." + lineSeparator());
    }

    public static final String PROMPT_VALID_EXIT_CONFIRMATION_MESSAGE =
            THINKING_FACE + "Uh... Lumi needs you to enter either " +
            toBoldAndItalic("YES") + " to confirm to leave " + toBold("without") + " saving or " +
            toBoldAndItalic("NO") + " to stay..." + lineSeparator();

    public static void printAbortExitMessage() {
        System.out.println(HAPPY_FACE + "Woohoo! Lumi continues to be with you!" + lineSeparator());
    }
}
