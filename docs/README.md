# User Guide
Kesin chat bot allows users to easily organise their tasks. 


## Table of Contents


- [Quick Start](https://github.com/itskesin/duke/tree/master/docs#quick-start)
- [Features](https://github.com/itskesin/duke/tree/master/docs#features)
    
    - [Add a new task](https://github.com/itskesin/duke/tree/master/docs#add-a-new-task)
    - [Find your tasks](https://github.com/itskesin/duke/tree/master/docs#find-your-tasks)
    - [Deletes a task in the list](https://github.com/itskesin/duke/tree/master/docs#deletes-a-task-in-the-list)
    - [View all tasks](https://github.com/itskesin/duke/tree/master/docs#view-all-tasks)
    - [Marks tasks as done](https://github.com/itskesin/duke/tree/master/docs#marks-tasks-as-done)
    - [Save and Exit](https://github.com/itskesin/duke/tree/master/docs#save-and-exit)

## Quick Start 

  1. Ensure `Java 11` or later is installed on the computer
    
  2. Download [DUKE v0.2](https://github.com/itskesin/duke/releases)
    
  3. Open the `JAR file` downloaded on the computer
    
  4. Enter `java -jar IP.jar` to start up the program

## Features

### Add a new task 

Add a new task into the list of tasks that are stored by Kesin chat bot.

##### Usage example:

`todo [description]`

`deadline [description] /by [by description]`

`event [description] /at [at description]`

###### Example: 
 
 - `todo grocery shopping`
   
   Adds a new todo task with description `grocery shopping` to the task list. 
  
 - `event project meeting /at school`
 
   Adds a new event task with description `project meeting`. 
   
   The location of the event is at `school`.
   
  - `deadline CS2102 assignment /by friday`
   
     Adds a deadline with description `CS2102 assignment`. 
     
     The due date of the deadline is by `friday`.
   
> ***Expected outcome:***
>
>     Got it. I've added this task:
>                               
>     [T][✘] grocery shopping
>
>     2 tasks in the list (╥_╥)
                     
### Find your tasks
Kesin will follow your command and help you to find tasks that contain the relevant keyword.

##### Usage example:

`find [keyword]`

###### Example: 

- `find project`

Used the keyword `project` to lookup for relevant tasks in the task list.

> ***Expected outcome:***
>     
>      Here are the matching tasks in your list:
>       1. [D][✘] CS2105 project (by: Thursday)
>       2. [T][✓] CS2102 project
>       3. [T][✘] CS2113T project
>       4. [E][✘] project meeting (at: Friday 2-4pm)


### Deletes a task in the list

Kesin will removes a certain task from the list of tasks.

##### Usage example:
`delete [index of the task]`

###### Example: 

- `delete 4`

Delete the `4`th task in the task list. 
   
> ***Expected outcome:***
>
>     Noted. I've removed this task: (＾▽＾)
>                                
>     [T][✓] borrow book
>   
>     Now you have 8 tasks in the list.
>
>

### View all tasks
Lists all the tasks that are currently stored by the Kesin chat bot.

##### Usage example:
`list`

###### Example: 

- `list`
   
> ***Expected outcome:***
> 
>     Here are the tasks in your list:
>                 
>     1. [T][✓] return book
>     2. [D][✓] CS2102 project (by: Monday)
>     3. [T][✘] study
>     4. [D][✘] CS2105 project (by: Thursday)

### Marks tasks as done
Marks a particular task as done in the list of tasks.

##### Usage example:
`done [index of the task]`

###### Example: 

- `done 3`

Mark the `3`rd task in the task list as completed.

> ***Expected outcome:***
> 
>     Nice! I've marked this task as done:
>     [D][✓] return book (by: Monday)

### Save and Exit
Save the user input and quit the program.

##### Example:
`bye`

> ***Expected outcome:***
> 
>     Bye, [user name]. Hope to see you again soon!
