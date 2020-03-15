# Duke's User Guide

# Table of content
<!-- TOC -->
1. [Introduction](#1-introduction)
1. [Pre-requisites](#2-pre-requisites)
1. [Features](#3-features)
    1. [Add task](#i-add-task)
    1. [List task](#ii-list-task)
    1. [Mark task as done](#iii-mark-task-as-done)
    1. [Delete task](#iv-delete-task)
    1. [Find task](#v-find-task)
    1. [Exit Program](#vi-exit-program)
1. [Usage](#4-usage)
    - [`todo [description]`](#todo-description)
    - [`deadline [description] /by [yyyy-mm-dd]`](#deadline-description-by-yyyy-mm-dd)
    - [`event [description] /at [yyyy-mm-dd]`](#event-description-at-yyyy-mm-dd)
    - [`list`](#list)
    - [`done [index_of_task]`](#done-index_of_task)
    - [`delete [index_of_task]`](#delete-index_of_task)
    - [`find [key_words]`](#find-key_words)
    - [`bye`](#bye)
<!-- /TOC -->

## 1. Introduction
    
    ____________________________________________________________
	Hello! I'm Duke
	It seems like you are needing some help.
	____________________________________________________________
	
Duke is a chatbot that actually resemble what is so-called ToDoList applications that we usually use nowadays.
However, this application has not yet had a GUI (Graphics User Interface) like the one we usually see but it is
functioning with CLI (Command Line Interface). Since there is no GUI, it will be a bit tough for some customer
to get used to this application. Therefore, we will be having a brief explanation of how to get started to this 
chatbot

To run the chatbot, type this command to the terminal/command prompt
    
    java -jar duke-0.2.jar

Remember to set path for Java beforehand or you can simply set a permanent path for java by typing

    set path={some_path_in_your_folder}\Java\jdk-11.0.1\bin
    
At first there will be a warning that there is a data file `data/duke.txt` that has not existed and it will create a 
new directory and save them after terminating the program

## 2. Pre-requisites

JDK 11 and above, you could set up the framework online at 
https://www.oracle.com/java/technologies/javase-jdk11-downloads.html

## 3. Features 

### i. Add Task 
Add a task in 1 of these 3 types (Todo , Deadline, Event)

### ii. List Task
List all the tasks available from the data

### iii. Mark Task As Done
When you finish a task, you could mark them as done so as not to refer to it next time

### iv. Delete Task
Delete a task that is not necessary anymore

### v. Find Task
Find tasks with containing certain key words from the task's descriptions

### vi. Exit Program
The program will be terminated and your data will be saved

## 4. Usage

### `todo [description]`

The command contains 2 parts:
1. "todo" word
1. [description]: the description of your task
It will then print out a message confirmed that your todo's task is added successfully.

Example of usage: 

`todo read books`

Expected outcome:

    ____________________________________________________________
 	Got it. I've added this task:
 	[T][✗] read books
 	Now you have 5 tasks in the list.
 	____________________________________________________________
 	
### `deadline [description] /by [yyyy-mm-dd]`

The command contains:
1. "deadline" word
1. [description]: the description of your task
1. /by: the word used to realize date
1. [yyyy-mm-dd]: date of the year in this form
It will then print out a message confirmed that your deadline's task is added successfully and number of
tasks left.

Example of usage: 

`deadline CS2113's iP /by 2020-03-02`

Expected outcome:

    ____________________________________________________________
    Got it. I've added this task:
    [D][✗] CS2113's iP (by: Mar 2 2020)
    Now you have 6 tasks in the list.
    ____________________________________________________________
    
### `event [description] /at [yyyy-mm-dd]`

The command contains:
1. "event" word
1. [description]: the description of your task
1. /by: the word used to realize date
1. [yyyy-mm-dd]: date of the year in this form
It will then print out a message confirmed that your event's task is added successfully and number of tasks left.

Example of usage: 

`event April's Fool /at 2020-04-01`

Expected outcome:

    ____________________________________________________________
    Got it. I've added this task:
    [E][✗] April's Fool (at: Apr 1 2020)
    Now you have 7 tasks in the list.
    ____________________________________________________________
    
### `list`

It will list out all the tasks from index 1 along with task's description and time if available

Example of usage: 

`list`

Expected outcome:

    ____________________________________________________________
    Here are the tasks in your list:
    1.[T][✓] haha
    2.[T][✗] read book
    3.[E][✗] do book stuffs (at: Nov 17 2000)
    4.[D][✗] read book (by: Nov 17 2010)
    5.[T][✗] read books
    6.[D][✗] CS2113's iP (by: Mar 2 2020)
    7.[E][✗] April's Fool (at: Apr 1 2020)
    ____________________________________________________________
    
### `done [index_of_task]`

The command contains:
1. "done" word
1. [index_of_task]: index of the task to be mark as done
It will print a message that confirmed that this task has been mark as done.

Example of usage: 

`done 6`

Expected outcome:

	____________________________________________________________
	Nice! I've marked this task as done:
	[D][✓] CS2113's iP (by: Mar 2 2020)
	____________________________________________________________

### `delete [index_of_task]`

The command contains:
1. "delete" word
1. [index_of_task]: index of the task to be removed
It will print out the task that you want to delete and show the number of tasks left.

Example of usage: 

`done 6`

Expected outcome:

	____________________________________________________________
	Noted. I've removed this task:
	[D][✗] read book (by: Nov 17 2010)
	Now you have 6 tasks in the list.
	____________________________________________________________

### `find [key_words]`

The command contains:
1. "find" word
1. [key_words]: words contained in the task's description
It will print out all tasks that contain the matching key words.

Example of usage: 

`find book`

Expected outcome:

	____________________________________________________________
	Here are the matching tasks in your list:
	1: [T][✗] read book
	2: [E][✗] do book stuffs (at: Nov 17 2000)
	3: [T][✗] read books
	____________________________________________________________

### `bye`

This command will make the program to terminate and your data will be saved
It will print out a bye message.

Example of usage: 

`bye`

Expected outcome:

	____________________________________________________________
	Bye. Hope to see you again soon!
	____________________________________________________________
