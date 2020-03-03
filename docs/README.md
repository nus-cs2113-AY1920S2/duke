# User Guide
Duke is program which manages a task list  
Users can add 3 types of task  to the task list: `todo`,`event`,`deadline`  
Other functions includes `list`,`delete`, `find` and mark task as `done`.

##Starting up


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
<br><br>
**_Note: First-Time User or Missing duke.txt_**  
````
Unable to find File
````
- If there is no duke.txt found in the designated folder, the above message will be displayed.
- Duke will create a new duke.txt file in the designated folder 
to save all new tasks and changes made.


## Features 

|Command|Command Action| 
| :---: |:---:|
[todo](#todo)|Add new Todo
[event](#event)|Add new Event
[deadline](#deadline)|Add new Deadline
[done](#done)|Mark Task as Done
[list](#list)|Display all Tasks
[delete](#delete)|Delete Task
[find](#find)|Find Task
[bye](#bye)|Exit Program
<br>
    
<h4 id="todo">`todo` : Add new Todo</h4>
Create new `Todo` and adds task into Task List  
**Format** : <code>todo **task name**</code>  
Example : <code>todo **CS Tutorial**</code>  
Outcome :  
````
Got it . I've added this task:   
  [T][x] CS Tutorial   
Now you have 1 tasks in the list. 
````
___

####`event` : Add new Event
Create new `Event` and adds it into Task List  
**Format** : <code>event **event name** /at **date and time**</code>  
Example : <code>event **NUS open house** /at **28-02-2020 2pm**</code>  
Outcome :  
````
Got it . I've added this task:
  [E][x] NUS open house (at: 28-02-2020 2pm)   
Now you have 2 tasks in the list. 
````
___
####`deadline` : Add new Deadline
Create new `Deadline` and adds it into Task List  
**Format** : <code>deadline **task name** /by **deadline**</code>  
Example : <code>event **CS2113 iP** /by **02-03-2020 12pm**</code>  
Outcome :  
````
Got it . I've added this task:
  [D][x] CS2113 iP (by: 02-03-2020 12pm)   
Now you have 3 tasks in the list. 
````
___
####`done` : Mark Task as done
Find the corresponding task at given `Index` and mark it as `done`.  
**Format** : <code>done **Index**</code>  
Example : done **2**</code>  
Outcome :  
````
Nice! I've marked this task as done:
  [E][/] NUS open house (at: 28-02-2020 2pm)
````
___
####`list` : Display all Saved Tasks
`List` out all tasks stored and its task indexes from the Task List  
**Format** : <code>**list**</code>  
Example : <code>**list**</code>  
Outcome :  
````
Here are the tasks in your list:
1.[T][x] CS Tutorial
2.[E][/] NUS open house (at: 28-02-2020 2pm)
3.[D][x] CS2113 iP (by: 02-03-2020 12pm)
````
___
####`delete` : Delete Task
Remove the corresponding task at given `Index` from Task List  
**Format** : <code>delete **Index** </code>  
Example : <code>delete **2**</code>  
Outcome :  
````
Noted. I've removed this task:
  [E][/] NUS open house (at: 28-02-2020 2pm)  
Now you have 2 tasks in the list. 
````
___
####`find` : Find Task
`Find` and list out task from Task List that contains the `Keyword`  
Note: keyword is non-case sensitive.  
E.g. "TuToRiAL" will get the same result as "tutorial"  
**Format** : <code>find **keyword**</code>  
Example : <code>find **cS**</code>  
Outcome :  
````
Here are the matching tasks in your list:
1.[T][x] CS Tutorial
2.[D][x] CS2113 iP (by: 02-03-2020 12pm)
````
___

####`bye` : Exit Program
Process will terminate and exit the program  
**Format** : <code>**bye**</code>  
Example : <code>**bye**</code>  
Outcome :  
````
Bye. Hope to see you again soon!
````
___