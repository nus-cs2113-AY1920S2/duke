# Duke User Guide

## Content Page
1.  **Introduction**

2.  **Features**
    *   2.1 Choose Mode
    
    *   2.2 Add Task
    
        *   2.2.1 Add ToDo Task
        
        *   2.2.2 Add Event Task
        
        *   2.2.3 Add Deadline Task
        
    *   2.3 Mark Task As Done
    
    *   2.4 List Tasks
    
    *   2.5 Delete Task
    
    *   2.6 Find Related Task
    
    *   2.7 Exit Program
    
    *   2.8 Save And Load Data
    
3.  **Command Summary**
    

## Introduction
Duke is a task management software which help people keep track of daily tasks.

It is a java application optimized for users who want to type fast with Command Line Interface(CLI).

## Features
###`Command Format`

Words in `UPPER_CASE` are the parameters to be supplied by the users.

For example: In `todo <TASK_DESCRIPTION>`,`TASK_DESCRIPTION` is a parameter used to describe a ToDo task.

### `2.1 Choose Mode`
Select one mode from two options:
1.  echo mode (simply repeat user's input)
2.  command mode (help manage user's tasks) `(Recommended)`

Format: `mode <MODE_INDEX>`

### `2.2 Add Task`

#### `2.2.1 Add ToDo Task`
Add a todo task to the list.

Format: `todo <TASK_DESCRIPTION>`

#### `2.2.2 Add Event Task`
Add a event task to the list.

Format: `event <TASK_DESCRIPTION> /at <TIME_DESCRIPTION>`

#### `2.2.3 Add Deadline Task`
Add a deadline task to the list.

Format: `deadline <TASK_DESCRIPTION> /by <TIME_DESCRIPTION>`

### `2.3 Mark Task As Done`
Mark a task as done with its index.
Format: `done <TASK_INDEX>`

### `2.4 List Tasks`
List all the tasks in the list.

Format: `list`

### `2.5 Delete Task`
Remove a task from the list with its index.

Format: `delete <TASK_INDEX>`

### `2.6 Find Related Task`
Search for all tasks that relate to certain keywords.

Format: `find <KEYWORDS>`

### `2.7 Exit Program`
Exit the program after use.

Format: `bye`

### `2.8 Save And Load Data`
All tasks in the list will be automatically saved in the hard disk before program exits.

The saved tasks will be restored automatically when launching the program later.

## Command Summary
### todo
Example:
~~~
todo read book
~~~
Expected Output
~~~
    ________________________________
    added: read book
    Now there are totally 1 task in the list
    ________________________________
~~~

### event 
~~~
event attend meeting /at 2020-03-01
~~~
Expected Output
~~~
    ________________________________
    added: attend meeting
    Now there are totally 2 tasks in the list
    ________________________________
~~~

### deadline
Example
~~~
deadline finish IP /by tomorrow
~~~
Expected Output
~~~
    ________________________________
    added: finish IP
    Now there are totally 3 tasks in the list
    ________________________________
~~~

### list 
Example
~~~
list
~~~
Expected Output
~~~
    ________________________________
    1.[T][×] read book
    2.[E][×] attend meeting (at: Mar 01 2020)
    3.[D][×] finish IP (by: tomorrow)
    ________________________________
~~~

### done
Example
~~~
done 1
~~~
Expected Output
~~~
    ________________________________
    Congratulations! You have just finished this task:
    [T][√] read book
    ________________________________
~~~

### delete
Example
~~~
delete 3
~~~
Expected Output
~~~
    ________________________________
    Noted. I've removed this task:
    [D][×] finish IP (by: tomorrow)
    There are totally 2 tasks in the taskList
    ________________________________
~~~

### find
Example
~~~
find book
~~~
Expected Output
~~~
    ________________________________
    Here are the matching tasks in your list:
    1.[T][√] read book
    ________________________________
~~~

### bye
Example
~~~
bye
~~~
Expected Output
~~~
    ________________________________
    Don't leave me alone! Please come back soon!
    ________________________________
~~~
