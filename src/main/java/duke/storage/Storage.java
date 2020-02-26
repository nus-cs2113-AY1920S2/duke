package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
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
