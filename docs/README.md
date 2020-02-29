# User Guide

## Task Types
* `Todo`
* `Deadline`
* `Event`

## Features 
1. Adding a `Task`
2. Listing all `Task`
3. Marking a `Task` as done
4. Finding related `Task` by keywords
5. Deleting a `Task`
6. Exiting the program

### Feature 1 - Adding a `Task`
Adds a `Task` to the list of `Task` kept by Duke. 

#### Format
> `<Task type> <Task description> </seperator> <Date if applicable>`

#### Usage
####1.1 Adding a `Todo`
Adds a `Todo` to the list of `Task`

Example of usage:

> `todo do IP progress`

Expected outcome:

> `Added the task:`\
    &nbsp;&nbsp;&nbsp;&nbsp;`[T][✘] do IP progress`\
    `Now you have 1 tasks in the list!`

####1.2 Adding a `Deadline`
Adds a `Deadline` to the list of `Task`

Example of usage:
> `deadline do IP progress /monday`

Expected outcome:
> `Added the task:`\
   &nbsp;&nbsp;&nbsp;&nbsp;`[D][✘] do IP progress(by: monday)`\
   `Now you have 2 tasks in the list!`

####1.3 Adding an `Event`
Adds an Event to the list of `Task` 
 
Example of usage:
> `event do IP progress /monday`

Expected outcome
> `Added the task:`\
   &nbsp;&nbsp;&nbsp;&nbsp;`[E][✘] do IP progress(by: monday)`\
   `Now you have 3 tasks in the list!`

### Feature 2 - Listing all `Task`
Lists all the `Task` currently in the list of `Task`\

#### Usage
Example of usage:
> `list`

Expected outcome:
>`Here are the tasks in your list:`\
  `1. [T][✘] do IP progress`\
  `2. [D][✘] do IP progress(by: monday)`\
  `3. [E][✘] do IP progress(by: monday)`

### Feature 3 - Marking `Task` as done
Marks the `task` in the `task` list corresponding to the `index` supplied as done

####Format 
> `done <index of task to be marked done>`

####Usage
Example of usage:
>`done 1`

Expected outcome:
>`[1. [T][✓] do IP progress] is marked done!`

### Feature 4 - Finding `Task` by keywords
Finds all related `task` using the keywords provided by user

####Format
>`find <keyword>`

#### Usage
Example of usage: 
>`find monday`

Expected outcome:
> `Here are the matching tasks in your list:`\
   `1. [D][✘] do IP progress(by: monday)`\
   `2. [E][✘] do IP progress(by: monday)`

### Feature 5 - Deleting a `Task`
Deletes the `task` in the `task` list corresponding to the `index` supplied

####Format
>`delete <index of task to be deleted>`

####Usage
Example of usage:
>`delete 2>

Expected outcome:
>`Removed the task:`\
  &nbsp;&nbsp;&nbsp;&nbsp;`[D][✘] do IP progress(by: monday)`\
  `Now you have 2 tasks in the list!`

###Feature 6 - Exiting the program
Exits the program

####Usage
Example of usage:
>`bye`

Expected outcome:
>`Exiting DUKE`\
>`____        _         `         
 `|  _ \ _   _| | _____ `   
 `| | | | | | | |/ / _ \`\
 `| |_| | |_| |   <  __/`\
 `|____/ \__,_|_|\_\___|`