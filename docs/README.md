# User Guide

## Introduction

ChattyChatBot is a chatbot for those who prefer to use a desktop application for managing their tasks. If you would like to interact with a chatbot and have it help you manage all your tasks, ChattyChatBot is for you!

## Quick Start

1. Install `Java 11` or above in your computer
2. Download the latest JAR file for ChattyChatBot [here](https://github.com/Zhilin-Huang/duke/releases).
3. Move the JAR file to the folder you want to use as the home folder for your ChattyChatBot.
4. Double-click the file to start the application or run the command `java -jar [path-to-chatty-chat-bot-jar-file]` from terminal
5. Type in commands and start chatting with ChattyChatBot!

## Command Summary

- **Add ToDo task**: `todo DESCRIPTION`
- **Add Deadline task**: `deadline DESCRIPTION /by yyyy-mm-dd`
- **Add Event task**: `event DESCRIPTION /at yyyy-mm-dd to yyyy-mm-dd`
- **Mark task as done**: `done INDEX`
- **Delete task**: `delete INDEX`
- **List**: `list`
- **List tasks on date**: `date yyyy-mm-dd`
- **List tasks with keyword**: `find KEYWORD`
- **Exit**: `bye`

## Features

### `todo` - Add ToDo Task

Adds a task of ToDo type to the list.

Example of usage: 

`todo DESCRIPTION`

Expected outcome:

```
Got it. I've added this task:
[T][✘] DESCRIPTION
Now you have NUMBER tasks in the list.
```

### `deadline` - Add Deadline Task

Adds a task of Deadline type to the list.

Example of usage:

`deadline DESCRIPTION /by yyyy-mm-dd`

Expected outcome:

```
Got it. I've added this task:
[D][✘] DESCRIPTION (by: MMM dd yyyy)
Now you have NUMBER tasks in the list.
```

### `event` - Add Event Task

Adds a task of Event type to the list.

Example of usage:

`event DESCRIPTION /at yyyy-mm-dd to yyyy-mm-dd`

Expected outcome:

```
Got it. I've added this task:
[E][✘] DESCRIPTION (at: MMM dd yyyy to MMM dd yyyy)
Now you have NUMBER tasks in the list.
```

### `done` - Mark Task as Done

Marks a task in the list as done.

Example of usage:

`done INDEX`

Expected outcome:

```
Congratulations! You've successfully marked the following task as done:
[TYPE][✓] DESCRIPTION
```

### `delete` - Delete a Task

Deletes a task from the list.

Example of usage:

`delete INDEX`

Expected outcome:

```
Successfully deleted the following task:
[TYPE][✓] DESCRIPTION
```

### `list` - List All Tasks

Lists all tasks in the list.

Example of usage:

`list`

Expected outcome:

```
Here are the tasks in your list:
1. [TYPE][MARKER] DESCRIPTION
2. [TYPE][MARKER] DESCRIPTION
3. [TYPE][MARKER] DESCRIPTION
...
```

### `date` - List Tasks on Date

Lists all tasks on a certain date.

Example of usage:

`date yyyy-mm-dd`

Expected outcome:

```
Here are the tasks on yyyy-mm-dd
1. [TYPE][MARKER] DESCRIPTION
2. [TYPE][MARKER] DESCRIPTION
3. [TYPE][MARKER] DESCRIPTION
...
```

### `find` - List Tasks with Keyword

Lists all tasks with a certain keyword.

Example of usage:

`find KEYWORD`

Expected outcome:

```
Here are the tasks with keyword 'KEYWORD' in your list:
1. [TYPE][MARKER] DESCRIPTION
2. [TYPE][MARKER] DESCRIPTION
3. [TYPE][MARKER] DESCRIPTION
...
```

### `bye` - Exit Application

Exits the application.

Example of usage:

`bye`

Expected outcome:

```
Your tasks have been successfully saved to disk!
Thanks for chatting with Chatty Chat Bot
See you again soon!
```
