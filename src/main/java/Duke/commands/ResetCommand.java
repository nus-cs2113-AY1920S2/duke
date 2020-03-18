package duke.commands;

import duke.storage.Storage;
import duke.taskList.TaskList;
import duke.taskManager.Task;
import duke.ui.DisplayUI;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import static duke.Duke.FILE_PATH;

/**
 * Reset command to delete all tasks stored and exit program
 */
public class ResetCommand implements Command {

    /**
     * This command will delete all stored tasks from tasklist.txt and exit the program once completed.
     *
     * @param function String containing "reset"
     * @param ui       ui object for printing statements
     * @param storage  Storage object for accessing and modifying tasklist.txt
     * @param taskList Array of tasks stored (Not in use currently)
     * @param tasks    Array of tasks stored (In Use)
     * @return boolean false to main function
     * * @throws IOException When there's error in closing filestream and filewriter
     */
    public boolean execute(String function, DisplayUI ui, Storage storage, TaskList taskList, ArrayList<Task> tasks) {
        FileInputStream fi = null;
        FileOutputStream fio = null;
        try {
            fi = new FileInputStream("taskList.txt");
            fio = new FileOutputStream("tasklist.txt");
        } catch (IOException e) {
            System.out.println("Error Closing File");
        } finally {
            try {
                fi.close();
                fio.close();
            } catch (IOException e) {
                ui.printToUser("IO EXCEPTION! Pls try again");
            }
        }
        deleteDirectory(new File("data"));
        return false;
    }

    private static void deleteDirectory(File dir) {
        boolean flag = false;
        if (dir.isDirectory()) {
            if (dir.list().length == 0) {
                dir.delete();
                System.out.println("Deleting folder : " + dir.getAbsolutePath());
            } else {
                File[] children = dir.listFiles();
                for (File temp : children) {
                    deleteDirectory(temp);
                }
            }
        } else {
            if(dir.delete()){
                flag = true;
                System.out.println(flag);
            }
            System.out.println("Deleting File  : " + dir.getAbsolutePath());
        }
        if (dir.isDirectory()) {
            dir.delete();
            System.out.println("Deleting folder : " + dir.getAbsolutePath());
            System.out.println(flag);
        }
    }
}
