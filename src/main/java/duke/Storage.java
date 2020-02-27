package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private ArrayList<Task> tasks = new ArrayList<>();
    private String filePath;

    public ArrayList<Task> load(Ui ui) throws DukeException {
        String description;
        int dividerIndex;

        File dir = new File("data");
        if (!dir.exists()) {
            ui.printLine(Ui.MAKING_DIRECTORY);
            dir.mkdir();
        }
        File file = new File("data/duke.txt");
        if (!file.exists()) {
            ui.printLine(Ui.CREATING_FILE);
        }
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new DukeException("File could not be created.");
        }

        ui.printLine(Ui.LOADING_TASKS);
        Scanner s;
        try {
            s = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new DukeException("File not found.");
        }
        while (s.hasNext()) {
            String line = s.nextLine();
            switch (line.charAt(1)) {
            case 'T':
                description = line.substring(7);
                tasks.add(new ToDo(description));
                break;
            case 'D':
                dividerIndex = line.indexOf("(by");
                description = line.substring(7, (dividerIndex - 1));
                String by = line.substring(dividerIndex + 5, line.length() - 1);
                tasks.add(new Deadline(description, by));
                break;
            case 'E':
                dividerIndex = line.indexOf("(at");
                description = line.substring(7, (dividerIndex - 1));
                String at = line.substring(dividerIndex + 5, line.length() - 1);
                tasks.add(new Event(description, at));
                break;
            default:
                break;
            }
            if (line.charAt(4) == '1') {
                tasks.get(tasks.size() - 1).markAsDone();
            }
        }
        ui.printLine(Ui.LOADING_DONE);

        return tasks;
    }

    public void saveTaskstoDisk(Ui ui, TaskList taskList) {
        try {
            FileWriter fw = new FileWriter("data/duke.txt");
            for (Task task : taskList.getTaskList()) {
                fw.write(task.toString() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            ui.printLine(Ui.SAVE_ERROR);
        }
    }
}
