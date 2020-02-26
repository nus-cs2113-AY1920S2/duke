# User Guide

1. Introduction
2. Quick Start
3. Features  
    3.1 Viewing help: help  
    3.2 Adding task: 
     + Todo: todo  
     + Deadline: deadline  
     + Event: event  
     
    3.3 Listing all tasks: list  
    3.4 Mark task as completed: done  
    3.5 Delete a task: delete  
    3.6 Locating all information using keyword: find  
    3.7 Exit the program: bye  
4. FAQ
5. Command Summary

## 1. Introduction  
Duke is for those who prefer to use a desktop application to manage the tasks they have in life.
   More importantly, Duke is optimized for people who preferred to use Command Line Interface (CLI).  
## 2. Quick Start
   1. Ensure you have Java 11 or above installed in your Computer
   2. Download the latest duke.jar here.
   3. Copy the file to the folder you want to use as the home folder for your Duke
   4. Use Command Prompt or Integrated Development Environment's (IDE) terminal.
     Example: Intellij.   
     Type the command `java -jar <FILENAME>.jar` in the command line
   5. Type the command in the command box and press Enter to execute it
        e.g. typing help and pressing enter will display the help instructions
   6. Refer to Section 3, for details of each command

### Features 
```
Command Format:    
- Words in UPPER_CASE are the parameters to be supplied by the user e.g. find KEYWORD, 
KEYWORD is a parameter which can be used as find duke.
- Items in square brackets with a pipe [|] is to choose one of the parameter
e.g. [DATE|DAY] INFO can be used as deadline return book /by 2020-02-18 3pm or deadline return book /by Sun 3pm
```
## 3.1. Viewing help: help
`help` : Shows the program duke CLI instructions.
  
`Format:` help  
## 3.2. Adding a task  
### Todo: todo  
`todo:` Adds a todo task to the list  
`Format :` todo **DESCRIPTION**  
`Example: todo read book`

```
DEADLINE AND EVENT DATES|DAY

Notes : There are multiple ways to add the Date to deadline and event.

Separator Consists of . (dot), / (forward slash) and - (dash)  
Date Format:
Year[Separator]Month[Separator]Day
Day[Separator]Month[Separator]Year

yyyy/mm/dd 2020-02-18
yyyy/MMM/dd 2020-Feb-18

dd/mm/yyyy 18-02-2020
dd/MMM/yyyy 18-Feb-2020

Day Format:
Day (First character is Capitalize) - Consist of 3 letter E.g. Mon, Tue, ..., Sun   
Mon

Cautionary:   
Inputing invalid date can throw an error - [Invalid] or set to the last date of the month
 
```
### Deadline: deadline  
`deadline:` Adds a deadline task to the list.  
`Format :` deadline **DESCRIPTION** /by \[DATE|DAY\] **INFO**   
`Example: deadline return book /by Sun 2pm`
### Event: event
`event:` Adds a event task to the list  
`Format :` deadline **DESCRIPTION** /at \[DATE|DAY\] **INFO**   
`Example: event project meeting /at 2020-02-26 2-4pm NUS` 
## 3.3 Listing all tasks: list  
`list:` Displays all the tasks in the list with index number.  
`Format :` list  
## 3.4 Mark task as completed: done  
`done:` Marks the task as completed in the list with `index` number.  
`Format :` done **\<INDEX\>**   
`Example: done 1`
## 3.5 Delete a task: delete  
`delete:`Deletes a task from the list with `index` number.  
`Format :` delete **INDEX**   
`Example: delete 1`
## 3.6 Locating all information using keyword: find  
`find:` Finds the list of information using specific `keyword`.   
`Format :` find **\<KEYWORD\>**   
`Example: event project meeting /at 2020-02-26 2-4pm NUS`
## 3.7 Exit the program: bye  
`bye:` Terminate the program. 
`Format :` deadline **DESCRIPTION** /at \<\[DATE|DAY\]\> **\<INFO\>**   
`Example: event project meeting /at 2020-02-26 2-4pm NUS`
## 4.FAQ  
Q: Does the application save the data automatically?  
A: Yes, the application will save the tasks input by you automatically by creating a directory folder
`"data"` and the txt `duke.txt`  
## 5.Command Summary  
   - `Help` help  
   - `List` list  
   - `Todo` todo \<DESCRIPTION\>  
   - `Deadline` deadline \<DESCRIPTION\> /by \<\[DATE|DAY\]\> \<INFO\>  
   - `Event` event <DESCRPTION> /at \<\[DATE|DAY\]\> \<INFO\>  
   - `Done` done \<INDEX\>  
   - `Delete` delete \<INDEX\>  
   - `Find` find \<KEYWORD\>  
   - `Exit` bye  