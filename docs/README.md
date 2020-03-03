# User Guide

## Features    

### Feature 1 - List

Allows the user to see all the current tasks in the list.

### Feature 2 - Add tasks

The user is able to add tasks of 3 different types, namely todo, events and deadlines.

### Feature 3 - Mark as done

The user is able to mark tasks as done once he is finished with them, allowing for easy tracking of their statuses.

### Feature 4 - Delete

The user can delete any task he wants, in order to avoid cluttering up the list.

### Feature 5 - Clear

The user can easily clear the list and start all over again.

### Feature 6 - Find

In case the user has a very large list, there is a find function to filter the list based on keywords specified by the user.


## Usage

### `help`- Display all available commands with their formats and usage.

Example of usage: **help**

Expected outcome:

**help
-Displays all commands and their input format**

**list
-Shows all tasks in the list currently**

**done (index)
-Marks task at specified index as done**

**delete (index)
-Deletes the task at the specified index**

**clear
-Clears all tasks in the list**

**bye
-Exits the application**

**todo (item)
-Adds a todo item to the list**

**event (item) /at (date or time)
-Adds an event to the list**

**deadline (item) /by (date or time)
-Adds a deadline to the list**

**find (string)
-Returns all tasks with the keyword (string) in their description**  


### `todo (event)`- Adds a todo item to the list.  

Example of usage: **todo finishIP**

Example outcome:

**Got it. I've added this task:**  

**[T][Not complete] finishIP** 

**Now you have 1 tasks in the list**

### `deadline (item) /by (date or time)`- Adds a deadline to the list.

Example of usage: **deadline launch /by March**

Example outcome:

**Got it. I've added this task:**  

**[D][Not complete] launch (by: March)** 

**Now you have 1 tasks in the list**

### `event (item) /at (date or time)`- Adds an event to the list.

Example of usage: **event avengersConference /at 1800h**

Example outcome:

**Got it. I've added this task:**  

**[E][Not complete] avengersConference (at: 1800h)** 

**Now you have 1 tasks in the list**

### `list`- Lists all the tasks.

Example of usage: **list**

Example outcome:

**Here are the tasks in your list:**

**1.[T][Not complete] writeReadme**

**2.[E][Not complete] sleep (at: 12 am)**  

### `done (index)`- Marks the task at the specified index as done.

Example of usage: **done 1**

Example outcome:

**Nice! I've marked this task as done:**  

**[T][Complete] writeReadme**

### `delete (index)`- Deletes the task at the specified index.

Example of usage: **delete 1**

Example outcome:

**Noted. I've removed this task:**  

**[T][Complete] writeReadme**  

**Now you have 1 tasks in the list.**

### `clear`- Clears all the tasks.

Example of usage: **clear**

Example outcome:

**All tasks removed successfully!**
 
### `find (keyword)`- Filters the list according to the keyword. Returns the full list if no keyword is specified.

Example of usage: **find sleep**

Example outcome:

**Here are the matching tasks in your list:**  

**1.[E][Not complete] sleep (at: 12 am)**

### `bye`- Closes the application

Example of usage: **bye**

Example outcome:

**Bye. Hope to see you again soon!**

