import exceptions.DukeException;
import tasks.Task;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/*
Deals with loading tasks from the file and saving tasks in the file
 */

public class Storage {

    private static String dir = System.getProperty("user.dir");
    private static Path filepath = Paths.get(dir, "data", "taskList.txt");
    private static String filepathStr  = String.valueOf(filepath);;
    private static File dukeFile = new File(filepathStr);

    protected static void saveDuke(ArrayList<Task> taskArrList) throws DukeException {
        try{
            // File stream to write to file
            FileOutputStream fileWrite = new FileOutputStream(dukeFile);
            // Object stream to be write object to file stream
            ObjectOutputStream objWrite = new ObjectOutputStream(fileWrite);

            // write the ArrayList into the file
            objWrite.writeObject(taskArrList);
            objWrite.flush();
            objWrite.close();
        }
        catch (IOException e) {
            throw new DukeException("Save error");
        }
    }

    protected static ArrayList<Task> loadDuke(ArrayList<Task> taskArrList) throws DukeException {
        //first load of program
        if(!dukeFile.exists()) {
            try {
                // creates all sub dir if not exist
                dukeFile.getParentFile().mkdirs();
                dukeFile.createNewFile();
            }
            catch (IOException e){
                throw new DukeException("File creation error");
            }
        }
        else {
            try{
                FileInputStream fileRead = new FileInputStream(dukeFile);
                ObjectInputStream objRead = new ObjectInputStream(fileRead);

                taskArrList = (ArrayList<Task>) objRead.readObject();
                objRead.close();
            }
            catch (IOException | ClassNotFoundException e) {
                throw new DukeException("Load error");
            }
        }
        return taskArrList;
    }

}
