package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Data {
    public static ArrayList<Todo> todos;




    // Import data from file into program
    public Data(String path) throws DateTimeParseException, IOException, FileNotFoundException {
        todos = new ArrayList<Todo>();
        final Parser dataParser = new Parser();

        try {
            File f = new File(path);
            if (f.createNewFile()) {
                System.out.println("data.txt is not found. It has been generated for you.");
            } else {
                System.out.println("data.txt is found.");
            }
            // Adapted from https://nus-cs2113-ay1920s2.github.io/website/schedule/week6/topics.html
            Scanner s = new Scanner(f);
            int i = 1;
            while (s.hasNext()) {
                String line = s.nextLine();
                String[] buffer = line.split(Ui.SPACE_SYMBOL);
                String taskType = buffer[0];
                String[] subBuffer;
                switch (taskType) {
                    case Ui.COMMAND_TODO:
                        subBuffer = line.split(Ui.COMMAND_TODO + Ui.SPACE_SYMBOL);
                        todos.add(new Todo(subBuffer[1]));
                        break;
                    case Ui.COMMAND_DEADLINE:
                        subBuffer = line.split(Ui.DEADLINE_SYMBOL);
                        todos.add(new Deadline(subBuffer[0].substring(9), subBuffer[1]));
                        break;
                    case Ui.COMMAND_EVENT:
                        subBuffer = line.split(Ui.EVENT_SYMBOL);
                        todos.add(new Event(subBuffer[0].substring(6), subBuffer[1]));
                        break;
                    default:
                        break;
                }
                boolean isDone = Boolean.parseBoolean(s.nextLine());
                if (isDone) {
                    setDone(i);
                }
                i++;
            }
        } catch (Exception e) {

        }


    }

    public void saveToFile() throws IOException {
        FileWriter fw = new FileWriter("data.txt");
        for (Todo todo : todos) {
            String description = todo.getDescription();
            boolean isDone = todo.isItDone();
            char taskType = todo.getTaskType();
            String toBeWritten = null;
            String at = null;
            String by = null;
            switch (taskType) {
                case 'T':
                    toBeWritten = Ui.COMMAND_TODO + Ui.SPACE_SYMBOL + description;
                    break;
                case 'E':
                    Event e = (Event) todo;
                    toBeWritten = Ui.COMMAND_EVENT + Ui.SPACE_SYMBOL + description + Ui.EVENT_SYMBOL + e.getAt() ;
                    break;
                case 'D':
                    Deadline d = (Deadline) todo;
                    toBeWritten = Ui.COMMAND_DEADLINE + Ui.SPACE_SYMBOL + description + Ui.DEADLINE_SYMBOL + d.getBy(); ;
                    break;
                default:
                    break;
            }
            fw.write(toBeWritten);
            fw.write("\n" + isDone + "\n");
        }
        fw.close();
    }


    public void add(Todo todo) {
        todos.add(todo);
    }

    public void setDone(int i) {
        todos.get(i-1).setDone();
    }

    public Todo getLastTask() {
        return todos.get(todos.size()-1);
    }

    public int getSize() {
        return todos.size();
    }

    public void printList() {
        Ui.printBreak();
        Ui.printListTitle();
        int i = 1;
        for (Todo todo : todos) {
            System.out.println("    ".concat(Integer.toString(i).concat(". ").concat(todo.toString())));
            i++;
        }
    }

    public void printItem(int index) {
        System.out.println("      " + todos.get(index-1));
    }

    public Todo removeItem(int index) {
        Todo temp = todos.get(index-1);
        todos.remove(index-1);
        return temp;
    }
}