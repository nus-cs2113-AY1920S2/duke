# User Guide

## About the program
This is a lightweight command-line interface (CLI) program for managing your tasks. 

## Running the program
1. Make sure your computer has Java 11.

2. Copy the jar file into an empty folder.

3. Open a command window in that folder.

4. Run the command `java -jar Duke.jar` (i.e. run the command in the same folder as the jar file).

## Features 

### 1. Task management
Tasks can be added, removed and marked as done.

### 2. Search 
Find tasks by searching for a keyword in the task description.

### 3. Auto-load
Tasks are loaded from a file called `duke.txt` in a directory called `data` upon start-up.
If no such file or directory exists, they are created.

### 4. Auto-save
Any change to the tasks in memory results in a save to the `duke.txt` file.

## Commands

### 1. help  
#### Displays a list of commands  
&nbsp;

### 2. list 
#### Shows the current tasks in the list
Example outcome:

    Here are the tasks in your list:
        1.[T][0] read book
        2.[D][0] return book (by: 1 March)
        3.[E][0] meeting (at: 4 March)
&nbsp;

### 3. todo \<description> 
#### Adds a todo to the list
Example usage: `todo read book`

Example outcome:

    Got it. I've added this task:
      [T][0] read book
    Now you have 1 tasks in the list.
&nbsp;

### 4. deadline \<description> /by \<deadline> 
#### Adds a deadline to the list
Example usage: `deadline return book /by 1 March`

Example outcome:

    Got it. I've added this task:
      [D][0] return book (by: 1 March)
    Now you have 2 tasks in the list.
&nbsp;

### 5. event \<description> /at \<timeslot> 
#### Adds an event to the list
Example usage: `event meeting /at 4 March`

Example outcome:

    Got it. I've added this task:
      [E][0] meeting (at: 4 March)
    Now you have 3 tasks in the list.
&nbsp;

### 6. done \<task index> 
#### Mark a task as done
Example usage: `done 1`

Example outcome:

    Nice! I've marked this task as done:
      [T][1] read book
&nbsp;

### 7. delete \<task index> 
#### Delete a task
Example usage: `delete 1`

Example outcome:

    Noted. I've removed this task:
      [T][1] read book
    Now you have 2 tasks in the list.
&nbsp;

### 8. find \<keyword> 
#### Search for tasks using a keyword
Example usage: `find book`

Example outcome:

    Here are the matching tasks in your list:
    1.[D][0] return book (by: 1 March)
    3.[T][0] read textbook
&nbsp;

### 9. clear 
#### Delete all tasks, after confirmation by user
&nbsp;

### 10. thanks
#### If you're feeling thankful
&nbsp;

### 11. bye 
#### Exit program