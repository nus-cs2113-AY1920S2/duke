# Duke Task Manager
Duke is a simple CLI task manager to help you keep track of your tasks, deadlines and events.

## Getting Started

### Prerequisites
* JDK 11

### Installing

 1. Download the Duke.jar file from [https://github.com/btricec/duke/releases](https://github.com/btricec/duke/releases)
 2. Save it in an empty folder
 3. Open a command window in that folder
 4. Run the command `java -jar duke.jar`

## Usage
### Features

Duke allows you to save 3 different types of task into a task list. 

 - todo [T] 
	Tasks that need to be completed.
- deadline [D]
	Tasks that need to be completed by a certain deadline.
- event [E]
	Upcoming events to take note of.

You can view all your task with a simple `list` command.
Tasks will appear as such:
1.  [T][✓] English homework 
2.  [T][✘] German homework 
3.  [D][✓] Physics assignment (by: Mar 1 2020)
4.  [E][✘] Mathematics quiz (at: Mar 5 2020)
5.  [E][✓] Field trip to the zoo (at: Mar 1 2020)

The first bracket indicates the type of task. The second bracket indicates whether the task has been completed or not. This is followed by the task description and date in brackets (where applicable).

You can also search for tasks by ***date*** or ***keyword***. Searching by keywords is case-insensitive! 

The first time you start up Duke, a text file `save.txt` will be created. Your task list is automatically saved into this file every time you update your list to ensure that changes are saved even when Duke is terminated prematurely. Upon subsequent start ups, your saved date will be read from this text file so that you can continue from where you left off!

### Commands
Here are all the commands that Duke understands, do note that they are all case-insensitive.

    todo <description>
  
**Adds a new todo task to the list.**
Example: `todo Homework`


    deadline <description> / <date>
    
**Adds a new deadline task to the list.** 
Date must be entered in the format YYYY-MM-DD.
Example: `deadline Book Report / 2020-02-29`

    event <description> / <YYYY-MM-DD>
    
**Adds a new event to the list.** 
Date must be entered in the format YYYY-MM-DD.
Example: `event Natalie's Birthday / 2020-10-05`

    delete <task number>
    
**Deletes the task.**
Example: `delete 1`
This will delete the task **English Homework** in the example given above.

    list
    
**List all tasks in the order they were added**

    done <task number>
    
**Marks the task  as done.**
Example: `done 2`
This will change the [✘] for **German Homework** to a [✓] in the example given above.

    find <keyword>
    
**Finds all tasks containing the keyword.** 
Find is case-insensitive.
Example: `find HoMeWoRk`
For the example above, this will return:
[T][✓] English homework 
[T][✘] German homework 

    date <date>
    
**Finds all events or deadlines with that date.**
Date must be entered in the format YYYY-MM-DD.
Example:`date 2020-03-01`
For the example above, this will return:
[D][✓] Physics assignment (by: Mar 1 2020)
[E][✓] Field trip to the zoo (at: Mar 1 2020)

    bye
    
**Exits duke.**
