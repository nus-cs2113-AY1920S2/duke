import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Duke {
	private final List<Task> taskList;
	
	private Duke(List<Task> newTaskList) {
		this.taskList = newTaskList;
	}
	
	public Duke() {
		this.taskList = new ArrayList<>();
	}
	
	public Duke addTask(int taskId, String taskName) {
		Task task = new Task(taskId, taskName);
		this.taskList.add(task);
		
		List<Task> newTaskList = this.taskList.stream()
				.collect(Collectors.toList());
		
		printEcho("added: " + task.taskWithSymbol());
		return new Duke(newTaskList);
	}
	
	public Duke completeTask(int taskId) {
		Task task = this.taskList.get(taskId - 1)
				.makeDone();
		
		List<Task> newTaskList = this.taskList.stream()
				.map(i -> {
					return (i.getTaskId() == taskId) ? i.makeDone() : i;
				})
				.collect(Collectors.toList());
	
		printDoneTask(task);
		return new Duke(newTaskList);
	}
		
	public void printDoneTask(Task task) {
		printBorder();
		
		String output = "\nNice! I've marked this task as done:\n";
		output += ("  " + task.taskWithSymbol());
		System.out.println(output);
		
		printBorder();
	}
	
	public void printList() {
		printBorder();
		
		System.out.println("\nHere are the tasks "
				+ "in your list:");
		
		this.taskList
			.stream()
			.forEachOrdered(System.out::println);
		
		printBorder();
	}
	
	public void printEcho(String content) {
		String output = "";
		
		printBorder(); 
		output += ("\n" + content);	  
		System.out.println(output);
		
		printBorder();
	}
	
	public void printBorder() {
		System.out.println("_______________________"
				+ "________________________");
	}
	
	@Override
	public String toString() {
		String logo = " ____        _        \n"
	            	+ "|  _ \\ _   _| | _____ \n"
	            	+ "| | | | | | | |/ / _ \\\n"
	            	+ "| |_| | |_| |   <  __/\n"
	            	+ "|____/ \\__,_|_|\\_\\___|\n";
		
		logo += ("\nHello! I'm Duke!" 
				+ "\nWhat can I do for you today?");
		return logo;
	}
}
