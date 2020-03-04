import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    public static void createNewFolder(String pathname) {
        File newFolder = new File(pathname);
        try {
            newFolder.mkdirs();
        } catch (SecurityException e){
            System.out.print("The memory folder could not be created due to security reasons, please relocate the Duke.jar file");
        }
    }

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

    public static void loadFile(String pathname, ArrayList<Task> tasks) {
        File saveState = new File(pathname);
        try {
            Scanner fileLoader = new Scanner(saveState);
            while (fileLoader.hasNextLine()) {
                Parser data = new Parser(fileLoader.nextLine());
                data.readFile(tasks);
            }
        } catch (FileNotFoundException e){
            System.out.print("No memory file found, please restart Duke");
        }
    }

    public static void saveFile(String pathname, ArrayList<Task> tasks) {
        try {
            FileWriter saveState = new FileWriter(pathname);
            Parser contentsToSave = new Parser("");
            contentsToSave.writeToFile(tasks);
            saveState.write(contentsToSave.input);
            saveState.close();
        } catch(IOException e) {
            System.out.print("Connection error");
        }
    }
}