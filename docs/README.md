# User Guide

##ALIE
ALIE is a personal task tracker app that uses command line interface.
It helps its users keep track of tasks that are entered into the app, keeping them in a task list.
It is also able to store tasks added into the app and store them, allowing it to 
be retrieved again when the user starts the app again. 

## Feature Summary
* [Add Task: Todo](#todo) - Add a `todo` task to ALIE
* [Add Task: Deadline](#deadline) - Add a `deadline` task to ALIE.
* [Add Task: Event](#event) - Add a `event` task to ALIE.
* [List](#list) - Lists all the tasks added into ALIE.
* [Mark Task as Complete](#done) - Mark a specific task as done in ALIE.
* [Delete Task](#delete) - Remove any task from ALIE. 
* [Find task](#find) - Search for tasks by name using keyword in the task list.
* [Exit](#exit) - Exit ALIE.


## Usage 
### 1. Add Task
ALIE keep track of 3 types of task - `todo`, `deadline` and `event`.
For each task, its type is marked by a symbol. 
If the task is completed, `[Y]` will be shown. If it is not, `[N]` will be shown.
<br/>

* #### keyword: `todo`
  Add a `todo` task to the task list in ALIE, keeping only a description of the task.
  `todo` tasks are marked with the symbol `[T]`.
  The `todo` task will be added to the list in ALIE and also in local file storage.

  Syntax: `todo <description>` 

  Example of usage: `todo quiz for CS2113T`

  Expected outcome:
  ```
  Got it. I've added this task:
     [T][N] quiz for CS2113T
  Now you have 1 tasks in the list.    
  ```
___
* #### keyword: `deadline`
  Add a `deadline` task to the list in ALIE, keeping a description and due date of the task.
  It is able to keep track of time in the format `YYYY-MM-DD`, converting them to `dd MMM YYYY`. 
  `deadline` tasks are marked with the symbol `[D]`.
  The `deadline` task will be added to the list in ALIE and also in local file storage.

  Syntax: `deadline <description> /by <date>` 

  Example of usage: `deadline CS2113T iP final submission /by 2020-03-02`

  Expected outcome:
  ```
  Got it. I've added this task:
    [D][N] CS2113T iP final submission (by: 02 Mar 2020)
  Now you have 2 tasks in the list.
  ```
  
  > Although other date formats are accepted, a warning will be shown as it is unable to convert the date properly.

___
* #### keyword: `event` 
  Add an `event` task to the list in ALIE, keeping a description and duedate of the task.
  It it able to keep track of time in the format `YYYY-MM-DD`, converting them to `dd MMM YYYY`.
  `event` tasks are marked with the symbol `[E]`.
  The `event` task will be added to the list in ALIE and also in local file storage.

  Syntax: `event <description> /at <time>` 

  Example of usage: `event NUS computing career fair /at 2020-04-02`

  Expected outcome:
  ```
  Got it. I've added this task:
    [E][N] NUS computing career fair (at: 02 Apr 2020)
  Now you have 3 tasks in the list.
  ```
   > Although other date formats are accepted, a warning will be shown as it is unable to convert
   the date properly.
___
### 2. List Task
#### keyword: `list`
Displays a numbered list with all the tasks stored in ALIE.

Syntax: `list` 

Expected outcome:
```
Here are the tasks in your list: 
1.[T][N] quiz for CS2113T
2.[D][N] CS2113T iP final submission (by: 02 Mar 2020)
3.[E][N] NUS computing career fair (at: 02 Apr 2020)
4.[T][Y] assignment 4
```
___
### 3. Complete Task
#### keyword: `done`
Mark a task in the list in ALIE as done. Changing the symbol from `[N]` to `[Y]` 

Syntax: `done <index of task in list>` 

Example of usage: `done 1`

Expected outcome:
```
Nice! I've marked this task as done:
  1. [T][Y] quiz for CS2113T
```
___
### 4. Delete Task
#### keyword: `delete`
Delete/Remove a task from the list in ALIE.

Syntax: `delete <index of task in list>` 

Example of usage: `delete 4`

Expected outcome:
```
Noted. I've removed this task:
  [T][N] assignment 4
Now you have 3 tasks in the list.
```
___
### 5. Find Task
#### keyword: `find`
Find tasks by searching for a specified key. 
The key can be a word, phrase or task name.
It will display any task whose name matches the specific key.

Syntax: `find <key>` 

Example of usage: `find CS2113T`

Expected outcome:
```
Here are the tasks in your list: 
1.[T][N] quiz for CS2113T
2.[D][N] CS2113T iP final submission (by: 02 Mar 2020)
```
___
### 6. Exit ALIE
#### keyword: `bye`
Exit the program.

Syntax: `bye` 

Expected outcome:
```
Exiting A.L.I.E...
-------------------------------------
Bye-bye! Hope to see you again soon.
```
