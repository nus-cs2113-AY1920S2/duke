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


Caution: Once this command is entered, all inputs will be put through strict checking to avoid illegal inputs. Echo function will be disabled.
Commands allowed after "add task" are: adding task items, list, delete, find, done, bye.


Example of usage: 

<br />`add task`
<br />`todo meeting`
<br />`deadline call david/by 2020-04-01`
<br />`event party/at 2020-04-02 1800`


Expected outcome:

<br />`Please add tasks`
<br />`Got it. I've added this task: meeting`
<br />`added : [T][✘]meeting`
<br />`Now you have 1 tasks in the list`
<br />`Got it. I've added this task: call david`
<br />`added : [D][✘]call david (by: 2020-04-01)`
<br />`Now you have 2 tasks in the list`
<br />`Got it. I've added this task: party`
<br />`added : [E][✘]party (at: 2020-04-02 1800)`
<br />`Now you have 3 tasks in the list`

Format for task items:
<br />`todo [DISCRIPITION]`
<br />`deadline [DISCRIPITION]/by [DATE IN YYYY-MM-DD]`
<br />`event [DISCRIPITION]/at [DATE IN YYYY-MM-DD]-[TIME IN HH-MM]`

### `list` -List the current tasks

This command lists all the current tasks in the list
with reepective date and time details.

Example of usage: 

<br />`list`

Expected outcome:

<br />`1 .[T][✘]dinner`
<br />`2 .[T][✘]football`
<br />`3 .[E][✘]meeting (at: Nov. 11 2020 1800)`

### `delete index` -Delete a task according to the index

This command deletes a task at the given index

Example of usage: 

<br />`list`
<br />`delete 1`

Expected outcome:

<br />`1 .[T][✘]dinner`
<br />`2 .[T][✘]football`
<br />`3 .[E][✘]meeting (at: Nov. 11 2020 1800)`

<br />`Noted. I've removed this task:` 
<br />`Removed : [T][✘]dinner`
<br />`Now you have 2 tasks in the list`

### `done index` -Mark a task done according to the index

This command marks a task done at the given index

Example of usage: 

`done 1`

Expected outcome:

<br />`1 .[T][✓]dinner`
<br />`2 .[T][✘]football`
<br />`3 .[E][✘]meeting (at: Nov. 11 2020 1800)`

<br />`Mark task done` 

### `find keyword` -fina a list of tasks containing the keyword

This command finds a list of tasks containing the keyword
Or it returns a error message

Example of usage: 

<br />`list`
<br />`find dinner`
<br />`find lunch`

Expected outcome:

<br />`1 .[T][✓]dinner`
<br />`2 .[T][✘]football`
<br />`3 .[E][✘]meeting (at: Nov. 11 2020 1800)`

<br />` Here are the matching tasks in your list:`
<br />`1 .[T][✓]dinner`

<br />`Here are the matching tasks in your list:`
<br />`No matching results `

### `bye` -Close the programme and saves to a local file

This command terminates the programme and automatically saves 
tasks into a local file. If such a file does not exist, it creates a new one.
Example of usage: 

`bye`

Expected outcome:

<br />`Tasks are being saved now`
<br />`Bye. Hope to see you again soon!`

## Matters needing attention
For the first time the programme is run, it displays a message saying file not found. 
This is normal and does not require attention. Once a new file is created by the
programme, please do not move the file into other folders.