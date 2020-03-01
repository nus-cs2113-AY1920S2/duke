# User Guide  
   
# Table of Contents
- ### Introduction
- ### Features
  1. Add a Task to your Task List  
    a. Add a To Do task to your Task List  
    b. Add a Deadline task to your Task List  
    c. Add an Event task to your Task List  
  **2. Mark a Task as Done**  
  **3. View all your Tasks**  
  **4. Delete a Task from your Task List**  
  **5. Filter Tasks from your Task List**  
    a. Filter tasks from your Task List by a Specified Keyword  
    b. Filter tasks from your Task List by a Specified Time Period  
- ### Miscellaneous Information
  - Exiting the LumiChat Program
  - Loading and Saving
    - Loading
    - Saving
  - Date Time Formats
    - Date Formats
    - Time Formats
    - Time Specifier Formats

# Introduction
 
This Duke program is a Chat Box program named **LumiChat** that manages a list of user-created tasks.
  
You will be interacting with the application via the command line with a virtual character called **Lumi**. Through the **LumiChat** program, you will be able to perform various operations to manage your tasks like adding, deleting and viewing tasks in a Task List.
  
There are 3 different types of tasks in the **LumiChat** program.

#### <u>Types of tasks</u>
| Task Type    | Description                                                             |  
|:-------------|:------------------------------------------------------------------------|  
| **To Do**    | A task that the you want to do with <u>no</u> *date time* restrictions. |  
| **Deadline** | A task that you want to do <u>by</u> a certain *date time*              |  
| **Event**    | A task that you want to do <u>at</u> a certain *date time*              |  

This should cover most of the common task types in a normal daily life.
  
I hope that you will enjoy the use of the **LumiChat** program. **Have fun!!**

<br><br>

# Features   
## 1. Add a Task to your Task List  
Add a task to your Task List. You may add the [3 different types of tasks](#types-of-tasks) available.
> A **newly-added** task will always be marked as *not done*  
  
### <u>Usage </u>
#### a. `todo` -- Add a To Do task to your Task List  
Adds a **To Do** task to your Task List.  
  
#### Format  
`todo <task description>`  
- `task description` -- The *description* of the **To Do** task

#### Example Usage  
	 todo watch anime
	 
#### Expected Outcome  
	   ʘᗜʘ Alright, Lumi has added: watch anime!  
		     [T][✘] watch anime  
	   You now have 1 task in Lumi's list! 

> **Note**: A **To Do** task is represented by the letter \'**T**\'. 

<br>

#### b. `deadline` -- Add a Deadline task to your Task List  
Adds a **Deadline** task to your Task List.  
The *date time* provided consists of an <u>optional</u> *date* and a *time*. If the *date* is omitted, the **LumiChat** program will automatically assume it to be the **current date**.
  
#### Format  
`deadline <task description> /by <date> <time>`  
- `task description` -- The *description* of the **Deadline** task
- `date` -- The *date* of the deadline
- `time` -- The *time* of the dateline

> **Note**: The `date` and `time` provided must adhere to the set of accepted [Date Time formats](#date-time-formats).

#### Example Usage  
	 deadline complete math assignment /by 12/6 8am
	 
#### Expected Outcome  
	  ʘᗜʘ Alright, Lumi has added: complete math assignment!
		    [D][✘] complete math assignment (by: 12/06/2020 08:00AM)
	  You now have 2 tasks in Lumi's list!

> **Note**: A **Deadline** task is represented by the letter \'**D**\'. 

<br>

#### c. `event` -- Add a Event task to your Task List  
Adds a **Event** task to your Task List.  
The *date time* provided consists of an <u>optional</u> *date* and a *time*. If the *date* is omitted, the **LumiChat** program will automatically assume it to be the **current date**.  
  
#### Format  
`event <task description> /at <date> <time>`  
- `task description` -- The *description* of the **Event** task
- `date` -- The *date* of the event
- `time` - -The *time* of the event

> **Note**: The `date` and `time` provided must adhere to the set of accepted [Date Time formats](#date-time-formats).

#### Example Usage  
	 event meet up with friends for lunch /at tmr 1230
	 
#### Expected Outcome  
	  ʘᗜʘ Alright, Lumi has added: meet up with friends for lunch!
		    [E][✘] meet up with friends for lunch (at: tomorrow 12:30PM)
	  You now have 3 tasks in Lumi's list!

> **Note**: An **Event** task is represented by the letter \'**E**\'. 


<br><br>


## 2. Mark a Task as Done
Marks a **previously undone** task as *done*.

### <u>Usage </u>
#### `done` -- Mark a task as done
Marks a specified task as *done*.
  > **Note**: If you try to mark an already done task as *done*, you will receive a **warning** message from **Lumi**.
  > 
#### Format  
`done <list number>`  
- `list number` -- The *list number* of the task to be marked as done.
  
  > You may find the *list number* of the task by [viewing the list](#3-view-all-your-tasks).

#### Example Usage  
	 done 2
	 
#### Expected Outcome
<u>Task was previously undone</u>

	  ʘᗜʘ Well done! Lumi marks this task as completed!
              [D][✓] complete math assignment (by: 12/06/2020 08:00AM)

<u>Task is already done</u>

	  ಠ~ಠ Hey!! Lumi already marked <complete math assignment> as completed!

<br><br>

## 3. View all your Tasks
View **all** the tasks that you have currently in the Task List.

### <u>Usage </u>
#### `list` -- View all your tasks as a list
Views **all** the tasks that you have currently in the Task List as a list. The total number of tasks will be shown at the bottom of the list.
> **Note**: The list is arranged according to the order in which you have added your tasks. 
  
#### Format  
`list`  

#### Example Usage  
	 list
	 
#### Expected Outcome  
	  ʘᗜʘ Sure! Lumi prints your list!
        +---------+
	+---| L I S T |---------------------------------------------------------------+
	|   +---------+                                                               |
	| 1. [T][✘] watch anime                                                      |
	| 2. [D][✓] complete math assignment (by: 12/06/2020 08:00AM)                |
	| 3. [E][✘] meet up with friends for lunch (at: tomorrow 12:30PM)            |
	+-----------------------------------------------------------------------------+
	| Total: 3 tasks                                                              |
	+-----------------------------------------------------------------------------+

> **Note**:  The numbers to the left of each task is referred to as the task's *list number*.


<br><br>

## 4. Delete a Task from your Task List
Delete a task from your Task List.

### <u>Usage </u>
#### `delete` -- Delete a task from your Task List
Deletes a specified task from your Task List.  To avoid *accidental* deletion of tasks. you will receive an additional prompt to **confirm** your deletion.

#### Format  
`delete <list number>`  
- `list number` -- The *list number* of the task to be deleted
  
  > You may find the *list number* of the task by [viewing the list](#view-all-your-tasks).

#### Example Usage  
	 delete 1
	 
#### Expected Outcome
<u>Delete Confirmation Prompt</u>

	  ʘoʘ? Umm... Lumi needs you to confirm to delete this task:
            [T][✘] watch anime

<u>Confirmed Deletion</u>

	  ʘᗜʘ Bleep, Lumi says bye-bye to:
	        [T][✘] watch anime
	  You now have 2 tasks in Lumi's list!

> **Note**: Enter `yes` or `y` to **confirm** deletion.

<u>Aborted Deletion</u>

	  ʘᗜʘ OK, Lumi continues without deleting!

> **Note**: Enter `no` or `n` to **abort** deletion.

<br><br>

## 5. Filter Tasks from your Task List
Filter tasks from your Task List either by a specified *keyword*, or by a specified *date time period*.

### <u>Usage </u>
#### `a. find` -- Filter tasks from your Task List by a Specified Keyword
Filters tasks from your Task List that contains a specified *keyword*. 
The *keyword* can contain multiple words and need not be complete words. The Task List is then filtered for tasks which *task description* matches the keyword. Filtering is done in a **non-case-sensitive** manner.
**All matched** tasks will be shown as a *Search List*. The total number of tasks will be shown at the bottom of the list.
> **Note**: The list is arranged according to the order in which you have added your tasks. 

#### Format  
`find <keyword>`  
- `keyword` -- The *keyword* to be used to filter the Task List

#### Example Usage  
	 find ASSIGNMENT
	 
#### Expected Outcome
<u>Successful Search</u>

	  ʘᗜʘ Sure! Lumi searches your list...
	    +-----------------------+
	+---| S E A R C H   L I S T |-------------------------------------------------+
	|   +-----------------------+                                                 |
	| 1. [D][✓] complete math assignment (by: 12/06/2020 08:00AM)                |
	| 4. [D][✘] do Computing Assignments (by: 02/03/2020 11:59PM)                |
	| 5. [E][✘] Group project assignment meeting (at: 05/03/2020 02:00PM)        |
	+-----------------------------------------------------------------------------+
	| Search Total: 3 tasks                                                       |
	+-----------------------------------------------------------------------------+

<u>No Search Results</u>

	  ʘᗜʘ Sure! Lumi searches your list...
	  ʘoʘ? Huh? Lumi is not able to find anything...

> **Note**: This may occur due to a mistype in the *keyword*. To confirm that no such tasks with the specified *keyword* exists, you may want to [view all your tasks](#view-all-your-tasks) to check.

<br>

#### `b. due` - Filter tasks from your Task List by a Specified Time Period
Filters tasks from your Task List according to a specified *time period*.  
A *time period* is defined by an <u>optional</u> *time specifier (e.g. on, before, after)* and a *date*. If the *time specifier* is left out, the *time period* is set to be **on** the *date* itself.
The Task List is then filtered for **Deadline** and **Event** tasks which *date time* information matches the specified *time period*.  
**All matched** tasks will be shown as a *Search List*. The total number of tasks will be shown at the bottom of the list.
> **Note**: The list is arranged according to the order in which you have added your tasks. 

#### Format  
`due (<time specifier>) <date>`  
- `time specifier` - The  <u>optional</u> *time specifier* to define the *time period* to filter the Task List
- `date` - The *date* to define the *time period* to filter the Task List

> **Note**: The `time specifier` and `date` provided must adhere to the set of accepted [Date Time formats](#date-time-formats).

#### Example Usage  
<u>With *time specifier*</u>

	 due after tmr

<u>Without *time specifier*</u>

	 due today
	 
#### Expected Outcome
	  ʘᗜʘ Sure! Lumi searches your list...
	    +-----------------------+
	+---| S E A R C H   L I S T |-------------------------------------------------+
	|   +-----------------------+                                                 |
	| 1. [D][✓] complete math assignment (by: 12/06/2020 08:00AM)                |
	| 5. [E][✘] Group project assignment meeting (at: 05/03/2020 02:00PM)        |
	| 6. [E][✘] attend party (at: 12/04/2020 04:00PM)                            |
	+-----------------------------------------------------------------------------+
	| Search Total: 3 tasks                                                       |
	+-----------------------------------------------------------------------------+

<br><br>

# Miscellaneous Information


## Exiting the LumiChat Program
Exiting the **LumiChat** program is simple. Simply enter `bye` to exit.
Upon exiting, the program will [save](#saving) your Task List into a file in your device.

<br>

## Loading and Saving
The **LumiChat** program loads and saves your Task List **automatically**, so there is no explicit way to freely load or save your file.  
### Loading 
Loading is done once you start up the **LumiChat** program. The data from the *task list file* in your device is read to initialise your Task List from when you last saved.  
   
If the *task list file* is corrupted, an option will be given to **continue** the program with a new **empty** Task List, or to **abort** the program.

### Saving
Saving is only done upon [exiting](#exiting-the-lumichat-program) the **LumiChat** program. The current tasks in your Task List will be saved into a *task list file* in your device.  
  
Should there be an *unfortunate* error in saving your Task List, an option will be given to **continue** to exit the program **without** saving your Task List, or to **stay** in the program *(when you may then try to manually salvage the tasks in your Task List)*.

<br>

## Date Time Formats
Any *date time* data that you provide has to adhere to certain formats pre-defined by the **LumiChat** program. Failure to do so will likely result in the program to be unable to recognise your input command, and a warning will be shown.  
Instances when you may need to enter a *date time* will be when [adding a **Deadline**](#b-deadline---add-a-deadline-task-to-your-task-list) or [**Event**](#c-event---add-a-event-task-to-your-task-list) task, or [filtering the Task List by a *time period*](#b-due---filter-tasks-from-your-task-list-by-a-specified-time-period).
  
Here are the following *date time* formats.
  
### Date Formats
There are **two** types *date* formats allowed.

#### 1. **Words**
You may enter **only** the following *date* words.
- `today` or `tdy` -- represents the <u>current</u> date
- `tomorrow` or `tmr` -- represents the <u>next</u> date
- `yesterday` or `yst` -- represents the <u>previous</u> date
> **Note**: All dates are taken with reference to the current date on your device.
  
#### 2. Standard Date Format
This refers to the typical dates that are represented with numbers and delimiter symbols.  
  
In this **LumiChat**  program, *dates* should be in the order of **day**, **month**, then an <u>optional</u> **year**. If the **year** is not provided, the program will automatically assume it to be the **current year**.  Also, the **day**, **month** and **year** should only be entered as **numbers** and not words *(e.g. January is not accepted for the **month** attribute)*.
  
Regarding delimiters, the program will **only** consider '/' and '-' as valid delimiters for *dates*.  Delimiters are <u>optional</u> and may be omitted provided you include the **year** of the *date* *(e.g. 1/1/20, 1/1 and 010120 are accepted, but not 0101).*

An **exhaustive** list of the standard *date* formats is given below for your reference.
```
	dd/MM/yyyy, d/MM/yyyy, dd/M/yyyy, d/M/yyyy, 
	dd/MM/yy, d/MM/yy, dd/M/yy, d/M/yy,
	dd/MM, d/MM, dd/M, d/M,
	dd-MM-yyyy, d-MM-yyyy, dd-M-yyyy, d-M-yyyy, 
	dd-MM-yy, d-MM-yy, dd-M-yy, d-M-yy,
	dd-MM, d-MM, dd-M, d-M,
	ddMMyyyy, ddMMyy
```

### Time Formats
The **LumiChat** program accepts most time typical time formats that are represented with numbers, delimiter symbols and <u>optional</u> am-pm markers.  
  
The *time* should be in the order of  **hour**, then **minute**. The **seconds** attribute of *time* should **not** be given. The **minute** attribute must be a **double** digit *(i.e. single digits must be padded with a 0 in front)*. The **minute** attribute is also <u>optional</u>, and should it be omitted, the **LumiChat** program will automatically set the **minute** to be 0. Both the **12-h** format and the **24-h** format are valid *time* formats for this program.  
  
Regarding delimiters, the program will **only** consider ':' and '.' as valid delimiters for *time*.  Delimiters are <u>optional</u> and may be omitted.

Lastly, the am-pm marker is an <u>optional</u> attribute, and should it be omitted, the **LumiChat** program will automatically assume the *time* to follow the **24-h** format.

An **exhaustive** list of the *time* formats is given below for your reference.
```
	h:mma, H:mma, H:mm,
	h.mma, H.mma, H.mm,
	hmma, Hmma, Hmm,
	ha, Ha, H
```

### Time Specifier Formats
The *time specifier* is used in conjunction with a *date* to define the *time period* to [filter tasks in the Task List](#b-due---filter-tasks-from-your-task-list-by-a-specified-time-period). The following words are considered valid *time specifiers* in the **LumiChat** program.
- `on` -- <u>on</u> the specified *date*
- `after` or `a` -- <u>after</u> the specified *date*
- `before` or `b` -- <u>before</u> the specified *date*

The *time specifier* is <u>optional</u> and if omitted, is set to `on`.
