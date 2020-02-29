# User Guide
Version by: Anqi-nus
* This is a CLI (Command Line Interface) Personal Assistant Chatbot 
that helps a person to keep track of his/her daily tasks. 
* The tasks in the task list will be saved into a txt file named 
"myTasks.txt" under folder ".\data".
* The content of "myTasks.txt" will be loaded into the list upon 
every start up.
* This user guide will explain how to run and use this application.
    
## Running the Application
* Download the latest released Jar file from 
[here](https://github.com/Anqi-nus/duke/releases)

* Open the Command Line and access the directory that the file 
is located at.
* Run the application using *java -jar Duke.jar*
* Have fun!

## Features 
All possible input commands at any stage while running the application 
will be showed by a instruction list. When selecting the instructions, 
you should key in the respective number assigned to your desired 
instruction in the list. 

### Feature 1 - Add a task
At the main page, key in `1` to add your task.
Next, similarly, key in  `1` `2` or `3` to select the type of your event.  

After selected the task type,  
`1`: Todo task. Only task name is required.  
`2`: Deadline task. Upon seeing `Please enter the task:`, enter the
task name **ONLY**. After that, you will see the instruction 
`  Please enter the deadline of your task:`, that is when you can
input the deadline of your task.    
`3`: Event task. Similar to the deadline task, enter the task name 
and venue of your event task **ONLY** when the application is asking.

If the task is added successfully, the application will show a 
respond message and go back to the main page.

### Feature 2 - Print task list
At the main page, key in `2` to show the current task list.
The application will return to the main page after 
showing the list. 

### Feature 3 - Mark a task as done
**Do note that this is an irreversible action**  
At the main page, key in `3` to see the instructions.  
Select your task and no worries if you have key in a wrong 
number, as you will be given a chance to try again. 
However, if you key in anything but a number, an error 
message will appear and you will be directed back to the main page.  
If the task is marked as done successfully, the application 
will show a respond message and go back to the main page.
showing the list.  

### Feature 4 - Delete a task
**Do note that this is an irreversible action**  
At the main page, key in `4` to see the instructions.  
Select your task and no worries if you have key in a wrong 
number, as you will be given a chance to try again. 
However, if you key in anything but a number, an error 
message will appear and you will be directed back to the main page.  
If the task is deleted successfully, the application 
will show a respond message and return to the main page.
showing the list.  

### Feature 5 - Find a task
**Do note that this is case sensitive**  
At the main page, key in `5` and key in ` your keyword`. 
The application will show you list of tasks that contains 
the keyword.  

### Feature 6 - Clear the entire task list
**Do note that this is an irreversible action**  
At the main page, key in `6` to clear all tasks in the list.  

### Feature 7 - Close the application
At the main page, key in `7` to safely close the application.

##Feedback, Bug Reports
* If you have feedback or encountered any problems when using
Duke, please contact me at e0322837@u.nus.edu.  
