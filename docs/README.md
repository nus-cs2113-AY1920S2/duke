#Duke User Guide

##Content Page

1. **Introduction**

2. **Features**

    2.1. Add Tasks
    
     - 2.1.1 Add Todo Task
        
     -  2.1.2 Add Deadline Task
        
     -  2.1.3 Add Event Task
        
    2.2. Mark Tasks As Done 
    
    2.3. Delete Tasks
    
    2.4. List Tasks
    
    2.5. Find Tasks
    
    2.6. Save and Load
    
3. **Command Summary**

## 1. Introduction:

Duke is a software to help people manage their daily tasks. 

And it is optimized for users who want to type fast with CLI.
 
## 2. Features:

###Command Format

Words in `UPPER_CASE` are the parameters to be supplied by the user.

For example:

in ​```todo TASK_NAME```, TASK_NAME is the parameter of a specific todo task’s name which can be used as `todo buy a book`.

###2.1 `Add` tasks: 

#### 2.1.1 Add `Todo` task

Add todo task into task list

Format:​ ```todo TASK_NAME```

#### 2.1.2 Add `Deadline` task

Add deadline task into task list

Format:​ ```deadline TASK_NAME /by TIME```

Noted: If the users input a time in the format of `yyyy-mm-dd`, Duke can recognize the input time string as a real time.

#### 2.1.3 Add `Event` task

Add event task into task list

Format:​ ```event TASK_NAME /by TIME```

Noted: If the users input a time in the format of `yyyy-mm-dd`, Duke can recognize the input time string as a real time.

###2.2 Mark Tasks As `Done`

Users can mark a task as done with the index of a task when they finish it.\

Format: ​`done TASK_INDEX`

###2.3 `Delete` Tasks

Users can delete a task as done with the index of a task when they finish it.\

Format: ​`delete TASK_INDEX`

###2.4 `List` Tasks

Users can list all the tasks in the task list when they need to look through it.\

Format: ​`list`

###2.5 `Find` Tasks

Users can find all the tasks which contains a certain keyword.\

Format: ​`find KEYWORD`

###2.6 Save and load

This software can save and load the recorded data automatically.

##3. Command Summary:

### `Todo`

Command:

    todo TASK_NAME

Example:​ 

    todo buy a book
    
Expected outcome:
```
    ____________________________________________________________
	Got it. I've added this task:
	[T] [✘] buy a book
	Now you have 1 task(s) in the list
    ____________________________________________________________

Please enter your command or enter "bye" to exit:
```

###`Deadline`

Command: 

    deadline TASK_NAME /by TIME 

Example:​ 

    deadline finish iP /by next weekend
    
Expected outcome:

```
    ____________________________________________________________
	Got it. I've added this task:
	[D] [✘] finish iP (by: next weekend)
	Now you have 2 task(s) in the list
    ____________________________________________________________

Please enter your command or enter "bye" to exit:
```
    
Example:​ 

    deadline finish iP /by 2020-03-02

Expected outcome:

```
    ____________________________________________________________
	Got it. I've added this task:
	[D] [✘] finish iP (by: Mar 2 2020)
	Now you have 3 task(s) in the list
    ____________________________________________________________

Please enter your command or enter "bye" to exit:
```

###`Event`

Command:

    event TASK_NAME /at TIME 

Example:​ 

    event watch a moive /at tomorrow
    
Expected outcome:
```
    ____________________________________________________________
	Got it. I've added this task:
	[E] [✘] watch a moive (at: tomorrow)
	Now you have 4 task(s) in the list
    ____________________________________________________________

Please enter your command or enter "bye" to exit:
```

Example:​ 

    event watch two moives /at 2020-02-21

Expected outcome:

```
    ____________________________________________________________
	Got it. I've added this task:
	[E] [✘] watch two moives (at: Feb 21 2020)
	Now you have 5 task(s) in the list
    ____________________________________________________________

Please enter your command or enter "bye" to exit:
```

###`Done`

Command: 

    done TASK_INDEX

Example:​ 

    done 2

Expected outcome:

```
    ____________________________________________________________
	Got it. I've marked this task:
	[D] [✓] finish iP (by: next weekend)
    ____________________________________________________________

Please enter your command or enter "bye" to exit:
```

###`Delete`

Command: 

    delete TASK_INDEX

Example:​ 

    delete 2

Expected outcome:

```
    ____________________________________________________________
	Got it. I've remove this task:
	[D] [✓] finish iP (by: next weekend)
	Now you have 4 task(s) in the list
    ____________________________________________________________

Please enter your command or enter "bye" to exit:
```

###`List`

Command: 

    list

Expected outcome:

```
    ____________________________________________________________
	Here are the matching tasks in your list:
	1.[T] [✘] buy a book
	2.[D] [✘] finish iP (by: Mar 2 2020)
	3.[E] [✘] watch a moive (at: tomorrow)
	4.[E] [✘] watch two moives (at: Feb 21 2020)
    ____________________________________________________________

Please enter your command or enter "bye" to exit:
```
    
###`Find`

Command: 

    find KEYWORD

Example:​ 

    find iP
    
Expected outcome:

```
    ____________________________________________________________
		Here are the matching tasks in your list:
	1.[D] [✘] finish iP (by: Mar 2 2020)
    ____________________________________________________________

Please enter your command or enter "bye" to exit:
```