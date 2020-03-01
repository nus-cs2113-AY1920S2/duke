# [Project Hiroshi](https://github.com/alaukiknpant/duke) - User Guide

By: `Alaukik Nath Pant`      Since: `Feb 2020`      Licence: `MIT`      Course: `CS2113`

## Introduction

Hiroshi Nagai Task Tracker(HNTT) is for those who *prefer to use a desktop app for managing their tasks*.
It is worth noting that HNTT is *optimized for those who prefer to work with a Command Line Interface* (CLI).
It helps you save todos, events and deadlines.

## Quick Start

*  Ensure you have Java `11` or above installed in your Computer.
*  Download the latest `hiroshi.jar` [latest reslease here!](https://github.com/alaukiknpant/duke/releases/tag/v0.2).
*  Copy the file to the folder you want to use as the home folder for your Task tracker.
*  Go to the directory where the Jar file is stored and type: `java -jar Hiroshi.jar`
* When the application executes, all possible commands that can help you manage your tasks are shown.
*Some example commands you can try:
    * **`list`** : lists all tasks that have been added and saved. until now
    * **`todo`**`finish all your Computer Science homework` : adds a todo task with the corresponding description to the Task List.
    * **`delete`**`1` : deletes the 1st item in your current task list
    * **`bye`** : exits the app


## Features


### Command Format

* The words in `UPPER_CASE` are to be supplied by the user e.g. in `todo TASK`, `TASK` is a parameter which can be used to add a task `todo Go Running`.
* The date for tasks of type `deadline` and `event` are labelled as `DATE`, which is of the format `YYYY-MM-DD`: `deadline TASK \by DATE`, `event TASK \at DATE`.
* The `TASK_NUMBER` is the interger index of a task in the tasklist and is of the format `delete TASKNUMBER`, `done TASK_NUMBER`.
* The `KEY_WORD` is the string that you are searching for in the task list.`find food`, `find homework`.
* The `TASK_NUMBER` refers to the index number shown in the displayed task list. Remember that the index *must be a positive integer* 1, 2, 3, ...

#### Adding a todo task : `todo`

* Adds a todo task to the tasklist 
* Format: `todo TASK`

[TIP]
A todo task just needs to be a string but cannot be empty.

Examples:

    todo eat


Expected Outcome:

    Got it. I have added this task: 
    
    [T] [✘] eat
    
    Now you have 2 item/s in the list`

****

#### Adding a deadline task: `deadline `

* Adds a dedline to the tasklist 
* Format: `deadline TASK /by DATE`

[TIP]
A deadline task must necessarily have a date mentioned.

Examples:

    deadline eat /by 2019-01-02

Expected Outcome

    Got it. I have added this task: 
    
    [D] [✘] eat (by: Jan 2 2019)
    
    Now you have 3 item/s in the list

****

#### Adding an `event` task: `event `

* Adds a event to the tasklist
* Format: `event TASK /at DATE`

[TIP]
A event task must necessarily have a date mentioned.

Examples:

    event eat /at 2019-01-05

Expected Outcome:

    Got it. I have added this task: 
    
    [E] [✘] eat (at: Jan 5 2019)
    
    Now you have 3 item/s in the list 

****

#### Deleting a task : `delete`

* Deletes the task at the specified `TASK_NUMBER`. 
* When a task is deleted, all subsequest tasks in the task list will have their index reduced by 1.
* Format: `delete TASK_NUMBER`

Examples:

     delete 2

Expected Outcome
    
    Cool, we will remove the following task:
    [D] [✘] eat (by: Jan 2 2019)
    Now you have 2 items in your list
****

#### Listing all tasks in the tasklist : `list`

* Shows a list of all tasks in tasklist.
* Format: `list`

Examples:

    list

Expected outcome:

    1. [T] [✘] eat
    2. [E] [✘] eat (at: Jan 5 2019)


****

#### Marking a task as done : `done`

* Marks the task at the specified `TASK_NUMBER` as done. The task number refers to the index number shown in the displayed task list. Remember that the index *must be a positive integer* 1, 2, 3, ...
* If a task is already marked as done, the app remarks it as done again.
* Format: `done TASK_NUMBER`


Examples:

    done 2

Expected Outcome:

    2. [E][✓] eat (at: Jan 5 2019)
    Done! We have checked 2!

****

#### Locating a task by key word: `find`

* Finds tasks whose description contains a specified keyword. The search is case sensitive. e.g `run` will not match `Run`
* Format: `find KEYWORD`

Examples:

    find eat 

Expected Outcome:
    
    Here are the matching tasks in your list:
    1. [T] [✘] eat
    2. [E] [✓] eat (at: Jan 5 2019)
    
    There were 2 tasks with the "eat" keyword
****

#### Clearing the task list : `clear`

* Clears all the tasks in task list. 
* Format: `clear`

Examples:

    clear

Expected Ourcome:

    TaskList is now Empty

****

#### Exiting the program : `bye`

* Exits the program. 
* Format: `bye`

Examples:

* `bye` 

Expected Ourcome:

    Bye. Hope to see you again soon!
    
    ****--****--****--****--****--****--****--****--****--****--****--****--****--***

****

## FAQ

*Q*: How do I transfer my data to another Computer? +
*A*: Copy the text file called "files" that has saved your task list into the directory from which you will run the application in your new computer. You are then set to go!

## Command Summary
* *Todo*  : `todo TASK`; 
e.g. `todo Run beside Clementi Park.`
* *Deadline* : `deadline TASK /by DATE`;
eg. `deadline Problem Set 2 \by 2020-01-02`
* *Event* : `event TASK /at DATE`;
eg. `event Marathon \at 2020-01-01`
* *List* : `list`
* *Done* : `done TASK_NUMBER`;
eg. `done 2`
* *Delete* : `delete TASK_NUMBER`;
e.g. `delete 3`
* *Find* : `find KEYWORD`;
e.g. `find eat`
* *Clear* : `clear`
* *Exit* : `bye`
