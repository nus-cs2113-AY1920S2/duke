# User Guide

## Using Duke
Create a .txt file named *duke.txt* in the same path as *duke.jar* folder.    
This should be done **before** running duke.


## Features 

### Feature 1 
`todo`  - Add todo task
 
*Example of usage:*
> **todo** Buy eggs  
 
*Expected outcome:*
>Got it! I've added this task  
> [T][x] Buy eggs   
>Now you have 1 task left in your list.

### Feature 2
`deadline`  - Add deadline task
 
*Example of usage:*
> **deadline** Project **/by**  2020-09-06 2359
 
*Expected outcome:*
>Got it! I've added this task  
> [D][x] Project (by: Sep 6 2020 2359)   
>Now you have 2 tasks left in your list.

### Feature 3
`event`  - Add event
 
*Example of usage:*
> **event** Seminar **/at**  2020-04-03 1230
 
*Expected outcome:*
>Got it! I've added this task  
> [E][x] Seminar (at: Apr 3 2020 1230)   
>Now you have 3 tasks left in your list.

### Feature 4
`list`  - List all the tasks 
 
*Example of usage:*
> **list**
 
*Expected outcome:*
>Here are the tasks in your list  
> 1. [T][x] Buy eggs   
> 2. [D][x] Project (by: Sep 6 2020 2359) 
> 3. [E][x] Seminar (at: Apr 3 2020 1230)

### Feature 5
`done`  - Mark task as done
 
*Example of usage:*
> **done** 3 (index of task in the list)
 
*Expected outcome:*
>Nice! I've marked this task as done:   
> [E][✓] Seminar (at: Apr 3 2020 1230)

### Feature 6
`delete`  - Delete task from the list 
 
*Example of usage:*
> **delete** 3 (index of task in the list)
 
*Expected outcome:*  
>Noted. I've removed this task:   
> [E][✓] Seminar (at: Apr 3 2020 1230)


### Feature 7
`find`  - Find a task by its keyword
 
*Example of usage:*
> **find** buy
 
*Expected outcome:*  
>  1. [T][x] Buy eggs  


### Feature 8
`bye`  - Exit duke and automatically save tasks in a text file
 
*Example of usage:*
> **bye**
 
*Expected outcome:*  
> Bye. Hope to see you again!   

### Feature 9
Open the text file to see the saved tasks in the following  
format.
 
*Expected outcome:*  
> T | 0 | Buy eggs  
> D | 0 | Project Sep 6 2020 2359


## Usage

### `Keyword` - Describe action

Describe action and its outcome.

Example of usage: 

>keyword (optional arguments)

Expected outcome:

>outcome
