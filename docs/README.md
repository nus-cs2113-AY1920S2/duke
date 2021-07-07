# User Guide for Duke
Author: **Wu Sibing**<br/>
Since: **Feb 2020**

1. [Introduction](#1-introduction)<br/>
2. [Quick Start](#2-quick-start)<br/>
3. [Features](#3-features)<br/>
    3.1. [Viewing help: `help`](#31-viewing-help-help)<br/>
    3.2. [Adding a "Todo" task: `todo`](#32-adding-a-todo-task-todo)<br/>
    3.3. [Adding a "Deadline" task: `deadline`](#33-adding-a-deadline-task-deadline)<br/>
    3.4. [Adding a "Event" task: `event`](#34-adding-a-event-task-event)<br/>
    3.5. [Listing all tasks: `list`](#35-listing-all-tasks-list)<br/>
    3.6. [Marking a task as done: `done`](#36-marking-a-task-as-done-done)<br/>
    3.7. [Deleting a task: `delete`](#37-deleting-a-task-delete)<br/>
    3.8. [Checking tasks on a date: `check`](#38-checking-tasks-on-a-date-check)<br/>
    3.9. [Finding tasks via keyword: `find`](#39-finding-tasks-via-keyword-find)<br/>
    3.10. [Clearing all entries: `clear`](#310-clearing-all-entries-clear)<br>
    3.11. [Exiting the program: `bye`](#311-exiting-the-program-bye)<br/>
    3.12. [Saving the data](#312-saving-the-data)<br/>
4. [FAQ](#4-faq)<br/>
5. [Command Summary](#5-command-summary)<br/>

## 1. Introduction
Duke is for those who want to record their "todo list". It allows user to add in, update, and keep track of their own daily tasks.
Duke adopts Command Line Interface (CLI).
If you can type fast, AB3 can get your contact management tasks done faster than traditional GUI apps. Interested? Jump to the [Section 2, “Quick Start”](#2-quick-start) to get started. Enjoy!

## 2. Quick Start
1. Ensure you have Java **11** or above installed in your Computer.
2. Download the latest **duke.jar** [here](https://github.com/SibingWu/duke/releases).
3. Copy the file to the folder you want to use as the home folder for your Duke.
4. Open a Terminal in that folder.
5. Run the command `java -jar duke.jar`. The CLI should appear in a few seconds. It should be a welcome page with existing task list.<br/>
![Welcome Page](WelcomePage.png)
6. Type the command in the command box and press Enter to execute it.<br/>
e.g. typing `help` and pressing <kbd>Enter</kbd> will open the help window.
7. Some example commands you can try:<br/>
   - `todo read book`: add the task "read book" into the task list
   - `list`: list all the tasks in the task list
   - `bye`: exit the Duke program
8. Refer to [Section 3, “Features”](#3-features) for details of each command.

## 3. Features
The word with UPPER_CASE: parameter.<br/>
The date format: yyyy-mm-dd.<br/>
### 3.1. Viewing help: `help`
Format: `help`
### 3.2. Adding a "Todo" task: `todo`
Adds an ordinary task to the task list.<br/>
Format: `todo TASK`<br/>
*TASK can be any content.*<br/>
Examples:<br/>
* `todo read book`
* `todo join sports club`<br/>

### 3.3. Adding a "Deadline" task: `deadline`
Adds a task with a deadline to the task list.<br/>
Format: `deadline TASK /by yyyy-mm-dd`<br/>
*TASK can be any content.*<br/>
Reminder: The deadline date format must be **yyyy-mm-dd**.<br/>
Examples:<br/>
* `deadline return book /by 2020-06-06`
* `deadline CS2113 iP project /by 2020-03-01`<br/>

### 3.4. Adding a "Event" task: `event`
Adds an event task on a specific date to the task list.<br/>
Format: `event TASK /at yyyy-mm-dd`<br/>
*TASK can be any content.*<br/>
Reminder: The deadline date format must be **yyyy-mm-dd**.<br/>
Examples:<br/>
* `event project meeting /at 2020-03-01`<br/>

### 3.5. Listing all tasks: `list`
Shows a list of all tasks in the Duke task list.<br/>
Format: `list`<br/>
### 3.6. Marking a task as done: `done`
Marks a task as done.<br/>
Format: `done TASK_NUMBER`<br/>
*TASK_NUMBER is the number of that task in the task list. It is an integer.*
Examples:<br/>
* `done 1`
* `done 4`<br/>

### 3.7. Deleting a task: `delete`
Deletes the specified task from the Duke task list.<br/>
Format: `delete TASK_NUMBER`<br/>
*TASK_NUMBER is the number of that task in the task list. It is an integer.*
Example:<br/>
* `delete 1`
* `delete 4`<br/>

### 3.8. Checking tasks on a date: `check`
Displays all the tasks on a specified date.<br/>
Format: `check /on yyyy-mm-dd`<br/>
Reminder: The deadline date format must be **yyyy-mm-dd**.<br/>
Example:<br/>
* `check /on 2020-03-03`<br/>

### 3.9. Finding tasks via keyword: `find`
Finds tasks whose names contain the given keyword.<br/>
Format: `find KEYWORD`
Example:<br/>  
* `find book`
* `find basketball`<br/>

### 3.10. Clearing all entries: `clear`
Clears all entries from the Duke task list.<br/>
Format: `clear`
### 3.11. Exiting the program: `bye`
Exit the Duke program.<br/>
Format: `bye`<br/>
### 3.12. Saving the data
Task list data are saved in the hard disk automatically after any command that changes the task list.<br/>
There is no need to save manually.

## 4. FAQ
Q: How do I transfer my data to another Computer?<br/>
A:  
1. Install the **duke.jar** in the other computer;<br/>
2. Copy the **data** folder in the previous Duke folder to the new Duke folder.

## 5. Command Summary
* Help `help`
* Add
  - `todo TASK`
  - `deadline TASK /by yyyy-mm-dd`
  - `event TASK /by yyyy-mm-dd`
* List `list`
* Done `done TASK_NUMBER`
* Delete `delete TASK_NUMBER`
* Search
  - `check /on yyyy-mm-dd`
  - `find KEYWORD`
* Clear `clear`
* Exit `bye`
