# Duke Task Manager
Duke is a simple CLI task manager to help you keep track of your tasks, deadlines and events.

## Getting Started

### Prerequisites
* JDK 11

### Installing

 1. Download the Duke.jar file
 2. Save it in an empty folder
 3. Open a command window in that folder
 4. Run the command `java -jar duke.jar`

## Usage
### Features

Duke allows you to save 3 different types of task into a task list. Upon completion of a task, you can mark it as 'done'. When the task is no longer relevant, you can delete it from your task list. Duke can also find specific tasks by word and date. Your task list will be saved upon termination of duke into a text file so that you can have the same task list the next time you start up Duke.

### Commands

    todo <description>

Adds a new todo task to the list

    deadline <description> / <YYYY-MM-DD>
    
Adds a new deadline task to the list

    event <description> / <YYYY-MM-DD>
    
Adds a new event to the list

    delete <task number>
    
Deletes the task

    list
    
List all tasks in the order they were added

    done <task number>
    
Marks the task  as done

    find <keyword>
    
Finds all tasks containing the keyword 

    date <YYYY-MM-DD>
    
Finds all event or deadlines with that date

    bye
    
Exits duke
