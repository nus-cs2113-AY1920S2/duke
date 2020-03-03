# User Guide 


## Features :notebook:
 
- [List all items in your todo list](#list---list-all-items-in-your-todo-list)
- [Add a todo item](#todo---adds-a-general-todo-item)
- [Add an event item](#event---adds-an-event-item-at-a-specified-time)
- [Add a deadline item](#deadline---adds-a-deadline-item-at-a-specified-time)
- [Mark an item as done](#done---marks-an-item-as-done)
- [Delete an item](#delete---deletes-an-item)
- [Find an item](#find---finds-all-matching-tasks)
- [Exit program](#bye---exits-program)
- [Local data storage](#local-data-storage)
 <br/><br/>
 
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
<br/>
 
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
<br/>

### `event` - adds an event item at a specified time
Example of usage: 
`event Watch the news /at 1am`

Expected outcome:
```
____________________________________________________________
 Got it. I've added this task:
   [E][ ] Watch the news (at: 1am)
 Now you have 4 tasks in the list.
 ____________________________________________________________
```
<br/>
 
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
<br/>
 
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
<br/>
 
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
<br/>
 
### `find` - finds all matching tasks
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
<br/>
 
### `bye` - exits program
Example of usage: 
`bye`

Expected outcome:
```
____________________________________________________________
 Bye. Hope to see you again soon!
____________________________________________________________
```
<br/>

### local data storage
On start up, if it exists, Duke loads past list from `[project_root]/data/duke.txt`, or else it creates a new one.

On exit, Duke saves the created list in the same location.
