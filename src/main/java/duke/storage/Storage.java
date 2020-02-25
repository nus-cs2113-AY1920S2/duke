package duke.storage;

import duke.commands.Command;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    File f;

    public Storage (String filePath) throws FileNotFoundException {
        f = new File("data/duke_list.txt");
    }

    public void updateListDataOnDisk(ArrayList<Command> list){
        try {
            Files.createDirectory(Paths.get("data"));
        } catch (IOException ignored) {}
        try {
            Files.createFile(Paths.get("data/duke_list.txt"));
        } catch (IOException ignored) {}
        try {
            FileWriter fw = new FileWriter("data/duke_list.txt");
            list.forEach((n) -> {
                try {
                    fw.write(n.command + System.lineSeparator());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public ArrayList <Command> loadListDataFromDisk() throws FileNotFoundException {
        ArrayList<Command> list = new ArrayList<Command>();
        Scanner reader = new Scanner(f);
        while (reader.hasNext()) {
            list.add(new loader(reader.nextLine()));
        }
        return list;
    }
}
