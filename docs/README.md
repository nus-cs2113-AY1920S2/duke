#User Guide

##1. Introduction
Duke - version LISA, is a CLI task scheduler that supports three 
different types of tasks (todo, event and deadline). 
It is simple to use, and comes with a save function to 
remember your

##2. Quick Start
1. Ensure you have Java 11 or above installed in your Computer

1. Download the latest duke.jar

1. Copy the file to the folder you want to use as the home folder.

1. Run the jar file using `java - jar duke.jar`

##3. Features

###3.1 - Add
There are three types of tasks users can add

#####Todo:
* Basic tasks for quick noting down, no date or time required 
* **Usage**: `todo <description>`
#####Event:
* Event type tasks, _date and time required_ 
* **Usage**: `event <description> / <date> <time>`
    * date format: YYYY-MM-DD
    * time format: HHMM  
#####Deadline:
* Tasks with a deadline, _date and time required_ 
* **Usage**: `deadline <description> / <date> <time>`
    * date format: YYYY-MM-DD
    * time format: HHMM  
###3.2 - List
* Lists all tasks
* **Usage**: `list`

###3.3 - Delete
* Deletes a task from the list
* **Usage**: `delete <task index>`
    * The index refers to the index number shown in the displayed task list.
    * use `list` to derive task index
    * index has to be an _integer_

###3.4 - Done
* Changes the status of a task to completed
* **Usage**: `done <task index>`
    * The index refers to the index number shown in the displayed task list.
    * use `list` to derive task index
    * index has to be an _integer_

###3.5 - Find
* Searches all task descriptions for supplied keyword
* **Usage**: `find <keyword>`
    * Keyword has to be a _**single word**_
    * Keyword is case _insensitive_

###3.6 - Help
* displays the set of commands supported
* **Usage**: `help`

###3.7 - Exit
* Exits the program
* **Usage**: `bye`

##4. Sample Usage

1. Adding new todo task: `todo homework before class` 
   * Expected outcome: adds "homework" to the list 

1. Adding new deadline task: `deadline bathe after class / 2020-02-29 1500`
    * Expected outcome: adds "bathe" to the list
 
1. Search for specific keyword: `find class`
    * Expected outcome: displays the above two tasks since they contain keyword "class" in description
    
 ##5. FAQ
 * How do I save my tasks?
    * Tasks are saved automatically and loaded upon start up of application
    * If unable to load, check the directory and file name
        * Default folder (windows): `C:\Users\<computer name>\Duke`
        * Default file name: `data.txt`
 
 ##6. Command Summary
 * **Add**:
    1. `todo <description>`
    1. `event <description> / <date> <time>`
    1. `deadline <description> / <date> <time>`
 * **List**: `list` 
 * **Delete**: `delete <task index>` 
 * **Done**: `done <task index>` 
 * **Find**: `find <keyword>` 
 * **Help**: `help`
 * **Exit**: `bye`