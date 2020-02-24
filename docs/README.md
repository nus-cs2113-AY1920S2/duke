# User Guide
## Table of Contents
1. **Introduction**
2. **Features**
    - 2.1. **Add Tasks**
        - 2.1.a **Add Todo Task**
        - 2.1.b **Add Event Task**
        - 2.1.c **Add Deadline Task**
    - 2.2. **Mark As Done**
    - 2.3. **Delete Tasks**
    - 2.4. **List Tasks**
    - 2.5. **Find Tasks**
    - 2.6. **Save**
    - 2.7. **Load**
3. **Usage**

## 1. Introduction
Duke is a personal assistant chatbot that helps a person to keep track of upcoming tasks.

## 2. Features 
### 2.1. Add Tasks
#### 2.1.a Add Todo Task
- Add a `todo` task into task list.
- Format: `todo TASK`

#### 2.1.b Add Event Task
- Add an `event` task into task list.
- Format: `event TASK /at TIME`

#### 2.1.c Add Deadline Task
- Add a `deadline` task into task list.
- Format: `deadline TASK /by TIME`

### 2.2. Mark As Done
- User can mark a task as done by the given task number.
- Format: `done TASK_NUMBER`

### 2.3. Delete Tasks
- User can delete a task by the given task number.
- Format: `delete TASK_NUMBER`

### 2.4. List Tasks
- User can list all the tasks in the task list.
- Format: `list`

### 2.5. Find Tasks
- User can search related tasks by a given keyword.
- Format: `find KEYWORD`

### 2.6. Save
Duke save the tasks in the hard disk automatically whenever the task list changes.

### 2.7. Load
Load the data automatically from the hard disk when Duke starts up.

## 3. Usage
### Todo
- Format: ```todo TASK```
- Example of usage: ```todo read book```
- Expected outcome:
```
╔═══════════════════════════════════════════════════════════╗
║               Got it. I've added this task:               ║
║                    1. [T] [x] read book                   ║
║             Now you have 1 tasks in the list.             ║
╚═══════════════════════════════════════════════════════════╝
What can I do for you? Type 'bye' to exit.
```

### Event
- Format: ```event TASK /at TIME```
- Example of usage: ```event project meeting /at 2020-02-12```
- Expected outcome:
```
╔═══════════════════════════════════════════════════════════╗
║               Got it. I've added this task:               ║
║        2. [E] [x] project meeting (at: Feb 12 2020)       ║
║              Now you have 2 tasks in the list.            ║
╚═══════════════════════════════════════════════════════════╝
What can I do for you? Type 'bye' to exit.
```

### Deadline
- Format: ```deadline TASK /by TIME```
- Example of usage: ```deadline return book /by Mon```
- Expected outcome:
```
╔═══════════════════════════════════════════════════════════╗
║               Got it. I've added this task:               ║
║              3. [D] [x] return book (by: Mon)             ║
║              Now you have 3 tasks in the list.            ║
╚═══════════════════════════════════════════════════════════╝
What can I do for you? Type 'bye' to exit.
```

### Mark As Done
- Format: ```done TASK_NUMBER```
- Example of usage: ```done 3```
- Expected outcome:
```
╔═══════════════════════════════════════════════════════════╗
║           Nice! I've marked this task as done:            ║
║              3. [D] [v] return book (by: Mon)             ║
╚═══════════════════════════════════════════════════════════╝
What can I do for you? Type 'bye' to exit.
```

### Delete Tasks
- Format: ```delete TASK_NUMBER```
- Example of usage: ```delete 3```
- Expected outcome:
```
╔═══════════════════════════════════════════════════════════╗
║              Noted. I've removed this task:               ║
║              3. [D] [v] return book (by: Mon)             ║
║              Now you have 2 tasks in the list.            ║
╚═══════════════════════════════════════════════════════════╝
What can I do for you? Type 'bye' to exit.
```

### List
- Format: ```list```
- Expected outcome:
```
╔═══════════════════════════════════════════════════════════╗
║              Here are the tasks in your list:             ║
║                    1. [T] [x] read book                   ║
║        2. [E] [x] project meeting (at: Feb 12 2020)       ║
╚═══════════════════════════════════════════════════════════╝
What can I do for you? Type 'bye' to exit.
```

### Find Tasks
- Format: ```find KEYWORD```
- Example of usage: ```find book```
- Expected outcome:
```
╔═══════════════════════════════════════════════════════════╗
║         Here are the matching tasks in your list:         ║
║                    1. [T] [x] read book                   ║
╚═══════════════════════════════════════════════════════════╝
What can I do for you? Type 'bye' to exit.
```
