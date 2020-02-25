# User Guide
# Introduction
Duke is a desktop personal planner for those who **prefer to use command line**.
If you like the freedom of command line, jump to *Quick Start* to get started. Enjoy!

# Quick Start
1. Ensure you have `Java 11` or above installed in your computer.
1. Download the latest `Duke.jar` [here](https://github.com/lowjiayee/duke/releases).
1. Store `Duke.jar` at the folder you intend to use as your home folder.
1. Navigate to the folder in your Command Prompt (Windows) or Terminal (MacOS & Linux).
1. Type `java -jar Duke.jar` to start the app.

Refer to the next section *Feature* for details of each command.

# Features

**Save/load**: Any previous tasks added to this app earlier will be loaded to the app during startup. Conversely, all tasks in the app will be saved when the app is closed.

## Understanding the syntax
> - Words in `UPPER_CASE` are the parameters to be supplied by the user.
> 
>   E.g. in `event NAME /at TIME`, `NAME` and `TIME` are parameters which can be replaced to be `event meeting /at 5pm`.
> 
> - `/IDENTIFIER` such as `/by` and `/at` must be supplied to recognise the parameters after them.

## List all tasks

Syntax: `list`

Expected output:

- If you have no tasks in the list:
```
	Here are the tasks in your list:
```
- Otherwise:
```
	Here are the tasks in your list:
	1.[T][✗] borrow book
	2.[D][✓] return book (by: Sunday)
	3.[E][✗] project meeting (at: Mon 2-4pm)
```

## Add a To-Do

Syntax: `todo NAME`

Example: `todo borrow book`

Expected outcome:
```
	Got it. I've added this task:
	  [T][✗] borrow book
	Now you have 1 tasks in the list.
```

## Add a Deadline

Syntax: `deadline NAME /by TIME`

Example: `deadline return book /by Sunday`

Expected outcome:
```
	Got it. I've added this task:
	  [D][✗] return book (by: Sunday)
	Now you have 2 tasks in the list.
```

## Add an Event

Syntax: `event NAME /at TIME`

Example: `event project meeting /at Mon 2-4pm`

Expected outcome:
```
	Got it. I've added this task:
	  [E][✗] project meeting (at: Mon 2-4pm)
	Now you have 3 tasks in the list.
```

## Mark a task as done

Syntax: `done INDEX`

Tick an existing task.
Note: a task that is already done will be left unchanged.

Example: `done 2`

Expected outcome:
```
	Nice! I've marked this task as done:
	  [D][✓] return book (by: Sunday)
```

## Delete a task

Syntax: `delete INDEX`

Example: `delete 3`

Expected outcome:
```
	Noted. I've removed this task:
	  [E][✗] project meeting (at: Mon 2-4pm)
	Now you have 2 tasks in the list.
```

## Find tasks by name

Syntax: `find NAME`

Search for all tasks with names that contain the search keyword.
Does not search from any other parameters (e.g. `/by`, `/at`).

Example: `find book`

Expected outcome:

- If no result is found:
```
	Here are the matching tasks in your list:
```
- Otherwise:
```
	Here are the matching tasks in your list:
	1.[T][✗] borrow book
	2.[D][✓] return book (by: Sunday)
```

## Exit

Syntax: `bye`

Exit the app and save all tasks in `/data/duke.txt`

Expected outcome:
```
	Bye. Hope to see you again soon!
```

# Command Summary

- [`list`](#list-all-tasks)
- [`todo`](#add-a-to-do)
- [`deadline`](#add-a-deadline)
- [`event`](#add-an-event)
- [`done`](#mark-a-task-as-done)
- [`delete`](#delete-a-task)
- [`find`](#find-tasks-by-name)
