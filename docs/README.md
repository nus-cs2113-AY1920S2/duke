# Duke User Guide

---
Duke is a command line interface program capable of handling tasks such as Todo, Events and Deadlines.
This User Guide will demonstrate the capabilities of Duke. 

---
## Running duke Duke 
1. Download the latest version of Java 11
2. Navigate to path of `Duke.jar` file
3. Run `java -jar Duke.jar` 
4. Duke will automatically save any tasks added into an `output.txt` file
5. On start up, Duke will also load existing tasks from `output.txt` file
6. To exit Duke, input `bye` command

---
## Duke Task Types
* Todo 
* Event 
* Deadline

---
## Main Features
1. Add different types of tasks
2. List all tasks 
3. Mark task as done
4. Find tasks from list 
5. Delete a task from list

---
## Feature List

### 1.1. Add Todo task 
Users are able to add todo tasks into Duke.

#### Usage
`todo <description of todo task>`
* Adds a single todo task into task list

#### Example of usage: 
`todo buy bread`

#### Expected outcome:
\_________________________________________________

Got it! You've added a todo task: 

\[T][&#10008;] buy bread

You now have 1 task in the list

\_________________________________________________

---
### 1.2. Add Event task
Users are able to add events into Duke. 

### Usage
`event <description of event> /at <date and time of event>`
* Adds an event into task list

#### Example of usage:
`event project meeting /at Monday 2pm`

#### Expected outcome:
\_________________________________________________

Got it! You've added an event task: 

\[E][&#10008;] project meeting (at: Monday 2pm)

You now have 2 tasks in the list

\_________________________________________________


---
### 1.3. Add Deadline task
Users are able to add deadline into Duke

### Usage
`deadline <description of deadline> /by <date and time of deadline>`
* Adds a deadline into task list

#### Example of usage:
`deadline CS2113T assignment /by Monday 12pm`

#### Expected outcome:
\_________________________________________________

Got it! You've added a deadline task: 

\[D][&#10008;] CS2113T assignment (by: Monday 12pm)
 
 You now have 3 tasks in the list
 
\_________________________________________________

---
### 2. List all tasks
Users are able to view all tasks in their list

### Usage
`list`
* Lists all tasks in task list

#### Example of usage:
`list`

#### Expected outcome:
\_________________________________________________

Here are the tasks in your list: 
1. \[T][&#10008;] buy bread
2. \[E][&#10008;] project meeting (at: Monday 2pm)
3. \[D][&#10008;] CS2113T assignment (by: Monday 12pm)

\_________________________________________________

---
### 3. Mark task as done
Users are able to mark a task in the list as done 

### Usage
`done <index of task on list>`
* Marks a single task as done

#### Example of usage:
`done 1`

#### Expected outcome:
\________________________________________________

Awesome! I've marked the following task as done:

\[T][&#10003;] buy bread

\_________________________________________________

---
### 4. Find tasks 
Users are able to find tasks in task list based on keywords.

### Usage
`find <keywords to search for>`
* Finds all tasks that contains keywords

#### Example of usage: 
`find bread`

#### Expected outcome:
\_________________________________________________

I've found the following tasks matching your search: 
1. \[T][&#10003;] buy bread

\_________________________________________________

---
### 5. Delete task
Users are able to delete tasks in task list

### Usage
`delete <index of task to be deleted>`
* Deletes a single task from the task list

#### Example of Usage:
`delete 1`

#### Expected outcome:
\_________________________________________________

Got it! I've removed this task:

\[T][&#10003;] buy bread

You now have 2 tasks in the list

\_________________________________________________

