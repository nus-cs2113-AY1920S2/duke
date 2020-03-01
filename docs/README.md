# User Guide

## Content
1. [Introduction](#1-introduction)
2. [Download](#2-download)
3. [Features](#3-features)
    1. [Add a task](#add-a-task)
    2. [Delete a task](#delete-a-task)
    3. [Mark task as done](#mark-task-as-done)
    4. [View task list](#view-task-list)
    5. [Clear task list](#clear-task-list)
    6. [Find a task](#find-a-task)
    7. [View help menu](#view-help-menu)
    8. [Exit Program](#exit-program)
4. [Summary](#4-summary)


## 1. Introduction

 Duke is a personal text-based chat-bot application used for managing tasks.
 Duke is capable of handling 3 different types of tasks:
 * _**ToDo**_ (Consists of a short description)
 * _**Deadline**_ (Consists of a short description and task deadline details)
 * _**Event**_ (Consists of a short description and task location details)

## 2. Download
 
 * Ensure that you have Java 11 or above installed in your Computer.
 * Download the latest version of duke.jar [here](https://github.com/dejunnn/duke/releases).
 * Open and run the duke.jar file.
 * Duke will store task list data as a "duke.txt" file in the "data" directory.

If there is no directory present, Duke will create one upon startup. Duke will also create an empty duke.txt file.
     
     __________________________________________________________________________________________
     New Directory created: data
     No existing file is found, new file created: duke.txt
     __________________________________________________________________________________________

If there is an existing directory and duke.txt file present, Duke will load existing data from the file.
    
    __________________________________________________________________________________________
    File already exists. Existing data loaded from: duke.txt
    __________________________________________________________________________________________

The following text-based user interface should be displayed:
    
    __________________________________________________________________________________________
     ____        _        
    |  _ \ _   _| | _____ 
    | | | | | | | |/ / _ \
    | |_| | |_| |   <  __/
    |____/ \__,_|_|\_\___|
    
    Hello! I'm Duke. What can I do for you?
    Type /help for a list of supported commands.
    __________________________________________________________________________________________

## 3. Features

### Add a task
This command creates a new task and adds it to the task list.

#### Usage
* Add new ToDo: `todo description` <br>
* Add new Deadline: `deadline description /by deadline` <br>
* Add new Event: `event description /at location` <br>

**Example of usage:** <br>
> `todo project` <br>
> `deadline project-report /by 2020-05-18` <br>
> `event project-meeting /at 2020-05-10` <br>

**Expected outcome:** <br>
The respective tasks will be created and added to the task list.

    todo project
    __________________________________________________________________________________________
    Noted! I have added a new todo.
    [T] [X] project
    You now have 1 tasks in the list.
    __________________________________________________________________________________________
    deadline project-report /by 2020-05-18
    __________________________________________________________________________________________
    Noted! I have added a new deadline.
    [D] [X] project-report  (by: 18 May 2020)
    You now have 2 tasks in the list.
    __________________________________________________________________________________________
    event project-meeting /at 2020-05-10
    __________________________________________________________________________________________
    Noted! I have added a new event.
    [E] [X] project-meeting  (at: 2020-05-10)
    You now have 3 tasks in the list.
    __________________________________________________________________________________________


### Delete a task
This command deletes a task from the task list depending on the specified index.

#### Usage
* Delete task at specified index: `delete index`

**Example of usage:** <br>
> `delete 3`

**Expected outcome:** <br>
The task at the specified index will be deleted from the list.

    delete 3
    __________________________________________________________________________________________
    Got it! The following task has been successfully deleted:
    [E] [X] project-meeting  (at: 2020-05-10)
    __________________________________________________________________________________________

### Mark task as done
This command marks a task as done depending on the specified index.

#### Usage
* Mark task as done at specified index: `done index`

**Example of usage:** <br>
> `done 2`

**Expected outcome:** <br>
The task at the specified index will be marked as done.

    done 2
    __________________________________________________________________________________________
    Great! I have marked this task as done.
    [D] [/] project-report  (by: 18 May 2020)
    __________________________________________________________________________________________
 
### View task list
This command displays the current task list.

#### Usage
* Display task list: `list`

**Example of usage:** <br> 
> `list`

**Expected outcome:** <br>
All tasks in the task list will be displayed according to their current index.

    list
    __________________________________________________________________________________________
    Here are the tasks in your list:
    1. [T] [X] project
    2. [D] [/] project-report  (by: 18 May 2020)
    __________________________________________________________________________________________
    
### Clear task list
This command clears the current task list.

#### Usage
* Clear task list: `clear`

**Example of usage:** <br> 
> `clear`

**Expected outcome:** <br>
All tasks in the task list will be cleared after second confirmation by the user.

    clear
    __________________________________________________________________________________________
    Are you sure you want to clear all tasks in your list? Type 'Y' to confirm.
    __________________________________________________________________________________________
    Y
    __________________________________________________________________________________________
    Your task list has been cleared successfully!
    __________________________________________________________________________________________


    clear
    __________________________________________________________________________________________
    Are you sure you want to clear all tasks in your list? Type 'Y' to confirm.
    __________________________________________________________________________________________
    N
    __________________________________________________________________________________________
    You task list has not been cleared.
    __________________________________________________________________________________________

        
### Find a task
This command performs a search of all tasks currently in the task list and displays 
a list of all tasks that contains a specified substring in the description.

#### Usage
* Find task using substring: `find substring`

**Example of usage:** <br>
> `find report`

**Expected outcome:** <br>
All matching tasks in the task list will be displayed in a numbered list.

    find report
    __________________________________________________________________________________________
    Here are the matching tasks in your list:
    1. [D] [/] project-report  (by: 18 May 2020)
    __________________________________________________________________________________________   

### View help menu
This command displays the list of commands supported by the application.

#### Usage
* Display help menu: `/help`

**Example of usage:** <br>
> `/help`

**Expected outcome:** <br>
The list of all commands supported by the application will be displayed.

    /help
    __________________________________________________________________________________________
    The following commands are supported by Duke:
    
    To add a ToDo, enter: todo description
    To add a Deadline, date in yyyy-mm-dd or otherwise, enter: deadline /by date
    To add an Event, enter: event /at location
    To find a task from a substring, enter: find substring
    To delete a task from your task list with its index, enter: delete index
    To mark a task as done with its index, enter: done index
    To display your task list, enter: list
    To clear your task list, enter: clear
    To display the help list, enter: /help
    To exit the programme, enter: bye

    __________________________________________________________________________________________

### Exit Program
This command is used to exit the application.

#### Usage
* Exit program: `bye`

**Example of usage:** <br>
> `bye`

**Expected outcome:** <br>
The application will terminate.

    __________________________________________________________________________________________
    Goodbye. Hope to see you again soon!
    __________________________________________________________________________________________

## 4. Summary

No. | Command | Description
----| ------- | -----------
1  | `todo description` | Add new ToDo
2  | `deadline description /by deadline` | Add new Deadline
3  | `event description /at location` | Add new Event
4  | `delete index` | Delete task
5  | `done index` | Mark task as done
6  | `list` | Display task list
7  | `clear` | Clear task list
8  | `find substring` | Find task
9  | `/help` | Display help menu
10 | `bye` | Exit program

