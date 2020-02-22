# User Guide

## Features 

### Display all Tasks
User can view all the tasks and its completion status as indicated with a tick or cross.

### Addition of New Task
User can choose to add task of different types (Todo, Deadline, Event)

### Marking Task as Completed
User can indicate tasks as done.

### Removal of Task
User can delete task that he/ she deems irrelevant.

### Searching of Task
User can search of Task that matches the search keyword

## Usage

### `todo <task_description>` - Adding a Todo task

Adding a task with the `todo` type 

Example of usage | Expected outcome
-----------------|------------------
`todo read book` | Got it. I've added this task: [T][✘] read book

### `deadline <deadline_description> /by <date>` - Adding a Deadline Task
Adding a task with the `deadline` type

Example of usage | Expected outcome
-----------------|------------------
`deadline return book /by june 6th` | Got it. I've added this task: [D][✘] return book (by: june 6th)

### `event <event_description> /at <date>` - Adding a Event Task
Adding a task with the `event` type

Example of usage | Expected outcome
-----------------|------------------
`event project meeting /at Aug 6th 2-4pm` | Got it. I've added this task: [E][✘] project meeting (at: Aug 6th 2-4pm)

### `list` - View all tasks in Duke
Displays all task that were added, both completed and uncompleted

Example of usage: `list`

Example of usage | Expected outcome
-----------------|------------------
`list` |  1. [T][✘] read book <br> 2. [D][✘] return book (by: june 6th) <br> 3. [E][✘] project meeting (at: Aug 6th 2-4pm) <br> 4. [T][✘] join sports club <br> 5. [T][✘] borrow book <br> 6. [D][✘] return book (by: Sunday) <br> 7. [E][✘] project meeting (at: Mon 2-4pm)`

### `done (item_number)` - Mark Task as Done
Indicate task as completed by its item number

Example of usage | Expected outcome
-----------------|------------------
`done 1` |  Nice! I've marked this task as done: [T][✓] read book

### `delete (item_number)` - Delete Specific Task
Remove task by its item number

Example of usage | Expected outcome
-----------------|------------------
`delete 2` | Item No.2 is removed from list

### `find <search_keyword>` - Find Task with the Specified Keyword(s)
Search for tasks that are relevant to the search keyword

Example of usage | Expected outcome
-----------------|------------------
`find book` | 1.[T][✓] read book <br> 2.[D][✓] return book (by: June 6th)

### `bye` - Exit the Program
Stop running Duke

Example of usage | Expected outcome
-----------------|------------------
`bye` | Bye bye! Talk to me again soon!