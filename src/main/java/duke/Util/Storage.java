package duke;

import duke.taskmanager.TaskManager;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private static boolean fileExists;
    private static Path path;

    public Storage() {
        path = Paths.get("data/myTasks.txt");
        fileExists = Files.isRegularFile(path);
    }

    boolean getFileExits() {
        return fileExists;
    }

    private static void setFileExists() {
        fileExists = Files.isRegularFile(path);
    }

    public static void writeData(List<TaskManager> taskList) {
        List<String> store = new ArrayList<>();
        for (TaskManager temp : taskList) {
            store.add(temp.contentToFile());
        }
        try {
            if (!fileExists) {
                Files.createDirectories(path.getParent());
                Files.createFile(path);
                setFileExists();
            }
            Files.write(path, store, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
