
# Duke - User Guide
By: `JosephLimWeiJie` Since: `Feb 2019`


* [1. Introduction](#introduction)
* [2. Quick Start](#quick-start)
* [3. Features](#features)
    + [3.1. Adding a task:](#addtask) `todo, deadline, event`
    + [3.2. Listing all tasks:](#list) `list`
    + [3.3. Finding a task:](#find) `find`
    + [3.4. Deleting a task:](#delete) `delete`
    + [3.5. Filter tasks by date:](#filter) `filter`
    + [3.6. Complete a task:](#complete) `done`
    + [3.7. Exiting the program:](#exit) `bye`
* [4. FAQ](#faq)
* [5. Command Summary](#command-summary)


<a name="introduction"></a>


##  1. Introduction
<p align="center">
  <img src="https://user-images.githubusercontent.com/59989652/75109414-a9446300-565d-11ea-938e-8bcc5e3c0bc7.PNG">
</p>

Duke is for those who prefer to use a desktop app to keep track of their to-do lists. More importantly, Duke is **optimized for those who prefer to work with a Command Line Interface (CLI)**. 
Jump to [_Section 2, "Quick Start"_](#quick-start) to get started.


<a name="quick-start"></a>

## 2. Quick Start

*   1. Ensure that `Java 11` or above is installed in your Computer.
*   2. Download the latest `duke.jar` [_here_](https://github.com/JosephLimWeiJie/duke/releases/download/v0.2.0/duke.jar).
*   3. Copy the file to the folder you want to use as the home folder for your Duke.
*   4. Double-click on `duke.jar` to start the app. It should appear in a few seconds.
*   5. Type in a command and press `ENTER` to execute it. For e.g. typing `list` and                         pressing enter will list all your current tasks.
*   6. Some other commands you can try:
    *  `todo return book`: adds a todo task to return a book
    *   `deadline Finish a movie /by 2019-12-01T10:00`: adds a deadline task to finish a movie by 1 Dec 2019 at 1000 hrs.
    *   `event Midnight Party /on 2019-12-01T10:00 to 2019-12-01T23:59`: adds an event task to attend a midnight party at Marina Bay Sands
    *   `done 1`: Mark the first task as done shown in the current list.
    *   `bye`: Exits the app
*   7. Refer to [_Section 3, "Features"_](#features) for more details of each command.

<a name="features"></a>

## 3. Features

**Command Format**

``` javascript
* Words in `UPPER_CASE` are the parameters to be supplied by the user. 
    * e.g. in `todo TASK_DESCRIPTION`, TASK_DESCRIPTION is a parameter
    to specify a task's description.    

* For a general todo task, a task description MUST be added
    * e.g. in `todo TASK_DESCRIPTION`.

* For a deadline task, a date MUST be added right after the TASK_DESCRIPTION by using /by. 
    * e.g. `deadline complete homework /by YYYY-MM-DDTHH:mm` such as 2020-01-12T23:59. 
    Note that you have to add 'T' between the date and the time.  
  
* For an event task, an event start and end date and time MUST be added 
  right after the TASK_DESCRIPTION by using /on.
    * e.g. in `event TASK_DESCRIPTION /on YYYY-MM-DDTHH:mm to YYYY-MM-DDTHH:mm` 
         such as 2020-01-12T10:00 to 2020-01-12T11:00`.
    Note that you have to add 'T' between the date and the time.     
```

<a name="addtask"></a>

### 3.1 Adding a task: `todo, deadline, event`

Adds a todo, deadline or an event task into Duke.


* **Format**: 
    * `todo TASK_DESCRIPTION`
    * `deadline TASK_DESCRIPTION /by DATE_TIME`
    * `event TASK_DESCRIPTION /on START_DATE_TIME to END_DATE_TIME`

* **Examples**:
    * `todo return book`
    * `deadline Thesis submission /by 2020-01-12T23:59`
    * `event Wedding Ceremony /on 2020-01-12T11:00 to 2020-01-12T12:00`

<a name="list"></a>

### 3.2. Listing all tasks: `list`

Shows a list of all the tasks in Duke.

**Format**: `list`


<a name="find"></a>

### 3.3 Finding a task: `find`

Finds tasks that contain a given keyword.

**Format** : `find KEYWORD`

**Example**: `find book`

<a name="delete"></a>

### 3.4 Deleting a task: `delete`
Deletes a specified task from Duke.

**Format**: `delete INDEX`

```javascript
    * Deletes the task at the specified INDEX.
    * The index refers to the index number shown on the displayed task list.
    * The index must be a positive number 1,2,3,...
```

<a name="filter"></a>

### 3.5 Filter tasks by date: `filter`
Filters tasks based on a given DATE.

**Format**: `filter DATE`

**Example**: `filter 2019-01-01`

```javascript
* Note that the month and date MUST be in 2 digits. 
  If a date occurs on 1 Jan 2019, you have to add a ZERO in front. 
```

<a name="complete"></a>

### 3.6 Complete a task: `done`

Marks a task as completed at the specified INDEX.

**Format**: `done INDEX`

```javascript
* The index refers to the index number shown on the displayed task list.
* The index must be a positive number 1,2,3,..
```

<a name="exit"></a>

### 3.7 Exiting the program: `bye`

Exits the program.

**Format**: `bye`


<a name="faq"></a>

## 4. FAQ

*Q:* How do I transfer my data onto another computer?


*A:* Download or copy the previous `Duke.jar` onto the other computer and copy the `storage.txt` from the previous Duke folder onto your current new folder containing the `Duke.jar`.


<a name="command-summary"></a>

## 5. Command Summary
* Add 
    * Format: 
        * `todo TASK_DESCRIPTION`
        * `deadline TASK_DESCRIPTION /by DATE_TIME`
        * `event TASK_DESCRIPTION /on START_DATE_TIME to END_DATE_TIME`
    * Examples:
        * `todo return book`
        * `deadline Thesis submission /by 2020-01-12T23:59`
        * `event Wedding Ceremony /on 2020-01-12T11:00 to 2020-01-12T12:00`
* List: `list`
* Find: `find KEYWORD`
    * e.g. `find book`
* Filter: `filter DATE`
    * e.g. `filter 2019-01-01`
* Delete: `delete INDEX`
    * e.g. `delete 2`
* Complete: `done INDEX`
    * e.g. `done 1`
   
* Exit: `bye`

