package Duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    public static int loadFileContents(String filePath, ArrayList<Task> taskArrayList) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        int taskListSize = 0;
        while (s.hasNext()) {
            String newLine = s.nextLine();
            String[] tokenizedLine = newLine.split("\\|");
            String type = tokenizedLine[2];
            switch (type) {
            case ("T"):
                taskArrayList.add(new ToDo(tokenizedLine[1], tokenizedLine[0]));
                taskListSize++;
                break;
            case ("E"):
                taskArrayList.add(new Event(tokenizedLine[1], tokenizedLine[3], tokenizedLine[0]));
                taskListSize++;
                break;
            case ("D"):
                taskArrayList.add(new Deadline(tokenizedLine[1], tokenizedLine[3], tokenizedLine[0]));
                taskListSize++;
                break;
            }
        }
        System.out.println("Previous tasks has been loaded successfully:");
        return taskListSize;
    }

    static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

    public static void saveTasks(String filePath, ArrayList<Task> taskArrayList, boolean overWrite) {
        for (Task currTask : taskArrayList) {
            if(overWrite == true) {
                try {
                    Storage.writeToFile(filePath, currTask.toSaveFormat() + "\n");
                } catch (IOException e) {
                    System.out.println("Something went wrong: " + e.getMessage());
                }
                overWrite = false;
            } else {
                try {
                    Storage.appendToFile(filePath, currTask.toSaveFormat() + "\n");
                } catch (IOException e) {
                    System.out.println("Something went wrong: " + e.getMessage() + "\n");
                }
            }
        }
    }
}