import duke.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;

/**
 * Main class of the Duke project containing UI display, storage handling, input handling
 * and task list operations.
 */

public class Duke {

    private static ArrayList<Task> taskList = new ArrayList<Task>();

    public static void main(String[] args) throws IOException, DukeException {
        welcomeMessage();
        loadFile();
        greetUser();
        conversation();
    }

    public static void welcomeMessage(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    public static void greetUser() {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you");
        System.out.println("____________________________________________________________");
    }

    public static void finishConversation() {
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    /**
     * Takes in input string from terminal and calls various
     * functions according to different keywords.This function uses recursion.
     *
     * @throws IOException or DukeException If input does not match format.
     */
    public static void conversation() throws IOException, DukeException {
        System.out.println("____________________________________________________________");
        String str = readCommand();
        System.out.println("____________________________________________________________");
        if (str.equals("bye")) {
            saveTask();
            finishConversation();
        } else if (str.contains("delete")) {
            deleteTask(str);
            conversation();
        } else if (str.equals("add task")) {
            System.out.println("____________________________________________________________");
            addTaskScreen();
        } else if (str.equals("list")) {
            listTask();
            conversation();
        }else if (str.contains("done")) {
            markDone(str);
            System.out.println("Mark task done");
            conversation();
        }else if(str.contains("find")) {
            findTask(str);
            conversation();
        }else {
            System.out.println(str);
            System.out.println("____________________________________________________________");
            conversation();
        }
    }

    private static String readCommand() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = null;
        System.out.println("Type something");
        str = br.readLine().toLowerCase();
        return str;
    }

    /**
     * Takes a string pre-processed from other functions
     * and turns it into a LocalDate object
     *
     * @param input A string from the duke.txt file
     */
    private static LocalDate extractDate(String input){
        LocalDate date;
        date = LocalDate.parse(input);
        return date;
    }

    /**
     * Triggered after entering "add task" in terminal
     * In adding Task mode, keywords are used to put tasks in different categories
     * and handle the status of the task list.
     *
     * @throws IOException or DukeException If input does not match format.
     */
    public static void addTaskScreen() throws IOException, DukeException {
        System.out.println("____________________________________________________________");
        System.out.println("Please add tasks");
        System.out.println("____________________________________________________________");
        String str = readCommand();

        if (str.equals("bye")) {
            saveTask();
            finishConversation();
        } else if (str.contains("done")) {
            markDone(str);
            System.out.println("Mark task done");
            addTaskScreen();
        }else if(str.contains("find")){
            findTask(str);
            addTaskScreen();
        } else if (str.equals("list")) {
            listTask();
            addTaskScreen();
        } else if ((str.contains("todo"))) {
            missingTaskError(str);
            int dividerPosition = str.indexOf(" ");
            String task = str.substring(dividerPosition + 1);
            ToDos newTask = new ToDos(task);
            addTask(newTask);
            addTaskScreen();
        } else if ((str.contains("deadline"))) {
            missingTaskError(str);
            missingDateTimeError(str);
            int dividerPosition = str.indexOf(" ");
            int dividerPositionSlash = str.indexOf("/");
            String task = str.substring(dividerPosition + 1, dividerPositionSlash);
            String by = str.substring(dividerPositionSlash + 4);
            LocalDate date = extractDate(by);
            Deadlines newTask = new Deadlines(task, date);
            addTask(newTask);
            addTaskScreen();
        } else if ((str.contains("event"))) {
            missingTaskError(str);
            missingDateTimeError(str);
            int dividerPosition = str.indexOf(" ");
            int dividerPositionSlash = str.indexOf("/");
            String task = str.substring(dividerPosition + 1, dividerPositionSlash);
            String calender = str.substring(dividerPositionSlash + 4);
            int dateAndTime = calender.indexOf(" ");
            String at = calender.substring(0, dateAndTime);
            LocalDate date = extractDate(at);
            String time = calender.substring(dateAndTime + 1);
            Events newTask = new Events(task, date, time);
            addTask(newTask);
            addTaskScreen();
        } else if (str.contains("delete")) {
            deleteTask(str);
            addTaskScreen();
        } else {
            wrongInputFormatError(str);
        }
    }

    /**
     * Add a task item into taskList
     * and prints a message
     *
     * @throws IOException
     */
    public static void addTask(Task newTask) throws IOException {
        System.out.println("Got it. I've added this task: " + newTask.description);
        taskList.add(newTask);
        System.out.println("added : " + newTask);
        System.out.println("Now you have " + taskList.size() + " tasks in the list");
    }

    /**
     * Deletes a task according to the index
     * Raises error if index is missing or out of range
     *
     * @param str A string from the terminal
     * @throws IOException DukeException if index is missing or out of range
     */
    public static void deleteTask(String str) throws IOException, DukeException {
        if (str.equals("delete")) {
            missingIndexError();
        }
        int dividerPosition = str.indexOf(" ");
        String index = str.substring(dividerPosition + 1);
        int i = Integer.parseInt(index);
        deleteError(i, taskList);
        Task toRemove = taskList.get(i - 1);
        taskList.remove(i - 1);
        System.out.println("Noted. I've removed this task: ");
        System.out.println("Removed : " + toRemove);
        System.out.println("Now you have " + taskList.size() + " tasks in the list");
    }

    /**
     * Takes in the list in taskList
     * and list the input to the terminal.
     */
    public static void listTask() {
        System.out.println("____________________________________________________________");
        int i = 0;
        while (i < taskList.size()) {
            int j = i + 1;
            System.out.println(j + " ." + taskList.get(i).showSearch());
            i++;
        }

        System.out.println("____________________________________________________________");
    }

    /**
     * finds tasks  according to the index.
     * Raises errors if keyword is missing
     * Return a emppty list if no match
     * @param input A string from the terminal
     */
    public static void findTask(String input){
        System.out.println("____________________________________________________________");
        System.out.println(" Here are the matching tasks in your list:");
        ArrayList<Task> searchResult = new ArrayList<>();
        String keyword = input.substring(5);
        for(Task task: taskList){
            if(task.description.contains(keyword)){
                searchResult.add(task);
            }
        }
        if(searchResult.size() == 0){
            System.out.println(" No matching results ");
        }
        listSearchResult(searchResult);
    }

    /**
     * Takes in the list generated using findTask instead of the taskList
     * and list the input to the terminal.
     * @param  input A list of tasks found using keywords
     */
    public static void listSearchResult(ArrayList<Task> input) {
        System.out.println("____________________________________________________________");
        int i = 0;
        while (i < input.size()) {
            int j = i + 1;
            System.out.println(j + " ." + input.get(i).showSearch());
            i++;
        }

        System.out.println("____________________________________________________________");
    }


    /**
     * Mark a task done according to the index.
     * Raises errors if index is missing or index out of range
     *
     * @throws IOException DukeException if index is missing or out of range
     */
    public static void markDone(String str) throws IOException, DukeException {
        if (str.equals("done")) {
            missingIndexError();
        }
        int dividerPosition = str.indexOf(" ");
        String index = str.substring(dividerPosition + 1);
        int i = Integer.parseInt(index);
        markDoneError(i, taskList);
        taskList.get(i - 1).markDone();
        listTask();
    }

    public static void missingDateTimeError(String input) throws DukeException {
        if (!input.contains("/")) {
            throw new DukeException("OOPS!!!Please specify more details");
        }
    }

    public static void missingTaskError(String input) throws DukeException {
        if (input.equals("todo") || input.equals("deadline") || input.equals("event")) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        }
    }

    public static void wrongInputFormatError(String input) throws DukeException {
        throw new DukeException("OOPS!!!Wrong input format.");
    }

    public static void markDoneError(int index, ArrayList<Task> taskList) throws DukeException {
        if (index == 0) {
            throw new DukeException("OOPS!!!Invalid input index");
        } else if (index > taskList.size()) {
            throw new DukeException("OOPS!!!Index out of range");
        }
    }

    public static void deleteError(int index, ArrayList<Task> taskList) throws DukeException {
        if (index == 0) {
            throw new DukeException("OOPS!!!Invalid input index");
        } else if (index > taskList.size()) {
            throw new DukeException("OOPS!!!Index out of range");
        }
    }

    public static void missingIndexError() throws DukeException {
        throw new DukeException("OOPS!!! The index cannot be empty.");
    }

    /**
     * Save the task items in taskList one by one.
     * It calls writeToFile every time it finds a valid task item.
     *
     * @throws IOException if new file can not be created.
     */
    public static void saveTask() throws IOException {
        System.out.println("Tasks are being saved now");
        try {
            for (Task ignored : taskList) {
                writeToFile();
            }
        } catch (IOException e) {
            System.out.println("Task can not be saved");
        }

    }

    /**
     * Write taskList into a txt file named Duke.txt
     * A new blank file will be created if the programme
     * can not fina a existing one.
     *
     * @throws IOException if new file can not be created.
     */
    private static void writeToFile() throws IOException {
        File duke = new File("duke.txt");
        if (!duke.exists()) {
            try {
                duke.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("New duke file has been created");
        }
        FileWriter fw = new FileWriter("duke.txt");
        for (Task task : taskList) {
            fw.write(task.toString() + System.lineSeparator());
        }
        fw.close();
    }


    /**
     * Searches for a duke.txt file.
     * Display a message if such file does not exist
     * but does not terminate the programme
     *
     */
    public static void loadFile() {
        try {
            readFromFile();
        } catch (FileNotFoundException e) {
            System.out.println("____________________________________________________________");
            System.out.println("File not found");
            System.out.println("____________________________________________________________");
        } catch (IOException e) {
            System.out.println("____________________________________________________________");
            System.out.println("IO error");
            System.out.println("____________________________________________________________");
            e.printStackTrace();
        }
    }

    /**
     * Read from a duke.txt file using a scanner to capture
     * the strings and calls loadTaskList.
     *
     * @throws IOException if new file can not be read.
     */
    public static void readFromFile() throws IOException {
        File file = new File("duke.txt");
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            String raw = scanner.nextLine();
            loadTaskList(raw, taskList);
        }
    }

    /**
     * Handle raw string inputs from the duke.txt
     * and creates respective task items with details
     * and stores in a list
     *
     * @param raw A string from the duke file containing task details
     * @param  tasks A list used to store task items
     * @throws IOException if new file can not be read.
     */
    private static void loadTaskList(String raw, ArrayList<Task> tasks) throws IOException {
        if (raw.contains("[T]")) {
            if (raw.contains("\u2713")) {
                String taskDescription = raw.substring(6);
                ToDos newTask = new ToDos(taskDescription);
                newTask.isDone = true;
                taskList.add(newTask);
            } else {
                String taskDescription = raw.substring(6);
                ToDos newTask = new ToDos(taskDescription);
                newTask.isDone = false;
                taskList.add(newTask);
            }
        } else if (raw.contains("[D]")) {
            int timeSectionStart = raw.indexOf("(");
            int timeSectionEnd = raw.indexOf(")");
            String taskDescription = raw.substring(6, timeSectionStart - 1);
            String timePeriod = raw.substring(timeSectionStart + 5, timeSectionEnd);
            LocalDate date = extractDate(timePeriod);
            Deadlines newTask = new Deadlines(taskDescription, date);
            if (raw.contains("\u2713")) {
                newTask.isDone = true;
            }
            taskList.add(newTask);
        } else if (raw.contains("[E]")) {
            int timeSectionStart = raw.indexOf("(");
            int timeSectionEnd = raw.indexOf(")");
            String taskDescription = raw.substring(6, timeSectionStart-1);
            int colon = raw.indexOf(":");
            String timePeriod = raw.substring(colon + 2  , timeSectionEnd);
            int space = timePeriod.indexOf(" ");
            String on = timePeriod.substring(0 , space );
            String at = timePeriod.substring(space+1);
            LocalDate date = extractDate(on);
            Events newTask = new Events(taskDescription, date, at);
            if (raw.contains("\u2713")) {
                newTask.isDone = true;
            }
            taskList.add(newTask);
        }
    }
}