# User Guide for Application SHEENA

## Features 

### 1. Add a todo task

Users are able to add one specific task every time they type "todo" command
A capital letter "T" will shown when you successfully added the task.
A tick or cross will shown besides the capital letter "E" to indicate whether the task is done by the user.

"todo" - to indicate the system that this is a todo task
"description" - describe the task 


Format: todo description

Eg: todo Love Sheena.

IMPORTANT: User must provide a description.

Outcome:
[T].[tick / cross] todo Love Sheena 


### 2. Add an Event task

Users are able to add one specific event task every time they type "event" command
We will use /at to differentiate the time  
A capital letter "E" will shown when you successfully added the event.
A tick or cross will shown besides the capital letter "E" to indicate whether the event is done by the user.


"event" - to indicate the system that this is a todo task
"/at" - to indicate the system that the time is behind the /at 
"description" - describe the event
"time" - describe the details for time (at: Mon 2-4pm)

Format: event (description) /at (time)

Eg: event Love Coco /at Mon 2-4pm

IMPORTANT: User must provide a description and time.

Outcome:
[E][tick / cross] event Love Coco (at: Mon 2-4pm)



### 3. Add a Deadline task

Users are able to add one specific deadline task every time they type "event" command
We will use /by to differentiate the day  
A capital letter "D" will shown when you successfully added the deadline.
A tick or cross will shown besides the capital letter "E" to indicate whether the task is done by the user.


"event" - to indicate the system that this is a todo task
"/by" - to indicate the system that the day is behind the /by 
"description" - describe the event
"day" - describe the details for day (Eg: Sunday)

Format: event (description) /by (day)

Eg: deadline I love you Sheena /by Sunday

IMPORTANT: User must provide a description and day.

Outcome:
[D][tick / cross] event I love you Sheena (by: Sunday)


### 4. List Down the task List

Users are able to retrieve the task that they had saved.  

"list" - to list down the task.

Format: list

Eg: list

Outcome:

Sheena: Yay! Now you have these ~
----------------------------
Sheena: Well, you have these items in your list: 

 1. [T][✓]buy food
 2. [T][✓]miss Sheena
 3. [T][✘]love Sheena forever
 4. [T][✘]I love you Coco
----------------------------
Sheena: Yay! 4 tasks listed!


### 5. Delete A Task.

Users are able to delete one specific event task every time they type "delete" command


"delete" - to indicate the system that this is a delete function
"taskNumber" - indicate the index number for the specific task (eg: 5)

Format: delete taskNumber

Eg: delete 5

IMPORTANT: User must provide a description and time.

Outcome:

Sheena: YAY! You deleted task: [T][✘]buy books !

Now you have (taskNumber-1) tasks in your list ~


### 6. Find a task

Users are able to add one specific event task every time they type "find" command


"find" - to indicate the system that this is a todo task 
"description" - describe the thing you want to find

Format: find (description)

Eg: find Sheena

IMPORTANT: User must provide a description and time.

Outcome:
Sheena: Yay! Here are the matching tasks in your list:

----------------------------
Sheena: Well, you have these items in your list: 

 1. [T][✓]miss Sheena
 2. [T][✘]love Sheena forever
----------------------------
Sheena: Yay! 2 tasks listed!


### 7. Clear the list 

Users are able to clear the task list


"clear" - to indicate the system that user is clearing the task

Format: clear

Eg: clear

Outcome:
Sheena: So sad but yeah the list has been cleared..


### 8. Exit the program

Users are able to exit program. The task will be saved in a text file (eg: duke.txt)


"bye" - to indicate the system that user is going to exit the program

Format: bye

Eg: bye

Outcome:

Sheena: Let me save down everything ^^
 
Sheena: I will miss you! Hope to see you again soon!
