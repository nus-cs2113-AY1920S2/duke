import java.io.File;
import java.util.List;
import java.util.Scanner;

public class FileLoader {

    public static void loadFile(Storage myTasks, File f) {
        try  (Scanner s = new Scanner(f)){

            while (s.hasNext()) {
                String sentence = s.nextLine();
                List<String> parsedSentence = Parser.parseFile(sentence);

                String taskType = parsedSentence.get(0); //TODO get rid of magic numbers
                String description = parsedSentence.get(2); //TODO get rid of magic numbers
                int isDone = Integer.parseInt(parsedSentence.get(1)); //TODO get rid of magic numbers

                switch (taskType) {
                case "T":
                    ToDo toDoTask = new ToDo(isDone, description);
                    myTasks.storeTasks(toDoTask);
                    continue;

                case "D":
                    String dateline = parsedSentence.get(3); // TODO get rid of magic numbers
                    Deadline deadlineTask = new Deadline(isDone, description, dateline);
                    myTasks.storeTasks(deadlineTask);
                    continue;

                case "E":
                    String EventAt = parsedSentence.get(3); //TODO get rid of magic numbers
                    Event eventTask = new Event(isDone, description, EventAt);
                    myTasks.storeTasks(eventTask);
                    continue;

                default:
                    throw new Exception(); //TODO throw custom exception
                }
            }

        } catch (Exception e) {
            System.out.print("Save file corrupted, please check your file"); //TODO shift print statement to printer
            System.exit(1);
        }
    }
}
