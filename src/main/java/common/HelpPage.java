package common;

/**
 * Help page containing all valid commands and formats.
 */
public class HelpPage {
	
	public final static String HelpMessage = "Welcome to the Help Page" + '\n'
			+ "  Here is a list of valid commands and formats:" + '\n'
			+ "    1) todo <task description>  -  adds a todo task" + '\n'
			+ "    2) event <task description> <date> <time(optional)>  -  adds an event task" + '\n'
			+ "    * refer to date and time format help section below for accepted formats *" + '\n'
			+ "    3) deadline <task description> <date> <time(optional)>  -  adds an deadline task" + '\n'
			+ "    4) list  -  lists out all tasks and their index numbers" + '\n'
			+ "    5) done <index of task>  -  marks a task as done based on their index (refer to list command)" + '\n'
			+ "    6) delete <index of task>  -  deletes the task based on their index (refer to list command)" + '\n'
			+ "    7) find <keyword / characters>  -  finds all tasks containing the keyword/characters" + '\n'
			+ "    8) show_overdue  -  lists all overdue tasks" + '\n'
			+ "    9) show_upcoming <number of days>  -  lists all upcoming tasks within the number of days" + '\n'
			+ "    10) remove_past  -  removes all tasks that have passed" + '\n'
			+ "    11) remove_done  -  removes all tasks that are marked as done" + '\n'
			+ "    12) show_deleted  -  lists all deleted tasks" + '\n'
			+ "    13) clear_all  -  deletes all tasks" + '\n'
			+ "    14) bye  -  terminates the program" + '\n'
			+ '\n'
			+ "  Acceptable date formats:" + '\n'
			+ "    1) date/month/year  (e.g. 1/3/19, 01/03/19, 1/3/2019, 01/03/2019, 2019/03/01)" + '\n'
			+ "    2) date-month-year  (e.g. 1-3-19, 01-03-19, 1-3-2019, 01-03-2019)" + '\n'
			+ "    3) date.month.year  (e.g. 1.3.19, 01.03.19, 1.3.2019, 01.03.2019)" + '\n'
			+ "    4) date-MMM-year  (e.g. 1-Mar-19, 1-Mar-2019, 01-Mar-2019)" + '\n'
			+ "  Acceptable time formats:" + '\n'
			+ "    1) 12-hr time  (e.g. 10PM, 10:15PM, 10:15:00PM)" + '\n'
			+ "    2) 24-hr time  (e.g. 2215, 22:15, 22:15:00)";
	
}
