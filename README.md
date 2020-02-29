# Duke 
**Version by: nigellenl**

*  This is a CLI (Command Line Interface) Task Management application written in Java. 
*  It allows the user to record and manage their tasks with a number of features.
*  The tasks in the task list will be saved to a text file and the contents will be loaded into the list upon every start up.
*  This user guide will explain how to run and use this application.

# Running the Application
* Download the Jar file from https://github.com/nigellenl/duke/releases
* Open the Command Line and access the directory that the file is located at.
* Run the application using _java -jar duke.jar_
* Enjoy!

# Application Features

Feature | Syntax | Description
--------|--------|------------
List | _**list**_ | Displays the list of tasks
Todo | _**todo** [task name]_ | Adds a todo task to the list
Event | _**event** [task name] **/at** [date]_ | Adds an event with a specified date to the list
Deadline | **deadline** [task name] **/at** [date]_ | Adds a deadline with a specified date to the list
Done | _**done** [task number]_ | Marks the specified task as completed
Find | _**find** [keyword]_ | Returns a list of tasks that contain the specified keyword
Delete | _**delete** [task number]_ | Deletes the specified task from the list
Exit | _**bye**_ | Closes the application 

# Feedback, Bug Reports

* If you have feedback or bug reports, please contact me at e0028344@u.nus.edu.
* You can also submit a pull request at [nigellenl/duke](https://github.com/nigellenl/duke/).
