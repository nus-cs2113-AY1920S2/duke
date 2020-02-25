package storage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import common.tasks.Deadline;
import common.tasks.Event;
import common.tasks.Task;
import common.tasks.ToDo;
import common.exceptions.DukeException;

public class Storage {
	public ArrayList<Task> list = new ArrayList<>();
	
	public void writeToFile(ArrayList<Task> tasks) throws IOException, DukeException {
    	if (list.isEmpty()) {
    		throw new DukeException("Oops!! List is empty.");
    	}
    	FileWriter fw = new FileWriter("duke.txt");
    	try {
    		for (Task task : tasks) {
    			fw.write(task.toString() + '\n');
    		}
    		fw.close();
    	} catch (IOException e) {
    		System.out.println("Something went wrong: " + e.getMessage());
    	}
    }
    
    public void readFromFile() throws IOException, DukeException {
    	BufferedReader br;
    	try {
    		br = new BufferedReader(new FileReader("duke.txt"));
    	} catch (Exception e) {
    		return;
    	}
		String string;
		try {
			while ((string = br.readLine()) != null) {
				boolean isDone = string.charAt(4) == 'Y';
				String task;
				String by;
				int index;
				switch (string.charAt(1)) {
					case 'T':
						list.add(new ToDo(string.substring(7), isDone));
						break;
					case 'D':
						index = string.indexOf('(');
						task = string.substring(7, index);
						by = string.substring(index + 5, string.length() - 1);
						list.add(new Deadline(task, by, isDone));
						break;
					case 'E':
						index = string.indexOf('(');
						task = string.substring(7, index);
						by = string.substring(index + 5, string.length() - 1);
						list.add(new Event(task, by, isDone));
						break;
				}
			}
		} catch (IOException e) {
    		System.out.println("Something went wrong: " + e.getMessage());
    	}
    }
}
