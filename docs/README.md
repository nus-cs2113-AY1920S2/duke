# User Guide

## Introduction
Pupu is a task organiser that keeps your tasks, deadlines and events all in one place.

## Getting Started
1. Install Java 11 from oracle.
  
2. Download the jar file from released section.
  
3. Ensure that data.txt file is stored in a "lib" folder.
  
4. "lib" folder is in the same directory as the jar file.
  
5. To run, enter "java -jar duke.jar" in terminal in correct directory.

## Features

### Adding a Task
To add a todo: 

    todo [task description]
    
To add an event:
    
    event [event description] /at [location]
      
To add a deadline:
  
    deadline [deadline description] /at [date]
    
You're encouraged to enter date in the format YYYY-MM-DD.
  
### Listing all Tasks
  
    list
    
### Deleting a Task
  
    delete [task number]
    
### Marking a Task as Done
  
    done [task number]
    
### Finding a Task based on Keywords
  
    find [description]
    
### To exit the program
  
    bye
