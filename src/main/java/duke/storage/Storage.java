package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A class that deals with File I/O operations. Can write string to file and
 * read a file in as an <code>ArrayList</code> of <code>Strings</code>
 */
public class Storage {
    /**
     * Save string to file. Creates file if it does not exist.
     * @param filePath location of file to save to. Assumes the format directory/filename
     * @param message message to be saved to file
     * @throws IOException if saving to file fails or is interrupted
     */
    public static void saveToFile(String filePath, String message) throws IOException {
        File d = new File(filePath.substring(0, filePath.indexOf("/")));
        if (!d.exists()) {
            d.mkdir();
        }

        File f = new File(filePath);
        if (!f.exists()) {
            f.createNewFile();
        }

        FileWriter fw = new FileWriter(filePath);
        fw.write(message);
        fw.close();
    }

    /**
     * Read in file as an <code>ArrayList</code> of <code>Strings</code>
     * @param filePath location of file to read from
     * @return <code>ArrayList</code> of each line read from the file
     * @throws FileNotFoundException if the file specified by <code>filePath</code> does not exist
     */
    public static ArrayList<String> loadFromFile(String filePath) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        ArrayList<String> fileContents = new ArrayList<>();

        while (s.hasNext()) {
            fileContents.add(s.nextLine());
        }

        return fileContents;
    }
}
