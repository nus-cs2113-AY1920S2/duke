import java.util.Scanner;

public class Duke {
    public static final int MAXIMUM_CAPACITY = 100;
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
                }catch (ArrayIndexOutOfBoundsException e){
                    dukeExceptions.printInvalidDeadlineException();
                }
            }else if(firstCommand.equals("event")){
                try {
                    if(parser.getEventItem().equals(" ") || parser.getEventItem().isEmpty() || parser.getEventAt().isEmpty() || parser.getEventAt().equals(" ")){
                        throw new ArrayIndexOutOfBoundsException();
                    }
                    addTask(new Event(parser.getEventItem(), parser.getEventAt()));
                }catch (ArrayIndexOutOfBoundsException e){
                    dukeExceptions.printInvalidEventException();
                }
            }else if(firstCommand.equals("todo")){
                try{
                    if(parser.getToDo().equals(" ") || parser.getToDo().isEmpty()){
                        throw new ArrayIndexOutOfBoundsException();
                    }
                    addTask(new ToDo(parser.getToDo()));
                }catch (ArrayIndexOutOfBoundsException e){
                    dukeExceptions.printInvalidToDoException();
                }
            }else if(firstCommand.equals("done")) {
                try {
                    if(parser.getCompleteNumber().equals(" ")  || parser.getCompleteNumber().isEmpty()){
                        throw new ArrayIndexOutOfBoundsException();
                    }
                    completeTask(parser.getCompleteNumber());
                }catch (ArrayIndexOutOfBoundsException e){
                    dukeExceptions.printInvalidDoneException();
                }
            }else{
                dukeExceptions.printInvalidCommandException();
            }
        }catch (Exception e){
            System.out.println(e);
            dukeExceptions.printInvalidCommandException();
        }

    }

    private static void exitProgram() {
        System.exit(0);
    }
    private void run(){
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
