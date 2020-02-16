import com.sun.source.tree.SwitchTree;
import exception.InexplicitTimeDescription;
import exception.UnknownCommandException;
import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;

import java.util.*;
public class Duke {
    private static TaskList taskList = new TaskList();
    private static final String FILE_PATH = "data/duke.txt";

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        showWelcomeMessage(logo);
        File f = new File(FILE_PATH);
        try {
            loadFileData(f);
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }
        Scanner in = new Scanner(System.in);
        String input = getAndProcessInput(in);
        Boolean inputNotValid = Boolean.TRUE;
        chooseOneMode(in, input, inputNotValid);
    }

    private static void chooseOneMode(Scanner in, String input, Boolean inputNotValid) {
        while (inputNotValid) {
            switch (input) {
            case "1":
                echoMode(input, in);
                inputNotValid = Boolean.FALSE;
                break;
            case "2":
                commandMode(input, in);
                inputNotValid = Boolean.FALSE;
                break;
            default:
                System.out.println("Unknown mode, please try again.");
                input = getAndProcessInput(in);
                break;
            }
        }
    }

    private static void showWelcomeMessage(String logo) {
        System.out.println("Hello from\n" + logo);
        System.out.println("________________________________");
        System.out.println("Hello,I'm little pepper. Your personal sweetheart.");
        System.out.println("What can I do for you? You can choose two model:");
        System.out.println("1.echo mode\n2.command mode");
        System.out.println("________________________________");
    }

    private static void printTaskDoneInfo(Task cur_task) {
        System.out.println("    Congratulations! You have just finished this task:");
        System.out.println("    " + cur_task.showTaskInfo());
    }

    private static void printTaskRemovedInfo(Task cur_task){
        System.out.println("    Noted. I've removed this task:");
        System.out.println("    "+cur_task.showTaskInfo());
    }

    public static void sayBye() {
        System.out.println("    ________________________________");
        System.out.println("    Don't leave me alone! Please come back soon!");
        System.out.println("    ________________________________");
    }

    public static void echoMode(String input, Scanner in) {
        input = getAndProcessInput(in);
        while (!(input.equals("bye"))) {
            echo(input);
            input = getAndProcessInput(in);
        }
        sayBye();
    }

    public static void commandMode(String input, Scanner in) {
//        TaskList taskList = new TaskList();
        int taskNum = 0;
        input = getAndProcessInput(in);
        while (!input.toLowerCase().equals("bye")) {
            System.out.println("    ________________________________");
            parseInputCommand(input);
            System.out.println("    ________________________________");
            input = getAndProcessInput(in);
        }
        sayBye();
    }

    private static String getAndProcessInput(Scanner in) {
        String input;
        input = in.nextLine().trim();
        return input;
    }

    private static void echo(String input) {
        System.out.println("    ________________________________");
        System.out.println("    " + input);
        System.out.println("    ________________________________");
    }

    private static void parseInputCommand(String input) {
        if(input.startsWith("done")){
            setTaskDone(input);
        }else if(input.startsWith("delete")){
            removeCertainTask(input);
        } else if(!input.equals("list")) {
            addNewTask(input);
        } else taskList.printTaskList();
    }

    private static void removeCertainTask(String input) {
        try{
            int taskIndex = getTaskIndex(input);
            Task cur_task = taskList.getOneTask(taskIndex-1);
            taskList.remove(taskIndex);
            saveToFile();
            printTaskRemovedInfo(cur_task);
            System.out.println("    There are totally "+Integer.toString(taskList.getLenOfList())+" tasks in the taskList");
        } catch (NumberFormatException e){
            System.out.println("    You have to point out which task to delete!!!");
        } catch (IndexOutOfBoundsException e){
            System.out.println("    Referred task doesn't exist!!!");
            System.out.println("    There are totally "+Integer.toString(taskList.getLenOfList())+" tasks in the taskList");
        }
    }

    private static void addNewTask(String input) {
        int dividePosition = input.indexOf(" ");
        try {
            String type = input.substring(0,dividePosition);
            String newTaskName = newSpecificTask(input, type);
            saveToFile();
            showFeedback(newTaskName);
        } catch(StringIndexOutOfBoundsException e){
            System.out.println("    Invalid input! Cannot find description for a task event");
            System.out.println("    Your input: "+input+".");
            System.out.println("    Please use ' ' to split a task type and its description");
        } catch (UnknownCommandException e) {
            System.out.println("    OOPS!!! I'm sorry, but I don't know what that means :-(");
        } catch (InexplicitTimeDescription e){
            System.out.println("    Invalid input!!! Please use '/' to split task name and its time description");
        }
    }

    private static void setTaskDone(String input) {
        try{
            int taskIndex = getTaskIndex(input);
            Task cur_task = taskList.getOneTask(taskIndex-1);
            cur_task.setTaskStatus(Task.DONE);
            saveToFile();
            printTaskDoneInfo(cur_task);
        } catch (NumberFormatException e){
            System.out.println("    You have to point out which task to mark as done!!!");
        } catch (IndexOutOfBoundsException e){
            System.out.println("    Reffered task doesn't exist!!!");
            System.out.println("    There are totally "+Integer.toString(taskList.getLenOfList())+" tasks in the taskList");
        }
    }

    private static void showFeedback(String newTaskName) {
        System.out.println("    added: " + newTaskName);
        System.out.println("    Now you have " + Integer.toString(taskList.getLenOfList()) + " tasks in the list");
    }

    private static String newSpecificTask(String input, String type) throws UnknownCommandException, InexplicitTimeDescription {
        String newTaskName = "";
        switch (type) {
        case "todo":
            newTaskName = getNewTodoName(input);
            taskList.append(new ToDo(newTaskName));
            break;
        case "deadline":
            try {
                newTaskName = getDdlOrEventName(input);
            } catch (InexplicitTimeDescription e) {
                throw e;
            }
            String by = getByOrDate(input);
            taskList.append(new Deadline(newTaskName, by));
            break;
        case "event":
            try {
                newTaskName = getDdlOrEventName(input);
            } catch (InexplicitTimeDescription e) {
                throw e;
            }
            String date = getByOrDate(input);
            taskList.append(new Event(newTaskName, date));
            break;
        default:
            throw new UnknownCommandException();
        }
        return newTaskName;
    }

    private static String getNewTodoName(String input) {
        input = input.replace("todo ", "");
        return input;
    }

    private static String getDdlOrEventName(String input) throws InexplicitTimeDescription {
        String newName = input.replace("deadline ", "").replace("event ", "");
        try {
            int cutPosition = newName.indexOf("/");
            newName = newName.substring(0, cutPosition - 1);
        } catch (StringIndexOutOfBoundsException e) {
            throw new InexplicitTimeDescription();
        }
        return newName;
    }


    private static String getByOrDate(String input) {
        int byBeginPosition = input.indexOf("/");
        return input.substring(byBeginPosition + 4);
    }


    private static int getTaskIndex(String input) throws NumberFormatException {
        int dividePosition = input.indexOf(" ");
        try {
            return Integer.parseInt(input.substring(dividePosition + 1));
        } catch (NumberFormatException e) {
            throw e;
        }
    }

    private static void loadFileData(File f) throws FileNotFoundException {
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String record = s.nextLine().replace("[", "")
                    .replace("]", "");
            restoreTasks(record);
        }
    }

    private static void restoreTasks(String record) {
        String taskType = record.substring(0, 1);
        String taskStatus = record.substring(1, 2);
        String taskName = "";
        String timeDescription = "";
        if (record.contains("(")) {
            taskName = record.substring(2,record.indexOf('(')).trim();
            timeDescription = record.substring(record.indexOf(":") + 1).replace(")","").trim();
        } else{
            taskName = record.substring(2).trim();
        }
        switch (taskType) {
        case "T":
            taskList.append(new ToDo(taskName));
            break;
        case "E":
            taskList.append(new Event(taskName, timeDescription));
            break;
        case "D":
            taskList.append(new Deadline(taskName, timeDescription));
            break;
        }
    }

    private static void saveToFile() {
        try {
            rewriteFile(FILE_PATH);
        } catch (IOException e) {
            System.out.println("Something wrong while writing to file");
        }
    }

    private static void rewriteFile(String file_path) throws IOException {
        FileWriter fw = new FileWriter(file_path);
        for (int i = 0; i < taskList.getLenOfList(); i++) {
            Task cur_task = taskList.getOneTask(i);
            String taskInfo = cur_task.showTaskInfo();
            fw.write(taskInfo+System.lineSeparator());
        }
        fw.close();
    }
}