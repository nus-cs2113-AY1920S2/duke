package Duke.data.exceptions;

public class DukeExceptions {
    public static void doLine(){
        String line = "_".repeat(60);
        System.out.println("\t"+line);
    }
    public static void printInvalidCommandException(){
        doLine();
        System.out.println("\tYou have entered an incorrect input. Please try again.");
        System.out.println("\tHere is a list of command:");
        System.out.println("\tbye\t\t\t\t\t- Exit Duke.");
        System.out.println("\tdeadline <item> /by <date>\t\t\t\t\t- Add Duke.data.objects.Deadline item into List.");
        System.out.println("\tdone <item number>\t\t\t\t\t- Mark item number as done. Ex 'done 1' to mark item(1) as done");
        System.out.println("\tevent <item> /at <location>\t\t\t\t\t- Add Duke.data.objects.Event item into List.");
        doLine();
    }
    public static void printInvalidEventException(){
        doLine();
        System.out.println("\tYou have entered an incorrect input. Please try again.");
        System.out.println("\tHere is a example of the Duke.data.objects.Event command:");
        System.out.println("\tevent <item> /at <location>\t\t\t\t\t <Ex: 'event CTF /at COM2'");
        doLine();
    }
    public static void printInvalidDeadlineException(){
        doLine();
        System.out.println("\tYou have entered an incorrect input. Please try again.");
        System.out.println("\tHere is a example of the Duke.data.objects.Deadline command:");
        System.out.println("\tdeadline <item> /by <date/time>\t\t\t\t\t <Ex: 'deadline level-5 assignment /by tomorrow'");
        doLine();
    }
    public static void printInvalidToDoException(){
        doLine();
        System.out.println("\tYou have entered an incorrect input. Please try again.");
        System.out.println("\tHere is a example of the Duke.data.objects.ToDo command:");
        System.out.println("\ttodo <item>\t\t\t\t\t <Ex: 'todo readbook'");
        doLine();
    }

    public static void printInvalidDoneException(){
        doLine();
        System.out.println("\tYou have entered an incorrect input. Please try again.");
        System.out.println("\tHere is a example of the done command:");
        System.out.println("\tdone <item number>\t\t\t\t\t <Ex: 'done 1'");
        doLine();
    }
    public static void printInvalidDeleteException(){
        doLine();
        System.out.println("\tYou have entered an incorrect input. Please try again.");
        System.out.println("\tHere is a example of the done command:");
        System.out.println("\tdelete <item number>\t\t\t\t\t <Ex: 'delete 1'");
        doLine();
    }
    public static void printIndexOutOfBoundsException(){
        doLine();
        System.out.println("\tYou have entered an item number that does not exist. Please try again.");
        System.out.println("\tTry 'list' to display the items that exists in the Duke.data.objects.Task list.");
        doLine();
    }

}
