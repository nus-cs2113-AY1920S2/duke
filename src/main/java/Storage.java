import exceptions.DukeException;
import tasks.Task;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

//TODO: Possibility of further abstraction

/**
 * <h1>Storage</h1>
 * The Storage class deals with saving and loading the task list to/from
 * the preset file
 */
public class Storage  {

    String dir = System.getProperty("user.dir");
    Path filepath = Paths.get(dir, "data", "taskList.txt");
    String filepathStr  = String.valueOf(filepath);;
    File dukeFile = new File(filepathStr);

    /**
     * Attempts to load a preexisting task list from the preset file.
     * If the file is not found, the file is created and a new ArrayList is created
     * Else, the preexisting task list is loaded into Duke
     * @return the preexising task list, or a new (clean) task list
     * @throws DukeException of IOException if the file cannot be created
     * @throws DukeException of IOException if the file cannot be read
     * @throws DukeException of ClassNotFoundException if the saved class type cannot be identified
     */
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
                throw new DukeException( e+ "\nLoad error");
            }
        }
        return loadTasks;
    }

    /**
     * Saves the current task list to the preset file
     * @param saveTasks the current TaskList
     * @throws DukeException of IOException if the task list cannot be saved
     */
    public void saveDuke(TaskList saveTasks) throws DukeException {
        try{
            FileOutputStream fileWrite = new FileOutputStream(dukeFile);
            ObjectOutputStream objWrite = new ObjectOutputStream(fileWrite);

            objWrite.writeObject(saveTasks.getTaskList());
            objWrite.flush();
            objWrite.close();
        }
        catch (IOException e) {
            throw new DukeException(e+"\nSave error");
        }
    }
}
