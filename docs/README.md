# User Guide

## Features 

###  Feature 1 
User can _add_ Tasks. There exists 
three different tasks: 
* Todo
* Event 
* Deadline. 

Note: Event and Deadline Tasks have an additional
aspect in which timing is also stored on top of 
task description.

When a task is added, *Nini* will
inform the User accordingly:

 If the Task is Todo:

	 Got it. I've added this task:
	   [T][✘] {task description}
	 Now you have {list size} tasks in the list.
   
If the Task is Event:

	 Got it. I've added this task:
	   [E][✘] {task description} {timing}
	 Now you have {list size} tasks in the list.
   
If the Task is Deadline:

	  Got it. I've added this task:
	   [D][✘] {task description} {timing}
	 Now you have {list size} tasks in the list.



### Feature 2
User can mark Tasks as _done_. 

User must indicate the index in the Task List
that the Task is assigned to. 

When a task is marked as _done_,
*Nini* will inform the User as follows:

	 Nice! I've marked this task as done:
	   [T][✓] read books

User also has the option to mark all as done. 
In which *Nini* will inform:

	 All tasks have been marked as done!
	 1.[T][✓] read books
	 2.[T][✓] finish homework
	 3.[E][✓] student ambassador interview (at: 26 feb 2019 14:00)
	 4.[E][✓] friend's birthday (at: tomorrow)
	 5.[D][✓] cg2027 assignment (by: 23 feb 2019 09:00)
   
   
### Feature 3 
User can _delete_ Tasks.

User must indicate the index in the Task List
that the Task is assigned to. 

When a task is deleted, *Nini* will update
as follows: 

	 Noted. I've removed this task: 
	   [E][✓] student ambassador interview (at: 26 feb 2019 14:00)
	 Now you have 4 tasks in the list.

User also has the option to delete all Tasks.

In doing so, *Nini* will inform the User as follows:
	
    All tasks have been deleted!
### Feature 4
User can list Tasks.

*Nini* will inform User of the all the 
Tasks currently stored in his Task List

### Feature 5 
User can find Tasks

*Nini* will search for Tasks that matches
the key entered by the User.

	 Here are the matching tasks in your list!
	 1.[T][✓] read books


##  Usage

### `Keyword` - Describe action

Describe action and its outcome.

Example of usage: 

`keyword (optional arguments)`

Expected outcome:

`outcome`
