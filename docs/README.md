# User Guide
Duke is program which manages a task list  
Users can add 3 types of task  to the task list: `todo`,`event`,`deadline`  
Other functions includes `list`,`delete`, `find` and mark task as `done`.

<h2>Starting up</h2>


**Using Command Line**
1. Open up command prompt

2. `cd` to the folder which `Duke.jar`'s directory

3. Type the following command to run `Duke.jar`
 ````
 java -jar Duke.jar
 ````
<br><br>
**Load and Save the Task List**
- Duke will automatically load up duke.txt from designated file path upon program start up.

- All changes made to Task List will automatically saved into duke.txt
<br>  
**_Note: First-Time User or Missing duke.txt_**  
````
Unable to find File
````
- If there is no duke.txt found in the designated folder, the above message will be displayed.
- Duke will create a new duke.txt file in the designated folder 
to save all new tasks and changes made.


## Features 

Command|Command Action
:---:|:---:
[todo](#todo)|Add new Todo
[event](#event)|Add new Event
[deadline](#deadline)|Add new Deadline
[done](#done)|Mark Task as Done
[list](#list)|Display all Tasks
[delete](#delete)|Delete Task
[find](#find)|Find Task
[bye](#bye)|Exit Program

<br>

<h4 id="todo"><code>todo</code> : Add new Todo</h4>
Create new `Todo` and adds task into Task List  
**Format** : <code>todo <b>task name</b></code>  
Example : <code>todo <b>CS Tutorial</b></code>  
Outcome :  
````
Got it . I've added this task:   
  [T][x] CS Tutorial   
Now you have 1 tasks in the list. 
````
___

<h4 id="event"><code>event</code> : Add new Event</h4>
Create new `Event` and adds it into Task List  
**Format** : <code>event <b>event name</b> /at <b>date and time</b></code>  
Example : <code>event <b>NUS open house</b> /at <b>28-02-2020 2pm</b></code>  
Outcome :  
````
Got it . I've added this task:
  [E][x] NUS open house (at: 28-02-2020 2pm)   
Now you have 2 tasks in the list. 
````
___
<h4 id="deadline"><code>deadline</code> : Add new Deadline</h4>
Create new `Deadline` and adds it into Task List  
**Format** : <code>deadline <b>task name</b> /by <b>deadline</b></code>  
Example : <code>event <b>CS2113 iP</b> /by <b>02-03-2020 12pm</b></code>  
Outcome :  
````
Got it . I've added this task:
  [D][x] CS2113 iP (by: 02-03-2020 12pm)   
Now you have 3 tasks in the list. 
````
___
<h4 id="done"><code>done</code> : Mark Task as done</h4>
Find the corresponding task at given `Index` and mark it as `done`  
Task Status Box: [x] -Incomplete | [ / ] Completed  
**Format** : <code>done <b>Index</b></code>  
Example : <code>done <b>2</b></code>  
Outcome :  
````
Nice! I've marked this task as done:
  [E][/] NUS open house (at: 28-02-2020 2pm)
````
___
<h4 id="list"><code>list</code> : Display all Saved Tasks</h4>
`List` out all tasks stored and its task indexes from the Task List  
**Format** : <code><b>list</b></code>  
Example : <code><b>list</b></code>  
Outcome :  
````
Here are the tasks in your list:
1.[T][x] CS Tutorial
2.[E][/] NUS open house (at: 28-02-2020 2pm)
3.[D][x] CS2113 iP (by: 02-03-2020 12pm)
````
___
<h4 id="delete"><code>delete</code> : Delete Task</h4>
Remove the corresponding task at given `Index` from Task List  
**Format** : <code>delete <b>Index</b> </code>  
Example : <code>delete <b>2</b></code>  
Outcome :  
````
Noted. I've removed this task:
  [E][/] NUS open house (at: 28-02-2020 2pm)  
Now you have 2 tasks in the list. 
````
___
<h4 id="find"><code>find</code> : Find Task</h4>
`Find` and list out task from Task List that contains the `Keyword`  
Note: keyword is non-case sensitive.  
E.g. "TuToRiAL" will get the same result as "tutorial"  
**Format** : <code>find <b>keyword</b></code>  
Example : <code>find <b>cS</b></code>  
Outcome :  
````
Here are the matching tasks in your list:
1.[T][x] CS Tutorial
2.[D][x] CS2113 iP (by: 02-03-2020 12pm)
````
___

<h4 id="bye"><code>bye</code> : Exit Program</h4>
Process will terminate and exit the program  
**Format** : <code><b>bye</b></code>  
Example : <code><b>bye</b></code>  
Outcome :  
````
Bye. Hope to see you again soon!
````
___