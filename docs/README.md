# User Guide

## What is Duke?
Duke is a personal assistant chatbot that helps a person keep track of various things.

## Features

### 1 - Adding of tasks
Keep track of various tasks by adding them to the task list in Duke.

### 2 - Can support up to 3 different types of tasks
Supports the adding of Todo, Deadline and Event tasks. For Deadline and Event tasks,
Duke can help to keep track of the given due date or the event date and time respectively.

### 3 - Mark task as done
Allows the user to mark an existing task as done. 

### 4 - Display the list of tasks
Display the full task list. Uses symbol [T], [D] and [E] respectively to differentiate between the Todo, Deadline and 
Event tasks. Also uses the (X) and (✓) respectively to show if the task is done or not.

### 5 - Delete a task
Deletes an existing task in the task list.

### 6 - Storing/Loading of tasks
Data on the task list is stored locally on the computer. Thus, information on the existing task list will not be lost 
when Duke terminates. This data will be loaded at the next execution of Duke.

### 7 - Find a task in the list using a keyword
Ability to find a particular task or a group of tasks in the task list using a keyword.

## Usage

### `todo` - Adds a Todo task

Adds a task of the Todo type and its task description to the task list.

Syntax: `todo <task description>`

Example of usage: `todo read book`

Expected outcome: 

`Got it. I've added this task:`\
`[T][✘] read book`\
`Now you have 11 tasks in the list.`

### `deadline` - Adds a Deadline task

Adds a task of the Deadline type with its task description and due date to the task list.

Syntax: `deadline <task description> /by <date and time>`

Example of usage: `deadline return book /by Sunday 2359`

Expected outcome:

`Got it. I've added this task:`\
`[D][✘] return book (by: Sunday 2359)`\
`Now you have 12 tasks in the list.`

### `event` - Adds a Event task

Adds a task of the Event type with its task description and event date and time to the task list.

Syntax: `event <task description> /at <date and time>`

Example of usage: `event project meeting /at Mon 2-4pm`

Expected outcome:

`Got it. I've added this task:`\
`[E][✘] project meeting (at: Mon 2-4pm)`\
`Now you have 13 tasks in the list.`

### `done` - Mark a task as done

Marks an existing task as done, i.e. changes the cross mark (X) to a check mark (✓).

Syntax: `done <task number>`

Example of usage: `done 11`\
*To get the task number of a task, use the function list(will be elaborated below)*

Expected outcome:

`Nice! I've marked this task as done:`\
`[T][✓] read book`

### `delete` - Delete a task from the task list

Deletes a task and removes it from the task list. \
*Immediately propagate the deletion to the local datafile stored on computer*.

Syntax: `delete <task number>`

Example of usage: `delete 8`\
*To get the task number of a task, use the function list(will be elaborated below)*

Expected outcome:

`Noted. I've removed this task:`\
`[E][✘] skype meeting (at: 26/2/2020 2-5pm)`\
`Now you have 9 tasks in the list.`

### `list` - Display all the tasks in the task list

These symbols [T],[D] and [E] are used to differentiate between the todo, deadline and event tasks. 
Also uses the (X) and (✓) respectively to show if the task is done or not. 
**The number beside each task is the task number** *(used for done and delete function)*.

Syntax: `list`

Expected outcome:

`Here are the tasks in your list:`\
`1. [D][✘] return book (by: June 6th)`\
`2. [E][✓] project meeting (at: Aug 6th 2-4pm)`\
`3. [T][✓] buy drinks`\
`4. [D][✘] 2113 iP (by: monday)`\
`5. [E][✘] watch movie (at: friday 4pm)`

### `find` - Find a task in the task list

Using a specified keyword, find all the tasks in the task lists that have this
keyword in their task description.

Syntax: `find <keyword>`

Example of usage: `find book`

Expected outcome:

`Here are the matching tasks in your list:`\
`1. [D][✘] return book (by: June 6th)`\
`2. [T][✓] read book`

