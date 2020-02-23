# Duke - User Guide

## Features 
1.Keep track of tasks

  Duke helps you to keep track of ToDos, Deadlines and Events and displays them.
  
2.Mark as done

  Duke helps you keep track of completed task through the `done` command.
  
3.Deleting tasks

  Duke helps you to delete tasks through the `delete` command.
  
4.Finding tasks

  Duke helps you to find for tasks with matching keywords using `find` command.
  
5.Auto-save and stores our tasks

  Duke helps you to auto-save and stores the task.
  
### Commands
+ [ToDo](#todo)
+ [Deadline](#deadline)
+ [Event](#event)
+ [List](#list) 
+ [Done](#done)
+ [Delete](#delete)
+ [Find](#find)
+ [Help](#help)
+ [Bye](#bye)

### Command Format
Words enclosed by `{` and `}` are parameters that are supplied by you.

## Setting Up
1. Extract the jar folder into an empty folder
2. Enter your preferred terminal and move to the location of the folder
3. Run the Command-Line interface(CLI) with `java -jar Duke.jar` in your terminal
![Setting up Duke](/images/startup.JPG)
## Usage

### <a id="todo"></a> `todo` - Adds a todo task to your list

Syntax: 

`todo {task description}`

Example of usage: 

`todo return book`

Expected outcome:

`adds a ToDo task to your list`

![Adding ToDo task](images/todo.JPG)

### <a id="deadline"></a> `deadline` - Adds a deadline task to your list

Syntax:

`deadline {task description} /by {deadline}`

Example of usage: 

`deadline submit assignment /by Sunday`

Expected outcome:

![Adding Deadline task](images/deadline.JPG)

### <a id="event"></a> `event` - Adds an Event task to your list

Syntax: 

`event {description} /at {location/time}`

Example of usage: 

`event project meeting /at NUS SOC,2pm`

Expected outcome:

`adds an Event task to your list`

![Adding Event task](images/event.JPG)

### <a id="list"></a> `list` - Displays all your tasks

Syntax: 

`list`

Expected outcome:

`Display all your tasks`

![Listing all tasks](images/list.JPG)

### <a id="done"></a> `done` - Mark task as completed

Syntax: 

`done {task index}`

Example of usage: 

`done 3`

Expected outcome:

`Marks task as completed based on specified index`

![Marking tasks as completed](images/done.JPG)

### <a id="delete"></a> `delete` - Deletes task from list

Syntax: 

`delete {task index}`

Example of usage: 

`delete 1`

Expected outcome:

`Deletes task based on specified index`

![Deleting tasks](images/delete.JPG)

### <a id="find"></a> `find` - Finds task from list

Syntax: 

`find {keyword}`

Example of usage: 

`find assignment`

Expected outcome:

`Finds task from list`

![Finding tasks](images/find.JPG)

### <a id="help"></a> `help` - Displays all the commands

Syntax: 

`help`


Expected outcome:

`Displays help command`

![help](images/help.JPG)

### <a id="bye"></a> `bye` - Terminate program

Syntax: 

`bye`

Expected outcome:

`Terminates program`

![bye](images/bye.JPG)
