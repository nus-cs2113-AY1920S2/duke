package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

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
            for (int i = 0; i < Duke.sizeOfList; i++) {
                String currentListLine = Duke.listOfTasks.get(i).toString();
                String typeOfTask = currentListLine.substring(1, 2);
                String markedOrUnmarked = Duke.listOfTasks.get(i).getStatusIcon();
                String description = Duke.listOfTasks.get(i).getDescription();
                String extra = Duke.listOfTasks.get(i).getExtra();
                String textToAppend;

                if (extra == null) { // if it does not have a /by or /at (it is a todo)
                    textToAppend = typeOfTask + " | " + markedOrUnmarked + " | " + description;
                } else { // it is a deadline or event
                    textToAppend = typeOfTask + " | " + markedOrUnmarked + " | " + description + " | " + extra;
                }

                appendToFile(filePath, textToAppend + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println(Constants.ioErrorMessage);
        }
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
     * Adds the items in the existing list in
     * the file to system so that changes can
     * be made to existing list
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
                String[] itemInLine = lineInList.split(" \\| ");

                if (itemInLine[0].contains("T")) { // add task as todo into list
                    Todo newTodo = new Todo(itemInLine[2]);
                    Duke.listOfTasks.add(newTodo);
                    if (itemInLine[1].contains("1")) {
                        Duke.listOfTasks.get(Duke.sizeOfList).markAsDone();
                    }
                    Duke.sizeOfList++;

                } else if (itemInLine[0].contains("D")) { // add task as deadline into list
                    Deadline newDeadline = new Deadline(itemInLine[2], itemInLine[3]);
                    Duke.listOfTasks.add(newDeadline);
                    if (itemInLine[1].contains("1")) {
                        Duke.listOfTasks.get(Duke.sizeOfList).markAsDone();
                    }
                    Duke.sizeOfList++;

                } else { // add task as event into list
                    Event newEvent = new Event(itemInLine[2], itemInLine[3]);
                    Duke.listOfTasks.add(newEvent);
                    if (itemInLine[1].contains("1")) {
                        Duke.listOfTasks.get(Duke.sizeOfList).markAsDone();
                    }
                    Duke.sizeOfList++;

                }
            }
        }
    }
}
