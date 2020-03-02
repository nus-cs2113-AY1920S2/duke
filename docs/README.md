# User Guide

## Features 

### List Tasks 
  List the tasks currently stored in the application.
  
### Add Task
  Adds a task into the application.
  A task can be of different types:
  1. Todo (Contains task description only)
  2. Deadline (Contains task description and deadline)
  3. Event (Contains task description and date of event)
  
### Mark task as done
  When a task is completed, the user can mark the task as done.<br/>
  If the task is done, it is marked as [Done].<br/>
  If the task is not done, it is marked as [Not Done].

### Find Task
  The user can input a keyword. The application searches the task descriptions <br/>
  in the list and displays the tasks that contain the keyword.
  
### Delete Task
  Deletes a task from the application.
  
### Exit
  Exits the application.

## Usage

### `Keyword` - Describe action

Describe action and its outcome.

Example of usage: 

`keyword (optional arguments)`

Expected outcome:

`outcome`

### `todo` - Adds a todo task
Format: `todo TASK_DESCRIPTION`

Example of usage | Expected outcome
------------ | -------------
`todo Buy Present`|`New task added:` <br/>`[T][Not Done]  Buy present` <br/>`Now you have 1 tasks in the list.`

### `deadline` - Adds a deadline task
Format: `deadline TASK_DESCRIPTION /by DEADLINE`

Example of usage | Expected outcome
------------ | -------------
`deadline Finish iP /by 2/3/2020 1200`|`New task added:` <br/>`[D][Not Done]  Finish iP  (by: Mar 2 2020 12:00)` <br/>`Now you have 2 tasks in the list.`

### `event` - Adds an event task
Format: `event TASK_DESCRIPTION /by DATE`

Example of usage | Expected outcome
------------ | -------------
`event John's birthday /at 22/3/2020 1300`|`New task added:` <br/>`[E][Not Done]  John's birthday  (at: Mar 22 2020 01:00)` <br/>`Now you have 3 tasks in the list.`

### `list` - Lists the tasks in the application
Format: `list`

Example of usage | Expected outcome
------------ | -------------
`list`|`Here are your tasks:` <br/>`1. [T][Not Done]  Buy present` <br/>`2. [D][Not Done]  Finish iP  (by: Mar 2 2020 12:00)` <br/> `3. [E][Not Done]  John's birthday  (at: Mar 22 2020 01:00)`

### `done` - Marks a task as done
Format: `done INDEX`

Example of usage | Expected outcome
------------ | -------------
`done 1`|`Nice! I've marked this task as done:` <br/>`[T][Done]  Buy present`

### `delete` - Deletes a task from the list
Format: `delete INDEX`

Example of usage | Expected outcome
------------ | -------------
`delete 3`|`Noted. I've removed this task:` <br/>`[E][Not Done]  John's birthday  (at: Mar 22 2020 01:00)` <br/> `Now you have 2 tasks in the list.`

### `find` - Finds the tasks that contains the keyword in their description
Format: `find INDEX`

Example of usage | Expected outcome
------------ | -------------
`find ip`|`Here are the matching tasks in your list:` <br/>`1. [D][Not Done]  Finish iP  (by: Mar 2 2020 12:00)`

### `bye` - Exits the application
Format: `bye`

Example of usage | Expected outcome
------------ | -------------
`bye`|`Bye. Hope to see you soon!`
