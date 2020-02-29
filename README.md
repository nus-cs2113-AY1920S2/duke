# Duke Task Manager
**Version by: nigellenl**

*  This is a CLI (Command Line Interface) Task Management application written in Java. 
*  It allows the user to record and manage their tasks with a number of features.
*  The tasks in the task list will be saved to a text file and the contents will be loaded into the list upon every start up.
*  This user guide will explain how to run and use this application.

## Running the Application
* Download the Jar file from https://github.com/nigellenl/duke/releases
* Open the Command Line and access the directory that the file is located at.
* Run the application using _java -jar "Individual Project.jar"_
* Enjoy!

## Application Features

Feature | Syntax | Description
--------|--------|------------
List | _**list**_ | Displays the list of tasks
Todo | _**todo** [task name]_ | Adds a todo task to the list
Event | _**event** [task name] **/at** [date]_ | Adds an event with a specified date to the list
Deadline | _**deadline** [task name] **/at** [date]_ | Adds a deadline with a specified date to the list
Done | _**done** [task number]_ | Marks the specified task as completed
Find | _**find** [keyword]_ | Returns a list of tasks that contain the specified keyword
Delete | _**delete** [task number]_ | Deletes the specified task from the list
Exit | _**bye**_ | Closes the application 

### Examples  
Add tasks:
> _todo return books_  
> _deadline english homework /by 2020-02-07_  
> _event group meeting /at 2020-01-29_  

Mark as done:
> _done 3_  

A task that is marked as done will be represented with the **✓** symbol while a task that is incomplete will be represented with the **✘** symbol. 

Display list:
> _list_

The list will be displayed in the following format:
> [T][✘] return books  
> [D][✘] english homework (by: Feb 7 2020)  
> [E][✓] group meeting (at: Jan 29 2020)  



Find tasks:
> _find books_

The following task(s) will be displayed:
> [T][✘] return books  

Delete tasks:
> _delete 1_ 

The following task will be removed:
> [T][✘] return books  

## Feedback, Bug Reports

* If you have feedback or bug reports, please contact me at e0028344@u.nus.edu.
* You can also submit a pull request at [nigellenl/duke](https://github.com/nigellenl/duke/).
