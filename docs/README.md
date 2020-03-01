# User Guide

## Features 

### Add a Task
User can add tasks like Todo, Event, Deadline with their descriptions and date/time if applicable.

### Delete a Task
User can delete the tasks by providing task number.

### Mark Task as completed
User can indicate tasks as done whenever they complete the task.

### Find relevant Tasks
User can search for all Tasks that matches their input keyword.

### List all Tasks
User can view all the tasks and its status and description.


## Usage

### `todo <task_description>` - Adding a Todo task
Adding a task of `todo` type.

Example of usage | Expected outcome
-----------------|------------------
`todo read book` | Got it. I've added this task: [T][✘] read book You now have 1 task(s) in the list.

### `deadline <deadline_description> /by <date>` - Adding a Deadline Task
Adding a task of `deadline` type.

Example of usage | Expected outcome
-----------------|------------------
`deadline return book /by Feb 6th` | Got it. I've added this task: [D][✘] return book (by: Feb 6th) You now have 1 task(s) in the list.

### `event <event_description> /at <date>` - Adding a Event Task
Adding a task of `event` type.

Example of usage | Expected outcome
-----------------|------------------
`event study MA1521 (at: Monday 2-4pm)` | Got it. I've added this task: [E][✘] study MA1521 (at: Monday 2-4pm) You now have 1 task(s) in the list.

### `delete (item_number)` - Delete Specific Task
Remove task by its index in the list.

Example of usage | Expected outcome
-----------------|------------------
`delete 2` | Got it. I've removed this task: [E][✘] study MA1521 (at: Monday 2-4pm) You now have 0 task(s) in the list.

### `done (item_number)` - Mark Task as Done
Mark task as completed by its index in the list.

Example of usage | Expected outcome
-----------------|------------------
`done 1` |  Nice! I've marked this task as done: [T][✓] read book

### `find <search_keyword>` - Find Task with the Specified Keyword(s)
Search for tasks that are relevant to the search keyword by user.

Example of usage | Expected outcome
-----------------|------------------
`find book` | Here are the matching tasks in your list: 1.[T][✓] read book <br> 2.[D][✓] return book (by: Feb 6th)

### `list` - View all tasks in Duke
Displays all task in the current list.

Example of usage: `list`

Example of usage | Expected outcome
-----------------|------------------
`list` | Here are the duke.tasks in your list: 1. [T][✘] read book <br> 2. [D][✘] return book (by: Feb 6th) <br> 3. [E][✘] study MA1521 (at: Monday 2-4pm) <br> 

### `bye` - Exit the Program
Terminate Duke.

Example of usage | Expected outcome
-----------------|------------------
`bye` | Bye. Hope to see you again soon!



