package duke.storage;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;
import duke.tasklist.TaskList;
import duke.task.Tasks;

import java.io.*;
import java.util.ArrayList;

public class Storage {
    private static String filename;

    public Storage(String filename) {
        this.filename = filename;
    }
    public static void writeFile(TaskList list) {
        try {
            BufferedWriter bufferWriter = new BufferedWriter(new FileWriter(filename));
            for (int i = 0; i < list.getTaskCount(); i++) {
                bufferWriter.write(list.getTaskList(i) + "\n");
            }
            bufferWriter.close();
        } catch (IOException e){
            System.out.println("File not found");
            e.printStackTrace();
        }

    }
    public ArrayList<Tasks> readFile() throws FileNotFoundException {
        ArrayList<Tasks> list = new ArrayList<>();
        try {
            BufferedReader bufferReader = new BufferedReader(new FileReader(filename));
            String input = bufferReader.readLine();
            int count = 0;
            String status;
            while (input != null) {
                status = input.substring(3, 6);
                if (input.startsWith("[T]")) {
                    input = input.substring(7).trim();
                    Tasks task = new Todo(input);
                    list.add(task);
                    if (status.equals("[\u2713]")) {
                        list.get(count).markAsDone();
                    }
                    count += 1;
                } else if (input.startsWith("[D]")) {
                    input = input.substring(7);
                    String[] splitter = input.split("\\(by:", 2);
                    String remove_bracket = splitter[1].substring(0, splitter[1].length() - 1);
                    Tasks task = new Deadline(splitter[0].trim(), remove_bracket.trim());
                    list.add(task);
                    if (status.equals("[\u2713]")) {
                        list.get(count).markAsDone();
                    }
                    count += 1;
                } else if (input.startsWith("[E]")) {
                    input = input.substring(7);
                    String[] splitter = input.split("\\(at:", 2);
                    String remove_bracket = splitter[1].substring(0, splitter[1].length() - 1);
                    Tasks task = new Event(splitter[0].trim(), remove_bracket.trim());
                    list.add(task);
                    if (status.equals("[\u2713]")) {
                        list.get(count).markAsDone();
                    }
                    count += 1;
                }
                input = bufferReader.readLine();
            }
            bufferReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("There's no file");
            e.printStackTrace();
        }
        return list;
    }
}
