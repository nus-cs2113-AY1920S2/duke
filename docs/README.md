# User Guide

## Features 

### Add task
Add different types of tasks to a list.
* Todo
* Event
* Deadline

### List tasks
List all the existing tasks, with their completion status.

### Delete task
Remove a task from the list.

### Mark task as complete
Sets a task's completion status to completed.

### Find task
Use a keyword to find tasks that include the keyword in their task description.

## Usage

### `todo` - add a Todo task to the list

Adds a task of type 'Todo' to the task list.

Example of usage: 

`todo complete individual project`

Expected outcome:

    ...................................................
 	 Dook has added task: 
 	  [T][✘] complete individual project
 	 1 task(s) in the list now!
    ...................................................

### `event` - add an Event task to the list

Adds a task of type 'Event' to the task list. Use /at to specify event date or time.

Example of usage: 

`event party /at 1800-2200h`

Expected outcome:

    ...................................................
	 Dook has added task: 
	  [E][✘] party  (at: 1800-2200h)
	 2 task(s) in the list now!
    ...................................................
    
### `deadline` - add a Deadline task to the list

Adds a task of type 'Deadline' to the task list. Use /by to specify the deadline.
Input deadline in format YYYY-MM-DD for Dook to recognise and print the date in MM DD YYYY style.

Example of usage: 

`deadline homework /by 2020-02-26`

Expected outcome:

    ...................................................
	 Dook has added task: 
	  [D][✘] homework  (by: Feb 26 2020)
	 3 task(s) in the list now!
    ...................................................
    
### `list` - list all existing tasks

 Shows a numbered list of all existing tasks, with their descriptions and completion statuses.

Example of usage: 

`list`

Expected outcome:

    ...................................................
	 Dook will list your tasks now:
	 1. [T][✘] complete individual project
	 2. [E][✘] party  (at: 1800-2200h)
	 3. [D][✘] homework  (by: Feb 26 2020)
    ...................................................
    
### `done` - mark a task as done

Set a task's completion status to done.

Example of usage: 

`done 1`

Expected outcome:

    ...................................................
	 Dun dun dun dun! This task is done:
	   [T][✓] complete individual project
    ...................................................
    
### `delete` - remove a task from the list

Removes an existing task from the task list.

Example of usage: 

`delete 1`

Expected outcome:

    ...................................................
	 This task has been whisked out of existence:
	  [T][✓] complete individual project
	 2 task(s) remaining.
    ...................................................
    
### `find` - find matching tasks

Finds and shows tasks which contain the matching keyword.

Example of usage: 

`find par`

Expected outcome:

    ...................................................
	 Dook has found the following tasks: 
	  1. [E][✘] party  (at: 1800-2200h)
    ...................................................
    
### `bye` - exit the program

Exits Dook. All existing tasks are saved in a .txt file and restored upon startup.

Example of usage: 

`bye`

Expected outcome:

    ...................................................
	 Goodbye, see you in the seventh dimension!
                   *       +
             '                  |
         ()    .-.,="``"=.    - o -
               '=/_       \     |
            *   |  '=._    |
                 \     `=./`,        '
              .   '=.__.=' `='      *
     +                         +
          O      *        '       .
    ...................................................