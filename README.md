# 1. Introduction

**Duke** is a *task management system* that can help you keep track of various tasks. It is a **Command Line Interface(CLI)**.

# 2. Quick Start

1. Ensure you have a Java **`11`** or above installed in your computer
1. Download the latest **`duke.jar`**
1. Copy the file to the folder you want to use as the working directory for your Duke
1. Open Command Prompt(CMD) of your computer and direct to the working directory of **`Duke`**
1. Type the command **`java -jar duke.jar`** to launch the program
1. Type the command in CLI to manage your tasks in **`Duke`**
1. Some example commands you can try:
   * **`help`**: show a simple user guide
   * **`list`**: list all tasks in the task list
   * **`todo read book`**: add a todo task called **`read book`** into the task list
   * **`delete 3`**: delete the 3rd task in the task list
   * **`bye`**: exit the software

# 3. Features

### 3.1 Viewing help: **`help`**
Format: `help`

### 3.2 Adding a Todo task: **`todo`**
Format: `todo [TaskName]`

### 3.3 Adding a Deadline task: **`deadline`**
Format: `deadline [TaskName] /by [Deadline]`

### 3.4 Adding a Event task: **`event`**
Format: `event [TaskName] /at [Timeslot]`

### 3.5 Marking a task as Done: **`done`**
Format: `done [Task#]`

### 3.6 Deleting a task: **`delete`**
Format: `delete [Task#]`

### 3.7 Finding tasks by searching for a keyword: **`find`**
Format: `find [Keyword]`

### 3.8 Listing all tasks: **`list`**
Format: `list`

### 3.9 Exiting the program: **`bye`**
Format: `bye`

### 3.10 Saving the data
The task list is saved in the hard disk automatically after any command that changes the list and saved data will be loaded up when launching the program.
There is no need to save or load data manually.

# 4. Command Summary
* **todo**: `todo [TaskName]`
   e.g. `todo read book`
* **deadline**: `deadline [TaskName] /by [Deadline]`
   e.g. `deadline return book /by Sunday`
* **event**: `event [TaskName] /at [Timeslot]`
   e.g. `event project meeting /at Monday 2-4pm`
* **done**: `done [Task#]`
   e.g. `done 3`
* **delete**: `delete [Task#]`
   e.g. `delete 2`
* **find**: `find [Keyword]`
   e.g. `find book`
* **list**: `list`
* **help**: `help`
* **bye**: `bye`
