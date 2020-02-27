# User Guide

## What is Duke?
Duke is a personal task manager which records your tasks, remembers the status 
of your tasks, and shows you all your tasks at one go. Duke uses a command-line 
interface, where single-line user commands are used for interaction. Duke also 
stores your tasks locally, so that your data persists between sessions.

## Features 
* [Add Task: Todo](#todo) - Add a `todo` to the task list
* [Add Task: Deadline](#deadline) - Add a `deadline` to the task list
* [Add Task: Event](#event) - Add an `event` to the task list
* [Delete Task](#delete) - Delete a task from the task list
* [Mark Task as Complete](#done) - Set the status of a task to `done`
* [View Task List](#list) - Show all tasks added to Duke's task list
* [Find a Task](#find) - Search for a task by name
* [Exit](#bye) - Exit Duke

## Usage
### Add a Task  
Duke can track 3 different types of tasks, which are `todo`, `deadline`, and `event`\
The format for adding a task is `<TASK_TYPE> <TASK_PARAMETERS>`
---
#### `todo`
Add a `todo` task to the task list. The `todo` contains a description of the task.

Syntax  
`todo <description>`

Example  
`todo buy bread`  

Expected Outcome
```
Task added:
  [T][X] buy bread
You have 1 task in the list
```
---
#### `deadline`
Add a `deadline` task to the task list. The `deadline` contains a description of 
the task, and a date which indicates when the task is due. The date is specified 
in the format `DD/MM/YY`, and the time is specified in the 24h format `HHMM`.

Syntax  
`deadline <description> /by <date> <time>`

Example  
`deadline 2113T iP final submission /by 02/03/20 1200`

Expected Outcome  
```
Task added:
  [D][X] 2113T iP final submission (by: Mon 02 Mar 2020 12:00)
You have 2 tasks in the list
```
---
#### `event`
Add an `event` task to the task list. The `event` contains a description of the 
task, and a date, start time, and end time which indicates when the task takes 
place. The date is specified in the format `DD/MM/YY`, and the times are 
specified in the 24h format `HHMM`.

Syntax  
`event <description> /at <date> <startTime> - <endTime>`

Example  
`event tP meeting 7 /at 04/03/20 1400 - 1500`  

Expected Outcome  
```
Task added:
  [E][X] tP meeting 7 (at: Wed 04 Mar 2020 14:00 - 15:00)
You have 3 tasks in the list
```
---
### View all Tasks
#### `list`
Shows a list of all tasks stored by Duke.

Syntax  
`list`  

Example  
`list`  

Expected Outcome  
```
Quite a few tasks you got there
     1. [T][X] buy bread
     2. [D][X] 2113T iP final submission (by: Mon 02 Mar 2020 12:00)
     3. [E][X] tP meeting 7 (at: Wed 04 Mar 2020 14:00 - 15:00)
```
---
### Delete a Task
#### `delete`
Removes a task from the task list. The task to be removed is identified by 
its number shown in the `list` command.  

Syntax  
`delete <taskNumber>`

Example  
`delete 1`

Expected Outcome
```
Task deleted:
  [T][X] buy bread
You have 2 tasks in the list
```
---
### Mark a Task as completed
#### `done`
Change the status of a task from not done to done. The task to be marked as done 
is identified by its number shown in the `list` command.

Syntax  
`done <taskNumber>`  

Example  
`done 2`  

Expected Outcome:  
```
Well, that's one task down
  [E][O] tP meeting 7 (at: Wed 04 Mar 2020 14:00 - 15:00)
```
---
### Find a specific Task
#### `find`
Searches the task list for tasks with names that contain the query, then shows 
all matching tasks to the user.

Syntax  
`find <query>`  

Example  
`find iP`  

Expected Outcome  
```
Are these what you're looking for?
  1. [D][X] 2113T iP final submission (by: Mon 02 Mar 2020 12:00)
```
---
### Exit Duke
#### `bye`
Closes the Duke program.

Syntax  
`bye`  

Example  
`bye`

Expected Outcome  
`Bye then`  

---
