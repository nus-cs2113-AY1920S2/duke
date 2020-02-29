# DUKE User Guide
DUKE is a chat bot that helps users manage their tasks.

## Table of Contents
- [Requirements](https://github.com/sitinadiah25/duke/tree/master/docs#requirements)
- [Features](https://github.com/sitinadiah25/duke/tree/master/docs#features)
    
    - [Add a new task](https://github.com/sitinadiah25/duke/tree/master/docs#add-a-new-task)
    - [Find tasks](https://github.com/sitinadiah25/duke/tree/master/docs#find-tasks)
    - [Delete task](https://github.com/sitinadiah25/duke/tree/master/docs#deletes-task)
    - [List tasks](https://github.com/sitinadiah25/duke/tree/master/docs#list-tasks)
    - [Mark tasks as done](https://github.com/sitinadiah25/duke/tree/master/docs#mark-tasks-as-done)
- [Feedback](https://github.com/sitinadiah25/duke/tree/master/docs#feedback) 
## Requirements 
Java 11 or later.

## Features 
### Add a new task
Add a new task for DUKE to store 
##### Usage example:

`todo [description]`

`deadline [description] /by [by deadline]`

`event [description] /at [at place/time]`

###### Example: 
 
 - `todo CS2113T iP`
   
   Adds a new todo task with description `CS2113T iP` into the task list. 
  
 - `event meeting /at school`
 
   Adds a new event task with description `meeting` with the location of the event as `school`.
   
  - `deadline CS2113T iP final /by 2 March`
   
     Adds a deadline with description `CS2113T iP final`. 
     
     The due date of the deadline is by `2 March`.

> ***Expected outcome:***
>
>     ____________________________________________________________
>     Got it! I've added this task:
>        [T][✘] CS2113T iP
>     Now you have 1 task(s) in the list.
>     ____________________________________________________________

### Find task
Find tasks in the task list according to the keyword given
##### Usage example:

`find [work]`

###### Example: 
 
 - `find CS2113T iP`
   
   Finds any task that contains `CS2113T iP` in its description. 
  
     
> ***Expected outcome:***
>
>     ____________________________________________________________
>     Here are the matching tasks in your list: 
>     1.[T][✘] CS2113T iP
>     ____________________________________________________________

### Delete task
Delete a task according to the task number given
##### Usage example:

`delete [task number]`

###### Example: 
 
 - `delete 3`
   
   Deletes the task with task number `3`. 
  
     
> ***Expected outcome:***
>
>     ____________________________________________________________
>     Noted. I've removed this task:  
>        [T][✘] work
>     Now you have 3 task(s) in the list.
>     ____________________________________________________________

### List tasks
List all the tasks that are in the task list 
##### Usage example:

`list`
  
> ***Expected outcome:***
>
>     ____________________________________________________________
>     Here are the tasks in your list:  
>     1. [T][✘] work
>     2. [T][✘] CS2113T iP
>     3. [E][✘] sleep (at: 12am)
>     ____________________________________________________________

### Mark task as done
Mark a task as done according to the task number given
##### Usage example:

`done [task number]`

###### Example: 
 
 - `done 3`
   
   Deletes the task with task number `3`. 
  
     
> ***Expected outcome:***
>
>     ____________________________________________________________
>     Nice! I've marked this task as done: 
>        [E][✓] sleep (at: 12am)
>     ____________________________________________________________

## Feedback

If you have any feedback or bugs to report, please email me at e0318697@u.nus.edu


