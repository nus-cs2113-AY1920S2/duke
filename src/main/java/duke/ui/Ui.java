package duke.ui;

import java.util.Scanner;

import duke.commands.CommandResult;
import duke.task.Task;
import duke.task.TaskList;

public class Ui {

    private static Scanner input = new Scanner(System.in);
    private static String command;

    public static String readInput() {
        command = input.nextLine();
        return command;
    }

    public static void showMessage(String message) {
        System.out.println(message);
    }

    private static void confirmTask(Task addedTask, TaskList tasks) {
        System.out.println("Got it. I've added this task:\n  " + addedTask + "\n"
                + "Now you have " + tasks.getSize() + " tasks in the list.");
    }

    public static void showResult(CommandResult result) {
        System.out.println(result.feedback);
        if (result.tasks != null) {
            if (result.tasks.size() != 0) {
                for (int i = 0; i < result.tasks.size(); i++) {
                    System.out.println((i + 1) + "." + result.tasks.get(i));
                }
            }
        }

    }

}
