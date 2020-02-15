package duke;

import duke.task.Task;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.List;

import static duke.Duke.tasks;

import duke.task.Todo;
import duke.task.Deadline;
import duke.task.Event;

public class Storage {
    
    private static final String PIPE = "|";
    private static final String T = "T";
    private static final String D = "D";
    private static final String E = "E";
    
    
    public Storage(String filePath) {
        try {
            File f = new File(filePath);
            if (!f.exists()) {
                f.createNewFile();
            }
            printAndLoadContents(filePath);
        } catch (IOException e) {
            System.out.println("Unable to create a file");
        }
    }
    
    protected static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAppend + System.lineSeparator());
        fw.close();
    }
    
    //@@author Paul Vargas-reused
    //Reused from https://stackoverflow.com/questions/31375972/how-to-replace-a-specific-line-in-a-file-using-java
    //with minor modifications
    protected static void modifyFile(String filePath, int lineNumber, String data) throws IOException {
        Path path = Paths.get(filePath);
        List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
        lines.set(lineNumber, data);
        Files.write(path, lines, StandardCharsets.UTF_8);
    }
    //@@author Paul Vargas-reused
    
    protected static void printAndLoadContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        
        while (s.hasNextLine()) {
            String input = s.toString();
            String[] arguments = input.split(PIPE);
            System.out.println(s.nextLine());
            
            switch (arguments[0]) {
            case T:
                tasks[Task.getTaskCount()] = new Todo(arguments[2]);
                if (Boolean.parseBoolean(arguments[1])) {
                    tasks[Task.getTaskCount() - 1].markAsDone();
                }
                break;
            case D:
                tasks[Task.getTaskCount()] = new Deadline(arguments[2], "/by" + arguments[3]);
                if (Boolean.parseBoolean(arguments[1])) {
                    tasks[Task.getTaskCount() - 1].markAsDone();
                }
                break;
            case E:
                tasks[Task.getTaskCount()] = new Event(arguments[2], "/at" + arguments[3]);
                if (Boolean.parseBoolean(arguments[1])) {
                    tasks[Task.getTaskCount() - 1].markAsDone();
                }
                break;
            }
        }
    }
}
