package duke;

import java.io.*;
import java.lang.NullPointerException;
import java.util.Scanner;

import duke.exceptions.MissingDescriptionException;
import duke.exceptions.WhitespaceExceptions;
import duke.taskManager.Deadline;
import duke.taskManager.Events;
import duke.taskManager.Task;
import duke.taskManager.Todo;
import duke.ui.*;

import java.util.ArrayList;
import java.nio.file.*;
import java.io.BufferedReader;

public class Duke {
    private static ArrayList<Task> tasks = new ArrayList<Task>();
    private static int size = 0;

    //Importing of all task from Tasklist.txt to program when first launch
    public Duke() {
        try {
            Path path = Paths.get("data");
            if(!Files.exists(path)) {
                Files.createDirectory(path);
                File f = new File("data/Tasklist.txt");
            } else {
                File f = new File("data/Tasklist.txt");
                Scanner fileScanner = new Scanner(f);
                while (fileScanner.hasNextLine()) {
                    String line = fileScanner.nextLine();
                    String[] singleTaskDescriptions = line.split("//");
                    switch (singleTaskDescriptions[0]) {
                        case "T":
                            addtask(new Todo(singleTaskDescriptions[2]));
                            if(Integer.parseInt(singleTaskDescriptions[1]) == 1){
                                tasks.get(size - 1).importDone();
                            }
                            break;

                        case "D":
                            addtask(new Deadline(singleTaskDescriptions[2], singleTaskDescriptions[3]));
                            if(Integer.parseInt(singleTaskDescriptions[1]) == 1){
                                tasks.get(size - 1).importDone();
                            }
                            break;

                        case "E":
                            addtask(new Events(singleTaskDescriptions[2], singleTaskDescriptions[3]));
                            if(Integer.parseInt(singleTaskDescriptions[1]) == 1){
                                tasks.get(size - 1).importDone();
                            }
                            break;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error occurred. Please try again!");
        }
    }

    public static void main(String[] args) {
        programStart();
        loopTillEnd();
    }

    //Loop the program until "bye" is entered
    private static void loopTillEnd(){
        Scanner myScanner = new Scanner(System.in);
        boolean flag = true; //Boolean flag for while loop
        String function;
        while (flag == true) {

            try {
                function = myScanner.next();

                switch (function) {
                    //add new todo task
                    case "todo":
                        String line = myScanner.nextLine();
                        if(line.equals("")){
                            throw new MissingDescriptionException("☹ OOPS!!! The todo description cannot be empty!!");
                        }
                        addtask(new Todo(line));
                        writeToFile(function,line);
                        printAddedTask("New To-Do Task added:");
                        break;

                    //add new task with deadline
                    case "deadline":
                        line = myScanner.nextLine();
                        String[] description = line.split("/");
                        if(description[0].equals("")){
                            throw new MissingDescriptionException("☹ OOPS!!! The deadline description cannot be empty!!");
                        }
                        String[] deadLine = description[1].split("by ");
                        if(description[0] == null){
                            throw new ArrayIndexOutOfBoundsException();
                        }
                        addtask(new Deadline(description[0], deadLine[1]));
                        writeToFile(function,line);
                        printAddedTask("New Task with deadline added:");
                        break;

                    //add new event task
                    case "event":
                        line = myScanner.nextLine();
                        String[] eventName = line.split("/");
                        if(eventName[0].equals("")){
                            throw new MissingDescriptionException("☹ OOPS!!! The event description cannot be empty!!");
                        }
                        String[] event = eventName[1].split("at ");
                        if(event[0].equals(null)){
                            throw new ArrayIndexOutOfBoundsException();
                        }
                        addtask(new Events(eventName[0], event[1]));
                        writeToFile(function,line);
                        printAddedTask("New event added:");
                        break;

                     //exit program
                    case "bye":
                        System.out.println("Bye! Hope to see you again soon!");
                        System.out.println("____________________________________________________________");
                        flag = false;
                        break;

                    //list all task stored
                    case "list":
                        if(tasks.size() != 0) {
                            System.out.println("____________________________________________________________");
                            System.out.println("Here are your task(s) currently in your planner:");
                            for (int i = 0; i < tasks.size(); i++) {
                                System.out.println(i + 1 + "." + tasks.get(i));
                            }
                            System.out.println("____________________________________________________________");
                        } else {
                            System.out.println("Wow! your planner is empty currently! Try adding some tasks first!");
                        }
                        break;

                    //mark a task as completed
                    case "done":
                        line = myScanner.nextLine();
                        String l = line.replace(" ", "");
                        if(l == ""){
                            throw new IllegalArgumentException();
                        }
                        int taskNumber = Integer.parseInt(l) - 1;
                        if(taskNumber >= tasks.size() || taskNumber < 0){
                            throw new NullPointerException();
                        }
                        tasks.get(taskNumber).markAsDone(tasks.get(taskNumber));
                        String str = tasks.get(taskNumber).toString();
                        String str2 = str.substring(6,str.length());
                        appendToFile(str2);
                        System.out.println("____________________________________________________________");
                        System.out.println("Great job! I've marked this task as done in your planner:");
                        System.out.println(tasks.get(taskNumber));
                        System.out.println("____________________________________________________________");
                        break;

                    //delete a task
                    case "delete":
                        line = myScanner.nextLine();
                        l = line.replace(" ","");
                        taskNumber = Integer.parseInt(l) - 1;
                        if(l == ""){
                            throw new IllegalArgumentException();
                        }
                        if(taskNumber >= tasks.size() || taskNumber < 0){
                            throw new NullPointerException();
                        }
                        System.out.println("____________________________________________________________");
                        System.out.println("Noted. I've removed this task:");
                        System.out.println(tasks.get(taskNumber));
                        str = tasks.get(taskNumber).toString();
                        str2 = str.substring(6,str.length());
                        deleteToFile(str2);
                        tasks.remove(taskNumber);
                        System.out.println("Now you have " + tasks.size() + " tasks in your list.");
                        System.out.println("____________________________________________________________");
                        break;

                    //delete /data dir and contents and end the program (Bugs)
                    case "reset":
                        deleteDirectory(new File("data"));
                        flag = false;
                        break;

                    //find task with matching descriptions to keyword
                    case "find":
                        boolean found = false;
                        line = myScanner.nextLine().trim();
                        String[] keywords = line.split("\\s+");
                        System.out.println("Here are the matching tasks in your list:");
                        System.out.println("____________________________________________________________");
                        for(String word : keywords) {
                            for(int i = 0; i < tasks.size(); i++){
                                String descrp = tasks.get(i).getDescription();
                                if(descrp.contains(word)){
                                    System.out.println(i + 1 + "." + tasks.get(i));
                                    found = true;
                                }
                            }
                        }
                        if(!found){
                            System.out.println("Sorry! There are no task with descriptions matching your keyword! Please try again!");
                            System.out.println("____________________________________________________________");
                        }
                        break;
                    case "help":
                        DisplayUI ui = new DisplayUI();
                        ui.showFunctionList();
                        break;

                    default:
                        System.out.println("Please key in a valid function"); //loop till valid function entered
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
            }
        }
    }

    //Run greetings and show table of available functions
    private static void programStart() {
        DisplayUI ui = new DisplayUI();
        ui.showStartMessages();
    }

    // method for printing the last type of task entered
    public static void printAddedTask(String s) {
        System.out.println("____________________________________________________________");
        System.out.println(s);
        System.out.println("Total number of tasks in the list:  " + tasks.size());
        System.out.println("____________________________________________________________");
    }

    //method for adding adding newly created task into tasks array
    public static void addtask(Task description) {
        tasks.add(description);
        size++;
    }

    //Write new task to tasklist.txt
    private static void writeToFile(String str1, String str2) {
        String filePath = "data/Tasklist.txt";
        try {
            FileWriter fw = new FileWriter(filePath, true);
            if (str1.contains("todo")) {
                String newLineFormatted = str2.stripLeading();
                fw.write("T//0//" + newLineFormatted + System.lineSeparator());
                fw.close();
            }
            if (str1.contains("deadline")) {
                String[] newLineFormatted = str2.split("/by");
                fw.write("D//0//" + newLineFormatted[0].trim() + "//" + newLineFormatted[1].trim() + System.lineSeparator());
                fw.close();
            }
            if (str1.contains("event")){
                String[] newLineFormatted = str2.split("/at");
                fw.write("E//0//" + newLineFormatted[0].trim() + "//" + newLineFormatted[1].trim() + System.lineSeparator());
                fw.close();
            }
        } catch (IOException e) {
            System.out.println("Error while writing into file. Please try again");
        }
    }

    //Append tasks in tasklist.txt
    private static void appendToFile(String newLine) throws WhitespaceExceptions {
        String taskSymbol;
        String [] strArr;
        String description;
        String timeDate;
    //    System.out.println("input:"+newLine);
        if(newLine.contains("(by:")){
            String newline2 = newLine.replace("(by:", ":");
            strArr = newline2.split(":");
            description = strArr[0].trim();
            System.out.println(description);
            timeDate = strArr[1].replace(")","").trim();
            taskSymbol = "D//";
         //   System.out.println("D Formatted: " + description + timeDate);
        } else if (newLine.contains("(at:")) {
            String newline2 = newLine.replace("(at:", ":");
            strArr = newline2.split(":");
            description = strArr[0].trim();
            timeDate = strArr[1].replace(")","").trim();
            taskSymbol = "E//";
         //   System.out.println(" E Formatted: " + description + timeDate);
        } else {
            description = newLine.trim();
            timeDate = "";
            taskSymbol = "T//";
         //   System.out.println("T Formatted: " + description);
        }
        if(description.isBlank()){
            throw new WhitespaceExceptions();
        }

        String filePath = "data/Tasklist.txt";
        File fileToBeModified = new File(filePath);
        String originalFileContent = "";
        BufferedReader reader = null;
        FileWriter writer = null;
        try {
            reader = new BufferedReader(new FileReader(fileToBeModified));
            String currentReadingLine = reader.readLine();
            String oldString = null;
            String newString = null;

            while (currentReadingLine != null) {
                if(currentReadingLine.contains(description)){
                    oldString = currentReadingLine;
                    newString = taskSymbol + 1 + "//" + description + "//" + timeDate;
                }
                originalFileContent += currentReadingLine + System.lineSeparator();
                currentReadingLine = reader.readLine();
            }
            String newFileContent = originalFileContent.replace(oldString, newString);
            writer = new FileWriter(fileToBeModified);
            writer.write(newFileContent);

        } catch (IOException e) {
            System.out.println("Error appending to file! Please try again!");
        } finally {
            try {
                reader.close();
                writer.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    //Delete task from tasklist.txt
    private static void deleteToFile(String newLine) throws WhitespaceExceptions {
        String taskSymbol;
        String [] strArr;
        String description;
        String timeDate;
    //    System.out.println("input:"+newLine);
        if(newLine.contains("(by:")){
            String newline2 = newLine.replace("(by:", ":");
            strArr = newline2.split(":");
            description = strArr[0].trim();
            timeDate = strArr[1].replace(")","").trim();
            taskSymbol = "D//";
         //   System.out.println("D Formatted: " + description + timeDate);
        } else if (newLine.contains("(at:")) {
            String newline2 = newLine.replace("(at:", ":");
            strArr = newline2.split(":");
            description = strArr[0].trim();
            timeDate = strArr[1].replace(")","").trim();
            taskSymbol = "E//";
         //   System.out.println(" E Formatted: " + description + timeDate);
        } else {
            description = newLine.trim();
            timeDate = "";
            taskSymbol = "T//";
         //   System.out.println("T Formatted: " + description);
        }
        if(description.isBlank()){
            throw new WhitespaceExceptions();
        }

        String filePath = "data/Tasklist.txt";
        File fileToBeModified = new File(filePath);
        String originalFileContent = "";
        BufferedReader reader = null;
        FileWriter writer = null;
        try {
            reader = new BufferedReader(new FileReader(fileToBeModified));
            String currentReadingLine = reader.readLine();
            String oldString = null;
            String newString = null;

            while (currentReadingLine != null) {
                if(currentReadingLine.contains(description)){
                    oldString = currentReadingLine;
                    newString = "";
                }
                originalFileContent += currentReadingLine + System.lineSeparator();
                currentReadingLine = reader.readLine();
            }
            String newFileContent = originalFileContent.replace(oldString, newString);
            writer = new FileWriter(fileToBeModified);
            writer.write(newFileContent);

        } catch (IOException e) {
            System.out.println("Error appending to file! Please try again!");
        } finally {
            try {
                reader.close();
                writer.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    //Delete /data dir and contents in it
    private static void deleteDirectory(File dir){
        if (dir.isDirectory()) {
            if (dir.list().length == 0) {
                dir.delete();
                System.out.println("Directory is deleted : " + dir.getAbsolutePath());
            } else {
                String[] children = dir.list();
                for (String temp : children) {
                    File fileDelete = new File(dir, temp);
                    deleteDirectory(fileDelete);
                }
            }
            if(dir.list().length==0){
                dir.delete();
                System.out.println("Directory is deleted : " + dir.getAbsolutePath());
            }
        } else{
            dir.delete();
            System.out.println("File is deleted : " + dir.getAbsolutePath());
        }
    }

}