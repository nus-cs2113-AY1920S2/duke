package duke;

import duke.TaskList;
import duke.task.Task;

public class Ui {
    private static final String logo =
              "     ____        _        \n"
            + "    |  _ \\ _   _| | _____ \n"
            + "    | | | | | | | |/ / _ \\\n"
            + "    | |_| | |_| |   <  __/\n"
            + "    |____/ \\__,_|_|\\_\\___|\n";

    private static final String splitLine = "    ________________________________";



    public void showWelcomeMessage(){
        System.out.println("    Hello from\n" + logo);
        showSplitLine();
        System.out.println("    Hello,I'm little pepper. Your personal sweetheart.");
        System.out.println("    What can I do for you? You can choose two model:");
        System.out.println("    1.echo mode\n    2.command mode");
        showSplitLine();
    }

    public static void showSplitLine(){
        System.out.println(splitLine);
    }

    public static void sayBye(){
        showSplitLine();
        System.out.println("    Don't leave me alone! Please come back soon!");
        showSplitLine();
    }

    public static void showCommandModeGuideInfo(){
        showSplitLine();
        System.out.println("    Now you are in command mode!");
        showSplitLine();
    }

    public static void showEchoModeGuideInfo(){
        showSplitLine();
        System.out.println("    Now you are in echo mode!");
        System.out.println("    Type in anything you want and I will repeat your words!");
        System.out.println("    'Bye' to exit");
        showSplitLine();
    }

    public static void showUnknownModeInfo(){
        System.out.println("    Unknown mode, please try again.");
    }

    public static void showUnknownCommandInfo(){
        System.out.println("    OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public static void showInexplictTimeDesCriptionInfo(){
        System.out.println("    Invalid input!!! Please use '/' to split task name and its time description");
    }

    public static void showCannotResolveTaskNameInfo(String input){
        System.out.println("    Invalid input! Cannot find description for a task event");
        System.out.println("    Your input: "+input+".");
        System.out.println("    Please use ' ' to split a task type and its description");
    }

    public static void showUnknownTaskIndexInfo(){
        System.out.println("    You have to point out clearly which task to mark as done or delete!!!");
    }

    public static void printFileNotFound(){
        System.out.println("    File not found!");
    }

    public static void showTaskNotFoundInfo(){
        System.out.println("    Referred task doesn't exist!!!");
        showTotalTaskNum();
    }

    public static void showTotalTaskNum(){
        System.out.println("    There are totally "+Integer.toString(TaskList.getLenOfList())+" tasks in the taskList");
    }

    public static void showAddTaskInfo(String newTaskName) {
        System.out.println("    added: " + newTaskName);
        System.out.println("    Now there are totally " + Integer.toString(TaskList.getLenOfList()) + " tasks in the list");
    }

    public static void printTaskDoneInfo(Task cur_task) {
        System.out.println("    Congratulations! You have just finished this task:");
        System.out.println("    " + cur_task.showTaskInfo());
    }

    public static void printTaskRemovedInfo(Task cur_task){
        System.out.println("    Noted. I've removed this task:");
        System.out.println("    "+cur_task.showTaskInfo());
    }

    public static void printIoWrongInfo(){
        System.out.println("Something wrong while writing to file");
    }

    public static void repeatInput(String input){
        showSplitLine();
        System.out.println("    " + input);
        showSplitLine();
    }
}
