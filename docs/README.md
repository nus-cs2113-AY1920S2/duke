# User Guide :negative_squared_cross_mark:

## Introduction
Duke is a command-line interface task manager. You can manage your tasks, set deadlines and view upcoming tasks. The list is then saved automatically when you exit.

## Features 
1. Managing tasks 
  * Your tasks can be added using `todo`, `deadline` and `event` Commands or deleted with the `delete` Command.
2. Marking your tasks as completed
  * Track the completion status of your tasks by using the `done` Command.
3. Display all your tasks 
  * To view the tasks you have so far, use the `list` Command.
  * If you would like to find a specific task using a keyword, use the `find` Command.
4. Saving your data
  * Your tasks and information are stored automatically when you add a task into the program. Upon closing and reopening of Duke, your data is retained. 

### Adding Tasks
There are 3 ways you can add tasks to your task list, namely - todo, deadline or event. More details can be found below.

## Usage

### `todo` - Creates a Todo task

This commands adds a todo task to your current list.

Format:
`todo <description>`

* `<description>` is the description of the todo

Example of usage: 

`todo code for CS2113T`

Expected outcome:

```
____________________________________________________________ 
Got it. I've added this task:
  [T][✘] study for CS2113T
Now you have 1 task in the list
____________________________________________________________
```
>
>**Notice:**
>If created successfully, it appears as a `[T]` beside the description to represent a todo task.
>`[✘]` represents an incomplete task.
>

### `deadline` - Creates a Deadline task

This command is created with a description with a deadline date and time. 


Format: 
`deadline <description><space></by><date><space><time>`

* `<description>` is the description of the task with a deadline
* `<space>` represents a white space
* `</by>` is a **required** parameter
* `<date>` must follow the dd/mm/yyyy format
* `<time>` must follow the hhmm format

Example of usage: 

`deadline study /by 22/05/2020 1530`

Expected outcome:

```
____________________________________________________________
Got it. I've added this task:
 [D][✘] study (by:Fri 22 May 2020 03:30 pm)
Now you have 2 task in the list
____________________________________________________________
```
>
>**Notice:**
>If created successfully, it appears as a `[D]` beside the description to represent a deadline task.
>`[✘]` represents an incomplete task.
>

### `event` - Creates a Event task

Similar to deadline feature, an event task is created with both the description parameter and a deadline date and time.

Format: 
`event <event><space></at><date><space><time>`

* `<event>` is the description of the task
* `<space>` represents a white space
* `</at>` is a **required** parameter
* `<date>` must follow the dd/mm/yyyy format
* `<time>` must follow the hhmm format

Example of usage: 

`event party /at 23/05/2020 0030`

Expected outcome:

```
____________________________________________________________ 
Got it. I've added this task:
   [E][✘] party (at:Sat 23 May 2020 12:30 am)
Now you have 3 task in the list
____________________________________________________________
```
>
>**Notice:**
>If created successfully, it appears as a `[E]` beside the description to represent an Event task.
>`[✘]` represents an incomplete task.
>

### `done` - Marks a task as completed

This command marks an existing task as Done.

Format: 
`done <task number>`

* `<task number>` represents a index of the task in the current task list

Example of usage: 

`done 2`

Expected outcome:

```
____________________________________________________________
Nice! I've marked this task as done:
  [E][✓] study (by:Fri 22 May 2020 03:30 pm)
____________________________________________________________
```
>
>**Notice:**
>If created successfully, a `✓` appears beside the task.
>

### `list` - Displays a list of current tasks

This command shows you the existing list of tasks.

Format: `<list>`

Example of usage: 

`list`

Expected outcome:
```
____________________________________________________________
Here are the tasks in your list:
1.[T][✘] study for CS2113T
2.[D][✓] study (by:Fri 22 May 2020 03:30 pm)
3.[E][✘] party (at:Sat 23 May 2020 12:30 am)
____________________________________________________________

```

### `delete` - Deletes a task from the list

This command allows the tasks to be deleted from the task list.

Format: `delete <Task Number>`

* `<task number>` represents a index of the task in the current task list

Example of usage:

`delete 2`

Expected outcome:
```
____________________________________________________________
 Noted. I've removed this task: 
  [D][✘] study (by:Fri 22 May 2020 03:30 pm)
Now you have 4 task in the list
____________________________________________________________
```

### `find` - Finds a task with matching keyword

This command finds a task in the existing task list with the same keyword.

Format: `find <Keyword>`

* `<Keyword>` represents the name of the task you wish to look for in the current task list.

Example of usage:

`find party`

Expected outcome:

```
____________________________________________________________

 Here are the matching tasks in your lists:
1.[E][✘] party (at:Sat 23 May 2020 12:30 am)
____________________________________________________________
```

### `bye` - Exits the program
 
Upon exit, this command automatically saves the existing list of tasks in a file.

Format: `<bye>`

Example of usage:
`bye`

Expected outcome:
```
Bye. Hope to see you again soon!
```
