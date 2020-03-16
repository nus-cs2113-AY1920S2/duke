package storage;

import task.Task;
import parser.ParserFile;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    /**
     * Create a directory to contain the data file if no memory file is found when Duke starts
     * @param pathname Location of the new directory
     */
    public static void createNewFolder(String pathname) {
        File newFolder = new File(pathname);
        try {
            newFolder.mkdirs();
        } catch (SecurityException e){
            System.out.print("The memory folder could not be created due to security reasons, please relocate the Duke.jar file");
        }
    }

    /**
     * Create a new file if no memory file is found when Duke starts
     * @param pathname Location of the memory file
     */
    public static void createNewFile(String pathname) {
        File newFile = new File(pathname);
        try {
            newFile.createNewFile();
        } catch (SecurityException e){
            System.out.print("The memory file could not be created due to security reasons, please relocate the Duke.jar file");
        } catch (IOException e) {
            System.out.print("The memory file could not be created due to connection reasons, please restart the Duke.jar file");
        }
    }

    /**
     * Load any existing memory when Duke starts into the task ArrayList
     * @param pathname Location of the memory file
     * @param tasks ArrayList in Duke that stores all the tasks
     */
    public static void loadFile(String pathname, ArrayList<Task> tasks) {
        File saveState = new File(pathname);
        try {
            Scanner fileLoader = new Scanner(saveState);
            while (fileLoader.hasNextLine()) {
                ParserFile data = new ParserFile(fileLoader.nextLine());
                data.parseInputFromFile(tasks);
            }
        } catch (FileNotFoundException e){
            System.out.print("No memory file found, please restart Duke");
        }
    }

    /**
     * Write the data in the task ArrayList into the memory file
     * @param pathname Location of the memory file
     * @param tasks ArrayList in Duke that stores all the tasks
     */
    public static void saveFile(String pathname, ArrayList<Task> tasks) {
        try {
            FileWriter saveState = new FileWriter(pathname);
            ParserFile contentsToSave = new ParserFile("");
            contentsToSave.parseOutputToFile(tasks);
            saveState.write(contentsToSave.getInput());
            saveState.close();
        } catch(IOException e) {
            System.out.print("Connection error");
        }
    }
}