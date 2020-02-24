package Duke;

import java.util.ArrayList;
import java.util.Scanner;

public class Parser {

    public static void parseUserCommands(ArrayList<Task> taskArrayList, ArrayList<Task> lastShownList, int taskListSize, Scanner scanner) {
        int lastShownListSize;
        int exit = 0;
        while (exit == 0) {
            String userInput = scanner.nextLine();
            String[] tokenizedInputs = userInput.split(" ", 2);
            String instruction = tokenizedInputs[0];

            switch (instruction) {
            case "bye":
                System.out.println(Duke.GOODBYE);
                exit = 1;
                Storage.saveTasks(Duke.FILEPATH, taskArrayList, true);
                break;
            case "find":
                String keyword = tokenizedInputs[1];
                lastShownList.clear();
                lastShownListSize = 0;
                Ui.displayMatchingTasks(taskArrayList, lastShownList, lastShownListSize, keyword);
                break;
            case "list":
                Ui.printTasks(taskArrayList, taskListSize);
                lastShownList.clear();
                lastShownList = (ArrayList<Task>) taskArrayList.clone();
                break;
            case "done":
                if (checkEmptyDescription(tokenizedInputs, instruction)) break;
                int taskDone = Integer.valueOf(tokenizedInputs[1]) - 1;
                //to do more error handling here for index out of bounds
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println(lastShownList.get(taskDone).markAsDone());
                break;
            case "delete":
                if (checkEmptyDescription(tokenizedInputs, instruction)) break;
                int taskToDelete = Integer.valueOf(tokenizedInputs[1]) - 1;
                //to do more error handling here for index out of bounds
                taskListSize--;
                Ui.respondDeleteSuccess(taskListSize, lastShownList.get(taskToDelete));
                taskArrayList.remove(lastShownList.get(taskToDelete));
                break;
            case "todo":
                if (checkEmptyDescription(tokenizedInputs, instruction)) break;
                taskArrayList.add(new ToDo(tokenizedInputs[1]));
                Ui.respondAddedSuccess(taskListSize, taskArrayList.get(taskListSize));
                taskListSize++;
                break;
            case "deadline":
                if (checkEmptyDescription(tokenizedInputs, instruction)) break;
                String[] deadlineInfo = tokenizedInputs[1].split(" /by ");
                if (checkDateEntered(deadlineInfo)) break;
                taskArrayList.add(new Deadline(deadlineInfo[0], deadlineInfo[1]));
                Ui.respondAddedSuccess(taskListSize, taskArrayList.get(taskListSize));
                taskListSize++;
                break;
            case "event":
                if (checkEmptyDescription(tokenizedInputs, instruction)) break;
                String[] eventInfo = tokenizedInputs[1].split(" /at ");
                if (checkDateEntered(eventInfo)) break;
                taskArrayList.add(new Event(eventInfo[0], eventInfo[1]));
                Ui.respondAddedSuccess(taskListSize, taskArrayList.get(taskListSize));
                taskListSize++;
                break;
            default:
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                break;
            }
        }
    }

    static boolean checkDateEntered(String[] information) {
        if (information.length == 1) {
            System.out.println("☹ OOPS!!! You did not enter a date");
            return true;
        }
        return false;
    }

    static boolean checkEmptyDescription(String[] tokens, String instruction) {
        if (tokens.length == 1) {
            System.out.println("☹ OOPS!!! The description of a " + instruction + " cannot be empty.");
            return true;
        }
        return false;
    }
}