# User Guide

## Features 

### 1. List
Show the current list of tasks.

### 2. Add task
Three types of tasks can be added: a todo, a deadline, or an event.

### 3. Mark task as done
Tasks can be marked as done.

### 4. Delete task
Any task in the list can be deleted.

### 5. Clear list
The entire list can be cleared to allow the user to start over with a clean slate.

### 6. Find task
Find tasks using a keyword. 

## Commands

- ### `help` - Displays a list of commands

- ### `list` - Shows the current tasks in the list

Example of outcome:

    Here are the tasks in your list:
        1.[T][0] read book
        2.[D][0] return book (by: 1 March)
        3.[E][0] meeting (at: 4 March)
    
- ### `todo <description>` - Adds a todo to the list

Example of usage: `todo read book`

Example of outcome:

    Got it. I've added this task:
      [T][0] read book
    Now you have 1 tasks in the list.

- ### `deadline <description> /by <deadline>` - Adds a deadline to the list

Example of usage: `deadline return book /by 1 March`

Example of outcome:

    Got it. I've added this task:
      [D][0] return book (by: 1 March)
    Now you have 2 tasks in the list.

- ### `event <description> /at <timeslot>` - Adds an event to the list

Example of usage: `event meeting /at 4 March`

Example of outcome:

    Got it. I've added this task:
      [E][0] meeting (at: 4 March)
    Now you have 3 tasks in the list.

- ### `done <task index>` - Mark a task as done

Example of usage: `done 1`

Example of outcome:

    Nice! I've marked this task as done:
      [T][1] read book
      
- ### `delete <task index>` - Delete a task

Example of usage: `delete 1`

Example of outcome:

    Noted. I've removed this task:
      [T][1] read book
    Now you have 2 tasks in the list.

- ### `find <keyword>` - Search for tasks using a keyword

Example of usage: `find book`

Example of outcome:

    Here are the matching tasks in your list:
    1.[D][0] return book (by: 1 March)
    3.[T][0] read textbook

- ### `clear` - Delete all tasks

- ### `thanks` - If you're feeling thankful

- ### `bye` - Exit program