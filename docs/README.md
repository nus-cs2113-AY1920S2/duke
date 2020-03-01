# Duke User Guide

## 1. Introduction

Duke is for those who prefer to use a desktop app to manage tasks. 
More importantly, Duke is optimized for those who prefer to work with a Command Line Interface (CLI). 
It includes features that help with making a list of tasks and searching tasks with keywords.

## 2. Quick Start
Install `Java 11` or above and download most updated version of `Individual_Project.jar` 
from [sliu107 GitHub repository](https://github.com/sliu107/duke/releases)  

## 3. Features 

### Feature 0 - Command Format
* Words in `UPPER_CASE` are the parameters to be supplied by the user 
e.g `todo d/DESCRIPTION` `DESCRIPTION` is a parameter which can be used as `todo d/borrow some books`
* The order of the parameters matters. 
e.g.If the command specifies `deadline d/DESCRIPTION /b/BY`, `deadline b/BY /d/Description` is not acceptable.

### Feature 1 - Help
Shows help message which includes examples of every kind of command.

Format: `help`

### Feature 2 - Adding a todo task
Adds a new todo task to Duke

Format: `todo d/DESCRIPTION`

Example: `todo d/borrow some book`  
Adds a todo task with description _borrow some book_

### Feature 3 - Adding a deadline task
Adds a new deadline task to Duke

Format: `deadline d/DESCRIPTION /b/BY` where `b/BY` has a format: `by YYYY-MM-DDTHH:MM:SS`

Example: `deadline d/return book /b/by 2020-03-02T11:00:00`  
Adds a task with description `return book` and deadline `2020/03/02 11:00:00 `

### Feature 4 - Adding an event
Adds a new event to Duke

Format: `event d/DESCRIPTION /a/AT` where `a/AT` has a format: `at YYYY-MM-DDTHH:MM:SS`

Example: `event d/group meeting /a/at 2020-03-02T14:00:00`  
Adds an event with description `group meeting` and start time `2020/03/02 14:00:00`

### Feature 5 - Listing all the tasks
Shows a list of tasks in the Duke.

Format: `list`

### Feature 6 - Done a task
Marks an existing task in the Duke as done.

Format: `done i/INDEX`  
* Done the task at the specified INDEX.
* The index refers to the index number shown in the displayed task list.
* The index must be a positive integer 1, 2, 3, …​

Example: `done 2`  
Marks the second task in the list as done.

### Feature 7 - Finding tasks
Finds tasks by searching keywords.

Format: `find k/KEYWORDS`

Example: `find k/book`  
Finds out the task with `book` in its description.

### Feature 8 - Printing tasks for a date
Prints out all the tasks that occurs on a specific date.

Format: `show d/DATA` where `d/DATA` has a format `YYYY-MM-DD`

Example: `show 2020-03-04`  
Prints out all the tasks that occurs on 2020/03/04.

### Feature 9 - Deleting a task
Deletes an existing task from Duke.

Format: `delete i/INDEX`
* Deletes the task at the specified INDEX.
* The index refers to the index number shown in the displayed task list.
* The index must be a positive integer 1, 2, 3, …​

Example: `delete 1`  
Deletes the first task in the Duke.

### Feature 10 - Exit the program
Exits the program.

Format: `bye`

### Feature 11 - Saving the data
Duke will save the data in the hard disk automatically every time before your exit the program.
There is no need to save manually.

## Usage
### `help` - Giving help message
Prints out examples of commands to help user familiar the format of commands.
             
Example of usage: 
```
help
```
Expected outcome:
```
------------***------------
There are 10 types of commands；todo, deadline, event, list, find, show, done, delete, help and exit
Followings are examples of commands:
todo : todo borrow books
deadline : deadline return books /by 2020-03-02T11:00:00
event : event group meeting /at 2020-03-03T14:00:00
list: list
find: find books
show : show 2020-03-02
done : done 2
delete : delete 1
help : help
exit : bye
------------***------------
```


### `todo` - Adding a todo task

Adds a new todo task to Duke

Example of usage: 

```
todo borrow some books
```
Expected outcome:
```
------------***------------
Got it. I've added this task:
[T] [✘]  borrow some books
Now you have 1 tasks in the list.
------------***------------
```
### `deadline` - Adding a deadline task
Adds a new deadline task to Duke

Example of usage: 
```
deadline return books /by 2020-03-02T11:00:00
```
Expected outcome:
```
------------***------------
Got it. I've added this task:
[D] [✘]  return books  (by: Mar. 2, 2020, 11:00:00 a.m.)
Now you have 2 tasks in the list.
------------***------------
```
### `event` - Adding an event task
Adds a new event task to Duke

Example of usage: 
```
event group meeting /at 2020-03-03T14:00:00
```
Expected outcome:
```
------------***------------
Got it. I've added this task:
[E] [✘]  group meeting  (at: Mar. 3, 2020, 2:00:00 p.m.)
Now you have 3 tasks in the list.
------------***------------
```
### `list` - Listing all the tasks
Prints out a list of tasks with details.
             
Example of usage: 
```
list
```
Expected outcome:
```
------------***------------
Here are the tasks in your list:
1. [T] [✘]  borrow some book
2. [D] [✘]  return books  (by: Mar. 2, 2020, 11:00:00 a.m.)
3. [E] [✘]  group meeting  (at: Mar. 3, 2020, 2:00:00 p.m.)
------------***------------
```
### `done` - Done a task
Mark a specific task as done.
             
Example of usage: 
```
done 2
```
Expected outcome:
```
------------***------------
Nice! I've marked this task as done:
  [✓]  return books 
------------***------------
```
### `find` - Finding a task
Searches for tasks with keywords and shows a list of tasks whose description contains the keyword.
             
Example of usage: 
```
find book
```
Expected outcome:
```
------------***------------
Here are the matching tasks in your list:
1. [T] [✘]  borrow some book
2. [D] [✓]  return books  (by: Mar. 2, 2020, 11:00:00 a.m.)
------------***------------
```
### `show` -Printing tasks due or occur on a date
Prints out a list of tasks that due or occur on a specific dates with detailed information.
             
Example of usage: 
```
show 2020-03-02
```
Expected outcome:
```
------------***------------
[D] [✓] return books  (by: Mar. 2, 2020, 11:00:00 a.m.)
------------***------------
```
### `delete` -Deleting a taskDeleting a task
Deletes an existing task from Duke.
             
Example of usage: 
```
delete 2
```
Expected outcome:
```
------------***------------
Noted. I've removed this task: 
[D] [✓] return books  (by: Mar. 2, 2020, 11:00:00 a.m.)
Now you have 2 tasks in the list.
------------***------------
```
### `bye` -Exiting the program
Saves the changes of the task list to local file before exiting the program and shows exit messages.
             
Example of usage: 
```
bye
```
Expected outcome:
```
------------***------------
Bye. Hope to see you again soon!
------------***------------
```
