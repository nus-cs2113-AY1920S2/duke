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

    String dir = System.getProperty("user.dir");
    Path filepath = Paths.get(dir, "data", "taskList.txt");
    String filepathStr  = String.valueOf(filepath);;
    File dukeFile = new File(filepathStr);

    public ArrayList<Task> loadDuke() throws DukeException {
        ArrayList<Task> loadTasks = new ArrayList<>();
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

                loadTasks = (ArrayList<Task>) objRead.readObject();
                objRead.close();
            }
            catch (IOException | ClassNotFoundException e) {
                throw new DukeException("Load error");
            }
        }
        return loadTasks;
    }

    public void saveDuke(ArrayList<Task> taskArrList) throws DukeException {
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

}
