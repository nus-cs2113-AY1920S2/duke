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

public class ResetCommand implements Command {

    public boolean execute(String function, DisplayUI ui, Storage storage, TaskList taskList, ArrayList<Task> tasks) throws IOException {
        FileInputStream fi = null;
        FileOutputStream fio = null;
        try {
            fi = new FileInputStream("data/Tasklist.txt");
            fio = new FileOutputStream("data/Tasklist.txt");
        } catch (IOException e) {
            System.out.println("Error Closing File");
        } finally {
            fi.close();
            fio.close();
        }
        deleteDirectory(new File("data"));
        return false;
    }

    private static void deleteDirectory(File dir) {
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
            if (dir.list().length == 0) {
                dir.delete();
                System.out.println("Deleting folder : " + dir.getAbsolutePath());
            } else {
                dir.delete();
                System.out.println("Deleting File  : " + dir.getAbsolutePath());
            }
        }
    }
}
