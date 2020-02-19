package duke.storage;

import duke.ui.Ui;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Storage {
    public String dataFilePath;
    public File dataFile;

    public Storage(String dataFilePath) {
        this.dataFilePath = dataFilePath;
        dataFile = new File(dataFilePath);
    }

    public void writeToFile(String s) throws IOException {
        FileWriter fw = new FileWriter(dataFilePath, true);
        fw.write(s + System.lineSeparator());
        fw.close();
    }

    // Return true if file previously existed, false if it didn't exist (and it created a new one)
    public boolean loadFile() {

        // Create data file if it does not exist already
        if (!dataFile.exists()) {
            try  {
                createDataFile();
                return false; // false = file didn't previously exist, so it was created
            } catch (IOException e) {
                Ui.formatPrint("Error loading data file.");
            }
        }
        return true; // true = file previously existed, and was not created
    }

    private void createDataFile() throws IOException {
        dataFile.getParentFile().mkdirs(); // Create data directory (does nothing if directory already exists)
        dataFile.createNewFile();
    }


}
