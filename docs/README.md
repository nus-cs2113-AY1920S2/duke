# User Guide

## Running the program
1. Make sure your computer has Java 11.

2. Copy the jar file into an empty folder.

3. Open a command window in that folder.

4. Run the command `java -jar Duke.jar` (i.e. run the command in the same folder as the jar file).

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

### 1. **help** - Displays a list of commands


### 2. **list** - Shows the current tasks in the list
Example outcome:

    Here are the tasks in your list:
        1.[T][0] read book
        2.[D][0] return book (by: 1 March)
        3.[E][0] meeting (at: 4 March)

    
### 3. **todo <description>** - Adds a todo to the list
Example usage: `todo read book`

Example outcome:

    Got it. I've added this task:
      [T][0] read book
    Now you have 1 tasks in the list.


### 4. **deadline <description> /by <deadline>** - Adds a deadline to the list
Example usage: `deadline return book /by 1 March`

Example outcome:

    Got it. I've added this task:
      [D][0] return book (by: 1 March)
    Now you have 2 tasks in the list.


### 5. **event <description> /at <timeslot>** - Adds an event to the list
Example usage: `event meeting /at 4 March`

Example outcome:

    Got it. I've added this task:
      [E][0] meeting (at: 4 March)
    Now you have 3 tasks in the list.


### 6. **done <task index>** - Mark a task as done
Example usage: `done 1`

Example outcome:

    Nice! I've marked this task as done:
      [T][1] read book
      

### 7. **delete <task index>** - Delete a task
Example usage: `delete 1`

Example outcome:

    Noted. I've removed this task:
      [T][1] read book
    Now you have 2 tasks in the list.


### 8. **find <keyword>** - Search for tasks using a keyword
Example usage: `find book`

Example outcome:

    Here are the matching tasks in your list:
    1.[D][0] return book (by: 1 March)
    3.[T][0] read textbook


### 9. **clear** - Delete all tasks, after confirmation by user


### 10. **thanks** - If you're feeling thankful


### 11. **bye** - Exit program