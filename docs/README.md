# User Guide

## Content
1. [Introduction](#1-introduction)
2. [Download](#2-download)
3. [Features](#3-features)
    1. [Add a task](#add-a-task)
    2. [Delete a task](#delete-a-task)
    3. [Mark task as done](#mark-task-as-done)
    4. [View task list](#view-task-list)
    5. [Find a task](#find-a-task)
    6. [View help menu](#view-help-menu)
    7. [Exit Program](#exit-program)
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
 * The following text-based user interface should be displayed:
 
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
The respective task will be created and added to the task list.

    __________________________________________________________________________________________
    
    __________________________________________________________________________________________


### Delete a task
This command deletes a task from the task list depending on the specified index.

#### Usage
* Delete task at specified index: `delete index`

**Example of usage:** <br>
> `delete 3`

**Expected outcome:** <br>
The task at the specified index will be deleted from the list.

    __________________________________________________________________________________________
  
    __________________________________________________________________________________________

### Mark task as done
This command marks a task as done depending on the specified index.

#### Usage
* Mark task as done at specified index: `done index`

**Example of usage:** <br>
> `done 2`

**Expected outcome:** <br>
The task at the specified index will be marked as done.

    __________________________________________________________________________________________

    __________________________________________________________________________________________
 
### View task list
This command displays the current task list.

#### Usage
* Display task list: `list`

**Example of usage:** <br> 
> `list`

**Expected outcome:** <br>
All tasks in the task list will be displayed according to their current index.

    __________________________________________________________________________________________
  
    __________________________________________________________________________________________
    

### Find a task
This command performs a search of all tasks currently in the task list and displays 
a list of all tasks that contains a specified substring in the description.

#### Usage
* Find task using substring: `find substring`

**Example of usage:** <br>
> `find meeting`

**Expected outcome:** <br>
All matching tasks in the task list will be displayed in a numbered list.

    __________________________________________________________________________________________

    __________________________________________________________________________________________   

### View help menu
This command displays the list of commands supported by the application.

#### Usage
* Display help menu: `/help`

**Example of usage:** <br>
> `/help`

**Expected outcome:** <br>
The list of all commands supported by the application will be displayed.

    __________________________________________________________________________________________
    
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

    __________________________________________________________________________________________

## 4. Summary
1. 
2.
3.
4.
5.
6.
7.
8.
9.
10. 

