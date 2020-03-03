# User Guide
## Features 
### Display help
Open the list of available commands that are supported by Duke

Keyword: `help`

### Create task
Creates a task with description only

Keyword: `todo`

### Create task with deadline
Creates a task with description and a time limit

Keyword: `deadline`

### Create event with timing
Creates an event with description, date and time

Keyword: `event`

### Mark task as done
Mark a task/deadline task/event as done

Keyword: `done`

### Display the list of tasks
Open the list of available tasks

Keyword: `list`

### Delete task
Delete a task/deadline task/event

Keyword: `delete`

### Terminate program
Terminate the program

Keyword: `bye`


## Usage
### Display help
Enter the keyword `help`
#### Example of usage:
`help`
#### Expected outcome:
Duke supports the following commands

Please enter the keyword followed by details required in the <>

todo &lt;task details&gt; --------------------------- Create a new task
  
deadline &lt;task details&gt; /by: &lt;timing details&gt; - Create a new task with a time element
  
event &lt;event details&gt; /at: &lt;timing details&gt; --- Create a new event with a time element
  
done &lt;task number&gt; ---------------------------- Mark task as done

delete &lt;task number&gt; -------------------------- Delete task

list ------------------------------------------ List all available tasks and events

bye ------------------------------------------- Shutdown Duke


### Create task
Enter keyword `todo` followed by the description of the task to create a new incomplete task with that description.
#### Example of usage: 
`todo` get eggs
#### Expected outcome:
The following task has been created:

1. [T][NotDone] get eggs 

Total number of incomplete tasks: 1

### Create task with deadline
Enter the keyword `deadline`, the task description, keyword `/by`, and lastly the intended deadline, all on the same line
#### Example of usage: 
`deadline` OCIP planning `\by` 20 Mar 2020 18:00
#### Expected outcome:
The following task has been created:

2. [D][NotDone] OCIP planning (by: 20 Mar 2020 18:00)

Total number of incomplete tasks: 2

### Create event with timing
Enter the keyword `event`, the event description, keyword `/at`, and lastly the intended date and time, all on the same line
#### Example of usage: 
`event` attend open house `/at` 8 Mar 2020 12:00
#### Expected outcome:
The following task has been created:

3. [E][NotDone] attend open house (at: 8 Mar 2020 12:00) 

Total number of incomplete tasks: 3

### Mark task as done
Enter the keyword `done` followed by the index number of the task (the index number could be checked using the list command)
#### Example of usage:
`done` 1
#### Expected outcome:
Nice! I've marked this task as done:

1.[Done] get eggs

Total number of incomplete tasks: 2

### Display the list of tasks
Enter the keyword `list`
#### Example of usage: 
`list`
#### Expected outcome:
Here is a list of all your tasks:

1. [T][Done] get eggs 

2. [D][NotDone] OCIP planning (by: 20 Mar 2020 18:00)

3. [E][NotDone] attend open house (at: 8 Mar 2020 12:00) 

Total number of incomplete tasks: 2

### Delete task
Enter the keyword `delete` followed by the index number of the task (the index number could be checked using the list command)
#### Example of usage: 
`delete` 1
#### Expected outcome:
get eggs has been deleted!

### Terminate program
Enter the keyword `bye`
#### Example of usage: 
`bye`
#### Expected outcome:
Bye. Hope to see you again soon!
