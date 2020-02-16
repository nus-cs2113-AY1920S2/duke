# Duke - User Guide
By: `JosephLimWeiJie` Since: `Feb 2019`


- [1.Introduction](##1.Introduction)
- [2.Quick Start](#2.Quick Start)
- [3.Features](#3.Features)
    * [3.1. Adding a task:](##3.1. Adding a task:) `add`
    * [3.2. Listing all tasks:](#3.2. Listing all tasks:) `list`
    * [3.3. Finding a task:](#3.3. Finding a task:) `find`
    * [3.4. Deleting a task:](#3.4. Deleting a task:) `delete`
    * [3.5. Filter tasks by date:](#3.5. Filter tasks by date:) `filter`
    * [3.6. Complete a task:](#3.6 Complete a task:) `done`
    * [3.7. Exiting the program:](#3.7. Exiting the program) `exit`
- [4.FAQ](#4.FAQ)
- [5. Command Summary](#5. Command Summary)

## 1.Introduction

Duke is for those who prefer to use a desktop app to keep track of their to-do lists. More importantly, Duke is optimized for those who prefer to work with a Command Line Interface (CLI). 
Jump to Section 2, "Quick Start" to get started.

##2. Quick Start

*   1. Ensure that Java 11 or above is installed in your Computer.
*   2. Download the latest duke.jar [here](https://github.com/JosephLimWeiJie/duke/releases/download/v0.1.0/duke.jar).
*   3. Copy the file to the foldere you want to use as the home folder for your Duke.
*   4. Double-click on duke.jar to start the app. It should appear in a few seconds.
*   5. Type in a command and press ENTER to execute it. For e.g. typing list and                         pressing enter will list all your current tasks.
*   6. Some other commands you can try:
    *  todo return book: adds a todo task to return a book
    *   deadline Finish a movie /by 2019-12-01T10:00: adds a deadline task to finish a movie by 1 Dec 2019 at 1000 hrs.
    *   event Midnight Party /at Marina Bay Sands: adds an event task to attend a midnight party at Marina Bay Sands
    *   done 1: Mark the first task as done shown in the current list.
    *   exit: exits the app

##3. Features

Command Format

``` javascript
* Words in UPPER_CASE are the parameters to be supplied by the user e.g. in todo TASK_DESCRIPTION, TASK_DESCRIPTION is a parameter to specify a task's description
* Tasks with a venue can be added right after the TASK_DESCRIPTION by using /at e.g. in event TASK_DESCRIPTION /at VENUE
* Tasks with a deadline can be added right after the TASK_DESCRIPTION by using /by e.g. deadline complete homework /by YYYY-MM-DDTHH:mm. Note that you have to add 'T' between the date and the time.
```

###3.1. Adding a task: add

Adds a task into Duke.

Format: 
* todo TASK_DESCRIPTION
* deadline TASK_DESCRIPTION /by DATE T TIME
* event TASK_DESCRIPTION /at VENUE

Examples:
* todo return book
* deadline Thesis submission /by 2020-01-12T23:59
* event Wedding Ceremony /at ABC hotel

###3.2. Listing all tasks: list

Shows a list of all the tasks in Duke.

Format: list

###3.3. Finding a task: find

Finds tasks that contains a given keyword.

Format:
* find KEYWORD

Example:
* find book

###3.4. Deleting a task: delete
Deletes a specified task from Duke.

Format: delete INDEX
* Deletes the task at the specified INDEX.
* The index refers to the index number shown on the displayed task list.
* The index must be a positive number 1,2,3,...

###3.5. Filter tasks by date: filter
Filters tasks based on a given DATE

Format: filter DATE

Example:
filter 2019-01-01
* Note that the month and date must be in 2 digits. If a date occurs on 1 Jan 2019,
you have to add a zero in front. 

###3.6. Complete a task: complete

Marks a task as completed at the specified INDEX.

Format: done INDEX
* The index refers to the index number shown on the displayed task list.
* The index must be a positive number 1,2,3,..

###3.7. Exiting the program: exit

Exits the program.
Format: exit

##4.FAQ


*Q:* How do I transfer my data onto another Computer?


*A:* Install the app in the other computer and copy the 'storage.txt' from the previous Duke folder onto your current new folder containing the duke.jar.


##5. Command Summary
* Add 
    * Format: 
        * todo TASK_DESCRIPTION
        * deadline TASK_DESCRIPTION /by DATE T TIME
        * event TASK_DESCRIPTION /at VENUE
    * Examples:
        * todo return book
        * deadline Thesis submission /by 2020-01-12T23:59
        * event Wedding Ceremony /at ABC hotel
* List: list 
* Find: find KEYWORD
    * e.g. find book
* Filter: filter DATE
    * e.g. filter 2019-01-01
* Delete: delete INDEX
    * delete 2
* Done: done INDEX
    * e.g. done 1
   
* Exit: exit


[Arbitrary case-insensitive reference text]
