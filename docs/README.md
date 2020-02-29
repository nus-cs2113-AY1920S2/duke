# DUKE User Guide
DUKE is a chat bot that helps users manage their tasks.

## Table of Contents
- [Quick Start](https://github.com/sitinadiah25/duke/tree/master/docs#quick-start)
- [Features](https://github.com/sitinadiah25/duke/tree/master/docs#features)
    
    - [Add a new task](https://github.com/sitinadiah25/duke/tree/master/docs#add-a-new-task)
    - [Find tasks](https://github.com/sitinadiah25/duke/tree/master/docs#find-task)
    - [Delete task](https://github.com/sitinadiah25/duke/tree/master/docs#delete-task)
    - [List tasks](https://github.com/sitinadiah25/duke/tree/master/docs#list-tasks)
    - [Mark task as done](https://github.com/sitinadiah25/duke/tree/master/docs#mark-task-as-done)
    - [Exit program](https://github.com/sitinadiah25/duke/tree/master/docs#exit-program)

- [Feedback](https://github.com/sitinadiah25/duke/tree/master/docs#feedback) 

## Quick Start 

1. Ensure that you have `Java 11` or later installed in your computer 

2. Download `DUKE v0.2` [here](https://github.com/sitinadiah25/duke/releases/tag/A-Release) 

3. Move `duke.jar` onto your Desktop

4. Navigate to your Desktop folder and open Terminal or Command Prompt 

5. Enter `java -jar duke.jar` to start up the program

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

### Exit program

Exit the program 

##### Usage example:

`bye`

> ***Expected outcome:***
>
>     ____________________________________________________________
>     Bye. Hope to see you again soon!
>     ____________________________________________________________

## Feedback

If you have any feedback or bugs to report, please email me at e0318697@u.nus.edu


