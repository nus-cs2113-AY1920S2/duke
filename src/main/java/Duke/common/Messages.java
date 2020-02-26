package Duke.common;

public class Messages {
    public static final String MESSAGE_GREETING = "\tHello! I'm Duke\n\tWhat can I do for you?";
    public static final String MESSAGE_FAREWELL = "\tBye. Hope to see you again soon!";
    public static final String MESSAGE_GETLIST = "\tHere are the tasks in your list:";
    public static final String MESSAGE_ADDTASK = "\tGot it. I've added this task:";
    public static final String MESSAGE_DELTASK = "\tNoted. I've removed this task:";
    public static final String MESSAGE_TASKDONE = "\tNice! I've marked this task as done:";
    public static final String MESSAGE_HELP = "\tHere is a list of command:\n"+"\tbye\t\t\t\t\t- Exit Duke.\n" +
            "\tdeadline <item> /by <date>\t\t\t\t\t- Add Duke.data.objects.Deadline item into List.\n" +
            "\tdone <item number>\t\t\t\t\t- Mark item number as done. Ex 'done 1' to mark item(1) as done\n" +
            "\tevent <item> /at <location>\t\t\t\t\t- Add Duke.data.objects.Event item into List."+
            "\thelp \t\t\t\t\t- Displays all function";

}
