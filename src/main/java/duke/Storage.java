package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


/**
 * deals with loading tasks from the file and saving tasks in the file
 * Happens before and after the program;
 * loads from file before user starts entering their commands,
 * then saves tasks into file when user exits from program
 */
public class Storage {

    /**
     * saves the current list offline into duke.txt file
     */
    public static void saveListOffline(String filePath) {
        try {
            File f = new File(filePath);
            if (!f.exists()) {
                f.createNewFile();
            }

            writeToFile(filePath); // overwrite old contents
            for (int i = 0; i < TaskList.sizeOfList; i++) {
                String textToAppend = getTextToAppend(i);
                appendToFile(filePath, textToAppend);
            }
        } catch (IOException e) {
            System.out.println(Constants.ioErrorMessage);
        }
    }


    /**
     * gets task from current list and formats it to
     * be a line of text to add into the offline file
     *
     * @param i index of task to add
     * @return the line of task to add offline
     */
    public static String getTextToAppend(int i) {
        String currentListLine = TaskList.listOfTasks.get(i).toString();
        String typeOfTask = currentListLine.substring(1, 2);
        String markedOrUnmarked = TaskList.listOfTasks.get(i).getStatusIcon();
        String description = TaskList.listOfTasks.get(i).getDescription();
        String extra = TaskList.listOfTasks.get(i).getExtra();
        String textToAppend;

        if (extra == null) { // if it does not have a /by or /at (it is a todo)
            textToAppend = typeOfTask + " | " + markedOrUnmarked + " | " + description;
        } else { // it is a deadline or event
            textToAppend = typeOfTask + " | " + markedOrUnmarked + " | " + description + " | " + extra;
        }
        return textToAppend + System.lineSeparator();
    }


    /**
     * appends the given text into the file duke.txt
     *
     * @param textToAppend the text to be added into file
     * @throws IOException if file duke.txt is not found
     */
    public static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAppend);
        fw.close();
    }


    /**
     * overwrites the file duke.txt each time there are changes to the list
     *
     * @throws IOException if file duke.txt is not found
     */
    public static void writeToFile(String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write("");
        fw.close();
    }


    /**
     * Adds existing list in the file to system so
     * that changes can be made to existing list
     *
     * @throws IOException if error in reading from file
     */
    public static void addExistingList(String filePath) throws IOException {
        File f = new File(filePath);
        if (!f.exists()) {
            f.createNewFile();
        } else { // if file does exist, add into current list
            Scanner scan = new Scanner(f);
            while (scan.hasNext()) {
                String lineInList = scan.nextLine();
                addExistingTask(lineInList);
            }
        }
    }


    /**
     * Add current task in existing list into list
     *
     * @param lineInList task in text formatted inside offline list
     */
    public static void addExistingTask(String lineInList) {
        String[] itemInLine = lineInList.split(" \\| ");

        if (itemInLine[0].contains("T")) { // add task as todo into list
            Todo newTodo = new Todo(itemInLine[2]);
            TaskList.listOfTasks.add(newTodo);
            if (itemInLine[1].contains("1")) {
                TaskList.listOfTasks.get(TaskList.sizeOfList).markAsDone();
            }
            TaskList.sizeOfList++;

        } else if (itemInLine[0].contains("D")) { // add task as deadline into list
            Deadline newDeadline = new Deadline(itemInLine[2], itemInLine[3]);
            TaskList.listOfTasks.add(newDeadline);
            if (itemInLine[1].contains("1")) {
                TaskList.listOfTasks.get(TaskList.sizeOfList).markAsDone();
            }
            TaskList.sizeOfList++;

        } else { // add task as event into list
            Event newEvent = new Event(itemInLine[2], itemInLine[3]);
            TaskList.listOfTasks.add(newEvent);
            if (itemInLine[1].contains("1")) {
                TaskList.listOfTasks.get(TaskList.sizeOfList).markAsDone();
            }
            TaskList.sizeOfList++;

        }
    }
}
