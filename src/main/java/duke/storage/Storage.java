package duke.storage;

import duke.tasklist.TaskList;
import duke.exceptions.ChatboxException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private String dirPath;
    private String fileName;
    private String filePath;
    
    private List<Task> fileTasks = new ArrayList<>();
    
    public Storage(String dirPath, String fileName) {
        this.dirPath = dirPath;
        this.fileName = fileName;
        this.filePath = dirPath + File.separator + fileName;
    }

    public List<Task> loadFileContents() throws IOException, ChatboxException {
        if (dirPath != "") {
            File directory = new File(dirPath);
            if (!directory.exists()) { // create the folder
                directory.mkdir();
            }
        }

        File f = new File(filePath); // create a File for the given file path
        if (!f.exists()) {
            f.createNewFile();
        }
        
        Scanner s = new Scanner(f); // create a Scanner using the File as the source

        
        if (!s.hasNext()) { // empty list
            throw new ChatboxException();
        }

        System.out.println("Tasks on the list: ");

        while (s.hasNext()) {
            String taskLine = s.nextLine();
            System.out.println(taskLine);
            saveStringtoTask(taskLine);
        }
        
        return fileTasks;
    }

    public void saveStringtoTask(String fileLine) {
        String[] arrays = fileLine.split("\\|");
        String type = arrays[0].trim();
        String isDone = arrays[1].trim();
        String description = arrays[2].trim();
        switch (type) {
        case "T":
            Task todo = new Todo(description);
            if (isDone == "1") {
                todo.markAsDone();
            }
            fileTasks.add(todo);
            break;
        case "D":
            String by = arrays[3].trim();
            Task deadline = new Deadline(description, by);
            if (isDone == "1") {
                deadline.markAsDone();
            }
            fileTasks.add(deadline);
            break;
        case "E":
            String at = arrays[3].trim();
            Task event = new Event(description, at);
            if (isDone == "1") {
                event.markAsDone();
            }
            fileTasks.add(event);
            break;
        }
    }

    public void updateTasksToFile(TaskList taskList) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (int i = 1; i <= taskList.getListSize(); i++) {
            fw.write(taskList.getTask(i).getFileString() + System.lineSeparator());
        }
        fw.close();
    }
}
