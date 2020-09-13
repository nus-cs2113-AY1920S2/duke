# User Guide

## Features 
* [Add a ToDo](#adding-a-todo-todo)
* [Add a Deadline](#adding-a-deadline-deadline)
* [Add an Event](#adding-an-event-event)
* [Display list of tasks](#displaying-list-of-tasks-list)
* [Mark task as done](#marking-task-as-done-done)
* [Delete task from list](#deleting-task-from-list-delete)
* [Find keyword in list](#finding-keyword-in-list-find)
* [Exit Duke](#exiting-duke-bye)
* [Save task list](#saving-task-list)

## Usage

### Adding a ToDo: `todo`

Adds a new ToDo task to your list.

Example of usage: 
<pre>
<b>todo</b> borrow book
</pre>

Expected outcome:
```
____________________________________________________________
Got it. I've added this task: 
[T][ ] borrow book
Now you have 1 tasks in the list.
____________________________________________________________
```
### Adding a Deadline: `deadline`

Adds a new Deadline task to your list.

Example of usage: 
<pre>
<b>deadline</b> return book <b>/by</b> Sunday
</pre>

Expected outcome:
```
____________________________________________________________
Got it. I've added this task: 
[D][ ] return book (by: Sunday)
Now you have 2 tasks in the list.
____________________________________________________________
```
### Adding an Event: `event`

Adds a new Event task to your list.

Example of usage: 
<pre>
<b>event</b> project meeting <b>/at</b> Mon 2-4pm
</pre>

Expected outcome:
```
____________________________________________________________
Got it. I've added this task: 
[E][ ] project meeting (at: Mon 2-4pm)
Now you have 3 tasks in the list.
____________________________________________________________
```
### Displaying list of tasks: `list`

Shows all tasks in your list.

Example of usage: 
<pre>
<b>list</b>
</pre>

Expected outcome:
```
____________________________________________________________
Here are the tasks in your list:
1. [T][ ] borrow book
2. [D][ ] return book (by: Sunday)
3. [E][ ] project meeting (at: Mon 2-4pm)
____________________________________________________________
```
### Marking task as done: `done`

Marks a specific task as done.

Example of usage: 
<pre>
<b>done</b> 2
</pre>

Expected outcome:
```
____________________________________________________________
Nice! I've marked this task as done: 
[D][x] return book (by: Sunday)
____________________________________________________________
```
### Deleting task from list: `delete`

Removes a specific task from the list.

Example of usage: 
<pre>
<b>delete</b> 1
</pre>

Expected outcome:
```
____________________________________________________________
Noted. I've removed this task: 
[T][ ] borrow book
Now you have 2 tasks in the list.
____________________________________________________________
```
### Finding keyword in list: `find`

Shows all tasks from the list which contains the keyword.

Example of usage: 
<pre>
<b>find</b> book
</pre>

Expected outcome:
```
____________________________________________________________
Here are the matching tasks in your list:
1. [T][ ] borrow book
2. [D][ ] return book (by: Sunday)
____________________________________________________________
```
### Exiting Duke: `bye`

Exits the program.

Example of usage: 
<pre>
<b>bye</b>
</pre>

Expected outcome:
```
Bye. Hope to see you again soon!
 _                
| |               
| |__  _   _  ___ 
| '_ \| | | |/ _ \
| |_) | |_| |  __/
|_.__/ \__, |\___|
        __/ |     
       |___/      
```

### Saving task list

Duke automatically saves the tasks into `[project_root]/data/duke.txt` whenever the task list changes.

The task list from the previous session is automatically loaded when Duke starts up if the file `duke.txt` exists in the directory `[project_root]/data`.