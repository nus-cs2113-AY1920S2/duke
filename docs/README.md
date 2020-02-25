# User Guide

## Table Of Contents
1. [Introduction](#intro)
2. [Quick Start](#quick-start)
3. [Features](#features)
  3.1. [Add new ToDo task](#add-todo)
  3.2. [Add new Deadline task](#add-deadline)
  3.3. [Add new Event task](#add-event)
  3.4. [Delete task](#delete)
  3.5. [Mark task as done](#mark)
  3.6. [List all tasks](#list)
  3.7. [Exit](#exit)
4. [FAQ](#faq)
5. [Command Summary](#command-summary)


<a name="intro"></a>

## 1. Introduction

 Duke is a command line based personal chatbot application used for managing tasks.
 The various types of tasks it can handle are:
 * **ToDo** Tasks --> Contains a simple description of the task and completion status.
 * **Deadline** Tasks --> Contains the description, completion status and deadline timing details.
 * **Event** Tasks --> Contains the description, completion status  and location details of the task.

 The application can add, delete tasks. It also provides methods to search for tasks 
 and has the ability of marking the tasks as done when they are completed. It also has the ability
 of storing data to a physical location on the computer, so that the tasks always remain in memory.
 

<a name="quick-start"></a>

## 2. Quick Start
 
 * Ensure you have Java 11 or above installed in your Computer.
 * Download the latest iP.jar from [here](https://github.com/GanapathySanathBalaji/duke/releases) and place it in an empty folder. 
 * Double-click on iP.jar to open it.
 * If it fails do the following:
    * Open Command Prompt.
    * Navigate to the folder with the jar.
    * Type the following command:  java -jar iP.jar to run it.
  * A text based UI should appear with a welcome message.
  * Some example commands you can try:
    * todo homework
    * list
    * search homework
  * Refer to Section 3, Features for details of each command.

<a name="features"></a>

## 3. Features


<a name="add-todo"></a>

### 1. Add new ToDo task
Adds a new ToDo task to the list of tasks.

### Usage

#### `todo description` - Adds a new ToDo task to the list of tasks

The command creates a new ToDo task with the description provided.
Upon success a successful addition message similar to the one in the example would appear.
If the wrong format is used an alert would be displayed.

Example of usage: 
`todo homework`

Expected outcome:
The ToDo task would be added to the list.
Upon success a response similar to the following one would appear:

    __________________________________________________________________________________________
     Got it. I've added this task:
       [T][ ] homework
     Now you have 1 task in the list.
    __________________________________________________________________________________________



<a name="add-deadline"></a>

### 2. Add new Deadline task
Adds a new Deadline task to the list of tasks.

### Usage

#### `deadline description /by yyyy-mm-dd hhmm` - Adds a new Deadline task to the list of tasks


The command creates a new Deadline task with the description and timing details provided.
Note: The date and time should be provided in the correct format
Upon success a successful addition message similar to the one in the example would appear.
If the wrong format is used an alert would be displayed.

Example of usage: 
`deadline assignment /by 2020-03-21 1700`

Expected outcome:
The Deadline task would be added to the list.
Upon success a response similar to the following one would appear:

    __________________________________________________________________________________________
     Got it. I've added this task:
        [D][ ] assignment (by: Mar 21 2020 1700 Hrs )
     Now you have 2 tasks in the list.
    __________________________________________________________________________________________



<a name="add-event"></a>

### 3. Add new Event task
Adds a new Event task to the list of tasks.

### Usage

#### `event description /at location` - Adds a new ToDo task to the list of tasks

The command creates a new Event task with the description and the location detail provided.
Upon success a successful addition message similar to the one in the example would appear.
If the wrong format is used an alert would be displayed.

Example of usage: 
`event meeting /at school`

Expected outcome:
The ToDo task would be added to the list.
Upon success a response similar to the following one would appear:

    __________________________________________________________________________________________
     Got it. I've added this task:
        [E][ ] meeting (at: school)
     Now you have 3 tasks in the list.
    __________________________________________________________________________________________


<a name="delete"></a>

### 4. Delete task
Deletes the task at the specified index.

### Usage

#### `delete index` - Deletes task at specified index

The command deletes the task at the specifed index, if the index provided is valid.
Upon success a successful deletion message similar to the one in the example would appear.
If the wrong format is used an alert would be displayed.

Example of usage: 
`delete 2`

Expected outcome:
The task would be deleted from the list.
Upon success a response similar to the following one would appear:

    __________________________________________________________________________________________
     Noted. I've removed this task:
       [D][ ] assignment (by: Mar 21 2020 1700 Hrs )
     Now you have 2 tasks in the list.
    __________________________________________________________________________________________


<a name="mark"></a>

### 5. Mark task as done
Marks the task at the specified index as done.

### Usage

#### `done index` - Marks the task at specified index as done

The command marks the task at the specifed index as done, if the index provided is valid.
Upon success a successful message similar to the one in the example would appear.
If the wrong format is used an alert would be displayed.

Example of usage: 
`done 1`

Expected outcome:
The task would be marked as done.
Upon success a response similar to the following one would appear:

    __________________________________________________________________________________________
     Nice! I've marked this task as done:
       [T][/] homework
    __________________________________________________________________________________________
 

<a name="add-todo"></a>
   
### 6. List all tasks
List all current tasks stored.

### Usage

#### `list` - Lists all the tasks in the list

The command displays all the current tasks in a numbered list.
Upon success a successful message similar to the one in the example would appear.
If the wrong format is used an alert would be displayed.

Example of usage: 
`list`

Expected outcome:
All tasks in the list would be displayed in a numbered list.
A response similar to the following one would appear:

    __________________________________________________________________________________________
     Here are the tasks in your list:
     1. [T][/] homework
     2. [E][ ] meeting (at: school)
    __________________________________________________________________________________________
    

<a name="list"></a>

### 6. Search tasks
Performs a linear search of all tasks stored in the list at the point of execution and list
all the tasks containing the keyword in their description in a numbered list.

### Usage

#### `find keyword` - Lists all matching tasks in the list containing the keyword 

The command displays all the tasks with description containg the keyword searched for in a numbered list.
Upon success a successful message similar to the one in the example would appear.
If the wrong format is used an alert would be displayed.

Example of usage: 
`find meeting`

Expected outcome:
All tasks in the list would be displayed in a numbered list.
A response similar to the following one would appear:

    __________________________________________________________________________________________
     Here are the matching tasks in your list:
     1. [E][ ] meeting (at: school)
    __________________________________________________________________________________________   


<a name="exit"></a>
    
### 7. Exit 
The command is used to exit the application. But before the actual application is exited the 
tasks currently in the list are stored in a file.

### Usage

#### `bye` - Exits the application 

The command when issued performs an exit.
Upon success a successful message similar to the one in the example would appear and the application would be exited.
If the wrong format is used an alert would be displayed.

Example of usage: 
`bye`

Expected outcome:
The application would be exited successfully.
A response similar to the following one would appear:

    __________________________________________________________________________________________
     Bye. Hope to see you again soon!
    __________________________________________________________________________________________

<a name="faq"></a>

## 4. FAQ
    Q: How to transfer the task list data from one computer to an other computer?
    A:      Just copy the "TaskList.txt" file which could be found at the same directory
        as the jar file and paste it to the location containing the jar file in the other
        computer. This should transfer the required data

<a name="command-summary"></a>

## 5. Command Summary
1. `todo description` - Adds a new ToDo task to the list of tasks
2. `deadline description /by yyyy-mm-dd hhmm` - Adds a new Deadline task to the list of tasks
3. `event description /at location` - Adds a new ToDo task to the list of tasks
4. `delete index` - Deletes task at specified index
5. `done index` - Marks the task at specified index as done
6. `find keyword` - Lists all matching tasks in the list containing the keyword
7. `bye` - Exits the application