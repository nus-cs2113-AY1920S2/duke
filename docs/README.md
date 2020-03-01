# Duke User Guide


1. Introduction <br/>
2. Quick Start <br/>
3. Features <br/>
&nbsp; &nbsp; &nbsp; 3.1 [Add todo: <code>todo</code>](#31-add-todo-todo)<br/>
&nbsp; &nbsp; &nbsp; 3.2 [Add deadline: <code>deadline</code>](#32-add-deadline-deadline) <br/>
&nbsp; &nbsp; &nbsp; 3.3 [Add event: <code>event</code>](#33-add-event-event) <br/>
&nbsp; &nbsp; &nbsp; 3.4 [Mark a task as done: <code>done</code>](#34-mark-a-task-as-done-done) <br/>
&nbsp; &nbsp; &nbsp; 3.5 [Delete a task: <code>delete</code>](#35-delete-a-task-delete) <br/>
&nbsp; &nbsp; &nbsp; 3.6 [Find tasks: <code>find</code>](#36-find-tasks-find) <br/>
&nbsp; &nbsp; &nbsp; 3.7 [List all tasks: <code>list</code>](#37-list-all-tasks-list) <br/>
&nbsp; &nbsp; &nbsp; 3.8 [Clear window: <code>clc</code>](#38-clear-window-clc) <br/>
&nbsp; &nbsp; &nbsp; 3.9 [Exit the program: <code>bye</code>](#39-exit-the-program-bye) <br/>
4. FAQ <br/>
5. Command Summary 


## 1. Introduction
**Zapato** is a task manager to keep all your todos, deadlines,and events in one place.
It is a great CLI application to organize all yours tasks. If you are interested in using 
this application, go to the [quick start](#2-quick-start) to get started.

## 2. Quick Start
1. Ensure that you have Java 11 installed in your machine. 
2. Download the latest version of **Zapato** [here](https://github.com/rsanchez-macias/duke/releases).
3. Choose a home folder to keep your application.
4. From the command prompt or terminal window, execute the following:
`java -jar duke iP.jar`
5. Type help to find out about the supported commands.
6. Head to section 3 to learn about all the [features](#3-features).

## 3. Features 

Command format:
* Words in "[ ]" are parameters given by the user


#### 3.1. Add todo: `todo`
Adds a todo task to the task list <br/> 
Format: `todo [description]` <br/>
Example: `todo return book`

#### 3.2. Add deadline: `deadline`
Adds a deadline task to the task list. It can be given either a time (24-cycle), date, or both. <br/> 
Format: `deadline [description] /by [dd-mm-yyyy] [hh:mm]` <br/>
Example: `deadline submit tax form /by 15-04-2020 23:59`

#### 3.3. Add event: `event`
Adds an event task to the task list <br/> 
Format: `event [description /at [place/time]` <br/>
Example: `event project meeting /at COM1`

#### 3.4. Mark a task as done: `done`
Marks a task as done <br/> 
Format: `done [task #]` <br/>
Example: 
* `done 3` <br />
Marks the third task as done

#### 3.5. Delete a task: `delete`
Removes a task from the task list <br/> 
Format: `delete [task #]` <br/>
Example: 
* `delete 1` <br/>
deletes the first person from the list

#### 3.6. Find tasks: `find`
Finds the tasks with the given pattern found in their description <br/> 
Format: `find [pattern]` <br/>
Example: 
* `find homework` <br/>
Lists all the tasks that contain the keyword "homework" in their description

#### 3.7. List all tasks: `list`
Lists all the tasks in the task list <br/> 
Format: `list` <br/>

#### 3.8. Clear window: `clc`
Clears the terminal or command prompt window <br/>
Format: `clc` 

#### 3.9. Exit the program: `bye`
Exits the program at any point <br/>
Format: `bye`

## 4. FAQ
**Q**: How do I save my tasks after I exit my application? <br/>
**A**: No need to worry, tasks are saved automatically when any changes are made to the list.

## 5. Command Summary
* **Todo** `todo [description]` <br/> eg. `todo go to the store`

* **Deadline** `deadline [description] /by [dd-mm-yyyy] [hh:mm]` <br/> eg. `deadline finish book /by 07-03-2020 15:30`

* **Event** `event [description /at [place/time]` <br/> eg. `event dinner /at Chick-fil-A`

* **Done** `done [task #]` <br/> eg. `done 1`

* **Delete** `delete [task #]` <br/> eg. `delete 4`

* **Find** `find [pattern]` <br/> eg. `find appointment`

* **List** `list`

* **Clear** `clc`

* **Exit** `bye`