import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

import task.Task;
import exception.*;
import exception.*;

/**
 * A bot that helps users to manage their tasks.
 * It has a UI, a storage file and a task list.
 */

public class Duke {

    private static Storage myStorage;
    private static TaskList myTasks;
    private static UI myUI;


    /**
     * Class constructor of the Duke class.
     * Creates user's UI, storage file and task list.
     *
     * @param f  file object that is used as storage file
     *           to write and load tasks
     */
    public Duke(File f) {
        myUI = new UI();
        myStorage = new Storage(f);
        try {
            myTasks = new TaskList(myStorage.loadFile());
        } catch (FileNotFoundException e) {
            myUI.printIOExceptionMessage();
        }
    }

    /**
     * Main running loop of the bot that takes in user input and
     * handles the command.
     * Loops ends with the command "bye".
     */
    public void run(){

        myUI.printIntroMessage();

        Scanner in = new Scanner(System.in);
        String line = in.nextLine();

        while (!line.equals("bye")){
            String[] words = Parser.splitFirstWord(line);
            try {
                ArrayList<Task> tasksInList = myTasks.getTaskList();
                Command.handleCommand(line, words, tasksInList);
                ArrayList<Task> tasksToWrite = myTasks.getTaskList();
                myStorage.writeToFile(tasksToWrite);
            } catch (EmptyTaskListException e) {
                myUI.printEmptyTaskListExceptionMessage();
            } catch (EmptyDoneException e) {
                myUI.printEmptyDoneExceptionMessage();
            } catch (EmptyDeleteException e) {
                myUI.printEmptyDeleteExceptionMessage();
            } catch (EmptyFindException e) {
                myUI.printEmptyFindExceptionMessage();
            } catch (UnknownWordException e) {
                myUI.printUnknownWordExceptionMessage();
            } catch (EmptyTodoException e) {
                myUI.printEmptyTodoExceptionMessage();
            } catch (EmptyDeadlineException e) {
                myUI.printEmptyDeadlineExceptionMessage();
            } catch (EmptyEventException e) {
                myUI.printEmptyEventExceptionMessage();
            } catch (IndexOutOfBoundsException e) {
                myUI.printIndexOutOfBoundsExceptionMessage();
            } catch (IOException e){
                myUI.printIOExceptionMessage();
            }
            line = in.nextLine();
        }
        myUI.printGoodbyeMessage();
    }

    /**
     * Creates file path for storage file if it does not exist.
     * Calls the Duke class constructor to create a bot, then run it.
     */
    public static void main(String[] args) {
        String folderName = "data";
        String fileName = "duke.txt";
        String filePath = folderName + "/" + fileName;
        File storageFile = new File(filePath);
        File directory = new File(folderName);
        if (!directory.exists()) {
            directory.mkdir();
        }
        try {
            if (!storageFile.exists()) {
                storageFile.createNewFile();
            }
        }
        catch (IOException e){
            myUI.printIOExceptionMessage();
        }
        new Duke(storageFile).run();
    }

}
