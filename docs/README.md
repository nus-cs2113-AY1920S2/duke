# User Guide

## Short introduction
* Duke features adding of three types of task: `todo deadline event`. <br>
* Duke is also able to `delete` existing task, `find` specific task from a populated list of task and mark task as `done`. <br>
* In addition, Duke is able to convert user input description of `deadline` and `event` task if date-time is of the format `yyyy-mm-dd`
## Features
- [Usage](#usage)
	- [Add a todo](#adding-a-task-todo)
	- [Add a deadline](#adding-a-deadline-deadline)
	- [Add an event](#adding-an-event-event)
	- [List all tasks](#listing-all-tasks-list)
	- [Mark task as complete](#marking-a-task-as-complete-done)
	- [Delete a task](#deleting-a-task-delete)
	- [Find a task](#locating-a-certain-task-find)
	- [Exit Duke](#exiting-the-program-bye)
	- [Save to hard disk](#saving-the-task-list)
	
## Usage

### Adding a task: <code>todo</code>
Adds a new task to Duke. <br>
Format: <code>todo <strong>task</strong> </code> <br>
Example of usage: <code>todo <strong>CS2113T tutorial 5</strong></code> <br>
Expected outcome: 
```
Got it . I've added this task:
[T][✘] CS2113T tutorial 5 
Now you have x tasks in the list.
```
### Adding a deadline: <code>deadline</code>
Adds a new task with deadline to Duke. <br>
Format: <code>deadline <strong>task</strong> /by <strong>description of task</strong></code> <br>
Example of usage: <code>deadline <strong>CS2113T tutorial 5</strong> /by <strong>2020-05-25 6.00pm</strong></code> <br>
Expected outcome: 
```
Got it . I've added this task:
[D][✘] CS2113T tutorial 5 (by: 5 MAY 2020, MONDAY 6.00pm) 
Now you have x tasks in the list.
```
### Adding an event: <code>event</code>
Adds a new event task to Duke.<br>
Format: <code>event <strong>task</strong> /at <strong>description</strong></code> <br>
Example of usage: <code>event <strong>CS2113T oral presentation</strong> /at <strong>2020-03-01 1.00pm</strong></code> <br>
Expected outcome:
```
Got it . I've added this task: <br>
[E][✘] CS2113T oral presentation (at: 3 MARCH 2020, SUNDAY 1.00pm)
Now you have x tasks in the list.
```
### Listing all tasks : <code>list</code>
Shows all the tasks user has typed into. <br>
Example of usage: <code>list</code> <br>
Expected outcome:
```
Here are the tasks in your list:
1 .[T] Task 1
2 .[D] Task 2
```
### Marking a task as complete: <code>done</code>
Mark a specific task that user has typed in as completed. <br>
Format: <code>done <strong>index</strong></code> <br>
Example of usage: <code>done <strong>1</strong></code> <br>
Expected outcome:
```
Nice! I've marked this task as done:
[T] Task 1
```
* A tick[✔] will be shown instead of a cross[✘] to indicate task is completed.
* <strong>index</strong> must be within the range of the displayed task <code>list</code> 
* <strong>index</strong> must start from 1 

### Deleting a task: <code>delete</code>
Deletes a specific task from the <code>list</code> of task. <br>
Format: <code> delete <strong>index</strong></code> <br>
Example of usage: <code> delete <strong>1</strong> </code> <br>
Expected outcome:
```
Noted, I've removed this task: 
[D] Task 1
```
* <strong>index</strong> must be within the range of the displayed task <code>list</code>
* <strong>index</strong> must start from 1

### Locating a certain task: <code>find</code>
Find all the tasks which contains the keyword that user has typed in. <br>
Format: <code>find <strong>keyword [more keyword]</strong></code> <br>
Example of usage: <code>find <strong>CS2113T oral</strong></code> <br>
Expected outcome:
```
Here are the matching task/s in your list:
[D] Task 1
[T] Task 2
```
* Tasks containing at least one of the keyword would be displayed.
* Order of keyword matters: <code>find john roe</code> is not equivalent to <code>find roe john</code>
### Exiting the program: <code>bye</code>
Ends the Duke application.<br>
Example of usage: <code><strong>bye</strong></code> <br>

### Saving the task list
* Duke automatically save the task list into the project folder directory under <code>/savedTasks.txt</code> <br>
* Duke automatically loads the task list from previous session if the file <code>/savedTasks.txt</code> exist

