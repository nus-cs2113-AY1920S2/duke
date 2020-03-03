# User Guide
## Welcome to Shannon's Duke
**Is this your first time here?**

Shannon's Duke is Shannon's implementation of Duke and it is an online to-do-list tracker.

In your to-do-list, you can categorise your tasks into these 3 types of tasks into your list:
* Todo `[T]` : These are tasks that are to be done, no deadlines, just at your own pace and at your own comfort. No pressure, just do it!
* Deadline `[D]` : These are tasks that have a deadline and must be done before a certain deadline.
* Event `[E]` : These are events that you need to be at and not just tasks to achieve.

These tasks added into the list can also be marked as done when completed. They are all automatically unmarked when first added into the list
* Unmarked Todo Task - `[T][0] do laundry`
* Marked Event Task - `[E][1] attend seminar (at: school hall)`

Here are some instructions and introductions to familiarise yourself with using this program.

## Instructions
**NOTE:** All commands are case-sensitive, but task descriptions are not case-sensitive.
#### Adding Tasks
##### Adding a Todo task: `todo`
Adds a Todo task into your list

**Format:** `todo <DESCRIPTION>`

*Examples:*
* `todo buy lunch`
* `todo finish homework`

##### Adding a Deadline Task: `deadline`
Adds a Deadline task into your list.

**Format:** `deadline <DESCRIPTION> /by <DEADLINE OF TASK>`

*Examples:*
* `deadline submit proposal /by Wednesday 3PM`
* `deadline make reservations at restaurant /by tonight 7PM`

##### Adding an Event Task: `event`
Adds an Event task into your list

**Format:** `event <DESCRIPTION> /at <VENUE/DATE/TIME OF TASK>`

*Examples:*
* `event Secondary School Reunion /at Concorde Hotel, 14th Mar, 7PM`
* `event Mom's Birthday Celebrations /at Home, 28th Sept, 6PM`

#### Removing Tasks: `remove`
Removes task at the stated index

**Format:** `remove <INDEX>`

*Example:*
* `remove 2` - Removes Task at index 2 of the list

#### Marking Tasks as Done: `done`
Marks task at the stated index as done

**Format:** `done <INDEX>`

*Example:*
* `done 6` - Marks task 6 in list as done.

Returns the task to be `[1]` rather than `[0]`

#### Listing All Tasks: `list`
Shows all the tasks inside the list, indexed from the tasks that are added first to the the latest entry.

**Format:** `list`

#### Finding Tasks: `find`
Finds the tasks with containing the keywords
The command is not case-sensitive.

**Format:** `find <KEYWORD>`

*Example:*
* `find work` - finds tasks containing the keyword 'work'

Returns `homework` and `Work from Home` (assuming these are tasks inside the list)

#### Getting Commands for List: `help`
Returns a list of all the commands and a description of their usage

**Format:** `help
`
#### Exiting the Program: `bye`
**Format:** `bye`

#### Saving the Data
Task list data are saved in the hard disk automatically after the program is exited.

There is no need to save manually.