# User Guide

## Features 

### Help
Fetch the command list.

## Usage
### `Keyword` - Describe action

Describe action and its outcome.

Example of usage: `keyword (optional arguments)`

Expected outcome: `outcome`

### `Bye` - Exits programme.

Programme closes.

Example of usage:  `bye`

Expected outcome: `Programme closes`

### `Clear` - Deletes all tasks in the list.

Example of usage:  `clear`

Expected outcome: `List becomes empty`

### `Deadline` - Creates task with a deadline.

deadline [description] /by [date]

Example of usage:  `deadline tutorial /by 1/2/2020`

Expected outcome: `1. [D][✗] tutorial (by 1/2/2020)`

### `Delete` - Deletes task with task number.

delete [number]

Example of usage:  `delete 1`

Expected outcome: `Deletes task numbered 1`

### `Done` - Marks task with task number as done.

done [task number]

Example of usage:  `done 1`

Expected outcome: `1. [D][✓] tutorial (by 1/2/2020)`

### `Event` - Creates task that is an event.

Event [description] /at [date] 

Example of usage:  `event party /at 1/2/2020`

Expected outcome: `1. [E][✗] party (at 1/2/2020)`

### `Find` - Returns list of tasks with keyword in name.

Find [keyword] 

Example of usage:  `find party`

Expected outcome: `1. [E][✗] party (at 1/2/2020)`

### `Todo` - Creates task.

Todo [description] 

Example of usage:  `todo finish tutorial`

Expected outcome: `1. [T][✗] finish tutorial`


