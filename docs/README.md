# User Guide

## Features 

### Feature 1 
Echo user input

### Feature 2 
Add upcoming tasks

### Feature 3 
Mark tasks done

### Feature 4 
Delete tasks from the list

### Feature 5 
Find tasks using keywords

### Feature 6 
Export tasks to a local file



## Usage

### Echo input

The duke programme echos input of the user

Example of usage: 

`hello`

Expected outcome:

`hello`

### `add task` - Evoke the task management mode

This command triggers the task management mode.
Once triggered, the duke programmes awaits task details 
to be entered and will store the tasks in a list.

Example of usage: 

`add task`
`todo meeting`

Expected outcome:

`Please add tasks`
`Got it. I've added this task: meeting`
`added : [T][✘]meeting`
`Now you have 1 tasks in the list`

### `list` -List the current tasks

This command lists all the current tasks in the list
with reepective date and time details.

Example of usage: 

`list`

Expected outcome:

`1 .[T][✘]dinner`
`2 .[T][✘]football`
`3 .[E][✘]meeting (at: Nov. 11 2020 1800)`

### `delete index` -Delete a task according to the index

This command deletes a task at the given index

Example of usage: 

`list`
`delete 1`
Expected outcome:

`1 .[T][✘]dinner`
`2 .[T][✘]football`
`3 .[E][✘]meeting (at: Nov. 11 2020 1800)`

`Noted. I've removed this task:` 
`Removed : [T][✘]dinner`
`Now you have 2 tasks in the list`

### `done index` -Mark a task done according to the index

This command marks a task done at the given index

Example of usage: 

`done 1`
Expected outcome:

`1 .[T][✓]dinner`
`2 .[T][✘]football`
`3 .[E][✘]meeting (at: Nov. 11 2020 1800)`

`Mark task done` 

### `find keyword` -fina a list of tasks containing the keyword

This command finds a list of tasks containing the keyword
Or it returns a error message

Example of usage: 

`list`
`find dinner`
`find lunch`
Expected outcome:

`1 .[T][✓]dinner`
`2 .[T][✘]football`
`3 .[E][✘]meeting (at: Nov. 11 2020 1800)`

` Here are the matching tasks in your list:`
`____________________________________________________________`
`1 .[T][✓]dinner`

`Here are the matching tasks in your list:`
`No matching results `

### `bye` -Close the programme and saves to a local file

This command terminates the programme and automatically saves 
tasks into a local file. If such a file does not exist, it creates a new one.
Example of usage: 

`bye`

Expected outcome:

`Tasks are being saved now`
`____________________________________________________________`
`Bye. Hope to see you again soon!`
`___________________________________`