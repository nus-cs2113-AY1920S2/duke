package duke.Util;

import duke.taskmanager.Tasks;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Storage {
    private static Path path = Paths.get("data/myTasks.txt");

    public Storage() {
    }

    public static void writeData(List<Tasks> taskList) {
        for (Tasks temp : taskList) {
            String store = temp.contentToFile();
            try {
                writeToFile(store+System.lineSeparator());
            } catch (IOException e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }
        }
    }
    private static void writeToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(String.valueOf(path));
        fw.write(textToAdd);
        fw.close();
    }
}
