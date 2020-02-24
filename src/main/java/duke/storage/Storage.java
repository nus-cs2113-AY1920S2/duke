package duke.storage;

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
    
    private static final String T = "T";
    private static final String D = "D";
    private static final String E = "E";
    private static final String MK_DIR_STRING = (getRelativePath() + "\\data").replace("\\", "/");
    private static final String MK_FILE_STRING = (getRelativePath() + "\\data\\duke.txt").replace("\\", "/");
    private static final String PIPE_REGEX = "\\s+\\|\\s+";
    
    public Storage() {
        try {
            File dir = new File(MK_DIR_STRING);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File f = new File(MK_FILE_STRING);
            if (!f.exists()) {
                f.createNewFile();
            }
            printAndLoadContents();
        } catch (IOException e) {
            System.out.println("Unable to create a file");
        }
    }
    
    //@@author geoO-reused
    //Reused from https://stackoverflow.com/questions/4871051/getting-the-current-working-directory-in-java
    public static String getRelativePath() {
        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();
        return s;
    }
    //@@author geoO-reused
    
    public static void appendToFile(String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(MK_FILE_STRING, true);
        fw.write(textToAppend + System.lineSeparator());
        fw.close();
    }
    
    //@@author Paul Vargas-reused
    //Reused from https://stackoverflow.com/questions/31375972/how-to-replace-a-specific-line-in-a-file-using-java
    //with minor modifications
    public static void modifyFileContent(int lineNumber, String data) throws IOException {
        Path path = Paths.get(MK_FILE_STRING);
        List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
        lines.set(lineNumber, data);
        Files.write(path, lines, StandardCharsets.UTF_8);
    }
    //@@author Paul Vargas-reused
    
    public static void deleteFileContent(int lineNumber) throws IOException {
        Path path = Paths.get(MK_FILE_STRING);
        List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
        lines.remove(lineNumber);
        Files.write(path, lines, StandardCharsets.UTF_8);
    }
    
    public static void printAndLoadContents() throws FileNotFoundException {
        File f = new File(MK_FILE_STRING);
        Scanner s = new Scanner(f);
        
        while (s.hasNextLine()) {
            String input = s.nextLine();
            String[] argumentLine = input.trim().split(PIPE_REGEX);
            System.out.println(input);
            boolean isDone = argumentLine[1].equals("1");
            switch (argumentLine[0]) {
            case T:
                tasks.add(new Todo(argumentLine[2]));
                if (isDone) {
                    tasks.get(tasks.size() - 1).markAsDone();
                }
                break;
            case D:
                tasks.add(new Deadline(argumentLine[2], "/by" + argumentLine[3]));
                if (isDone) {
                    tasks.get(tasks.size() - 1).markAsDone();
                }
                break;
            case E:
                tasks.add(new Event(argumentLine[2], "/at" + argumentLine[3]));
                if (isDone) {
                    tasks.get(tasks.size() - 1).markAsDone();
                }
                break;
            }
        }
    }
    
}
