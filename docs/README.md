# [Project Duke] (https://www.github.com/A11riseforme/duke) - User Guide
By: `A11riseforme`      Since: `Feb 2020`      Licence: `MIT`      Course: `CS2113`

* [1. Introduction](#introduction)
* [2. Quick Start](#quick-start)
* [3. Features](#features)
    + [3.1 View the help message:](#help)`help`
    + [3.2. Add a Todo task:](#todo) `todo`/`td`
    + [3.3. Add a Deadline task:](#deadline) `deadline`/`ddl`
    + [3.4. Add a Event task:](#event) `event`/`evt`
    + [3.5. List all tasks:](#list) `list`/`ls`
    + [3.6. Mark a task as done:](#done) `done`
    + [3.7. Delete a task:](#delete) `delete`/`del`
    + [3.8. Search for specific tasks by keywords:](#find) `find`
    + [3.9. Exit the program:](#exit) `bye`
    + [3.10. Save the data:](#save)
* [4. Command Summary](#command-summary)

<a name="introduction"></a>

## 1. Introduction

Duke is a *task management system* which aims to help you to keep track of various tasks. It is a java application based on **Command Line Interface(CLI)**.

<a name="quick-start"></a>
## 2. Quick Start

1. Ensure you have **`Java 11`** installed in your computer, it may work with other versions, but with no guarantee.
1. Download the latest **`duke.jar`** from the [release page](https://github.com/A11riseforme/duke/releases).
1. Copy the file to the folder you want to use as the working directory for your Duke.
1. Launch the terminal(cmd.exe for windows), and navigate to the working directory of Duke.
1. Type the command **`java -jar duke.jar`** to launch the program.
1. Type the command to manage your tasks in Duke.
1. Some example commands you can try:
   * **`help`**: show a simple user guide.
   * **`list`**: list all tasks in the task list.
   * **`todo read book`**: add a todo task with the description of **`read book`** into the task list.
   * **`delete 3`**: delete the task with the task id 3 in the task list.
   * **`bye`**: exit the software.

<a name="features"></a>
## 3. Features

**Command Format**

- Words in the format of `<UPPER_CASE>` are the parameters to be supplied by the user e.g. in `todo <TASK_DESCRIPTION>`, `<TASK_DESCRIPTION>` is a parameter which can be used as `todo revise cs3230`.
- Items in square brackets are optional e.g., `help <COMMAND_WORD>`can be used as`help <COMMAND_WORD>` or `help`.

<a name="help"></a>
### 3.1 View the help message: `help`

Format: `help [<COMMAND_WORD>]`

<a name="todo"></a>
### 3.2 Add a Todo task: `todo` or `td`

Format: `todo <TASK_DESCRIPTION>` or `td <TASK_DESCRIPTION>`

<a name="deadline"></a>
### 3.3 Add a Deadline task: `deadline` or `ddl`

Format: `deadline <TASK_DESCRIPTION> /by <DEADLINE_DATE>` or `ddl <TASK_DESCRIPTION> /by <DEADLINE_DATE>`

*note that the `<DEADLINE_DATE>` should be in the form of yyyy-mm-dd (e.g., 2020-12-29).*

<a name="event"></a>
### 3.4 Add a Event task: `event` or `evt`

Format: `event <TASK_DESCRIPTION> /at <EVENT_DATE>` or `evt <TASK_DESCRIPTION> /at <EVENT_DATE>`

*note that the `<EVENT_DATE>` should be in the form of yyyy-mm-dd (e.g., 2020-12-29).*

<a name="list"></a>
### 3.5 List all tasks: `list` or `ls`

Format: `list`

<a name="done"></a>
### 3.6 Mark a task as done: `done`

Format: `done <TASK_ID>`

*the task id corresponds to the id of the task listed in the `list` command*

<a name="delete"></a>
### 3.7 Delete a task: `delete` or `del`

Format: `delete <TASK_ID>` or `del <TASK_ID>`

*the task id corresponds to the id of the task listed in the `list` command*

<a name="find"></a>
### 3.8 Search for specific tasks by keywords: **`find`**

Format: `find <KEYWORD>`

*This is function is case insensitive, so 'asdf' will match 'AsdF'*

<a name="exit"></a>
### 3.9 Exit the program: `bye` or `quit` or `exit`

Format: `bye` or `quit` or `exit`

<a name="save"></a>
### 3.10 Save the data

The tasks will be saved in the hard disk automatically before the program exits, and the saved data will be loaded up when launching the program. If the program exits unexpectedly, the file will not be changed, and the newly added tasks will not be saved.

<a name="command-summary"></a>
# 4. Command Summary

* **todo**: `todo <TASK_DESCRIPTION>`
  e.g. `todo cs3235 assignment` or `td cs3235 assignment`

* **deadline**: `deadline <TASK_DESCRIPTION> /by <DEADLINE_DATE>` or `ddl <TASK_DESCRIPTION> /by <DEADLINE_DATE>`
  e.g. `deadline cs3230 assignment /by 2020/03/06`

* **event**: `event <TASK_DESCRIPTION> /at <EVENT_DATE>` or `evt <TASK_DESCRIPTION> /at <EVENT_DATE>`
  e.g. `event project meeting /at 2020/03/02`

* **list**: `list`

* **done**: `done <TASK_ID>`
  e.g. `done 3`

* **delete**: `delete <TASK_ID>` or `del <TASK_ID>`
  e.g. `delete 2`

* **find**: `find <KEYWORD>`
  e.g. `find book`

* **help**: `help`

  e.g. `help deadline` or `help`

* **bye**: `bye` or `quit` or `exit`