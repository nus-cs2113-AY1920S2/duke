# User Guide

## Features 

### 1. Add Task  
Add tasks to the list.

## Usage

### `TODO` 

Adds a task that needs to be completed.

Example of usage: 

`Todo buy dinner`

Expected outcome:

`Got it. I''ve added this task:
[T][✘] buy dinner
Now you have 1 task in the list.`

### `DEADLINE` 

Adds a task that needs to be completed by a certain date.

Example of usage: 

`Deadline buy dinner /by 7pm`

Expected outcome:

`Got it. I''ve added this task: 
[D][✘] buy dinner (by: 7pm)
Now you have 2 tasks in the list.`

### `EVENT` 

Adds an event that is happening on a certain date.

Example of usage: 

`Event dinner /at Friday 7pm`

Expected outcome:

`Got it. I''ve added this task: 
[E][✘] dinner (at: Friday 7pm)
Now you have 3 tasks in the list.`
 
### 2. Delete Task  
Delete tasks from the list.

## Usage

### `DELETE` 

Removes the task specified by the number in the list.

Example of usage: 

`Delete 1`

Expected outcome:

`Got it. I''ve removed this task: 
[T][✘] buy dinner
Now you have 2 tasks in the list.`
 
### 3. Done Task  
Mark tasks as done.
 
## Usage
 
### `DONE` 
 
Indicate the number of the completed task in the list.
 
Example of usage: 
 
`Done 1`
 
Expected outcome:

`Nice! I've marked this task as done: 
[D][✓] buy dinner (by: 7pm)`
  
### 4. List Tasks  
View all the tasks current in your list.
 
## Usage
 
### `LIST` 
 
Specify the "list" word to view list.
 
Example of usage: 
 
`List`
 
Expected outcome:

`Here are the tasks in your list: 
 1.[D][✓] buy dinner (by: 7pm)
 2.[E][✘] dinner (at: Friday 7pm)`
  
### 5. Find Tasks  
Search for that contain the specified keyword.
 
## Usage
 
### `FIND` 
 
Specify the keyword to search for tasks that contain that keyword.
 
Example of usage: 
 
`Find dinner`
 
Expected outcome:

`Here are the matching tasks in your list: `
`1. [D][✓] buy dinner (by: 7pm) `
`2. [E][✘] dinner (at: Friday 7pm)`
 
### 6. Exit Program  
Save the current tasks in your list and exit the program.
  
## Usage
  
### `BYE` 
  
Example of usage: 
  
`bye`
  
Expected outcome:

`Bye. Hope to see you again soon!`
    