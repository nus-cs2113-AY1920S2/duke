package duke;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
public class Storage {
    protected String fname;
    public Storage(String fname) {
        this.fname = fname;
    }
    public static void writeToFile(String filePath, ArrayList<Task> tasksToWrite) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        try {
            for (int i = 0; i < tasksToWrite.size(); i++) {
                String writtenString = String.format("%d. %s\n", i + 1, tasksToWrite.get(i).toString());
                fw.write(writtenString);
            }
        } catch (IOException e){
            System.out.println("Invalid path");
        }
        fw.close();
    }
    public static ArrayList<String> readFromFile(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        Scanner sc = null;
        ArrayList<String> tasks = new ArrayList<String>();
        try {
            sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String task = sc.nextLine();
            }
        }catch (FileNotFoundException e) {
            System.out.println("File Not Found");
        }
        sc.close();
        return tasks;
        }

        }

    }
}
