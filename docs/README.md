# User Guide
## Introduction
Duke is a personalized bot that assists you in keeping track of things that need to be completed. Duke is optimized to be used with a command line interface.
## Features 
**Command Format**
* All commands must be specified in lower case
* Parameters must follow the specified order 
* Only one command can be entered per line

### Feature 1 - Adding todo
This adds a todo task to the task list <br/>
Format: todo TASK <br/>
Example: todo sleep

### Feature 2 - Adding deadline
This adds a deadline task to the task list <br/>
Format: deadline TASK /by DATE <br/>
Example: deadline return book /by Friday

### Feature 3 - Adding event
This adds an event task to the task list <br/>
Format: event TASK /at TIME <br/>
Example: event project meeting /at Mon 3-5pm

### Feature 4 - List tasks
This lists all tasks that are in the list <br/>
Format: list

### Feature 5 - Mark task as done
This marks the tasks at the specified index as done <br/>
Note: Index starts at 1 <br/>
Format: done INDEX <br/>
Example: done 1 

### Feature 6 - Delete Task
This deletes the task at the specified index <br/>
Note: Index starts at 1 <br/>
Format: delete INDEX <br/>
Example: delete 2

### Feature 7 - Save tasks
The tasks are automatically saved to 'duke.txt' when the program is exitted normally

### Feature 8 - Load tasks
Tasks are automatically loaded from 'duke.txt' when the program starts

### Feature 9 - Find tasks
This searches for the tasks where the description contains the keywords <br/>
Format: find KEYWORD <br/>
Example: find sleep

### Feature 10 - Exit the program
This exits the program <br/>
Format: bye


