package Duke;

import Exceptions.NoParameterException;
import Exceptions.emptyListException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

    /*
    List of exceptions handled:

    1. General commands
        a. No recognised command given
        b. No follow up parameters in command

    2. Done command
        a. Out of range
        b. Not integer

    3. Load from data.txt
        a. Not exist
        b. Error reading / writing

     */


public class Duke {

    public static final int LENGTH_DEADLINE = 9;
    public static final int LENGTH_EVENT = 6;
    public static final int LENGTH_TODO = 5;
    public static final int SIZE_DONE_COMMAND = 2;
    public static final String WORKING_DIRECTORY = System.getProperty("user.dir");
    public static final java.nio.file.Path FOLDER_PATH = java.nio.file.Paths.get(WORKING_DIRECTORY, "Duke");
    public static final java.nio.file.Path FILE_PATH = java.nio.file.Paths.get(WORKING_DIRECTORY, "Duke", "data.txt");
    public static final int SIZE_DELETE_COMMAND = 2;
    public static final int LIST_TO_INDEX = 1;


    public static void getDateTime() {
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedDate = myDateObj.format(myFormatObj);
        System.out.println(formattedDate);
    }






    public static void main(String[] args) {

        printWelcomeMessage();
        checkFolderPath();
        ArrayList<Task> tasks = new ArrayList<Task>();
        if (checkFileExists()) {
            populateList(tasks);
        }

        runCommandLoop(tasks);
    }

    private static void runCommandLoop(ArrayList<Task> tasks) {
        Scanner input = new Scanner(System.in);
        String userCommand = input.nextLine();

    }
}
