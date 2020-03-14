import duke.*;
import java.io.*;


/**
 * Main class of the Duke project containing UI display, storage handling, input handling
 * and task list operations.
 */

public class Duke {

    private static UI ui = new UI();
    private static Storage storage = new Storage();
    private static Parser parser = new Parser();
    private static TaskList taskListOp = new TaskList();

    public static void main(String[] args) throws DukeException, IOException {
        ui.welcomeMessage();
        storage.loadFile();
        ui.greetUser();
        conversation();
    }

    /**
     * Takes in input string from terminal and calls various
     * functions according to different keywords.This function uses recursion.
     *
     * @throws IOException or DukeException If input does not match format.
     */
    public static void conversation() throws DukeException, IOException {
        System.out.println("____________________________________________________________");
        String str = parser.readCommand();
        System.out.println("____________________________________________________________");
        if (str.equals("bye")) {
            storage.saveTask();
            ui.finishConversation();
        } else if (str.contains("delete")) {
            taskListOp.deleteTask(str);
            conversation();
        } else if (str.equals("add task")) {
            System.out.println("____________________________________________________________");
            System.out.println("Task Management Mode has been activated");
            addTaskScreen();
        } else if (str.equals("list")) {
            taskListOp.listTask();
            conversation();
        } else if (str.contains("done")) {
            taskListOp.markDone(str);
            conversation();
        } else if (str.contains("find")) {
            taskListOp.findTask(str);
            conversation();
        } else {
            System.out.println(str);
            System.out.println("____________________________________________________________");
            conversation();
        }
    }

    /**
     * Triggered after entering "add task" in terminal
     * In adding Task mode, keywords are used to put tasks in different categories
     * and handle the status of the task list.
     *
     * @throws IOException or DukeException If input does not match format.
     */
    public static void addTaskScreen() throws IOException, DukeException {
        System.out.println("____________________________________________________________");
        String str = parser.readCommand();

        if (str.equals("bye")) {
            storage.saveTask();
            ui.finishConversation();
        } else if (str.contains("done")) {
            taskListOp.markDone(str);
            addTaskScreen();
        } else if (str.contains("find")) {
            taskListOp.findTask(str);
            addTaskScreen();
        } else if (str.equals("list")) {
            taskListOp.listTask();
            addTaskScreen();
        } else {
            final boolean b = str.equals("todo") || str.equals("deadline") || str.equals("event");
            if ((str.contains("todo"))) {
                if (b) {
                    new DukeException("OOPS!!! The description cannot be empty.");
                } else {
                    String task = str.replace(" ","").substring(4);
                    ToDos newTask = new ToDos(task);
                    taskListOp.addTask(newTask);
                }
                addTaskScreen();
            } else if ((str.contains("deadline"))) {
                String input = str.replace(" ","");
                if (b) {
                    new DukeException("OOPS!!! The description cannot be empty.");
                }else if (!input.contains("/") || !input.contains("by")) {
                    new DukeException("OOPS!!!Wrong format");
                }else if(input.contains("deadline/")) {
                    new DukeException("OOPS!!!Please specify more details");
                }else if(input.endsWith("/")) {
                    new DukeException("OOPS!!!Please specify more details");
                }else{
                    int dividerPositionSlash = input.indexOf("/");
                    String task = input.substring(8, dividerPositionSlash);
                    if (input.substring(dividerPositionSlash + 1).equals("by")||
                            input.substring(dividerPositionSlash + 1).contains("at")) {
                        new DukeException("OOPS!!!Please specify more details");
                    } else {
                        String date = input.substring(dividerPositionSlash + 3);
                        Deadlines newTask = new Deadlines(task, date);
                        taskListOp.addTask(newTask);
                    }
                }
                addTaskScreen();
            } else if ((str.contains("event"))) {
                String input = str.replace(" ","");
                if (b) {
                    new DukeException("OOPS!!! The description cannot be empty.");
                } else if (!input.contains("/") || !input.contains("at")) {
                    new DukeException("OOPS!!!Wrong format");
                } else if(input.contains("event/")) {
                    new DukeException("OOPS!!!Please specify more details");
                }else if(input.endsWith("/")) {
                    new DukeException("OOPS!!!Please specify more details");
                }else{
                    int dividerPositionSlash = input.indexOf("/");
                    String task = input.substring(5, dividerPositionSlash);
                    if (input.substring(dividerPositionSlash + 1).equals("at") ||
                            input.substring(dividerPositionSlash + 1).contains("by")){
                        new DukeException("OOPS!!!Please specify more details");
                    } else {
                        String date = input.substring(dividerPositionSlash + 3);
                        Events newTask = new Events(task, date);
                        taskListOp.addTask(newTask);
                    }
                }
                addTaskScreen();
            } else if (str.contains("delete")) {
                taskListOp.deleteTask(str);
                addTaskScreen();
            } else {
                new DukeException("OOPS!!!Wrong input format.");
                addTaskScreen();
            }
        }
    }
}

