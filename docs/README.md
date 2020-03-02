# User Guide
Nyan chat bot allows users to take down their notes.

# Table of Contents

- [Requirements](https://github.com/NyanWunPaing/duke/tree/master/docs#requirements)
- [Features](https://github.com/NyanWunPaing/duke/tree/master/docs#features)
    
    - [Add a new task](https://github.com/NyanWunPaing/duke/tree/master/docs#add-a-new-task)
    - [Find your tasks](https://github.com/NyanWunPaing/duke/tree/master/docs#find-your-tasks)
    - [Remove a task in the list](https://github.com/NyanWunPaing/duke/tree/master/docs#remove-a-task-in-the-list)
    - [List all tasks](https://github.com/NyanWunPaing/duke/tree/master/docs#list-all-tasks)
    - [Mark tasks as done](https://github.com/NyanWunPaing/duke/tree/master/docs#marks-tasks-as-done)
    - [Exit](https://github.com/NyanWunPaing/duke/tree/master/docs#exit)

# Requirements 
Java 11 or later.

# Features

### Add a new task 

Add a new task into task list.

##### Usage example:

`todo [description]`

`deadline [description] /by [by details]`

`event [description] /at [at details]`

###### Example: 
 
 - `todo read book`
   
   Adds a new todo task with description `read book` to the task list. 
  
 - `event lecture /at school`
 
   Adds a new event task with description `lecture`. 
   
   The location of the event is at `school`.
   
  - `deadline assignment /by tomorrow`
   
     Adds a deadline with description `assignment`. 
     
     The due date of the deadline is by `tomorrow`.
   
 ***Expected outcome:***

    ===================================================
    Got it! I've added this task:
       [T][✘] read book
    Now you have 4 task(s) in the list.
    ===================================================
                     
### Find your tasks
Find tasks that contain the relevant keyword.

##### Usage example:

`find [keyword]`

###### Example: 

- `find book`

Used the keyword `book` to lookup for relevant tasks in the task list.

***Expected outcome:***
  
    ===================================================
    Here are the matching tasks in your list:
       1.[T][✘] read book
       2.[D][✘] return book (by: sunday)
    ===================================================

### Remove a task in the list

Remove a certain task from the task list.

##### Usage example:
`delete [index of the task]`

###### Example: 

- `delete 4`

Delete the `4`th task in the task list. 
   
 ***Expected outcome:***
    
    ===================================================
    Noted. I've removed this task:
      [D][✘] return book (by: sunday)
    Now you have 4 task(s) in the list.
    ===================================================

### List all tasks
Lists all the tasks in task list.

##### Usage example:
`list`

###### Example: 

- `list`
   
 ***Expected outcome:***
 
    ===================================================
    Here are the tasks in your list:
    1.[T][✘] eat sleep
    2.[T][✘] sleep play
    3.[E][✘] play (at: home)
    4.[T][✘] read book
    ===================================================

### Marks tasks as done
Marks a particular task as done in the list of tasks.

##### Usage example:
`done [index of the task]`

###### Example: 

- `done 4`

Mark the `4`th task in the task list as completed.

 ***Expected outcome:***

    ===================================================
    Nice! I've marked this task as done:
      [T][✓] read book
    ===================================================
    

### Exit
Exit Nyan Chatbot and save task list.

##### Usage example:
`bye`

###### Example: 

- `bye`

Exit and print bye text.

 ***Expected outcome:***

    ===================================================
    Bye. Hope to see you soon!
    ===================================================

