package duke;

import types.Task;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Main class for the Duke Personal Assistant
 */
public class Duke {

    /**
     * Line that divides text
     */
    private static final String BAR = "____________________________________";

    /**
     * File path for saving and loading
     */
    private static final String FILE_PATH = "./duke.txt";

    public static void main(String[] args) throws FileNotFoundException {
        TaskList tl = new TaskList();
        Ui.introduction();
        File f = Storage.loadFile();
        String input = Parser.getInput();
        while (!input.equals("bye")) {
            System.out.println(BAR);
            String cWord = Parser.getCommandWord(input);
            String task = Parser.getDescription(input);
            if (input.equals("list")) {
                Ui.listCommand();
            } else if (cWord.equals("done")) {
                try {
                    doneCommand(input, f);
                } catch (DukeException e) {
                    System.out.println("    You must include the number of the completed task!");
                }
            } else if (cWord.equals("todo")) {
                try {
                    todoCommand(task, f);
                } catch (DukeException e) {
                    System.out.println("    You must include what needs to be done!");
                }
            } else if (cWord.equals("deadline")) {
                try {
                    deadlineCommand(task, f);
                } catch (DukeException e) {
                    System.out.println("    You must specify when the deadline is by!");
                }
            } else if (cWord.equals("event")) {
                try {
                    eventCommand(task, f);
                } catch (DukeException e) {
                    System.out.println("    You must specify when the event is at!");
                }
            } else if (cWord.equals("delete")) {
               try {
                   deleteCommand(input, f);
               } catch (DukeException e) {
                   System.out.println("    You must include the number of deleted task!");
               }
            } else if (cWord.equals("find")) {
                try {
                    findCommand(task);
                } catch (DukeException e) {
                    System.out.println("    You must include a keyword!");
                }
            } else {
                System.out.println("    I'm sorry! I don't know what that means :( ");
            }
            System.out.println(BAR);
            input = Parser.getInput();
        }
        Ui.goodbye();
    }

    /**
     * The event command, which adds a new event and when it is
     * @param task what the event is and when it is at, denoted by /at
     * @param f file to write to
     * @throws DukeException exception
     */
    private static void eventCommand(String task, File f) throws DukeException{
        if (!task.contains("/at")) {
            throw new DukeException();
        }
        String[] splitTask2 = task.split("/at");
        types.Task t = new types.Event(splitTask2[0], splitTask2[1]);
        completeCommand(f, t);
    }

    /**
     * The deadline command, which adds a new task and its deadline
     * @param task what the deadline is for and when it needs to be done by, denoted by /by
     * @param f file to write to
     * @throws DukeException exception
     */
    private static void deadlineCommand(String task, File f) throws DukeException {
        if (!task.contains("/by")) {
            throw new DukeException();
        }
        String[] splitTask2 = task.split("/by");
        types.Task t = new types.Deadline(splitTask2[0], splitTask2[1]);
        completeCommand(f, t);
    }

    /**
     * The to do command, which adds a new task to be done
     * @param task what needs to be done
     * @param f file to write to
     * @throws DukeException exception
     */
    private static void todoCommand(String task, File f) throws DukeException {
        if (task.isEmpty()) {
            throw new DukeException();
        }
        types.Task t = new types.Todo(task);
        completeCommand(f, t);
    }

    /**
     * The done command, which can mark and event as done or not
     * @param command the command that was done and the number of the command
     * @param f file to write to
     * @throws DukeException exception
     */
    private static void doneCommand(String command, File f) throws DukeException {
        String[] splitTask2 = command.split(" ");
        if (splitTask2.length != 2) {
            throw new DukeException();
        }
        int taskDoneNum = Integer.parseInt(splitTask2[1]);
        if (taskDoneNum - 1 >= TaskList.getNumTasks() || taskDoneNum <= 0) {
            System.out.println("    You haven't done that many tasks yet!");
        } else {
            TaskList.getTaskList()[taskDoneNum - 1].setDone(true);
            System.out.println("    Good work! I've marked this task as done!");
            System.out.println("    " + TaskList.getTaskList()[taskDoneNum - 1].toString());
        }
        Storage.saveChanges(f);
    }

    /**
     * The delete command, which deletes an item from the list
     * @param command number of task to be deleted
     * @param f file to write to
     * @throws DukeException exception
     */
    private static void deleteCommand(String command, File f) throws DukeException {
        String[] splitTask2 = command.split(" ");
        if (splitTask2.length != 2) {
            throw new DukeException();
        }
        int taskDeleteNum = Integer.parseInt(splitTask2[1]);
        if (taskDeleteNum - 1 >= TaskList.getNumTasks() || taskDeleteNum <= 0) {
            System.out.println("    You haven't done that many tasks yet!");
        } else {
            System.out.println("    Noted. I've removed this task: ");
            System.out.println("    " + TaskList.getTaskList()[taskDeleteNum - 1].toString());
            TaskList.setTaskList(TaskList.deleteIndex(TaskList.getTaskList(), taskDeleteNum - 1));
            System.out.println("    Now you have " + TaskList.getNumTasks() + " tasks in the list.");
        }
        Storage.saveChanges(f);
    }

    /**
     * Command to search for tasks by keyword
     * @param keyword keyword to search by
     * @throws DukeException exception
     */
    private static void findCommand(String keyword) throws DukeException{
        if (keyword.isEmpty()) {
            throw new DukeException();
        }
        types.Task[] temp = new types.Task[100];
        int counter = 0;
        for (int i = 0; i < TaskList.getNumTasks(); i++) {
            Task currTask = TaskList.getTaskList()[i];
            if (currTask.getName().contains(keyword)) {
                temp[counter] = currTask;
                counter++;
            }
        }
        System.out.println("    Here are matching tasks in your list:");
        for (int k = 0; k < counter; k++) {
            System.out.print("    " + (k + 1) + ".");
            System.out.println(temp[k].toString());
        }
    }

    /**
     * Adds task t list, prints task, and saves changes
     * @param f file to save changes to
     * @param t task to process
     */
    private static void completeCommand(File f, Task t) {
        TaskList.addTask(t);
        Ui.printTask(t);
        Storage.saveChanges(f);
    }

}

