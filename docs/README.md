# Duke User Guide

Duke is a command line application to keep track of tasks.
Duke currently supports the following types of tasks:  
* Todo
* Event
* Deadline

Duke also supports the following commands to operate on your tasks:  
* List
* Done
* Delete
* By
* On
* Find

Duke also automatically saves your tasks to disk after every modification and loads
tasks from file automatically on startup

## Features 

### Todo
Create a Todo task with a description

### Event
Create an Event task with a description and a date/time

### Deadline
Create a Deadline task with a description and a due date/time

### List
List all your tasks

### Done
Mark a task as done

### Delete
Delete a task

### By
List all tasks with associated dates/times before specified date/time

### On
List all tasks with associated dates on specified date

### Find
List all tasks with descriptions containing specified search term

### Saving to disk
Duke will automatically save your tasks to a relative path /data/tasks.txt after
every operation that modifies the list of tasks

### Loading from disk
Duke will automatically load your tasks from disk at startup

## Usage

### `todo` - Create a new Todo task

Add a new Todo task to your list of tasks

Example of usage: 

`todo math homework`

Expected outcome:

```
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~  
Added this task:  
[T][✘] math homework  
Now you have 1 task(s) in your list  
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
```

### `event` - Create a new Event task

Add a new Event task to your list of tasks

Example of usage: 

`event math class /at 31/7/2020 8:30`

Expected outcome:

```
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Added this task:
[E][✘] math class (at: 31/7/2020 8:30)
Now you have 2 task(s) in your list
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
```

### `deadline` - Create a new Deadline task

Add a new Deadline task to your list of tasks

Example of usage: 

`deadline finish math homework /by 5/10/2021 16:30`

Expected outcome:

```
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Added this task:
[D][✘] finish math homework (by: 5/10/2021 16:30)
Now you have 3 task(s) in your list
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
```

### `List` - List your tasks

Print a list of your tasks to the console

Example of usage: 

`list`

Expected outcome:

```
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
These are your tasks:
1. [T][✘] math homework
2. [E][✘] math class (at: 31/7/2020 8:30)
3. [D][✘] finish math homework (by: 5/10/2021 16:30)
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
```

### `Done` - Mark a task as done

Mark a specified task as done based on task number

Example of usage: 

`done 2`

Expected outcome:

```
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Task 2 has been marked as done
[E][✓] math class (at: 31/7/2020 8:30)
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
```

### `Delete` - Delete a task

Delete a specified task based on task number

Example of usage: 

`delete 1`

Expected outcome:

```
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Deleted task 1:
[T][✘] math homework
Now you have 2 tasks left in your list
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
```

### `By` - List tasks by date/time

List all tasks before a specified date/time

Example of usage: 

`by 20/2/2021 4:30`

Expected outcome:

```
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
These are your tasks by 20/2/2021 4:30:
1. [E][✓] math class (at: 31/7/2020 8:30)
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
```

### `On` - List tasks on date

List all tasks on a specified date

Example of usage: 

`on 5/10/2021`

Expected outcome:

```
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
These are your tasks on 2021-10-05:
2. [D][✘] finish math homework (by: 5/10/2021 16:30)
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
```

### `Find` - Find tasks containing search term

List all tasks whose descriptions contain specified search term

Example of usage: 

`find homework`

Expected outcome:

```
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Here are your search results:
2. [D][✘] finish math homework (by: 5/10/2021 16:30)
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
```

