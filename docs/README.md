# User Guide

## Features 

### `todo`
A task that needs to be done

### `event`
An event and where/when it is located at

### `deadline`
A deadline and when it needs to be done by

### `find`
Find tasks by keyword

### `done`
Mark tasks as done

### `list`
See current tasks in list and their status

### `delete`
Delete task from list

### `bye`
Exit program

### `saving to file`
Duke saves all tasks to a .txt file. When the users exits the program and starts it again later, 
it will read from the written text file to pick up where it left off.

## Usage

### `todo`

Example of usage:

*todo read book*

Expected outcome:

*Got it. I've added this task:*

*[T][✘]  read book*

*Now you have 1 tasks in the list.*

### `event`

Example of usage: 

*event concert /at March 23rd*

Expected outcome:

*Got it. I've added this task:*

*[E][✘]  concert  (at: March 23rd)*

*Now you have 2 tasks in the list.*

### `deadline`

Example of usage: 

*deadline return book /by Tuesday*

Expected outcome:

*Got it. I've added this task:*

*[D][✘]  return book  (by: Tuesday)*

*Now you have 3 tasks in the list.*

### `find`

Example of usage: 

*find book*

Expected outcome:

*Here are matching tasks in your list:*

*1. [T][✘]  read book*

*2. [D][✘]  return book  (by: Tuesday)*

### `done`

Example of usage: 

*done 1*

Expected outcome:

*Good work! I've marked this task as done!*

*1. [T][✓]  read book*

### `list`

Example of usage: 

*list*

Expected outcome:

*Here are the tasks in your list:*

*1. [T][✓]  read book*

*2. [E][✘]  concert  (at: March 23rd)*

*3. [D][✘]  return book  (by: Tuesday)*

### `delete`

Example of usage: 

*delete 3*

Expected outcome:

*Noted. I've removed this task:*

*3. [D][✘]  return book  (by: Tuesday)*

*Now you have 2 tasks in the list.*

### `bye`

Example of usage: 

*bye*

Expected outcome:

*Bye! See ya next time :)*
