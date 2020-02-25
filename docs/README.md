# User Guide

##Table Of Contents
### 1. Introduction
### 2. Quick Start
### 3. Features
### 4. FAQ
### 5. Command Summary

## 1. Introduction

	Duke is a command line based application used for managing tasks.
 The various types of tasks it can handle are:
 * **ToDo** Tasks --> Contains a simple description of the task and completion status.
 * **Deadline** Tasks --> Contains the description, completion status and deadline timing details.
 * **Event** Tasks --> Contains the description, completion status  and location details of the task.

 The application can add, delete tasks. It also provides methods to search for tasks 
 and has the ability of marking the tasks as done when they are completed. It also has the ability
 of storing data to a physical location on the computer, so that the tasks always remain in memory.
 

## 2. Quick Start
 
 * Ensure you have Java 11 or above installed in your Computer.
 * Download the latest iP.jar from https://github.com/GanapathySanathBalaji/duke/releases and place it in an empty folder. 
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

## 3. Features

### 1. Add a new ToDo task
Adds a new ToDo task to the list of tasks.

## Usage

### `todo description` - Adds a new ToDo task to the list of tasks

The command creates a new ToDo task with the description provided.
Upon success a successful addition message similar to the one in the example would appear.
If the wrong format is used an alert would be displayed.

Example of usage: 

`todo homework`

Expected outcome:
The ToDo task would be added to the list.
Upon success a response similar to the following one would appear:

`    __________________________________________________________________________________________
     Got it. I've added this task:
       [T][ ] homework
     Now you have 1 task in the list.
    __________________________________________________________________________________________`

### 2. Add a new Deadline task
Adds a new Deadline task to the list of tasks.

## Usage

### `deadline description /by yyyy-mm-dd hhmm` - Adds a new Deadline task to the list of tasks


The command creates a new Deadline task with the description and timing details provided.
Note: The date and time should be provided in the correct format
Upon success a successful addition message similar to the one in the example would appear.
If the wrong format is used an alert would be displayed.
Example of usage: 

`deadline assignment /by 2020-03-21 1700`

Expected outcome:
The Deadline task would be added to the list.
Upon success a response similar to the following one would appear:
`    __________________________________________________________________________________________
     Got it. I've added this task:
       [D][ ] assignment (by: Mar 21 2020 1700 Hrs )
     Now you have 2 tasks in the list.
    __________________________________________________________________________________________`

### 3. Add a new Event task
Adds a new Event task to the list of tasks.

## Usage

### `event description /at location` - Adds a new ToDo task to the list of tasks

The command creates a new Event task with the description and the location detail provided.
Upon success a successful addition message similar to the one in the example would appear.
If the wrong format is used an alert would be displayed.

Example of usage: 

`event meeting /at school`

Expected outcome:
The ToDo task would be added to the list.
Upon success a response similar to the following one would appear:

`  __________________________________________________________________________________________
     Got it. I've added this task:
       [E][ ] meeting (at: school)
     Now you have 3 tasks in the list.
    __________________________________________________________________________________________
`


## 4. FAQ
## 5. Command Summary