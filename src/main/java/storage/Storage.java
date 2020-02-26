package storage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Optional;

import common.tasks.Deadline;
import common.tasks.Event;
import common.tasks.Task;
import common.tasks.ToDo;
import parser.Parser;
import common.exceptions.DukeException;

public class Storage {
    public ArrayList<Task> list = new ArrayList<>();
    public static int COMMAND_TYPE = 0;
    public static int COMMAND_DESCRIPTION = 1;
    public static int COMMAND_CHECK = 2;
    public static int COMMAND_DATE = 3;
    public static int COMMAND_TIME = 4;
    
    /**
     * Writes all tasks to a text file in the home directory.
     * 
     * @param tasks List of tasks to be written to a text file.
     * @throws IOException
     * @throws DukeException
     */
    public void writeToFile(ArrayList<Task> tasks) throws IOException, DukeException {
        FileWriter fw = new FileWriter("duke.txt");
        if (list.isEmpty()) {
            fw.write("<duke.java> No saved tasks yet! :)");
        } else if (!list.isEmpty()) {
            try {
                fw.write("<duke.java> Saved tasks: (Please do not edit)" + '\n');
                for (Task task : tasks) {
                    fw.write(task.toString() + '\n');
                }
            } catch (IOException e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }
        } else {
            fw.close();
            throw new DukeException("Oops!! Something went wrong.");
        }
        fw.close();
    }
    
    /**
     * Reads and import stored tasks from a text file in the home directory.
     * The text file has to be named "duke.txt".
     * 
     * @throws IOException
     * @throws DukeException
     */
    public void readFromFile() throws IOException, DukeException {
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader("duke.txt"));
            br.readLine();
        } catch (Exception e) {
            return;
        }
        String input;
        try {
            while ((input = br.readLine()) != null) {
                Parser parser = new Parser();
                boolean isDone = input.charAt(4) == 'Y';
                String[] commandParts = parser.parseFromStorage(input);
                String commandType = commandParts[COMMAND_TYPE];
                String commandDescription = commandParts[COMMAND_DESCRIPTION];
                Optional<LocalDate> date = parser.parseDate(commandParts[COMMAND_DATE]);
                Optional<LocalTime> time = parser.parseTime(commandParts[COMMAND_TIME]);
                switch (commandType) {
                    case "T":
                        list.add(new ToDo(commandDescription, isDone));
                        break;
                    case "D":
                        list.add(new Deadline(commandDescription, date, time, isDone));
                        break;
                    case "E":
                        list.add(new Event(commandDescription, date, time, isDone));
                        break;
                }
            }
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
    
}
