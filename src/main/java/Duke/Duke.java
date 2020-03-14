package duke;

import java.io.*;
import java.lang.NullPointerException;
import java.util.Scanner;

import duke.exceptions.MissingDateTimeException;
import duke.exceptions.MissingDescriptionException;
import duke.exceptions.MissingLocationException;
import duke.exceptions.WhitespaceExceptions;
import duke.taskList.TaskList;
import duke.taskManager.Deadline;
import duke.taskManager.Events;
import duke.taskManager.Task;
import duke.taskManager.Todo;
import duke.ui.*;
import java.util.ArrayList;
import java.io.BufferedReader;
import duke.storage.*;

/**
 * Duke is a a chatbot with task managing functions.
 * @author Lim Yu Xiang
 * @version CS2113 AY19/20 Sem 2 Duke
 */

public class Duke {
    private static ArrayList<Task> tasks = new ArrayList<Task>();
    public static final String FILE_PATH = "data/Tasklist.txt";
    /**
     * Main method for Duke.
     * @param args String[] args in main.
     */
    public static void main(String[] args) throws IOException {
        boolean reset;
        DisplayUI ui = new DisplayUI();
        Storage storage = new Storage(FILE_PATH);
        TaskList taskList = new TaskList();
        tasks = storage.importTaskFromFile();
        programStart(ui, storage, taskList);
        reset = loopTillEnd(ui, storage, taskList);
        if(reset){
            resetProgram();
        }
    }


    /**
     * Run greetings and show table of available functions
     */
    private static void programStart(DisplayUI ui, Storage storage, TaskList taskList) {
        ui.showStartMessages();
    }

    /**
     * Loop the program until "bye" is entered
     * @return true if reset is keyed in function else false
     */
    private static boolean loopTillEnd(DisplayUI ui, Storage storage, TaskList taskList) {
        Scanner myScanner = new Scanner(System.in);
        boolean flag = true; //Boolean flag for while loop
        String function;
        while (flag == true) {

            try {
                function = myScanner.next();

                switch (function) {
                    case "todo":
                        todoCommand(myScanner, function, ui, storage, taskList);
                        break;

                    case "deadline":
                        deadlineCommand(myScanner, function, ui, storage, taskList);
                        break;

                    case "event":
                        eventCommand(myScanner, function, ui, storage, taskList);
                        break;

                    case "bye":
                        System.out.println("Bye! Hope to see you again soon!");
                        System.out.println("____________________________________________________________");
                        flag = false;
                        break;

                    case "list":
                        listCommand(ui, storage, taskList);
                        break;

                    case "done":
                        doneCommand(myScanner, ui, storage, taskList);
                        break;

                    case "delete":
                        deleteCommand(myScanner, ui, storage, taskList);
                        break;

                    case "reset":
                        return true;

                    case "find":
                        findCommand(myScanner, ui, storage, taskList);
                        break;

                    case "help":
                        ui.showFunctionList();
                        break;

                    default:
                        //loop till valid function entered
                        String redundant = myScanner.nextLine();
                        System.out.println("Please key in a valid function");
                        break;
                }
            } catch (MissingDescriptionException e) {
                e.printDescr();
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("☹ OOPS! Error storing date and time!! Please try again.");
            } catch (NullPointerException e) {
                System.out.println("☹ OH NO! task number is missing!! Please try storing a task first.");
            } catch (IllegalArgumentException e) {
                System.out.println("☹ OOPS! Missing command! Please try command: done [task number] ");
            } catch (WhitespaceExceptions e) {
                System.out.println("Error while appending to file. Please check your white spaces and symbols.");
            } catch (MissingDateTimeException e) {
                e.printDescr();
            } catch (MissingLocationException e) {
                e.printDescr();
            }
        }
        return false;
    }

    /**
     * Method for finding task
     * @param myScanner
     */
    private static void findCommand(Scanner myScanner, DisplayUI ui, Storage storage, TaskList taskList) {
        String line;
        boolean found = false;
        line = myScanner.nextLine().trim();
        String[] keywords = line.split("\\s+");
        System.out.println("Here are the matching tasks in your list:");
        System.out.println("____________________________________________________________");
        for (String word : keywords) {
            for (int i = 0; i < tasks.size(); i++) {
                String descrp = tasks.get(i).getDescription();
                if (descrp.contains(word)) {
                    System.out.println("    " + i + 1 + "." + tasks.get(i));
                    found = true;
                }
            }
        }
        if (!found) {
            System.out.println("Sorry! There are no task with descriptions matching your keyword! Please try again!");
            System.out.println("____________________________________________________________");
        }
    }

    /**
     * Method for marking task as done
     * @param myScanner
     * @throws WhitespaceExceptions
     */
    private static void doneCommand(Scanner myScanner, DisplayUI ui, Storage storage, TaskList taskList) throws WhitespaceExceptions {
        String line;
        line = myScanner.nextLine();
        String l = line.replace(" ", "");
        if (l == "") {
            throw new IllegalArgumentException();
        }
        int taskNumber = Integer.parseInt(l) - 1;
        if (taskNumber >= tasks.size() || taskNumber < 0) {
            throw new NullPointerException();
        }
        tasks.get(taskNumber).markAsDone(tasks.get(taskNumber));
        String str = tasks.get(taskNumber).toString();
        String str2 = str.substring(6, str.length());
        storage.appendToFile(str2);
        System.out.println("____________________________________________________________");
        System.out.println("Great job! I've marked this task as done in your planner:");
        System.out.println("    " + tasks.get(taskNumber));
        System.out.println("____________________________________________________________");
    }

    /**
     * Method for deleting task
     * @param myScanner
     * @throws WhitespaceExceptions
     */
    private static void deleteCommand(Scanner myScanner, DisplayUI ui, Storage storage, TaskList taskList) throws WhitespaceExceptions {
        String line;
        String l;
        int taskNumber;
        String str;
        String str2;
        line = myScanner.nextLine();
        l = line.replace(" ", "");
        taskNumber = Integer.parseInt(l) - 1;
        if (l == "") {
            throw new IllegalArgumentException();
        }
        if (taskNumber >= tasks.size() || taskNumber < 0) {
            throw new NullPointerException();
        }
        System.out.println("____________________________________________________________");
        System.out.println("Noted. I've removed this task:");
        System.out.println("    " + tasks.get(taskNumber));
        str = tasks.get(taskNumber).toString();
        str2 = str.substring(6, str.length());
        storage.deleteToFile(str2);
        tasks.remove(taskNumber);
        System.out.println("Now you have " + tasks.size() + " tasks in your list.");
        System.out.println("____________________________________________________________");
        return;
    }

    /**
     * Method for listing all task in the Array List
     */
    private static void listCommand(DisplayUI ui, Storage storage, TaskList taskList) {
        if (tasks.size() != 0) {
            System.out.println("____________________________________________________________");
            System.out.println("Here are your task(s) currently in your planner:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(i + 1 + "." + tasks.get(i));
            }
            System.out.println("____________________________________________________________");
        } else {
            System.out.println("Wow! your planner is empty currently! Try adding some tasks first!");
        }
    }

    /**
     * Add a new event to the task list command
     * @param myScanner Scanner for task description input
     * @param function function name to throw into writeToFile method
     * @throws MissingDescriptionException if descriptions is missing in the input
     */
    private static void eventCommand(Scanner myScanner, String function, DisplayUI ui, Storage storage, TaskList taskList)
            throws MissingDescriptionException, MissingLocationException {
        String line;
        line = myScanner.nextLine();
        String[] eventName = line.split("/at");
        if (eventName[0].equals("")) {
            throw new MissingDescriptionException("The event description cannot be empty! PLease try again");
        }
        if (eventName[1].equals(null)) {
            throw new MissingLocationException("Location not found! Please try again or type 'help' to check input format.");
        }
        tasks.add(new Events(eventName[0], eventName[1]));
        storage.writeToFile(function, line);
        printAddedTask(tasks.get(tasks.size() - 1));
    }

    /**
     * Add a new task with deadline into the task list commmand
     * @param myScanner Scanner for task description input
     * @param function name to throw into writeToFile method
     * @throws MissingDescriptionException if descriptions is missing in the input
     */
    private static void deadlineCommand(Scanner myScanner, String function, DisplayUI ui, Storage storage, TaskList taskList)
            throws MissingDescriptionException, MissingDateTimeException {
        String line;
        line = myScanner.nextLine();
        String[] description = line.split("/by");
        if (description[0].equals("")) {
            throw new MissingDescriptionException("The deadline description cannot be empty! Please try again");
        }
        if (description[1].equals("")) {
            throw new MissingDateTimeException("Date/time not found! Please try again or type 'help' to check input format.");
        }
        tasks.add(new Deadline(description[0], description[1]));
        storage.writeToFile(function, line);
        printAddedTask(tasks.get(tasks.size() - 1));
    }

    /**
     * Add a new todo task to the tasklist command
     * @param myScanner Scanner for task description input
     * @param function name to throw into writeToFile method
     * @throws MissingDescriptionException if descriptions is missing in the input
     */
    private static void todoCommand(Scanner myScanner, String function, DisplayUI ui, Storage storage, TaskList taskList)
            throws MissingDescriptionException {
        String line = myScanner.nextLine();
        if (line.equals("")) {
            throw new MissingDescriptionException("The todo description is empty. Please try again or type 'help' to check input formats");
        }
        taskList.addTask(line, tasks);
        storage.writeToFile(function, line);
        taskList.printAddedTask(tasks.get(tasks.size() - 1), tasks);
    }

    /**
     * Print out the added new task to user
     * @param t The last task that is added into the list
     */
    public static void printAddedTask(Task t) {
        System.out.println("\n____________________________________________________________");
        System.out.println("    New task added:");
        System.out.println("    " + t);
        System.out.println("    Total number of tasks in the list:  " + tasks.size());
        System.out.println("____________________________________________________________");
    }


    /**
     * Clean the Tasklist.txt file in /data dir and reset the program
     * @throws IOException If there is error in closing the fileInStream
     */
    private static void resetProgram() throws IOException {
        FileInputStream fi = null;
        FileOutputStream fio = null;
        try {
            fi = new FileInputStream("data/Tasklist.txt");
            fio = new FileOutputStream("data/Tasklist.txt");
        } catch (IOException e) {
            System.out.println("Error Closing File");
        } finally {
            fi.close();
            fio.close();
        }
        deleteDirectory(new File("data"));
    }

    /**
     * Delete the /data and contents in it
     * @param dir The name of the path of dir
     */
    private static void deleteDirectory(File dir) {
        if (dir.isDirectory()) {
            if (dir.list().length == 0) {
                dir.delete();
                System.out.println("Deleting folder : " + dir.getAbsolutePath());
            } else {
                File[] children = dir.listFiles();
                for (File temp : children) {
                    deleteDirectory(temp);
                }
            }
            if (dir.list().length == 0) {
                dir.delete();
                System.out.println("Deleting folder : " + dir.getAbsolutePath());
            } else {
                dir.delete();
                System.out.println("Deleting File  : " + dir.getAbsolutePath());
            }
        }
    }
}