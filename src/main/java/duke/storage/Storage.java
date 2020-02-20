package duke.storage;

import duke.data.TaskList;
import duke.exception.DukeException;
import duke.exception.TestDukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


/**
 * Saves and loads the file of Duke's data.
 */
public class Storage {
    public static final String DEFAULT_FILEPATH = "data/tasks.txt";
    private final String filePath;

    public Storage(){
        this(DEFAULT_FILEPATH);
    }

    public Storage(String filePath){
        this.filePath = filePath;
    }

    /**
     * Loads the file of Duke's data.
     * @return Duke's task list.
     * @throws DukeException if file path is wrong.
     */
    public TaskList load() throws DukeException{
        try {
            return loadFileContents();
        }catch (FileNotFoundException e) {
            throw new DukeException("File Path is wrong!");
        }
    }

    /**
     * Uses scanner to get the data in file and gets the stored task list.
     * @return Duke's task list.
     * @throws FileNotFoundException if file path is wrong.
     */
    public TaskList loadFileContents() throws FileNotFoundException {
        File fileToBeLoad = new File(filePath);
        Scanner s = new Scanner(fileToBeLoad);
        TaskList tasks = new TaskList();
        while (s.hasNext()){
            int indexOfCommand = 3;
            String command = s.nextLine().substring(indexOfCommand).replace("(","/")
                    .replace(")","")
                   ;
            addLoadedTask(command,tasks);
        }
        return tasks;
    }

    /**
     * Translates data into tasks and adds tasks into task list.
     * @param description one single line record in the file.
     * @param tasks the task list
     */
    public void addLoadedTask(String description, TaskList tasks){
        Task newTask;
        String[] words = description.split(" ",3);
        String taskType = words[0];
        String taskStatus = words[1];
        String args = words[2];
        String isDoneIcon = "[\u2713]";

        boolean isToDo = taskType.equals("[T]");
        boolean isDeadline = taskType.equals("[D]");
        boolean isEvent = taskType.equals("[E]");
        boolean isDone = taskStatus.equals(isDoneIcon);

        TestDukeException testDukeException = new TestDukeException(description);
        try {
            if (isToDo) {
                String command = "todo " + args;
                newTask = processToDoDescription(command,args);
            } else if (isDeadline) {
                String command = "deadline " + args;
                newTask = processDeadlineDescription(command,args);
            } else if (isEvent) {
                String command = "event " + args;
                newTask = processEventDescription(command,args);
            } else {
                testDukeException.throwTaskTypeException();
                newTask = null;
            }
            if (isDone) {
                assert newTask != null;
                newTask.markAsDone();
            }
            tasks.add(newTask);
        } catch (DukeException dukeException){
            System.out.println(dukeException.getMessage());
        }
    }

    /**
     * Save task list into a local file.
     * @param tasks the task list
     * @throws DukeException if gets a IOException when saving.
     */
    public void save(TaskList tasks) throws DukeException {
        try {
            writeToFile(tasks);
        } catch (IOException e){
            throw new DukeException("Some thing wrong when saving...");
        }
    }

    /**
     * Writes the task list to the local file.
     * @param tasks the task list.
     * @throws IOException if something wrong when writes the file.
     */
    public void writeToFile(TaskList tasks) throws IOException{
        FileWriter fw = new FileWriter(filePath);
        for (Task task : tasks) {
            int taskID = tasks.indexOf(task) + 1;
            fw.write(taskID + ". " + task.toString() + System.lineSeparator());
        }
        fw.close();
    }


    /**
     * Processes todo's recorded line in file and translates it into a todo task.
     * @param fullCommand todo's line recorded in file.
     * @param args todo's task name.
     * @return a todo task
     * @throws DukeException if task name missing.
     */
    public Todo processToDoDescription(String fullCommand, String args) throws DukeException {
        /*Process Todo Exception */
        TestDukeException testTodoException = new TestDukeException(fullCommand);
        testTodoException.throwToDoException();

        /*Set up a new Todo Task */
        String todoDescription = args;
        return new Todo(todoDescription);
    }

    /**
     * Processes deadline's recorded line in file and translates it into a deadline task.
     * @param fullCommand deadline's line recorded in file.
     * @param args deadline's task name and time.
     * @return a deadline task
     * @throws DukeException if deadline's task name or time missing.
     */
    public Deadline processDeadlineDescription(String fullCommand, String args) throws DukeException {
        /*Process Deadline Exception */
        TestDukeException testDeadlineException = new TestDukeException(fullCommand);
        testDeadlineException.throwDeadlineException();

        /*Set up a new Deadline Task */
        String[] words = args.split(" /by: ");
        String name = words[0];
        String time = words[1];
        return new Deadline(name, time);
    }

    /**
     * Processes event's recorded line in file and translates it into a deadline task.
     * @param fullCommand event's line recorded in file.
     * @param args event's task name and time.
     * @return a event task
     * @throws DukeException if event's task name or time missing.
     */
    public Event processEventDescription(String fullCommand, String args) throws DukeException {
        /*Process Event Exception */
        TestDukeException testEventException = new TestDukeException(fullCommand);
        testEventException.throwEventException();

        /*Set up a new Event Task */
        String[] words = args.split(" /at: ");
        String name = words[0];
        String time = words[1];
        return new Event(name, time);
    }

}
