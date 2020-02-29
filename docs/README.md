# User Guide 

## Features :notebook:

### Feature 1 - list  
List every task that is currently in your todo list.

### Feature 2 - todo  
Adds an item into your todo list.

### Feature 3 - event 
Adds an event into your todo list at a specified time.

### Feature 4 - deadline
Adds an item with a deadline into your todo list with its specified time.

### Feature 5 - done
Marks an item as completed.

### Feature 6 - delete
Deletes an item from your todo list.

### Feature 7 - bye
Quit the application, storing all items in your todo list onto your hard drive.


## Usage :memo:	

### `list` - display all items currently in your todo list
Example of usage: 
`list`
Expected outcome:
```
____________________________________________________________
 Here are the tasks in your list:
____________________________________________________________

```

### `todo` - adds a general item
Example of usage: 
`todo Practice coding`
Expected outcome:
```
____________________________________________________________
 Got it. I've added this task: 
   [T][✗] Practice coding
 Now you have 1 tasks in the list.
____________________________________________________________
```

### `event` - adds an event at a specified time
Example of usage: 
`event Watch the News /at 1am`
Expected outcome:
```
____________________________________________________________
 Got it. I've added this task:
   [E][✗] Watch the News (at: 1am)
 Now you have 2 tasks in the list.
 ____________________________________________________________
```

### `deadline` - adds an item with a deadline
Example of usage: 
`deadline Submit 1st draft for Joker 2 /by: 1st March 5pm`
Expected outcome:
```
____________________________________________________________
 Got it. I've added this task:
   [D][✗] Submit 1st draft for Joker 2 (by: 1st March 5pm)
 Now you have 3 tasks in the list.
____________________________________________________________
```

### `done` - marks an item as completed
Example of usage: 
`done 1`
Expected outcome:
```
____________________________________________________________
 Nice! I've marked this task as done:
   [T][✓] Practice coding
____________________________________________________________
```

### `delete` - deletes an item
Example of usage: 
`delete 1`
Expected outcome:
```
____________________________________________________________
 Noted. I've removed this task: 
   [T][✓] Practice coding
 Now you have 2 tasks in the list.
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
