
public class Task {
	private final String taskName;
	private final int taskId;
	private boolean isDone;
	
	private Task(int taskId, String taskName, boolean isDone) {
		this.taskId = taskId;
		this.taskName = taskName;
		this.isDone = isDone;
	}
	
	public Task(int taskId, String taskName) {
		this.taskId = taskId;
		this.taskName = taskName;
		this.isDone = false;
	}
	
	public int getTaskId() {
		return this.taskId;
	}
	
	public String getTaskName() {
		return this.taskName;
	}
	
	public boolean getDoneStatus() {
		return this.isDone;
	}
	
	public String taskWithSymbol() {
		return ((this.isDone) ? "[✓]" : "[✗]")
				+ " " + this.taskName;
	}
	
	public Task makeDone() {
		return new Task(this.taskId, this.taskName, true);
	}
	
	@Override
	public boolean equals(Object object) {
	    if (object == this) {
	    	return true;
	    } 
	    if (object instanceof Task) {
	      Task task = (Task) object;
	      	return task.taskId == this.taskId;
	    }
	    return false;
	}
	
	@Override
	public String toString() {
		return this.taskId + "." + this.taskWithSymbol(); 			
	}
}
