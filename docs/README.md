# User Guide 


## Features :notebook:
 
- [List all items in your todo list](#list---list-all-items-in-your-todo-list)
- [Adds a todo item](#todo---adds-a-general-todo-item)
- [Adds an event item](#event---adds-an-event-item-at-a-specified-time)
- [Adds a deadline item](#deadline---adds-a-deadline-item-at-a-specified-time)
- [Marks an item as done](#done---marks-an-item-as-done)
- [Deletes an item](#delete---deletes-an-item)
- [Find an item](#find---find-all-matching-tasks)
- [Exits program](#bye---exits-program)
- [Local data storage](#Local-data-storage)
 
 
## Usage :memo:	
 
### `list` - list all items in your todo list

Example of usage: 
`list`

Expected outcome:
```
____________________________________________________________
 Here are the tasks in your list:
 1. [T][1] Wash the dishes
 2. [E][ ] Attend interview for the news (at: 1 March 1pm)
____________________________________________________________

```
 
### `todo` - adds a general todo item
Example of usage: 
`todo Practice coding`

Expected outcome:
```
____________________________________________________________
 Got it. I've added this task: 
   [T][ ] Practice coding
 Now you have 3 tasks in the list.
____________________________________________________________
```
 
### `event` - adds an event item at a specified time
Example of usage: 
`event Watch the News /at 1am`

Expected outcome:
```
____________________________________________________________
 Got it. I've added this task:
   [E][ ] Watch the news (at: 1am)
 Now you have 4 tasks in the list.
 ____________________________________________________________
```
 
### `deadline` - adds a deadline item at a specified time
Example of usage: 
`deadline Submit 1st draft for Joker 2 /by: 1st March 5pm`

Expected outcome:
```
____________________________________________________________
 Got it. I've added this task:
   [D][ ] Submit 1st draft for Joker 2 (by: 1st March 5pm)
 Now you have 5 tasks in the list.
____________________________________________________________
```
 
### `done` - marks an item as done
Example of usage: 
`done 3`

Expected outcome:
```
____________________________________________________________
 Nice! I've marked this task as done:
   [T][1] Practice coding
____________________________________________________________
```
 
### `delete` - deletes an item
Example of usage: 
`delete 3`

Expected outcome:
```
____________________________________________________________
 Noted. I've removed this task: 
   [T][1] Practice coding
 Now you have 4 tasks in the list.
____________________________________________________________
```
 
### `find` - find all matching tasks
Example of usage: 
`find news`

Expected outcome:
```
____________________________________________________________
 Here are the matching tasks in your list:
 2.[E][ ] Attend interview for the news (at: 1 March 1pm)
 3.[E][ ] Watch the news (at: 1am)
____________________________________________________________
```
 
### `bye` - exits program
Example of usage: 
`bye`

Expected outcome:
```
____________________________________________________________
 Bye. Hope to see you again soon!
____________________________________________________________
```
 
### Local data storage
On start up, if it exists, Duke loads past list from `\[project_root]/data/duke.txt`, or else it creates one.
On exit, Duke saves the created list in the same location.
