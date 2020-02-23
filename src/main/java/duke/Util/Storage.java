package duke.Util;

import duke.taskmanager.Tasks;

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

    public static void writeData(List<Tasks> taskList) throws IOException {
        FileWriter fw = new FileWriter(String.valueOf(path));
        List<String> store = new ArrayList<>();
        for (Tasks temp : taskList) {
            store.add(temp.contentToFile());
        }
        for(String str: store) {
            fw.write(str + System.lineSeparator());
        }
        fw.close();
    }
}

