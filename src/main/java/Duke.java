import java.io.FileWriter;
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.util.Scanner;

public class Duke {
    public static final int MAXIMUM_CAPACITY = 100;
    public static final String SAVE_FILE_DIRECTORY = System.getProperty("user.dir") + "\\duke.txt";
    private static final Scanner SCANNER = new Scanner(System.in);
    private static Task[] tasks = new Task[MAXIMUM_CAPACITY];
    private static int taskCount = 0;
    private Parser parser;
    private DukeExceptions dukeExceptions;

    public Duke(){
        parser = new Parser();
        dukeExceptions = new DukeExceptions();
    }
    public static void doLine(){
        String line = "_".repeat(60);
        System.out.println("\t"+line);
    }
    public static void doGreeting(){
        doLine();
        System.out.println("\tHello! I'm Duke\n\tWhat can I do for you?");
        doLine();
    }
    public static void doFarewell(){
        doLine();
        System.out.println("\tBye. Hope to see you again soon!");
        doLine();

    }
    public void loadList1(){
        try {
            File file =
                    new File(SAVE_FILE_DIRECTORY);
            Scanner sc = new Scanner(file);
            while(sc.hasNextLine()){
                String text = sc.nextLine();
                String[] scanned = text.split(" \\| ");
                switch(scanned[0]){
                case "T":
                    tasks[taskCount] = new ToDo(scanned[2]);
                    if(scanned[1].equals("1")){
                        tasks[taskCount].markAsDone();
                    }
                    taskCount++;
                    break;
                case "D":
                    tasks[taskCount] = new Deadline(scanned[2], scanned[3]);
                    if(scanned[1].equals("1")){
                        tasks[taskCount].markAsDone();
                    }
                    taskCount++;
                    break;
                case "E":
                    tasks[taskCount] = new Event(scanned[2], scanned[3]);
                    if(scanned[1].equals("1")){
                        tasks[taskCount].markAsDone();
                    }
                    taskCount++;
                    break;
                }
            }
            System.out.println("Duke List loaded.");
        }catch (Exception e){
            System.out.println("Error loading file.");
            System.out.println(e);
        }

    }
    public static void saveList(){
        try{
            FileWriter fw =new FileWriter(SAVE_FILE_DIRECTORY, false);
            for(int i =0; i<taskCount;i++){
                String data = "";
                if(tasks[i].getType().equals("T")){
                    int dataBoolean = tasks[i].getIsDone() ? 1:0;
                    data = tasks[i].getType() + " | " + dataBoolean + " | " + tasks[i].getDescription() + "\n";
                }else{
                    int dataBoolean = tasks[i].getIsDone() ? 1:0;
                    data = tasks[i].getType() + " | " + dataBoolean + " | " + tasks[i].getDescription() + " | " + tasks[i].getExtra() + "\n";
                }
                fw.write(data);
            }
            fw.close();
        }catch (Exception e){
            System.out.println("Writing to file failed.");
        }
    }
    public static void getList(){
        doLine();
        System.out.println("\tHere are the tasks in your list:");
        for(int i = 0; i< taskCount; i++){
            System.out.println("\t"+(i+1)+"."+tasks[i].toString());
        }
        doLine();
    }
    private static String getUserInput() {
        String inputLine = SCANNER.nextLine();
        while (inputLine.trim().isEmpty()) {
            inputLine = SCANNER.nextLine();
        }
        return inputLine;
    }
    private static void addTask(Task task){
        tasks[taskCount] = task;

        doLine();
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t\t"+tasks[taskCount].toString());
        taskCount++;
        System.out.println("\tNow you have "+ taskCount +" tasks in the list.");
        doLine();
    }
    private static void completeTask(String taskCount){
        int taskNumber = Integer.parseInt(taskCount) -1;
        tasks[taskNumber].markAsDone();
        doLine();
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t\t"+tasks[taskNumber].toString());
        doLine();
    }
    public void processInput(String input){
        try{
            parser.setInput(input);
            String firstCommand = parser.getFirst();
            if(firstCommand.equals("bye")){
                doFarewell();
                exitProgram();
            }else if(firstCommand.equals("list")) {
                getList();
            }else if(firstCommand.equals("deadline")){
                try {
                    if(parser.getDeadlineItem().equals(" ") || parser.getDeadlineBy().isEmpty() || parser.getDeadlineItem().isEmpty() || parser.getDeadlineBy().equals(" ")){
                        throw new ArrayIndexOutOfBoundsException();
                    }
                    addTask(new Deadline(parser.getDeadlineItem(), parser.getDeadlineBy()));
                    saveList();
                }catch (ArrayIndexOutOfBoundsException e){
                    dukeExceptions.printInvalidDeadlineException();
                }
            }else if(firstCommand.equals("event")){
                try {
                    if(parser.getEventItem().equals(" ") || parser.getEventItem().isEmpty() || parser.getEventAt().isEmpty() || parser.getEventAt().equals(" ")){
                        throw new ArrayIndexOutOfBoundsException();
                    }
                    addTask(new Event(parser.getEventItem(), parser.getEventAt()));
                    saveList();
                }catch (ArrayIndexOutOfBoundsException e){
                    dukeExceptions.printInvalidEventException();
                }
            }else if(firstCommand.equals("todo")){
                try{
                    if(parser.getToDo().equals(" ") || parser.getToDo().isEmpty()){
                        throw new ArrayIndexOutOfBoundsException();
                    }
                    addTask(new ToDo(parser.getToDo()));
                    saveList();
                }catch (ArrayIndexOutOfBoundsException e){
                    dukeExceptions.printInvalidToDoException();
                }
            }else if(firstCommand.equals("done")) {
                try {
                    if(parser.getCompleteNumber().equals(" ")  || parser.getCompleteNumber().isEmpty()){
                        throw new ArrayIndexOutOfBoundsException();
                    }
                    completeTask(parser.getCompleteNumber());
                    saveList();
                }catch (ArrayIndexOutOfBoundsException e){
                    dukeExceptions.printInvalidDoneException();
                }
            }else{
                dukeExceptions.printInvalidCommandException();
            }
        }catch (Exception e){
            dukeExceptions.printInvalidCommandException();
        }

    }

    private static void exitProgram() {
        System.exit(0);
    }
    private void run(){
        loadList1();
        while(true){
            String userCommand = getUserInput();
            processInput(userCommand);
        }
    }
    public static void main(String[] args) {

        doGreeting();
        Duke duke = new Duke();
        duke.run();
    }
}
