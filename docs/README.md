# Duke User Guide

## Features 

### List All Tasks
The user can view all current tasks and their completion status

### Add New Tasks 
The user can add tasks of type Todo/Deadline/Event
* Todo: These are Todo tasks that require no date
* Deadline: These are Deadline tasks that have a due by date
* Event: These are Event tasks that have a given date

### Mark Task as Done
The user can indicate that the given task is complete.
Tasks have a default completion status of N. A completed status is indicated by Y.

### Delete Task
The user can delete a given task based on task index

### Find Task
The user can find a task that matches the given search keyword

### Quit Program
The user can safely exit the program

## Usage

### `todo <task name>` - Add a Todo task
Adding a task of type `todo`

Example of usage | Expected Outcome
---------------- | ---------------- 
`todo buy food` | Got it. I've added this task: <br/>[T][N] eat dinner

### `deadline <deadline name> /by <date>` - Add a Deadline task
Adding a task of type `deadline`

Example of usage | Expected Outcome
---------------- | ---------------- 
`deadline complete homework /by Monday` | Got it. I've added this task: <br/>[D][N] complete homework (by: Monday)

### `event <event name> /at <date>` - Add an Event task
Adding a task of type `event`

Example of usage | Expected Outcome
---------------- | ---------------- 
`event carnival /at Sunday` | Got it. I've added this task: <br/>[E][N] carnival (at: Sunday)

### `list` - List all Tasks
Displays all the current tasks in the task list

Example of usage | Expected Outcome
---------------- | ---------------- 
`list` | Current task list:<br/>1. [T][N] buy food<br>2. [D][N] complete homework (by: Monday)<br>3. [E][N] carnival (at: Sunday)

### `done <index number>` - Mark given Task as Done
Mark the task in the task list of the given index as done

Example of usage | Expected Outcome
---------------- | ---------------- 
`done 2` | I've marked this task as done:<br>[D][Y] complete homework (by: Monday)

### `delete <index number>` - Remove given Task from Task List 
Removes the task in the task list of the given index

Example of usage | Expected Outcome
---------------- | ---------------- 
`delete 2` | I've removed this task:<br>[D][Y] complete homework (by: Monday)<br>You have 2 task(s) left.

### `find <keyword>` - Find any Tasks with a given Keyword
Searches through the task list for any tasks that match the keyword 

Example of usage | Expected Outcome
---------------- | ---------------- 
`find buy` | Given keyword: buy<br>Here are the matching tasks: <br>1. [T][N] buy food

### `bye` - Exit the Program
Stops the Duke program

Example of usage | Expected Outcome
---------------- | ---------------- 
`bye` | Bye. Hope to see you again!