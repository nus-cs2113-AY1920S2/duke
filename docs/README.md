# User Guide for Duke
## Introduction
Duke is your personal chatbot who is capable of managing your task.

Some of the task that Duke can manage are *Todo*, *Deadline* and *Event*.

* **Todo:** A Task with description of what needs to be done.
* **Deadline:** A Task with description of what needs to be done along with the due date and time.
* **Event:** A Task with description of what needs to be done along with the date and time that the task will occur.

## Features
Below are the list of command that you can key in to interact with Duke.
1. Add Task
    1. Input: todo j  
    Example: todo CS2113 Homework  
    Command: Add a *Todo* Task with String description j.
    
    1. Input: deadline j /by d  
    Example: deadline CS2113 Homework /by 2020-04-15 16:00  
    Command: Add a *Deadline* Task with String description j, followed by "/by", then the corresponding due date in YYYY-MM-DD format and the time in HH:MM format.\
    
    1. Input: event j /at d  
    Example: deadline CS2113 Homework /at 2020-04-15 16:00  
    Command: Add a *Event* Task with String description j, followed by "/at", then the corresponding date in YYYY-MM-DD format and the time in HH:MM format.

1. List Task  
Input: list  
Example: list  
Command: List out all the stored task.

1. Complete Task  
Input: done i  
Example: done 3  
Command: Mark Task i as done, where i is the corresponding Task number.

1. Delete Task  
Input: delete i  
Example: delete 3  
Command: Delete Task i, where i is the corresponding Task number.

1. Find Task  
Input: find j  
Example: find Homework  
Command: Find all the task with description that contains the word/phrase j.

1. Exit  
Input: bye  
Command: Terminate the program and automatically help you store the Task into your hard disk with directory ../data/Duke.txt  
**Note:** Duke will help you take care of creating a folder and txt file if one does not already exist.

## Summary of Commands
| Index | Input | Command |
| ----- | ----- | --------------- |
| 01 | list | List out all the stored task |
| 02 | done index | Mark task index as done |
| 03 | bye | Terminate the program and store all Task into hard disk |
| 04 | todo description | Add a todo task with description |
| 05 | dateline description /by YYYY-MM-DD HH:MM | Add a deadline task description and due date |
| 06 | event description /by YYYY-MM-DD HH:MM | Add a event task description and due date |
| 07 | delete index | Delete task index |
| 08 | find description | Find all task containing the description |
