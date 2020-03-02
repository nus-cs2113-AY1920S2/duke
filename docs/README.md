#User Guide

##<h2>1. Introduction</h2>
Duke - version LISA, is a CLI task scheduler that supports three 
different types of tasks (todo, event and deadline). 
It is simple to use, and comes with a save function to 
remember your

##<h2>2. Quick Start</h2>
1. Ensure you have Java 11 or above installed in your Computer

1. Download the latest duke.jar

1. Copy the file to the folder you want to use as the home folder.

1. Run the jar file using `java - jar duke.jar`

##<h2>3. Features</h2>

###<h3>3.1 - Add</h3>
There are three types of tasks users can add

#####<h5>Todo:</h5>
* Basic tasks for quick noting down, no date or time required 
* **Usage**: `todo <description>`
##### <h5>Event</h5>
* Event type tasks, _date and time required_ 
* **Usage**: `event <description> / <date> <time>`
    * date format: YYYY-MM-DD
    * time format: HHMM  
##### <h5>Deadline</h5>
* Tasks with a deadline, _date and time required_ 
* **Usage**: `deadline <description> / <date> <time>`
    * date format: YYYY-MM-DD
    * time format: HHMM  
###<h3>3.2 - List</h3>
* Lists all tasks
* **Usage**: `list`

###<h3>3.3 - Delete</h3>
* Deletes a task from the list
* **Usage**: `delete <task index>`
    * The index refers to the index number shown in the displayed task list.
    * use `list` to derive task index
    * index has to be an _integer_

###<h3>3.4 - Done</h3>
* Changes the status of a task to completed
* **Usage**: `done <task index>`
    * The index refers to the index number shown in the displayed task list.
    * use `list` to derive task index
    * index has to be an _integer_

###<h3>3.5 - Find</h3>
* Searches all task descriptions for supplied keyword
* **Usage**: `find <keyword>`
    * Keyword has to be a _**single word**_
    * Keyword is case _insensitive_

###<h3>3.6 - Help</h3>
* displays the set of commands supported
* **Usage**: `help`

###<h3>3.7 - Exit</h3>
* Exits the program
* **Usage**: `bye`

##<h2>4. Sample Usage</h2>

1. Adding new todo task: `todo homework before class` 
   * Expected outcome: adds "homework" to the list 

1. Adding new deadline task: `deadline bathe after class / 2020-02-29 1500`
    * Expected outcome: adds "bathe" to the list
 
1. Search for specific keyword: `find class`
    * Expected outcome: displays the above two tasks since they contain keyword "class" in description
    
 ##<h2>5. FAQ</h2>
 * How do I save my tasks?
    * Tasks are saved automatically and loaded upon start up of application
    * If unable to load, check the directory and file name
        * Default folder (windows): `C:\Users\<computer name>\Duke`
        * Default file name: `data.txt`
 
 ##<h2>6. Command Summary</h2>
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