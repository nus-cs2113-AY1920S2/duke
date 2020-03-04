# User Guide
Welcome to Duke/NUSBOT! This is a minimal command-line task list manager that can be used to keep track of your tasks.

## Prerequisites
- Java Development Kit (JDK) >= 11

## Installation
1. Download the `duke.jar` file from the [latest release](https://github.com/rdimaio/duke/releases/tag/v0.2.1)
2. Place the `duke.jar` in the folder of your choice (data will be saved to the `/data/` subfolder)
3. Run `java -jar duke.jar`

## Features 


### Todo
Add a simple task that can be marked as done.

### Deadline
Add a task to be completed by a set deadline.

### Event
Add a task involving a specific date.

### List
List all tasks.

### Mark as done
Mark task as done.

### Delete
Delete task.

### Find
Find task.

## Usage

### `todo <description>` - Add todo

Adds a task to be done.

Example of usage: 

`todo study for test`

Expected outcome:

`Added task: [T][X] study for test`

### `deadline <description> /by <date (yyyy-mm-dd)>` - Add deadline

Adds a task to be completed by a set deadline.

Example of usage: 

`deadline finalize design /by 2020-06-05`

Expected outcome:

`Added task: [D][X] finalize design (by: Jun 5 2020)`

### `event <description> /at <date (yyyy-mm-dd)>` - Add event

Adds a task involving a specific date.

Example of usage: 

`event attend meeting /at 2020-05-15`

Expected outcome:

`Added task: [E][X] attend meeting (on: May 15 2020)`

### `list` - List tasks

Lists all the current tasks.

Example of usage: 

`list`

Expected outcome:

```
 1. [T][X] study for test
 2. [D][X] finalize design (by: Jun 5 2020)
 3. [E][X] attend meeting (on: May 15 2020)
```

### `done <task-id>` - Mark task as done

Marks task starting with the specified ID as done.

Example of usage: 

`done 1`

Expected outcome:

`Marked task as done.`

New output of `list`:

```
1. [T][/] study for test
2. [D][X] finalize design (by: Jun 5 2020)
3. [E][X] attend meeting (on: May 15 2020)
```

### `delete <task-id>` - Delete task

Deletes task starting with the specified ID.

Example of usage: 

`delete 1`

Expected outcome:

`Deleted task 1`

New output of `list`:

```
1. [D][X] finalize design (by: Jun 5 2020)
2. [E][X] attend meeting (on: May 15 2020)
```

### `find <string>` - Find text in tasks

Searches the task descriptions and returns tasks that contain the input string.

Example of usage: 

`find design`

Expected outcome:

```
Found 1 match(es) in your list:
[D][X] finalize design (by: Jun 5 2020)
```
