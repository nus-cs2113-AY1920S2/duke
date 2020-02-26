package Duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private String filePath;
    private static Ui ui;

    public Storage(String filePath){
        this.filePath = filePath;
    }

    public static List<Task> load() throws DukeException{
        try{
            List<Task> tasks = new ArrayList<>();
            File file = new File("data/duke.txt");
            Scanner scanner = new Scanner(file);
            if(!scanner.hasNext()){
                throw new DukeException("Task List is empty.");
            }
            while(scanner.hasNext()){
                String[] line = scanner.nextLine().split(" \\| ");
                boolean isDone = false;
                if (line[1].equals("1")){
                    isDone = true;
                }
                switch (line[0]){
                case "D":
                    tasks.add(new Deadline(line[2], line[3], isDone));
                    break;
                case "E":
                    tasks.add(new Event(line[2], line[3], isDone));
                    break;
                case "T":
                    tasks.add(new Todo(line[2], isDone));
                    break;
                }
            }
            return tasks;
        } catch (FileNotFoundException e) {
            ui.printMessage("File does not exist");
            return new ArrayList<>();
        } catch (ArrayIndexOutOfBoundsException e){
            ui.printMessage("Invalid file contents");
            return new ArrayList<>();
        }
    }

    public static void writeNewData(TaskList tasks){
        try {
            FileWriter fileWriter = new FileWriter( "data/duke.txt");
            for (Task task : tasks.getTasks()) {
                fileWriter.write(task.toText() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            ui.printMessage("An error occurred while writing to file");
        }
    }

}
