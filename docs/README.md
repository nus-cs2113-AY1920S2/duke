# User Guide

## Features 

#### 1) Add Tasks 
There are 3 types of tasks that can be added. They are:
1. Todo - A task that can be completed at any time. Cannot be assigned a date or time.
1. Event - A task that is going to occur at a specified date. Users must indicate the date of the event, while indicating the time is optional.
1. Deadline - A task that is due at a specified date. Users must indicate the date of the deadline, while indicating the time is optional.

#### 2) Mark Tasks as Done
Users are able to mark tasks as done once they have finished it. This allows for the chatbot to accurately check the status of all saved tasks for other functions that take into account the completion status of each task.

#### 3) Search for Tasks
The chatbot comes with a search function, and will return all tasks that contain the keyword or character sequence that the user has input.

#### 4) Manage and Remove Tasks
Users are able to manually remove tasks, based on their individual index number. Alternatively, users can choose to remove all tasks that have passed or all tasks that have already been completed, and the chatbot will automatically remove all such tasks.

#### 5) Keep Track of Events and Deadlines
This chatbot is also able to keep track of events and deadlines that has been entered by the user.

Upon initialisation of the chatbot, it will prompt the user on tasks that are due/occurring today, based on the System time of the computer. Users can also check uncompleted events/deadlines that have passed, and upcoming events/deadlines within a user-defined amount of days from today.

#### 6) Save All Tasks
All tasks will be automatically saved in an external text file `Duke.txt`. Please do not tamper with the location or contents of the text file, as this could result in the loss of your stored data.



## Usage

#### `help` - Accesses the help page

Shows the help page which contains all accepted commands and formats. Please check the help page if you are using this program for the first time.

Example of usage: 

`help`

Expected outcome:

<code>Welcome to the Help Page<br/>
Here is a list of valid commands and formats:<br/>
&nbsp;&nbsp;1) todo <task description>  -  adds a todo task<br/>
&nbsp;&nbsp;&nbsp; ...&nbsp;&nbsp;...&nbsp;&nbsp;...</code>

#### `todo` - Adds a todo task

A todo task will be added to the chatbot. By default, the todo task will be marked as undone. The chatbot will then reply with a confirmation that the command has been executed, and will inform the user of the number of tasks currently in the list.

Example of usage: 

`todo read book`

Expected outcome:

<code>Got it. I've added this task:<br/>
&nbsp;&nbsp;[T][N] read book<br/>
Now you have 1 task(s) in the list.</code>

#### `event` - Adds an event task

An event task will be added to the chatbot. By default, the event task will be marked as undone. The chatbot will then reply with a confirmation that the command has been executed, and will inform the user of the number of tasks currently in the list.<br/>
Events need to be added with a date, while a time is optional. For the full list of accepted date and time formats, please refer to the date and time format section below.<br/>

Events are added using the following format: `event <description of event> /at <date> <time>`

Example of usage: 

`event CCA meeting /at 2020-01-01 6PM`

Expected outcome:

<code>Got it. I've added this task:<br/>
&nbsp;&nbsp;[E][N] CCA meeting(at: 2020-01-01 18:00)<br/>
Now you have 2 task(s) in the list.</code>

#### `deadline` - Adds a deadline task

A deadline task will be added to the chatbot. By default, the deadline task will be marked as undone. The chatbot will then reply with a confirmation that the command has been executed, and will inform the user of the number of tasks currently in the list.<br/>
Deadlines need to be added with a date, while a time is optional. For the full list of accepted date and time formats, please refer to the date and time format section below.<br/>

Deadlines are added using the following format: `deadline <description of deadline> /by <date> <time>`

Example of usage (1):

`deadline finish reading book /by 3/3/2020 1015`

Expected outcome (1):

<code>Got it. I've added this task:<br/>
&nbsp;&nbsp;[D][N] finish reading book(by: 2020-03-03 10:15)<br/>
Now you have 3 task(s) in the list.</code>

Example of usage (2):

`deadline return book /by 4-Mar-2020`

Expected outcome (2):

<code>Got it. I've added this task:<br/>
&nbsp;&nbsp;[D][N] return book(by: 2020-03-04)<br/>
Now you have 4 task(s) in the list.</code>

#### `list` - Lists out all tasks

Displays all saved tasks in a list format for easy viewing, in order of when the task was added. Each task will also be assigned an index based on their position in the list. This index can be used for other functions such as deleting or making done a specified task.

Example of usage: 

`list`

Expected outcome:

<code>Here are the tasks in your list:<br/>
&nbsp;&nbsp;1. [T][N] read book<br/>
&nbsp;&nbsp;2. [E][N] CCA meeting(at: 2020-01-01 18:00)<br/>
&nbsp;&nbsp;3. [D][N] finish reading book(by: 2020-03-03 10:15)<br/>
&nbsp;&nbsp;4. [D][N] return book(by: 2020-03-04)</code>

#### `done` - Marks a selected task as done

Marks a task as done based on their index in the list (refer to `list` for more details). This changes the task's status to done, which is reflected by a `[Y]` instead of a `[N]` for undone tasks.

Tasks are marked as done using the following format: `done <index of task>`

Example of usage: 

`done 3`

Expected outcome:

<code>Nice! I've marked this task as done:<br/>
&nbsp;&nbsp;[D][Y] finish reading book(by: 2020-03-03 10:15)</code>

#### `delete` - Deletes a selected task

Deletes a task based on their index in the list (refer to `list` for more details). This removes the task from the list, and moves it into a deleted tasks list.

Tasks are deleted using the following format: `delete <index of task>`

Example of usage: 

`delete 4`

Expected outcome:

<code>Noted. I've removed this task:<br/>
&nbsp;&nbsp;[D][N] return book(by: 2020-03-04)</code>

#### `find` - Finds for certain tasks

Searches for and returns all tasks that contain the specified keyword or character sequence.

Tasks are searched using the following format: `find <keyword / characters>`

Example of usage: 

`find book`

Expected outcome:

<code>Here are the matching tasks in your list:<br/>
&nbsp;&nbsp;1. [T][N] read book<br/>
&nbsp;&nbsp;2. [D][Y] finish reading book(by: 2020-03-03 10:15)</code>

#### `show_overdue` - Lists all overdue tasks

Shows all tasks that have passed, but are still marked as undone. This does not delete the task from the chatbot.

Example of usage: 

`show_overdue`

Expected outcome:

<code>Here are the uncompleted overdue tasks:<br/>
&nbsp;&nbsp;1. [E][N] CCA meeting(at: 2020-01-01 18:00)</code>

#### `show_upcoming` - Lists all upcoming tasks

Shows all upcoming tasks within the number of days specified in the input command. This includes tasks that are already marked as done.

Upcoming tasks are shown using the following format: `show_upcoming <number of days>`

Example of usage: 

`show_upcoming 5`

Expected outcome:

<code>Here are the upcoming tasks within 5 day(s):<br/>
&nbsp;&nbsp;1. [D][Y] finish reading book(by: 2020-03-03 10:15)</code>

#### `remove_past` - Removes all tasks that have passed

Deletes all tasks that have passed, regardless of if the task has been marked as done or not.

Example of usage: 

`remove_past`

Expected outcome:

<code>All past tasks have been removed.</code>

#### `remove_done` - Removes all tasks that are marked as done

Deletes all tasks that are marked as done, regardless of if the task has passed or not.

Example of usage: 

`remove_done`

Expected outcome:

<code>All completed tasks have been removed.</code>

#### `show_deleted` - Lists all deleted tasks

Shows all tasks that have been deleted in this instance of the chatbot. This is cleared everytime the chatbot is terminated.

Example of usage: 

`show_deleted`

Expected outcome:

<code>Here are the tasks that have been removed:<br/>
&nbsp;&nbsp;1. [D][N] return book(by: 2020-03-04)<br/>
&nbsp;&nbsp;2. [E][N] CCA meeting(at: 2020-01-01 18:00)<br/>
&nbsp;&nbsp;3. [D][Y] finish reading book(by: 2020-03-03 10:15)</code>

#### `clear_all` - Deletes all tasks

Deletes all tasks. This action cannot be reversed, and all tasks saved in the external text file will be wiped as well.

Example of usage: 

`clear_all`

Expected outcome:

<code>All tasks have been cleared.</code>

#### `bye` - Terminates the program

Terminates the program and saves all tasks into an external text file `Duke.txt`. This text file will be read and loaded from when Duke is next initialised.

Example of usage: 

`bye`

Expected outcome:

<code>Bye. Hope to see you again soon!</code>



## Acceptable Date and Time Formats

#### Acceptable date formats:
1. date/month/year  (e.g. 1/3/19, 01/03/19, 1/3/2019, 01/03/2019, 2019/03/01)
1. date-month-year  (e.g. 1-3-19, 01-03-19, 1-3-2019, 01-03-2019)
1. date.month.year  (e.g. 1.3.19, 01.03.19, 1.3.2019, 01.03.2019)
1. date-MMM-year  (e.g. 1-Mar-19, 1-Mar-2019, 01-Mar-2019)

#### Acceptable time formats:
1. 12-hr time  (e.g. 10PM, 10:15PM, 10:15:00PM)
2. 24-hr time  (e.g. 2215, 22:15, 22:15:00)