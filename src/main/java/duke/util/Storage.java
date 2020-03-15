package duke.util;

import duke.taskmanager.Task;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private static Path path = Paths.get("data/myTasks.txt");

    public Storage() {
    }
    /**
     * Write the current task list to designated file
     * path "data/myTasks.txt". If the file exists, rewrite
     * the content. If the file does not exist, create and
     * write to the new file.
     * @param  taskList    the current task list
     * @throws IOException when writing the task list
     *                     to the file at designated
     *                     path fails
     */
    public static void writeData(List<Task> taskList) throws IOException {
        FileWriter fw = new FileWriter(String.valueOf(path));
        List<String> store = new ArrayList<>();
        for (Task temp : taskList) {
            store.add(temp.contentToFile());
        }
        for(String str: store) {
            fw.write(str + System.lineSeparator());
        }
        fw.close();
    }
}

